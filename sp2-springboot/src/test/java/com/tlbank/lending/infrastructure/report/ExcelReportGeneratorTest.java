package com.tlbank.lending.infrastructure.report;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tlbank.lending.application.report.service.DailyStatisticsData;
import com.tlbank.lending.domain.application.ApplicationStatus;

class ExcelReportGeneratorTest {

    private ExcelReportGenerator excelReportGenerator;

    @BeforeEach
    void setUp() {
        excelReportGenerator = new ExcelReportGenerator();
    }

    @Test
    void generateDailyStatistics_shouldContainCorrectSheetNames() throws Exception {
        byte[] content = excelReportGenerator.generateDailyStatistics(sampleData());

        try (Workbook workbook = new XSSFWorkbook(new ByteArrayInputStream(content))) {
            assertThat(workbook.getNumberOfSheets()).isEqualTo(2);
            assertThat(workbook.getSheetAt(0).getSheetName()).isEqualTo("Summary");
            assertThat(workbook.getSheetAt(1).getSheetName()).isEqualTo("By Product");
        }
    }

    @Test
    void generateDailyStatistics_shouldContainAllStatuses() throws Exception {
        byte[] content = excelReportGenerator.generateDailyStatistics(emptyData());

        try (Workbook workbook = new XSSFWorkbook(new ByteArrayInputStream(content))) {
            Sheet summary = workbook.getSheetAt(0);
            String sheetText = collectSheetText(summary);

            Arrays.stream(ApplicationStatus.values())
                    .forEach(status -> assertThat(sheetText).contains(status.name()));
        }
    }

    private DailyStatisticsData sampleData() {
        Map<ApplicationStatus, Long> statusCounts = Arrays.stream(ApplicationStatus.values())
                .collect(Collectors.toMap(status -> status, status -> status == ApplicationStatus.SUBMITTED ? 2L : 0L,
                        (left, right) -> left, LinkedHashMap::new));

        Map<String, Long> productCounts = Map.of("TL Classic Card", 2L);

        return new DailyStatisticsData(LocalDate.of(2024, 6, 7), 2L, statusCounts, productCounts);
    }

    private DailyStatisticsData emptyData() {
        Map<ApplicationStatus, Long> statusCounts = Arrays.stream(ApplicationStatus.values())
                .collect(Collectors.toMap(status -> status, status -> 0L, (left, right) -> left, LinkedHashMap::new));

        return new DailyStatisticsData(LocalDate.of(2024, 6, 7), 0L, statusCounts, Map.of());
    }

    private String collectSheetText(Sheet sheet) {
        StringBuilder builder = new StringBuilder();
        sheet.forEach(row -> row.forEach(cell -> builder.append(cell.toString()).append(' ')));
        return builder.toString();
    }
}
