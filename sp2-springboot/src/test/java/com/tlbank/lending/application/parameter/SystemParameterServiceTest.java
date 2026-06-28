package com.tlbank.lending.application.parameter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tlbank.lending.application.parameter.service.SystemParameterResponse;
import com.tlbank.lending.application.parameter.service.SystemParameterService;
import com.tlbank.lending.application.parameter.service.UpdateParameterCommand;
import com.tlbank.lending.common.exception.BusinessException;
import com.tlbank.lending.common.exception.ErrorCode;
import com.tlbank.lending.domain.parameter.SystemParameter;
import com.tlbank.lending.domain.parameter.SystemParameterRepository;
import com.tlbank.lending.infrastructure.cache.CacheKeys;
import com.tlbank.lending.infrastructure.cache.CacheStore;
import com.tlbank.lending.infrastructure.cache.CacheTtlProvider;

@ExtendWith(MockitoExtension.class)
class SystemParameterServiceTest {

    @Mock
    private SystemParameterRepository systemParameterRepository;

    @Mock
    private CacheStore cacheStore;

    @Mock
    private CacheTtlProvider cacheTtlProvider;

    @InjectMocks
    private SystemParameterService systemParameterService;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        systemParameterService = new SystemParameterService(
                systemParameterRepository,
                cacheStore,
                cacheTtlProvider);
        lenient().when(cacheStore.get(anyString())).thenReturn(Optional.empty());
        lenient().when(cacheTtlProvider.getTtlSeconds()).thenReturn(21_600L);
    }

    @Test
    void getValue_whenExists_shouldReturnValue() {
        when(systemParameterRepository.findByGroupAndKey("OTP", "expiry.minutes"))
                .thenReturn(Optional.of(sampleParameter("5")));

        String value = systemParameterService.getValue("OTP", "expiry.minutes");

        assertThat(value).isEqualTo("5");
    }

    @Test
    void getValue_whenNotFound_shouldThrowBusinessException() {
        when(systemParameterRepository.findByGroupAndKey("OTP", "missing"))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> systemParameterService.getValue("OTP", "missing"))
                .isInstanceOf(BusinessException.class)
                .extracting(ex -> ((BusinessException) ex).getErrorCode())
                .isEqualTo(ErrorCode.PARAMETER_NOT_FOUND);
    }

    @Test
    void getValueOrDefault_whenNotFound_shouldReturnDefault() {
        when(systemParameterRepository.findByGroupAndKey("OTP", "missing"))
                .thenReturn(Optional.empty());

        String value = systemParameterService.getValueOrDefault("OTP", "missing", "default");

        assertThat(value).isEqualTo("default");
    }

    @Test
    void update_shouldPersistNewValue() {
        SystemParameter existing = sampleParameter("5");
        when(systemParameterRepository.findAllEnabled()).thenReturn(java.util.List.of(existing));
        when(systemParameterRepository.save(any(SystemParameter.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        SystemParameterResponse response = systemParameterService.update(
                new UpdateParameterCommand(1L, "10"));

        assertThat(response.paramValue()).isEqualTo("10");

        ArgumentCaptor<SystemParameter> captor = ArgumentCaptor.forClass(SystemParameter.class);
        verify(systemParameterRepository).save(captor.capture());
        assertThat(captor.getValue().getParamValue()).isEqualTo("10");
        verify(cacheStore).evict(CacheKeys.systemParamKey("OTP", "expiry.minutes"));
    }

    @Test
    void getValue_whenCacheHit_shouldNotHitRepository() {
        when(cacheStore.get(anyString())).thenReturn(Optional.of("cached-value"));

        String value = systemParameterService.getValue("OTP", "expiry.minutes");

        assertThat(value).isEqualTo("cached-value");
        verify(systemParameterRepository, never()).findByGroupAndKey(anyString(), anyString());
    }

    private SystemParameter sampleParameter(String value) {
        return SystemParameter.builder()
                .paramId(1L)
                .paramGroup("OTP")
                .paramKey("expiry.minutes")
                .paramValue(value)
                .description("OTP expiry in minutes")
                .enabled(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
