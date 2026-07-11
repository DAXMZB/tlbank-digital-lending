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

| Priority     | Meaning                                    | Status                      |
| ------------ | ------------------------------------------ | --------------------------- |
| **Critical** | Must rehearse before interviews            | **14 pages done (Phase 2)** |
| **High**     | Strong follow-up surface                   | **8 pages done (Phase 4)**  |
| Related only | Explained under Critical/High or `topics/` | No dedicated file           |

**Coverage:** 14 Critical + 8 High = **22** dedicated source-map pages. Medium/trivial types stay related-only (§4.3 roadmap).

---

## Critical pages (Phase 2)

| Class                         | Category       | Priority | One-sentence role                                      | Related Q IDs                                                                            | Page                                                                                           |
| ----------------------------- | -------------- | -------- | ------------------------------------------------------ | ---------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------- |
| `Application`                 | Domain         | Critical | Aggregate root for application lifecycle               | Q014, Q015, Q027, Q029, Q034, Q037, Q041, Q042, Q045, Q049, Q051, Q053–Q056              | [domain/Application.md](domain/Application.md)                                                 |
| `ApplicationAppService`       | Application    | Critical | Use-case orchestration for create/upload/submit/cancel | Q030, Q031, Q037, Q094, Q096, Q100, Q106, Q127, Q131, Q151, Q227, Q252, Q256, Q274, Q281 | [application/ApplicationAppService.md](application/ApplicationAppService.md)                   |
| `ApplicationStatus`           | Domain         | Critical | Enum + allowed transition map                          | Q009, Q012, Q014, Q042, Q051, Q052, Q055, Q059, Q114, Q145, Q243, Q246                   | [domain/ApplicationStatus.md](domain/ApplicationStatus.md)                                     |
| `ApplicationRepositoryImpl`   | Infrastructure | Critical | JPA adapter for `ApplicationRepository`                | Q027–Q029, Q034, Q092, Q101, Q102, Q107, Q210, Q258, Q297                                | [infrastructure/ApplicationRepositoryImpl.md](infrastructure/ApplicationRepositoryImpl.md)     |
| `ApplicationEntity`           | Infrastructure | Critical | JPA mapping for `applications`                         | Q029, Q089–Q092, Q127, Q259                                                              | [infrastructure/ApplicationEntity.md](infrastructure/ApplicationEntity.md)                     |
| `SecurityConfig`              | Security       | Critical | Session SecurityFilterChain and roles                  | Q017, Q020, Q023, Q025, Q033, Q070, Q077, Q081, Q083–Q087, Q112, Q203, Q207, Q208, Q250  | [security/SecurityConfig.md](security/SecurityConfig.md)                                       |
| `IdempotencyService`          | Application    | Critical | Idempotent execute by key + body hash                  | Q009, Q011, Q035, Q063, Q067, Q132, Q269                                                 | [application/IdempotencyService.md](application/IdempotencyService.md)                         |
| `RedisIdempotencyStore`       | Infrastructure | Critical | Redis idempotency entries/locks only                   | Q016, Q028, Q132, Q134, Q229, Q268                                                       | [infrastructure/RedisIdempotencyStore.md](infrastructure/RedisIdempotencyStore.md)             |
| `OtpAppService`               | Application    | Critical | OTP send/verify and INIT→OTP_VERIFIED                  | Q046, Q103, Q135, Q137–Q139, Q150, Q164, Q204, Q205, Q251, Q272, Q277                    | [application/OtpAppService.md](application/OtpAppService.md)                                   |
| `ReviewAppService`            | Application    | Critical | Dual-aggregate approve/reject + events                 | Q104, Q109, Q110, Q130, Q148, Q209, Q214                                                 | [application/ReviewAppService.md](application/ReviewAppService.md)                             |
| `ReviewCase`                  | Domain         | Critical | Review aggregate and remarks                           | Q041, Q045, Q049, Q145–Q147, Q232, Q273                                                  | [domain/ReviewCase.md](domain/ReviewCase.md)                                                   |
| `AuditAspect`                 | Common         | Critical | AOP audit for `@Auditable`                             | Q024, Q072–Q074, Q076, Q135, Q277, Q282, Q291                                            | [common/AuditAspect.md](common/AuditAspect.md)                                                 |
| `LocalDocumentStorageService` | Infrastructure | Critical | Local filesystem document storage                      | Q008, Q053, Q106, Q177–Q182, Q208, Q216, Q255, Q298                                      | [infrastructure/LocalDocumentStorageService.md](infrastructure/LocalDocumentStorageService.md) |
| `NotificationEventHandler`    | Infrastructure | Critical | Event → mock notification dispatch                     | Q002, Q038, Q044, Q099, Q100, Q109, Q130, Q233                                           | [infrastructure/NotificationEventHandler.md](infrastructure/NotificationEventHandler.md)       |

