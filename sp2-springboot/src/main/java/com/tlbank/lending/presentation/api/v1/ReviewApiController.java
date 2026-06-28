package com.tlbank.lending.presentation.api.v1;

import java.time.LocalDate;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tlbank.lending.application.review.service.AddRemarkCommand;
import com.tlbank.lending.application.review.service.ApproveCaseCommand;
import com.tlbank.lending.application.review.service.RejectCaseCommand;
import com.tlbank.lending.application.review.service.ReviewAppService;
import com.tlbank.lending.application.review.service.ReviewCaseDetailResponse;
import com.tlbank.lending.application.review.service.ReviewCaseSummaryResponse;
import com.tlbank.lending.application.review.service.ReviewRemarkResponse;
import com.tlbank.lending.common.config.StandardApiResponses;
import com.tlbank.lending.common.response.ApiResponse;
import com.tlbank.lending.common.response.PageResponse;
import com.tlbank.lending.domain.review.ReviewCaseSearchCriteria;
import com.tlbank.lending.domain.review.ReviewStatus;
import com.tlbank.lending.presentation.api.v1.review.AddRemarkRequest;
import com.tlbank.lending.presentation.api.v1.review.ApproveReviewRequest;
import com.tlbank.lending.presentation.api.v1.review.RejectReviewRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * REST API for credit review case management.
 */
@RestController
@RequestMapping("/api/v1/review/cases")
@PreAuthorize("hasAnyRole('REVIEWER','ADMIN')")
@Tag(name = "Review", description = "Credit review case management APIs")
@StandardApiResponses
@RequiredArgsConstructor
public class ReviewApiController {

    private final ReviewAppService reviewAppService;

    @GetMapping
    @Operation(summary = "Search review cases", description = "Returns a paginated list of credit review cases.")
    public ApiResponse<PageResponse<ReviewCaseSummaryResponse>> getCases(
            @Parameter(description = "Filter by review status") @RequestParam(required = false) ReviewStatus status,
            @Parameter(description = "Partial applicant name match") @RequestParam(required = false) String applicantName,
            @Parameter(description = "Card product identifier") @RequestParam(required = false) String productId,
            @Parameter(description = "Created date from (inclusive)") @RequestParam(required = false) LocalDate dateFrom,
            @Parameter(description = "Created date to (inclusive)") @RequestParam(required = false) LocalDate dateTo,
            @Parameter(description = "Page index (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        ReviewCaseSearchCriteria criteria = new ReviewCaseSearchCriteria(
                status, applicantName, productId, dateFrom, dateTo);
        return ApiResponse.success(reviewAppService.searchCases(criteria, pageable));
    }

    @GetMapping("/{reviewCaseId}")
    @Operation(summary = "Get review case", description = "Retrieves detailed information for a review case.")
    public ApiResponse<ReviewCaseDetailResponse> getCaseDetail(
            @Parameter(description = "Business review case identifier") @PathVariable String reviewCaseId) {
        return ApiResponse.success(reviewAppService.getCaseDetail(reviewCaseId));
    }

    @PostMapping("/{reviewCaseId}/actions/approve")
    @Operation(summary = "Approve case", description = "Approves a credit review case and updates the application.")
    public ApiResponse<Void> approveCase(
            @Parameter(description = "Business review case identifier") @PathVariable String reviewCaseId,
            @Valid @RequestBody ApproveReviewRequest request,
            @AuthenticationPrincipal UserDetails user) {
        reviewAppService.approveCase(new ApproveCaseCommand(
                reviewCaseId, request.remark(), user.getUsername()));
        return ApiResponse.success(null);
    }

    @PostMapping("/{reviewCaseId}/actions/reject")
    @Operation(summary = "Reject case", description = "Rejects a credit review case and updates the application.")
    public ApiResponse<Void> rejectCase(
            @Parameter(description = "Business review case identifier") @PathVariable String reviewCaseId,
            @Valid @RequestBody RejectReviewRequest request,
            @AuthenticationPrincipal UserDetails user) {
        reviewAppService.rejectCase(new RejectCaseCommand(
                reviewCaseId, request.remark(), user.getUsername()));
        return ApiResponse.success(null);
    }

    @PostMapping("/{reviewCaseId}/remarks")
    @Operation(summary = "Add remark", description = "Adds an internal review remark to a case.")
    public ResponseEntity<ApiResponse<ReviewRemarkResponse>> addRemark(
            @Parameter(description = "Business review case identifier") @PathVariable String reviewCaseId,
            @Valid @RequestBody AddRemarkRequest request,
            @AuthenticationPrincipal UserDetails user) {
        ReviewRemarkResponse response = reviewAppService.addRemark(new AddRemarkCommand(
                reviewCaseId, request.content(), user.getUsername()));
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response));
    }
}
