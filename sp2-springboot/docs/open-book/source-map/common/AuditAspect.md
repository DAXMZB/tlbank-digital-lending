# AuditAspect

- [Back to Open Book Home](../../README.md)
- [Back to Source Map Index](../README.md)
- Previous Critical Class: [ReviewCase](../domain/ReviewCase.md)
- Next Critical Class: [LocalDocumentStorageService](../infrastructure/LocalDocumentStorageService.md)
- Related Topics: [topics/](../../topics/README.md)
- Related Questions: [09-interview-source-map-300.md](../../../handbook/09-interview-source-map-300.md)

---

## One-Sentence Summary

AOP `@Around` advice that writes SUCCESS/FAILURE audit logs for `@Auditable` methods.

## 中文一句話

攔截 `@Auditable`：成功／失敗寫入稽核；OTP 驗證失敗會改寫成 OTP_VERIFY_FAILED。

## Why This Class Exists

Cross-cutting audit without cluttering every use case.

## Responsibilities

- Resolve username from SecurityContext (or ANONYMOUS)
- Resolve client IP
- On success: async save SUCCESS with sanitized detail
- On failure: async save FAILURE (with OTP action remap); rethrow
- Clear `AuditContext` in finally

## Runtime Execution Flow

1. Match `@annotation(auditable)`.
2. `proceed()`.
3. Success → `auditLogWriter.saveAsync(...)`.
4. Exception → remap action if OTP verify; save FAILURE; throw.
5. `AuditContext.clear()`.

## Dependencies

### Depends On

- [AuditLogWriter](AuditLogWriter.md), `HttpServletRequest`
- `AuditDetailBuilder`, `AuditIpResolver`, `AuditContext`

### Called By

- Spring AOP runtime for `@Auditable` services (OTP, application, review, …)

### Calls

- `joinPoint.proceed`, `auditLogWriter.saveAsync`

## Important Public Methods

### `Object audit(ProceedingJoinPoint joinPoint, Auditable auditable)`

- **Purpose:** Around advice entry
- **Input:** join point + annotation
- **Output:** proceed result or throw
- **Security behavior:** Reads SecurityContext username
- **Side effects:** Persists audit rows asynchronously via writer

## Design Decisions

- Failure action remap only for `OTP_VERIFY_SUCCESS` → `OTP_VERIFY_FAILED`
- Detail from args + `AuditContext` suffix
- Exception messages truncated to 500 chars

## Trade-offs and Alternatives

- Async writer isolation vs sync audit in same TX — writer uses separate concerns (see High page later)
- AOP magic can surprise; annotation keeps call sites clean

## Related Classes

- `AuditLogWriter`, `AuditDetailBuilder`, `Auditable`, services with `@Auditable`

## Related Configuration

- Spring AOP proxying (Boot auto-config)
- No special property beyond datasource for audit tables

## Related Tests

- [AuditAspectTest.java](../../../../src/test/java/com/tlbank/lending/common/audit/AuditAspectTest.java)
- Helper [AuditableTestService.java](../../../../src/test/java/com/tlbank/lending/common/audit/AuditableTestService.java)

## Related ADRs and Design Documents

- [11-audit-logging.md](../../../design/11-audit-logging.md)

## Related Interview Questions

[`Q024`](../../../handbook/09-interview-source-map-300.md#Q024), [`Q072`](../../../handbook/09-interview-source-map-300.md#Q072), [`Q073`](../../../handbook/09-interview-source-map-300.md#Q073), [`Q074`](../../../handbook/09-interview-source-map-300.md#Q074), [`Q076`](../../../handbook/09-interview-source-map-300.md#Q076), [`Q135`](../../../handbook/09-interview-source-map-300.md#Q135), [`Q277`](../../../handbook/09-interview-source-map-300.md#Q277), [`Q282`](../../../handbook/09-interview-source-map-300.md#Q282), [`Q291`](../../../handbook/09-interview-source-map-300.md#Q291)

## 30-Second Explanation

`AuditAspect` wraps `@Auditable` methods, writes SUCCESS or FAILURE audit logs with user/IP/detail, and remaps OTP verify failures to a dedicated action.

## 2-Minute Explanation

Explain anonymous users, detail builder sanitization, and finally clearing AuditContext so OTP codes do not leak across requests.

## 5-Minute Deep Explanation

Discuss interaction with `AuditLogWriter` transaction settings and why audit must not swallow business exceptions.

## 中文口語重點

- `@Around @Auditable`
- 失敗仍拋出原例外
- OTP 驗證失敗 action 會改寫
- finally 清 `AuditContext`

## Whiteboard Sketch

- **What to draw:** advice around service method → AuditLogWriter
- **Drawing order:** before/after/throw paths
- **Narration order:** success then failure remap

## Common Follow-Up Questions

- Does audit rollback with business TX?
- How is OTP code prevented in logs?

## Common Mistakes

- Saying audit replaces business error handling
- Forgetting ANONYMOUS path

## Current Limitations

- Relies on HTTP request bean (web thread assumption)
- Async semantics depend on `AuditLogWriter` implementation

## Source File

[AuditAspect.java](../../../../src/main/java/com/tlbank/lending/common/audit/AuditAspect.java)
