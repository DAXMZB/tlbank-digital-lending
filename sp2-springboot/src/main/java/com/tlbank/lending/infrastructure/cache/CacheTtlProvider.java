package com.tlbank.lending.infrastructure.cache;

import org.springframework.stereotype.Component;

import com.tlbank.lending.domain.parameter.SystemParameterRepository;

import lombok.RequiredArgsConstructor;

/**
 * Resolves cache TTL from system parameters with a safe default.
 */
@Component
@RequiredArgsConstructor
public class CacheTtlProvider {

    private static final long DEFAULT_TTL_SECONDS = 21_600L;

    private final SystemParameterRepository systemParameterRepository;

    /**
     * Returns the configured cache TTL in seconds, defaulting to 6 hours.
     */
    public long getTtlSeconds() {
        return systemParameterRepository.findByGroupAndKey("CACHE", "ttl_seconds")
                .map(param -> {
                    try {
                        return Long.parseLong(param.getParamValue());
                    } catch (NumberFormatException ex) {
                        return DEFAULT_TTL_SECONDS;
                    }
                })
                .orElse(DEFAULT_TTL_SECONDS);
    }
}
