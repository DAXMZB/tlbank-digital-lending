package com.tlbank.lending.application.dto.response;

import java.time.LocalDateTime;

import com.tlbank.lending.domain.application.DocumentType;

/**
 * Response DTO after a successful document upload.
 */
public record DocumentUploadResponse(
        DocumentType documentType,
        String fileName,
        LocalDateTime uploadedAt
) {
}
