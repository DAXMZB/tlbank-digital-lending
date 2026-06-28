package com.tlbank.lending.infrastructure.notification;

/**
 * Port for sending SMS notifications.
 */
public interface SmsSender {

    void send(SmsMessage message);
}
