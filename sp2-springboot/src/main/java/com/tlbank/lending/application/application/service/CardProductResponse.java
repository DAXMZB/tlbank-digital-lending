package com.tlbank.lending.application.application.service;

import java.time.LocalDateTime;
import java.util.List;

import com.tlbank.lending.domain.product.CardType;

/**
 * Response DTO for a credit card product.
 */
public record CardProductResponse(
        String productId,
        String productCode,
        String productName,
        CardType cardType,
        List<ProductFeatureResponse> features,
        LocalDateTime createdAt
) {
}
