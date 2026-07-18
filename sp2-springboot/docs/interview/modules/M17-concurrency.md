# M17 — Concurrency / 並發

## Why This Module Matters

### English

ConcurrentHashMap stores, ThreadLocal audit/MDC, and single-JVM session registry limits.

### 中文

ConcurrentHashMap 儲存、ThreadLocal 稽核／MDC，以及單 JVM session registry 限制。

## Files to Open First

- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/cache/InMemoryCacheStore.java` — ConcurrentHashMap cache.  
  `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/cache/InMemoryCacheStore.java` — ConcurrentHashMap 快取。
- `sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditContext.java` — ThreadLocal audit extras.  
  `sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditContext.java` — ThreadLocal 稽核附加資料。
- `sp2-springboot/src/main/java/com/tlbank/lending/security/filter/MdcLoggingFilter.java` — MDC put/clear.  
  `sp2-springboot/src/main/java/com/tlbank/lending/security/filter/MdcLoggingFilter.java` — MDC 寫入／清除。
- `sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditLogWriter.java` — @Async writer.  
  `sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditLogWriter.java` — @Async 寫入器。

## Primary Question

### English

Where is ConcurrentHashMap used and what does it not solve?

### 中文

ConcurrentHashMap 用於何處？它不解決什麼？

## Suggested Answer

### English

InMemoryCacheStore and InMemoryIdempotencyStore use ConcurrentHashMap for single-JVM concurrent access. It does not provide multi-instance coherence or distributed locking across pods.

### 中文

InMemoryCacheStore 與 InMemoryIdempotencyStore 使用 ConcurrentHashMap 處理單 JVM 並發存取。它不提供多實例一致性，也不提供跨節點分散式鎖。

## Follow-up Question

### English

Does @Async inherit web MDC automatically?

### 中文

@Async 是否自動繼承 web MDC？

## Follow-up Answer

### English

No. AuditLogWriter runs on another thread. MDC set by MdcLoggingFilter on the request thread is not automatically present unless copied explicitly.

### 中文

否。AuditLogWriter 在其他執行緒執行。MdcLoggingFilter 在請求執行緒設定的 MDC 不會自動出現，除非明確複製。

## Interview Tip

### English

Why asked: concurrency boundaries.
Answer first: CHM local safety only.
Keywords: AuditContext.clear, MDC finally, SessionRegistryImpl.
Follow-ups: maximumSessions across nodes, TaskDecorator.

### 中文

提問原因：並發邊界。
先答：CHM 僅本機安全。
關鍵詞：AuditContext.clear、MDC finally、SessionRegistryImpl。
追問：跨節點 maximumSessions、TaskDecorator。

## Open Book Navigation

- [Transactions / async audit](../open-book/topics/06-transactions.md)  
  [交易／非同步稽核](../open-book/topics/06-transactions.md)
- [Security sessions](../open-book/topics/03-security.md)  
  [安全性 session](../open-book/topics/03-security.md)
