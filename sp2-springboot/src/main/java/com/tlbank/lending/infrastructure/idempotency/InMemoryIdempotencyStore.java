package com.tlbank.lending.infrastructure.idempotency;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * In-memory idempotency store for tests.
 * 測試用記憶體實作（不需 Redis）。
 */
@Component
@ConditionalOnProperty(name = "tlbank.idempotency.store", havingValue = "memory")
public class InMemoryIdempotencyStore implements IdempotencyStore {

    private final Map<String, TimedEntry> entries = new ConcurrentHashMap<>();
    private final Map<String, Instant> locks = new ConcurrentHashMap<>();

    @Override
    public Optional<IdempotencyEntry> find(String key) {
        TimedEntry timed = entries.get(key);
        if (timed == null) {
            return Optional.empty();
        }
        if (timed.expiresAt().isBefore(Instant.now())) {
            entries.remove(key);
            return Optional.empty();
        }
        return Optional.of(timed.entry());
    }

    @Override
    public void save(String key, IdempotencyEntry entry, long ttlSeconds) {
        entries.put(key, new TimedEntry(entry, Instant.now().plusSeconds(ttlSeconds)));
    }

    @Override
    public boolean tryAcquireLock(String lockKey, long ttlSeconds) {
        Instant now = Instant.now();
        locks.entrySet().removeIf(e -> e.getValue().isBefore(now));
        Instant expiresAt = now.plusSeconds(ttlSeconds);
        return locks.putIfAbsent(lockKey, expiresAt) == null;
    }

    @Override
    public void releaseLock(String lockKey) {
        locks.remove(lockKey);
    }

    /**
     * Wrapper holding entry + expiry for in-memory TTL simulation.
     * 內部 record：必須是 static，否則 Java 不允許在非 static 外部類別內定義。
     */
    private static record TimedEntry(IdempotencyEntry entry, Instant expiresAt) {
    }
}
