package com.tlbank.lending.application.review.service;

/**
 * Command to add a remark to a review case.
 */
public record AddRemarkCommand(
        String reviewCaseId,
        String content,
        String operator
) {
}
