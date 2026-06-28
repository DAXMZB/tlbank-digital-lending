package com.tlbank.lending.application.audit.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlbank.lending.common.audit.AuditAction;
import com.tlbank.lending.common.audit.AuditLog;
import com.tlbank.lending.common.audit.AuditLogRepository;
import com.tlbank.lending.common.response.PageResponse;

import lombok.RequiredArgsConstructor;

/**
 * Application service for querying audit log records.
 */
@Service
@RequiredArgsConstructor
public class AuditLogService {

    private static final List<AuditAction> NOTIFICATION_ACTIONS = List.of(
            AuditAction.OTP_SEND,
            AuditAction.APPLICATION_SUBMIT,
            AuditAction.APPLICATION_APPROVE,
            AuditAction.APPLICATION_REJECT
    );

    private static final Pattern OTP_CODE_PATTERN = Pattern.compile("(?:^|,)\\s*otpCode=(\\d{6})");

    private final AuditLogRepository auditLogRepository;

    @Transactional(readOnly = true)
    public PageResponse<AuditLogResponse> search(
            String username,
            AuditAction action,
            LocalDate dateFrom,
            LocalDate dateTo,
            Pageable pageable) {
        LocalDateTime from = dateFrom != null
                ? dateFrom.atStartOfDay()
                : LocalDateTime.of(1970, 1, 1, 0, 0);
        LocalDateTime to = dateTo != null
                ? dateTo.plusDays(1).atStartOfDay()
                : LocalDateTime.of(2099, 12, 31, 23, 59, 59);

        String normalizedUsername = username != null && !username.isBlank() ? username.trim() : null;
        Page<AuditLog> page = auditLogRepository.search(normalizedUsername, action, from, to, pageable);

        return new PageResponse<>(
                page.getContent().stream().map(this::toResponse).toList(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }

    @Transactional(readOnly = true)
    public PageResponse<NotificationLogResponse> searchNotificationAttempts(Pageable pageable) {
        Page<AuditLog> page = auditLogRepository.findByActionIn(NOTIFICATION_ACTIONS, pageable);

        return new PageResponse<>(
                page.getContent().stream().map(this::toNotificationResponse).toList(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }

    private NotificationLogResponse toNotificationResponse(AuditLog auditLog) {
        return new NotificationLogResponse(
                auditLog.getLogId(),
                auditLog.getUsername(),
                auditLog.getAction(),
                auditLog.getResult(),
                auditLog.getDetail(),
                extractOtpCode(auditLog),
                auditLog.getCreatedAt()
        );
    }

    private String extractOtpCode(AuditLog auditLog) {
        if (auditLog.getAction() != AuditAction.OTP_SEND || auditLog.getDetail() == null) {
            return null;
        }
        Matcher matcher = OTP_CODE_PATTERN.matcher(auditLog.getDetail());
        return matcher.find() ? matcher.group(1) : null;
    }

    private AuditLogResponse toResponse(AuditLog auditLog) {
        return new AuditLogResponse(
                auditLog.getLogId(),
                auditLog.getUsername(),
                auditLog.getAction(),
                auditLog.getIpAddress(),
                auditLog.getResult(),
                auditLog.getDetail(),
                auditLog.getCreatedAt()
        );
    }
}
