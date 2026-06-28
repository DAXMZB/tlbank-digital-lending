package com.tlbank.lending.common.exception;

/**
 * Standardized error codes for the TLBank Digital Lending Platform.
 * <p>
 * Each code maps to a specific HTTP status in {@link com.tlbank.lending.presentation.api.advice.GlobalExceptionHandler}.
 */
public enum ErrorCode {

    SYSTEM_ERROR,
    UNAUTHORIZED,
    FORBIDDEN,
    NOT_FOUND,
    VALIDATION_FAILED,
    APPLICATION_NOT_FOUND,
    INVALID_WORKFLOW_TRANSITION,
    APPLICATION_ALREADY_SUBMITTED,
    OTP_EXPIRED,
    OTP_MISMATCH,
    OTP_RETRY_EXCEEDED,
    REVIEW_CASE_NOT_FOUND,
    PRODUCT_NOT_FOUND,
    DOCUMENT_UPLOAD_FAILED,
    INCOMPLETE_DOCUMENTS,
    DUPLICATE_USERNAME,
    PARAMETER_NOT_FOUND,
    IDEMPOTENCY_KEY_CONFLICT,
    IDEMPOTENCY_KEY_IN_PROGRESS

}
