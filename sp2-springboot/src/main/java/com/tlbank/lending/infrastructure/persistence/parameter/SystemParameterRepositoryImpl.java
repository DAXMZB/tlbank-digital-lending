package com.tlbank.lending.infrastructure.persistence.parameter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.tlbank.lending.domain.parameter.SystemParameter;
import com.tlbank.lending.domain.parameter.SystemParameterRepository;

import lombok.RequiredArgsConstructor;

/**
 * JPA-backed implementation of the {@link SystemParameterRepository} domain port.
 */
@Repository
@RequiredArgsConstructor
public class SystemParameterRepositoryImpl implements SystemParameterRepository {

    private final SystemParameterJpaRepository systemParameterJpaRepository;

    @Override
    public Optional<SystemParameter> findByGroupAndKey(String group, String key) {
        return systemParameterJpaRepository.findByParamGroupAndParamKeyAndEnabledTrue(group, key)
                .map(this::toDomain);
    }

    @Override
    public List<SystemParameter> findAllByGroup(String group) {
        return systemParameterJpaRepository.findByParamGroup(group).stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<SystemParameter> findAllEnabled() {
        return systemParameterJpaRepository.findByEnabledTrue().stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public SystemParameter save(SystemParameter param) {
        SystemParameterEntity entity = systemParameterJpaRepository.findById(param.getParamId())
                .map(existing -> {
                    applyToEntity(existing, param);
                    return existing;
                })
                .orElseGet(() -> toEntity(param));
        return toDomain(systemParameterJpaRepository.save(entity));
    }

    private SystemParameter toDomain(SystemParameterEntity entity) {
        return SystemParameter.builder()
                .paramId(entity.getId())
                .paramGroup(entity.getParamGroup())
                .paramKey(entity.getParamKey())
                .paramValue(entity.getParamValue())
                .description(entity.getDescription())
                .enabled(entity.isEnabled())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    private SystemParameterEntity toEntity(SystemParameter param) {
        return SystemParameterEntity.builder()
                .id(param.getParamId())
                .paramGroup(param.getParamGroup())
                .paramKey(param.getParamKey())
                .paramValue(param.getParamValue())
                .description(param.getDescription())
                .enabled(param.isEnabled())
                .build();
    }

    private void applyToEntity(SystemParameterEntity entity, SystemParameter param) {
        entity.setParamGroup(param.getParamGroup());
        entity.setParamKey(param.getParamKey());
        entity.setParamValue(param.getParamValue());
        entity.setDescription(param.getDescription());
        entity.setEnabled(param.isEnabled());
    }
}
