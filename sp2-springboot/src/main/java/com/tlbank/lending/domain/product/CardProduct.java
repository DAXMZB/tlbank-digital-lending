package com.tlbank.lending.domain.product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.tlbank.lending.domain.application.CardProductId;

import lombok.Builder;
import lombok.Getter;

/**
 * Domain model representing a credit card product offering.
 */
@Getter
@Builder
public class CardProduct {

    private CardProductId productId;
    private String productCode;
    private String productName;
    private CardType cardType;
    @Builder.Default
    private List<ProductFeature> features = new ArrayList<>();
    private boolean enabled;
    private LocalDateTime createdAt;
}
