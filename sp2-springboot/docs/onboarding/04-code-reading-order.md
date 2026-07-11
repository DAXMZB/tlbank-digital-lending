# Code Reading Order

A suggested path through `com.tlbank.lending` for a new backend engineer. Use [../handbook/08-file-reference-handbook.md](../handbook/08-file-reference-handbook.md) as the index; this page is the **sequence**.

## Pass 1 — Skeleton (45–60 minutes)

| Order | Open | Why |
| --- | --- | --- |
| 1 | `TlbankLendingApplication.java` | Boot entry |
| 2 | `presentation/` + `application/` + `domain/` + `infrastructure/` package tree | Layer map — [../decisions/0001-use-clean-architecture.md](../decisions/0001-use-clean-architecture.md) |
| 3 | `security.config.SecurityConfig` | AuthN/Z matrix — [../decisions/0006-session-over-jwt.md](../decisions/0006-session-over-jwt.md) |
| 4 | `domain.application.ApplicationStatus` | Allowed transitions |
| 5 | `domain.application.Application` | Aggregate behavior / `transitionTo` |
| 6 | `application.application.service.ApplicationAppService` | Use-case orchestration |

Companion: [../handbook/02-architecture-handbook.md](../handbook/02-architecture-handbook.md) § Master Feature Index.

## Pass 2 — Applicant happy path

Follow one create → OTP → upload → submit path:

| Step | Classes / packages |
| --- | --- |
| HTTP in | `presentation.api.v1.ApplicationApiController`, `OtpApiController` |
| Idempotency | `application.idempotency.IdempotencyService` + `infrastructure.idempotency.*` |
| Persistence | `infrastructure.persistence.application.ApplicationRepositoryImpl` |
| OTP | `application.otp.service.OtpAppService`, `domain.otp.OtpRecord` |
| Documents | `infrastructure.storage.LocalDocumentStorageService` |
| Submit side effect | `infrastructure.event.ReviewEventHandler` |

Business narrative: [../handbook/03-business-feature-handbook.md](../handbook/03-business-feature-handbook.md).  
Workflow rules: [../design/08-workflow-design.md](../design/08-workflow-design.md).

## Pass 3 — Reviewer path

| Focus | Open |
| --- | --- |
| API / web | `ReviewApiController`, `ReviewController` |
| Service | `application.review.service.ReviewAppService` |
| Domain | `domain.review.ReviewCase` |
| Events | approve/reject → `NotificationEventHandler` |

Architecture § Credit Review: [../handbook/02-architecture-handbook.md](../handbook/02-architecture-handbook.md).

## Pass 4 — Cross-cutting

| Concern | Start here | Doc |
| --- | --- | --- |
| Errors | `presentation.api.advice.GlobalExceptionHandler` | [../design/10-error-handling.md](../design/10-error-handling.md) |
| Audit | `common.audit`, `AuditLogService` | [../design/11-audit-logging.md](../design/11-audit-logging.md) |
| Cache | `InMemoryCacheStore`, `CacheManagementService` | [../design/12-cache-design.md](../design/12-cache-design.md) |
| Schedulers | `infrastructure.scheduler.*` | [../design/13-scheduler-design.md](../design/13-scheduler-design.md) |
| Reports | `ReportAppService`, `ExcelReportGenerator` / `PdfReportGenerator` | [../design/14-report-design.md](../design/14-report-design.md) |

## Pass 5 — Tests as documentation

Read these before changing behavior:

- Domain: `ApplicationTest`, `OtpRecordTest`, `ReviewCaseTest`, `WorkflowDomainServiceTest`
- Flow: `ApplicationFlowIntegrationTest`, `ReviewFlowIntegrationTest`
- Security: `SecurityIntegrationTest`
- Idempotency: `ApplicationIdempotencyIntegrationTest`

Strategy: [../design/16-testing-strategy.md](../design/16-testing-strategy.md).

## Pass 6 — Ops touchpoints

| Area | Path |
| --- | --- |
| Profiles | `src/main/resources/application*.yml` |
| Migrations | `db/migration`, `db/migration-sqlserver` |
| Container | `docker/app/Dockerfile`, `docker-compose.yml` |
| CI/CD | `../../../.github/workflows/ci.yml` |
| Terraform practice | `../../../infra/local/` |

## Suggested weekly depth

After the passes above, pick **one** vertical per day from [../handbook/02-architecture-handbook.md](../handbook/02-architecture-handbook.md) (OTP, review, idempotency, cache, reports) and trace controller → service → domain → adapter.

Interview prep (optional): [../handbook/05-interview-handbook.md](../handbook/05-interview-handbook.md) · [../handbook/07-cheat-sheet.md](../handbook/07-cheat-sheet.md).

## Back to onboarding index

→ [README.md](README.md)
