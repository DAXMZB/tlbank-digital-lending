package com.tlbank.lending.domain.review;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

/**
 * Value object representing a unique business identifier for a review case.
 */
public record ReviewCaseId(String value) {

    private static final Pattern FORMAT = Pattern.compile("^RC-\\d{8}-\\d{4}$");
    private static final DateTimeFormatter ID_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");

    public ReviewCaseId {
        if (value == null || !FORMAT.matcher(value).matches()) {
            throw new IllegalArgumentException("Invalid ReviewCaseId format: " + value);
        }
    }

    /**
     * Generates a new review case identifier using the RC- prefix pattern.
     */
    public static ReviewCaseId generate() {
        String datePart = LocalDate.now().format(ID_DATE_FORMAT);
        int randomSuffix = ThreadLocalRandom.current().nextInt(1000, 10000);
        return new ReviewCaseId("RC-" + datePart + "-" + randomSuffix);
    }

    public static ReviewCaseId of(String value) {
        return new ReviewCaseId(value);
    }
}
