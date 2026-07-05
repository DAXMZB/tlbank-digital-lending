# 09 – Module Design

This document describes each of the platform's 16 functional modules: purpose, key classes, responsibilities,
and how it interacts with other modules. Cross-references point to the dedicated deep-dive documents
(`10`–`17`) where one exists.

---

## 1. Authentication & Authorization

**Purpose:** Authenticate internal users (`ADMIN`, `REVIEWER`) via session-based form login and enforce
role-based access control on every protected endpoint and page.

**Key classes:**

| Class | Responsibility |
| --- | --- |
| `security.config.SecurityConfig` | Wires the `SecurityFilterChain`: URL authorization matrix, form login, logout, session management, exception handling |
| `security.service.UserDetailsServiceImpl` | Loads a `UserEntity` by username, maps `user_roles` to Spring `GrantedAuthority` |
| `security.model.AuthenticatedUser` | Principal carrying `fullName` alongside standard Spring `User` fields |
| `security.handler.*` | `LoginSuccessHandler`, `LoginFailureHandler`, `LogoutSuccessHandlerImpl`, `CustomAuthenticationEntryPoint`, `CustomAccessDeniedHandler`, `SessionExpiredStrategy` |
| `security.util.LoginResponseMode` / `JsonResponseWriter` | Decide and produce JSON vs. browser-redirect responses for auth events |
| `security.filter.MdcLoggingFilter` | Attaches `requestId`/`username` to SLF4J MDC for every request |

**Interacts with:** User Management (loads `UserEntity`), Audit Log (writes `USER_LOGIN`/`USER_LOGIN_FAILED`/`USER_LOGOUT`).

**Full detail:** `07-security-design.md`.

---

## 2. User Management

**Purpose:** CRUD-style administration of internal user accounts (admins, reviewers) — separate from the
anonymous applicant flow, which has no account at all.

**Key classes:**

| Class | Responsibility |
| --- | --- |
| `domain.user.User` | Aggregate: `enable()`, `disable()`, `assignRole()`, `removeRole()`, `hasRole()` |
| `domain.user.UserId` / `Role` | Business identifier (`USR-XXXXXXXX`) and role enum |
| `domain.user.repository.UserRepository` | Port: `findById`, `save`, `existsByUsername`, `findAll` |
| `infrastructure.persistence.user.UserEntity` / `UserJpaRepository` / `UserRepositoryImpl` | JPA adapter |
| `application.user.service.UserAppService` | Use cases: `createUser`, `updateStatus`, `findAll`, `findById` |
| `presentation.api.v1.UserManagementApiController` | `GET/POST /api/v1/admin/users`, `GET/PUT .../{userId}` |
| `presentation.web.AdminController#users` | Thymeleaf user list page |

**Business rules:**

- Username must be unique (`DUPLICATE_USERNAME` → HTTP 409).
- New users default to `ROLE_USER` if no roles are supplied.
- Passwords are hashed with the shared `PasswordEncoder` bean (BCrypt, strength 12) before persistence — the
  domain `User` aggregate never sees a plaintext password, only the already-hashed value.

**Interacts with:** Authentication (provides credentials/roles), Audit Log (status changes are visible via
general audit, though not currently behind `@Auditable` — see `20-maintenance-and-future-enhancement.md`).

---

## 3. Credit Card Application

**Purpose:** The platform's core business capability — intake of a credit card application from an anonymous
applicant through to a final decision.

**Key classes:**

| Class | Responsibility |
| --- | --- |
| `domain.application.Application` | Aggregate root, full lifecycle behavior (see `04-domain-model.md`) |
| `domain.application.{Applicant, Address, MobileNumber, Email, DocumentInfo, WorkflowHistory}` | Value objects |
| `domain.application.ApplicationStatus` | State machine + transition validation |
| `domain.application.repository.ApplicationRepository` | Port |
| `infrastructure.persistence.application.*` | `ApplicationEntity` + embeddables (`ApplicantEmbeddable`, `AddressEmbeddable`) + child entities (`WorkflowHistoryEntity`, `ApplicationDocumentEntity`) + `ApplicationJpaRepository` + `ApplicationRepositoryImpl` |
| `application.application.service.ApplicationAppService` | Use cases: `createApplication`, `getApplication`, `uploadDocuments`, `submitApplication`, `cancelApplication`, `findAllEnabledProducts` |
| `presentation.api.v1.ApplicationApiController` / `CardProductApiController` | Public REST surface |
| `presentation.web.ApplicationWebController` | Public Thymeleaf flow: product list → apply form → OTP page → upload page → submit confirm → complete page |

