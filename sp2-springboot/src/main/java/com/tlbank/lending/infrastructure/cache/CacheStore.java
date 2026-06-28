package com.tlbank.lending.infrastructure.cache;

import java.util.Optional;
import java.util.Set;

/**
 * Generic cache storage port without framework dependencies.
 *
 * @param <V> type of cached values
 */
public interface CacheStore {

    <V> Optional<V> get(String key);

    <V> void put(String key, V value, long ttlSeconds);

    void evict(String key);

    void evictAll();

    Set<String> keys();
}
