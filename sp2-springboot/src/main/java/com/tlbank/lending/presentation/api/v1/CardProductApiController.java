package com.tlbank.lending.presentation.api.v1;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tlbank.lending.application.application.service.ApplicationAppService;
import com.tlbank.lending.application.application.service.CardProductResponse;
import com.tlbank.lending.common.config.StandardApiResponses;
import com.tlbank.lending.common.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * REST API for browsing available credit card products.
 */
@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Products", description = "Credit card product catalog APIs")
@StandardApiResponses
@RequiredArgsConstructor
public class CardProductApiController {

    private final ApplicationAppService applicationAppService;

    @GetMapping
    @Operation(summary = "List products", description = "Returns all enabled credit card products.")
    public ApiResponse<List<CardProductResponse>> getProducts() {
        return ApiResponse.success(applicationAppService.findAllEnabledProducts());
    }
}
