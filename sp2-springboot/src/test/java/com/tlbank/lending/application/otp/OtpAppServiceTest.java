package com.tlbank.lending.application.otp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tlbank.lending.application.notification.service.NotificationService;
import com.tlbank.lending.application.otp.service.OtpAppService;
import com.tlbank.lending.application.otp.service.OtpResponse;
import com.tlbank.lending.application.otp.service.SendOtpCommand;
import com.tlbank.lending.application.otp.service.VerifyOtpCommand;
import com.tlbank.lending.application.otp.service.VerifyOtpResponse;
import com.tlbank.lending.application.parameter.service.SystemParameterService;
import com.tlbank.lending.common.exception.BusinessException;
import com.tlbank.lending.common.exception.ErrorCode;
import com.tlbank.lending.domain.application.Address;
import com.tlbank.lending.domain.application.Applicant;
import com.tlbank.lending.domain.application.Application;
import com.tlbank.lending.domain.application.ApplicationId;
import com.tlbank.lending.domain.application.ApplicationStatus;
import com.tlbank.lending.domain.application.CardProductId;
import com.tlbank.lending.domain.application.Email;
import com.tlbank.lending.domain.application.MobileNumber;
import com.tlbank.lending.domain.application.repository.ApplicationRepository;
import com.tlbank.lending.domain.otp.OtpPurpose;
import com.tlbank.lending.domain.otp.OtpRecord;
import com.tlbank.lending.domain.otp.OtpStatus;
import com.tlbank.lending.domain.otp.repository.OtpRepository;

@ExtendWith(MockitoExtension.class)
class OtpAppServiceTest {

    private static final ZoneId ZONE = ZoneId.systemDefault();
    private static final Clock FIXED_CLOCK = Clock.fixed(Instant.parse("2024-01-01T12:00:00Z"), ZONE);
    private static final String APPLICATION_ID = "APP-20240101120000-1234";

    @Mock
    private OtpRepository otpRepository;

    @Mock
    private ApplicationRepository applicationRepository;

    @Mock
    private NotificationService notificationService;

    @Mock
    private SystemParameterService systemParameterService;

    @InjectMocks
    private OtpAppService otpAppService;

    @BeforeEach
    void setUp() {
        otpAppService = new OtpAppService(
                otpRepository,
                applicationRepository,
                notificationService,
                systemParameterService,
                FIXED_CLOCK
        );
    }

    @Test
    void sendOtp_shouldCancelExistingPendingOtp() {
        OtpRecord existing = pendingOtp("654321");
        when(otpRepository.findLatestPendingByMobile("0912345678")).thenReturn(Optional.of(existing));
        when(systemParameterService.getIntValue("OTP", "expire_minutes", 5)).thenReturn(5);
        when(systemParameterService.getIntValue("OTP", "max_retry", 3)).thenReturn(3);
        when(otpRepository.save(any(OtpRecord.class))).thenAnswer(invocation -> invocation.getArgument(0));

        otpAppService.sendOtp(new SendOtpCommand(APPLICATION_ID, "0912345678", OtpPurpose.APPLICATION_VERIFICATION));

        ArgumentCaptor<OtpRecord> captor = ArgumentCaptor.forClass(OtpRecord.class);
        verify(otpRepository, times(2)).save(captor.capture());
        assertThat(captor.getAllValues().get(0).getStatus()).isEqualTo(OtpStatus.CANCELLED);
        assertThat(captor.getAllValues().get(1).getStatus()).isEqualTo(OtpStatus.PENDING);
    }

