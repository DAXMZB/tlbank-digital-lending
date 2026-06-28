package com.tlbank.lending.infrastructure.cache;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemoryCacheStoreTest {

    private static final Clock FIXED_CLOCK = Clock.fixed(
            Instant.parse("2024-01-01T12:00:00Z"), ZoneId.systemDefault());

    private InMemoryCacheStore cacheStore;

    @BeforeEach
    void setUp() {
        cacheStore = new InMemoryCacheStore(FIXED_CLOCK);
    }

    @Test
    void put_thenGet_shouldReturnValue() {
        cacheStore.put("key1", "value1", 3600);

        assertThat(cacheStore.<String>get("key1")).contains("value1");
    }

    @Test
    void get_whenExpired_shouldReturnEmpty() {
        cacheStore.put("expired", "value", -1);

        assertThat(cacheStore.<String>get("expired")).isEmpty();
        assertThat(cacheStore.keys()).doesNotContain("expired");
    }

    @Test
    void evict_shouldRemoveEntry() {
        cacheStore.put("key1", "value1", 3600);

        cacheStore.evict("key1");

        assertThat(cacheStore.<String>get("key1")).isEmpty();
    }

    @Test
    void concurrentAccess_shouldBeThreadSafe() {
        IntStream.range(0, 200).parallel().forEach(i ->
                cacheStore.put("parallel:" + i, "value-" + i, 3600));

        long hitCount = IntStream.range(0, 200).parallel()
                .filter(i -> cacheStore.<String>get("parallel:" + i).isPresent())
                .count();

        assertThat(hitCount).isEqualTo(200);
        assertThat(cacheStore.keys()).hasSize(200);
    }
}
