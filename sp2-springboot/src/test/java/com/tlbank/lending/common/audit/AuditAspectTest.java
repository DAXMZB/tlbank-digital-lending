package com.tlbank.lending.common.audit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.concurrent.Executor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
@Import(AuditAspectTest.TestConfig.class)
class AuditAspectTest {

    private static final String SUCCESS_APPLICATION_ID = "APP-20250607120000-9001";
    private static final String FAILURE_APPLICATION_ID = "APP-20250607120000-9002";

    @Autowired
    private AuditableTestService auditableTestService;

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Test
    void auditableMethod_onSuccess_shouldSaveAuditLogWithSuccessResult() {
        long beforeCount = countSuccessLogs(SUCCESS_APPLICATION_ID);

        String result = auditableTestService.success(SUCCESS_APPLICATION_ID);

        assertThat(result).isEqualTo(SUCCESS_APPLICATION_ID);
        awaitSuccessLog(SUCCESS_APPLICATION_ID, beforeCount + 1);

        AuditLog saved = auditLogRepository.findAll().stream()
                .filter(log -> log.getAction() == AuditAction.DOCUMENT_UPLOAD)
                .filter(log -> "SUCCESS".equals(log.getResult()))
                .filter(log -> log.getDetail() != null && log.getDetail().contains(SUCCESS_APPLICATION_ID))
                .reduce((first, second) -> second)
                .orElseThrow();

        assertThat(saved.getResult()).isEqualTo("SUCCESS");
        assertThat(saved.getDetail()).contains("applicationId=" + SUCCESS_APPLICATION_ID);
    }

    @Test
    void auditableMethod_onFailure_shouldSaveAuditLogWithFailureResult() {
        long beforeCount = countFailureLogs();

        assertThatThrownBy(() -> auditableTestService.failure(FAILURE_APPLICATION_ID))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("audit failed");

        awaitFailureLog(beforeCount + 1);

        AuditLog saved = auditLogRepository.findAll().stream()
                .filter(log -> log.getAction() == AuditAction.DOCUMENT_UPLOAD)
                .filter(log -> "FAILURE".equals(log.getResult()))
                .filter(log -> "audit failed".equals(log.getDetail()))
                .reduce((first, second) -> second)
                .orElseThrow();

        assertThat(saved.getResult()).isEqualTo("FAILURE");
        assertThat(saved.getDetail()).isEqualTo("audit failed");
    }

    private long countSuccessLogs(String applicationId) {
        return auditLogRepository.findAll().stream()
                .filter(log -> log.getAction() == AuditAction.DOCUMENT_UPLOAD)
                .filter(log -> "SUCCESS".equals(log.getResult()))
                .filter(log -> log.getDetail() != null && log.getDetail().contains(applicationId))
                .count();
    }

    private void awaitSuccessLog(String applicationId, long expectedCount) {
        try {
            for (int i = 0; i < 20 && countSuccessLogs(applicationId) < expectedCount; i++) {
                Thread.sleep(50);
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        assertThat(countSuccessLogs(applicationId)).isEqualTo(expectedCount);
    }

    private void awaitFailureLog(long expectedCount) {
        try {
            for (int i = 0; i < 20 && countFailureLogs() < expectedCount; i++) {
                Thread.sleep(50);
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        assertThat(countFailureLogs()).isEqualTo(expectedCount);
    }

    private long countFailureLogs() {
        return auditLogRepository.findAll().stream()
                .filter(log -> log.getAction() == AuditAction.DOCUMENT_UPLOAD)
                .filter(log -> "FAILURE".equals(log.getResult()))
                .filter(log -> "audit failed".equals(log.getDetail()))
                .count();
    }

    @TestConfiguration
    static class TestConfig {

        @Bean
        AuditableTestService auditableTestService() {
            return new AuditableTestService();
        }

        @Bean(name = {
                TaskExecutionAutoConfiguration.APPLICATION_TASK_EXECUTOR_BEAN_NAME,
                "taskExecutor"
        })
        @Primary
        Executor syncTaskExecutor() {
            return Runnable::run;
        }
    }
}
