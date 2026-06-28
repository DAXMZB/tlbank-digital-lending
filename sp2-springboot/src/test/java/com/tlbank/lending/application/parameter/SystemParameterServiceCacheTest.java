package com.tlbank.lending.application.parameter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tlbank.lending.application.parameter.service.SystemParameterService;
import com.tlbank.lending.domain.parameter.SystemParameter;
import com.tlbank.lending.domain.parameter.SystemParameterRepository;
import com.tlbank.lending.infrastructure.cache.CacheKeys;
import com.tlbank.lending.infrastructure.cache.CacheStore;
import com.tlbank.lending.infrastructure.cache.CacheTtlProvider;

@ExtendWith(MockitoExtension.class)
class SystemParameterServiceCacheTest {

    private static final String CACHE_KEY = CacheKeys.systemParamKey("OTP", "expire_minutes");

    @Mock
    private SystemParameterRepository systemParameterRepository;

    @Mock
    private CacheStore cacheStore;

    @Mock
    private CacheTtlProvider cacheTtlProvider;

    @InjectMocks
    private SystemParameterService systemParameterService;

    @BeforeEach
    void setUp() {
        systemParameterService = new SystemParameterService(
                systemParameterRepository,
                cacheStore,
                cacheTtlProvider);
    }

    @Test
    void getValue_secondCall_shouldNotHitRepository() {
        when(cacheStore.get(CACHE_KEY))
                .thenReturn(Optional.empty())
                .thenReturn(Optional.of("5"));
        when(systemParameterRepository.findByGroupAndKey("OTP", "expire_minutes"))
                .thenReturn(Optional.of(sampleParameter("5")));
        when(cacheTtlProvider.getTtlSeconds()).thenReturn(21_600L);

        assertThat(systemParameterService.getValue("OTP", "expire_minutes")).isEqualTo("5");
        assertThat(systemParameterService.getValue("OTP", "expire_minutes")).isEqualTo("5");

        verify(systemParameterRepository, times(1)).findByGroupAndKey("OTP", "expire_minutes");
        verify(cacheStore).put(CACHE_KEY, "5", 21_600L);
    }

    @Test
    void refreshCache_shouldEvictAndReload() {
        when(cacheStore.keys()).thenReturn(Set.of(CACHE_KEY));
        when(systemParameterRepository.findAllEnabled()).thenReturn(List.of(sampleParameter("5")));
        when(cacheTtlProvider.getTtlSeconds()).thenReturn(21_600L);

        int refreshed = systemParameterService.refreshCache();

        verify(cacheStore).evict(CACHE_KEY);
        verify(cacheStore).put(eq(CACHE_KEY), eq("5"), eq(21_600L));
        assertThat(refreshed).isEqualTo(2);
    }

    private SystemParameter sampleParameter(String value) {
        return SystemParameter.builder()
                .paramId(1L)
                .paramGroup("OTP")
                .paramKey("expire_minutes")
                .paramValue(value)
                .description("OTP expiry in minutes")
                .enabled(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
