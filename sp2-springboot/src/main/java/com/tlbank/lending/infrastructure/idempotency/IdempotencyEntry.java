package com.tlbank.lending.infrastructure.idempotency;
/**
 * Cached idempotent response entry.
 * 快取的冪等回應紀錄（stored response snapshot）。
 */
public record IdempotencyEntry(
    String requestHash,
    int httpStatus,
    String responseBody

) {
    
}
