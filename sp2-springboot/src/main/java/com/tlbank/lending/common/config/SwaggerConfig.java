package com.tlbank.lending.common.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * OpenAPI / Swagger configuration for REST API documentation.
 */
@Configuration
@OpenAPIDefinition(info = @Info(
        title = "TLBank Digital Lending Platform API",
        version = "1.0",
        description = "Fictional portfolio project - Internal Credit Card Application System"
))
public class SwaggerConfig {
}
