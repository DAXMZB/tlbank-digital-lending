package com.tlbank.lending.infrastructure.notification;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Mock email sender that logs messages instead of sending real email.
 */
@Component
@ConditionalOnProperty(name = "tlbank.notification.mode", havingValue = "mock", matchIfMissing = true)
@Slf4j
public class MockEmailSender implements EmailSender {

    @Override
    public void send(EmailMessage message) {
        String body = message.body() != null ? message.body() : "";
        int previewLength = Math.min(50, body.length());
        log.info("[MOCK EMAIL] To: {} | Subject: {} | Body preview: {}",
                message.to(), message.subject(), body.substring(0, previewLength));
    }
}
