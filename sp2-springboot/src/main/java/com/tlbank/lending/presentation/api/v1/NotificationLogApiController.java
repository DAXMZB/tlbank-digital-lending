package com.tlbank.lending.presentation.api.v1;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tlbank.lending.application.audit.service.AuditLogService;
import com.tlbank.lending.application.audit.service.NotificationLogResponse;
import com.tlbank.lending.common.config.StandardApiResponses;
import com.tlbank.lending.common.response.ApiResponse;
import com.tlbank.lending.common.response.PageResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * REST API for admin notification log queries.
 */
@RestController
@RequestMapping("/api/v1/admin/notifications")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Admin Notifications", description = "Notification log query APIs")
@StandardApiResponses
@RequiredArgsConstructor
public class NotificationLogApiController {

    private final AuditLogService auditLogService;

    @GetMapping
    @Operation(summary = "List notification logs", description = "Returns paginated OTP and application notification audit entries.")
    public ApiResponse<PageResponse<NotificationLogResponse>> listNotificationLogs(
            @Parameter(description = "Page index (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return ApiResponse.success(auditLogService.searchNotificationAttempts(pageable));
    }
}
