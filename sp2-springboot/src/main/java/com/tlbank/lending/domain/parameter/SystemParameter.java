package com.tlbank.lending.domain.parameter;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

/**
 * Domain model representing a configurable system parameter.
 */
@Getter
@Builder
public class SystemParameter {

    private Long paramId;
    private String paramGroup;
    private String paramKey;
    private String paramValue;
    private String description;
    private boolean enabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Updates the parameter value after validating it is not blank.
     */
    public void updateValue(String newValue) {
        if (newValue == null || newValue.isBlank()) {
            throw new IllegalArgumentException("Parameter value must not be blank");
        }
        this.paramValue = newValue;
    }
}
