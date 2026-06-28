package com.tlbank.lending.common.audit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import lombok.extern.slf4j.Slf4j;

/**
 * Asynchronously persists audit log entries without blocking business operations.
 */
@Component
@Slf4j
public class AuditLogWriter {

    private final AuditLogRepository auditLogRepository;
    private final TransactionTemplate requiresNewTransaction;

    public AuditLogWriter(AuditLogRepository auditLogRepository, PlatformTransactionManager transactionManager) {
        this.auditLogRepository = auditLogRepository;
        this.requiresNewTransaction = new TransactionTemplate(transactionManager);
        this.requiresNewTransaction.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
    }

    @Async
    public void saveAsync(AuditLog auditLog) {
        try {
            requiresNewTransaction.executeWithoutResult(status -> auditLogRepository.save(auditLog));
        } catch (Exception ex) {
            log.warn("Failed to persist audit log for action {}: {}", auditLog.getAction(), ex.getMessage());
        }
    }
}
