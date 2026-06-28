package com.tlbank.lending.infrastructure.persistence.application;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Embeddable value object for applicant personal information.
 */
@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantEmbeddable {

    @Column(name = "applicant_full_name", length = 100)
    private String fullName;

    @Column(name = "applicant_national_id", length = 20)
    private String nationalId;

    @Column(name = "applicant_mobile", length = 20)
    private String mobile;

    @Column(name = "applicant_email", length = 100)
    private String email;

    @Column(name = "applicant_date_of_birth")
    private LocalDate dateOfBirth;
}
