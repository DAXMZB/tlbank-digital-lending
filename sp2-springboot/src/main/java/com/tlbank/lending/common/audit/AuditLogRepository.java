package com.tlbank.lending.common.audit;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Spring Data JPA repository for {@link AuditLog} persistence.
 */
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

    Page<AuditLog> findByUsernameContainingAndCreatedAtBetween(
            String username,
            LocalDateTime from,
            LocalDateTime to,
            Pageable pageable);

    Page<AuditLog> findByActionAndCreatedAtBetween(
            AuditAction action,
            LocalDateTime from,
            LocalDateTime to,
            Pageable pageable);

    @Query("""
            SELECT a FROM AuditLog a
            WHERE a.createdAt >= :from AND a.createdAt < :to
              AND (:username IS NULL OR :username = '' OR LOWER(a.username) LIKE LOWER(CONCAT('%', :username, '%')))
              AND (:action IS NULL OR a.action = :action)
            ORDER BY a.createdAt DESC
            """)
    Page<AuditLog> search(
            @Param("username") String username,
            @Param("action") AuditAction action,
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to,
            Pageable pageable);

    Page<AuditLog> findByActionIn(Collection<AuditAction> actions, Pageable pageable);
}
