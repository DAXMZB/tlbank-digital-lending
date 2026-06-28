package com.tlbank.lending.common.audit;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.experimental.UtilityClass;

/**
 * Thread-local holder for supplemental audit detail captured during method execution.
 */
@UtilityClass
public class AuditContext {

    private static final ThreadLocal<Map<String, String>> CONTEXT =
            ThreadLocal.withInitial(LinkedHashMap::new);

    public static void put(String key, String value) {
        if (key == null || key.isBlank() || value == null || value.isBlank()) {
            return;
        }
        CONTEXT.get().put(key, value);
    }

    public static String buildSuffix() {
        Map<String, String> entries = CONTEXT.get();
        if (entries.isEmpty()) {
            return null;
        }
        return entries.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining(", "));
    }

    public static void clear() {
        CONTEXT.remove();
    }
}
