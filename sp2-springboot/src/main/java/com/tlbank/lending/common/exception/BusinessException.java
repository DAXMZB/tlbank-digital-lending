package com.tlbank.lending.common.exception;

import lombok.Getter;

/**
 * Base runtime exception for domain and application-level business rule violations.
 * <p>
 * Carries an {@link ErrorCode} that is translated to an HTTP response by the global handler.
 */
@Getter
public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.name());
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
