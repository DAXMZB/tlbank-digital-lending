package com.tlbank.lending.infrastructure.persistence.review;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tlbank.lending.domain.review.ReviewStatus;

/**
 * Spring Data JPA repository for {@link ReviewCaseEntity}.
 */
public interface ReviewCaseJpaRepository extends JpaRepository<ReviewCaseEntity, Long> {

    Optional<ReviewCaseEntity> findByReviewCaseNo(String reviewCaseNo);

    Optional<ReviewCaseEntity> findByApplicationNo(String applicationNo);

    Page<ReviewCaseEntity> findByStatus(ReviewStatus status, Pageable pageable);

    @Query("""
            SELECT rc FROM ReviewCaseEntity rc
            JOIN ApplicationEntity app ON rc.applicationNo = app.applicationNo
            JOIN CardProductEntity cp ON app.productId = cp.id
            WHERE (:status IS NULL OR rc.status = :status)
              AND (:applicantName IS NULL OR LOWER(app.applicant.fullName) LIKE LOWER(CONCAT('%', :applicantName, '%')))
              AND (:productId IS NULL OR cp.productCode = :productId)
              AND (:dateFrom IS NULL OR app.submittedAt >= :dateFrom)
              AND (:dateTo IS NULL OR app.submittedAt < :dateTo)
            ORDER BY rc.createdAt DESC
            """)
    Page<ReviewCaseEntity> search(
            @Param("status") ReviewStatus status,
            @Param("applicantName") String applicantName,
            @Param("productId") String productId,
            @Param("dateFrom") LocalDateTime dateFrom,
            @Param("dateTo") LocalDateTime dateTo,
            Pageable pageable);
}
