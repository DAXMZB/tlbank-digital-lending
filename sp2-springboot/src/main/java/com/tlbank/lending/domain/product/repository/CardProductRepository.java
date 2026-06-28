package com.tlbank.lending.domain.product.repository;

import java.util.List;
import java.util.Optional;

import com.tlbank.lending.domain.application.CardProductId;
import com.tlbank.lending.domain.product.CardProduct;

/**
 * Domain repository port for {@link CardProduct} persistence.
 */
public interface CardProductRepository {

    List<CardProduct> findAllEnabled();

    Optional<CardProduct> findById(CardProductId id);
}
