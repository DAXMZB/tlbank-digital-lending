package com.tlbank.lending.domain.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class ApplicationIdTest {

    @Test
    void of_whenValidFormat_shouldCreateApplicationId() {
        ApplicationId id = ApplicationId.of("APP-20250607120000-1234");

        assertThat(id.value()).isEqualTo("APP-20250607120000-1234");
    }

    @Test
    void of_whenInvalidFormat_shouldThrowException() {
        assertThatThrownBy(() -> ApplicationId.of("APP-INVALID"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void generate_shouldMatchExpectedFormat() {
        ApplicationId id = ApplicationId.generate();

        assertThat(id.value()).matches("^APP-\\d{14}-\\d{4}$");
    }

    @Test
    void generate_shouldProduceUniqueValues() {
        ApplicationId first = ApplicationId.generate();
        ApplicationId second = ApplicationId.generate();

        assertThat(first.value()).isNotEqualTo(second.value());
    }
}
