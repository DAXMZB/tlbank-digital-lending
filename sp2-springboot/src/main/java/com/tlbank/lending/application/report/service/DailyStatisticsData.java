package com.tlbank.lending.application.report.service;

import java.time.LocalDate;
import java.util.Map;

import com.tlbank.lending.domain.application.ApplicationStatus;

/**
 * Aggregated daily application statistics used for report generation.
 */
public record DailyStatisticsData(
        LocalDate reportDate,
        long totalApplications,
        Map<ApplicationStatus, Long> statusCounts,
        Map<String, Long> productCounts
) {
}
