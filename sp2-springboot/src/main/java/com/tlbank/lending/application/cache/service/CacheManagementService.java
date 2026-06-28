package com.tlbank.lending.application.cache.service;

import org.springframework.stereotype.Service;

import com.tlbank.lending.application.parameter.service.SystemParameterService;
import com.tlbank.lending.infrastructure.cache.CacheStore;
import com.tlbank.lending.infrastructure.cache.CachedCardProductRepository;

import lombok.RequiredArgsConstructor;

/**
 * Application service for cache refresh and statistics operations.
 */
@Service
@RequiredArgsConstructor
public class CacheManagementService {

    private final CacheStore cacheStore;
    private final SystemParameterService systemParameterService;
    private final CachedCardProductRepository cachedCardProductRepository;

    public int refreshAll() {
        return refreshSystemParameters() + refreshProducts();
    }

    public int refreshSystemParameters() {
        return systemParameterService.refreshCache();
    }

    public int refreshProducts() {
        return cachedCardProductRepository.refreshCache();
    }

    public CacheStatsResponse getStats() {
        long memoryEstimateBytes = 0L;
        for (String key : cacheStore.keys()) {
            memoryEstimateBytes += estimateEntryBytes(key);
        }
        return new CacheStatsResponse(cacheStore.keys().size(), memoryEstimateBytes);
    }

    private long estimateEntryBytes(String key) {
        long bytes = key.length() * 2L;
        return cacheStore.get(key)
                .map(value -> bytes + String.valueOf(value).length() * 2L)
                .orElse(bytes);
    }
}
