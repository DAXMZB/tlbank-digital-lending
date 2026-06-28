package com.tlbank.lending.security.util;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tlbank.lending.common.response.ApiResponse;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/**
 * Utility for writing {@link ApiResponse} objects as JSON HTTP responses.
 */
@Component
@RequiredArgsConstructor
public class JsonResponseWriter {

    private final ObjectMapper objectMapper;

    /**
     * Serializes the given response body and writes it to the servlet response.
     */
    public void write(HttpServletResponse response, int status, ApiResponse<?> body) throws IOException {
        response.setStatus(status);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        objectMapper.writeValue(response.getWriter(), body);
    }
}
