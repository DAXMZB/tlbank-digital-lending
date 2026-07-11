# TLBank Interview Handbook

Transforms repository knowledge into interview-ready answers. **Does not re-explain the system** — it frames decisions, trade-offs, and follow-ups. Read the linked handbooks for facts.

| Before this doc | Read |
| --- | --- |
| Project narrative | [00-project-overview.md](00-project-overview.md) |
| Wiki / topic map | [01-repository-handbook.md](01-repository-handbook.md) §19 |
| Feature → files | [02-architecture-handbook.md](02-architecture-handbook.md) |
| Feature depth | [03-business-feature-handbook.md](03-business-feature-handbook.md) |
| Tech depth | [04-technology-handbook.md](04-technology-handbook.md) |
| Honest gaps | [20-maintenance-and-future-enhancement.md](20-maintenance-and-future-enhancement.md) |
| Public talking points | [../../README.md](../../README.md) § Design Decisions |

---

## How to use this handbook

1. Pick a topic below.
2. Open the **Source** links for ground truth.
3. Practice the **Strong answer** out loud (60–90 seconds).
4. Prepare for the **Follow-ups**.

**Honesty rule:** Always state portfolio scope — local staging, mock notifications, no cloud production. Interviewers reward calibrated claims.

---

## 1. Architecture — Clean / Hexagonal + DDD-lite

**Source:** [01-repository-handbook.md](01-repository-handbook.md) §3–4 · [02-architecture-handbook.md](02-architecture-handbook.md) · [../design/02-architecture-design.md](../design/02-architecture-design.md)

### Architecture — Why this technology / approach

Keep lending workflow rules (state transitions, OTP, review) testable without Spring or a database. Ports let JPA, Redis, and file storage be swapped behind interfaces.

### Architecture — Trade-offs

| Gain | Cost |
| --- | --- |
| Domain unit tests without DB | More packages and mapping code |
| Clear dependency direction | Not a perfect hexagonal audit — some Spring types leak (`@Service` on `WorkflowDomainService`, `Pageable` on ports) |

### Architecture — Alternatives

| Alternative | When it fits |
| --- | --- |
| Classic layered Spring (controller → service → repository) | Smaller CRUD apps |
| Microservices per capability | Multiple teams, independent scale |
| Strict DDD with MapStruct + no Spring in domain | Larger product teams |

### Architecture — Production considerations

Modular monolith is fine until team or scale boundaries force extraction. Event handlers (`ReviewEventHandler`, `NotificationEventHandler`) are the natural seams for a broker later — see [20-maintenance-and-future-enhancement.md](20-maintenance-and-future-enhancement.md) §3.2.

### Architecture — Common interview questions

- Why not start with microservices?
- Where does the domain stop and infrastructure start?
- How do you prevent controllers from calling JPA directly?

### Architecture — Strong interview answer

> TLBank is a modular monolith with Clean/Hexagonal layering: presentation and infrastructure depend inward; domain holds aggregates and ports. That lets me unit-test the credit-card state machine without H2 or SQL Server. Adapters like `ApplicationRepositoryImpl` map entities to domain objects. I would not claim a perfect boundary — a few Spring types still appear near the domain — but the dependency rule is intentional and interviewable.

### Architecture — Follow-up questions

- Show me an invalid transition path in code.
- What would you extract first if the team grew?
- How would you enforce the dependency rule in CI?

---

## 2. Security — Session auth vs JWT

**Source:** [04-technology-handbook.md](04-technology-handbook.md) Ch.4 · [03-business-feature-handbook.md](03-business-feature-handbook.md) § Login · [../design/07-security-design.md](../design/07-security-design.md)

### Session auth — Why this technology

Staff portals are browser-based (Thymeleaf). Server sessions give simple logout, `maximumSessions(1)`, and CSRF on forms without client token storage.

### Session auth — Trade-offs

| Gain | Cost |
| --- | --- |
| Easy logout / session kill | Sticky or shared session store needed for multi-instance |
| Fits form login UX | Applicant APIs are `permitAll` today — not production-safe |

### Session auth — Alternatives

JWT / OAuth2 resource server for mobile or third-party APIs; keep session chain for admin UI (roadmap in [20-maintenance-and-future-enhancement.md](20-maintenance-and-future-enhancement.md) §3.1).