**Business rules:** see `08-workflow-design.md` for the full state machine; see `15-file-upload-design.md`
for document validation; PII is always masked in outbound DTOs via `MaskingUtil` (`toMaskedApplicant`).

**Interacts with:** Card Product module (validates `cardProductId`, reads product for display), OTP module
(status transition trigger), File Upload module (`DocumentStorageService`), Workflow Engine
(`ApplicationStatus`/`WorkflowDomainService`), Credit Review (via `ApplicationSubmittedEvent`), Notification
(submission/approval/rejection messages), Audit Log (`@Auditable` on submit/cancel).

---

## 4. OTP Verification

**Purpose:** Verify that the applicant controls the mobile number they supplied, as a lightweight identity
check before allowing document upload.

**Key classes:**

| Class | Responsibility |
| --- | --- |
| `domain.otp.OtpRecord` | Aggregate: `verify(code, maxRetry, clock)`, `isExpired`, `markExpired`, `cancel` |
| `domain.otp.{OtpPurpose, OtpStatus, VerifyResult}` | Supporting enums |
| `domain.otp.repository.OtpRepository` | Port: `save`, `findLatestPendingByMobile`, `markExpiredBefore` |
| `infrastructure.persistence.otp.*` | `OtpRecordEntity`, `OtpJpaRepository`, `OtpRepositoryImpl` |
| `application.otp.service.OtpAppService` | Use cases: `sendOtp`, `verifyOtp` |
| `presentation.api.v1.OtpApiController` | `POST /api/v1/otp/actions/send`, `POST /api/v1/otp/actions/verify` |

**Business rules:**

- Sending a new OTP **cancels** any existing `PENDING` OTP for the same mobile number first (one active code
  per mobile at a time).

- 6-digit numeric code generated with `java.security.SecureRandom` (cryptographically strong, not
  `Math.random()`).

- Expiry window and max retry count are **not hardcoded** — read from `SystemParameterService`
  (`OTP.expire_minutes`, default 5; `OTP.max_retry`, default 3), so an administrator can tune them live
  without a deployment.

- `AuditContext.put("otpCode", otpCode)` stashes the generated code into the current request's audit detail
  *before* it gets masked for the notification log display (`AuditLogService.extractOtpCode`) — this exists
  purely so administrators can see the OTP value in the **Notification Logs** admin screen during
  development/demo without a real SMS gateway; this would be removed/gated behind a stricter permission in
  a real production system.

**Interacts with:** Credit Card Application (drives `INIT → OTP_VERIFIED`), System Parameter Management
(expiry/retry config), Notification (delivers the code), Scheduler (`OtpCleanupScheduler` expires stale
records), Audit Log (`@Auditable(OTP_SEND)`, `@Auditable(OTP_VERIFY_SUCCESS)`).

---

## 5. Credit Review

**Purpose:** Give internal Credit Reviewers a queue of submitted applications, decision tooling (approve /
reject with remark), and a running commentary trail per case.

**Key classes:**

| Class | Responsibility |
| --- | --- |
| `domain.review.ReviewCase` | Aggregate: `createFor(applicationId)`, `assign`, `startReview`, `approve`, `reject`, `addRemark` |
| `domain.review.{ReviewCaseId, ReviewStatus, ReviewRemark, ReviewCaseSearchCriteria}` | Identifier, state enum, remark value object, search filter record |
| `domain.review.repository.ReviewCaseRepository` | Port: `findById`, `save`, `search(criteria, pageable)` |
| `infrastructure.persistence.review.*` | `ReviewCaseEntity`, `ReviewRemarkEntity`, `ReviewCaseJpaRepository`, `ReviewCaseRepositoryImpl` |
| `application.review.service.ReviewAppService` | Use cases: `searchCases`, `getCaseDetail`, `startCaseReview`, `approveCase`, `rejectCase`, `addRemark` |
| `presentation.api.v1.ReviewApiController` (+ `review.*Request` DTOs) | REST surface |
| `presentation.web.ReviewController` | Thymeleaf review queue + case detail pages |