    @Test
    void sendOtp_shouldCreateNewOtpWithCorrectExpiry() {
        when(otpRepository.findLatestPendingByMobile("0912345678")).thenReturn(Optional.empty());
        when(systemParameterService.getIntValue("OTP", "expire_minutes", 5)).thenReturn(5);
        when(systemParameterService.getIntValue("OTP", "max_retry", 3)).thenReturn(3);
        when(otpRepository.save(any(OtpRecord.class))).thenAnswer(invocation -> invocation.getArgument(0));

        OtpResponse response = otpAppService.sendOtp(
                new SendOtpCommand(APPLICATION_ID, "0912345678", OtpPurpose.APPLICATION_VERIFICATION));

        assertThat(response.expiredAt()).isEqualTo(LocalDateTime.now(FIXED_CLOCK).plusMinutes(5));
        assertThat(response.maskedMobile()).isEqualTo("0912****78");
        assertThat(response.remainingRetry()).isEqualTo(3);

        ArgumentCaptor<OtpRecord> captor = ArgumentCaptor.forClass(OtpRecord.class);
        verify(otpRepository).save(captor.capture());
        assertThat(captor.getValue().getExpiredAt()).isEqualTo(LocalDateTime.now(FIXED_CLOCK).plusMinutes(5));
        assertThat(captor.getValue().getOtpCode()).matches("\\d{6}");
    }

    @Test
    void sendOtp_shouldCallNotificationService() {
        when(otpRepository.findLatestPendingByMobile("0912345678")).thenReturn(Optional.empty());
        when(systemParameterService.getIntValue("OTP", "expire_minutes", 5)).thenReturn(5);
        when(systemParameterService.getIntValue("OTP", "max_retry", 3)).thenReturn(3);
        when(otpRepository.save(any(OtpRecord.class))).thenAnswer(invocation -> invocation.getArgument(0));

        otpAppService.sendOtp(new SendOtpCommand(APPLICATION_ID, "0912345678", OtpPurpose.APPLICATION_VERIFICATION));

        verify(notificationService).sendOtpNotification(eq("0912345678"), any(String.class), eq(5));
    }

