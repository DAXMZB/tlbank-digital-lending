# RedisIdempotencyStore

- [Back to Open Book Home](../../README.md)
- [Back to Source Map Index](../README.md)
- Previous Critical Class: [IdempotencyService](../application/IdempotencyService.md)
- Next Critical Class: [OtpAppService](../application/OtpAppService.md)
- Related Topics: [topics/](../../topics/README.md)
- Related Questions: [09-interview-source-map-300.md](../../../handbook/09-interview-source-map-300.md)

---

## One-Sentence Summary

Redis `IdempotencyStore` using `StringRedisTemplate` SET/GET/SETNX/DELETE with JSON entries.

## 中文一句話

Redis 冪等儲存實作：`@ConditionalOnProperty(store=redis)`，只服務冪等，不是一般快取。

## Why This Class Exists

Provide durable-enough idempotency records when `tlbank.idempotency.store=redis`.

## Responsibilities

- `find` / `save` entry JSON with TTL
- `tryAcquireLock` via `setIfAbsent`
- `releaseLock` via delete

## Runtime Execution Flow

Used only through `IdempotencyService` when this bean is active.

## Dependencies

### Depends On

- `StringRedisTemplate`, `ObjectMapper`

### Called By

- `IdempotencyService` (when Redis store bean present)

### Calls

- Redis value operations

## Important Public Methods

### `Optional<IdempotencyEntry> find(String key)`

- **Purpose:** GET JSON and deserialize
- **Output:** Optional entry

### `void save(String key, IdempotencyEntry entry, long ttlSeconds)`

- **Purpose:** SET JSON with TTL
- **Side effects:** Writes Redis key

### `boolean tryAcquireLock(String lockKey, long ttlSeconds)`

- **Purpose:** SETNX-style lock
- **Output:** true if acquired

### `void releaseLock(String lockKey)`

- **Purpose:** DELETE lock key

## Design Decisions

- Conditional bean on property `redis`
- Fail soft on deserialize (log warn, empty)
- Fail hard on save serialize errors

## Trade-offs and Alternatives

- Sibling `InMemoryIdempotencyStore` for `store=memory` / tests
- Not a general Spring Cache manager

## Related Classes

- [IdempotencyService](../application/IdempotencyService.md), `InMemoryIdempotencyStore`, `IdempotencyStore`

## Related Configuration

- `tlbank.idempotency.store=redis` in [application-dev.yml](../../../../src/main/resources/application-dev.yml)
- Redis connection under Spring Data Redis settings in the same profiles
- Compose Redis service: [docker-compose.yml](../../../../docker-compose.yml)

## Related Tests

- **No dedicated `RedisIdempotencyStoreTest`**
- Behavior covered via [IdempotencyServiceTest.java](../../../../src/test/java/com/tlbank/lending/application/idempotency/IdempotencyServiceTest.java) (typically with memory/mock store) and [ApplicationIdempotencyIntegrationTest.java](../../../../src/test/java/com/tlbank/lending/application/ApplicationIdempotencyIntegrationTest.java) depending on test profile

## Related ADRs and Design Documents

- [0003-use-redis-idempotency.md](../../../decisions/0003-use-redis-idempotency.md)

## Related Interview Questions

[`Q016`](../../../handbook/09-interview-source-map-300.md#Q016), [`Q028`](../../../handbook/09-interview-source-map-300.md#Q028), [`Q132`](../../../handbook/09-interview-source-map-300.md#Q132), [`Q134`](../../../handbook/09-interview-source-map-300.md#Q134), [`Q229`](../../../handbook/09-interview-source-map-300.md#Q229), [`Q268`](../../../handbook/09-interview-source-map-300.md#Q268)

## 30-Second Explanation

`RedisIdempotencyStore` persists idempotency entries and locks in Redis when `tlbank.idempotency.store=redis`. It is not used for HTTP sessions or product cache.

## 2-Minute Explanation

Show SET with TTL for entries and setIfAbsent for locks. Mention the memory alternative and conditional beans.

## 5-Minute Deep Explanation

Discuss deserialize warn path, lock deletion, and operational need for Redis in dev compose. Contrast with `InMemoryCacheStore` for products/parameters.

## 中文口語重點

- 條件：`store=redis`
- SETNX 鎖；TTL 清資料
- 不是 session，也不是商品快取

## Whiteboard Sketch

- **What to draw:** IdempotencyService → Redis keys (entry + lock)
- **Drawing order:** entry key then lock key
- **Narration order:** property switch memory/redis

## Common Follow-Up Questions

- What if Redis is down?
- Difference from cache store?

## Common Mistakes

- “We use Redis for sessions”
- “Redis caches all API responses”

## Current Limitations

- No dedicated store unit test in repo
- Soft-fail find may hide corruption until conflicts appear

## Source File

[RedisIdempotencyStore.java](../../../../src/main/java/com/tlbank/lending/infrastructure/idempotency/RedisIdempotencyStore.java)