**Business rules:** see `08-workflow-design.md` §2–§3. A `ReviewCase` is **never created directly by a
reviewer** — it is always a side effect of `ApplicationSubmittedEvent`. Approving/rejecting a case
transparently drives the linked `Application` to the matching terminal status in the same transaction.

**Interacts with:** Credit Card Application (reads/writes linked `Application`, consumes
`ApplicationSubmittedEvent`), Notification (approval/rejection messages via `ApplicationApprovedEvent` /
`ApplicationRejectedEvent`), Audit Log (`@Auditable(APPLICATION_APPROVE)`, `@Auditable(APPLICATION_REJECT)`),
Card Product (resolves product name for case summaries).

---

## 6. Workflow Engine

**Purpose:** Centralize and enforce the application's status transition rules so that no code path can move
an `Application` (or `ReviewCase`) into an invalid state.

**Key classes:**

| Class | Responsibility |
| --- | --- |
| `domain.application.ApplicationStatus` | Declares `ALLOWED_TRANSITIONS` and exposes `canTransitionTo(next)` |
| `domain.service.WorkflowDomainService` | Stateless domain service: `validateTransition(from, to)`, throws `WorkflowException` |
| `common.exception.WorkflowException` | Specialized `BusinessException` mapped to HTTP 409 |
| `domain.application.Application#transitionTo` (private) | The single internal call site that both checks the rule and records `WorkflowHistory` |
| `domain.review.ReviewCase#transitionTo` (private) | The equivalent, simpler, inline transition table for review status |

This module is intentionally **not** a generic, configurable workflow/BPM engine (no external rule DSL, no
admin-editable transition graph) — it is a deliberately simple, compile-time-checked state machine, which is
the right level of complexity for this domain's two finite, well-understood lifecycles. A configurable engine
is listed as a possible (but not necessary) future enhancement.

**Interacts with:** Credit Card Application, Credit Review (both lifecycles depend on this module for their
transition rules).

---

## 7. System Parameter Management

**Purpose:** Externalize tunable business constants (OTP expiry/retry, cache TTL, upload size limit) as
database rows instead of hardcoded values or static config files, so they can be changed by an administrator
at runtime.

**Key classes:**

| Class | Responsibility |
| --- | --- |
| `domain.parameter.SystemParameter` | Aggregate: `updateValue(newValue)` (rejects blank) |
| `domain.parameter.SystemParameterRepository` | Port: `findByGroupAndKey`, `findAllByGroup`, `findAllEnabled`, `save` |
| `infrastructure.persistence.parameter.*` | `SystemParameterEntity`, `SystemParameterJpaRepository`, `SystemParameterRepositoryImpl` |
| `application.parameter.service.SystemParameterService` | Use cases: `getValue` (cached, throws if missing), `getValueOrDefault`, `getIntValue`, `findByGroup`, `findAllEnabled`, `update`, `refreshCache` |
| `presentation.api.v1.SystemParameterApiController` | `GET/PUT /api/v1/admin/system-parameters` |
| `presentation.web.AdminController#systemParameters` | Thymeleaf grouped parameter editor |

**Design note:** `getValue` is the only read path that consults the cache (`CacheKeys.systemParamKey`);
`getIntValue`/`getValueOrDefault` intentionally bypass the cache and read straight from the repository with
an in-code default, which is the right trade-off for low-traffic, rarely-missing config reads where simplicity
beats micro-optimizing every call site.

**Interacts with:** Cache Management (parameter values are cached and refreshed together with products),
OTP (`OTP.expire_minutes`, `OTP.max_retry`), File Upload (`UPLOAD.max.size.mb`), Scheduler
(`CacheRefreshScheduler` periodically reloads parameters into cache).

---

## 8. Cache Management

**Purpose:** Provide a small, dependency-light caching abstraction for read-heavy, rarely-changing data
(card products, system parameters), with admin visibility and manual refresh.

**Key classes:**

