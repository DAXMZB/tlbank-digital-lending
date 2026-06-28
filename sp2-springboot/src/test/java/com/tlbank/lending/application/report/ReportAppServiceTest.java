package com.tlbank.lending.application.report;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tlbank.lending.application.dto.request.GenerateReportRequest;
import com.tlbank.lending.application.report.service.DailyStatisticsData;
import com.tlbank.lending.application.report.service.ReportAppService;
import com.tlbank.lending.application.report.service.ReportDataService;
import com.tlbank.lending.application.report.service.ReportFormat;
import com.tlbank.lending.domain.application.ApplicationStatus;
import com.tlbank.lending.infrastructure.report.ExcelReportGenerator;
import com.tlbank.lending.infrastructure.report.PdfReportGenerator;

@ExtendWith(MockitoExtension.class)
class ReportAppServiceTest {

    private static final LocalDate REPORT_DATE = LocalDate.of(2024, 6, 7);
    private static final byte[] EXCEL_BYTES = new byte[] {1, 2, 3};
    private static final byte[] PDF_BYTES = new byte[] {4, 5, 6};

    @Mock
    private ReportDataService reportDataService;

    @Mock
    private ExcelReportGenerator excelReportGenerator;

    @Mock
    private PdfReportGenerator pdfReportGenerator;

    @InjectMocks
    private ReportAppService reportAppService;

    @BeforeEach
    void setUp() {
        reportAppService = new ReportAppService(reportDataService, excelReportGenerator, pdfReportGenerator);
    }

    @Test
    void generateReport_whenExcel_shouldCallExcelGenerator() {
        DailyStatisticsData data = sampleData();
        when(reportDataService.buildDailyStatistics(REPORT_DATE)).thenReturn(data);
        when(excelReportGenerator.generateDailyStatistics(data)).thenReturn(EXCEL_BYTES);

        byte[] result = reportAppService.generateDailyStatisticsReport(
                new GenerateReportRequest(REPORT_DATE, ReportFormat.EXCEL));

        assertThat(result).isEqualTo(EXCEL_BYTES);
        verify(excelReportGenerator).generateDailyStatistics(data);
    }

    @Test
    void generateReport_whenPdf_shouldCallPdfGenerator() {
        DailyStatisticsData data = sampleData();
        when(reportDataService.buildDailyStatistics(REPORT_DATE)).thenReturn(data);
        when(pdfReportGenerator.generateDailyStatistics(data)).thenReturn(PDF_BYTES);

        byte[] result = reportAppService.generateDailyStatisticsReport(
                new GenerateReportRequest(REPORT_DATE, ReportFormat.PDF));

        assertThat(result).isEqualTo(PDF_BYTES);
        verify(pdfReportGenerator).generateDailyStatistics(data);
    }

    private DailyStatisticsData sampleData() {
        Map<ApplicationStatus, Long> statusCounts = Arrays.stream(ApplicationStatus.values())
                .collect(Collectors.toMap(status -> status, status -> 0L, (left, right) -> left, LinkedHashMap::new));
        return new DailyStatisticsData(REPORT_DATE, 0L, statusCounts, Map.of());
    }
}
