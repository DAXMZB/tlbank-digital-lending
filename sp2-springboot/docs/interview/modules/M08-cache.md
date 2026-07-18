# M08 — Cache / 快取

## Why This Module Matters

### English

In-memory product cache versus Redis idempotency is a deliberate separation.

### 中文

記憶體產品快取與 Redis 冪等為刻意分離。

## Files to Open First

- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/cache/InMemoryCacheStore.java` — ConcurrentHashMap CacheStore.  
  `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/cache/InMemoryCacheStore.java` — ConcurrentHashMap 實作的 CacheStore。
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/cache/CachedCardProductRepository.java` — @Primary decorator.  
  `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/cache/CachedCardProductRepository.java` — @Primary 裝飾器。
- `sp2-springboot/src/main/java/com/tlbank/lending/application/cache/service/CacheManagementService.java` — Concrete dependency leak.  
  `sp2-springboot/src/main/java/com/tlbank/lending/application/cache/service/CacheManagementService.java` — 具體類依賴洩漏。

## Primary Question

### English

Where is application cache stored?

### 中文

應用快取存在何處？

## Suggested Answer

### English

Process-local InMemoryCacheStore. CachedCardProductRepository is a @Primary decorator over cardProductRepositoryImpl. Redis is not this cache. TTL comes from CacheTtlProvider (CACHE/ttl_seconds, default 21600).

### 中文

進程內 InMemoryCacheStore。CachedCardProductRepository 以 @Primary 裝飾 cardProductRepositoryImpl。Redis 不是此快取。TTL 來自 CacheTtlProvider（CACHE／ttl_seconds，預設 21600）。

## Follow-up Question

### English

What breaks with two app instances?

### 中文

兩個應用實例會出現什麼問題？

## Follow-up Answer

### English

Each JVM has its own memory. Refresh on one node does not invalidate the other. Distributed cache is not implemented.

### 中文

各 JVM 記憶體獨立。一節點刷新不會使另一節點失效。分散式快取未實作。

## Interview Tip

### English

Why asked: Redis confusion.
Answer first: InMemoryCacheStore, not Redis.
Keywords: @Primary, refreshCache, CacheManagementService leak.
Follow-ups: empty Optional caching, CacheRefreshScheduler.

### 中文

提問原因：Redis 混淆。
先答：InMemoryCacheStore，非 Redis。
關鍵詞：@Primary、refreshCache、CacheManagementService 洩漏。
追問：空 Optional 快取、CacheRefreshScheduler。

## Open Book Navigation

- [Cache topic](../open-book/topics/08-cache.md)  
  [快取主題](../open-book/topics/08-cache.md)
- [CachedCardProductRepository source-map](../open-book/source-map/infrastructure/CachedCardProductRepository.md)  
  [CachedCardProductRepository 類別地圖](../open-book/source-map/infrastructure/CachedCardProductRepository.md)
