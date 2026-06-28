package com.tlbank.lending.infrastructure.scheduler;

import java.time.Clock;
import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tlbank.lending.domain.otp.repository.OtpRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Scheduled task that marks expired pending OTP records as EXPIRED.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class OtpCleanupScheduler {

    private final OtpRepository otpRepository;
    private final Clock clock;

    @Scheduled(cron = "${tlbank.scheduler.otp-cleanup.cron}")
    @Transactional
    public void cleanupExpiredOtps() {
        long start = System.currentTimeMillis();
        try {
            log.info("[SCHEDULER] OTP cleanup started");
            int count = otpRepository.markExpiredBefore(LocalDateTime.now(clock));
            long elapsed = System.currentTimeMillis() - start;
            log.info("[SCHEDULER] OTP cleanup completed. Marked {} records as EXPIRED in {}ms",
                    count, elapsed);
        } catch (Exception ex) {
            long elapsed = System.currentTimeMillis() - start;
            log.warn("[SCHEDULER] OTP cleanup failed after {}ms: {}", elapsed, ex.getMessage(), ex);
        }
    }
}
