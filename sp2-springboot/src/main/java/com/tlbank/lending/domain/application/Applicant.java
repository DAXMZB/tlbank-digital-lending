package com.tlbank.lending.domain.application;

import java.time.LocalDate;

/**
 * Value object representing an application applicant's personal information.
 */
public record Applicant(
        String fullName,
        String nationalId,
        MobileNumber mobile,
        Email email,
        LocalDate dateOfBirth,
        Address address
) {

    public Applicant {
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Full name must not be blank");
        }
    }
}
