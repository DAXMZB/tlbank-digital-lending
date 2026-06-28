package com.tlbank.lending.common.audit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * AOP aspect that records audit logs for methods annotated with {@link Auditable}.
 */
@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class AuditAspect {

    private static final String ANONYMOUS = "ANONYMOUS";
    private static final String SUCCESS = "SUCCESS";
    private static final String FAILURE = "FAILURE";

    private final AuditLogWriter auditLogWriter;
    private final HttpServletRequest httpServletRequest;

    @Around("@annotation(auditable)")
    public Object audit(ProceedingJoinPoint joinPoint, Auditable auditable) throws Throwable {
        String username = resolveUsername();
        String ipAddress = AuditIpResolver.resolveClientIp(httpServletRequest);
        AuditAction action = auditable.action();

        try {
            Object result = joinPoint.proceed();
            auditLogWriter.saveAsync(AuditLog.builder()
                    .username(username)
                    .action(action)
                    .ipAddress(ipAddress)
                    .result(SUCCESS)
                    .detail(mergeDetail(AuditDetailBuilder.buildDetail(joinPoint.getArgs())))
                    .build());
            return result;
        } catch (Throwable ex) {
            AuditAction failureAction = resolveFailureAction(action);
            auditLogWriter.saveAsync(AuditLog.builder()
                    .username(username)
                    .action(failureAction)
                    .ipAddress(ipAddress)
                    .result(FAILURE)
                    .detail(truncateMessage(ex.getMessage()))
                    .build());
            throw ex;
        } finally {
            AuditContext.clear();
        }
    }

    private AuditAction resolveFailureAction(AuditAction action) {
        if (action == AuditAction.OTP_VERIFY_SUCCESS) {
            return AuditAction.OTP_VERIFY_FAILED;
        }
        return action;
    }

    private String resolveUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ANONYMOUS;
        }
        String name = authentication.getName();
        if (name == null || name.isBlank() || "anonymousUser".equals(name)) {
            return ANONYMOUS;
        }
        return name;
    }

    private String mergeDetail(String baseDetail) {
        String suffix = AuditContext.buildSuffix();
        if (suffix == null || suffix.isBlank()) {
            return baseDetail;
        }
        if (baseDetail == null || baseDetail.isBlank()) {
            return suffix;
        }
        return baseDetail + ", " + suffix;
    }

    private String truncateMessage(String message) {
        if (message == null) {
            return null;
        }
        return message.length() <= 500 ? message : message.substring(0, 500);
    }
}
