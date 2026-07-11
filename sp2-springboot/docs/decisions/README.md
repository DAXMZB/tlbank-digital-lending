# Architecture Decision Records (ADRs)

Lightweight decisions that shaped `sp2-springboot`. Each ADR records **context, choice, alternatives, trade-offs, consequences, and future work** without re-documenting the full system.

## Index

| ADR | Title |
| --- | --- |
| [0001](0001-use-clean-architecture.md) | Use Clean / Hexagonal Architecture |
| [0002](0002-use-ddd.md) | Use DDD-lite aggregates and a domain state machine |
| [0003](0003-use-redis-idempotency.md) | Use Redis for application-create idempotency |
| [0004](0004-use-github-actions.md) | Use GitHub Actions for CI and manual CD |
| [0005](0005-use-terraform-local.md) | Use Terraform with the local provider |
| [0006](0006-session-over-jwt.md) | Prefer session authentication over JWT |
| [0007](0007-h2-vs-sqlserver.md) | Use H2 for dev/tests and SQL Server for staging |

## Related reading

- Design rationale: [../design/02-architecture-design.md](../design/02-architecture-design.md)
- Interview framing: [../handbook/05-interview-handbook.md](../handbook/05-interview-handbook.md)
- Public summary: [../../README.md](../../README.md) § Design Decisions
- Gaps / next steps: [../design/20-maintenance-and-future-enhancement.md](../design/20-maintenance-and-future-enhancement.md)

## Conventions

- Number ADRs sequentially (`0008-…` next).
- Link to real packages, classes, or config files under `sp2-springboot/`.
- Mark status as **Accepted** unless superseded.
- Do not invent cloud or production behavior that the repository does not implement.
