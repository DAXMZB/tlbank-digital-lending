package com.tlbank.lending.application.otp.service;

import com.tlbank.lending.domain.otp.OtpPurpose;

/**
 * Command object for sending an OTP to a mobile number.
 */
public record SendOtpCommand(String applicationId, String mobile, OtpPurpose purpose) {
}
