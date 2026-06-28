package com.tlbank.lending.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Enables scheduled tasks such as cache cleanup.
 */
@Configuration
@EnableScheduling
public class SchedulingConfig {
}
