package com.tlbank.lending.infrastructure.persistence.otp;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.tlbank.lending.domain.otp.OtpRecord;
import com.tlbank.lending.domain.otp.OtpStatus;
import com.tlbank.lending.domain.otp.repository.OtpRepository;

import lombok.RequiredArgsConstructor;

/**
 * JPA-backed implementation of the {@link OtpRepository} domain port.
 */
@Repository
@RequiredArgsConstructor
public class OtpRepositoryImpl implements OtpRepository {

    private final OtpJpaRepository otpJpaRepository;

    @Override
    public OtpRecord save(OtpRecord otp) {
        OtpRecordEntity entity = otp.getOtpId() == null
                ? toEntity(otp)
                : otpJpaRepository.findById(otp.getOtpId())
                        .map(existing -> {
                            applyToEntity(existing, otp);
                            return existing;
                        })
                        .orElseGet(() -> toEntity(otp));

        return toDomain(otpJpaRepository.save(entity));
    }

    @Override
    public Optional<OtpRecord> findLatestPendingByMobile(String mobile) {
        return otpJpaRepository.findTopByMobileAndStatusOrderByCreatedAtDesc(mobile, OtpStatus.PENDING)
                .map(this::toDomain);
    }

    @Override
    public int markExpiredBefore(LocalDateTime cutoff) {
        return otpJpaRepository.markExpiredBefore(cutoff, OtpStatus.PENDING, OtpStatus.EXPIRED);
    }

    private OtpRecord toDomain(OtpRecordEntity entity) {
        return OtpRecord.builder()
                .otpId(entity.getId())
                .mobile(entity.getMobile())
                .otpCode(entity.getOtpCode())
                .purpose(entity.getPurpose())
                .status(entity.getStatus())
                .retryCount(entity.getRetryCount())
                .expiredAt(entity.getExpiredAt())
                .verifiedAt(entity.getVerifiedAt())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    private OtpRecordEntity toEntity(OtpRecord otp) {
        return OtpRecordEntity.builder()
                .id(otp.getOtpId())
                .mobile(otp.getMobile())
                .otpCode(otp.getOtpCode())
                .purpose(otp.getPurpose())
                .status(otp.getStatus())
                .retryCount(otp.getRetryCount())
                .expiredAt(otp.getExpiredAt())
                .verifiedAt(otp.getVerifiedAt())
                .build();
    }

    private void applyToEntity(OtpRecordEntity entity, OtpRecord otp) {
        entity.setMobile(otp.getMobile());
        entity.setOtpCode(otp.getOtpCode());
        entity.setPurpose(otp.getPurpose());
        entity.setStatus(otp.getStatus());
        entity.setRetryCount(otp.getRetryCount());
        entity.setExpiredAt(otp.getExpiredAt());
        entity.setVerifiedAt(otp.getVerifiedAt());
    }
}
