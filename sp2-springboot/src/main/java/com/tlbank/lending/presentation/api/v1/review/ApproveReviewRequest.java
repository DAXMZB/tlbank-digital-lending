package com.tlbank.lending.presentation.api.v1.review;

import jakarta.validation.constraints.NotBlank;

/**
 * Request body for approving a review case.
 */
public record ApproveReviewRequest(
        @NotBlank String remark
) {
}
