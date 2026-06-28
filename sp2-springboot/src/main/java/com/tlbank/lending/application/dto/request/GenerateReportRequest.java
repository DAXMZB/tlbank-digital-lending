package com.tlbank.lending.application.dto.request;

import java.time.LocalDate;

import com.tlbank.lending.application.report.service.ReportFormat;

import jakarta.validation.constraints.NotNull;

/**
 * Request DTO for generating a daily statistics report.
 */
public record GenerateReportRequest(
        @NotNull LocalDate reportDate,
        @NotNull ReportFormat format
) {
}
