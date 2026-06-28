package com.tlbank.lending.infrastructure.notification;

/**
 * SMS message payload for notification delivery.
 */
public record SmsMessage(String to, String message) {
}
