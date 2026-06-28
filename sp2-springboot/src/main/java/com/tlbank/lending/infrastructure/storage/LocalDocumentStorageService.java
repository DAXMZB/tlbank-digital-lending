package com.tlbank.lending.infrastructure.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import org.springframework.web.multipart.MultipartFile;

import com.tlbank.lending.application.parameter.service.SystemParameterService;
import com.tlbank.lending.common.exception.BusinessException;
import com.tlbank.lending.common.exception.ErrorCode;
import com.tlbank.lending.domain.application.DocumentType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Local filesystem implementation of {@link DocumentStorageService}.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class LocalDocumentStorageService implements DocumentStorageService {

    private static final Set<String> ALLOWED_EXTENSIONS = Set.of("jpg", "jpeg", "png", "pdf");
    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    private final SystemParameterService systemParameterService;
    private final Clock clock;

    @Value("${tlbank.upload.base-path}")
    private String basePath;

    private Path resolvedBasePath;

    @PostConstruct
    void initUploadDirectory() {
        resolvedBasePath = Paths.get(basePath).toAbsolutePath().normalize();
        try {
            Files.createDirectories(resolvedBasePath);
        } catch (IOException ex) {
            throw new IllegalStateException(
                    "Cannot create upload base directory: " + resolvedBasePath, ex);
        }
        log.info("Document upload base path: {}", resolvedBasePath);
    }

    @Override
    public void validate(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new BusinessException(ErrorCode.DOCUMENT_UPLOAD_FAILED, "File must not be empty");
        }

        String extension = extractExtension(file.getOriginalFilename());
        if (!ALLOWED_EXTENSIONS.contains(extension)) {
            throw new BusinessException(ErrorCode.DOCUMENT_UPLOAD_FAILED,
                    "Unsupported file extension: " + extension);
        }

        int maxSizeMb = systemParameterService.getIntValue("UPLOAD", "max.size.mb", 10);
        long maxBytes = maxSizeMb * 1024L * 1024L;
        if (file.getSize() > maxBytes) {
            throw new BusinessException(ErrorCode.DOCUMENT_UPLOAD_FAILED,
                    "File size exceeds maximum allowed size of " + maxSizeMb + " MB");
        }
    }

    @Override
    public String store(String applicationId, DocumentType documentType, MultipartFile file) {
        validate(file);

        String extension = extractExtension(file.getOriginalFilename());
        String timestamp = LocalDateTime.now(clock).format(TIMESTAMP_FORMAT);
        String fileName = documentType.name() + "_" + timestamp + "." + extension;
        Path targetDir = resolvedBasePath.resolve(applicationId);

        try {
            Files.createDirectories(targetDir);
            Path targetFile = targetDir.resolve(fileName);
            Files.copy(file.getInputStream(), targetFile, StandardCopyOption.REPLACE_EXISTING);
            String relativePath = applicationId + "/" + fileName;
            log.info("Document stored for applicationId: {}", applicationId);
            return relativePath;
        } catch (IOException ex) {
            throw new BusinessException(ErrorCode.DOCUMENT_UPLOAD_FAILED,
                    "Failed to store document: " + ex.getMessage());
        }
    }

    private String extractExtension(String originalFilename) {
        if (originalFilename == null || !originalFilename.contains(".")) {
            throw new BusinessException(ErrorCode.DOCUMENT_UPLOAD_FAILED, "File must have an extension");
        }
        return originalFilename.substring(originalFilename.lastIndexOf('.') + 1).toLowerCase();
    }
}
