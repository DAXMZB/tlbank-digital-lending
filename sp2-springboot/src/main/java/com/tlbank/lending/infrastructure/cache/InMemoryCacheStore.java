package com.tlbank.lending.infrastructure.cache;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Thread-safe in-memory implementation of {@link CacheStore}.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class InMemoryCacheStore implements CacheStore {

    private final ConcurrentHashMap<String, CacheEntry<?>> store = new ConcurrentHashMap<>();
    private final Clock clock;

    @Override
    @SuppressWarnings("unchecked")
    public <V> Optional<V> get(String key) {
        CacheEntry<?> entry = store.get(key);
        if (entry == null) {
            return Optional.empty();
        }
        if (entry.isExpired(clock)) {
            store.remove(key, entry);
            return Optional.empty();
        }
        return Optional.of((V) entry.value());
    }

    @Override
    public <V> void put(String key, V value, long ttlSeconds) {
        LocalDateTime expiresAt = LocalDateTime.now(clock).plusSeconds(ttlSeconds);
        store.put(key, new CacheEntry<>(value, expiresAt));
    }

    @Override
    public void evict(String key) {
        store.remove(key);
    }

    @Override
    public void evictAll() {
        store.clear();
    }

    @Override
    public Set<String> keys() {
        return Collections.unmodifiableSet(Set.copyOf(store.keySet()));
    }

    /**
     * Periodically removes expired entries from the store.
     */
    @Scheduled(fixedDelay = 60_000)
    public void cleanupExpiredEntries() {
        int removed = 0;
        for (var entry : store.entrySet()) {
            if (entry.getValue().isExpired(clock)) {
                if (store.remove(entry.getKey(), entry.getValue())) {
                    removed++;
                }
            }
        }
        if (removed > 0) {
            log.debug("Cache cleanup evicted {} expired entries", removed);
        }
    }
}
