# M28 — Code Review / 程式碼審查

## Why This Module Matters

### English

Review findings: dependency leaks, permitAll breadth, sync event coupling.

### 中文

審查焦點：依賴洩漏、permitAll 範圍、同步事件耦合。

## Files to Open First

- `sp2-springboot/src/main/java/com/tlbank/lending/application/cache/service/CacheManagementService.java` — Concrete cache dependency.  
  `sp2-springboot/src/main/java/com/tlbank/lending/application/cache/service/CacheManagementService.java` — 具體快取依賴。
- `sp2-springboot/src/main/java/com/tlbank/lending/application/report/service/ReportAppService.java` — Concrete generator dependency.  
  `sp2-springboot/src/main/java/com/tlbank/lending/application/report/service/ReportAppService.java` — 具體產生器依賴。
- `sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java` — permitAll and CSRF scope.  
  `sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java` — permitAll 與 CSRF 範圍。

## Primary Question

### English

What review comment fits CacheManagementService?

### 中文

對 CacheManagementService 的審查意見為何？

## Suggested Answer

### English

It injects CachedCardProductRepository concretely. Prefer depending on a port or a narrower interface. Also clarify that cache is InMemoryCacheStore, not Redis.

### 中文

它直接注入 CachedCardProductRepository。應改為依賴 port 或更窄介面。並釐清快取為 InMemoryCacheStore，非 Redis。

## Follow-up Question

### English

What security demo risk should a review call out?

### 中文

安全性審查應指出哪些演示風險？

## Follow-up Answer

### English

Broad permitAll on applicant APIs and CSRF ignore on /api/**. Those are acceptable for local demos only if named explicitly.

### 中文

申請人 API 的寬鬆 permitAll，以及 /api/** 的 CSRF 忽略。僅在明確說明為本機演示時可接受。

## Interview Tip

### English

Why asked: review judgment.
Answer first: concrete dependency leak.
Keywords: @Primary decorator, permitAll, sync @EventListener.
Follow-ups: required tests for a new aggregate.

### 中文

提問原因：審查判斷。
先答：具體依賴洩漏。
關鍵詞：@Primary 裝飾器、permitAll、同步 @EventListener。
追問：新聚合所需測試。

## Open Book Navigation

- [Architecture](../open-book/topics/01-architecture.md)  
  [架構](../open-book/topics/01-architecture.md)
- [Cache](../open-book/topics/08-cache.md)  
  [快取](../open-book/topics/08-cache.md)
- [Security](../open-book/topics/03-security.md)  
  [安全性](../open-book/topics/03-security.md)
