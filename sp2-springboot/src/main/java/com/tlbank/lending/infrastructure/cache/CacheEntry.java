package com.tlbank.lending.infrastructure.cache;

import java.time.Clock;
import java.time.LocalDateTime;

/**
 * A cache entry with an expiration timestamp.
 *
 * @param <V> type of the cached value
 */
public record CacheEntry<V>(V value, LocalDateTime expiresAt) {

    /**
     * Returns whether this entry has expired relative to the given clock.
     */
    public boolean isExpired(Clock clock) {
        return expiresAt != null && !expiresAt.isAfter(LocalDateTime.now(clock));
    }
}
