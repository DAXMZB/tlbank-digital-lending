package com.tlbank.lending.domain.otp.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import com.tlbank.lending.domain.otp.OtpRecord;

/**
 * Domain repository port for {@link OtpRecord} persistence.
 */
public interface OtpRepository {

    OtpRecord save(OtpRecord otp);

    Optional<OtpRecord> findLatestPendingByMobile(String mobile);

    int markExpiredBefore(LocalDateTime cutoff);
}
