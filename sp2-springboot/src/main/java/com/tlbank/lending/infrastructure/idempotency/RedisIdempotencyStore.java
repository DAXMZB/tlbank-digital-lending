package com.tlbank.lending.infrastructure.idempotency;

import java.time.Duration;
import java.util.Optional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/**
 * Redis-backed idempotency store (production / dev with Redis).
 * 使用 Redis 儲存冪等紀錄，TTL 到期自動清除。
 */
@Slf4j
@Component
@ConditionalOnProperty(name = "tlbank.idempotency.store", havingValue = "redis")
@RequiredArgsConstructor
public class RedisIdempotencyStore implements IdempotencyStore{
    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public Optional<IdempotencyEntry> find(String key) {
        String json = redisTemplate.opsForValue().get(key);
        if (json == null) {
            return Optional.empty();
        }
        try {
            return Optional.of(objectMapper.readValue(json, IdempotencyEntry.class));
        } catch (Exception ex) {
            log.warn("Failed to deserialize idempotency entry for key: {}", key, ex);
            return Optional.empty();
        }
    }

    @Override
    public void save(String key, IdempotencyEntry entry, long ttlSeconds) {
        try {
            String json = objectMapper.writeValueAsString(entry);
            redisTemplate.opsForValue().set(key, json, Duration.ofSeconds(ttlSeconds));
        } catch (Exception ex) {
            throw new IllegalStateException("Failed to store idempotency entry for Redis: {}",ex);
        }
    }

    @Override
    public boolean tryAcquireLock(String lockKey, long ttlSeconds) {
        Boolean acquired = redisTemplate.opsForValue().setIfAbsent(lockKey, "1", Duration.ofSeconds(ttlSeconds));
        return Boolean.TRUE.equals(acquired);
    }

    @Override
    public void releaseLock(String lockKey) {
        redisTemplate.delete(lockKey);
    }
}
