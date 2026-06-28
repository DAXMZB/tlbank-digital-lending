package com.tlbank.lending.security.service;

import java.util.List;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlbank.lending.infrastructure.persistence.user.UserEntity;
import com.tlbank.lending.infrastructure.persistence.user.UserJpaRepository;
import com.tlbank.lending.security.model.AuthenticatedUser;

import lombok.RequiredArgsConstructor;

/**
 * Loads user account details from the database for Spring Security authentication.
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserJpaRepository userJpaRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userJpaRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        if (!user.isEnabled()) {
            throw new DisabledException("User account is disabled: " + username);
        }

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(this::toSpringRole)
                .map(SimpleGrantedAuthority::new)
                .map(GrantedAuthority.class::cast)
                .toList();

        return new AuthenticatedUser(
                user.getUsername(),
                user.getPasswordHash(),
                user.isEnabled(),
                authorities,
                user.getFullName());
    }

    private String toSpringRole(String role) {
        return switch (role) {
            case "APPLICANT" -> "ROLE_USER";
            case "ADMIN" -> "ROLE_ADMIN";
            case "REVIEWER" -> "ROLE_REVIEWER";
            default -> "ROLE_" + role;
        };
    }
}
