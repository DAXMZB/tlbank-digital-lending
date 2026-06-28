package com.tlbank.lending.domain.otp;

import java.time.Clock;
import java.time.LocalDateTime;

import com.tlbank.lending.common.exception.BusinessException;
import com.tlbank.lending.common.exception.ErrorCode;

import lombok.Builder;
import lombok.Getter;

/**
 * Aggregate root representing a one-time password verification record.
 */
@Getter
@Builder
public class OtpRecord {

    private Long otpId;
    private String mobile;
    private String otpCode;
    private OtpPurpose purpose;
    private OtpStatus status;
    private int retryCount;
    private LocalDateTime expiredAt;
    private LocalDateTime verifiedAt;
    private LocalDateTime createdAt;

    /**
     * Verifies the provided OTP code against this record.
     *
     * @throws BusinessException with {@link ErrorCode#OTP_EXPIRED} if expired
     * @throws BusinessException with {@link ErrorCode#OTP_RETRY_EXCEEDED} if retry limit reached
     * @throws BusinessException with {@link ErrorCode#OTP_MISMATCH} if code does not match
     */
    public VerifyResult verify(String inputCode, int maxRetry, Clock clock) {
        if (isExpired(clock)) {
            markExpired();
            throw new BusinessException(ErrorCode.OTP_EXPIRED);
        }
        if (retryCount >= maxRetry) {
            throw new BusinessException(ErrorCode.OTP_RETRY_EXCEEDED);
        }
        if (!otpCode.equals(inputCode)) {
            retryCount++;
            throw new BusinessException(ErrorCode.OTP_MISMATCH);
        }
        this.status = OtpStatus.VERIFIED;
        this.verifiedAt = LocalDateTime.now(clock);
        return VerifyResult.SUCCESS;
    }

    /**
     * Returns whether this OTP has passed its expiration time.
     */
    public boolean isExpired(Clock clock) {
        return expiredAt.isBefore(LocalDateTime.now(clock));
    }

    /**
     * Marks this OTP record as expired.
     */
    public void markExpired() {
        this.status = OtpStatus.EXPIRED;
    }

    /**
     * Cancels this OTP record.
     */
    public void cancel() {
        this.status = OtpStatus.CANCELLED;
    }
}
