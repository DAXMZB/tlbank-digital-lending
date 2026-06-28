package com.tlbank.lending.application.application.service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.tlbank.lending.application.dto.request.CreateApplicationRequest;
import com.tlbank.lending.application.dto.response.DocumentUploadResponse;
import com.tlbank.lending.common.audit.AuditAction;
import com.tlbank.lending.common.audit.Auditable;
import com.tlbank.lending.common.exception.BusinessException;
import com.tlbank.lending.common.exception.ErrorCode;
import com.tlbank.lending.common.util.MaskingUtil;
import com.tlbank.lending.domain.application.Address;
import com.tlbank.lending.domain.application.Applicant;
import com.tlbank.lending.domain.application.Application;
import com.tlbank.lending.domain.application.ApplicationId;
import com.tlbank.lending.domain.application.ApplicationStatus;
import com.tlbank.lending.domain.application.CardProductId;
import com.tlbank.lending.domain.application.DocumentInfo;
import com.tlbank.lending.domain.application.DocumentType;
import com.tlbank.lending.domain.application.Email;
import com.tlbank.lending.domain.application.MobileNumber;
import com.tlbank.lending.domain.application.repository.ApplicationRepository;
import com.tlbank.lending.domain.event.ApplicationSubmittedEvent;
import com.tlbank.lending.domain.product.CardProduct;
import com.tlbank.lending.domain.product.repository.CardProductRepository;
import com.tlbank.lending.infrastructure.storage.DocumentStorageService;

import lombok.RequiredArgsConstructor;

/**
 * Application service for credit card application lifecycle use cases.
 */
@Service
@RequiredArgsConstructor
public class ApplicationAppService {

    private final ApplicationRepository applicationRepository;
    private final CardProductRepository cardProductRepository;
    private final DocumentStorageService documentStorageService;
    private final ApplicationEventPublisher eventPublisher;
    private final Clock clock;

    @Transactional
    public ApplicationSummaryResponse createApplication(CreateApplicationRequest request) {
        CardProductId cardProductId = CardProductId.of(request.cardProductId());
        CardProduct product = cardProductRepository.findById(cardProductId)
                .filter(CardProduct::isEnabled)
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.PRODUCT_NOT_FOUND,
                        "Card product not found: " + request.cardProductId()));

        Applicant applicant = toApplicant(request);

        Application application = Application.builder()
                .applicationId(ApplicationId.generate())
                .applicant(applicant)
                .cardProductId(cardProductId)
                .status(ApplicationStatus.INIT)
                .workflowHistories(new ArrayList<>())
                .documentInfos(new ArrayList<>())
                .build();

        Application saved = applicationRepository.save(application);

        return new ApplicationSummaryResponse(
                saved.getApplicationId().value(),
                saved.getStatus(),
                saved.getCreatedAt()
        );
    }

    @Transactional(readOnly = true)
    public ApplicationDetailResponse getApplication(String applicationId) {
        Application application = findApplicationOrThrow(applicationId);

        CardProduct product = cardProductRepository.findById(application.getCardProductId())
                .orElse(null);
        String productName = product != null ? product.getProductName() : application.getCardProductId().value();

        return new ApplicationDetailResponse(
                application.getApplicationId().value(),
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
                application.getSubmittedAt(),
                application.getCreatedAt()
        );
    }

    @Transactional
    @Auditable(action = AuditAction.DOCUMENT_UPLOAD)
    public DocumentUploadResponse uploadDocuments(
            String applicationId, DocumentType documentType, MultipartFile file) {
        Application application = findApplicationOrThrow(applicationId);

        documentStorageService.validate(file);
        String storagePath = documentStorageService.store(applicationId, documentType, file);

        DocumentInfo documentInfo = new DocumentInfo(
                documentType,
                file.getOriginalFilename(),
                storagePath,
                file.getSize(),
                LocalDateTime.now(clock)
        );

        application.uploadDocuments(List.of(documentInfo), "APPLICANT");
        applicationRepository.save(application);

        return new DocumentUploadResponse(
                documentType,
                file.getOriginalFilename(),
                documentInfo.uploadedAt()
        );
    }

    @Transactional
    @Auditable(action = AuditAction.APPLICATION_SUBMIT)
    public ApplicationSummaryResponse submitApplication(String applicationId) {
        Application application = findApplicationOrThrow(applicationId);

        application.submit("APPLICANT");
        Application saved = applicationRepository.save(application);

        eventPublisher.publishEvent(new ApplicationSubmittedEvent(
                saved.getApplicationId().value(),
                saved.getApplicant().mobile().value(),
                saved.getApplicant().email().value(),
                LocalDateTime.now(clock)
        ));

        return new ApplicationSummaryResponse(
                saved.getApplicationId().value(),
                saved.getStatus(),
                saved.getCreatedAt()
        );
    }

    @Transactional
    @Auditable(action = AuditAction.APPLICATION_CANCEL)
    public ApplicationSummaryResponse cancelApplication(String applicationId, String reason, String operator) {
        Application application = findApplicationOrThrow(applicationId);

        application.cancel(operator, reason);
        Application saved = applicationRepository.save(application);

        return new ApplicationSummaryResponse(
                saved.getApplicationId().value(),
                saved.getStatus(),
                saved.getCreatedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<CardProductResponse> findAllEnabledProducts() {
        return cardProductRepository.findAllEnabled().stream()
                .map(product -> new CardProductResponse(
                        product.getProductId().value(),
                        product.getProductCode(),
                        product.getProductName(),
                        product.getCardType(),
                        product.getFeatures().stream()
                                .map(feature -> new ProductFeatureResponse(
                                        feature.featureKey(),
                                        feature.featureValue()))
                                .toList(),
                        product.getCreatedAt()))
                .toList();
    }

    private Application findApplicationOrThrow(String applicationId) {
        return applicationRepository.findById(ApplicationId.of(applicationId))
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.APPLICATION_NOT_FOUND,
                        "Application not found: " + applicationId));
    }

    private Applicant toApplicant(CreateApplicationRequest request) {
        return new Applicant(
                request.applicant().fullName(),
                request.applicant().nationalId(),
                MobileNumber.of(request.applicant().mobile()),
                Email.of(request.applicant().email()),
                request.applicant().dateOfBirth(),
                new Address(
                        request.applicant().address().city(),
                        request.applicant().address().district(),
                        request.applicant().address().street(),
                        request.applicant().address().zipCode())
        );
    }

    private MaskedApplicantResponse toMaskedApplicant(Applicant applicant) {
        return new MaskedApplicantResponse(
                applicant.fullName(),
                MaskingUtil.maskNationalId(applicant.nationalId()),
                applicant.mobile().masked(),
                MaskingUtil.maskEmail(applicant.email().value()),
                applicant.dateOfBirth(),
                applicant.address().city(),
                applicant.address().district(),
                applicant.address().street(),
                applicant.address().zipCode()
        );
    }
}
