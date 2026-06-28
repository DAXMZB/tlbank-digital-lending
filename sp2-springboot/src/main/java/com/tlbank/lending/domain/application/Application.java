package com.tlbank.lending.domain.application;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.tlbank.lending.common.exception.BusinessException;
import com.tlbank.lending.common.exception.ErrorCode;
import com.tlbank.lending.common.exception.WorkflowException;

import lombok.Builder;
import lombok.Getter;

/**
 * Aggregate root representing a credit card application and its workflow lifecycle.
 */
@Getter
@Builder
public class Application {

    private static final EnumSet<ApplicationStatus> CANCELLABLE_STATUSES = EnumSet.of(
            ApplicationStatus.INIT,
            ApplicationStatus.OTP_VERIFIED,
            ApplicationStatus.DOCUMENT_UPLOADED
    );

    private static final Set<DocumentType> REQUIRED_DOCUMENT_TYPES = EnumSet.allOf(DocumentType.class);

    private ApplicationId applicationId;
    private Applicant applicant;
    private CardProductId cardProductId;
    private ApplicationStatus status;
    @Builder.Default
    private List<WorkflowHistory> workflowHistories = new ArrayList<>();
    @Builder.Default
    private List<DocumentInfo> documentInfos = new ArrayList<>();
    private LocalDateTime submittedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Transitions the application from INIT to OTP_VERIFIED after OTP verification.
     */
    public void verifyOtp(String operator) {
        transitionTo(ApplicationStatus.OTP_VERIFIED, operator, "OTP verified");
    }

    /**
     * Adds uploaded documents and transitions from OTP_VERIFIED to DOCUMENT_UPLOADED when applicable.
     */
    public void uploadDocuments(List<DocumentInfo> docs, String operator) {
        if (status == ApplicationStatus.OTP_VERIFIED) {
            transitionTo(ApplicationStatus.DOCUMENT_UPLOADED, operator, "Documents uploaded");
        } else if (status != ApplicationStatus.DOCUMENT_UPLOADED) {
            throw new WorkflowException("Cannot upload documents from status " + status);
        }
        this.documentInfos.addAll(docs);
    }

    /**
     * Submits the application for review.
     *
     * @throws BusinessException with {@link ErrorCode#INCOMPLETE_DOCUMENTS} when any required
     *         {@link DocumentType} is missing
     */
    public void submit(String operator) {
        validateRequiredDocuments();
        transitionTo(ApplicationStatus.SUBMITTED, operator, "Application submitted");
        this.submittedAt = LocalDateTime.now();
    }

    /**
     * Starts the review process for a submitted application.
     */
    public void startReview(String operator) {
        transitionTo(ApplicationStatus.UNDER_REVIEW, operator, "Review started");
    }

    /**
     * Approves the application under review.
     */
    public void approve(String operator, String remark) {
        transitionTo(ApplicationStatus.APPROVED, operator, remark);
    }

    /**
     * Rejects the application under review.
     */
    public void reject(String operator, String remark) {
        transitionTo(ApplicationStatus.REJECTED, operator, remark);
    }

    /**
     * Cancels the application when still in a cancellable status.
     */
    public void cancel(String operator, String reason) {
        if (!CANCELLABLE_STATUSES.contains(this.status)) {
            throw new WorkflowException("Cannot transition from " + status + " to " + ApplicationStatus.CANCELLED);
        }
        transitionTo(ApplicationStatus.CANCELLED, operator, reason);
    }

    private void validateRequiredDocuments() {
        Set<DocumentType> uploadedTypes = documentInfos.stream()
                .map(DocumentInfo::documentType)
                .collect(Collectors.toSet());
        if (!uploadedTypes.containsAll(REQUIRED_DOCUMENT_TYPES)) {
            throw new BusinessException(
                    ErrorCode.INCOMPLETE_DOCUMENTS,
                    "All required documents must be uploaded before submission: "
                            + REQUIRED_DOCUMENT_TYPES);
        }
    }

    private void transitionTo(ApplicationStatus next, String operator, String remark) {
        if (!status.canTransitionTo(next)) {
            throw new WorkflowException("Cannot transition from " + status + " to " + next);
        }

        workflowHistories.add(WorkflowHistory.builder()
                .applicationId(applicationId)
                .fromStatus(status)
                .toStatus(next)
                .operator(operator)
                .remark(remark)
                .operatedAt(LocalDateTime.now())
                .build());

        this.status = next;
    }
}
