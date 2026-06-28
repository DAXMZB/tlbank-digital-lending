package com.tlbank.lending.infrastructure.persistence.review;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.tlbank.lending.domain.review.ReviewCase;
import com.tlbank.lending.domain.review.ReviewCaseId;
import com.tlbank.lending.domain.review.ReviewCaseSearchCriteria;
import com.tlbank.lending.domain.review.ReviewRemark;
import com.tlbank.lending.domain.review.ReviewStatus;
import com.tlbank.lending.domain.review.repository.ReviewCaseRepository;
import com.tlbank.lending.infrastructure.persistence.application.ApplicationEntity;
import com.tlbank.lending.infrastructure.persistence.application.ApplicationJpaRepository;

import lombok.RequiredArgsConstructor;

/**
 * JPA-backed implementation of the {@link ReviewCaseRepository} domain port.
 */
@Repository
@RequiredArgsConstructor
public class ReviewCaseRepositoryImpl implements ReviewCaseRepository {

    private final ReviewCaseJpaRepository reviewCaseJpaRepository;
    private final ApplicationJpaRepository applicationJpaRepository;

    @Override
    public ReviewCase save(ReviewCase reviewCase) {
        ReviewCaseEntity entity = reviewCaseJpaRepository.findByReviewCaseNo(reviewCase.getReviewCaseId().value())
                .map(existing -> {
                    applyToEntity(existing, reviewCase);
                    return existing;
                })
                .orElseGet(() -> toEntity(reviewCase));

        return toDomain(reviewCaseJpaRepository.save(entity));
    }

    @Override
    public Optional<ReviewCase> findById(ReviewCaseId id) {
        return reviewCaseJpaRepository.findByReviewCaseNo(id.value()).map(this::toDomain);
    }

    @Override
    public Optional<ReviewCase> findByApplicationId(String applicationId) {
        return reviewCaseJpaRepository.findByApplicationNo(applicationId).map(this::toDomain);
    }

    @Override
    public Page<ReviewCase> findByStatus(ReviewStatus status, Pageable pageable) {
        return reviewCaseJpaRepository.findByStatus(status, pageable).map(this::toDomain);
    }

    @Override
    public Page<ReviewCase> search(ReviewCaseSearchCriteria criteria, Pageable pageable) {
        LocalDateTime dateFrom = criteria.dateFrom() != null
                ? criteria.dateFrom().atStartOfDay()
                : null;
        LocalDateTime dateTo = criteria.dateTo() != null
                ? criteria.dateTo().plusDays(1).atStartOfDay()
                : null;

        return reviewCaseJpaRepository.search(
                criteria.status(),
                blankToNull(criteria.applicantName()),
                blankToNull(criteria.productId()),
                dateFrom,
                dateTo,
                pageable
        ).map(this::toDomain);
    }

    private ReviewCase toDomain(ReviewCaseEntity entity) {
        return ReviewCase.builder()
                .reviewCaseId(ReviewCaseId.of(entity.getReviewCaseNo()))
                .applicationId(entity.getApplicationNo())
                .assignedTo(entity.getAssignedTo())
                .reviewStatus(entity.getStatus())
                .remarks(new ArrayList<>(entity.getRemarks().stream()
                        .map(remark -> ReviewRemark.builder()
                                .remarkId(remark.getId())
                                .reviewCaseId(entity.getReviewCaseNo())
                                .content(remark.getContent() != null ? remark.getContent() : remark.getRemark())
                                .operator(remark.getOperator())
                                .createdAt(remark.getCreatedAt())
                                .build())
                        .toList()))
                .reviewedAt(entity.getReviewedAt())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    private ReviewCaseEntity toEntity(ReviewCase reviewCase) {
        ApplicationEntity application = applicationJpaRepository
                .findByApplicationNo(reviewCase.getApplicationId())
                .orElse(null);

        ReviewCaseEntity entity = ReviewCaseEntity.builder()
                .reviewCaseNo(reviewCase.getReviewCaseId().value())
                .applicationNo(reviewCase.getApplicationId())
                .application(application)
                .assignedTo(reviewCase.getAssignedTo())
                .status(reviewCase.getReviewStatus())
                .reviewedAt(reviewCase.getReviewedAt())
                .remarks(new ArrayList<>())
                .build();

        syncRemarks(entity, reviewCase);
        return entity;
    }

    private void applyToEntity(ReviewCaseEntity entity, ReviewCase reviewCase) {
        entity.setAssignedTo(reviewCase.getAssignedTo());
        entity.setStatus(reviewCase.getReviewStatus());
        entity.setReviewedAt(reviewCase.getReviewedAt());
        syncRemarks(entity, reviewCase);
    }

    private void syncRemarks(ReviewCaseEntity entity, ReviewCase reviewCase) {
        int existingCount = entity.getRemarks().size();
        var domainRemarks = reviewCase.getRemarks();

        for (int i = existingCount; i < domainRemarks.size(); i++) {
            ReviewRemark remark = domainRemarks.get(i);
            entity.getRemarks().add(ReviewRemarkEntity.builder()
                    .reviewCase(entity)
                    .content(remark.getContent())
                    .remark(remark.getContent())
                    .operator(remark.getOperator())
                    .createdAt(remark.getCreatedAt() != null ? remark.getCreatedAt() : LocalDateTime.now())
                    .build());
        }
    }

    private String blankToNull(String value) {
        return value == null || value.isBlank() ? null : value.trim();
    }
}
