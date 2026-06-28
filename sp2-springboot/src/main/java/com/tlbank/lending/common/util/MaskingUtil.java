package com.tlbank.lending.common.util;

import lombok.experimental.UtilityClass;

/**
 * Utility for masking sensitive personal data in logs and API responses.
 */
@UtilityClass
public class MaskingUtil {

    private static final String MOBILE_MASK = "****";
    private static final String NATIONAL_ID_MASK = "****";
    private static final String EMAIL_MASK = "****";

    /**
     * Masks a Taiwan mobile number, e.g. {@code 0912345678} → {@code 0912****78}.
     */
    public static String maskMobile(String mobile) {
        if (mobile == null || mobile.length() < 8) {
            return mobile;
        }
        return mobile.substring(0, 4) + MOBILE_MASK + mobile.substring(mobile.length() - 2);
    }

    /**
     * Masks a national ID, e.g. {@code A123456789} → {@code A123****89}.
     */
    public static String maskNationalId(String nationalId) {
        if (nationalId == null || nationalId.length() < 6) {
            return nationalId;
        }
        return nationalId.substring(0, 4) + NATIONAL_ID_MASK + nationalId.substring(nationalId.length() - 2);
    }

    /**
     * Masks a person's name, e.g. {@code 王大明} → {@code 王*明}.
     */
    public static String maskName(String name) {
        if (name == null || name.isBlank()) {
            return name;
        }
        if (name.length() == 1) {
            return "*";
        }
        if (name.length() == 2) {
            return name.charAt(0) + "*";
        }
        return name.charAt(0) + "****" + name.charAt(name.length() - 1);
    }

    /**
     * Masks an email address, e.g. {@code user@example.com} → {@code us****@example.com}.
     */
    public static String maskEmail(String email) {
        if (email == null || !email.contains("@")) {
            return email;
        }
        var parts = email.split("@", 2);
        var local = parts[0];
        var domain = parts[1];
        if (local.length() <= 2) {
            return EMAIL_MASK + "@" + domain;
        }
        return local.substring(0, 2) + EMAIL_MASK + "@" + domain;
    }
}
