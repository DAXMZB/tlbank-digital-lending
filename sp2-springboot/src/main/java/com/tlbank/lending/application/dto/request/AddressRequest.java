package com.tlbank.lending.application.dto.request;

import jakarta.validation.constraints.NotBlank;

/**
 * Request DTO for applicant address information.
 */
public record AddressRequest(
        @NotBlank String city,
        @NotBlank String district,
        @NotBlank String street,
        @NotBlank String zipCode
) {
}
