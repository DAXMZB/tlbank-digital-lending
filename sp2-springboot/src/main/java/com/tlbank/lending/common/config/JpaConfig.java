package com.tlbank.lending.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * JPA configuration enabling automatic population of audit fields on {@link com.tlbank.lending.common.entity.BaseEntity}.
 */
@Configuration
@EnableJpaAuditing
public class JpaConfig {
}
