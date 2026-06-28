package com.tlbank.lending.domain.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class EmailTest {

    @Test
    void of_whenValid_shouldCreateEmail() {
        Email email = Email.of("test@example.com");

        assertThat(email.value()).isEqualTo("test@example.com");
    }

    @Test
    void of_whenMissingAtSymbol_shouldThrowException() {
        assertThatThrownBy(() -> Email.of("invalid-email"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid email format");
    }

    @Test
    void of_whenNull_shouldThrowException() {
        assertThatThrownBy(() -> Email.of(null))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
