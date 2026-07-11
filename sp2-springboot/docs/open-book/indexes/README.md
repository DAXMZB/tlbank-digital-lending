# Indexes

Navigation indexes for the Interview Open Book.

Parent: [Open Book README](../README.md) · Plan: [00-open-book-roadmap.md](../00-open-book-roadmap.md)

---

## Purpose

Indexes answer “where do I go?” without rewriting handbook content.

Planned indexes will list links by:

- **Class** — Critical/High source-map pages
- **Technology** — Spring Security, JPA, Redis idempotency, Docker, CI, …
- **Business feature** — OTP, application, review, reports, uploads, …
- **Priority** — Critical vs High vs topic-only
- **Question ID** — Q001–Q300 → Open Book page
- **Test** — integration/unit tests → class/topic pages
- **Configuration** — `application*.yml`, Compose, CI, Terraform local

Until Phase 6 ships, use the fallbacks below.

### 中文筆記

indexes 只做索引與交叉連結，不重複寫實作細節。題號對照以 handbook 09 為準。

---

## Planned Index Files (**Pending** — Phase 6)

| Planned file            | Role                                              |
| ----------------------- | ------------------------------------------------- |
| `by-class.md`           | Class → source-map / topic                        |
| `by-question.md`        | Q ID → Open Book target                           |
| `study-paths.md`        | 30 min / 2 h / full day                           |
| `core-30-map.md`        | Core 30 → pages                                   |
| `must-not-overclaim.md` | Redis≠cache, mock notify, local TF, manual deploy |

Also planned later (same phase or as sections): technology, business feature, test, and configuration views — either separate files or sections inside the five files above (keep total Open Book size in the roadmap cap).

---

## Use These Now

| Need                | Existing file                                                                                                                                                                                                             |
| ------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Question catalog    | [09-interview-source-map-300.md](../../handbook/09-interview-source-map-300.md)                                                                                                                                           |
| Core 30             | [10-core-interview-questions-30.md](../../handbook/10-core-interview-questions-30.md)                                                                                                                                     |
| Primary 100         | [11-primary-interview-questions-100.md](../../handbook/11-primary-interview-questions-100.md)                                                                                                                             |
| Quick bullets       | [12-interview-quick-review.md](../../handbook/12-interview-quick-review.md)                                                                                                                                               |
| File locator        | [08-file-reference-handbook.md](../../handbook/08-file-reference-handbook.md)                                                                                                                                             |
| Critical class list | [Open Book README — By Critical Class](../README.md#by-critical-class)                                                                                                                                                    |
| Topic list          | [topics/README.md](../topics/README.md)                                                                                                                                                                                   |
| Config / CI / TF    | [application.yml](../../../src/main/resources/application.yml), [docker-compose.yml](../../../docker-compose.yml), [ci.yml](../../../../.github/workflows/ci.yml), [infra/local/main.tf](../../../../infra/local/main.tf) |

---

## Status

No index body pages yet. Only this README (Phase 1).
