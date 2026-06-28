package com.tlbank.lending.application.parameter.service;

import jakarta.validation.constraints.NotBlank;

/**
 * Command object for updating a system parameter value.
 */
public record UpdateParameterCommand(
        Long paramId,
        @NotBlank String paramValue
) {
}