Suggested reading order follows the table (also linked as Previous/Next on each page).

---

## High pages (Phase 4)

| Class                         | Category       | Priority | One-sentence role                               | Related Q IDs                                        | Page                                                                                           |
| ----------------------------- | -------------- | -------- | ----------------------------------------------- | ---------------------------------------------------- | ---------------------------------------------------------------------------------------------- |
| `OtpRecord`                   | Domain         | High     | OTP verify/expire/cancel domain rules           | Q045, Q046, Q060, Q136, Q138, Q140, Q235, Q266       | [domain/OtpRecord.md](domain/OtpRecord.md)                                                     |
| `AuditLogWriter`              | Common         | High     | Async REQUIRES_NEW audit persistence            | Q024, Q074, Q079, Q128, Q149, Q281, Q291             | [common/AuditLogWriter.md](common/AuditLogWriter.md)                                           |
| `GlobalExceptionHandler`      | Presentation   | High     | Exception → HTTP + ApiResponse                  | Q009, Q013, Q031, Q070, Q071, Q072, Q075, Q265, Q300 | [presentation/GlobalExceptionHandler.md](presentation/GlobalExceptionHandler.md)               |
| `ApplicationApiController`    | Presentation   | High     | REST + idempotency header on create             | Q031, Q037, Q062–Q067, Q177, Q278, Q286              | [presentation/ApplicationApiController.md](presentation/ApplicationApiController.md)           |
| `WorkflowDomainService`       | Domain         | High     | Transition validator (domain service vs entity) | Q030, Q032, Q225                                     | [domain/WorkflowDomainService.md](domain/WorkflowDomainService.md)                             |
| `CachedCardProductRepository` | Infrastructure | High     | In-memory cache decorator (not Redis)           | Q039, Q086, Q108, Q212, Q284                         | [infrastructure/CachedCardProductRepository.md](infrastructure/CachedCardProductRepository.md) |
| `OtpCleanupScheduler`         | Infrastructure | High     | Cron OTP expiry cleanup                         | Q022, Q060, Q093, Q129, Q140, Q168, Q170             | [infrastructure/OtpCleanupScheduler.md](infrastructure/OtpCleanupScheduler.md)                 |
| `ReportAppService`            | Application    | High     | Excel/PDF daily-statistics orchestration        | Q133, Q171, Q173, Q176, Q209, Q231                   | [application/ReportAppService.md](application/ReportAppService.md)                             |

Suggested High reading order follows the table (Previous/Next on each page).

---

## Page template

Each Critical/High page includes: One-Sentence Summary, 中文一句話, Why, Responsibilities, Runtime Flow, Dependencies, Important Public Methods, Design Decisions, Trade-offs, Related Classes/Config/Tests/ADRs, Related Interview Questions (Q links), 30s/2m/5m explanations, 中文口語重點, Whiteboard Sketch, Follow-ups, Mistakes, Limitations, Source File, plus navigation.

Architecture depth belongs in [topics/](../topics/README.md) — source-map pages link there instead of duplicating hexagonal lectures.

---

## Question evidence

Full catalog: [09-interview-source-map-300.md](../../handbook/09-interview-source-map-300.md)