### Session auth — Production considerations

Externalize sessions (Spring Session + Redis), rate-limit OTP, tighten applicant endpoints, add password policy beyond `@Size(min=8)`.

### Session auth — Common interview questions

- Why not JWT everywhere?
- How does concurrent session control work?
- How is CSRF handled for `/api/**` vs web forms?

### Session auth — Strong interview answer

> I chose session-based Spring Security because the primary staff UX is server-rendered. Form login, BCrypt(12), 30-minute timeout, and one concurrent session match that model. JWT would be better for pure API clients; the maintenance doc already sketches a second filter chain for `/api/v2/**` without ripping out the UI chain. I would also call out that public applicant APIs are intentionally open for the demo and would be locked down in production.

### Session auth — Follow-up questions

- Where is `maximumSessions(1)` configured?
- What happens on concurrent login?
- How would you add OAuth2 without breaking Thymeleaf?

---

## 3. Workflow — Application state machine

**Source:** [03-business-feature-handbook.md](03-business-feature-handbook.md) § Card Application · [../design/08-workflow-design.md](../design/08-workflow-design.md)

### State machine — Why this approach

Lending intake is a lifecycle, not CRUD. Invalid jumps (e.g. `INIT` → `APPROVED`) must fail loudly with `WorkflowException`.

### State machine — Trade-offs

Compile-time `EnumMap` transitions are clear and testable, but not admin-editable. A configurable workflow engine is only justified if products diverge — see [20-maintenance-and-future-enhancement.md](20-maintenance-and-future-enhancement.md) §3.3.

### State machine — Alternatives

BPMN / Camunda; data-driven transition tables; saga orchestration across services.

### State machine — Production considerations

