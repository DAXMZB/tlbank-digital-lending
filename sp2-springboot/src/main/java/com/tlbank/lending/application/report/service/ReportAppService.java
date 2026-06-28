package com.tlbank.lending.application.report.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.tlbank.lending.application.dto.request.GenerateReportRequest;
import com.tlbank.lending.common.audit.AuditAction;
import com.tlbank.lending.common.audit.Auditable;
import com.tlbank.lending.infrastructure.report.ExcelReportGenerator;
import com.tlbank.lending.infrastructure.report.PdfReportGenerator;

import lombok.RequiredArgsConstructor;

/**
 * Application service for generating downloadable daily statistics reports.
 */
@Service
@RequiredArgsConstructor
public class ReportAppService {

    private static final DateTimeFormatter FILE_DATE_FORMATTER = DateTimeFormatter.BASIC_ISO_DATE;

    private final ReportDataService reportDataService;
    private final ExcelReportGenerator excelReportGenerator;
    private final PdfReportGenerator pdfReportGenerator;

    @Auditable(action = AuditAction.REPORT_EXPORT)
    public byte[] generateDailyStatisticsReport(GenerateReportRequest request) {
        DailyStatisticsData data = reportDataService.buildDailyStatistics(request.reportDate());

        if (request.format() == ReportFormat.EXCEL) {
            return excelReportGenerator.generateDailyStatistics(data);
        }
        return pdfReportGenerator.generateDailyStatistics(data);
    }

    public String getFileName(LocalDate date, ReportFormat format) {
        String extension = format == ReportFormat.EXCEL ? "xlsx" : "pdf";
        return "daily-statistics-" + FILE_DATE_FORMATTER.format(date) + "." + extension;
    }
}
