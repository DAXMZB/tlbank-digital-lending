package com.tlbank.lending.security.handler;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.tlbank.lending.common.audit.AuditAction;
import com.tlbank.lending.common.audit.AuditIpResolver;
import com.tlbank.lending.common.audit.AuditLog;
import com.tlbank.lending.common.audit.AuditLogRepository;
import com.tlbank.lending.common.exception.ErrorCode;
import com.tlbank.lending.common.response.ApiResponse;
import com.tlbank.lending.security.util.JsonResponseWriter;
import com.tlbank.lending.security.util.LoginResponseMode;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Handles failed login attempts by returning a JSON 401 response.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class LoginFailureHandler implements AuthenticationFailureHandler {

    private final JsonResponseWriter jsonResponseWriter;
    private final AuditLogRepository auditLogRepository;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException {
        String username = request.getParameter("username");
        log.debug("Login failed: {}", exception.getMessage());
        auditLogRepository.save(AuditLog.builder()
                .username(username != null && !username.isBlank() ? username : "ANONYMOUS")
                .action(AuditAction.USER_LOGIN_FAILED)
                .ipAddress(AuditIpResolver.resolveClientIp(request))
                .result("FAILURE")
                .detail("Invalid username or password")
                .build());

        if (LoginResponseMode.prefersJson(request)) {
            jsonResponseWriter.write(response, HttpServletResponse.SC_UNAUTHORIZED,
                    ApiResponse.error(ErrorCode.UNAUTHORIZED, "Invalid username or password", null));
            return;
        }

        response.sendRedirect(request.getContextPath() + "/login?error");
    }
}
