package com.tlbank.lending.domain.review;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.tlbank.lending.common.exception.WorkflowException;

import lombok.Builder;
import lombok.Getter;

/**
 * Aggregate root representing a credit review case for a submitted application.
 */
@Getter
@Builder
public class ReviewCase {

    private ReviewCaseId reviewCaseId;
    private String applicationId;
    private String assignedTo;
    private ReviewStatus reviewStatus;
    @Builder.Default
    private List<ReviewRemark> remarks = new ArrayList<>();
    private LocalDateTime reviewedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Creates a new review case for the given application in PENDING status.
     */
    public static ReviewCase createFor(String applicationId) {
        return ReviewCase.builder()
                .reviewCaseId(ReviewCaseId.generate())
                .applicationId(applicationId)
                .reviewStatus(ReviewStatus.PENDING)
                .remarks(new ArrayList<>())
                .build();
    }

    /**
     * Assigns this case to a reviewer username.
     */
    public void assign(String username) {
        this.assignedTo = username;
    }

    /**
     * Starts the review process for this case.
     */
    public void startReview(String operator) {
        transitionTo(ReviewStatus.UNDER_REVIEW);
        addRemarkInternal("Review started", operator);
    }

    /**
     * Approves this review case.
     */
    public void approve(String operator, String remark) {
        if (reviewStatus != ReviewStatus.UNDER_REVIEW) {
            throw new WorkflowException("Cannot approve review case from status " + reviewStatus);
        }
        transitionTo(ReviewStatus.APPROVED);
        this.reviewedAt = LocalDateTime.now();
        addRemarkInternal(remark, operator);
    }

    /**
     * Rejects this review case.
     */
    public void reject(String operator, String remark) {
        if (reviewStatus != ReviewStatus.UNDER_REVIEW) {
            throw new WorkflowException("Cannot reject review case from status " + reviewStatus);
        }
        transitionTo(ReviewStatus.REJECTED);
        this.reviewedAt = LocalDateTime.now();
        addRemarkInternal(remark, operator);
    }

    /**
     * Adds a remark to this review case.
     */
    public void addRemark(String content, String operator) {
        addRemarkInternal(content, operator);
    }

    private void transitionTo(ReviewStatus next) {
        if (reviewStatus == next) {
            throw new WorkflowException("Cannot transition from " + reviewStatus + " to " + next);
        }
        if (reviewStatus == ReviewStatus.PENDING && next == ReviewStatus.UNDER_REVIEW) {
            this.reviewStatus = next;
            return;
        }
        if (reviewStatus == ReviewStatus.UNDER_REVIEW
                && (next == ReviewStatus.APPROVED || next == ReviewStatus.REJECTED)) {
            this.reviewStatus = next;
            return;
        }
        throw new WorkflowException("Cannot transition from " + reviewStatus + " to " + next);
    }

    private void addRemarkInternal(String content, String operator) {
        remarks.add(ReviewRemark.builder()
                .reviewCaseId(reviewCaseId.value())
                .content(content)
                .operator(operator)
                .createdAt(LocalDateTime.now())
                .build());
    }
}
