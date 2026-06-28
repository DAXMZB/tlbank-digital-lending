package com.tlbank.lending.domain.application;

import java.time.LocalDateTime;

/**
 * Value object representing metadata for an uploaded application document.
 */
public record DocumentInfo(
        DocumentType documentType,
        String fileName,
        String storagePath,
        long fileSize,
        LocalDateTime uploadedAt
) {
}
