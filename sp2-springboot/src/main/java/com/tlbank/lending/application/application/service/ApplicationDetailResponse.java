package com.tlbank.lending.application.application.service;

import java.time.LocalDateTime;
import java.util.List;

import com.tlbank.lending.domain.application.ApplicationStatus;

/**
 * Detailed response DTO for a credit card application with masked sensitive fields.
 */
public record ApplicationDetailResponse(
        String applicationId,
        MaskedApplicantResponse maskedApplicant,
        String cardProductName,
        ApplicationStatus status,
        List<WorkflowHistoryResponse> workflowHistories,
        List<DocumentInfoResponse> documents,
        LocalDateTime submittedAt,
        LocalDateTime createdAt
) {
}
