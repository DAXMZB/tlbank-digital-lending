package com.tlbank.lending.application.otp.service;

/**
 * Command object for verifying an OTP code.
 */
public record VerifyOtpCommand(String applicationId, String mobile, String otpCode) {
}