| Class | Responsibility |
| --- | --- |
| `infrastructure.cache.CacheStore` (port) / `InMemoryCacheStore` (adapter) | Thread-safe `ConcurrentHashMap`-backed store with per-entry TTL and a `@Scheduled` sweep of expired entries |
| `infrastructure.cache.CacheEntry<V>` | `(value, expiresAt)` record with `isExpired(clock)` |
| `infrastructure.cache.CacheKeys` | Centralized key-naming helpers (`systemParamKey`, `cardProductKey`) |
| `infrastructure.cache.CacheTtlProvider` | Resolves TTL from `SystemParameterRepository` (`CACHE.ttl_seconds`, default 6h) |
| `infrastructure.cache.CachedCardProductRepository` | `@Primary` caching decorator around the JPA-backed `CardProductRepository`, selected automatically over the plain `@Qualifier("cardProductRepositoryImpl")` bean |
| `application.cache.service.CacheManagementService` | Admin use cases: `refreshAll`, `refreshSystemParameters`, `refreshProducts`, `getStats` |
| `presentation.api.v1.CacheManagementApiController` | `POST .../cache/refresh*`, `GET .../cache/stats` |

Full detail: `12-cache-design.md`.

---

## 9. Notification

**Purpose:** Deliver SMS and email messages to applicants at key milestones, fully decoupled from real
delivery infrastructure via mock adapters.

**Key classes:**

| Class | Responsibility |
| --- | --- |
| `infrastructure.notification.{SmsSender, EmailSender}` (ports) | Delivery abstractions |
| `infrastructure.notification.{MockSmsSender, MockEmailSender}` | Log-only adapters, `@ConditionalOnProperty(tlbank.notification.mode=mock)` |
| `infrastructure.notification.NotificationTemplate` | Centralized `MessageFormat`-based message templates (OTP, submitted, approved, rejected) |
| `application.notification.service.{NotificationService, NotificationServiceImpl}` | Use-case-facing port + implementation, best-effort delivery (catches and logs failures, never throws) |
| `infrastructure.event.NotificationEventHandler` | Listens for `ApplicationSubmittedEvent` / `ApplicationApprovedEvent` / `ApplicationRejectedEvent` and dispatches the matching notification |
| `presentation.web.AdminController#notifications` / `presentation.api.v1.NotificationLogApiController` | Admin visibility into notification *attempts*, sourced from the audit log (there is no separate `notifications` table — see `11-audit-logging.md`) |

**Interacts with:** OTP (direct synchronous call from `OtpAppService`), Credit Card Application & Credit
Review (decoupled, event-driven), Audit Log (notification visibility is derived from audit entries, not a
dedicated notification log table).

---

## 10. Report Generation

**Purpose:** Produce downloadable daily operational statistics for administrators in Excel or PDF format.

**Key classes:**

| Class | Responsibility |
| --- | --- |
| `application.report.service.ReportDataService` | Aggregates `DailyStatisticsData` (total count, per-status counts, per-product counts) from `ApplicationJpaRepository`/`CardProductJpaRepository` for a given date |
| `application.report.service.ReportAppService` | Use case: `generateDailyStatisticsReport(request)` — delegates to the right generator by `ReportFormat`; `@Auditable(REPORT_EXPORT)` |
| `application.report.service.{DailyStatisticsData, ReportFormat}` | Aggregation result record, supported format enum (`EXCEL`, `PDF`) |
| `infrastructure.report.ExcelReportGenerator` | Apache POI: "Summary" sheet (totals + status breakdown with percentages) + "By Product" sheet |
| `infrastructure.report.PdfReportGenerator` | iText 7: title, totals, status table, product table, generation timestamp footer |
| `presentation.api.v1.ReportApiController` | `POST /api/v1/reports/daily-statistics` — returns the raw file with a `Content-Disposition` header |
| `presentation.web.AdminController#reports` | Thymeleaf report trigger page |

Full detail: `14-report-design.md`.

---

## 11. Scheduler

**Purpose:** Run periodic background maintenance and reporting tasks without manual intervention, while
still allowing an administrator to trigger any of them on demand.

**Key classes:**

| Class | Cron property | Responsibility |
| --- | --- | --- |
| `infrastructure.scheduler.OtpCleanupScheduler` | `tlbank.scheduler.otp-cleanup.cron` | Marks expired `PENDING` OTPs as `EXPIRED` (`OtpRepository.markExpiredBefore`) |
| `infrastructure.scheduler.CacheRefreshScheduler` | `tlbank.scheduler.cache-refresh.cron` | Reloads system parameter cache, evicts card product cache keys |
| `infrastructure.scheduler.DailyStatisticsScheduler` | `tlbank.scheduler.daily-stats.cron` | Builds and logs the previous day's statistics (does not itself generate a file — see Report Generation) |
| `presentation.api.v1.SchedulerApiController` | n/a | Manual trigger endpoints for all three jobs |

