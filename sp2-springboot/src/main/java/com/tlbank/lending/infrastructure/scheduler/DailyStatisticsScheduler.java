package com.tlbank.lending.infrastructure.scheduler;

import java.time.Clock;
import java.time.LocalDate;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tlbank.lending.application.report.service.DailyStatisticsData;
import com.tlbank.lending.application.report.service.ReportDataService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Scheduled task that builds and logs daily application statistics.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class DailyStatisticsScheduler {

    private final ReportDataService reportDataService;
    private final Clock clock;

    @Scheduled(cron = "${tlbank.scheduler.daily-stats.cron}")
    public void generateDailyStatistics() {
        generateDailyStatistics(LocalDate.now(clock).minusDays(1));
    }

    public void generateDailyStatistics(LocalDate date) {
        long start = System.currentTimeMillis();
        try {
            log.info("[SCHEDULER] Daily statistics generation started for {}", date);
            DailyStatisticsData data = reportDataService.buildDailyStatistics(date);
            long elapsed = System.currentTimeMillis() - start;
            log.info(
                    "[SCHEDULER] Daily statistics completed in {}ms. total={}, by status={}, by product={}",
                    elapsed,
                    data.totalApplications(),
                    data.statusCounts(),
                    data.productCounts());
        } catch (Exception ex) {
            long elapsed = System.currentTimeMillis() - start;
            log.warn("[SCHEDULER] Daily statistics failed for {} after {}ms: {}",
                    date, elapsed, ex.getMessage(), ex);
        }
    }
}
