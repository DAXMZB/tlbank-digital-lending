package com.tlbank.lending.application.application.service;

import java.time.LocalDateTime;

import com.tlbank.lending.domain.application.ApplicationStatus;

/**
 * Summary response DTO for a credit card application.
 */
public record ApplicationSummaryResponse(
        String applicationId,
        ApplicationStatus status,
        LocalDateTime createdAt
) {
}
