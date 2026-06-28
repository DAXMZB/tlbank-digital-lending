package com.tlbank.lending.application.dto.request;

import com.tlbank.lending.domain.otp.OtpPurpose;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * Request DTO for sending an OTP.
 */
public record SendOtpRequest(
        @NotBlank @Schema(example = "APP-20250607120000-1234") String applicationId,
        @NotBlank @Pattern(regexp = "^09\\d{8}$") @Schema(example = "0912345678") String mobile,
        OtpPurpose purpose
) {
}
