package com.tlbank.lending.application.review.service;

import com.tlbank.lending.domain.review.ReviewStatus;

import java.time.LocalDateTime;

/**
 * Summary response for a review case in list views.
 */
public record ReviewCaseSummaryResponse(
        String reviewCaseId,
        String applicationId,
        String applicantName,
        String productName,
        ReviewStatus reviewStatus,
        LocalDateTime submittedAt
) {
}
