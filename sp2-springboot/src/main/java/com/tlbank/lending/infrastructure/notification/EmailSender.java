package com.tlbank.lending.infrastructure.notification;

/**
 * Port for sending email notifications.
 */
public interface EmailSender {

    void send(EmailMessage message);
}
