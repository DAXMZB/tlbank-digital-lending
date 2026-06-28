package com.tlbank.lending.application.review.service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlbank.lending.application.application.service.DocumentInfoResponse;
import com.tlbank.lending.application.application.service.MaskedApplicantResponse;
import com.tlbank.lending.application.application.service.WorkflowHistoryResponse;
import com.tlbank.lending.common.audit.AuditAction;
import com.tlbank.lending.common.audit.Auditable;
import com.tlbank.lending.common.exception.BusinessException;
import com.tlbank.lending.common.exception.ErrorCode;
import com.tlbank.lending.common.response.PageResponse;
import com.tlbank.lending.common.util.MaskingUtil;
import com.tlbank.lending.domain.application.Address;
import com.tlbank.lending.domain.application.Applicant;
import com.tlbank.lending.domain.application.Application;
import com.tlbank.lending.domain.application.ApplicationId;
import com.tlbank.lending.domain.application.ApplicationStatus;
import com.tlbank.lending.domain.application.repository.ApplicationRepository;
import com.tlbank.lending.domain.event.ApplicationApprovedEvent;
import com.tlbank.lending.domain.event.ApplicationRejectedEvent;
import com.tlbank.lending.domain.product.CardProduct;
import com.tlbank.lending.domain.product.repository.CardProductRepository;
import com.tlbank.lending.domain.review.ReviewCase;
import com.tlbank.lending.domain.review.ReviewCaseId;
import com.tlbank.lending.domain.review.ReviewCaseSearchCriteria;
import com.tlbank.lending.domain.review.ReviewRemark;
import com.tlbank.lending.domain.review.ReviewStatus;
import com.tlbank.lending.domain.review.repository.ReviewCaseRepository;

import lombok.RequiredArgsConstructor;

/**
 * Application service for credit review case use cases.
 */
@Service
@RequiredArgsConstructor
public class ReviewAppService {

    private final ReviewCaseRepository reviewCaseRepository;
    private final ApplicationRepository applicationRepository;
    private final CardProductRepository cardProductRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final Clock clock;

