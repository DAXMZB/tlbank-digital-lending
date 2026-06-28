package com.tlbank.lending.domain.application;

import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

/**
 * Represents the lifecycle status of a credit card application.
 */
public enum ApplicationStatus {

    INIT,
    OTP_VERIFIED,
    DOCUMENT_UPLOADED,
    SUBMITTED,
    UNDER_REVIEW,
    APPROVED,
    REJECTED,
    CANCELLED;

    private static final Map<ApplicationStatus, Set<ApplicationStatus>> ALLOWED_TRANSITIONS = Map.of(
            INIT, EnumSet.of(OTP_VERIFIED, CANCELLED),
            OTP_VERIFIED, EnumSet.of(DOCUMENT_UPLOADED, CANCELLED),
            DOCUMENT_UPLOADED, EnumSet.of(SUBMITTED, CANCELLED),
            SUBMITTED, EnumSet.of(UNDER_REVIEW),
            UNDER_REVIEW, EnumSet.of(APPROVED, REJECTED)
    );

    /**
     * Returns whether transitioning to the given status is permitted from this status.
     */
    public boolean canTransitionTo(ApplicationStatus next) {
        if (next == null) {
            return false;
        }
        Set<ApplicationStatus> allowed = ALLOWED_TRANSITIONS.get(this);
        return allowed != null && allowed.contains(next);
    }
}
