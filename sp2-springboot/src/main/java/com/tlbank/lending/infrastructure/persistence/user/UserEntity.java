package com.tlbank.lending.infrastructure.persistence.user;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.tlbank.lending.common.entity.BaseEntity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * JPA entity mapping the {@code users} table and associated role assignments.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseEntity {

    @Id
    @Column(name = "user_id", nullable = false, length = 50)
    private String userId;

    @Column(name = "id", insertable = false, updatable = false)
    private Long internalId;

    @Column(name = "username", nullable = false, length = 50, unique = true)
    private String username;

    @Column(name = "password", nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Column(name = "last_login_at")
    private LocalDateTime lastLoginAt;

    @Builder.Default
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    @Column(name = "role", nullable = false, length = 30)
    private Set<String> roles = new HashSet<>();

    /**
     * Indicates whether the account is active and permitted to authenticate.
     */
    public boolean isEnabled() {
        return "ACTIVE".equals(status);
    }

    /**
     * Updates the last successful login timestamp.
     */
    public void updateLastLoginAt(LocalDateTime loginTime) {
        this.lastLoginAt = loginTime;
    }
}
