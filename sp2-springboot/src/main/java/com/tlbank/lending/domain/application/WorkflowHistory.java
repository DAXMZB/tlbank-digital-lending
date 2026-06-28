package com.tlbank.lending.domain.application;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

/**
 * Records a single workflow status transition for an application.
 */
@Getter
@Builder
public class WorkflowHistory {

    private Long historyId;
    private ApplicationId applicationId;
    private ApplicationStatus fromStatus;
    private ApplicationStatus toStatus;
    private String operator;
    private String remark;
    private LocalDateTime operatedAt;
}
