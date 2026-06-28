package com.tlbank.lending.application.review.service;

/**
 * Command to approve a review case.
 */
public record ApproveCaseCommand(
        String reviewCaseId,
        String remark,
        String operator
) {
}
