package com.tlbank.lending.application.review.service;

import java.time.LocalDateTime;

/**
 * Response DTO for a review remark.
 */
public record ReviewRemarkResponse(
        Long remarkId,
        String reviewCaseId,
        String content,
        String operator,
        LocalDateTime createdAt
) {
}
