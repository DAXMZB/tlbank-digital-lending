package com.tlbank.lending.infrastructure.storage;

import org.springframework.web.multipart.MultipartFile;

import com.tlbank.lending.domain.application.DocumentType;

/**
 * Port for storing and validating application document uploads.
 */
public interface DocumentStorageService {

    /**
     * Stores the uploaded file and returns a relative storage path.
     */
    String store(String applicationId, DocumentType documentType, MultipartFile file);

    /**
     * Validates the uploaded file against business rules.
     */
    void validate(MultipartFile file);
}
