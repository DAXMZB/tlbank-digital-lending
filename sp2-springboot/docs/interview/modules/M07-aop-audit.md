# M07 — AOP Audit / AOP 稽核

## Why This Module Matters

### English

Cross-cutting audit via @Auditable tests AOP literacy and fail-open persistence.

### 中文

以 @Auditable 做跨切稽核，檢驗 AOP 觀念與 fail-open 落庫。

## Files to Open First

- `sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditAspect.java` — @Around advice.  
  `sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditAspect.java` — @Around 通知。
- `sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditLogWriter.java` — Async REQUIRES_NEW writer.  
  `sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditLogWriter.java` — 非同步 REQUIRES_NEW 寫入。
- `sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditDetailBuilder.java` — Detail sanitization.  
  `sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditDetailBuilder.java` — 細節脫敏。

## Primary Question

### English

How does @Auditable work end to end?

### 中文

@Auditable 端到端如何運作？

## Suggested Answer

### English

AuditAspect wraps @Auditable methods with @Around. Success schedules SUCCESS audit; failure schedules FAILURE then rethrows. Details go through AuditDetailBuilder. AuditContext ThreadLocal is cleared in finally. AuditLogWriter.saveAsync persists with REQUIRES_NEW.

### 中文

AuditAspect 以 @Around 包住 @Auditable 方法。成功排程 SUCCESS；失敗排程 FAILURE 後重新拋出。細節經 AuditDetailBuilder。AuditContext ThreadLocal 於 finally 清除。AuditLogWriter.saveAsync 以 REQUIRES_NEW 落庫。

## Follow-up Question

### English

Why might @Auditable not run on a same-class method call?

### 中文

為何同類別內部呼叫時 @Auditable 可能不執行？

## Follow-up Answer

### English

Spring AOP proxy self-invocation: this.otherMethod() bypasses the proxy, so the aspect does not apply unless the call goes through the Spring bean proxy.

### 中文

Spring AOP 代理自我呼叫：this.otherMethod() 繞過代理，切面不生效，除非經由 Spring bean 代理呼叫。

## Interview Tip

### English

Why asked: AOP proxy mechanics.
Answer first: around advice + rethrow.
Keywords: AuditContext.clear, REQUIRES_NEW, OTP action remap.
Follow-ups: async test Executor, missing audit rows.

### 中文

提問原因：AOP 代理機制。
先答：環繞通知 + 重新拋出。
關鍵詞：AuditContext.clear、REQUIRES_NEW、OTP action 重映射。
追問：非同步測試 Executor、稽核缺列。

## Open Book Navigation

- [Audit topic](../open-book/topics/10-audit-logging.md)  
  [稽核主題](../open-book/topics/10-audit-logging.md)
- [AuditAspect source-map](../open-book/source-map/common/AuditAspect.md)  
  [AuditAspect 類別地圖](../open-book/source-map/common/AuditAspect.md)
- [AuditLogWriter source-map](../open-book/source-map/common/AuditLogWriter.md)  
  [AuditLogWriter 類別地圖](../open-book/source-map/common/AuditLogWriter.md)
