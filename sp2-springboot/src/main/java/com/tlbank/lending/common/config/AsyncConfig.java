package com.tlbank.lending.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Enables asynchronous execution for non-blocking audit log persistence.
 */
@Configuration
@EnableAsync
public class AsyncConfig {
}
