package com.tlbank.lending.domain.service;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.EnumSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import com.tlbank.lending.common.exception.WorkflowException;
import com.tlbank.lending.domain.application.ApplicationStatus;

class WorkflowDomainServiceTest {

    private WorkflowDomainService workflowDomainService;

    @BeforeEach
    void setUp() {
        workflowDomainService = new WorkflowDomainService();
    }

    @Test
    void validateTransition_allLegalTransitions_shouldNotThrow() {
        assertThatCode(() -> workflowDomainService.validateTransition(
                ApplicationStatus.INIT, ApplicationStatus.OTP_VERIFIED)).doesNotThrowAnyException();
        assertThatCode(() -> workflowDomainService.validateTransition(
                ApplicationStatus.INIT, ApplicationStatus.CANCELLED)).doesNotThrowAnyException();
        assertThatCode(() -> workflowDomainService.validateTransition(
                ApplicationStatus.OTP_VERIFIED, ApplicationStatus.DOCUMENT_UPLOADED)).doesNotThrowAnyException();
        assertThatCode(() -> workflowDomainService.validateTransition(
                ApplicationStatus.DOCUMENT_UPLOADED, ApplicationStatus.SUBMITTED)).doesNotThrowAnyException();
        assertThatCode(() -> workflowDomainService.validateTransition(
                ApplicationStatus.SUBMITTED, ApplicationStatus.UNDER_REVIEW)).doesNotThrowAnyException();
        assertThatCode(() -> workflowDomainService.validateTransition(
                ApplicationStatus.UNDER_REVIEW, ApplicationStatus.APPROVED)).doesNotThrowAnyException();
        assertThatCode(() -> workflowDomainService.validateTransition(
                ApplicationStatus.UNDER_REVIEW, ApplicationStatus.REJECTED)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @EnumSource(ApplicationStatus.class)
    void validateTransition_allIllegalTransitions_shouldThrow(ApplicationStatus from) {
        for (ApplicationStatus to : ApplicationStatus.values()) {
            if (isLegalTransition(from, to)) {
                continue;
            }
            assertThatThrownBy(() -> workflowDomainService.validateTransition(from, to))
                    .isInstanceOf(WorkflowException.class)
                    .hasMessageContaining("Cannot transition from " + from + " to " + to);
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
