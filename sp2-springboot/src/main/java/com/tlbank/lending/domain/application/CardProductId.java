package com.tlbank.lending.domain.application;

/**
 * Value object representing a card product business identifier.
 */
public record CardProductId(String value) {

    public CardProductId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("CardProductId must not be blank");
        }
    }

    public static CardProductId of(String value) {
        return new CardProductId(value);
    }
}
