package com.tlbank.lending.domain.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class AddressTest {

    @Test
    void constructor_whenAllFieldsValid_shouldCreateAddress() {
        Address address = new Address("台北市", "信義區", "信義路一段1號", "110");

        assertThat(address.city()).isEqualTo("台北市");
        assertThat(address.district()).isEqualTo("信義區");
        assertThat(address.street()).isEqualTo("信義路一段1號");
        assertThat(address.zipCode()).isEqualTo("110");
    }

    @Test
    void constructor_whenCityBlank_shouldThrowException() {
        assertThatThrownBy(() -> new Address("  ", "信義區", "信義路一段1號", "110"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("City must not be blank");
    }

    @Test
    void constructor_whenDistrictNull_shouldThrowException() {
        assertThatThrownBy(() -> new Address("台北市", null, "信義路一段1號", "110"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("District must not be blank");
    }

    @Test
    void constructor_whenStreetBlank_shouldThrowException() {
        assertThatThrownBy(() -> new Address("台北市", "信義區", "", "110"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Street must not be blank");
    }

    @Test
    void constructor_whenZipCodeBlank_shouldThrowException() {
        assertThatThrownBy(() -> new Address("台北市", "信義區", "信義路一段1號", " "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Zip code must not be blank");
    }
}
