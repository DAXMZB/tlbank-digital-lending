package com.tlbank.lending.application.idempotency;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tlbank.lending.application.application.service.ApplicationSummaryResponse;
import com.tlbank.lending.common.exception.BusinessException;
import com.tlbank.lending.common.exception.ErrorCode;
import com.tlbank.lending.common.response.ApiResponse;
import com.tlbank.lending.domain.application.ApplicationStatus;
import com.tlbank.lending.infrastructure.idempotency.IdempotencyEntry;
import com.tlbank.lending.infrastructure.idempotency.IdempotencyStore;

@ExtendWith(MockitoExtension.class)
class IdempotencyServiceTest {

    @Mock
    private IdempotencyStore idempotencyStore;

    private IdempotencyService idempotencyService;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        idempotencyService = new IdempotencyService(idempotencyStore, objectMapper);
        ReflectionTestUtils.setField(idempotencyService, "ttlHours", 24L);
        ReflectionTestUtils.setField(idempotencyService, "keyPrefix", "test:");
    }

    @Test
    void execute_withoutIdempotencyKey_shouldCallActionDirectly() {
        SampleRequest request = new SampleRequest("TL-CLASSIC");
        ResponseEntity<ApiResponse<ApplicationSummaryResponse>> expected =
                ResponseEntity.status(HttpStatus.CREATED)
                        .body(ApiResponse.success(new ApplicationSummaryResponse(
                                "APP-1", ApplicationStatus.INIT, LocalDateTime.now())));

        ResponseEntity<ApiResponse<ApplicationSummaryResponse>> result =
                idempotencyService.execute(null, request, () -> expected);

        assertThat(result).isEqualTo(expected);
        verify(idempotencyStore, never()).find(any());
    }

    @Test
    void execute_withCachedEntry_shouldReturnCachedResponse() throws Exception {
        SampleRequest request = new SampleRequest("TL-CLASSIC");
        String requestHash = invokeHash(request);

        ApplicationSummaryResponse summary =
                new ApplicationSummaryResponse("APP-CACHED", ApplicationStatus.INIT, LocalDateTime.now());
        String responseJson = objectMapper.writeValueAsString(ApiResponse.success(summary));

        when(idempotencyStore.find("test:dup-key")).thenReturn(Optional.of(
                new IdempotencyEntry(requestHash, 201, responseJson)));

        ResponseEntity<ApiResponse<ApplicationSummaryResponse>> result =
                idempotencyService.execute("dup-key", request, () -> {
                    throw new AssertionError("action should not run");
                });

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        ApplicationSummaryResponse cached = objectMapper.convertValue(
                result.getBody().data(), ApplicationSummaryResponse.class);
        assertThat(cached.applicationId()).isEqualTo("APP-CACHED");
    }

    @Test
    void execute_withSameKeyDifferentBody_shouldThrowConflict() {
        when(idempotencyStore.find("test:dup-key")).thenReturn(Optional.of(
                new IdempotencyEntry("other-hash", 201, "{}")));

        assertThatThrownBy(() -> idempotencyService.execute(
                "dup-key",
                new SampleRequest("TL-CLASSIC"),
                () -> ResponseEntity.ok(ApiResponse.success(null))))
                .isInstanceOf(BusinessException.class)
                .satisfies(ex -> assertThat(((BusinessException) ex).getErrorCode())
                        .isEqualTo(ErrorCode.IDEMPOTENCY_KEY_CONFLICT));
    }

    @Test
    void execute_withNewKey_shouldStoreResponse() {
        SampleRequest request = new SampleRequest("TL-CLASSIC");

        when(idempotencyStore.find("test:new-key")).thenReturn(Optional.empty());
        when(idempotencyStore.tryAcquireLock("test:new-key:lock", 30)).thenReturn(true);

        ApplicationSummaryResponse summary =
                new ApplicationSummaryResponse("APP-NEW", ApplicationStatus.INIT, LocalDateTime.now());

        idempotencyService.execute("new-key", request, () ->
                ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(summary)));

        verify(idempotencyStore).save(eq("test:new-key"), any(IdempotencyEntry.class), eq(86400L));
        verify(idempotencyStore).releaseLock("test:new-key:lock");
    }

    private String invokeHash(Object request) throws Exception {
        var method = IdempotencyService.class.getDeclaredMethod("hashRequest", Object.class);
        method.setAccessible(true);
        return (String) method.invoke(idempotencyService, request);
    }

    private record SampleRequest(String cardProductId) {
    }
}