Full detail: `13-scheduler-design.md`.

---

## 12. Audit Log

**Purpose:** Provide a tamper-evident, queryable record of security and business events for compliance and
troubleshooting, with automatic PII masking.

**Key classes:**

| Class | Responsibility |
| --- | --- |
| `common.audit.{Auditable, AuditAspect}` | Declarative method-level audit logging via Spring AOP |
| `common.audit.AuditContext` | Thread-local supplemental detail accumulator, cleared at the end of every aspect invocation |
| `common.audit.AuditDetailBuilder` | Builds a sanitized, masked detail string from method arguments |
| `common.audit.{AuditLog, AuditLogRepository, AuditLogWriter}` | Entity, Spring Data repository, async `REQUIRES_NEW` writer |
| `common.audit.AuditIpResolver` | Client IP resolution honoring `X-Forwarded-For` |
| `application.audit.service.AuditLogService` | Admin query use cases: `search`, `searchNotificationAttempts` |
| `presentation.api.v1.{AuditLogApiController, NotificationLogApiController}` | Admin REST surfaces |

Full detail: `11-audit-logging.md`.

---

## 13. File Upload

**Purpose:** Accept, validate, and persist applicant-submitted identity/income/residence documents.

**Key classes:**

| Class | Responsibility |
| --- | --- |
| `domain.application.{DocumentType, DocumentInfo}` | Document category enum + value object metadata |
| `infrastructure.storage.DocumentStorageService` (port) / `LocalDocumentStorageService` (adapter) | Validation + filesystem persistence |
| `infrastructure.persistence.application.ApplicationDocumentEntity` | Persisted document metadata row |
| `application.application.service.ApplicationAppService#uploadDocuments` | Orchestrates validate → store → append to aggregate → persist |
| `presentation.api.v1.ApplicationApiController#uploadDocument` | `POST /applications/{id}/documents` (multipart) |

Full detail: `15-file-upload-design.md`.

---

## 14. Error Handling

**Purpose:** Translate every thrown exception, anywhere in the application, into a single consistent
`ApiResponse` JSON shape with the right HTTP status — so API consumers never need exception-type-specific
parsing logic.

**Key classes:**

| Class | Responsibility |
| --- | --- |
| `common.exception.ErrorCode` | Enum of every recognized business/system error |
| `common.exception.BusinessException` / `WorkflowException` | Exception hierarchy carrying an `ErrorCode` |
| `presentation.api.advice.GlobalExceptionHandler` | `@RestControllerAdvice` mapping exception types/codes → HTTP status + `ApiResponse` |
| `common.response.{ApiResponse, FieldErrorDetail, PageResponse}` | Response envelope types |

Full detail: `10-error-handling.md`.

---

## 15. Deployment

**Purpose:** Package and run the platform consistently across local development, staging, and production.

**Key artifacts:** `pom.xml` (Maven build, `staging` profile), `docker/app/Dockerfile` (multi-stage build),
`docker-compose.yml` (SQL Server + DB init + app, with health checks and named volumes),
`docker/sqlserver/init/*.sql` (first-run DB/login bootstrap), `application-{dev,staging,prod}.yml` (per-profile
configuration), `scripts/*.sh` (`prepare-dev.sh`, `start-dev.sh`, `verify.sh` developer convenience scripts).

Full detail: `17-deployment-design.md`.

---

## 16. Testing

**Purpose:** Give confidence in domain correctness, use-case orchestration, and HTTP/security wiring at the
appropriate level of the test pyramid, with fast feedback (H2, no external services required).

**Key conventions:** plain JUnit 5 for domain aggregates/value objects (no Spring context), Mockito-based
unit tests for application services, `@SpringBootTest`/`MockMvc`-based integration tests for end-to-end flows
(`ApplicationFlowIntegrationTest`, `ReviewFlowIntegrationTest`, `ApplicationIdempotencyIntegrationTest`,
`SecurityIntegrationTest`), JaCoCo coverage reporting with sensible exclusions (config, DTOs, entities, the
`@SpringBootApplication` class).

Full detail: `16-testing-strategy.md`.
