package com.tlbank.lending.application.application.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.tlbank.lending.domain.application.ApplicationStatus;

/**
 * Masked applicant information for API responses.
 */
public record MaskedApplicantResponse(
        String fullName,
        String maskedNationalId,
        String maskedMobile,
        String maskedEmail,
        LocalDate dateOfBirth,
        String city,
        String district,
        String street,
        String zipCode
) {
}
