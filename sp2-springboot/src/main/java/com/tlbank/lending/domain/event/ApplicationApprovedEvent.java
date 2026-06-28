package com.tlbank.lending.domain.event;

import java.time.LocalDateTime;

/**
 * Domain event published when a credit card application is approved.
 */
public record ApplicationApprovedEvent(
        String applicationId,
        String mobile,
        String email,
        LocalDateTime timestamp
) {
}