    @Transactional(readOnly = true)
    public PageResponse<ReviewCaseSummaryResponse> searchCases(
            ReviewCaseSearchCriteria criteria,
            Pageable pageable) {
        Page<ReviewCase> page = reviewCaseRepository.search(criteria, pageable);

        List<ReviewCaseSummaryResponse> content = page.getContent().stream()
                .map(this::toSummary)
                .toList();

        return new PageResponse<>(
                content,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }

    @Transactional(readOnly = true)
    public ReviewCaseDetailResponse getCaseDetail(String reviewCaseId) {
        ReviewCase reviewCase = findReviewCaseOrThrow(reviewCaseId);
        Application application = findApplicationOrThrow(reviewCase.getApplicationId());
        return toDetail(reviewCase, application);
    }

    @Transactional
    public void startCaseReview(String reviewCaseId, String operator) {
        ReviewCase reviewCase = findReviewCaseOrThrow(reviewCaseId);
        Application application = findApplicationOrThrow(reviewCase.getApplicationId());

        if (reviewCase.getReviewStatus() == ReviewStatus.PENDING) {
            reviewCase.startReview(operator);
        }
        if (application.getStatus() == ApplicationStatus.SUBMITTED) {
            application.startReview(operator);
        }

        reviewCaseRepository.save(reviewCase);
        applicationRepository.save(application);
    }

    @Transactional
    @Auditable(action = AuditAction.APPLICATION_APPROVE)
    public void approveCase(ApproveCaseCommand command) {
        ReviewCase reviewCase = findReviewCaseOrThrow(command.reviewCaseId());
        Application application = findApplicationOrThrow(reviewCase.getApplicationId());

        reviewCase.approve(command.operator(), command.remark());
        ensureApplicationUnderReview(application, command.operator());
        application.approve(command.operator(), command.remark());

        reviewCaseRepository.save(reviewCase);
        applicationRepository.save(application);

        Applicant applicant = application.getApplicant();
        eventPublisher.publishEvent(new ApplicationApprovedEvent(
                application.getApplicationId().value(),
                applicant.mobile().value(),
                applicant.email().value(),
                LocalDateTime.now(clock)
        ));
    }

    @Transactional
    @Auditable(action = AuditAction.APPLICATION_REJECT)
    public void rejectCase(RejectCaseCommand command) {
        ReviewCase reviewCase = findReviewCaseOrThrow(command.reviewCaseId());
        Application application = findApplicationOrThrow(reviewCase.getApplicationId());

        reviewCase.reject(command.operator(), command.remark());
        ensureApplicationUnderReview(application, command.operator());
        application.reject(command.operator(), command.remark());

        reviewCaseRepository.save(reviewCase);
        applicationRepository.save(application);

        Applicant applicant = application.getApplicant();
        eventPublisher.publishEvent(new ApplicationRejectedEvent(
                application.getApplicationId().value(),
                applicant.mobile().value(),
                applicant.email().value(),
                LocalDateTime.now(clock)
        ));
    }

    @Transactional
    public ReviewRemarkResponse addRemark(AddRemarkCommand command) {
        ReviewCase reviewCase = findReviewCaseOrThrow(command.reviewCaseId());
        reviewCase.addRemark(command.content(), command.operator());
        ReviewCase saved = reviewCaseRepository.save(reviewCase);

        ReviewRemark latest = saved.getRemarks().get(saved.getRemarks().size() - 1);
        return toRemarkResponse(latest);
    }

    private void ensureApplicationUnderReview(Application application, String operator) {
        if (application.getStatus() == ApplicationStatus.SUBMITTED) {
            application.startReview(operator);
        }
    }

    private ReviewCaseSummaryResponse toSummary(ReviewCase reviewCase) {
        Application application = findApplicationOrThrow(reviewCase.getApplicationId());
        CardProduct product = cardProductRepository.findById(application.getCardProductId()).orElse(null);
        String productName = product != null ? product.getProductName() : application.getCardProductId().value();
        Applicant applicant = application.getApplicant();

        return new ReviewCaseSummaryResponse(
                reviewCase.getReviewCaseId().value(),
                reviewCase.getApplicationId(),
                applicant != null ? MaskingUtil.maskName(applicant.fullName()) : null,
                productName,
                reviewCase.getReviewStatus(),
                application.getSubmittedAt()
        );
    }

    private ReviewCaseDetailResponse toDetail(ReviewCase reviewCase, Application application) {
        CardProduct product = cardProductRepository.findById(application.getCardProductId()).orElse(null);
        String productName = product != null ? product.getProductName() : application.getCardProductId().value();

        return new ReviewCaseDetailResponse(
                reviewCase.getReviewCaseId().value(),
                reviewCase.getApplicationId(),
                reviewCase.getAssignedTo(),
                reviewCase.getReviewStatus(),
                reviewCase.getRemarks().stream().map(this::toRemarkResponse).toList(),
                reviewCase.getReviewedAt(),
                reviewCase.getCreatedAt(),
                reviewCase.getUpdatedAt(),
                toMaskedApplicant(application.getApplicant()),
                productName,
                application.getStatus(),
                application.getWorkflowHistories().stream()
                        .map(history -> new WorkflowHistoryResponse(
                                history.getHistoryId(),
                                history.getFromStatus(),
                                history.getToStatus(),
                                history.getOperator(),
                                history.getRemark(),
                                history.getOperatedAt()))
                        .toList(),
                application.getDocumentInfos().stream()
                        .map(doc -> new DocumentInfoResponse(
                                doc.documentType(),
                                doc.fileName(),
                                doc.uploadedAt()))
                        .toList(),
                application.getSubmittedAt()
        );
    }

    private ReviewRemarkResponse toRemarkResponse(ReviewRemark remark) {
        return new ReviewRemarkResponse(
                remark.getRemarkId(),
                remark.getReviewCaseId(),
                remark.getContent(),
                remark.getOperator(),
                remark.getCreatedAt()
        );
    }

    private MaskedApplicantResponse toMaskedApplicant(Applicant applicant) {
        if (applicant == null) {
            return null;
        }
        Address address = applicant.address();
        return new MaskedApplicantResponse(
                MaskingUtil.maskName(applicant.fullName()),
                MaskingUtil.maskNationalId(applicant.nationalId()),
                applicant.mobile().masked(),
                MaskingUtil.maskEmail(applicant.email().value()),
                applicant.dateOfBirth(),
                address.city(),
                address.district(),
                address.street(),
                address.zipCode()
        );
    }

    private ReviewCase findReviewCaseOrThrow(String reviewCaseId) {
        return reviewCaseRepository.findById(ReviewCaseId.of(reviewCaseId))
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.REVIEW_CASE_NOT_FOUND,
                        "Review case not found: " + reviewCaseId));
    }

    private Application findApplicationOrThrow(String applicationId) {
        return applicationRepository.findById(ApplicationId.of(applicationId))
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.APPLICATION_NOT_FOUND,
                        "Application not found: " + applicationId));
    }
}
