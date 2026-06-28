package com.tlbank.lending.presentation.api.v1.review;

import jakarta.validation.constraints.NotBlank;

/**
 * Request body for adding a remark to a review case.
 */
public record AddRemarkRequest(
        @NotBlank String content
) {
}
