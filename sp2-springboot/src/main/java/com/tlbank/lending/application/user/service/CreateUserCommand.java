package com.tlbank.lending.application.user.service;

import java.util.Set;

import com.tlbank.lending.domain.user.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Command object for creating a new user account.
 */
public record CreateUserCommand(
        @NotBlank String username,
        @NotBlank @Size(min = 8) String rawPassword,
        @NotBlank String fullName,
        @NotBlank @Email String email,
        Set<Role> roles
) {
}
