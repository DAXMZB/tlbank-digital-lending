package com.tlbank.lending.domain.review;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

/**
 * A remark added to a review case by a reviewer.
 */
@Getter
@Builder
public class ReviewRemark {

    private Long remarkId;
    private String reviewCaseId;
    private String content;
    private String operator;
    private LocalDateTime createdAt;
}
