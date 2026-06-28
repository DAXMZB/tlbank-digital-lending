package com.tlbank.lending.application.review;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.tlbank.lending.application.review.service.AddRemarkCommand;
import com.tlbank.lending.application.review.service.ApproveCaseCommand;
import com.tlbank.lending.application.review.service.RejectCaseCommand;
import com.tlbank.lending.application.review.service.ReviewAppService;
import com.tlbank.lending.application.review.service.ReviewCaseSummaryResponse;
import com.tlbank.lending.common.response.PageResponse;
import com.tlbank.lending.domain.application.Address;
import com.tlbank.lending.domain.application.Applicant;
import com.tlbank.lending.domain.application.Application;
import com.tlbank.lending.domain.application.ApplicationId;
import com.tlbank.lending.domain.application.ApplicationStatus;
import com.tlbank.lending.domain.application.CardProductId;
import com.tlbank.lending.domain.application.Email;
import com.tlbank.lending.domain.application.MobileNumber;
import com.tlbank.lending.domain.application.repository.ApplicationRepository;
import com.tlbank.lending.domain.event.ApplicationApprovedEvent;
import com.tlbank.lending.domain.event.ApplicationRejectedEvent;
import com.tlbank.lending.domain.product.CardProduct;
import com.tlbank.lending.domain.product.CardType;
import com.tlbank.lending.domain.product.repository.CardProductRepository;
import com.tlbank.lending.domain.review.ReviewCase;
import com.tlbank.lending.domain.review.ReviewCaseId;
import com.tlbank.lending.domain.review.ReviewCaseSearchCriteria;
import com.tlbank.lending.domain.review.ReviewStatus;
import com.tlbank.lending.domain.review.repository.ReviewCaseRepository;

@ExtendWith(MockitoExtension.class)
class ReviewAppServiceTest {

    private static final String REVIEW_CASE_ID = "RC-20250607-1234";
    private static final String APPLICATION_ID = "APP-20250607120000-1234";
    private static final Clock FIXED_CLOCK = Clock.fixed(
            Instant.parse("2024-01-01T12:00:00Z"), ZoneId.systemDefault());

    @Mock
    private ReviewCaseRepository reviewCaseRepository;

    @Mock
    private ApplicationRepository applicationRepository;

    @Mock
    private CardProductRepository cardProductRepository;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private ReviewAppService reviewAppService;

    @BeforeEach
    void setUp() {
        reviewAppService = new ReviewAppService(
                reviewCaseRepository,
                applicationRepository,
                cardProductRepository,
                eventPublisher,
                FIXED_CLOCK
        );
    }

