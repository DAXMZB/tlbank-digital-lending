# 20 – Maintenance and Future Enhancement

## 1. Purpose

This document tracks known limitations and deliberate simplifications in the current implementation, and
lays out a realistic enhancement roadmap. Every item here was identified by reading the actual codebase, not
hypothesized — each is cross-referenced to the document where it is explained in context.

## 2. Known Issues / Technical Debt

| # | Issue | Where documented | Status |
|---|---|---|---|
| 1 | ~~`REVIEW_CASE_NOT_FOUND` resolves to HTTP `400` instead of `404`~~ | `10-error-handling.md` §3 | **Resolved (Sprint 17)** |
| 2 | ~~`SystemParameterService.update()` does not evict the corresponding cache entry~~ | `12-cache-design.md` §8 | **Resolved (Sprint 17)** |
| 3 | `SchedulingConfig` and `SchedulerConfig` both declare `@EnableScheduling` redundantly | `13-scheduler-design.md` §1 | Open — delete one of the two classes |
| 4 | A legacy, single-stage root-level `Dockerfile` (with non-English comments from an earlier iteration) exists alongside the canonical `docker/app/Dockerfile`; only the latter is referenced by `docker-compose.yml` | `17-deployment-design.md` §7 | Open — delete the root `Dockerfile` |
| 5 | `.env.example` documents `APP_OTP_EXPIRE_MINUTES`/`APP_OTP_MAX_RETRY` as environment overrides, but `OtpAppService` only reads these from `system_parameters` via `SystemParameterService` — there is no `@Value` binding to those env var names | `17-deployment-design.md` §7 | Open — wire env vars or remove from `.env.example` |
| 6 | `ExcelReportGenerator` and `PdfReportGenerator` duplicate the identical percentage-formatting logic | `14-report-design.md` §5 | Open — extract util if a third format is added |
| 7 | `domain.event.ApplicationCancelledEvent` and `OtpGeneratedEvent` are defined but never published by any current code path | `04-domain-model.md` §11 | Open — wire cancel event; decide on OTP event |
| 8 | `ReviewEventHandler.onApplicationSubmitted` runs as a default (same-transaction) `@EventListener` — a failure there would roll back the triggering application submission | `08-workflow-design.md` §6 | Open — consider `@TransactionalEventListener(AFTER_COMMIT)` |
| 9 | ~~`AuditAction.DOCUMENT_UPLOAD` is defined but no method is currently annotated `@Auditable`~~ | `11-audit-logging.md` §4 | **Resolved (Sprint 17)** |
| 10 | ~~Document type completeness enforced only at the web UI layer~~ | `15-file-upload-design.md` §2 | **Resolved (Sprint 17)** — `Application.submit()` guard |
| 11 | Application-level caching (`CacheStore`) is in-process only; only the idempotency store is Redis-backed today | `12-cache-design.md` §1 | Open — add `RedisCacheStore` when scaling |
| 12 | No Testcontainers-based test path exercises real SQL Server; all automated tests run against H2 | `16-testing-strategy.md` §3 | Open — separate CI stage |

None of these affect the platform's core correctness for its stated scope; they are exactly the kind of
calibrated, defensible trade-offs worth being able to discuss in a design review or interview.

## 3. Future Enhancement Candidates

### 3.1 Security

- **Token-based access for non-browser clients.** Add a second, independent Spring Security filter chain for
  a future `/api/v2/**` namespace using OAuth2/JWT, leaving the existing session-based chain untouched for
  the Thymeleaf UI (see `07-security-design.md` §8).
- **Configurable password policy** (complexity, rotation) for internal accounts, currently enforced only by
  `@Size(min = 8)` on `CreateUserRequest.password`.
- **Per-IP/per-mobile rate limiting** on `POST /api/v1/otp/actions/send` to prevent SMS-bombing abuse — there
  is currently no rate limit beyond "one pending OTP per mobile at a time."

### 3.2 Scalability

- **Horizontal scaling.** Moving `CacheStore` to Redis (see Known Issue #11) and externalizing HTTP sessions
  (e.g. Spring Session + Redis) would be the two changes needed to run more than one application instance
  behind a load balancer, given `maximumSessions(1)` currently depends on an in-memory `SessionRegistry`.
- **Async domain event dispatch.** Today, `ApplicationEventPublisher` dispatches synchronously within the
  same thread/transaction as the publishing use case. Introducing a real message broker (Kafka/RabbitMQ) at
  the event-handler seam (`ReviewEventHandler`, `NotificationEventHandler`) — already identified as the
  natural extension point in `02-architecture-design.md` §5 — would let review-case creation and
  notification delivery scale and fail independently of the originating request.

### 3.3 Business Capability

- **Real credit bureau / KYC integration**, replacing the current fully-manual review with an automated
  pre-screening step ahead of human review — the `domain.review` module already exposes this seam cleanly
  via `ReviewCase.assign`/`startReview`, which could be triggered by an automated scoring result instead of
  only by a human reviewer opening the queue.
- **Multi-currency / multi-region card products**, extending `CardProduct`/`CardType` and the application's
  address value object.
- **Configurable, admin-editable workflow engine** if the two-state-machine model (Application/ReviewCase)
  ever needs to support materially different lending products with different approval flows — today's
  compile-time `EnumMap` transition table (see `09-module-design.md` §6) is intentionally simple and would
  need to become data-driven only if that requirement actually materializes.

### 3.4 Observability

- **Distributed tracing** (OpenTelemetry) to replace/extend the current MDC-correlation-ID approach
  (`MdcLoggingFilter`) once the system is no longer a single process.
- **Metrics** (Micrometer + Prometheus) for cache hit/miss rate, OTP success/failure rate, and scheduler job
  duration — currently only visible via log lines.

### 3.5 Testing

- Testcontainers-based SQL Server integration test stage (Known Issue #12).
- Contract tests for the `ApiResponse` envelope shape itself, to catch any accidental breaking change to the
  response contract independent of any single endpoint's test.

## 4. Versioning & Change Management Policy

- **API versioning:** the REST API is already namespaced under `/api/v1`; any breaking change to a response
  shape or status-code mapping should be introduced under `/api/v2` rather than mutated in place, with both
  versions documented in `06-api-specification.md` until v1 is formally deprecated.
- **Database migrations:** every schema change is a new, additive Flyway migration file in **both**
  `db/migration` and `db/migration-sqlserver`, numbered sequentially after the highest existing version in
  each set — migrations are never edited after being applied to any shared environment.
- **Documentation upkeep:** whenever a module's behavior changes, the corresponding section of
  `09-module-design.md` (and any other affected document in this set) must be updated in the same change —
  this `/docs` folder is expected to stay synchronized with the code it describes, the same way the code's
  own `package-info.java`/Javadoc comments are expected to stay synchronized with their classes.
- **Sprint discipline for new features:** new functional capability should be planned the same way Sprints
  1–16 were (`19-cursor-implementation-roadmap.md`) — a named sprint with a clear business objective,
  technical objective, file list, and acceptance criteria — even after the initial build is complete, so the
  project remains easy for a new contributor (human or AI) to pick up from this document set alone.
