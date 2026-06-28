package com.tlbank.lending.infrastructure.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tlbank.lending.application.parameter.service.SystemParameterService;
import com.tlbank.lending.infrastructure.cache.CacheKeys;
import com.tlbank.lending.infrastructure.cache.CacheStore;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Scheduled task that refreshes system parameter cache and evicts card product cache entries.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class CacheRefreshScheduler {

    private final SystemParameterService systemParameterService;
    private final CacheStore cacheStore;

    @Scheduled(cron = "${tlbank.scheduler.cache-refresh.cron}")
    public void refreshCaches() {
        long start = System.currentTimeMillis();
        try {
            log.info("[SCHEDULER] Cache refresh started");
            systemParameterService.refreshCache();
            int evicted = evictCardProductCacheKeys();
            long elapsed = System.currentTimeMillis() - start;
            log.info("[SCHEDULER] Cache refresh completed in {}ms. Evicted {} card product cache keys",
                    elapsed, evicted);
        } catch (Exception ex) {
            long elapsed = System.currentTimeMillis() - start;
            log.warn("[SCHEDULER] Cache refresh failed after {}ms: {}", elapsed, ex.getMessage(), ex);
        }
    }

    private int evictCardProductCacheKeys() {
        int evicted = 0;
        for (String key : cacheStore.keys()) {
            if (key.equals(CacheKeys.CARD_PRODUCT_ALL) || key.startsWith(CacheKeys.CARD_PRODUCT_PREFIX)) {
                cacheStore.evict(key);
                evicted++;
            }
        }
        return evicted;
    }
}
