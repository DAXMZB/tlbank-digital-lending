package com.tlbank.lending.domain.otp;

/**
 * Lifecycle status of an OTP record.
 */
public enum OtpStatus {
    PENDING,
    VERIFIED,
    EXPIRED,
    CANCELLED
}
