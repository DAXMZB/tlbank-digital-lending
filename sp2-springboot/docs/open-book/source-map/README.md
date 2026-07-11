# Source Map

Class-oriented interview pages for TLBank (`sp2-springboot`).

Parent: [Open Book README](../README.md) · Plan: [00-open-book-roadmap.md](../00-open-book-roadmap.md)

---

## Purpose

Dedicated pages exist **only for important classes** (Critical + selected High).

Each page is a speaking script: why the class exists, runtime flow, design trade-offs, related tests, Q IDs, and timed explanations.

Do **not** expect pages for trivial DTOs, simple enums, thin config, or small adapters — those stay as related classes on a parent page or topic.

### 中文筆記

source-map 只覆蓋面試高頻類別。次要型別掛在父頁或 topics，不獨立成篇。

---

## Priority

| Priority | Meaning | When a page is created |
| --- | --- | --- |
| **Critical** | Must rehearse before interviews; highest Q density / flow centrality | Phase 2 — **14 pages Pending** |
| **High** | Strong follow-up surface; still worth a dedicated script | Phase 4 — **8 pages Pending** |
| Related only | Explained under a Critical/High page or a `topics/` page | No dedicated file |

---

## Page Template

Planned sections (from roadmap):

1. One Sentence Summary
2. Why This Class Exists
3. Responsibilities
4. Runtime Execution Flow
5. Dependencies
6. Important Public Methods
7. Design Decisions
8. Trade-offs
9. Related Classes
10. Related Tests
11. Related Interview Questions
12. 30-Second Explanation
13. 2-Minute Explanation
14. 5-Minute Deep Explanation
15. Whiteboard Sketch
16. Common Mistakes
17. Current Limitations

---

## Planned Class List

### Critical (**Pending** — Phase 2)

| Planned page | Class source |
| --- | --- |
| `Application.md` | [`Application.java`](../../../src/main/java/com/tlbank/lending/domain/application/Application.java) |
| `ApplicationAppService.md` | [`ApplicationAppService.java`](../../../src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java) |
| `ApplicationStatus.md` | [`ApplicationStatus.java`](../../../src/main/java/com/tlbank/lending/domain/application/ApplicationStatus.java) |
| `ApplicationRepositoryImpl.md` | [`ApplicationRepositoryImpl.java`](../../../src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationRepositoryImpl.java) |
| `ApplicationEntity.md` | [`ApplicationEntity.java`](../../../src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationEntity.java) |
| `SecurityConfig.md` | [`SecurityConfig.java`](../../../src/main/java/com/tlbank/lending/security/config/SecurityConfig.java) |
| `IdempotencyService.md` | [`IdempotencyService.java`](../../../src/main/java/com/tlbank/lending/application/idempotency/IdempotencyService.java) |
| `RedisIdempotencyStore.md` | [`RedisIdempotencyStore.java`](../../../src/main/java/com/tlbank/lending/infrastructure/idempotency/RedisIdempotencyStore.java) |
| `OtpAppService.md` | [`OtpAppService.java`](../../../src/main/java/com/tlbank/lending/application/otp/service/OtpAppService.java) |
| `ReviewAppService.md` | [`ReviewAppService.java`](../../../src/main/java/com/tlbank/lending/application/review/service/ReviewAppService.java) |
| `ReviewCase.md` | [`ReviewCase.java`](../../../src/main/java/com/tlbank/lending/domain/review/ReviewCase.java) |
| `AuditAspect.md` | [`AuditAspect.java`](../../../src/main/java/com/tlbank/lending/common/audit/AuditAspect.java) |
| `LocalDocumentStorageService.md` | [`LocalDocumentStorageService.java`](../../../src/main/java/com/tlbank/lending/infrastructure/storage/LocalDocumentStorageService.java) |
| `NotificationEventHandler.md` | [`NotificationEventHandler.java`](../../../src/main/java/com/tlbank/lending/infrastructure/event/NotificationEventHandler.java) |

### High (**Pending** — Phase 4)

| Planned page | Class source |
| --- | --- |
| `OtpRecord.md` | [`OtpRecord.java`](../../../src/main/java/com/tlbank/lending/domain/otp/OtpRecord.java) |
| `AuditLogWriter.md` | [`AuditLogWriter.java`](../../../src/main/java/com/tlbank/lending/common/audit/AuditLogWriter.java) |
| `GlobalExceptionHandler.md` | [`GlobalExceptionHandler.java`](../../../src/main/java/com/tlbank/lending/presentation/api/advice/GlobalExceptionHandler.java) |
| `ApplicationApiController.md` | [`ApplicationApiController.java`](../../../src/main/java/com/tlbank/lending/presentation/api/v1/ApplicationApiController.java) |
| `WorkflowDomainService.md` | [`WorkflowDomainService.java`](../../../src/main/java/com/tlbank/lending/domain/service/WorkflowDomainService.java) |
| `CachedCardProductRepository.md` | [`CachedCardProductRepository.java`](../../../src/main/java/com/tlbank/lending/infrastructure/cache/CachedCardProductRepository.java) |
| `OtpCleanupScheduler.md` | [`OtpCleanupScheduler.java`](../../../src/main/java/com/tlbank/lending/infrastructure/scheduler/OtpCleanupScheduler.java) |
| `ReportAppService.md` | [`ReportAppService.java`](../../../src/main/java/com/tlbank/lending/application/report/service/ReportAppService.java) |

Question evidence today: [09-interview-source-map-300.md](../../handbook/09-interview-source-map-300.md)

---

## Status

No class pages in this folder yet. Only this README (Phase 1).
