package com.tlbank.lending.application.audit.service;

import java.time.LocalDateTime;

import com.tlbank.lending.common.audit.AuditAction;

/**
 * Response DTO for notification-related audit log entries shown on the admin page.
 */
public record NotificationLogResponse(
        Long logId,
        String username,
        AuditAction action,
        String result,
        String detail,
        String otpCode,
        LocalDateTime createdAt
) {
}
