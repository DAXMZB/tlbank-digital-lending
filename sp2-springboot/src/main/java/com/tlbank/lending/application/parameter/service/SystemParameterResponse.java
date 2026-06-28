package com.tlbank.lending.application.parameter.service;

/**
 * Response DTO representing a system parameter.
 */
public record SystemParameterResponse(
        Long paramId,
        String paramGroup,
        String paramKey,
        String paramValue,
        String description,
        boolean enabled
) {
}
