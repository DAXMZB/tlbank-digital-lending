package com.tlbank.lending.domain.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class MobileNumberTest {

    @Test
    void of_whenValidFormat_shouldCreateMobileNumber() {
        MobileNumber mobile = MobileNumber.of("0912345678");

        assertThat(mobile.value()).isEqualTo("0912345678");
        assertThat(mobile.masked()).isEqualTo("0912****78");
    }

    @Test
    void of_whenInvalidFormat_shouldThrowException() {
        assertThatThrownBy(() -> MobileNumber.of("0812345678"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void of_whenTooShort_shouldThrowException() {
        assertThatThrownBy(() -> MobileNumber.of("0912345"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
