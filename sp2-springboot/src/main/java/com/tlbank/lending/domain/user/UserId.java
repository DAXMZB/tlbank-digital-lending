package com.tlbank.lending.domain.user;

import java.util.UUID;

/**
 * Value object representing a unique business identifier for a user.
 */
public record UserId(String value) {

    public UserId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("UserId must not be blank");
        }
    }

    /**
     * Creates a new business user identifier using the USR- prefix pattern.
     */
    public static UserId generate() {
        String suffix = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        return new UserId("USR-" + suffix);
    }

    /**
     * Reconstructs a {@link UserId} from a persisted value.
     */
    public static UserId of(String value) {
        return new UserId(value);
    }
}
