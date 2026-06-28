package com.tlbank.lending.domain.application;

/**
 * Value object representing a postal address.
 */
public record Address(String city, String district, String street, String zipCode) {

    public Address {
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City must not be blank");
        }
        if (district == null || district.isBlank()) {
            throw new IllegalArgumentException("District must not be blank");
        }
        if (street == null || street.isBlank()) {
            throw new IllegalArgumentException("Street must not be blank");
        }
        if (zipCode == null || zipCode.isBlank()) {
            throw new IllegalArgumentException("Zip code must not be blank");
        }
    }
}
