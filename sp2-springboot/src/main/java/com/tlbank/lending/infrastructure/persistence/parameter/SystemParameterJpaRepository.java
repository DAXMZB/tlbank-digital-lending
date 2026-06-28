package com.tlbank.lending.infrastructure.persistence.parameter;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for {@link SystemParameterEntity}.
 */
public interface SystemParameterJpaRepository extends JpaRepository<SystemParameterEntity, Long> {

    List<SystemParameterEntity> findByParamGroupAndEnabledTrue(String paramGroup);

    Optional<SystemParameterEntity> findByParamGroupAndParamKeyAndEnabledTrue(String paramGroup, String paramKey);

    List<SystemParameterEntity> findByEnabledTrue();

    List<SystemParameterEntity> findByParamGroup(String paramGroup);
}
