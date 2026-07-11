# Interview Open Book

Interview-oriented navigation for the TLBank Digital Lending codebase in `sp2-springboot`.

Depth lives in existing handbooks, SDDs, and ADRs. This Open Book points to those files and holds short speaking scripts (Critical source-map + unified topics). Whiteboards and indexes remain later phases.

Roadmap: [00-open-book-roadmap.md](00-open-book-roadmap.md)

---

## Purpose

This documentation is designed for:

- **Fast recall** — know what to open in 30 minutes or 2 hours
- **Code navigation** — jump from a theme to the real class path
- **Technical explanation** — ground answers in repository files
- **Design discussion** — link ADRs and SDDs instead of inventing rationale
- **Interview follow-up preparation** — use validated Q IDs from the 300-question source map

### 中文筆記

Open Book 用於面試前快速定位：先看路徑與題號，再打開原始碼與既有 handbook／ADR。不作第二份百科全書。

---

## Review Modes

### 30-Minute Review

1. [12-interview-quick-review.md](../handbook/12-interview-quick-review.md) — bullet cheat sheet
2. [10-core-interview-questions-30.md](../handbook/10-core-interview-questions-30.md) — highest-signal questions

Optional locator: [08-file-reference-handbook.md](../handbook/08-file-reference-handbook.md)

### 2-Hour Review

Recommended order (handbook depth + Open Book topic pages):

1. **Project overview** — [00-project-overview.md](../handbook/00-project-overview.md), [README.md](../../README.md)
2. **Architecture** — [topics/01-architecture.md](topics/01-architecture.md) · [02-architecture-handbook.md](../handbook/02-architecture-handbook.md), [0001-use-clean-architecture.md](../decisions/0001-use-clean-architecture.md)
3. **Application workflow** — [topics/04-domain-and-workflow.md](topics/04-domain-and-workflow.md) · [03-business-feature-handbook.md](../handbook/03-business-feature-handbook.md), [08-workflow-design.md](../design/08-workflow-design.md)
4. **Security** — [topics/03-security.md](topics/03-security.md) · [07-security-design.md](../design/07-security-design.md), [0006-session-over-jwt.md](../decisions/0006-session-over-jwt.md)
5. **JPA and transactions** — [topics/05-jpa-and-sql.md](topics/05-jpa-and-sql.md), [topics/06-transactions.md](topics/06-transactions.md) · [05-database-design.md](../design/05-database-design.md), [0007-h2-vs-sqlserver.md](../decisions/0007-h2-vs-sqlserver.md)
6. **Redis and idempotency** — [topics/07-redis-idempotency.md](topics/07-redis-idempotency.md) · [0003-use-redis-idempotency.md](../decisions/0003-use-redis-idempotency.md)
7. **Testing** — [topics/11-testing.md](topics/11-testing.md) · [16-testing-strategy.md](../design/16-testing-strategy.md)
8. **Docker and CI/CD** — [topics/12-delivery-and-limitations.md](topics/12-delivery-and-limitations.md) · [docker-compose.yml](../../docker-compose.yml), [ci.yml](../../../.github/workflows/ci.yml), [0004-use-github-actions.md](../decisions/0004-use-github-actions.md)
9. **Known limitations** — [topics/12-delivery-and-limitations.md](topics/12-delivery-and-limitations.md) · [06-system-design-handbook.md](../handbook/06-system-design-handbook.md), [0005-use-terraform-local.md](../decisions/0005-use-terraform-local.md)

### Full-Day Review

1. **Core 30** — [10-core-interview-questions-30.md](../handbook/10-core-interview-questions-30.md)
2. **Primary 100** — [11-primary-interview-questions-100.md](../handbook/11-primary-interview-questions-100.md)
3. **Critical + High source maps** — [source-map/](source-map/README.md) (14 Critical + 8 High done)
4. **Unified topic pages** — [topics/](topics/README.md) (**Done**; Phase 3)
5. **Whiteboard flows** — [whiteboards/](whiteboards/README.md) (**Pending**; Phase 5)
6. **System evolution** — [06-system-design-handbook.md](../handbook/06-system-design-handbook.md)
7. **Advanced questions** — [09-interview-source-map-300.md](../handbook/09-interview-source-map-300.md)

