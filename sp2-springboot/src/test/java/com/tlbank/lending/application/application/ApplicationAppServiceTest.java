package com.tlbank.lending.application.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
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

import com.tlbank.lending.application.application.service.ApplicationAppService;
import com.tlbank.lending.application.application.service.ApplicationSummaryResponse;
import com.tlbank.lending.application.dto.request.AddressRequest;
import com.tlbank.lending.application.dto.request.ApplicantRequest;
import com.tlbank.lending.application.dto.request.CreateApplicationRequest;
import com.tlbank.lending.common.exception.BusinessException;
import com.tlbank.lending.common.exception.ErrorCode;
import com.tlbank.lending.domain.application.Application;
import com.tlbank.lending.domain.application.ApplicationId;
import com.tlbank.lending.domain.application.ApplicationStatus;
import com.tlbank.lending.domain.application.CardProductId;
import com.tlbank.lending.domain.application.DocumentInfo;
import com.tlbank.lending.domain.application.DocumentType;
import com.tlbank.lending.domain.application.repository.ApplicationRepository;
import com.tlbank.lending.domain.event.ApplicationSubmittedEvent;
import com.tlbank.lending.domain.product.CardProduct;
import com.tlbank.lending.domain.product.CardType;
import com.tlbank.lending.domain.product.repository.CardProductRepository;
import com.tlbank.lending.infrastructure.storage.DocumentStorageService;

@ExtendWith(MockitoExtension.class)
class ApplicationAppServiceTest {

    private static final Clock FIXED_CLOCK = Clock.fixed(
            Instant.parse("2024-01-01T12:00:00Z"), ZoneId.systemDefault());

    @Mock
    private ApplicationRepository applicationRepository;

    @Mock
    private CardProductRepository cardProductRepository;

    @Mock
    private DocumentStorageService documentStorageService;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private ApplicationAppService applicationAppService;

    @BeforeEach
    void setUp() {
        applicationAppService = new ApplicationAppService(
                applicationRepository,
                cardProductRepository,
                documentStorageService,
                eventPublisher,
                FIXED_CLOCK
        );
    }

    @Test
    void createApplication_whenProductExists_shouldReturnSummaryWithInitStatus() {
        CreateApplicationRequest request = sampleRequest("TL-CLASSIC");

        when(cardProductRepository.findById(CardProductId.of("TL-CLASSIC")))
                .thenReturn(Optional.of(sampleProduct(true)));
        when(applicationRepository.save(any(Application.class))).thenAnswer(invocation -> {
            Application app = invocation.getArgument(0);
            return Application.builder()
                    .applicationId(app.getApplicationId())
                    .applicant(app.getApplicant())
                    .cardProductId(app.getCardProductId())
                    .status(app.getStatus())
                    .workflowHistories(new ArrayList<>())
                    .documentInfos(new ArrayList<>())
                    .createdAt(LocalDateTime.now())
                    .build();
        });

        ApplicationSummaryResponse response = applicationAppService.createApplication(request);

        assertThat(response.applicationId()).matches("^APP-\\d{14}-\\d{4}$");
        assertThat(response.status()).isEqualTo(ApplicationStatus.INIT);
        assertThat(response.createdAt()).isNotNull();

        ArgumentCaptor<Application> captor = ArgumentCaptor.forClass(Application.class);
        verify(applicationRepository).save(captor.capture());
        assertThat(captor.getValue().getStatus()).isEqualTo(ApplicationStatus.INIT);
    }

    @Test
    void createApplication_whenProductNotFound_shouldThrowBusinessException() {
        CreateApplicationRequest request = sampleRequest("UNKNOWN");

        when(cardProductRepository.findById(CardProductId.of("UNKNOWN"))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> applicationAppService.createApplication(request))
                .isInstanceOf(BusinessException.class)
                .extracting(ex -> ((BusinessException) ex).getErrorCode())
                .isEqualTo(ErrorCode.PRODUCT_NOT_FOUND);

        verify(applicationRepository, never()).save(any(Application.class));
    }

    @Test
    void createApplication_whenProductDisabled_shouldThrowBusinessException() {
        CreateApplicationRequest request = sampleRequest("TL-CLASSIC");

        when(cardProductRepository.findById(CardProductId.of("TL-CLASSIC")))
                .thenReturn(Optional.of(sampleProduct(false)));

        assertThatThrownBy(() -> applicationAppService.createApplication(request))
                .isInstanceOf(BusinessException.class)
                .extracting(ex -> ((BusinessException) ex).getErrorCode())
                .isEqualTo(ErrorCode.PRODUCT_NOT_FOUND);
    }

    @Test
    void getApplication_shouldReturnMaskedApplicant() {
        Application application = savedApplication();
        when(applicationRepository.findById(ApplicationId.of("APP-20240101120000-1234")))
                .thenReturn(Optional.of(application));
        when(cardProductRepository.findById(CardProductId.of("TL-CLASSIC")))
                .thenReturn(Optional.of(sampleProduct(true)));

        var response = applicationAppService.getApplication("APP-20240101120000-1234");

        assertThat(response.maskedApplicant().maskedMobile()).isEqualTo("0912****78");
        assertThat(response.maskedApplicant().maskedNationalId()).isEqualTo("A123****89");
        assertThat(response.maskedApplicant().maskedEmail()).contains("***");
    }

