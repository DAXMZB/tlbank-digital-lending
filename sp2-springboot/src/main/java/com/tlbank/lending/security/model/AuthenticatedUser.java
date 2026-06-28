package com.tlbank.lending.security.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

/**
 * Authenticated user principal carrying display metadata for the web UI.
 */
@Getter
public class AuthenticatedUser extends User {

    private final String fullName;

    public AuthenticatedUser(
            String username,
            String password,
            boolean enabled,
            Collection<? extends GrantedAuthority> authorities,
            String fullName) {
        super(username, password, enabled, true, true, true, authorities);
        this.fullName = fullName;
    }
}
