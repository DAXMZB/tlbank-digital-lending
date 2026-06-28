package com.tlbank.lending.presentation.api.v1;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tlbank.lending.application.cache.service.CacheManagementService;
import com.tlbank.lending.application.cache.service.CacheRefreshResponse;
import com.tlbank.lending.application.cache.service.CacheStatsResponse;
import com.tlbank.lending.common.config.StandardApiResponses;
import com.tlbank.lending.common.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * REST API for admin cache management operations.
 */
@RestController
@RequestMapping("/api/v1/admin/cache")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Admin Cache", description = "Cache management APIs")
@StandardApiResponses
@RequiredArgsConstructor
public class CacheManagementApiController {

    private final CacheManagementService cacheManagementService;

    @PostMapping("/refresh")
    @Operation(summary = "Refresh all caches", description = "Evicts and reloads all application caches.")
    public ApiResponse<CacheRefreshResponse> refreshAll() {
        return ApiResponse.success(new CacheRefreshResponse(cacheManagementService.refreshAll()));
    }

    @PostMapping("/refresh/system-parameters")
    @Operation(summary = "Refresh system parameters", description = "Evicts and reloads the system parameter cache.")
    public ApiResponse<CacheRefreshResponse> refreshSystemParameters() {
        return ApiResponse.success(new CacheRefreshResponse(cacheManagementService.refreshSystemParameters()));
    }

    @PostMapping("/refresh/products")
    @Operation(summary = "Refresh products", description = "Evicts and reloads the card product cache.")
    public ApiResponse<CacheRefreshResponse> refreshProducts() {
        return ApiResponse.success(new CacheRefreshResponse(cacheManagementService.refreshProducts()));
    }

    @GetMapping("/stats")
    @Operation(summary = "Cache statistics", description = "Returns current cache key statistics.")
    public ApiResponse<CacheStatsResponse> getStats() {
        return ApiResponse.success(cacheManagementService.getStats());
    }
}
