package com.tlbank.lending.infrastructure.notification;

/**
 * Email message payload for notification delivery.
 */
public record EmailMessage(String to, String subject, String body) {
}
