package com.tlbank.lending.common.response;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import com.tlbank.lending.common.exception.ErrorCode;

/**
 * Uniform API response envelope for all REST endpoints.
 *
 * @param <T> type of the response payload
 */
public record ApiResponse<T>(
        boolean success,
        T data,
        String errorCode,
        String message,
        List<FieldErrorDetail> fieldErrors,
        LocalDateTime timestamp
) {

    /**
     * Creates a successful response wrapping the given data.
     */
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, null, null, null, LocalDateTime.now());
    }

    /**
     * Creates a successful response with a custom message.
     */
    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(true, data, null, message, null, LocalDateTime.now());
    }

    /**
     * Creates an error response for the given error code.
     */
    public static <T> ApiResponse<T> error(ErrorCode code) {
        return new ApiResponse<>(false, null, code.name(), code.name(), null, LocalDateTime.now());
    }

    /**
     * Creates an error response with a custom message and optional field-level errors.
     */
    public static <T> ApiResponse<T> error(ErrorCode code, String message, List<FieldErrorDetail> fieldErrors) {
        return new ApiResponse<>(false, null, code.name(), message,
                fieldErrors == null ? null : Collections.unmodifiableList(fieldErrors),
                LocalDateTime.now());
    }
}