Supporting depth: [04-technology-handbook.md](../handbook/04-technology-handbook.md), [onboarding/04-code-reading-order.md](../onboarding/04-code-reading-order.md)

---

## Navigation

Folder indexes:

- [source-map/README.md](source-map/README.md)
- [topics/README.md](topics/README.md)
- [whiteboards/README.md](whiteboards/README.md)
- [indexes/README.md](indexes/README.md)

### By Critical Class

Critical source-map pages are available (Phase 2). Index: [source-map/README.md](source-map/README.md).

| Class                         | Source                                                                                                                                           | Open Book page                                                                                            |
| ----------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------ | --------------------------------------------------------------------------------------------------------- |
| `Application`                 | [`Application.java`](../../src/main/java/com/tlbank/lending/domain/application/Application.java)                                                 | [domain/Application.md](source-map/domain/Application.md)                                                 |
| `ApplicationAppService`       | [`ApplicationAppService.java`](../../src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java)                | [application/ApplicationAppService.md](source-map/application/ApplicationAppService.md)                   |
| `ApplicationStatus`           | [`ApplicationStatus.java`](../../src/main/java/com/tlbank/lending/domain/application/ApplicationStatus.java)                                     | [domain/ApplicationStatus.md](source-map/domain/ApplicationStatus.md)                                     |
| `ApplicationRepositoryImpl`   | [`ApplicationRepositoryImpl.java`](../../src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationRepositoryImpl.java) | [infrastructure/ApplicationRepositoryImpl.md](source-map/infrastructure/ApplicationRepositoryImpl.md)     |
| `ApplicationEntity`           | [`ApplicationEntity.java`](../../src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationEntity.java)                 | [infrastructure/ApplicationEntity.md](source-map/infrastructure/ApplicationEntity.md)                     |
| `SecurityConfig`              | [`SecurityConfig.java`](../../src/main/java/com/tlbank/lending/security/config/SecurityConfig.java)                                              | [security/SecurityConfig.md](source-map/security/SecurityConfig.md)                                       |
| `IdempotencyService`          | [`IdempotencyService.java`](../../src/main/java/com/tlbank/lending/application/idempotency/IdempotencyService.java)                              | [application/IdempotencyService.md](source-map/application/IdempotencyService.md)                         |
| `RedisIdempotencyStore`       | [`RedisIdempotencyStore.java`](../../src/main/java/com/tlbank/lending/infrastructure/idempotency/RedisIdempotencyStore.java)                     | [infrastructure/RedisIdempotencyStore.md](source-map/infrastructure/RedisIdempotencyStore.md)             |
| `OtpAppService`               | [`OtpAppService.java`](../../src/main/java/com/tlbank/lending/application/otp/service/OtpAppService.java)                                        | [application/OtpAppService.md](source-map/application/OtpAppService.md)                                   |
| `ReviewAppService`            | [`ReviewAppService.java`](../../src/main/java/com/tlbank/lending/application/review/service/ReviewAppService.java)                               | [application/ReviewAppService.md](source-map/application/ReviewAppService.md)                             |
| `ReviewCase`                  | [`ReviewCase.java`](../../src/main/java/com/tlbank/lending/domain/review/ReviewCase.java)                                                        | [domain/ReviewCase.md](source-map/domain/ReviewCase.md)                                                   |
| `AuditAspect`                 | [`AuditAspect.java`](../../src/main/java/com/tlbank/lending/common/audit/AuditAspect.java)                                                       | [common/AuditAspect.md](source-map/common/AuditAspect.md)                                                 |
| `LocalDocumentStorageService` | [`LocalDocumentStorageService.java`](../../src/main/java/com/tlbank/lending/infrastructure/storage/LocalDocumentStorageService.java)             | [infrastructure/LocalDocumentStorageService.md](source-map/infrastructure/LocalDocumentStorageService.md) |
| `NotificationEventHandler`    | [`NotificationEventHandler.java`](../../src/main/java/com/tlbank/lending/infrastructure/event/NotificationEventHandler.java)                     | [infrastructure/NotificationEventHandler.md](source-map/infrastructure/NotificationEventHandler.md)       |

