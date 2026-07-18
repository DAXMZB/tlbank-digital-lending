# M06 — Redis Idempotency / Redis 冪等

## Why This Module Matters

### English

Redis scope is a hard constraint: create-idempotency only, not cache or session. ADR 0003.

### 中文

Redis 範圍為硬限制：僅建立申請冪等，非快取或 session。見 ADR 0003。

## Files to Open First

- `sp2-springboot/src/main/java/com/tlbank/lending/application/idempotency/IdempotencyService.java` — execute orchestration.  
  `sp2-springboot/src/main/java/com/tlbank/lending/application/idempotency/IdempotencyService.java` — execute 編排。
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/idempotency/RedisIdempotencyStore.java` — Redis store adapter.  
  `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/idempotency/RedisIdempotencyStore.java` — Redis 儲存適配器。
- `sp2-springboot/docs/decisions/0003-use-redis-idempotency.md` — Redis scope ADR.  
  `sp2-springboot/docs/decisions/0003-use-redis-idempotency.md` — Redis 範圍 ADR。
- `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/v1/ApplicationApiController.java` — Idempotency-Key on create.  
  `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/v1/ApplicationApiController.java` — 建立時的 Idempotency-Key。

## Primary Question

### English

What is Redis used for in this project?

### 中文

本專案中 Redis 用於什麼？

## Suggested Answer

### English

Idempotency for application create. IdempotencyService builds storageKey = key-prefix + Idempotency-Key and lockKey = storageKey + ":lock". Product cache uses InMemoryCacheStore. Sessions use SessionRegistryImpl. OTP is not stored in Redis.

### 中文

用於建立申請的冪等。IdempotencyService 組成 storageKey = key-prefix + Idempotency-Key，lockKey = storageKey + ":lock"。產品快取為 InMemoryCacheStore。Session 為 SessionRegistryImpl。OTP 不存在 Redis。

## Follow-up Question

### English

Two parallel POSTs with the same key—what happens?

### 中文

兩個並行 POST 使用同一 key—結果？

## Follow-up Answer

### English

tryAcquireLock uses setIfAbsent (SETNX-style). If the lock is held, IDEMPOTENCY_KEY_IN_PROGRESS is thrown. Same key with different body hash yields IDEMPOTENCY_KEY_CONFLICT. finally releases the lock.

### 中文

tryAcquireLock 使用 setIfAbsent（SETNX 語意）。鎖被占用時拋 IDEMPOTENCY_KEY_IN_PROGRESS。同 key 不同 body hash 為 IDEMPOTENCY_KEY_CONFLICT。finally 釋放鎖。

## Interview Tip

### English

Why asked: Redis misconception trap.
Answer first: idempotency only + ADR 0003.
Keywords: storageKey, lockKey, setIfAbsent, memory store for tests.
Follow-ups: blank key behavior, docker-compose without Redis.

### 中文

提問原因：Redis 誤解陷阱。
先答：僅冪等 + ADR 0003。
關鍵詞：storageKey、lockKey、setIfAbsent、測試用 memory store。
追問：空白 key、compose 無 Redis。

## Open Book Navigation

- [Redis idempotency topic](../open-book/topics/07-redis-idempotency.md)  
  [Redis 冪等主題](../open-book/topics/07-redis-idempotency.md)
- [IdempotencyService source-map](../open-book/source-map/application/IdempotencyService.md)  
  [IdempotencyService 類別地圖](../open-book/source-map/application/IdempotencyService.md)
- [RedisIdempotencyStore source-map](../open-book/source-map/infrastructure/RedisIdempotencyStore.md)  
  [RedisIdempotencyStore 類別地圖](../open-book/source-map/infrastructure/RedisIdempotencyStore.md)
