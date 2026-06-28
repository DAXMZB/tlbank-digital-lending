package com.tlbank.lending.common.response;

/**
 * Describes a single field-level validation error.
 */
public record FieldErrorDetail(
        String field,
        String message
) {
}
