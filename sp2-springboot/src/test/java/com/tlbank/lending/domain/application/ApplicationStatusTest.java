package com.tlbank.lending.domain.application;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.EnumSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class ApplicationStatusTest {

    @Test
    void canTransitionTo_allLegalTransitions_shouldReturnTrue() {
        assertThat(ApplicationStatus.INIT.canTransitionTo(ApplicationStatus.OTP_VERIFIED)).isTrue();
        assertThat(ApplicationStatus.INIT.canTransitionTo(ApplicationStatus.CANCELLED)).isTrue();
        assertThat(ApplicationStatus.OTP_VERIFIED.canTransitionTo(ApplicationStatus.DOCUMENT_UPLOADED)).isTrue();
        assertThat(ApplicationStatus.OTP_VERIFIED.canTransitionTo(ApplicationStatus.CANCELLED)).isTrue();
        assertThat(ApplicationStatus.DOCUMENT_UPLOADED.canTransitionTo(ApplicationStatus.SUBMITTED)).isTrue();
        assertThat(ApplicationStatus.DOCUMENT_UPLOADED.canTransitionTo(ApplicationStatus.CANCELLED)).isTrue();
        assertThat(ApplicationStatus.SUBMITTED.canTransitionTo(ApplicationStatus.UNDER_REVIEW)).isTrue();
        assertThat(ApplicationStatus.UNDER_REVIEW.canTransitionTo(ApplicationStatus.APPROVED)).isTrue();
        assertThat(ApplicationStatus.UNDER_REVIEW.canTransitionTo(ApplicationStatus.REJECTED)).isTrue();
    }

    @ParameterizedTest
    @EnumSource(ApplicationStatus.class)
    void canTransitionTo_allIllegalTransitions_shouldReturnFalse(ApplicationStatus from) {
        for (ApplicationStatus to : ApplicationStatus.values()) {
            boolean expected = isLegalTransition(from, to);
            assertThat(from.canTransitionTo(to)).isEqualTo(expected);
        }
    }

    private boolean isLegalTransition(ApplicationStatus from, ApplicationStatus to) {
        return switch (from) {
            case INIT -> EnumSet.of(ApplicationStatus.OTP_VERIFIED, ApplicationStatus.CANCELLED).contains(to);
            case OTP_VERIFIED -> EnumSet.of(ApplicationStatus.DOCUMENT_UPLOADED, ApplicationStatus.CANCELLED).contains(to);
            case DOCUMENT_UPLOADED -> EnumSet.of(ApplicationStatus.SUBMITTED, ApplicationStatus.CANCELLED).contains(to);
            case SUBMITTED -> to == ApplicationStatus.UNDER_REVIEW;
            case UNDER_REVIEW -> EnumSet.of(ApplicationStatus.APPROVED, ApplicationStatus.REJECTED).contains(to);
            default -> false;
        };
    }
}