Audit every transition (`workflow_histories`), keep review case creation after submit, consider `@TransactionalEventListener(AFTER_COMMIT)` for review-case creation so handler failures do not roll back submit ([20-maintenance…](20-maintenance-and-future-enhancement.md) issue #8).

### State machine — Common interview questions

- Who enforces transitions — service or aggregate?
- How do you test illegal transitions?
- How does review status relate to application status?

### State machine — Strong interview answer

> Status lives on the `Application` aggregate. `ApplicationStatus.canTransitionTo()` defines edges; `transitionTo()` throws `WorkflowException` on illegal moves. Submit creates a `ReviewCase` via an event. That mirrors payment-style state machines: OTP is step-up auth, review is a fraud hold, approve/reject is the terminal decision.

### State machine — Follow-up questions

- Can you cancel after submit?
- Why is review `start` web-only today?
- Where is history persisted?

---

## 4. Redis — Idempotency only

**Source:** [01-repository-handbook.md](01-repository-handbook.md) §8 · [04-technology-handbook.md](04-technology-handbook.md) Ch.19 · [03-business-feature-handbook.md](03-business-feature-handbook.md) § Idempotency

### Redis — Why this technology

`POST /api/v1/applications` must be safely retryable. Redis TTL + lock (`SETNX`-style) fit idempotency keys better than the DB for this portfolio demo.

### Redis — Trade-offs

| Gain | Cost |
| --- | --- |
| Distributed-style replay semantics in `dev` | Not used for cache or sessions |
| Pluggable `IdempotencyStore` | Compose stack has no Redis; staging may lack store config |

### Redis — Alternatives

DB unique constraint on business key; idempotency table; Kafka exactly-once + outbox (heavier).

### Redis — Production considerations

Run Redis in every environment that accepts create retries; add `RedisCacheStore` and Spring Session when scaling horizontally ([20-maintenance…](20-maintenance-and-future-enhancement.md) §3.2).

### Redis — Common interview questions

- Why Redis if cache is still in-memory?
- What happens on key conflict vs in-progress lock?
- How do tests avoid needing Redis?

### Redis — Strong interview answer

> Redis is scoped narrowly: idempotency for application create via `IdempotencyService` and `RedisIdempotencyStore` when `tlbank.idempotency.store=redis`. Product/parameter cache stays `InMemoryCacheStore`. That is a deliberate teaching choice — I can explain distributed locks and TTL without pretending we already solved multi-node sessions. Tests use the in-memory store.

### Redis — Follow-up questions

- Show the header name and hash behavior.
- What is the TTL?
- What breaks if you run two app instances today?

---

## 5. Domain events — Notifications

**Source:** [04-technology-handbook.md](04-technology-handbook.md) Ch.14 · [03-business-feature-handbook.md](03-business-feature-handbook.md) § Notification

### Domain events — Why this approach

SMS/email must not roll back approve/submit if delivery fails. Publish events; handlers catch and log.

### Domain events — Trade-offs

In-process Spring events: no broker, no retry, no outbox. Mock senders only (`tlbank.notification.mode=mock`).

### Domain events — Alternatives

Transactional outbox + Kafka/RabbitMQ; async `@TransactionalEventListener`; external notification service.

### Domain events — Production considerations

Replace mocks with real providers; move handlers behind a queue; publish unused events (`ApplicationCancelledEvent`, `OtpGeneratedEvent`) or delete them ([20-maintenance…](20-maintenance-and-future-enhancement.md) issues #7–8).

### Domain events — Common interview questions

- Sync vs async events?
- How do you avoid dual-write problems?
- Why mock notifications?

### Domain events — Strong interview answer

> After approve/reject/submit we publish domain events. `NotificationEventHandler` sends mock SMS/email inside try/catch so delivery failure does not undo the business transaction. For production I would introduce an outbox and a broker at that same handler seam — the architecture already points there.

### Domain events — Follow-up questions

- Which events exist vs which are published?
- Is review-case creation on the same transaction as submit?
- How would you add retries?

---

## 6. Persistence — H2 vs SQL Server + Flyway

**Source:** [04-technology-handbook.md](04-technology-handbook.md) Ch.8–10 · [01-repository-handbook.md](01-repository-handbook.md) §15

### Persistence — Why this technology

H2 for fast `dev`/CI; SQL Server for Docker staging dialect fidelity; Flyway with two migration trees.

### Persistence — Trade-offs

Two trees must stay aligned manually. Automated tests do not use Testcontainers SQL Server yet ([20-maintenance…](20-maintenance-and-future-enhancement.md) issue #12).

### Persistence — Alternatives

Single Postgres everywhere; Testcontainers in CI; Liquibase.

### Persistence — Production considerations

One source of truth for schema; CI stage against real SQL Server; `ddl-auto: validate` stays.

### Persistence — Common interview questions

- Why two Flyway folders?
- Why `validate` not `update`?
- How do you keep H2 compatible?

### Persistence — Strong interview answer

> Dev and tests use H2 with MSSQL compatibility mode for speed. Staging uses SQL Server in Compose with a parallel Flyway set. Hibernate validates schema; we do not let it mutate production. The known gap is no Testcontainers SQL Server job yet — I would add that before claiming production readiness.

### Persistence — Follow-up questions

- Where are seed scripts?
- What happens on migration failure in staging?
- How would you zero-downtime migrate?

---

## 7. CI/CD — Manual staging deploy

**Source:** [01-repository-handbook.md](01-repository-handbook.md) §12 · [04-technology-handbook.md](04-technology-handbook.md) Ch.30–34 · [../../../.github/workflows/ci.yml](../../../.github/workflows/ci.yml)

### CI/CD — Why this approach

Staging is a home Mac. Self-hosted runner + `workflow_dispatch` avoids inbound SSH and keeps deploys intentional.

### CI/CD — Trade-offs

No auto-promote, blue/green, or canary. Trivy is report-only (`exit-code: 0`).

### CI/CD — Alternatives

Cloud staging + OIDC deploy; GitOps (Argo CD); required Trivy gate.

### CI/CD — Production considerations

Separate environments, secrets management, blocking security scans, health-check gated rollout.

### CI/CD — Common interview questions

- Why not deploy on every main push?
- What does CI guarantee today?
- What does Terraform actually provision?

### CI/CD — Strong interview answer

> CI always runs `mvn clean verify`, Trivy (advisory), and on `main` publishes to GHCR. CD is manual `workflow_dispatch` to a self-hosted macOS runner that restarts local Compose. Terraform under `infra/local` practices IaC with the local provider — it does not create cloud resources. That is an honest portfolio boundary, not a fake AWS story.

### CI/CD — Follow-up questions

- Show the deploy job triggers.
- Why Trivy does not fail the build?
- How would you promote to a real cloud?

---

## 8. Caching — In-process today

**Source:** [04-technology-handbook.md](04-technology-handbook.md) Ch.20 · [../design/12-cache-design.md](../design/12-cache-design.md)

### Caching — Why this approach

Card products and system parameters are read-heavy and small. An in-process `CacheStore` is enough for a single instance.

### Caching — Trade-offs

Cannot share cache across instances; restart loses cache; Redis already used only for idempotency.

### Caching — Alternatives

Spring Cache + Redis; Caffeine locally + Redis remote; CDN for public product catalog.

### Caching — Production considerations

`RedisCacheStore`, eviction on parameter update (already addressed for parameters in Sprint 17), hit/miss metrics ([20-maintenance…](20-maintenance-and-future-enhancement.md) §3.4).

### Caching — Common interview questions

- What is cached vs what is not?
- How do you invalidate?
- Why not Redis cache already?

### Caching — Strong interview answer

> We cache products and parameters in-process behind `CacheStore`, with admin APIs to evict/refresh. Redis is reserved for idempotency so the demo stays clear about each tool’s job. Horizontal scale requires moving cache (and sessions) to Redis — that is documented as the next scalability step, not something I pretend is done.

### Caching — Follow-up questions

- Which scheduler refreshes cache?
- What is `@Primary` on `CachedCardProductRepository`?
- How would you measure hit rate?

---

## 9. Testing strategy

**Source:** [01-repository-handbook.md](01-repository-handbook.md) §9 · [../design/16-testing-strategy.md](../design/16-testing-strategy.md) · [04-technology-handbook.md](04-technology-handbook.md) Ch.27

### Testing — Why this approach

Pyramid: domain unit tests for rules; service tests with mocks; Spring Boot integration for flows/security/idempotency. Counts: 36 classes / 133 methods (`mvn clean verify`).

### Testing — Trade-offs

H2-only automated path; no load tests; MapStruct unused so mapping bugs need integration coverage.

### Testing — Alternatives

Testcontainers; contract tests; mutation testing.

### Testing — Production considerations

Add SQL Server Testcontainers stage; keep JaCoCo exclusions honest; add OTP rate-limit tests when implemented.

### Testing — Common interview questions

- How do you test the state machine?
- How is security tested?
- What does CI run?

### Testing — Strong interview answer

> Illegal transitions are unit-tested on the aggregate. End-to-end applicant and review paths are Spring Boot integration tests. Idempotency has a dedicated integration test using the in-memory store. CI is `mvn clean verify` — that is the quality gate I trust today.

### Testing — Follow-up questions

- What does JaCoCo exclude and why?
- How would you test Redis idempotency in CI?
- Show a failing transition test name.

---

## 10. Opening / closing scripts

### "Walk me through the project" (90 seconds)

1. Business: digital credit-card apply → OTP → docs → review → approve ([00-project-overview.md](00-project-overview.md)).
2. Architecture: modular monolith, clean layers ([01-repository-handbook.md](01-repository-handbook.md) §3).
3. Differentiator: explicit state machine + Redis idempotency scope ([03-business-feature-handbook.md](03-business-feature-handbook.md)).
4. Delivery: Maven CI, GHCR, manual Mac staging ([01-repository-handbook.md](01-repository-handbook.md) §12).
5. Honesty: portfolio gaps in [20-maintenance-and-future-enhancement.md](20-maintenance-and-future-enhancement.md).

### "What would you do next in production?"

Point at [20-maintenance-and-future-enhancement.md](20-maintenance-and-future-enhancement.md) and [06-system-design-handbook.md](06-system-design-handbook.md): Redis cache + sessions, broker/outbox, real notifications, observability, cloud/K8s, Testcontainers SQL Server.

### Payment-domain analogy (quick)

| TLBank | Payments |
| --- | --- |
| Application lifecycle | Transaction state machine |
| OTP | Step-up / 3DS |
| ReviewCase | Fraud / manual review |
| Audit log | Ledger |
| Idempotency-Key | Payment idempotency |

Detail: [01-repository-handbook.md](01-repository-handbook.md) §19.3.

---

*Interview framing only. Implementation truth lives in handbooks 00–04 and 20.*
