package com.tlbank.lending.domain.user;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import lombok.Builder;
import lombok.Getter;

/**
 * Domain aggregate representing a platform user account.
 */
@Getter
@Builder
public class User {

    private UserId userId;
    private String username;
    private String passwordHash;
    private String fullName;
    private String email;

    @Builder.Default
    private Set<Role> roles = new HashSet<>();

    private boolean enabled;
    private LocalDateTime lastLoginAt;
    private LocalDateTime createdAt;

    /**
     * Activates the user account.
     */
    public void enable() {
        this.enabled = true;
    }

    /**
     * Deactivates the user account.
     */
    public void disable() {
        this.enabled = false;
    }

    /**
     * Assigns a role to the user.
     */
    public void assignRole(Role role) {
        this.roles.add(role);
    }

    /**
     * Removes a role from the user.
     */
    public void removeRole(Role role) {
        this.roles.remove(role);
    }

    /**
     * Checks whether the user has the given role.
     */
    public boolean hasRole(Role role) {
        return roles.contains(role);
    }
}
