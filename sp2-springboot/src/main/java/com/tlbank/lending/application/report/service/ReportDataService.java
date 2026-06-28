package com.tlbank.lending.application.report.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tlbank.lending.domain.application.ApplicationStatus;
import com.tlbank.lending.infrastructure.persistence.application.ApplicationJpaRepository;
import com.tlbank.lending.infrastructure.persistence.product.CardProductEntity;
import com.tlbank.lending.infrastructure.persistence.product.CardProductJpaRepository;

import lombok.RequiredArgsConstructor;

/**
 * Builds aggregated report data from application persistence queries.
 */
@Service
@RequiredArgsConstructor
public class ReportDataService {

    private final ApplicationJpaRepository applicationJpaRepository;
    private final CardProductJpaRepository cardProductJpaRepository;

    public DailyStatisticsData buildDailyStatistics(LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays(1).atStartOfDay();

        long total = applicationJpaRepository.countByCreatedAtBetween(start, end);
        Map<ApplicationStatus, Long> statusCounts = buildStatusCounts(start, end);
        Map<String, Long> productCounts = buildProductCounts(start, end);

        return new DailyStatisticsData(date, total, statusCounts, productCounts);
    }

    private Map<ApplicationStatus, Long> buildStatusCounts(LocalDateTime start, LocalDateTime end) {
        Map<ApplicationStatus, Long> counts = new LinkedHashMap<>();
        Arrays.stream(ApplicationStatus.values()).forEach(status -> counts.put(status, 0L));

        List<Object[]> rows = applicationJpaRepository.countByStatusAndCreatedAtBetween(start, end);
        for (Object[] row : rows) {
            ApplicationStatus status = ApplicationStatus.valueOf(String.valueOf(row[0]));
            counts.put(status, toLong(row[1]));
        }
        return counts;
    }

    private Map<String, Long> buildProductCounts(LocalDateTime start, LocalDateTime end) {
        Map<String, Long> counts = new LinkedHashMap<>();
        List<Object[]> rows = applicationJpaRepository.countByCardProductIdAndCreatedAtBetween(start, end);

        for (Object[] row : rows) {
            Long productId = toLong(row[0]);
            String productName = cardProductJpaRepository.findById(productId)
                    .map(CardProductEntity::getProductName)
                    .orElse("Product-" + productId);
            counts.put(productName, toLong(row[1]));
        }
        return counts;
    }

    private long toLong(Object value) {
        if (value instanceof Number number) {
            return number.longValue();
        }
        return Long.parseLong(String.valueOf(value));
    }
}
