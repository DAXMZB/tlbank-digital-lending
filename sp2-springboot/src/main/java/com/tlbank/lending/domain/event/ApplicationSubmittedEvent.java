package com.tlbank.lending.domain.event;

import java.time.LocalDateTime;

/**
 * Domain event published when a credit card application is submitted for review.
 */
public record ApplicationSubmittedEvent(
        String applicationId,
        String applicantMobile,
        String applicantEmail,
        LocalDateTime timestamp
) {
}
