package com.tlbank.lending.application.notification.service;

import org.springframework.stereotype.Service;

import com.tlbank.lending.common.util.MaskingUtil;
import com.tlbank.lending.infrastructure.notification.EmailMessage;
import com.tlbank.lending.infrastructure.notification.EmailSender;
import com.tlbank.lending.infrastructure.notification.NotificationTemplate;
import com.tlbank.lending.infrastructure.notification.SmsMessage;
import com.tlbank.lending.infrastructure.notification.SmsSender;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Sends SMS and email notifications using centralized templates.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final SmsSender smsSender;
    private final EmailSender emailSender;

    @Override
    public void sendOtpNotification(String mobile, String otpCode, int expireMinutes) {
        sendSmsSafely(mobile, NotificationTemplate.formatOtpSms(otpCode, expireMinutes));
        sendEmailSafely(
                "notifications@tlbank.local",
                NotificationTemplate.OTP_EMAIL_SUBJECT,
                NotificationTemplate.formatOtpEmailBody(expireMinutes));
    }

    @Override
    public void sendApplicationSubmittedNotification(String applicationId, String mobile, String email) {
        sendSmsSafely(mobile, NotificationTemplate.formatSubmitSms(applicationId));
        sendEmailSafely(
                email,
                NotificationTemplate.formatSubmitEmailSubject(applicationId),
                NotificationTemplate.formatSubmitEmailBody(applicationId));
    }

    @Override
    public void sendApplicationApprovedNotification(String applicationId, String mobile, String email) {
        sendSmsSafely(mobile, NotificationTemplate.formatApprovedSms(applicationId));
        sendEmailSafely(
                email,
                NotificationTemplate.formatApprovedEmailSubject(applicationId),
                NotificationTemplate.formatApprovedEmailBody(applicationId));
    }

    @Override
    public void sendApplicationRejectedNotification(String applicationId, String mobile, String email) {
        sendSmsSafely(mobile, NotificationTemplate.formatRejectedSms(applicationId));
        sendEmailSafely(
                email,
                NotificationTemplate.formatRejectedEmailSubject(applicationId),
                NotificationTemplate.formatRejectedEmailBody(applicationId));
    }

    private void sendSmsSafely(String mobile, String message) {
        try {
            smsSender.send(new SmsMessage(mobile, message));
            log.debug("SMS notification sent to {}", MaskingUtil.maskMobile(mobile));
        } catch (Exception ex) {
            log.warn("Failed to send SMS notification to {}: {}",
                    MaskingUtil.maskMobile(mobile), ex.getMessage());
        }
    }

    private void sendEmailSafely(String email, String subject, String body) {
        if (email == null || email.isBlank()) {
            log.debug("Skipping email notification because recipient is blank");
            return;
        }
        try {
            emailSender.send(new EmailMessage(email, subject, body));
            log.debug("Email notification sent to {}", MaskingUtil.maskEmail(email));
        } catch (Exception ex) {
            log.warn("Failed to send email notification to {}: {}",
                    MaskingUtil.maskEmail(email), ex.getMessage());
        }
    }
}
