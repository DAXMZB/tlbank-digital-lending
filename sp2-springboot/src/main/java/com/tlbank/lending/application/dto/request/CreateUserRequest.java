package com.tlbank.lending.application.dto.request;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * HTTP request body for creating a new user via the admin API.
 */
public record CreateUserRequest(
        @NotBlank String username,
        @NotBlank @Size(min = 8) String password,
        @NotBlank String fullName,
        @NotBlank @Email String email,
        Set<String> roles
) {
}
