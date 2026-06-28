package com.tlbank.lending.domain.application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

/**
 * Value object representing a unique business identifier for a credit card application.
 */
public record ApplicationId(String value) {

    private static final Pattern FORMAT = Pattern.compile("^APP-\\d{14}-\\d{4}$");
    private static final DateTimeFormatter ID_TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    public ApplicationId {
        if (value == null || !FORMAT.matcher(value).matches()) {
            throw new IllegalArgumentException("Invalid ApplicationId format: " + value);
        }
    }

    /**
     * Generates a new application identifier using the APP- prefix pattern.
     */
    public static ApplicationId generate() {
        String timestamp = LocalDateTime.now().format(ID_TIMESTAMP_FORMAT);
        int randomSuffix = ThreadLocalRandom.current().nextInt(1000, 10000);
        return new ApplicationId("APP-" + timestamp + "-" + randomSuffix);
    }

    /**
     * Reconstructs an {@link ApplicationId} from a persisted value.
     */
    public static ApplicationId of(String value) {
        return new ApplicationId(value);
    }
}
