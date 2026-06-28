package com.tlbank.lending.common.util;

import java.time.Clock;
import java.time.LocalDateTime;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Date and time utility with injectable {@link Clock} for testability.
 */
@Component
@RequiredArgsConstructor
public class DateUtil {

    private final Clock clock;

    /**
     * Returns {@code true} if the given expiration timestamp is before the current instant.
     */
    public boolean isExpired(LocalDateTime expiredAt) {
        if (expiredAt == null) {
            return true;
        }
        return expiredAt.isBefore(LocalDateTime.now(clock));
    }
}
