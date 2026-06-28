package com.tlbank.lending.application.audit.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.tlbank.lending.common.audit.AuditAction;

/**
 * Response DTO for an audit log entry.
 */
public record AuditLogResponse(
        Long logId,
        String username,
        AuditAction action,
        String ipAddress,
        String result,
        String detail,
        LocalDateTime createdAt
) {
}