    @Test
    void approveCase_shouldUpdateBothReviewCaseAndApplication() {
        ReviewCase reviewCase = underReviewCase();
        Application application = underReviewApplication();

        when(reviewCaseRepository.findById(ReviewCaseId.of(REVIEW_CASE_ID))).thenReturn(Optional.of(reviewCase));
        when(applicationRepository.findById(ApplicationId.of(APPLICATION_ID))).thenReturn(Optional.of(application));
        when(reviewCaseRepository.save(any(ReviewCase.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(applicationRepository.save(any(Application.class))).thenAnswer(invocation -> invocation.getArgument(0));

        reviewAppService.approveCase(new ApproveCaseCommand(REVIEW_CASE_ID, "Approved", "reviewer1"));

        ArgumentCaptor<ReviewCase> reviewCaptor = ArgumentCaptor.forClass(ReviewCase.class);
        ArgumentCaptor<Application> applicationCaptor = ArgumentCaptor.forClass(Application.class);
        verify(reviewCaseRepository).save(reviewCaptor.capture());
        verify(applicationRepository).save(applicationCaptor.capture());

        assertThat(reviewCaptor.getValue().getReviewStatus()).isEqualTo(ReviewStatus.APPROVED);
        assertThat(applicationCaptor.getValue().getStatus()).isEqualTo(ApplicationStatus.APPROVED);
    }

    @Test
    void rejectCase_shouldUpdateBothReviewCaseAndApplication() {
        ReviewCase reviewCase = underReviewCase();
        Application application = underReviewApplication();

        when(reviewCaseRepository.findById(ReviewCaseId.of(REVIEW_CASE_ID))).thenReturn(Optional.of(reviewCase));
        when(applicationRepository.findById(ApplicationId.of(APPLICATION_ID))).thenReturn(Optional.of(application));
        when(reviewCaseRepository.save(any(ReviewCase.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(applicationRepository.save(any(Application.class))).thenAnswer(invocation -> invocation.getArgument(0));

        reviewAppService.rejectCase(new RejectCaseCommand(REVIEW_CASE_ID, "Rejected", "reviewer1"));

        ArgumentCaptor<ReviewCase> reviewCaptor = ArgumentCaptor.forClass(ReviewCase.class);
        ArgumentCaptor<Application> applicationCaptor = ArgumentCaptor.forClass(Application.class);
        verify(reviewCaseRepository).save(reviewCaptor.capture());
        verify(applicationRepository).save(applicationCaptor.capture());

        assertThat(reviewCaptor.getValue().getReviewStatus()).isEqualTo(ReviewStatus.REJECTED);
        assertThat(applicationCaptor.getValue().getStatus()).isEqualTo(ApplicationStatus.REJECTED);
    }

    @Test
    void approveCase_shouldPublishApplicationApprovedEvent() {
        ReviewCase reviewCase = underReviewCase();
        Application application = underReviewApplication();

        when(reviewCaseRepository.findById(ReviewCaseId.of(REVIEW_CASE_ID))).thenReturn(Optional.of(reviewCase));
        when(applicationRepository.findById(ApplicationId.of(APPLICATION_ID))).thenReturn(Optional.of(application));
        when(reviewCaseRepository.save(any(ReviewCase.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(applicationRepository.save(any(Application.class))).thenAnswer(invocation -> invocation.getArgument(0));

        reviewAppService.approveCase(new ApproveCaseCommand(REVIEW_CASE_ID, "Approved", "reviewer1"));

        ArgumentCaptor<ApplicationApprovedEvent> eventCaptor = ArgumentCaptor.forClass(ApplicationApprovedEvent.class);
        verify(eventPublisher).publishEvent(eventCaptor.capture());

        ApplicationApprovedEvent event = eventCaptor.getValue();
        assertThat(event.applicationId()).isEqualTo(APPLICATION_ID);
        assertThat(event.mobile()).isEqualTo("0912345678");
        assertThat(event.email()).isEqualTo("test@example.com");
        assertThat(event.timestamp()).isEqualTo(LocalDateTime.now(FIXED_CLOCK));
    }

    @Test
    void rejectCase_shouldPublishApplicationRejectedEvent() {
        ReviewCase reviewCase = underReviewCase();
        Application application = underReviewApplication();

        when(reviewCaseRepository.findById(ReviewCaseId.of(REVIEW_CASE_ID))).thenReturn(Optional.of(reviewCase));
        when(applicationRepository.findById(ApplicationId.of(APPLICATION_ID))).thenReturn(Optional.of(application));
        when(reviewCaseRepository.save(any(ReviewCase.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(applicationRepository.save(any(Application.class))).thenAnswer(invocation -> invocation.getArgument(0));

        reviewAppService.rejectCase(new RejectCaseCommand(REVIEW_CASE_ID, "Rejected", "reviewer1"));

        ArgumentCaptor<ApplicationRejectedEvent> eventCaptor = ArgumentCaptor.forClass(ApplicationRejectedEvent.class);
        verify(eventPublisher).publishEvent(eventCaptor.capture());

        ApplicationRejectedEvent event = eventCaptor.getValue();
        assertThat(event.applicationId()).isEqualTo(APPLICATION_ID);
        assertThat(event.mobile()).isEqualTo("0912345678");
        assertThat(event.email()).isEqualTo("test@example.com");
        assertThat(event.timestamp()).isEqualTo(LocalDateTime.now(FIXED_CLOCK));
    }

    @Test
    void searchCases_shouldReturnPagedSummaries() {
        ReviewCase reviewCase = underReviewCase();
        Application application = underReviewApplication();
        Page<ReviewCase> page = new PageImpl<>(List.of(reviewCase));

        when(reviewCaseRepository.search(any(ReviewCaseSearchCriteria.class), any(Pageable.class)))
                .thenReturn(page);
        when(applicationRepository.findById(ApplicationId.of(APPLICATION_ID))).thenReturn(Optional.of(application));
        when(cardProductRepository.findById(CardProductId.of("TL-CLASSIC")))
                .thenReturn(Optional.of(sampleProduct()));

        PageResponse<ReviewCaseSummaryResponse> response = reviewAppService.searchCases(
                new ReviewCaseSearchCriteria(ReviewStatus.UNDER_REVIEW, null, null, null, null),
                Pageable.unpaged());

        assertThat(response.content()).hasSize(1);
        assertThat(response.content().get(0).reviewCaseId()).isEqualTo(REVIEW_CASE_ID);
    }

    @Test
    void addRemark_shouldPersistLatestRemark() {
        ReviewCase reviewCase = underReviewCase();
        when(reviewCaseRepository.findById(ReviewCaseId.of(REVIEW_CASE_ID))).thenReturn(Optional.of(reviewCase));
        when(reviewCaseRepository.save(any(ReviewCase.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var response = reviewAppService.addRemark(new AddRemarkCommand(REVIEW_CASE_ID, "Need docs", "reviewer1"));

        assertThat(response.content()).isEqualTo("Need docs");
        assertThat(response.operator()).isEqualTo("reviewer1");
    }

    private CardProduct sampleProduct() {
        return CardProduct.builder()
                .productId(CardProductId.of("TL-CLASSIC"))
                .productCode("TL-CLASSIC")
                .productName("TL Classic Card")
                .cardType(CardType.VISA)
                .enabled(true)
                .createdAt(LocalDateTime.now(FIXED_CLOCK))
                .build();
    }

    private ReviewCase underReviewCase() {
        return ReviewCase.builder()
                .reviewCaseId(ReviewCaseId.of(REVIEW_CASE_ID))
                .applicationId(APPLICATION_ID)
                .reviewStatus(ReviewStatus.UNDER_REVIEW)
                .remarks(new ArrayList<>())
                .build();
    }

    private Application underReviewApplication() {
        return Application.builder()
                .applicationId(ApplicationId.of(APPLICATION_ID))
                .applicant(sampleApplicant())
                .cardProductId(CardProductId.of("TL-CLASSIC"))
                .status(ApplicationStatus.UNDER_REVIEW)
                .workflowHistories(new ArrayList<>())
                .documentInfos(new ArrayList<>())
                .build();
    }

    private Applicant sampleApplicant() {
        return new Applicant(
                "Test User",
                "A123456789",
                MobileNumber.of("0912345678"),
                Email.of("test@example.com"),
                LocalDate.of(1990, 1, 1),
                new Address("台北市", "信義區", "信義路一段1號", "110")
        );
    }
}
