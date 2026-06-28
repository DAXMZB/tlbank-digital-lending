package com.tlbank.lending.infrastructure.persistence.product;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.tlbank.lending.domain.application.CardProductId;
import com.tlbank.lending.domain.product.CardProduct;
import com.tlbank.lending.domain.product.ProductFeature;
import com.tlbank.lending.domain.product.repository.CardProductRepository;

import lombok.RequiredArgsConstructor;

/**
 * JPA-backed implementation of the {@link CardProductRepository} domain port.
 */
@Repository
@RequiredArgsConstructor
public class CardProductRepositoryImpl implements CardProductRepository {

    private final CardProductJpaRepository cardProductJpaRepository;

    @Override
    public List<CardProduct> findAllEnabled() {
        return cardProductJpaRepository.findAllByEnabledTrue().stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Optional<CardProduct> findById(CardProductId id) {
        Optional<CardProductEntity> byCode = cardProductJpaRepository.findByProductCode(id.value());
        if (byCode.isPresent()) {
            return byCode.map(this::toDomain);
        }
        try {
            long numericId = Long.parseLong(id.value());
            return cardProductJpaRepository.findById(numericId).map(this::toDomain);
        } catch (NumberFormatException ex) {
            return Optional.empty();
        }
    }

    private CardProduct toDomain(CardProductEntity entity) {
        return CardProduct.builder()
                .productId(CardProductId.of(entity.getProductCode()))
                .productCode(entity.getProductCode())
                .productName(entity.getProductName())
                .cardType(entity.getCardType())
                .features(entity.getFeatures().stream()
                        .map(feature -> new ProductFeature(
                                feature.getFeatureName(),
                                feature.getFeatureDescription()))
                        .toList())
                .enabled(entity.isEnabled())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
