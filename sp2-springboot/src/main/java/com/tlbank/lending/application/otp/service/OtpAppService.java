package com.tlbank.lending.application.otp.service;

import java.security.SecureRandom;
import java.time.Clock;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlbank.lending.common.audit.AuditAction;
import com.tlbank.lending.common.audit.AuditContext;
import com.tlbank.lending.common.audit.Auditable;
import com.tlbank.lending.common.exception.BusinessException;
import com.tlbank.lending.common.exception.ErrorCode;
import com.tlbank.lending.common.exception.WorkflowException;
import com.tlbank.lending.application.notification.service.NotificationService;
import com.tlbank.lending.application.parameter.service.SystemParameterService;
import com.tlbank.lending.domain.application.Application;
import com.tlbank.lending.domain.application.ApplicationId;
import com.tlbank.lending.domain.application.ApplicationStatus;
import com.tlbank.lending.domain.application.MobileNumber;
import com.tlbank.lending.domain.application.repository.ApplicationRepository;
import com.tlbank.lending.domain.otp.OtpRecord;
import com.tlbank.lending.domain.otp.OtpStatus;
import com.tlbank.lending.domain.otp.repository.OtpRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Application service for OTP send and verify use cases.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class OtpAppService {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private final OtpRepository otpRepository;
    private final ApplicationRepository applicationRepository;
    private final NotificationService notificationService;
    private final SystemParameterService systemParameterService;
    private final Clock clock;

    @Transactional
    @Auditable(action = AuditAction.OTP_SEND)
    public OtpResponse sendOtp(SendOtpCommand command) {
        otpRepository.findLatestPendingByMobile(command.mobile()).ifPresent(existing -> {
            existing.cancel();
            otpRepository.save(existing);
        });

        int expireMinutes = systemParameterService.getIntValue("OTP", "expire_minutes", 5);
        int maxRetry = systemParameterService.getIntValue("OTP", "max_retry", 3);
        String otpCode = generateOtpCode();
        LocalDateTime expiredAt = LocalDateTime.now(clock).plusMinutes(expireMinutes);

        OtpRecord otpRecord = OtpRecord.builder()
                .mobile(command.mobile())
                .otpCode(otpCode)
                .purpose(command.purpose())
                .status(OtpStatus.PENDING)
                .retryCount(0)
                .expiredAt(expiredAt)
                .build();

        otpRepository.save(otpRecord);
        AuditContext.put("otpCode", otpCode);

        log.info("OTP generated for applicationId: {}", command.applicationId());
        notificationService.sendOtpNotification(command.mobile(), otpCode, expireMinutes);

        return new OtpResponse(
                MobileNumber.of(command.mobile()).masked(),
                expiredAt,
                maxRetry
        );
    }

    @Transactional
    @Auditable(action = AuditAction.OTP_VERIFY_SUCCESS)
    public VerifyOtpResponse verifyOtp(VerifyOtpCommand command) {
        OtpRecord otpRecord = otpRepository.findLatestPendingByMobile(command.mobile())
                .orElseThrow(() -> new BusinessException(ErrorCode.OTP_EXPIRED));

        int maxRetry = systemParameterService.getIntValue("OTP", "max_retry", 3);
        otpRecord.verify(command.otpCode(), maxRetry, clock);
        otpRepository.save(otpRecord);

        Application application = applicationRepository.findById(ApplicationId.of(command.applicationId()))
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.APPLICATION_NOT_FOUND,
                        "Application not found: " + command.applicationId()));

        if (application.getStatus() == ApplicationStatus.INIT) {
            application.verifyOtp("APPLICANT");
            applicationRepository.save(application);
        } else if (application.getStatus() != ApplicationStatus.OTP_VERIFIED) {
            throw new WorkflowException(
                    "Cannot verify OTP for application in status " + application.getStatus());
        }

        return new VerifyOtpResponse(true, command.applicationId());
    }

    private String generateOtpCode() {
        return String.format("%06d", SECURE_RANDOM.nextInt(1_000_000));
    }
}
