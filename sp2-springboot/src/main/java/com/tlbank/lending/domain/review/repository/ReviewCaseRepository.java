package com.tlbank.lending.domain.review.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tlbank.lending.domain.review.ReviewCase;
import com.tlbank.lending.domain.review.ReviewCaseId;
import com.tlbank.lending.domain.review.ReviewCaseSearchCriteria;
import com.tlbank.lending.domain.review.ReviewStatus;

/**
 * Domain repository port for {@link ReviewCase} persistence.
 */
public interface ReviewCaseRepository {

    ReviewCase save(ReviewCase reviewCase);

    Optional<ReviewCase> findById(ReviewCaseId id);

    Optional<ReviewCase> findByApplicationId(String applicationId);

    Page<ReviewCase> findByStatus(ReviewStatus status, Pageable pageable);

    Page<ReviewCase> search(ReviewCaseSearchCriteria criteria, Pageable pageable);
}
