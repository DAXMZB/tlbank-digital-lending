package com.tlbank.lending.infrastructure.persistence.otp;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tlbank.lending.domain.otp.OtpStatus;

/**
 * Spring Data JPA repository for {@link OtpRecordEntity}.
 */
public interface OtpJpaRepository extends JpaRepository<OtpRecordEntity, Long> {

    Optional<OtpRecordEntity> findTopByMobileAndStatusOrderByCreatedAtDesc(String mobile, OtpStatus status);

    @Modifying
    @Query("UPDATE OtpRecordEntity o SET o.status = :expiredStatus "
            + "WHERE o.status = :pendingStatus AND o.expiredAt < :cutoff")
    int markExpiredBefore(
            @Param("cutoff") LocalDateTime cutoff,
            @Param("pendingStatus") OtpStatus pendingStatus,
            @Param("expiredStatus") OtpStatus expiredStatus);
}
