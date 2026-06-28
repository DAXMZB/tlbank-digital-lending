package com.tlbank.lending.application.user.service;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Response DTO representing a user account for API and web views.
 */
public record UserResponse(
        String userId,
        String username,
        String fullName,
        String email,
        Set<String> roles,
        boolean enabled,
        LocalDateTime lastLoginAt,
        LocalDateTime createdAt
) {
}
