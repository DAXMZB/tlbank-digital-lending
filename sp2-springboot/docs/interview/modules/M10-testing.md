# M10 — Testing / 測試

## Why This Module Matters

### English

Test pyramid shape and how @Async audit is made deterministic.

### 中文

測試金字塔形狀，以及如何使 @Async 稽核可斷言。

## Files to Open First

- `sp2-springboot/src/test/java/com/tlbank/lending/application/application/ApplicationAppServiceTest.java` — Mockito unit test.  
  `sp2-springboot/src/test/java/com/tlbank/lending/application/application/ApplicationAppServiceTest.java` — Mockito 單元測試。
- `sp2-springboot/src/test/java/com/tlbank/lending/application/ApplicationFlowIntegrationTest.java` — Flow IT.  
  `sp2-springboot/src/test/java/com/tlbank/lending/application/ApplicationFlowIntegrationTest.java` — 流程整合測試。
- `sp2-springboot/src/test/java/com/tlbank/lending/common/audit/AuditAspectTest.java` — Sync Executor for @Async.  
  `sp2-springboot/src/test/java/com/tlbank/lending/common/audit/AuditAspectTest.java` — 以同步 Executor 處理 @Async。
- `sp2-springboot/docs/design/16-testing-strategy.md` — Testing strategy design.  
  `sp2-springboot/docs/design/16-testing-strategy.md` — 測試策略設計。

## Primary Question

### English

Describe the test pyramid in this repository.

### 中文

說明本倉庫的測試金字塔。

## Suggested Answer

### English

Fast Mockito unit tests cover application services and domain. @SpringBootTest covers flows and security. AuditAspectTest supplies a synchronous Executor so @Async saveAsync is assertable. Idempotency ITs use the memory store. Some adapters lack dedicated unit tests. Testcontainers is not the default suite baseline.

### 中文

快速 Mockito 單元測試覆蓋 application service 與領域。@SpringBootTest 覆蓋流程與安全性。AuditAspectTest 提供同步 Executor，使 @Async saveAsync 可斷言。冪等整合測試使用 memory store。部分 adapter 缺少專屬單元測試。Testcontainers 非預設基線。

## Follow-up Question

### English

How do idempotency tests avoid Redis?

### 中文

冪等測試如何避開 Redis？

## Follow-up Answer

### English

Test resources set tlbank.idempotency.store=memory so InMemoryIdempotencyStore is active.

### 中文

測試資源將 tlbank.idempotency.store 設為 memory，啟用 InMemoryIdempotencyStore。

## Interview Tip

### English

Why asked: testing maturity without overclaim.
Answer first: unit + IT split.
Keywords: MockitoExtension, SpringBootTest, sync Executor.
Follow-ups: JaCoCo, coverage gaps.

### 中文

提問原因：測試成熟度且避免超宣稱。
先答：單元與整合分層。
關鍵詞：MockitoExtension、SpringBootTest、同步 Executor。
追問：JaCoCo、覆蓋缺口。

## Open Book Navigation

- [Testing topic](../open-book/topics/11-testing.md)  
  [測試主題](../open-book/topics/11-testing.md)