High-priority pages (**Done** Phase 4): [OtpRecord](source-map/domain/OtpRecord.md), [AuditLogWriter](source-map/common/AuditLogWriter.md), [GlobalExceptionHandler](source-map/presentation/GlobalExceptionHandler.md), [ApplicationApiController](source-map/presentation/ApplicationApiController.md), [WorkflowDomainService](source-map/domain/WorkflowDomainService.md), [CachedCardProductRepository](source-map/infrastructure/CachedCardProductRepository.md), [OtpCleanupScheduler](source-map/infrastructure/OtpCleanupScheduler.md), [ReportAppService](source-map/application/ReportAppService.md).

### By Topic

| Topic page                                                                     | Status   | Existing depth now                                                                                                                                                                 |
| ------------------------------------------------------------------------------ | -------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| [topics/01-architecture.md](topics/01-architecture.md)                         | **Done** | [02-architecture-handbook.md](../handbook/02-architecture-handbook.md), [0001-use-clean-architecture.md](../decisions/0001-use-clean-architecture.md)                              |
| [topics/02-request-lifecycle.md](topics/02-request-lifecycle.md)               | **Done** | [06-api-specification.md](../design/06-api-specification.md)                                                                                                                       |
| [topics/03-security.md](topics/03-security.md)                                 | **Done** | [07-security-design.md](../design/07-security-design.md), [0006-session-over-jwt.md](../decisions/0006-session-over-jwt.md)                                                        |
| [topics/04-domain-and-workflow.md](topics/04-domain-and-workflow.md)           | **Done** | [04-domain-model.md](../design/04-domain-model.md), [08-workflow-design.md](../design/08-workflow-design.md), [0002-use-ddd.md](../decisions/0002-use-ddd.md)                      |
| [topics/05-jpa-and-sql.md](topics/05-jpa-and-sql.md)                           | **Done** | [05-database-design.md](../design/05-database-design.md), [0007-h2-vs-sqlserver.md](../decisions/0007-h2-vs-sqlserver.md)                                                          |
| [topics/06-transactions.md](topics/06-transactions.md)                         | **Done** | [04-technology-handbook.md](../handbook/04-technology-handbook.md) (transactions chapters)                                                                                         |
| [topics/07-redis-idempotency.md](topics/07-redis-idempotency.md)               | **Done** | [0003-use-redis-idempotency.md](../decisions/0003-use-redis-idempotency.md)                                                                                                        |
| [topics/08-cache.md](topics/08-cache.md)                                       | **Done** | [12-cache-design.md](../design/12-cache-design.md)                                                                                                                                 |
| [topics/09-events-and-notifications.md](topics/09-events-and-notifications.md) | **Done** | [08-workflow-design.md](../design/08-workflow-design.md), [09-module-design.md](../design/09-module-design.md)                                                                     |
| [topics/10-audit-logging.md](topics/10-audit-logging.md)                       | **Done** | [11-audit-logging.md](../design/11-audit-logging.md)                                                                                                                               |
| [topics/11-testing.md](topics/11-testing.md)                                   | **Done** | [16-testing-strategy.md](../design/16-testing-strategy.md)                                                                                                                         |
| [topics/12-delivery-and-limitations.md](topics/12-delivery-and-limitations.md) | **Done** | [17-deployment-design.md](../design/17-deployment-design.md), [ci.yml](../../../.github/workflows/ci.yml), [0005-use-terraform-local.md](../decisions/0005-use-terraform-local.md) |

### By Business Flow

| Flow                       | Existing docs                                                                                                                            | Open Book                                                                                    |
| -------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------- |
| OTP → application → review | [03-business-feature-handbook.md](../handbook/03-business-feature-handbook.md), [08-workflow-design.md](../design/08-workflow-design.md) | [topics/04-domain-and-workflow.md](topics/04-domain-and-workflow.md); Critical classes above |
| Idempotent create          | [0003-use-redis-idempotency.md](../decisions/0003-use-redis-idempotency.md)                                                              | [topics/07-redis-idempotency.md](topics/07-redis-idempotency.md)                             |
| Document upload            | [15-file-upload-design.md](../design/15-file-upload-design.md)                                                                           | [LocalDocumentStorageService](source-map/infrastructure/LocalDocumentStorageService.md)      |
| Reports                    | [14-report-design.md](../design/14-report-design.md)                                                                                     | [ReportAppService](source-map/application/ReportAppService.md)                               |

