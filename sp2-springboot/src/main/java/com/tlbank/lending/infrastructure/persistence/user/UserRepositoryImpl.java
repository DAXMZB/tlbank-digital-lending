package com.tlbank.lending.infrastructure.persistence.user;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.tlbank.lending.domain.user.Role;
import com.tlbank.lending.domain.user.User;
import com.tlbank.lending.domain.user.UserId;
import com.tlbank.lending.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * JPA-backed implementation of the {@link UserRepository} domain port.
 */
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<User> findById(UserId userId) {
        return userJpaRepository.findById(userId.value()).map(this::toDomain);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userJpaRepository.findByUsername(username).map(this::toDomain);
    }

    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public User save(User user) {
        UserEntity entity = userJpaRepository.findById(user.getUserId().value())
                .map(existing -> {
                    applyToEntity(existing, user);
                    return existing;
                })
                .orElseGet(() -> toEntity(user));
        return toDomain(userJpaRepository.save(entity));
    }

    @Override
    public boolean existsByUsername(String username) {
        return userJpaRepository.existsByUsername(username);
    }

    private User toDomain(UserEntity entity) {
        return User.builder()
                .userId(UserId.of(entity.getUserId()))
                .username(entity.getUsername())
                .passwordHash(entity.getPasswordHash())
                .fullName(entity.getFullName())
                .email(entity.getEmail())
                .roles(toDomainRoles(entity.getRoles()))
                .enabled(entity.isEnabled())
                .lastLoginAt(entity.getLastLoginAt())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    private UserEntity toEntity(User user) {
        return UserEntity.builder()
                .userId(user.getUserId().value())
                .username(user.getUsername())
                .passwordHash(user.getPasswordHash())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .status(user.isEnabled() ? "ACTIVE" : "DISABLED")
                .lastLoginAt(user.getLastLoginAt())
                .roles(toEntityRoles(user.getRoles()))
                .build();
    }

    private void applyToEntity(UserEntity entity, User user) {
        entity.setUsername(user.getUsername());
        entity.setPasswordHash(user.getPasswordHash());
        entity.setFullName(user.getFullName());
        entity.setEmail(user.getEmail());
        entity.setStatus(user.isEnabled() ? "ACTIVE" : "DISABLED");
        entity.setLastLoginAt(user.getLastLoginAt());
        entity.setRoles(toEntityRoles(user.getRoles()));
    }

    private Set<Role> toDomainRoles(Set<String> entityRoles) {
        if (entityRoles == null) {
            return EnumSet.noneOf(Role.class);
        }
        return entityRoles.stream().map(this::toDomainRole).collect(Collectors.toCollection(HashSet::new));
    }

    private Set<String> toEntityRoles(Set<Role> domainRoles) {
        if (domainRoles == null) {
            return new HashSet<>();
        }
        return domainRoles.stream().map(this::toEntityRole).collect(Collectors.toSet());
    }

    private Role toDomainRole(String entityRole) {
        return switch (entityRole) {
            case "ADMIN" -> Role.ROLE_ADMIN;
            case "REVIEWER" -> Role.ROLE_REVIEWER;
            case "APPLICANT", "USER" -> Role.ROLE_USER;
            default -> Role.valueOf(entityRole.startsWith("ROLE_") ? entityRole : "ROLE_" + entityRole);
        };
    }

    private String toEntityRole(Role role) {
        return switch (role) {
            case ROLE_ADMIN -> "ADMIN";
            case ROLE_REVIEWER -> "REVIEWER";
            case ROLE_USER -> "APPLICANT";
        };
    }
}