    @Test
    void verifyOtp_whenValid_shouldUpdateApplicationStatus() {
        OtpRecord otpRecord = pendingOtp("123456");
        Application application = sampleApplication();

        when(otpRepository.findLatestPendingByMobile("0912345678")).thenReturn(Optional.of(otpRecord));
        when(systemParameterService.getIntValue("OTP", "max_retry", 3)).thenReturn(3);
        when(otpRepository.save(any(OtpRecord.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(applicationRepository.findById(ApplicationId.of(APPLICATION_ID))).thenReturn(Optional.of(application));
        when(applicationRepository.save(any(Application.class))).thenAnswer(invocation -> invocation.getArgument(0));

        VerifyOtpResponse response = otpAppService.verifyOtp(
                new VerifyOtpCommand(APPLICATION_ID, "0912345678", "123456"));

        assertThat(response.verified()).isTrue();
        assertThat(response.applicationId()).isEqualTo(APPLICATION_ID);
        assertThat(otpRecord.getStatus()).isEqualTo(OtpStatus.VERIFIED);
        assertThat(application.getStatus()).isEqualTo(ApplicationStatus.OTP_VERIFIED);

        verify(applicationRepository).save(application);
    }

    @Test
    void verifyOtp_whenAlreadyOtpVerified_shouldReturnSuccess() {
        OtpRecord otpRecord = pendingOtp("123456");
        Application application = sampleApplication();
        application.verifyOtp("APPLICANT");

        when(otpRepository.findLatestPendingByMobile("0912345678")).thenReturn(Optional.of(otpRecord));
        when(systemParameterService.getIntValue("OTP", "max_retry", 3)).thenReturn(3);
        when(otpRepository.save(any(OtpRecord.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(applicationRepository.findById(ApplicationId.of(APPLICATION_ID))).thenReturn(Optional.of(application));

        VerifyOtpResponse response = otpAppService.verifyOtp(
                new VerifyOtpCommand(APPLICATION_ID, "0912345678", "123456"));

        assertThat(response.verified()).isTrue();
        verify(applicationRepository, never()).save(any(Application.class));
    }

    @Test
    void verifyOtp_whenNoOtpFound_shouldThrowException() {
        when(otpRepository.findLatestPendingByMobile("0912345678")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> otpAppService.verifyOtp(
                new VerifyOtpCommand(APPLICATION_ID, "0912345678", "123456")))
                .isInstanceOf(BusinessException.class)
                .extracting(ex -> ((BusinessException) ex).getErrorCode())
                .isEqualTo(ErrorCode.OTP_EXPIRED);

        verify(applicationRepository, never()).save(any(Application.class));
    }

    @Test
    void verifyOtp_whenCodeMismatch_shouldThrowOtpMismatch() {
        OtpRecord otpRecord = pendingOtp("123456");
        when(otpRepository.findLatestPendingByMobile("0912345678")).thenReturn(Optional.of(otpRecord));
        when(systemParameterService.getIntValue("OTP", "max_retry", 3)).thenReturn(3);

        assertThatThrownBy(() -> otpAppService.verifyOtp(
                new VerifyOtpCommand(APPLICATION_ID, "0912345678", "000000")))
                .isInstanceOf(BusinessException.class)
                .extracting(ex -> ((BusinessException) ex).getErrorCode())
                .isEqualTo(ErrorCode.OTP_MISMATCH);

        assertThat(otpRecord.getRetryCount()).isEqualTo(1);
    }

    @Test
    void verifyOtp_whenExpired_shouldThrowOtpExpired() {
        OtpRecord otpRecord = OtpRecord.builder()
                .mobile("0912345678")
                .otpCode("123456")
                .purpose(OtpPurpose.APPLICATION_VERIFICATION)
                .status(OtpStatus.PENDING)
                .retryCount(0)
                .expiredAt(LocalDateTime.now(FIXED_CLOCK).minusMinutes(1))
                .build();
        when(otpRepository.findLatestPendingByMobile("0912345678")).thenReturn(Optional.of(otpRecord));
        when(systemParameterService.getIntValue("OTP", "max_retry", 3)).thenReturn(3);

        assertThatThrownBy(() -> otpAppService.verifyOtp(
                new VerifyOtpCommand(APPLICATION_ID, "0912345678", "123456")))
                .isInstanceOf(BusinessException.class)
                .extracting(ex -> ((BusinessException) ex).getErrorCode())
                .isEqualTo(ErrorCode.OTP_EXPIRED);
    }

    @Test
    void verifyOtp_whenRetryExceeded_shouldThrowOtpRetryExceeded() {
        OtpRecord otpRecord = OtpRecord.builder()
                .mobile("0912345678")
                .otpCode("123456")
                .purpose(OtpPurpose.APPLICATION_VERIFICATION)
                .status(OtpStatus.PENDING)
                .retryCount(3)
                .expiredAt(LocalDateTime.now(FIXED_CLOCK).plusMinutes(5))
                .build();
        when(otpRepository.findLatestPendingByMobile("0912345678")).thenReturn(Optional.of(otpRecord));
        when(systemParameterService.getIntValue("OTP", "max_retry", 3)).thenReturn(3);

        assertThatThrownBy(() -> otpAppService.verifyOtp(
                new VerifyOtpCommand(APPLICATION_ID, "0912345678", "123456")))
                .isInstanceOf(BusinessException.class)
                .extracting(ex -> ((BusinessException) ex).getErrorCode())
                .isEqualTo(ErrorCode.OTP_RETRY_EXCEEDED);
    }

    private OtpRecord pendingOtp(String code) {
        return OtpRecord.builder()
                .otpId(1L)
                .mobile("0912345678")
                .otpCode(code)
                .purpose(OtpPurpose.APPLICATION_VERIFICATION)
                .status(OtpStatus.PENDING)
                .retryCount(0)
                .expiredAt(LocalDateTime.now(FIXED_CLOCK).plusMinutes(5))
                .build();
    }

    private Application sampleApplication() {
        return Application.builder()
                .applicationId(ApplicationId.of(APPLICATION_ID))
                .applicant(new Applicant(
                        "Test User",
                        "A123456789",
                        MobileNumber.of("0912345678"),
                        Email.of("test@example.com"),
                        LocalDate.of(1990, 1, 1),
                        new Address("台北市", "信義區", "信義路一段1號", "110")
                ))
                .cardProductId(CardProductId.of("TL-CLASSIC"))
                .status(ApplicationStatus.INIT)
                .workflowHistories(new ArrayList<>())
                .documentInfos(new ArrayList<>())
                .build();
    }
}
