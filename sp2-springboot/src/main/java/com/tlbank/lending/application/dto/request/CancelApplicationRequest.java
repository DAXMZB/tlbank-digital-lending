package com.tlbank.lending.application.dto.request;

import jakarta.validation.constraints.NotBlank;

/**
 * Request DTO for cancelling a credit card application.
 */
public record CancelApplicationRequest(
        @NotBlank String reason
) {
}