    @Test
    void submitApplication_whenDocumentsIncomplete_shouldThrowBusinessException() {
        Application application = Application.builder()
                .applicationId(ApplicationId.of("APP-20240101120000-1234"))
                .applicant(new com.tlbank.lending.domain.application.Applicant(
                        "Test User",
                        "A123456789",
                        com.tlbank.lending.domain.application.MobileNumber.of("0912345678"),
                        com.tlbank.lending.domain.application.Email.of("test@example.com"),
                        LocalDate.of(1990, 1, 1),
                        new com.tlbank.lending.domain.application.Address("台北市", "信義區", "信義路一段1號", "110")
                ))
                .cardProductId(CardProductId.of("TL-CLASSIC"))
                .status(ApplicationStatus.DOCUMENT_UPLOADED)
                .workflowHistories(new ArrayList<>())
                .documentInfos(new ArrayList<>(List.of(
                        new DocumentInfo(DocumentType.NATIONAL_ID, "id.pdf", "path/id.pdf", 1024L,
                                LocalDateTime.now(FIXED_CLOCK)))))
                .createdAt(LocalDateTime.now(FIXED_CLOCK))
                .build();
        when(applicationRepository.findById(ApplicationId.of("APP-20240101120000-1234")))
                .thenReturn(Optional.of(application));

        assertThatThrownBy(() -> applicationAppService.submitApplication("APP-20240101120000-1234"))
                .isInstanceOf(BusinessException.class)
                .extracting(ex -> ((BusinessException) ex).getErrorCode())
                .isEqualTo(ErrorCode.INCOMPLETE_DOCUMENTS);

        verify(applicationRepository, never()).save(any(Application.class));
        verify(eventPublisher, never()).publishEvent(any());
    }

    @Test
    void submitApplication_shouldPublishSubmittedEvent() {
        Application application = savedApplication();
        when(applicationRepository.findById(ApplicationId.of("APP-20240101120000-1234")))
                .thenReturn(Optional.of(application));
        when(applicationRepository.save(any(Application.class))).thenAnswer(invocation -> invocation.getArgument(0));

        applicationAppService.submitApplication("APP-20240101120000-1234");

        assertThat(application.getStatus()).isEqualTo(ApplicationStatus.SUBMITTED);
        ArgumentCaptor<ApplicationSubmittedEvent> eventCaptor = ArgumentCaptor.forClass(ApplicationSubmittedEvent.class);
        verify(eventPublisher).publishEvent(eventCaptor.capture());
        assertThat(eventCaptor.getValue().applicationId()).isEqualTo("APP-20240101120000-1234");
    }

    @Test
    void cancelApplication_shouldTransitionToCancelled() {
        Application application = savedApplication();
        when(applicationRepository.findById(ApplicationId.of("APP-20240101120000-1234")))
                .thenReturn(Optional.of(application));
        when(applicationRepository.save(any(Application.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var response = applicationAppService.cancelApplication("APP-20240101120000-1234", "不再需要", "APPLICANT");

        assertThat(response.status()).isEqualTo(ApplicationStatus.CANCELLED);
    }

    private Application savedApplication() {
        return Application.builder()
                .applicationId(ApplicationId.of("APP-20240101120000-1234"))
                .applicant(new com.tlbank.lending.domain.application.Applicant(
                        "Test User",
                        "A123456789",
                        com.tlbank.lending.domain.application.MobileNumber.of("0912345678"),
                        com.tlbank.lending.domain.application.Email.of("test@example.com"),
                        LocalDate.of(1990, 1, 1),
                        new com.tlbank.lending.domain.application.Address("台北市", "信義區", "信義路一段1號", "110")
                ))
                .cardProductId(CardProductId.of("TL-CLASSIC"))
                .status(ApplicationStatus.DOCUMENT_UPLOADED)
                .workflowHistories(new ArrayList<>())
                .documentInfos(new ArrayList<>(allRequiredDocuments()))
                .createdAt(LocalDateTime.now(FIXED_CLOCK))
                .build();
    }

    private List<DocumentInfo> allRequiredDocuments() {
        LocalDateTime uploadedAt = LocalDateTime.now(FIXED_CLOCK);
        return List.of(
                new DocumentInfo(DocumentType.NATIONAL_ID, "id.pdf", "path/id.pdf", 1024L, uploadedAt),
                new DocumentInfo(DocumentType.INCOME_PROOF, "income.pdf", "path/income.pdf", 1024L, uploadedAt),
                new DocumentInfo(DocumentType.RESIDENCE_PROOF, "residence.pdf", "path/residence.pdf", 1024L, uploadedAt));
    }

    private CreateApplicationRequest sampleRequest(String productId) {
        return new CreateApplicationRequest(
                new ApplicantRequest(
                        "Test User",
                        "A123456789",
                        "0912345678",
                        "test@example.com",
                        LocalDate.of(1990, 1, 1),
                        new AddressRequest("台北市", "信義區", "信義路一段1號", "110")
                ),
                productId
        );
    }

    private CardProduct sampleProduct(boolean enabled) {
        return CardProduct.builder()
                .productId(CardProductId.of("TL-CLASSIC"))
                .productCode("TL-CLASSIC")
                .productName("TL Classic Card")
                .cardType(CardType.VISA)
                .enabled(enabled)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
