package com.tlbank.lending.application.application.service;

import java.time.LocalDateTime;

import com.tlbank.lending.domain.application.DocumentType;

/**
 * Summary of an uploaded application document.
 */
public record DocumentInfoResponse(
        DocumentType documentType,
        String fileName,
        LocalDateTime uploadedAt
) {
}
