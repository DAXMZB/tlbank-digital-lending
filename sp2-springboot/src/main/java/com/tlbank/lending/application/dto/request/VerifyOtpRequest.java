package com.tlbank.lending.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Request DTO for verifying an OTP.
 */
public record VerifyOtpRequest(
        @NotBlank @Schema(example = "APP-20250607120000-1234") String applicationId,
        @NotBlank @Schema(example = "0912345678") String mobile,
        @NotBlank @Size(min = 6, max = 6) @Schema(example = "123456") String otpCode
) {
}
