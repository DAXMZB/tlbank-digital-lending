package com.tlbank.lending.infrastructure.persistence.parameter;

import com.tlbank.lending.common.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * JPA entity mapping the {@code system_parameters} table.
 */
@Entity
@Table(name = "system_parameters")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemParameterEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "param_group", nullable = false, length = 50)
    private String paramGroup;

    @Column(name = "param_key", nullable = false, length = 100)
    private String paramKey;

    @Column(name = "param_value", nullable = false, length = 500)
    private String paramValue;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private boolean enabled;
}
