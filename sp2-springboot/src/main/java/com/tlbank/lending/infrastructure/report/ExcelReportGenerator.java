package com.tlbank.lending.infrastructure.report;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.tlbank.lending.application.report.service.DailyStatisticsData;
import com.tlbank.lending.domain.application.ApplicationStatus;

import lombok.RequiredArgsConstructor;

/**
 * Generates Excel workbooks for daily application statistics reports.
 */
@Component
@RequiredArgsConstructor
public class ExcelReportGenerator {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    public byte[] generateDailyStatistics(DailyStatisticsData data) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            writeSummarySheet(workbook, data);
            writeProductSheet(workbook, data);
            workbook.write(output);
            return output.toByteArray();
        } catch (IOException ex) {
            throw new IllegalStateException("Failed to generate Excel report", ex);
        }
    }

    private void writeSummarySheet(Workbook workbook, DailyStatisticsData data) {
        Sheet sheet = workbook.createSheet("Summary");
        CellStyle headerStyle = createBoldStyle(workbook);

        Row titleRow = sheet.createRow(0);
        titleRow.createCell(0).setCellValue("TLBank Daily Application Statistics");

        Row dateRow = sheet.createRow(1);
        dateRow.createCell(0).setCellValue("Report Date: " + DATE_FORMATTER.format(data.reportDate()));

        Row totalRow = sheet.createRow(2);
        totalRow.createCell(0).setCellValue("Total Applications: " + data.totalApplications());

        Row headerRow = sheet.createRow(3);
        createHeaderCell(headerRow, 0, "Status", headerStyle);
        createHeaderCell(headerRow, 1, "Count", headerStyle);
        createHeaderCell(headerRow, 2, "Percentage", headerStyle);

        int rowIndex = 4;
        for (Map.Entry<ApplicationStatus, Long> entry : data.statusCounts().entrySet()) {
            Row row = sheet.createRow(rowIndex++);
            long count = entry.getValue();
            row.createCell(0).setCellValue(entry.getKey().name());
            row.createCell(1).setCellValue(count);
            row.createCell(2).setCellValue(formatPercentage(count, data.totalApplications()));
        }

        autoSizeColumns(sheet, 3);
    }

    private void writeProductSheet(Workbook workbook, DailyStatisticsData data) {
        Sheet sheet = workbook.createSheet("By Product");
        CellStyle headerStyle = createBoldStyle(workbook);

        Row headerRow = sheet.createRow(0);
        createHeaderCell(headerRow, 0, "Product", headerStyle);
        createHeaderCell(headerRow, 1, "Count", headerStyle);

        int rowIndex = 1;
        for (Map.Entry<String, Long> entry : data.productCounts().entrySet()) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(entry.getKey());
            row.createCell(1).setCellValue(entry.getValue());
        }

        if (data.productCounts().isEmpty()) {
            Row row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue("No applications");
            row.createCell(1).setCellValue(0);
        }

        autoSizeColumns(sheet, 2);
    }

    private CellStyle createBoldStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        return style;
    }

    private void createHeaderCell(Row row, int column, String value, CellStyle style) {
        Cell cell = row.createCell(column);
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }

    private void autoSizeColumns(Sheet sheet, int columnCount) {
        for (int column = 0; column < columnCount; column++) {
            sheet.autoSizeColumn(column);
        }
    }

    private String formatPercentage(long count, long total) {
        if (total == 0) {
            return "0.00%";
        }
        double percentage = (count * 100.0) / total;
        return String.format("%.2f%%", percentage);
    }
}
