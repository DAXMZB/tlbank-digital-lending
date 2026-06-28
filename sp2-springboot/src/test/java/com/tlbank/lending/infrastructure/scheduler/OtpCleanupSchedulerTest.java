package com.tlbank.lending.infrastructure.scheduler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tlbank.lending.domain.otp.repository.OtpRepository;

@ExtendWith(MockitoExtension.class)
class OtpCleanupSchedulerTest {

    private static final ZoneId ZONE = ZoneId.systemDefault();
    private static final Clock FIXED_CLOCK = Clock.fixed(Instant.parse("2024-06-07T12:00:00Z"), ZONE);

    @Mock
    private OtpRepository otpRepository;

    @InjectMocks
    private OtpCleanupScheduler otpCleanupScheduler;

    @BeforeEach
    void setUp() {
        otpCleanupScheduler = new OtpCleanupScheduler(otpRepository, FIXED_CLOCK);
    }

    @Test
    void cleanupExpiredOtps_shouldCallRepositoryWithCurrentTime() {
        when(otpRepository.markExpiredBefore(any(LocalDateTime.class))).thenReturn(0);

        otpCleanupScheduler.cleanupExpiredOtps();

        ArgumentCaptor<LocalDateTime> cutoffCaptor = ArgumentCaptor.forClass(LocalDateTime.class);
        verify(otpRepository).markExpiredBefore(cutoffCaptor.capture());
        assertThat(cutoffCaptor.getValue()).isEqualTo(LocalDateTime.now(FIXED_CLOCK));
    }

    @Test
    void cleanupExpiredOtps_shouldLogCount() {
        when(otpRepository.markExpiredBefore(any(LocalDateTime.class))).thenReturn(3);

        assertThatCode(() -> otpCleanupScheduler.cleanupExpiredOtps()).doesNotThrowAnyException();

        verify(otpRepository).markExpiredBefore(any(LocalDateTime.class));
    }
}
