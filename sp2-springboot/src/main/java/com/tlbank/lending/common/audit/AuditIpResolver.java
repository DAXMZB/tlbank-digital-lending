package com.tlbank.lending.common.audit;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Utility for resolving client IP addresses from HTTP requests.
 */
public final class AuditIpResolver {

    private static final String X_FORWARDED_FOR = "X-Forwarded-For";

    private AuditIpResolver() {
    }

    /**
     * Resolves the client IP address, honoring {@code X-Forwarded-For} when present.
     */
    public static String resolveClientIp(HttpServletRequest request) {
        if (request == null) {
            return null;
        }

        String forwarded = request.getHeader(X_FORWARDED_FOR);
        if (forwarded != null && !forwarded.isBlank()) {
            String firstHop = forwarded.split(",")[0].trim();
            if (!firstHop.isBlank()) {
                return firstHop;
            }
        }

        return request.getRemoteAddr();
    }
}
