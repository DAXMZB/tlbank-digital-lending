package com.tlbank.lending.security.handler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tlbank.lending.application.dto.response.LoginResponse;
import com.tlbank.lending.common.audit.AuditAction;
import com.tlbank.lending.common.audit.AuditIpResolver;
import com.tlbank.lending.common.audit.AuditLog;
import com.tlbank.lending.common.audit.AuditLogRepository;
import com.tlbank.lending.common.response.ApiResponse;
import com.tlbank.lending.infrastructure.persistence.user.UserEntity;
import com.tlbank.lending.infrastructure.persistence.user.UserJpaRepository;
import com.tlbank.lending.security.util.JsonResponseWriter;
import com.tlbank.lending.security.util.LoginResponseMode;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Handles successful form-login authentication by returning a JSON {@link LoginResponse}.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private static final int SESSION_TIMEOUT_MINUTES = 30;

    private final UserJpaRepository userJpaRepository;
    private final JsonResponseWriter jsonResponseWriter;
    private final AuditLogRepository auditLogRepository;

    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException {
        String username = authentication.getName();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime sessionExpiredAt = now.plusMinutes(SESSION_TIMEOUT_MINUTES);

        UserEntity user = userJpaRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("Authenticated user not found: " + username));

        user.updateLastLoginAt(now);
        userJpaRepository.save(user);

        List<String> roles = authentication.getAuthorities().stream()
                .map(authority -> authority.getAuthority().replace("ROLE_", ""))
                .toList();

        LoginResponse loginResponse = new LoginResponse(
                user.getUsername(),
                user.getFullName(),
                roles,
                sessionExpiredAt
        );

        log.debug("User '{}' logged in successfully", username);
        auditLogRepository.save(AuditLog.builder()
                .username(username)
                .action(AuditAction.USER_LOGIN)
                .ipAddress(AuditIpResolver.resolveClientIp(request))
                .result("SUCCESS")
                .detail("roles=" + String.join(",", roles))
                .build());

        if (LoginResponseMode.prefersJson(request)) {
            jsonResponseWriter.write(response, HttpServletResponse.SC_OK, ApiResponse.success(loginResponse));
            return;
        }

        response.sendRedirect(request.getContextPath() + resolveRedirectUrl(roles));
    }

    private String resolveRedirectUrl(List<String> roles) {
        if (roles.contains("ADMIN")) {
            return "/admin/users";
        }
        if (roles.contains("REVIEWER")) {
            return "/review/cases";
        }
        return "/";
    }
}
