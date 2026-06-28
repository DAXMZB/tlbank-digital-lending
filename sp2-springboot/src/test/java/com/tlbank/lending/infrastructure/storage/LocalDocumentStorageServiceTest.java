package com.tlbank.lending.infrastructure.storage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;

import com.tlbank.lending.application.parameter.service.SystemParameterService;
import com.tlbank.lending.domain.application.DocumentType;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class LocalDocumentStorageServiceTest {

    @Mock
    private SystemParameterService systemParameterService;

    @TempDir
    Path tempDir;

    private LocalDocumentStorageService storageService;

    @BeforeEach
    void setUp() {
        when(systemParameterService.getIntValue("UPLOAD", "max.size.mb", 10)).thenReturn(10);

        storageService = new LocalDocumentStorageService(
                systemParameterService,
                Clock.fixed(Instant.parse("2026-06-14T12:00:00Z"), ZoneId.of("UTC")));

        ReflectionTestUtils.setField(storageService, "basePath", tempDir.toString());
        storageService.initUploadDirectory();
    }

    @Test
    void store_shouldWriteFileUnderResolvedBasePath() throws IOException {
        MockMultipartFile file = new MockMultipartFile(
                "file", "id-card.jpg", "image/jpeg", "image-content".getBytes());

        String relativePath = storageService.store("APP-TEST-001", DocumentType.NATIONAL_ID, file);

        assertThat(relativePath).startsWith("APP-TEST-001/NATIONAL_ID_");
        Path storedFile = tempDir.resolve(relativePath);
        assertThat(Files.exists(storedFile)).isTrue();
        assertThat(Files.readString(storedFile)).isEqualTo("image-content");
    }
}
