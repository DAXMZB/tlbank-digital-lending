package com.tlbank.lending.domain.parameter;

import java.util.List;
import java.util.Optional;

/**
 * Domain repository port for {@link SystemParameter} persistence.
 */
public interface SystemParameterRepository {

    Optional<SystemParameter> findByGroupAndKey(String group, String key);

    List<SystemParameter> findAllByGroup(String group);

    List<SystemParameter> findAllEnabled();

    SystemParameter save(SystemParameter param);
}
