package com.tlbank.lending.application.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Request DTO for creating a new credit card application.
 */
public record CreateApplicationRequest(
        @Valid @NotNull ApplicantRequest applicant,
        @NotBlank String cardProductId
) {
}
