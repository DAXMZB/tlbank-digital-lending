package com.tlbank.lending.application.application.service;

import java.time.LocalDateTime;

import com.tlbank.lending.domain.application.ApplicationStatus;

/**
 * Response DTO for a workflow history entry.
 */
public record WorkflowHistoryResponse(
        Long historyId,
        ApplicationStatus fromStatus,
        ApplicationStatus toStatus,
        String operator,
        String remark,
        LocalDateTime operatedAt
) {
}
