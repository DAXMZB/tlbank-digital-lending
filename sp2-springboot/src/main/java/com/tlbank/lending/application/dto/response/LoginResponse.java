package com.tlbank.lending.application.dto.response;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Response payload returned upon successful authentication.
 */
public record LoginResponse(
        String username,
        String fullName,
        List<String> roles,
        LocalDateTime sessionExpiredAt
) {
}
