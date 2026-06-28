package com.tlbank.lending.application.otp.service;

import java.time.LocalDateTime;

/**
 * Response DTO after sending an OTP.
 */
public record OtpResponse(String maskedMobile, LocalDateTime expiredAt, int remainingRetry) {
}
