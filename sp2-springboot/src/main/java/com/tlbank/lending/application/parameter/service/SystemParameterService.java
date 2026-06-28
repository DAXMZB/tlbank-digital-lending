package com.tlbank.lending.application.parameter.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlbank.lending.common.exception.BusinessException;
import com.tlbank.lending.common.exception.ErrorCode;
import com.tlbank.lending.domain.parameter.SystemParameter;
import com.tlbank.lending.domain.parameter.SystemParameterRepository;
import com.tlbank.lending.infrastructure.cache.CacheKeys;
import com.tlbank.lending.infrastructure.cache.CacheStore;
import com.tlbank.lending.infrastructure.cache.CacheTtlProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Application service for reading and updating system configuration parameters.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SystemParameterService {

    private final SystemParameterRepository systemParameterRepository;
    private final CacheStore cacheStore;
    private final CacheTtlProvider cacheTtlProvider;

    @Transactional(readOnly = true)
    public String getValue(String group, String key) {
        String cacheKey = CacheKeys.systemParamKey(group, key);
        return cacheStore.<String>get(cacheKey)
                .map(cached -> {
                    log.debug("Cache HIT for param: {}", cacheKey);
                    return cached;
                })
                .orElseGet(() -> {
                    log.debug("Cache MISS for param: {}", cacheKey);
                    String value = systemParameterRepository.findByGroupAndKey(group, key)
                            .map(SystemParameter::getParamValue)
                            .orElseThrow(() -> new BusinessException(
                                    ErrorCode.PARAMETER_NOT_FOUND,
                                    "Parameter not found: " + group + "." + key));
                    cacheStore.put(cacheKey, value, cacheTtlProvider.getTtlSeconds());
                    return value;
                });
    }

    @Transactional(readOnly = true)
    public String getValueOrDefault(String group, String key, String defaultValue) {
        return systemParameterRepository.findByGroupAndKey(group, key)
                .map(SystemParameter::getParamValue)
                .orElse(defaultValue);
    }

    @Transactional(readOnly = true)
    public int getIntValue(String group, String key, int defaultValue) {
        return systemParameterRepository.findByGroupAndKey(group, key)
                .map(param -> Integer.parseInt(param.getParamValue()))
                .orElse(defaultValue);
    }

    @Transactional(readOnly = true)
    public List<SystemParameterResponse> findByGroup(String group) {
        return systemParameterRepository.findAllByGroup(group).stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<SystemParameterResponse> findAllEnabled() {
        return systemParameterRepository.findAllEnabled().stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public SystemParameterResponse update(UpdateParameterCommand command) {
        SystemParameter parameter = systemParameterRepository.findAllEnabled().stream()
                .filter(param -> command.paramId().equals(param.getParamId()))
                .findFirst()
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.PARAMETER_NOT_FOUND,
                        "Parameter not found: " + command.paramId()));

        parameter.updateValue(command.paramValue());
        SystemParameter saved = systemParameterRepository.save(parameter);
        cacheStore.evict(CacheKeys.systemParamKey(saved.getParamGroup(), saved.getParamKey()));
        return toResponse(saved);
    }

    /**
     * Evicts all cached system parameters and reloads them from the database.
     */
    @Transactional(readOnly = true)
    public int refreshCache() {
        int evicted = 0;
        for (String cacheKey : cacheStore.keys()) {
            if (cacheKey.startsWith(CacheKeys.SYSTEM_PARAM_PREFIX)) {
                cacheStore.evict(cacheKey);
                evicted++;
            }
        }

        int loaded = 0;
        for (SystemParameter parameter : systemParameterRepository.findAllEnabled()) {
            String cacheKey = CacheKeys.systemParamKey(parameter.getParamGroup(), parameter.getParamKey());
            cacheStore.put(cacheKey, parameter.getParamValue(), cacheTtlProvider.getTtlSeconds());
            loaded++;
        }
        return evicted + loaded;
    }

    private SystemParameterResponse toResponse(SystemParameter parameter) {
        return new SystemParameterResponse(
                parameter.getParamId(),
                parameter.getParamGroup(),
                parameter.getParamKey(),
                parameter.getParamValue(),
                parameter.getDescription(),
                parameter.isEnabled()
        );
    }
}
