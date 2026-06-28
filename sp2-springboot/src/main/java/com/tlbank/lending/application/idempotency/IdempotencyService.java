package com.tlbank.lending.application.idempotency;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HexFormat;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tlbank.lending.common.exception.BusinessException;
import com.tlbank.lending.common.exception.ErrorCode;
import com.tlbank.lending.common.response.ApiResponse;
import com.tlbank.lending.infrastructure.idempotency.IdempotencyEntry;
import com.tlbank.lending.infrastructure.idempotency.IdempotencyStore;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Coordinates idempotent API execution.
 * 協調冪等 API 執行：相同 Key + 相同 Body → 回傳快取；相同 Key + 不同 Body → 409。
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class IdempotencyService {

    private static final long LOCK_TTL_SECONDS = 30;

    private final IdempotencyStore idempotencyStore;
    private final ObjectMapper objectMapper;

    @Value("${tlbank.idempotency.ttl-hours:24}")
    private long ttlHours;

    @Value("${tlbank.idempotency.key-prefix:idempotency:applications:}")
    private String keyPrefix;

    public <T> ResponseEntity<ApiResponse<T>> execute(
            String idempotencyKey,
            Object requestBody,
            Supplier<ResponseEntity<ApiResponse<T>>> action) {

        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            return action.get();
        }

        String normalizedKey = idempotencyKey.trim();
        String storageKey = keyPrefix + normalizedKey;
        String lockKey = storageKey + ":lock";
        String requestHash = hashRequest(requestBody);
        long ttlSeconds = ttlHours * 3600L;

        var cached = idempotencyStore.find(storageKey);
        if (cached.isPresent()) {
            IdempotencyEntry entry = cached.get();
            if (!entry.requestHash().equals(requestHash)) {
                throw new BusinessException(
                        ErrorCode.IDEMPOTENCY_KEY_CONFLICT,
                        "Idempotency-Key reused with a different request payload");
            }
            log.debug("Returning cached idempotent response for key={}", normalizedKey);
            return rebuildResponse(entry);
        }

        if (!idempotencyStore.tryAcquireLock(lockKey, LOCK_TTL_SECONDS)) {
            throw new BusinessException(
                    ErrorCode.IDEMPOTENCY_KEY_IN_PROGRESS,
                    "A request with this Idempotency-Key is already in progress");
        }

        try {
            ResponseEntity<ApiResponse<T>> response = action.get();
            IdempotencyEntry entry = new IdempotencyEntry(
                    requestHash,
                    response.getStatusCode().value(),
                    serializeBody(response.getBody()));
            idempotencyStore.save(storageKey, entry, ttlSeconds);
            return response;
        } finally {
            idempotencyStore.releaseLock(lockKey);
        }
    }

    @SuppressWarnings("unchecked")
    private <T> ResponseEntity<ApiResponse<T>> rebuildResponse(IdempotencyEntry entry) {
        try {
            ApiResponse<T> body = objectMapper.readValue(entry.responseBody(), ApiResponse.class);
            return ResponseEntity.status(entry.httpStatus()).body(body);
        } catch (Exception ex) {
            throw new IllegalStateException("Failed to rebuild idempotent response", ex);
        }
    }

    private String serializeBody(Object body) {
        try {
            return objectMapper.writeValueAsString(body);
        } catch (Exception ex) {
            throw new IllegalStateException("Failed to serialize idempotent response body", ex);
        }
    }

    private String hashRequest(Object requestBody) {
        try {
            String json = objectMapper.writeValueAsString(requestBody);
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(json.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hash);
        } catch (Exception ex) {
            throw new IllegalStateException("Failed to hash request body", ex);
        }
    }
}
