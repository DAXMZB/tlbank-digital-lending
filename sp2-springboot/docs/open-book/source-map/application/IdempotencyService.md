# IdempotencyService

- [Back to Open Book Home](../../README.md)
- [Back to Source Map Index](../README.md)
- Previous Critical Class: [SecurityConfig](../security/SecurityConfig.md)
- Next Critical Class: [RedisIdempotencyStore](../infrastructure/RedisIdempotencyStore.md)
- Related Topics: [topics/](../../topics/README.md)
- Related Questions: [09-interview-source-map-300.md](../../../handbook/09-interview-source-map-300.md)

---

## One-Sentence Summary

Coordinates idempotent API execution: same key+body replays; same key+different body conflicts; optional blank key bypasses.

## 中文一句話

冪等協調器：同 Key 同 Body 回放；同 Key 不同 Body 409；無 Key 直接執行。

## Why This Class Exists

Protect create-like endpoints from double submits using `IdempotencyStore` (Redis or in-memory).

Redis here is **idempotency storage**, not cache/session — [0003-use-redis-idempotency.md](../../../decisions/0003-use-redis-idempotency.md).

## Responsibilities

- Hash request body (SHA-256 of JSON)
- Find cached entry / acquire lock / run action / save entry / release lock
- Rebuild `ResponseEntity<ApiResponse<T>>` from stored JSON

## Runtime Execution Flow

1. Blank key → `action.get()` immediately.
2. Else `find(storageKey)`; hash mismatch → `IDEMPOTENCY_KEY_CONFLICT`.
3. Hit → rebuild cached response.
4. Miss → `tryAcquireLock`; failure → `IDEMPOTENCY_KEY_IN_PROGRESS`.
5. Run action; `save` entry with TTL; always `releaseLock` in finally.

## Dependencies

### Depends On

- `IdempotencyStore`, `ObjectMapper`
- Properties `tlbank.idempotency.ttl-hours`, `tlbank.idempotency.key-prefix`

### Called By

- API controllers wrapping create (e.g. applications create with `Idempotency-Key`)

### Calls

- Store `find` / `tryAcquireLock` / `save` / `releaseLock`
- private `hashRequest`, `serializeBody`, `rebuildResponse`

## Important Public Methods

### `ResponseEntity<ApiResponse<T>> execute(String idempotencyKey, Object requestBody, Supplier<ResponseEntity<ApiResponse<T>>> action)`

- **Purpose:** Idempotent execute wrapper
- **Input:** key, body, action supplier
- **Output:** HTTP ApiResponse entity
- **Business meaning:** Deduplicate creates
- **Side effects:** May read/write idempotency store + lock keys

## Design Decisions

- 30s lock TTL constant
- Entry TTL from `ttl-hours` (default 24)
- Prefix keys for namespace isolation

## Trade-offs and Alternatives

- Optional key (compatibility) vs mandatory key
- Store abstraction allows Redis or memory without changing this class

## Related Classes

- [RedisIdempotencyStore](../infrastructure/RedisIdempotencyStore.md), `InMemoryIdempotencyStore`, `IdempotencyStore`, `IdempotencyEntry`

## Related Configuration

- [application.yml](../../../../src/main/resources/application.yml) — `tlbank.idempotency.ttl-hours`, `key-prefix`
- [application-dev.yml](../../../../src/main/resources/application-dev.yml) — `tlbank.idempotency.store: redis`

## Related Tests

- [IdempotencyServiceTest.java](../../../../src/test/java/com/tlbank/lending/application/idempotency/IdempotencyServiceTest.java)
- [ApplicationIdempotencyIntegrationTest.java](../../../../src/test/java/com/tlbank/lending/application/ApplicationIdempotencyIntegrationTest.java)

## Related ADRs and Design Documents

- [0003-use-redis-idempotency.md](../../../decisions/0003-use-redis-idempotency.md)

## Related Interview Questions

[`Q009`](../../../handbook/09-interview-source-map-300.md#Q009), [`Q011`](../../../handbook/09-interview-source-map-300.md#Q011), [`Q035`](../../../handbook/09-interview-source-map-300.md#Q035), [`Q063`](../../../handbook/09-interview-source-map-300.md#Q063), [`Q067`](../../../handbook/09-interview-source-map-300.md#Q067), [`Q132`](../../../handbook/09-interview-source-map-300.md#Q132), [`Q269`](../../../handbook/09-interview-source-map-300.md#Q269)

## 30-Second Explanation

`IdempotencyService.execute` skips work when the key is blank; otherwise it caches successful responses by key and body hash, conflicts on hash mismatch, and uses a short Redis/memory lock while the first call runs.

## 2-Minute Explanation

Explain storage key = prefix + key, lock key suffix `:lock`, SHA-256 body hash, and rebuild path. Stress Redis store is only for idempotency entries.

## 5-Minute Deep Explanation

Discuss race with lock TTL, what happens if serialization fails, and why controllers own the header while this service stays generic.

## 中文口語重點

- 空白 Key → 不冪等
- 衝突 ErrorCode：IDEMPOTENCY_KEY_CONFLICT／IN_PROGRESS
- Lock TTL 30 秒；資料 TTL 預設 24 小時
- Redis ≠ session／cache

## Whiteboard Sketch

- **What to draw:** find → lock → action → save → unlock
- **Drawing order:** happy path then conflict branches
- **Narration order:** header optional caveat first

## Common Follow-Up Questions

- What if two requests race?
- Why hash the body?
- Memory vs Redis store?

## Common Mistakes

- Calling Redis a response cache for all GETs
- Saying keys are mandatory

## Current Limitations

- Relies on store TTL for cleanup
- Lock TTL fixed at 30 seconds

## Source File

[IdempotencyService.java](../../../../src/main/java/com/tlbank/lending/application/idempotency/IdempotencyService.java)
