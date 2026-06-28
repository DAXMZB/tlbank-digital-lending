package com.tlbank.lending.infrastructure.persistence.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.tlbank.lending.common.entity.BaseEntity;
import com.tlbank.lending.domain.product.CardType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * JPA entity mapping the {@code card_products} table.
 */
@Entity
@Table(name = "card_products")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardProductEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_code", nullable = false, unique = true, length = 30)
    private String productCode;

    @Column(name = "product_name", nullable = false, length = 100)
    private String productName;

    @Column(length = 500)
    private String description;

    @Column(name = "annual_fee", nullable = false)
    private BigDecimal annualFee;

    @Column(name = "credit_limit_min", nullable = false)
    private BigDecimal creditLimitMin;

    @Column(name = "credit_limit_max", nullable = false)
    private BigDecimal creditLimitMax;

    @Column(nullable = false, length = 20)
    private String status;

    @Enumerated(EnumType.STRING)
    @Column(name = "card_type", nullable = false, length = 20)
    private CardType cardType;

    @Column(nullable = false)
    private boolean enabled;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<ProductFeatureEntity> features = new ArrayList<>();
}
