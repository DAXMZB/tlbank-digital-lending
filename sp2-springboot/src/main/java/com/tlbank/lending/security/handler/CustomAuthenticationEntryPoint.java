package com.tlbank.lending.security.handler;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.tlbank.lending.common.exception.ErrorCode;
import com.tlbank.lending.common.response.ApiResponse;
import com.tlbank.lending.security.util.JsonResponseWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Returns a JSON 401 response for API requests, or redirects browser users to the home page.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final JsonResponseWriter jsonResponseWriter;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException {
        log.debug("Unauthorized access to '{}': {}", request.getRequestURI(), authException.getMessage());
        if (isApiRequest(request)) {
            jsonResponseWriter.write(response, HttpServletResponse.SC_UNAUTHORIZED,
                    ApiResponse.error(ErrorCode.UNAUTHORIZED));
            return;
        }

        String redirectUrl = request.getContextPath() + "/?loginRequired=true";
        response.sendRedirect(redirectUrl);
    }

    private boolean isApiRequest(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        return request.getRequestURI().startsWith(contextPath + "/api/");
    }
}
