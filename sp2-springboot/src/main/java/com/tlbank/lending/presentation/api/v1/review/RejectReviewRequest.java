package com.tlbank.lending.presentation.api.v1.review;

import jakarta.validation.constraints.NotBlank;

/**
 * Request body for rejecting a review case.
 */
public record RejectReviewRequest(
        @NotBlank String remark
) {
}