### By Question ID

Until Open Book indexes exist:

- Full catalog: [09-interview-source-map-300.md](../handbook/09-interview-source-map-300.md) (anchors `Q001`…`Q300`)
- Core 30: [10-core-interview-questions-30.md](../handbook/10-core-interview-questions-30.md)
- Primary 100: [11-primary-interview-questions-100.md](../handbook/11-primary-interview-questions-100.md)

Planned: `indexes/by-question.md`, `indexes/core-30-map.md` (**Pending**, Phase 6).

### By Test

Representative tests (link to source now; Open Book citations **Pending** on class pages):

- [`ApplicationFlowIntegrationTest.java`](../../src/test/java/com/tlbank/lending/application/ApplicationFlowIntegrationTest.java)
- [`ApplicationIdempotencyIntegrationTest.java`](../../src/test/java/com/tlbank/lending/application/ApplicationIdempotencyIntegrationTest.java)
- [`SecurityIntegrationTest.java`](../../src/test/java/com/tlbank/lending/security/SecurityIntegrationTest.java)
- [`ApplicationTest.java`](../../src/test/java/com/tlbank/lending/domain/application/ApplicationTest.java)
- [`IdempotencyServiceTest.java`](../../src/test/java/com/tlbank/lending/application/idempotency/IdempotencyServiceTest.java)
- [`AuditAspectTest.java`](../../src/test/java/com/tlbank/lending/common/audit/AuditAspectTest.java)

Strategy: [16-testing-strategy.md](../design/16-testing-strategy.md) · [topics/11-testing.md](topics/11-testing.md)

### By Review Time

| Mode       | Entry                                 |
| ---------- | ------------------------------------- |
| 30 minutes | [30-Minute Review](#30-minute-review) |
| 2 hours    | [2-Hour Review](#2-hour-review)       |
| Full day   | [Full-Day Review](#full-day-review)   |

Planned: `indexes/study-paths.md` (**Pending**, Phase 6).

---

## Verification Labels

Labels used in handbook `09` and planned Open Book pages:

| Label                  | Meaning                                                                              |
| ---------------------- | ------------------------------------------------------------------------------------ |
| **Verified**           | Behavior confirmed against current source (and usually tests)                        |
| **Partially Verified** | Partly confirmed; do not overstate certainty                                         |
| **Documentation Only** | Supported by docs/ADR/SDD; not fully exercised as runtime proof in the question page |
| **Not Implemented**    | Absent in this repository — say so explicitly (no invented cloud/prod behavior)      |

### 中文筆記

- Verified：已對原始碼（或測試）核對
- Partially Verified：僅部分可核對，回答需保留彈性
- Documentation Only：主要依文件／ADR，勿說成已量測的執行證據
- Not Implemented：倉庫中不存在，不可外推為已上線能力

Hard constraints already verified in code/docs:

- Redis → idempotency store (`RedisIdempotencyStore`), not general cache/session
- Cache → in-memory (`InMemoryCacheStore`)
- Notifications → mock adapters
- Staging deploy → `workflow_dispatch` / `deploy-staging` in [ci.yml](../../../.github/workflows/ci.yml)
- Terraform → local provider under [infra/local/](../../../infra/local/README.md)

---

## Phase Status

| Phase                            | Scope                            | Status   |
| -------------------------------- | -------------------------------- | -------- |
| 1 Foundation and Navigation      | This README + folder READMEs     | **Done** |
| 2 Critical source-map pages      | 14 class pages                   | **Done** |
| 3 Unified topic pages            | 12 topic pages                   | **Done** |
| 4 High-priority source-map pages | 8 class pages                    | **Done** |
| 5 Whiteboards                    | 6 sketches                       | Pending  |
| 6 Indexes and cross-links        | 5 index files + docs index links | Pending  |
| 7 Selective JavaDoc              | Optional                         | Pending  |
| 8 Quality audit                  | Lint / claim check               | Pending  |
