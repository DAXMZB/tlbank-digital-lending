package com.tlbank.lending.domain.service;

import org.springframework.stereotype.Service;

import com.tlbank.lending.common.exception.WorkflowException;
import com.tlbank.lending.domain.application.ApplicationStatus;

/**
 * Domain service for validating application workflow status transitions.
 */
@Service
public class WorkflowDomainService {

    /**
     * Validates that a transition from one status to another is permitted.
     *
     * @throws WorkflowException if the transition is not allowed
     */
    public void validateTransition(ApplicationStatus from, ApplicationStatus to) {
        if (!from.canTransitionTo(to)) {
            throw new WorkflowException("Cannot transition from " + from + " to " + to);
        }
    }
}
