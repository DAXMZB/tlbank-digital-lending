# Whiteboards

Short sketch packs for interview drawing and narration.

Parent: [Open Book README](../README.md) · Plan: [00-open-book-roadmap.md](../00-open-book-roadmap.md)

---

## Purpose

Each whiteboard page supports:

- **What to draw** — nodes and edges that match this repository
- **What to say** — short narration while drawing
- **Where it lives in code** — related classes or workflow jobs
- **Which questions it backs** — Q IDs from [09-interview-source-map-300.md](../../handbook/09-interview-source-map-300.md)

Sketches stay small. Long prose stays in `topics/` and handbooks.

### 中文筆記

白板頁只放可畫的節點與口述要點，對應真實類別或 CI job，避免畫出倉庫中不存在的雲端元件。

---

## Planned Page Shape

1. When to Draw This
2. Nodes and Edges
3. Mermaid or ASCII
4. What to Say While Drawing
5. Related Classes
6. Related Question IDs

---

## Planned Sketches (**Pending** — Phase 5)

| Planned page                    | Grounding today                                                                                                                                                                                                                                                                                                                                                           |
| ------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `01-layer-dependency.md`        | [0001-use-clean-architecture.md](../../decisions/0001-use-clean-architecture.md), [03-package-structure.md](../../design/03-package-structure.md)                                                                                                                                                                                                                         |
| `02-application-create-flow.md` | [`ApplicationApiController.java`](../../../src/main/java/com/tlbank/lending/presentation/api/v1/ApplicationApiController.java), [`ApplicationAppService.java`](../../../src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java), [`Application.java`](../../../src/main/java/com/tlbank/lending/domain/application/Application.java) |
| `03-security-filter-chain.md`   | [`SecurityConfig.java`](../../../src/main/java/com/tlbank/lending/security/config/SecurityConfig.java), [07-security-design.md](../../design/07-security-design.md)                                                                                                                                                                                                       |
| `04-idempotency-redis.md`       | [`IdempotencyService.java`](../../../src/main/java/com/tlbank/lending/application/idempotency/IdempotencyService.java), [`RedisIdempotencyStore.java`](../../../src/main/java/com/tlbank/lending/infrastructure/idempotency/RedisIdempotencyStore.java), [0003-use-redis-idempotency.md](../../decisions/0003-use-redis-idempotency.md)                                   |
| `05-review-approve-flow.md`     | [`ReviewAppService.java`](../../../src/main/java/com/tlbank/lending/application/review/service/ReviewAppService.java), [`ReviewCase.java`](../../../src/main/java/com/tlbank/lending/domain/review/ReviewCase.java), [08-workflow-design.md](../../design/08-workflow-design.md)                                                                                          |
| `06-ci-job-graph.md`            | [ci.yml](../../../../.github/workflows/ci.yml) — jobs `build-test`, `code-quality`, `dependency-scan`, `build-and-push-image`, `deploy-staging`                                                                                                                                                                                                                           |

Until sketches exist, use Mermaid/notes already in [02-architecture-handbook.md](../../handbook/02-architecture-handbook.md) and [diagrams/README.md](../../diagrams/README.md).

---

## Status

No sketch pages yet. Only this README (Phase 1).
