package com.tlbank.lending.application.cache.service;

/**
 * Cache statistics snapshot.
 */
public record CacheStatsResponse(
        int keyCount,
        long estimatedMemoryBytes
) {
}
