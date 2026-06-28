package com.tlbank.lending.application.otp.service;

/**
 * Response DTO after successful OTP verification.
 */
public record VerifyOtpResponse(boolean verified, String applicationId) {
}
