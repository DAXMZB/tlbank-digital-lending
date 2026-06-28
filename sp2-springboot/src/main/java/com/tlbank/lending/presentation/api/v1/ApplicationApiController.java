package com.tlbank.lending.presentation.api.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tlbank.lending.application.application.service.ApplicationAppService;
import com.tlbank.lending.application.application.service.ApplicationDetailResponse;
import com.tlbank.lending.application.application.service.ApplicationSummaryResponse;
import com.tlbank.lending.application.dto.request.CancelApplicationRequest;
import com.tlbank.lending.application.dto.request.CreateApplicationRequest;
import com.tlbank.lending.application.dto.response.DocumentUploadResponse;
import com.tlbank.lending.application.idempotency.IdempotencyService;
import com.tlbank.lending.common.config.StandardApiResponses;
import com.tlbank.lending.common.response.ApiResponse;
import com.tlbank.lending.domain.application.DocumentType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * REST API for credit card application creation and retrieval.
 */
@RestController
@RequestMapping("/api/v1/applications")
@Tag(name = "Applications", description = "Credit card application lifecycle APIs")
@RequiredArgsConstructor
public class ApplicationApiController {

    private final ApplicationAppService applicationAppService;
    private final IdempotencyService idempotencyService;

    @PostMapping
    @StandardApiResponses
    @Operation(
            summary = "Create application",
            description = "Creates a new credit card application draft. "
                    + "Supports Idempotency-Key header (24h TTL, Redis-backed in dev/prod).")
    public ResponseEntity<ApiResponse<ApplicationSummaryResponse>> createApplication(
            @Parameter(description = "Unique client-generated key to prevent duplicate submissions")
            @RequestHeader(value = "Idempotency-Key", required = false) String idempotencyKey,
            @Valid @RequestBody CreateApplicationRequest request) {

        return idempotencyService.execute(idempotencyKey, request, () -> {
            ApplicationSummaryResponse response = applicationAppService.createApplication(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response));
        });
    }

    @GetMapping("/{applicationId}")
    @StandardApiResponses
    @Operation(summary = "Get application", description = "Retrieves application details by application ID.")
    public ApiResponse<ApplicationDetailResponse> getApplication(
            @Parameter(description = "Business application identifier") @PathVariable String applicationId) {
        return ApiResponse.success(applicationAppService.getApplication(applicationId));
    }

    @PostMapping("/{applicationId}/documents")
    @StandardApiResponses
    @Operation(summary = "Upload document", description = "Uploads an identity or income document for an application.")
    public ApiResponse<DocumentUploadResponse> uploadDocument(
            @Parameter(description = "Business application identifier") @PathVariable String applicationId,
            @Parameter(description = "Document category") @RequestParam DocumentType documentType,
            @Parameter(description = "Document file (PDF, JPG, PNG)") @RequestParam MultipartFile file) {
        return ApiResponse.success(applicationAppService.uploadDocuments(applicationId, documentType, file));
    }

    @PostMapping("/{applicationId}/actions/submit")
    @StandardApiResponses
    @Operation(summary = "Submit application", description = "Submits a completed application for credit review.")
    public ApiResponse<ApplicationSummaryResponse> submitApplication(
            @Parameter(description = "Business application identifier") @PathVariable String applicationId) {
        return ApiResponse.success(applicationAppService.submitApplication(applicationId));
    }

    @PostMapping("/{applicationId}/actions/cancel")
    @StandardApiResponses
    @Operation(summary = "Cancel application", description = "Cancels an in-progress application with a reason.")
    public ApiResponse<ApplicationSummaryResponse> cancelApplication(
            @Parameter(description = "Business application identifier") @PathVariable String applicationId,
            @Valid @RequestBody CancelApplicationRequest request) {
        return ApiResponse.success(
                applicationAppService.cancelApplication(applicationId, request.reason(), "APPLICANT"));
    }
}
