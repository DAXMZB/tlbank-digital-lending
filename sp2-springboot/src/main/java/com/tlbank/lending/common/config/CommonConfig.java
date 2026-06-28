package com.tlbank.lending.common.config;

import java.time.Clock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Common Spring bean definitions shared across the application.
 */
@Configuration
public class CommonConfig {

    /**
     * Provides a system-default {@link Clock} for time-sensitive utilities.
     */
    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
