# Source Map

Class-oriented interview pages for TLBank (`sp2-springboot`).

Parent: [Open Book README](../README.md) · Plan: [00-open-book-roadmap.md](../00-open-book-roadmap.md)

---

## Purpose

Dedicated pages exist **only for important classes** (Critical + selected High).

Each page is a speaking script: why the class exists, runtime flow, design trade-offs, related tests, Q IDs, and timed explanations.

Do **not** expect pages for trivial DTOs, simple enums used only as related types, thin config, or small adapters — those stay as related classes on a parent page or topic.

### 中文筆記

source-map 只覆蓋面試高頻類別。次要型別掛在父頁或 topics，不獨立成篇。

---

## Priority

| Priority | Meaning | Status |
| --- | --- | --- |
| **Critical** | Must rehearse before interviews | **14 pages done (Phase 2)** |
| **High** | Strong follow-up surface | **Pending — Phase 4** (8 classes) |
| Related only | Explained under Critical/High or `topics/` | No dedicated file |

---

## Critical pages (Phase 2)

| Class | Category | Priority | One-sentence role | Related Q IDs | Page |
| --- | --- | --- | --- | --- | --- |
| `Application` | Domain | Critical | Aggregate root for application lifecycle | Q014, Q015, Q027, Q029, Q034, Q037, Q041, Q042, Q045, Q049, Q051, Q053–Q056 | [domain/Application.md](domain/Application.md) |
| `ApplicationAppService` | Application | Critical | Use-case orchestration for create/upload/submit/cancel | Q030, Q031, Q037, Q094, Q096, Q100, Q106, Q127, Q131, Q151, Q227, Q252, Q256, Q274, Q281 | [application/ApplicationAppService.md](application/ApplicationAppService.md) |
| `ApplicationStatus` | Domain | Critical | Enum + allowed transition map | Q009, Q012, Q014, Q042, Q051, Q052, Q055, Q059, Q114, Q145, Q243, Q246 | [domain/ApplicationStatus.md](domain/ApplicationStatus.md) |
| `ApplicationRepositoryImpl` | Infrastructure | Critical | JPA adapter for `ApplicationRepository` | Q027–Q029, Q034, Q092, Q101, Q102, Q107, Q210, Q258, Q297 | [infrastructure/ApplicationRepositoryImpl.md](infrastructure/ApplicationRepositoryImpl.md) |
| `ApplicationEntity` | Infrastructure | Critical | JPA mapping for `applications` | Q029, Q089–Q092, Q127, Q259 | [infrastructure/ApplicationEntity.md](infrastructure/ApplicationEntity.md) |
| `SecurityConfig` | Security | Critical | Session SecurityFilterChain and roles | Q017, Q020, Q023, Q025, Q033, Q070, Q077, Q081, Q083–Q087, Q112, Q203, Q207, Q208, Q250 | [security/SecurityConfig.md](security/SecurityConfig.md) |
| `IdempotencyService` | Application | Critical | Idempotent execute by key + body hash | Q009, Q011, Q035, Q063, Q067, Q132, Q269 | [application/IdempotencyService.md](application/IdempotencyService.md) |
| `RedisIdempotencyStore` | Infrastructure | Critical | Redis idempotency entries/locks only | Q016, Q028, Q132, Q134, Q229, Q268 | [infrastructure/RedisIdempotencyStore.md](infrastructure/RedisIdempotencyStore.md) |
| `OtpAppService` | Application | Critical | OTP send/verify and INIT→OTP_VERIFIED | Q046, Q103, Q135, Q137–Q139, Q150, Q164, Q204, Q205, Q251, Q272, Q277 | [application/OtpAppService.md](application/OtpAppService.md) |
| `ReviewAppService` | Application | Critical | Dual-aggregate approve/reject + events | Q104, Q109, Q110, Q130, Q148, Q209, Q214 | [application/ReviewAppService.md](application/ReviewAppService.md) |
| `ReviewCase` | Domain | Critical | Review aggregate and remarks | Q041, Q045, Q049, Q145–Q147, Q232, Q273 | [domain/ReviewCase.md](domain/ReviewCase.md) |
| `AuditAspect` | Common | Critical | AOP audit for `@Auditable` | Q024, Q072–Q074, Q076, Q135, Q277, Q282, Q291 | [common/AuditAspect.md](common/AuditAspect.md) |
| `LocalDocumentStorageService` | Infrastructure | Critical | Local filesystem document storage | Q008, Q053, Q106, Q177–Q182, Q208, Q216, Q255, Q298 | [infrastructure/LocalDocumentStorageService.md](infrastructure/LocalDocumentStorageService.md) |
| `NotificationEventHandler` | Infrastructure | Critical | Event → mock notification dispatch | Q002, Q038, Q044, Q099, Q100, Q109, Q130, Q233 | [infrastructure/NotificationEventHandler.md](infrastructure/NotificationEventHandler.md) |

Suggested reading order follows the table (also linked as Previous/Next on each page).

---

## High pages (**Pending** — Phase 4)

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

---

## Page template

Each Critical page includes: One-Sentence Summary, 中文一句話, Why, Responsibilities, Runtime Flow, Dependencies, Important Public Methods, Design Decisions, Trade-offs, Related Classes/Config/Tests/ADRs, Related Interview Questions (Q links), 30s/2m/5m explanations, 中文口語重點, Whiteboard Sketch, Follow-ups, Mistakes, Limitations, Source File, plus navigation.

---

## Question evidence

Full catalog: [09-interview-source-map-300.md](../../handbook/09-interview-source-map-300.md)
