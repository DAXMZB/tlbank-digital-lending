# Topics

Unified theme pages for TLBank interview prep.

Parent: [Open Book README](../README.md) · Plan: [00-open-book-roadmap.md](../00-open-book-roadmap.md)

---

## Purpose

Each topic page **combines architecture and interview preparation** in one file:

- what the repository implements now
- runtime flow
- design decisions and trade-offs
- alternatives and production considerations
- related classes and tests
- exact question IDs
- 30-second and 2-minute explanations
- whiteboard sketch hints
- common follow-ups and current limitations

There is **no** parallel `architecture/` + `interview/` pair for the same theme.

### 中文筆記

topics 把「現況實作」與「面試怎麼講」寫在同一頁，避免同一主題兩份文件漂移。

---

## Required Sections (planned)

1. Current Implementation
2. Runtime Flow
3. Design Decisions
4. Trade-offs
5. Alternatives
6. Production Considerations
7. Related Classes
8. Related Tests
9. Related Question IDs
10. 30-Second Explanation
11. 2-Minute Explanation
12. Whiteboard Sketch
13. Common Follow-Up Questions
14. Current Limitations

---

## Planned Topic List (**Pending** — Phase 3)

| Planned page | Use existing depth now |
| --- | --- |
| `01-architecture.md` | [02-architecture-handbook.md](../../handbook/02-architecture-handbook.md), [0001-use-clean-architecture.md](../../decisions/0001-use-clean-architecture.md), [02-architecture-design.md](../../design/02-architecture-design.md) |
| `02-request-lifecycle.md` | [06-api-specification.md](../../design/06-api-specification.md) |
| `03-security.md` | [07-security-design.md](../../design/07-security-design.md), [0006-session-over-jwt.md](../../decisions/0006-session-over-jwt.md) |
| `04-domain-and-workflow.md` | [04-domain-model.md](../../design/04-domain-model.md), [08-workflow-design.md](../../design/08-workflow-design.md), [0002-use-ddd.md](../../decisions/0002-use-ddd.md), [03-business-feature-handbook.md](../../handbook/03-business-feature-handbook.md) |
| `05-jpa-and-sql.md` | [05-database-design.md](../../design/05-database-design.md), [0007-h2-vs-sqlserver.md](../../decisions/0007-h2-vs-sqlserver.md) |
| `06-transactions.md` | [04-technology-handbook.md](../../handbook/04-technology-handbook.md) |
| `07-redis-idempotency.md` | [0003-use-redis-idempotency.md](../../decisions/0003-use-redis-idempotency.md) |
| `08-cache.md` | [12-cache-design.md](../../design/12-cache-design.md) |
| `09-events-and-notifications.md` | [08-workflow-design.md](../../design/08-workflow-design.md), [09-module-design.md](../../design/09-module-design.md) |
| `10-audit-logging.md` | [11-audit-logging.md](../../design/11-audit-logging.md) |
| `11-testing.md` | [16-testing-strategy.md](../../design/16-testing-strategy.md) |
| `12-delivery-and-limitations.md` | [17-deployment-design.md](../../design/17-deployment-design.md), [13-scheduler-design.md](../../design/13-scheduler-design.md), [ci.yml](../../../../.github/workflows/ci.yml), [0004-use-github-actions.md](../../decisions/0004-use-github-actions.md), [0005-use-terraform-local.md](../../decisions/0005-use-terraform-local.md), [infra/local/README.md](../../../../infra/local/README.md) |

Exact Q ID lists: [00-open-book-roadmap.md](../00-open-book-roadmap.md) §5 · evidence [09-interview-source-map-300.md](../../handbook/09-interview-source-map-300.md)

---

## Status

No topic body pages yet. Only this README (Phase 1).
