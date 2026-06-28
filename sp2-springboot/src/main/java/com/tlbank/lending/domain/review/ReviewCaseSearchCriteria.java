package com.tlbank.lending.domain.review;

import java.time.LocalDate;

/**
 * Search criteria for filtering review cases.
 */
public record ReviewCaseSearchCriteria(
        ReviewStatus status,
        String applicantName,
        String productId,
        LocalDate dateFrom,
        LocalDate dateTo
) {
}
