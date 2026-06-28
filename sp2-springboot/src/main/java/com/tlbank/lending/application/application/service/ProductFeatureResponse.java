package com.tlbank.lending.application.application.service;

/**
 * Response DTO for a card product feature.
 */
public record ProductFeatureResponse(
        String featureKey,
        String featureValue
) {
}
