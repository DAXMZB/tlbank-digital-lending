package com.tlbank.lending.infrastructure.persistence.product;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for {@link CardProductEntity}.
 */
public interface CardProductJpaRepository extends JpaRepository<CardProductEntity, Long> {

    List<CardProductEntity> findAllByEnabledTrue();

    Optional<CardProductEntity> findByProductCode(String productCode);
}
