package com.tlbank.lending.presentation.api.v1;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tlbank.lending.application.dto.request.GenerateReportRequest;
import com.tlbank.lending.application.report.service.ReportAppService;
import com.tlbank.lending.common.config.StandardApiResponses;
import com.tlbank.lending.application.report.service.ReportFormat;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * REST API for generating and downloading administrative reports.
 */
@RestController
@RequestMapping("/api/v1/reports")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Reports", description = "Administrative report generation APIs")
@StandardApiResponses
@RequiredArgsConstructor
public class ReportApiController {

    private final ReportAppService reportAppService;

    @PostMapping("/daily-statistics")
    @Operation(summary = "Generate daily statistics", description = "Generates and downloads a daily application statistics report.")
    public ResponseEntity<byte[]> generateDailyStatistics(@Valid @RequestBody GenerateReportRequest request) {
        byte[] content = reportAppService.generateDailyStatisticsReport(request);
        String fileName = reportAppService.getFileName(request.reportDate(), request.format());

        MediaType mediaType = request.format() == ReportFormat.EXCEL
                ? MediaType.APPLICATION_OCTET_STREAM
                : MediaType.APPLICATION_PDF;

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .contentType(mediaType)
                .body(content);
    }
}
