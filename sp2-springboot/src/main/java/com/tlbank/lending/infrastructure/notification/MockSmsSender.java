package com.tlbank.lending.infrastructure.notification;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.tlbank.lending.common.util.MaskingUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Mock SMS sender that logs messages instead of sending real SMS.
 */
@Component
@ConditionalOnProperty(name = "tlbank.notification.mode", havingValue = "mock", matchIfMissing = true)
@Slf4j
public class MockSmsSender implements SmsSender {

    public static final String OTP_MESSAGE =
            "【TLBank】Your OTP is {otpCode}. Valid for {minutes} minutes. Do not share.";

    @Override
    public void send(SmsMessage message) {
        log.info("[MOCK SMS] To: {} | Message: {}",
                MaskingUtil.maskMobile(message.to()), redactOtpCode(message.message()));
    }

    private String redactOtpCode(String message) {
        return message.replaceAll("Your OTP is \\d{6}", "Your OTP is ******");
    }
}
