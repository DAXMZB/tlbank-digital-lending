package com.tlbank.lending.domain.application;

import java.util.regex.Pattern;

/**
 * Value object representing a Taiwan mobile phone number.
 */
public record MobileNumber(String value) {

    private static final Pattern FORMAT = Pattern.compile("^09\\d{8}$");

    public MobileNumber {
        if (value == null || !FORMAT.matcher(value).matches()) {
            throw new IllegalArgumentException("Invalid mobile number format: " + value);
        }
    }

    /**
     * Returns a masked representation of the mobile number.
     */
    public String masked() {
        return value.substring(0, 4) + "****" + value.substring(8);
    }

    public static MobileNumber of(String value) {
        return new MobileNumber(value);
    }
}
