package com.tlbank.lending.application.dto.request;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Request DTO for applicant personal information.
 */
public record ApplicantRequest(
        @NotBlank @Schema(example = "John Doe") String fullName,
        @NotBlank @Schema(example = "A123456789") String nationalId,
        @NotBlank @Schema(example = "0912345678") String mobile,
        @NotBlank @Schema(example = "user@example.com") String email,
        @NotNull LocalDate dateOfBirth,
        @Valid @NotNull AddressRequest address
) {
}
