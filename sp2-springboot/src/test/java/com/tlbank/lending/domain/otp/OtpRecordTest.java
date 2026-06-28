package com.tlbank.lending.domain.otp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tlbank.lending.common.exception.BusinessException;
import com.tlbank.lending.common.exception.ErrorCode;

class OtpRecordTest {

    private static final ZoneId ZONE = ZoneId.systemDefault();
    private static final Clock FIXED_CLOCK = Clock.fixed(Instant.parse("2024-01-01T12:00:00Z"), ZONE);

    private OtpRecord otpRecord;

    @BeforeEach
    void setUp() {
        otpRecord = OtpRecord.builder()
                .mobile("0912345678")
                .otpCode("123456")
                .purpose(OtpPurpose.APPLICATION_VERIFICATION)
                .status(OtpStatus.PENDING)
                .retryCount(0)
                .expiredAt(LocalDateTime.now(FIXED_CLOCK).plusMinutes(5))
                .build();
    }

    @Test
    void verify_whenValidCode_notExpired_shouldSetStatusVerified() {
        VerifyResult result = otpRecord.verify("123456", 3, FIXED_CLOCK);

        assertThat(result).isEqualTo(VerifyResult.SUCCESS);
        assertThat(otpRecord.getStatus()).isEqualTo(OtpStatus.VERIFIED);
        assertThat(otpRecord.getVerifiedAt()).isEqualTo(LocalDateTime.now(FIXED_CLOCK));
    }

    @Test
    void verify_whenExpired_shouldThrowOtpExpiredException() {
        otpRecord = OtpRecord.builder()
                .mobile("0912345678")
                .otpCode("123456")
                .purpose(OtpPurpose.APPLICATION_VERIFICATION)
                .status(OtpStatus.PENDING)
                .retryCount(0)
                .expiredAt(LocalDateTime.now(FIXED_CLOCK).minusMinutes(1))
                .build();

        assertThatThrownBy(() -> otpRecord.verify("123456", 3, FIXED_CLOCK))
                .isInstanceOf(BusinessException.class)
                .extracting(ex -> ((BusinessException) ex).getErrorCode())
                .isEqualTo(ErrorCode.OTP_EXPIRED);

        assertThat(otpRecord.getStatus()).isEqualTo(OtpStatus.EXPIRED);
    }

    @Test
    void verify_whenRetryExceeded_shouldThrowOtpRetryExceededException() {
        otpRecord = OtpRecord.builder()
                .mobile("0912345678")
                .otpCode("123456")
                .purpose(OtpPurpose.APPLICATION_VERIFICATION)
                .status(OtpStatus.PENDING)
                .retryCount(3)
                .expiredAt(LocalDateTime.now(FIXED_CLOCK).plusMinutes(5))
                .build();

        assertThatThrownBy(() -> otpRecord.verify("123456", 3, FIXED_CLOCK))
                .isInstanceOf(BusinessException.class)
                .extracting(ex -> ((BusinessException) ex).getErrorCode())
                .isEqualTo(ErrorCode.OTP_RETRY_EXCEEDED);
    }

    @Test
    void verify_whenWrongCode_shouldIncrementRetryCount() {
        assertThatThrownBy(() -> otpRecord.verify("000000", 3, FIXED_CLOCK))
                .isInstanceOf(BusinessException.class)
                .extracting(ex -> ((BusinessException) ex).getErrorCode())
                .isEqualTo(ErrorCode.OTP_MISMATCH);

        assertThat(otpRecord.getRetryCount()).isEqualTo(1);
        assertThat(otpRecord.getStatus()).isEqualTo(OtpStatus.PENDING);
    }

    @Test
    void isExpired_whenExpiredAtIsBeforeNow_shouldReturnTrue() {
        otpRecord = OtpRecord.builder()
                .mobile("0912345678")
                .otpCode("123456")
                .purpose(OtpPurpose.APPLICATION_VERIFICATION)
                .status(OtpStatus.PENDING)
                .retryCount(0)
                .expiredAt(LocalDateTime.now(FIXED_CLOCK).minusSeconds(1))
                .build();

        assertThat(otpRecord.isExpired(FIXED_CLOCK)).isTrue();
    }
}
