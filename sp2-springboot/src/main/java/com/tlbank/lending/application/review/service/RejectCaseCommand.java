package com.tlbank.lending.application.review.service;

/**
 * Command to reject a review case.
 */
public record RejectCaseCommand(
        String reviewCaseId,
        String remark,
        String operator
) {
}
