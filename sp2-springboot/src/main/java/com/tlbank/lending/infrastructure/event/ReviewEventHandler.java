package com.tlbank.lending.infrastructure.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.tlbank.lending.domain.event.ApplicationSubmittedEvent;
import com.tlbank.lending.domain.review.ReviewCase;
import com.tlbank.lending.domain.review.repository.ReviewCaseRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Handles domain events related to the credit review workflow.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class ReviewEventHandler {

    private final ReviewCaseRepository reviewCaseRepository;

    @EventListener
    public void onApplicationSubmitted(ApplicationSubmittedEvent event) {
        ReviewCase reviewCase = ReviewCase.createFor(event.applicationId());
        reviewCaseRepository.save(reviewCase);
        log.info("ReviewCase created for application: {}", event.applicationId());
    }
}
