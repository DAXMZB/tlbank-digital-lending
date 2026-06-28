package com.tlbank.lending.infrastructure.persistence.application;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tlbank.lending.domain.application.ApplicationStatus;

/**
 * Spring Data JPA repository for {@link ApplicationEntity}.
 */
public interface ApplicationJpaRepository extends JpaRepository<ApplicationEntity, Long> {

    Optional<ApplicationEntity> findByApplicationNo(String applicationNo);

    List<ApplicationEntity> findByStatus(ApplicationStatus status);

    @Query("""
            SELECT COUNT(a) FROM ApplicationEntity a
            WHERE a.createdAt >= :start AND a.createdAt < :end
            """)
    long countByCreatedAtBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query(value = """
            SELECT status, COUNT(*)
            FROM applications
            WHERE created_at >= :start AND created_at < :end
            GROUP BY status
            """, nativeQuery = true)
    List<Object[]> countByStatusAndCreatedAtBetween(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end);

    @Query(value = """
            SELECT product_id, COUNT(*)
            FROM applications
            WHERE created_at >= :start AND created_at < :end
            GROUP BY product_id
            """, nativeQuery = true)
    List<Object[]> countByCardProductIdAndCreatedAtBetween(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end);
}
