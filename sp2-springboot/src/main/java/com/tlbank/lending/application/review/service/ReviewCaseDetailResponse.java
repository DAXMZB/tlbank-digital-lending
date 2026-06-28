package com.tlbank.lending.application.review.service;

import java.time.LocalDateTime;
import java.util.List;

import com.tlbank.lending.application.application.service.DocumentInfoResponse;
import com.tlbank.lending.application.application.service.MaskedApplicantResponse;
import com.tlbank.lending.application.application.service.WorkflowHistoryResponse;
import com.tlbank.lending.domain.application.ApplicationStatus;
import com.tlbank.lending.domain.review.ReviewStatus;

/**
 * Detailed response for a review case including linked application data.
 */
public record ReviewCaseDetailResponse(
        String reviewCaseId,
        String applicationId,
        String assignedTo,
        ReviewStatus reviewStatus,
        List<ReviewRemarkResponse> remarks,
        LocalDateTime reviewedAt,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        MaskedApplicantResponse maskedApplicant,
        String productName,
        ApplicationStatus applicationStatus,
        List<WorkflowHistoryResponse> workflowHistories,
        List<DocumentInfoResponse> documents,
        LocalDateTime submittedAt
) {
}
