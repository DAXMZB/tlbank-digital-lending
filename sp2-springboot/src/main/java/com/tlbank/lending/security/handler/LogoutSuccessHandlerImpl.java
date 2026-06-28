package com.tlbank.lending.security.handler;

import java.io.IOException;

import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.tlbank.lending.common.audit.AuditAction;
import com.tlbank.lending.common.audit.AuditIpResolver;
import com.tlbank.lending.common.audit.AuditLog;
import com.tlbank.lending.common.audit.AuditLogRepository;
import com.tlbank.lending.common.response.ApiResponse;
import com.tlbank.lending.security.util.JsonResponseWriter;
import com.tlbank.lending.security.util.LoginResponseMode;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Handles successful logout by returning a JSON 200 response.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    private final JsonResponseWriter jsonResponseWriter;
    private final AuditLogRepository auditLogRepository;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
            org.springframework.security.core.Authentication authentication) throws IOException {
        if (authentication != null) {
            log.debug("User '{}' logged out successfully", authentication.getName());
            auditLogRepository.save(AuditLog.builder()
                    .username(authentication.getName())
                    .action(AuditAction.USER_LOGOUT)
                    .ipAddress(AuditIpResolver.resolveClientIp(request))
                    .result("SUCCESS")
                    .build());
        }
        if (LoginResponseMode.prefersJson(request)) {
            jsonResponseWriter.write(response, HttpServletResponse.SC_OK,
                    ApiResponse.success(null, "Logout successful"));
            return;
        }

        response.sendRedirect(request.getContextPath() + "/login");
    }
}
