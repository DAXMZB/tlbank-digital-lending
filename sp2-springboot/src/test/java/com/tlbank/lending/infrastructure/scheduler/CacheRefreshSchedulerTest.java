package com.tlbank.lending.infrastructure.scheduler;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tlbank.lending.application.parameter.service.SystemParameterService;
import com.tlbank.lending.infrastructure.cache.CacheKeys;
import com.tlbank.lending.infrastructure.cache.CacheStore;

@ExtendWith(MockitoExtension.class)
class CacheRefreshSchedulerTest {

    @Mock
    private SystemParameterService systemParameterService;

    @Mock
    private CacheStore cacheStore;

    @InjectMocks
    private CacheRefreshScheduler cacheRefreshScheduler;

    @BeforeEach
    void setUp() {
        cacheRefreshScheduler = new CacheRefreshScheduler(systemParameterService, cacheStore);
    }

    @Test
    void refreshCaches_shouldCallSystemParameterServiceRefresh() {
        when(systemParameterService.refreshCache()).thenReturn(2);
        when(cacheStore.keys()).thenReturn(Set.of(
                CacheKeys.CARD_PRODUCT_ALL,
                CacheKeys.cardProductKey("TL-CLASSIC"),
                CacheKeys.systemParamKey("OTP", "expire_minutes")
        ));

        assertThatCode(() -> cacheRefreshScheduler.refreshCaches()).doesNotThrowAnyException();

        verify(systemParameterService).refreshCache();
        verify(cacheStore).evict(CacheKeys.CARD_PRODUCT_ALL);
        verify(cacheStore).evict(CacheKeys.cardProductKey("TL-CLASSIC"));
    }
}
