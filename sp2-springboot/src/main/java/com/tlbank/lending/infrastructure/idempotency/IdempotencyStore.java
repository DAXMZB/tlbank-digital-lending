package com.tlbank.lending.infrastructure.idempotency;

import java.util.Optional;
/**
 * Port for idempotency storage.
 * 冪等儲存介面（Redis / In-Memory 實作）。
 */
public interface IdempotencyStore {
    Optional<IdempotencyEntry> find(String key);

    void save(String key, IdempotencyEntry entry, long ttlSeconds);
    
    boolean tryAcquireLock(String lockKey, long ttlSeconds);

    void releaseLock(String lockKey);
}
