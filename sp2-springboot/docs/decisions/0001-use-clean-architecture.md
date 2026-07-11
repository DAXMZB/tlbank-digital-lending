# ADR 0001 — Use Clean / Hexagonal Architecture

- **Status:** Accepted
- **Module:** `sp2-springboot`
- **Code anchors:** `com.tlbank.lending.{presentation,application,domain,infrastructure,security,common}`

## Context

TLBank models a credit-card application and review workflow. Business rules (status transitions, OTP, review) must stay testable without Spring MVC, JPA, or Redis. The project is a portfolio modular monolith, not a microservice mesh.

See [../handbook/00-project-overview.md](../handbook/00-project-overview.md) and [../design/02-architecture-design.md](../design/02-architecture-design.md).

## Decision

Organize the codebase as **Clean / Hexagonal Architecture**:

- `domain` — aggregates, value objects, domain events, repository **ports**
- `application` — use cases / application services, DTOs, idempotency orchestration
- `infrastructure` — JPA adapters, cache, Redis idempotency, storage, schedulers, reports, event handlers
- `presentation` — REST and Thymeleaf controllers
- `security` / `common` — cross-cutting concerns

**Dependency rule:** outer layers depend inward; domain must not depend on Spring Web, JPA implementations, or infrastructure adapters.

## Alternatives Considered

| Alternative | Why not chosen |
| --- | --- |
| Classic Spring layering (controller → service → repository impl) | Couples workflow rules to persistence early |
| Microservices per feature | Overhead exceeds portfolio/single-team scope |
| Strict hexagonal with zero Spring types in domain | Valuable, but current code still has limited leaks (e.g. `@Service` near workflow helpers) |

## Trade-offs

| Gain | Cost |
| --- | --- |
| Domain unit tests without a database | More packages and manual mapping in `*RepositoryImpl` |
| Clear seams for Redis, file storage, notifications | Not a perfect boundary audit — see README design decisions |
| Interviewable architecture story | MapStruct is on the classpath but unused; mapping is manual |

## Consequences

- Feature navigation is by layer + package ([../handbook/02-architecture-handbook.md](../handbook/02-architecture-handbook.md), [../handbook/08-file-reference-handbook.md](../handbook/08-file-reference-handbook.md)).
- New integrations should implement a port (or existing port) rather than calling JPA from controllers.
- Event handlers under `infrastructure.event` are the natural place to later attach a message broker ([../design/20-maintenance-and-future-enhancement.md](../design/20-maintenance-and-future-enhancement.md)).

## Future Improvements

- Tighten domain purity where Spring types still appear near domain services/ports.
- Consider MapStruct or shared mappers if mapping duplication grows.
- Enforce dependency direction in CI (ArchUnit or similar) if the team expands.
