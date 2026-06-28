package com.tlbank.lending.application.notification.service;

/**
 * Application service port for sending customer notifications.
 */
public interface NotificationService {

    void sendOtpNotification(String mobile, String otpCode, int expireMinutes);

    void sendApplicationSubmittedNotification(String applicationId, String mobile, String email);

    void sendApplicationApprovedNotification(String applicationId, String mobile, String email);

    void sendApplicationRejectedNotification(String applicationId, String mobile, String email);
}
