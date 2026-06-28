package com.tlbank.lending.security.handler;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.tlbank.lending.common.exception.ErrorCode;
import com.tlbank.lending.common.response.ApiResponse;
import com.tlbank.lending.security.util.JsonResponseWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Returns a JSON 403 response when an authenticated user lacks the required authority.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final JsonResponseWriter jsonResponseWriter;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException {
        log.debug("Access denied for '{}': {}", request.getRequestURI(), accessDeniedException.getMessage());
        jsonResponseWriter.write(response, HttpServletResponse.SC_FORBIDDEN,
                ApiResponse.error(ErrorCode.FORBIDDEN));
    }
}
