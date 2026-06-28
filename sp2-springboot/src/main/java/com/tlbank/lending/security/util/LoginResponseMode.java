package com.tlbank.lending.security.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;

/**
 * Determines whether an authentication request expects a JSON API response or a browser redirect.
 */
@UtilityClass
public class LoginResponseMode {

    /**
     * Returns {@code true} when the client expects a JSON response (API / tests).
     */
    public static boolean prefersJson(HttpServletRequest request) {
        String accept = request.getHeader("Accept");
        if (accept == null || accept.isBlank()) {
            return true;
        }
        if (accept.contains("text/html")) {
            return false;
        }
        return accept.contains("application/json") || accept.contains("*/*");
    }
}
