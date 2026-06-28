package com.tlbank.lending.application.audit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.tlbank.lending.application.audit.service.AuditLogService;
import com.tlbank.lending.application.audit.service.NotificationLogResponse;
import com.tlbank.lending.common.audit.AuditAction;
import com.tlbank.lending.common.audit.AuditLog;
import com.tlbank.lending.common.audit.AuditLogRepository;
import com.tlbank.lending.common.response.PageResponse;

@ExtendWith(MockitoExtension.class)
class AuditLogServiceTest {

    @Mock
    private AuditLogRepository auditLogRepository;

    @InjectMocks
    private AuditLogService auditLogService;

    @Test
    void searchNotificationAttempts_shouldExtractOtpCodeFromDetail() {
        AuditLog otpLog = AuditLog.builder()
                .logId(1L)
                .username("admin")
                .action(AuditAction.OTP_SEND)
                .result("SUCCESS")
                .detail("applicationId=APP-001, mobile=0912****78, otpCode=654321")
                .createdAt(LocalDateTime.of(2024, 1, 1, 12, 0))
                .build();

        when(auditLogRepository.findByActionIn(any(), any(PageRequest.class)))
                .thenReturn(new PageImpl<>(List.of(otpLog)));

        PageResponse<NotificationLogResponse> response =
                auditLogService.searchNotificationAttempts(PageRequest.of(0, 20));

        assertThat(response.content()).hasSize(1);
        assertThat(response.content().get(0).otpCode()).isEqualTo("654321");
    }
}
