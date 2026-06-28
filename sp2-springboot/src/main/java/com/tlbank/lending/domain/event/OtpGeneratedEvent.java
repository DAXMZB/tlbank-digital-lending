package com.tlbank.lending.domain.event;

/**
 * Domain event published when an OTP is generated (for future notification use).
 */
public record OtpGeneratedEvent(
        String mobile,
        String otpCode
) {
}
