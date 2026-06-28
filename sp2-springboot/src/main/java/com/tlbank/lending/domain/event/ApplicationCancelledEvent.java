package com.tlbank.lending.domain.event;

import java.time.LocalDateTime;

/**
 * Domain event published when a credit card application is cancelled.
 */
public record ApplicationCancelledEvent(
        String applicationId,
        String reason,
        LocalDateTime timestamp
) {
}
