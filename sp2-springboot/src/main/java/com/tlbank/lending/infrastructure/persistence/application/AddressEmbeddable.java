package com.tlbank.lending.infrastructure.persistence.application;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Embeddable value object for applicant address information.
 */
@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressEmbeddable {

    @Column(name = "address_city", length = 50)
    private String city;

    @Column(name = "address_district", length = 50)
    private String district;

    @Column(name = "address_street", length = 200)
    private String street;

    @Column(name = "address_zip_code", length = 10)
    private String zipCode;
}
