package com.tlbank.lending.domain.review;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.tlbank.lending.common.exception.WorkflowException;

class ReviewCaseTest {

    @Test
    void approve_whenUnderReview_shouldSetStatusApproved() {
        ReviewCase reviewCase = sampleCase(ReviewStatus.UNDER_REVIEW);

        reviewCase.approve("reviewer1", "Approved");

        assertThat(reviewCase.getReviewStatus()).isEqualTo(ReviewStatus.APPROVED);
        assertThat(reviewCase.getReviewedAt()).isNotNull();
        assertThat(reviewCase.getRemarks()).hasSize(1);
        assertThat(reviewCase.getRemarks().get(0).getContent()).isEqualTo("Approved");
    }

    @Test
    void approve_whenNotUnderReview_shouldThrowWorkflowException() {
        ReviewCase reviewCase = sampleCase(ReviewStatus.PENDING);

        assertThatThrownBy(() -> reviewCase.approve("reviewer1", "Approved"))
                .isInstanceOf(WorkflowException.class)
                .hasMessageContaining("Cannot approve review case from status PENDING");
    }

    @Test
    void reject_whenUnderReview_shouldSetStatusRejected() {
        ReviewCase reviewCase = sampleCase(ReviewStatus.UNDER_REVIEW);

        reviewCase.reject("reviewer1", "Rejected");

        assertThat(reviewCase.getReviewStatus()).isEqualTo(ReviewStatus.REJECTED);
        assertThat(reviewCase.getReviewedAt()).isNotNull();
        assertThat(reviewCase.getRemarks()).hasSize(1);
        assertThat(reviewCase.getRemarks().get(0).getContent()).isEqualTo("Rejected");
    }

    @Test
    void createFor_shouldInitializePendingCase() {
        ReviewCase reviewCase = ReviewCase.createFor("APP-20250607120000-1234");

        assertThat(reviewCase.getApplicationId()).isEqualTo("APP-20250607120000-1234");
        assertThat(reviewCase.getReviewStatus()).isEqualTo(ReviewStatus.PENDING);
        assertThat(reviewCase.getReviewCaseId()).isNotNull();
        assertThat(reviewCase.getRemarks()).isEmpty();
    }

    @Test
    void assign_shouldSetAssignedTo() {
        ReviewCase reviewCase = sampleCase(ReviewStatus.PENDING);

        reviewCase.assign("reviewer1");

        assertThat(reviewCase.getAssignedTo()).isEqualTo("reviewer1");
    }

    @Test
    void startReview_whenPending_shouldTransitionToUnderReview() {
        ReviewCase reviewCase = sampleCase(ReviewStatus.PENDING);

        reviewCase.startReview("reviewer1");

        assertThat(reviewCase.getReviewStatus()).isEqualTo(ReviewStatus.UNDER_REVIEW);
        assertThat(reviewCase.getRemarks()).hasSize(1);
    }

    @Test
    void reject_whenNotUnderReview_shouldThrowWorkflowException() {
        ReviewCase reviewCase = sampleCase(ReviewStatus.PENDING);

        assertThatThrownBy(() -> reviewCase.reject("reviewer1", "Rejected"))
                .isInstanceOf(WorkflowException.class)
                .hasMessageContaining("Cannot reject review case from status PENDING");
    }

    @Test
    void addRemark_shouldAppendToList() {
        ReviewCase reviewCase = sampleCase(ReviewStatus.PENDING);

        reviewCase.addRemark("Need more documents", "reviewer1");

        assertThat(reviewCase.getRemarks()).hasSize(1);
        assertThat(reviewCase.getRemarks().get(0).getContent()).isEqualTo("Need more documents");
        assertThat(reviewCase.getRemarks().get(0).getOperator()).isEqualTo("reviewer1");
    }

    private ReviewCase sampleCase(ReviewStatus status) {
        return ReviewCase.builder()
                .reviewCaseId(ReviewCaseId.of("RC-20250607-1234"))
                .applicationId("APP-20250607120000-1234")
                .reviewStatus(status)
                .remarks(new ArrayList<>())
                .build();
    }
}
