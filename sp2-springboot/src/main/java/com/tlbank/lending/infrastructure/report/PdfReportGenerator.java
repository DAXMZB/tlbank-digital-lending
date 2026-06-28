package com.tlbank.lending.infrastructure.report;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.tlbank.lending.application.report.service.DailyStatisticsData;
import com.tlbank.lending.domain.application.ApplicationStatus;

/**
 * Generates PDF documents for daily application statistics reports.
 */
@Component
public class PdfReportGenerator {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public byte[] generateDailyStatistics(DailyStatisticsData data) {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(output);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            document.add(new Paragraph("TLBank Daily Application Statistics")
                    .setBold()
                    .setFontSize(16)
                    .setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("Report Date: " + DATE_FORMATTER.format(data.reportDate())));
            document.add(new Paragraph("Total Applications: " + data.totalApplications()));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Status Breakdown").setBold());
            Table statusTable = new Table(UnitValue.createPercentArray(new float[]{3, 2, 2}))
                    .useAllAvailableWidth();
            statusTable.addHeaderCell(new Cell().add(new Paragraph("Status").setBold()));
            statusTable.addHeaderCell(new Cell().add(new Paragraph("Count").setBold()));
            statusTable.addHeaderCell(new Cell().add(new Paragraph("%").setBold()));

            for (Map.Entry<ApplicationStatus, Long> entry : data.statusCounts().entrySet()) {
                long count = entry.getValue();
                statusTable.addCell(entry.getKey().name());
                statusTable.addCell(String.valueOf(count));
                statusTable.addCell(formatPercentage(count, data.totalApplications()));
            }
            document.add(statusTable);
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Product Breakdown").setBold());
            Table productTable = new Table(UnitValue.createPercentArray(new float[]{3, 2}))
                    .useAllAvailableWidth();
            productTable.addHeaderCell(new Cell().add(new Paragraph("Product").setBold()));
            productTable.addHeaderCell(new Cell().add(new Paragraph("Count").setBold()));

            if (data.productCounts().isEmpty()) {
                productTable.addCell("No applications");
                productTable.addCell("0");
            } else {
                for (Map.Entry<String, Long> entry : data.productCounts().entrySet()) {
                    productTable.addCell(entry.getKey());
                    productTable.addCell(String.valueOf(entry.getValue()));
                }
            }
            document.add(productTable);

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Generated at " + TIMESTAMP_FORMATTER.format(LocalDateTime.now()))
                    .setFontSize(10)
                    .setTextAlignment(TextAlignment.RIGHT));

            document.close();
            return output.toByteArray();
        } catch (Exception ex) {
            throw new IllegalStateException("Failed to generate PDF report", ex);
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
