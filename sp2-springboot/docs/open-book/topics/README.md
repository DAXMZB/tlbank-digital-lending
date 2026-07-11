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

## Required Sections

1. One-Sentence Summary / 中文摘要
2. Why This Topic Matters
3. Current Implementation
4. Runtime Flow
5. Mermaid Diagram
6. Important Classes / Configuration / Tests
7. Design Decisions / Trade-offs / Alternatives
8. Production Considerations (Current / Partial / Planned)
9. Related ADRs / Related Interview Questions (exact Q IDs)
10. 30-Second / 2-Minute Explanation
11. Whiteboard Sketch
12. Common Follow-Up Questions / Common Mistakes
13. Current Limitations
14. Review Checklist

---

## Topic List (**Done** — Phase 3)

| Page                                                             | Theme                                    | Depth links                                                                                                                                             |
| ---------------------------------------------------------------- | ---------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------- |
| [01-architecture.md](01-architecture.md)                         | Hexagonal / dependency rule              | [02-architecture-handbook.md](../../handbook/02-architecture-handbook.md), [0001](../../decisions/0001-use-clean-architecture.md)                       |
| [02-request-lifecycle.md](02-request-lifecycle.md)               | HTTP → security → app → domain → DB      | [06-api-specification.md](../../design/06-api-specification.md)                                                                                         |
| [03-security.md](03-security.md)                                 | Session auth, roles, CSRF, MDC           | [07-security-design.md](../../design/07-security-design.md), [0006](../../decisions/0006-session-over-jwt.md)                                           |
| [04-domain-and-workflow.md](04-domain-and-workflow.md)           | DDD-lite, status machine, review         | [04-domain-model.md](../../design/04-domain-model.md), [0002](../../decisions/0002-use-ddd.md)                                                          |
| [05-jpa-and-sql.md](05-jpa-and-sql.md)                           | Entity mapping, Flyway, H2 vs SQL Server | [05-database-design.md](../../design/05-database-design.md), [0007](../../decisions/0007-h2-vs-sqlserver.md)                                            |
| [06-transactions.md](06-transactions.md)                         | Where `@Transactional` sits              | [04-technology-handbook.md](../../handbook/04-technology-handbook.md)                                                                                   |
| [07-redis-idempotency.md](07-redis-idempotency.md)               | Redis = idempotency, not cache/session   | [0003](../../decisions/0003-use-redis-idempotency.md)                                                                                                   |
| [08-cache.md](08-cache.md)                                       | In-memory cache only                     | [12-cache-design.md](../../design/12-cache-design.md)                                                                                                   |
| [09-events-and-notifications.md](09-events-and-notifications.md) | Spring events + mock SMS/email           | [08-workflow-design.md](../../design/08-workflow-design.md)                                                                                             |
| [10-audit-logging.md](10-audit-logging.md)                       | AOP audit + sanitization                 | [11-audit-logging.md](../../design/11-audit-logging.md)                                                                                                 |
| [11-testing.md](11-testing.md)                                   | Unit / IT / JaCoCo                       | [16-testing-strategy.md](../../design/16-testing-strategy.md)                                                                                           |
| [12-delivery-and-limitations.md](12-delivery-and-limitations.md) | Docker, CI, Terraform, limitations       | [0004](../../decisions/0004-use-github-actions.md), [0005](../../decisions/0005-use-terraform-local.md), [ci.yml](../../../../.github/workflows/ci.yml) |

Exact Q ID lists: [00-open-book-roadmap.md](../00-open-book-roadmap.md) §5 · evidence [09-interview-source-map-300.md](../../handbook/09-interview-source-map-300.md)

---

## Status

Phase 3 complete: 12 unified topic pages under this folder. Phases 4–8 remain Pending.
