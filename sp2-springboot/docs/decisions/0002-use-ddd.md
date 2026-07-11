# ADR 0002 — Use DDD-lite Aggregates and a Domain State Machine

- **Status:** Accepted
- **Module:** `sp2-springboot`
- **Code anchors:** `domain.application.Application`, `domain.application.ApplicationStatus`, `domain.review.ReviewCase`, `domain.otp.OtpRecord`, `domain.service.WorkflowDomainService`

## Context

A credit-card application is a **lifecycle**, not a single CRUD row. Invalid jumps (for example `INIT` → `APPROVED`) must fail in the domain, not only in the UI.

See [../design/04-domain-model.md](../design/04-domain-model.md), [../design/08-workflow-design.md](../design/08-workflow-design.md), and [../handbook/01-repository-handbook.md](../handbook/01-repository-handbook.md) §4.

## Decision

Apply **DDD-lite** inside one bounded context (digital lending):

- Aggregates such as `Application`, `ReviewCase`, `User`, `CardProduct`, `OtpRecord`, `SystemParameter`
- Value objects (`MobileNumber`, `Email`, `Address`, …)
- Domain events (`ApplicationSubmittedEvent`, `ApplicationApprovedEvent`, `ApplicationRejectedEvent`, …)
- Explicit application status transitions enforced on the aggregate (`ApplicationStatus` allowed edges + `Application.transitionTo()` → `WorkflowException`)

## Alternatives Considered

| Alternative | Why not chosen |
| --- | --- |
| Anemic entities + service-only rules | Easy to bypass; harder to unit-test invariants |
| External BPMN / Camunda | Too heavy for current product scope |
| Data-driven admin-editable workflow | Not required while products share one flow |

## Trade-offs

| Gain | Cost |
| --- | --- |
| Illegal transitions fail consistently | Compile-time transition table is not admin-configurable |
| Workflow history can live with the aggregate | Some events exist but are not published yet (e.g. cancel / OTP generated) |
| Clear ubiquitous language for interviews | Review `start` remains web-oriented in places |

## Consequences

- Applicant path: `INIT` → `OTP_VERIFIED` → `DOCUMENT_UPLOADED` → `SUBMITTED` → `UNDER_REVIEW` → `APPROVED` \| `REJECTED` (plus early `CANCELLED` where allowed).
- Submit triggers review-case creation via an application event handler seam.
- Feature behavior is documented in [../handbook/03-business-feature-handbook.md](../handbook/03-business-feature-handbook.md).

## Future Improvements

- Publish or remove unused domain events ([../design/20-maintenance-and-future-enhancement.md](../design/20-maintenance-and-future-enhancement.md)).
- Prefer `@TransactionalEventListener(AFTER_COMMIT)` for review-case creation so handler failures do not roll back submit.
- Introduce a configurable workflow engine only if product flows diverge materially.
