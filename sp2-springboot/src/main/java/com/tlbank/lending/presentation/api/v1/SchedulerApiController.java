package com.tlbank.lending.presentation.api.v1;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tlbank.lending.common.config.StandardApiResponses;
import com.tlbank.lending.common.response.ApiResponse;
import com.tlbank.lending.infrastructure.scheduler.CacheRefreshScheduler;
import com.tlbank.lending.infrastructure.scheduler.DailyStatisticsScheduler;
import com.tlbank.lending.infrastructure.scheduler.OtpCleanupScheduler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * REST API for manually triggering scheduled background tasks.
 */
@RestController
@RequestMapping("/api/v1/admin/schedulers")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Admin Schedulers", description = "Manual scheduler trigger APIs")
@StandardApiResponses
@RequiredArgsConstructor
public class SchedulerApiController {

    private final OtpCleanupScheduler otpCleanupScheduler;
    private final CacheRefreshScheduler cacheRefreshScheduler;
    private final DailyStatisticsScheduler dailyStatisticsScheduler;

    @PostMapping("/otp-cleanup/run")
    @Operation(summary = "Run OTP cleanup", description = "Manually triggers expired OTP cleanup.")
    public ApiResponse<String> runOtpCleanup() {
        otpCleanupScheduler.cleanupExpiredOtps();
        return ApiResponse.success("OTP cleanup triggered");
    }

    @PostMapping("/cache-refresh/run")
    @Operation(summary = "Run cache refresh", description = "Manually triggers cache refresh.")
    public ApiResponse<String> runCacheRefresh() {
        cacheRefreshScheduler.refreshCaches();
        return ApiResponse.success("Cache refresh triggered");
    }

    @PostMapping("/daily-stats/run")
    @Operation(summary = "Run daily statistics", description = "Manually triggers daily statistics generation for a date.")
    public ApiResponse<String> runDailyStatistics(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        dailyStatisticsScheduler.generateDailyStatistics(date);
        return ApiResponse.success("Daily statistics generation triggered for " + date);
    }
}
