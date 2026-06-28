package com.tlbank.lending.presentation.api.v1;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tlbank.lending.application.parameter.service.SystemParameterResponse;
import com.tlbank.lending.application.parameter.service.SystemParameterService;
import com.tlbank.lending.application.parameter.service.UpdateParameterCommand;
import com.tlbank.lending.common.config.StandardApiResponses;
import com.tlbank.lending.common.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * REST API for administrative system parameter management.
 */
@RestController
@RequestMapping("/api/v1/admin/system-parameters")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Admin Parameters", description = "System parameter configuration APIs")
@StandardApiResponses
@RequiredArgsConstructor
public class SystemParameterApiController {

    private final SystemParameterService systemParameterService;

    @GetMapping
    @Operation(summary = "List parameters", description = "Returns enabled system parameters, optionally filtered by group.")
    public ApiResponse<List<SystemParameterResponse>> getParameters(
            @Parameter(description = "Parameter group filter (e.g. OTP, CACHE)") @RequestParam(required = false) String group) {
        List<SystemParameterResponse> parameters = group == null || group.isBlank()
                ? systemParameterService.findAllEnabled()
                : systemParameterService.findByGroup(group);
        return ApiResponse.success(parameters);
    }

    @PutMapping("/{paramId}")
    @Operation(summary = "Update parameter", description = "Updates the value of a system parameter.")
    public ApiResponse<SystemParameterResponse> updateParameter(
            @Parameter(description = "Database parameter ID") @PathVariable Long paramId,
            @Valid @RequestBody UpdateParameterCommand command) {
        UpdateParameterCommand updateCommand = new UpdateParameterCommand(paramId, command.paramValue());
        return ApiResponse.success(systemParameterService.update(updateCommand));
    }
}
