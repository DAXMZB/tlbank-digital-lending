package com.tlbank.lending.presentation.api.v1;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tlbank.lending.application.dto.request.CreateUserRequest;
import com.tlbank.lending.application.user.service.CreateUserCommand;
import com.tlbank.lending.application.user.service.UpdateUserStatusCommand;
import com.tlbank.lending.application.user.service.UserAppService;
import com.tlbank.lending.application.user.service.UserResponse;
import com.tlbank.lending.common.config.StandardApiResponses;
import com.tlbank.lending.common.response.ApiResponse;
import com.tlbank.lending.domain.user.Role;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * REST API for administrative user management operations.
 */
@RestController
@RequestMapping("/api/v1/admin/users")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Admin Users", description = "Administrative user management APIs")
@StandardApiResponses
@RequiredArgsConstructor
public class UserManagementApiController {

    private final UserAppService userAppService;

    @GetMapping
    @Operation(summary = "List users", description = "Returns all registered users.")
    public ApiResponse<List<UserResponse>> getAllUsers() {
        return ApiResponse.success(userAppService.findAll());
    }

    @PostMapping
    @Operation(summary = "Create user", description = "Creates a new internal user account.")
    public ApiResponse<UserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
        Set<Role> roles = resolveRoles(request.roles());
        CreateUserCommand command = new CreateUserCommand(
                request.username(),
                request.password(),
                request.fullName(),
                request.email(),
                roles
        );
        return ApiResponse.success(userAppService.createUser(command));
    }

    @PutMapping("/{userId}/status")
    @Operation(summary = "Update user status", description = "Enables or disables a user account.")
    public ApiResponse<Void> updateStatus(
            @Parameter(description = "Business user identifier") @PathVariable String userId,
            @Parameter(description = "Whether the account is enabled") @RequestParam boolean enabled) {
        userAppService.updateStatus(new UpdateUserStatusCommand(userId, enabled));
        return ApiResponse.success(null);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get user", description = "Retrieves a user profile by business user ID.")
    public ApiResponse<UserResponse> getUser(
            @Parameter(description = "Business user identifier") @PathVariable String userId) {
        return ApiResponse.success(userAppService.findById(userId));
    }

    private Set<Role> resolveRoles(Set<String> roleNames) {
        if (roleNames == null || roleNames.isEmpty()) {
            return EnumSet.of(Role.ROLE_USER);
        }
        return roleNames.stream()
                .map(name -> Role.valueOf(name.startsWith("ROLE_") ? name : "ROLE_" + name))
                .collect(Collectors.toCollection(() -> EnumSet.noneOf(Role.class)));
    }
}
