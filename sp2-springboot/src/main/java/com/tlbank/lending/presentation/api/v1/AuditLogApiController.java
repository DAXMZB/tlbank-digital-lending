package com.tlbank.lending.presentation.api.v1;

import java.time.LocalDate;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tlbank.lending.application.audit.service.AuditLogResponse;
import com.tlbank.lending.application.audit.service.AuditLogService;
import com.tlbank.lending.common.audit.AuditAction;
import com.tlbank.lending.common.config.StandardApiResponses;
import com.tlbank.lending.common.response.ApiResponse;
import com.tlbank.lending.common.response.PageResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * REST API for admin audit log queries.
 */
@RestController
@RequestMapping("/api/v1/admin/audit-logs")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Admin Audit Logs", description = "Audit log query APIs")
@StandardApiResponses
@RequiredArgsConstructor
public class AuditLogApiController {

    private final AuditLogService auditLogService;

    @GetMapping
    @Operation(summary = "Search audit logs", description = "Searches audit logs with optional filters and pagination.")
    public ApiResponse<PageResponse<AuditLogResponse>> searchAuditLogs(
            @Parameter(description = "Filter by operator username") @RequestParam(required = false) String username,
            @Parameter(description = "Filter by audit action type") @RequestParam(required = false) AuditAction action,
            @Parameter(description = "Created date from (inclusive)") @RequestParam(required = false) LocalDate dateFrom,
            @Parameter(description = "Created date to (inclusive)") @RequestParam(required = false) LocalDate dateTo,
            @Parameter(description = "Page index (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return ApiResponse.success(auditLogService.search(username, action, dateFrom, dateTo, pageable));
    }
}
