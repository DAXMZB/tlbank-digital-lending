# TLBank Cheat Sheet

30-minute pre-interview review. **One short card per topic** — open the linked handbook only if you blank.

| Full docs | Link |
| --- | --- |
| Overview | [00-project-overview.md](00-project-overview.md) |
| Wiki | [01-repository-handbook.md](01-repository-handbook.md) |
| Architecture index | [02-architecture-handbook.md](02-architecture-handbook.md) |
| Features | [03-business-feature-handbook.md](03-business-feature-handbook.md) |
| Technologies | [04-technology-handbook.md](04-technology-handbook.md) |
| Gaps | [20-maintenance-and-future-enhancement.md](20-maintenance-and-future-enhancement.md) |
| Interview answers | [05-interview-handbook.md](05-interview-handbook.md) |
| Scale path | [06-system-design-handbook.md](06-system-design-handbook.md) |
| File finder | [08-file-reference-handbook.md](08-file-reference-handbook.md) |

---

## A. 60-second project pitch

Fictional **credit card apply → OTP → documents → review → approve** backend. **Modular monolith**, Clean/Hexagonal + DDD-lite. Differentiator: **explicit state machine** + **Redis idempotency (dev only)**. Staging = **local Mac Compose**, not cloud. Detail: [00-project-overview.md](00-project-overview.md).

```text
INIT → OTP_VERIFIED → DOCUMENT_UPLOADED → SUBMITTED → UNDER_REVIEW → APPROVED|REJECTED
```

---

## B. Technology cards

Each card: **role in TLBank → remember → deep link**.

### Tech — Java 17

Runtime language; records/switch used where helpful. → [04 Ch.1](04-technology-handbook.md)

### Tech — Spring Boot 3.4

Single executable app, profiles `dev`/`staging`/`prod`. → [04 Ch.2](04-technology-handbook.md)

### Tech — Spring Web MVC

REST `/api/v1/**` + multipart uploads. → [04 Ch.3](04-technology-handbook.md)

### Tech — Spring Security

Session form login, BCrypt(12), `maximumSessions(1)`, RBAC. → [04 Ch.4](04-technology-handbook.md)

### Tech — BCrypt

Password hashing strength 12. → [04 Ch.5](04-technology-handbook.md)

### Tech — Spring Data JPA / Hibernate

Ports → `*RepositoryImpl` → JPA; `ddl-auto: validate`. → [04 Ch.6–7](04-technology-handbook.md)

### Tech — Flyway

Two trees: H2 `db/migration`, SQL Server `db/migration-sqlserver`. → [04 Ch.8](04-technology-handbook.md)

### Tech — H2

`dev` + tests, fast CI. → [04 Ch.9](04-technology-handbook.md)

### Tech — SQL Server

Staging Compose DB. → [04 Ch.10](04-technology-handbook.md)

### Tech — Thymeleaf

Staff/applicant server UI. → [04 Ch.11](04-technology-handbook.md)

### Tech — Bean Validation

`@Valid` on DTOs. → [04 Ch.12](04-technology-handbook.md)

### Tech — Spring AOP

`@Auditable` → audit log. → [04 Ch.13](04-technology-handbook.md)

### Tech — Application Events

Submit/approve/reject → handlers (in-process). → [04 Ch.14](04-technology-handbook.md)

### Tech — Scheduling

OTP cleanup, cache refresh, daily stats. → [04 Ch.15](04-technology-handbook.md)

### Tech — Async

Async audit writes. → [04 Ch.16](04-technology-handbook.md)

### Tech — Actuator

Health for ops/probes. → [04 Ch.17](04-technology-handbook.md)

### Tech — SpringDoc

Swagger on non-prod. → [04 Ch.18](04-technology-handbook.md)

### Tech — Redis

**Idempotency store only** (`dev`). Not cache/sessions. → [04 Ch.19](04-technology-handbook.md)

### Tech — In-memory cache

Products + parameters via `CacheStore`. → [04 Ch.20](04-technology-handbook.md)

### Tech — Local filesystem

Document uploads on disk. → [04 Ch.21](04-technology-handbook.md)

### Tech — Apache POI / iText

Excel + PDF daily reports. → [04 Ch.22–23](04-technology-handbook.md)

### Tech — Lombok / Maven / JaCoCo

Boilerplate, build, coverage. → [04 Ch.24–26](04-technology-handbook.md)

### Tech — JUnit / Spring Test

36 classes / 133 methods; `mvn clean verify`. → [04 Ch.27](04-technology-handbook.md)

### Tech — Docker / Compose

Multi-stage image; SQL Server + app stack. → [04 Ch.28–29](04-technology-handbook.md)

### Tech — GitHub Actions / GHCR / Trivy

Verify → scan (report-only) → push image. → [04 Ch.30–32](04-technology-handbook.md)

### Tech — Terraform local

IaC practice, **no cloud resources**. → [04 Ch.33](04-technology-handbook.md)

### Tech — Self-hosted runner

Manual Mac staging deploy. → [04 Ch.34](04-technology-handbook.md)

### Tech — SLF4J / Logback

MDC correlation id. → [04 Ch.35](04-technology-handbook.md)

---

## C. Business feature cards

Each card: **actor → entry → one-liner → deep link**.

### Feature — Login

Staff → `/login`, `/api/v1/auth/login` → session + audit. → [03 §1](03-business-feature-handbook.md) · [02 §1](02-architecture-handbook.md)

### Feature — OTP

Applicant → `/api/v1/otp/**` → send/verify; unlocks docs. → [03 §2](03-business-feature-handbook.md) · [02 §9–10](02-architecture-handbook.md)

### Feature — Card catalog

Applicant → `/api/v1/products` → enabled products (cached). → [03 §3](03-business-feature-handbook.md) · [02 §3](02-architecture-handbook.md)

### Feature — Card application

Applicant → `/api/v1/applications` → create/query; state machine. → [03 §4](03-business-feature-handbook.md) · [02 §4–5](02-architecture-handbook.md)

### Feature — Document upload

Applicant → `POST .../documents` → ID/income/residence. → [03 §5](03-business-feature-handbook.md) · [02 §6](02-architecture-handbook.md)

### Feature — Submit / cancel

Applicant → `.../actions/submit|cancel` → enter queue or abandon. → [03 §4](03-business-feature-handbook.md) · [02 §7–8](02-architecture-handbook.md)

### Feature — Review workflow

Reviewer → `/api/v1/review/**` → search, start, remarks. → [03 §6](03-business-feature-handbook.md) · [02 §11](02-architecture-handbook.md)

### Feature — Approval / reject

Reviewer → approve/reject → terminal app status + events. → [03 §7](03-business-feature-handbook.md) · [02 §11](02-architecture-handbook.md)

### Feature — Notification

System → event handlers / OTP path → mock SMS/email. → [03 §8](03-business-feature-handbook.md) · [02 §15](02-architecture-handbook.md)

### Feature — Audit log

Admin/system → `@Auditable` + login handlers → `audit_logs`. → [03 §9](03-business-feature-handbook.md) · [02 §13](02-architecture-handbook.md)

### Feature — Schedulers

System/Admin → cron + `/admin/schedulers/**` → cleanup/refresh/stats. → [03 §10](03-business-feature-handbook.md) · [02 §17](02-architecture-handbook.md)

### Feature — Reports

Admin → `/api/v1/reports/daily-statistics` → Excel/PDF. → [03 §11](03-business-feature-handbook.md) · [02 §16](02-architecture-handbook.md)

### Feature — User management

Admin → `/api/v1/admin/users` → create/enable users. → [03 §12](03-business-feature-handbook.md) · [02 §2](02-architecture-handbook.md)

### Feature — System parameters

Admin → `/api/v1/admin/system-parameters` → tunables + cache. → [03 §13](03-business-feature-handbook.md) · [02 §12](02-architecture-handbook.md)

### Feature — Cache management

Admin → `/api/v1/admin/cache/**` → evict/refresh. → [03 §14](03-business-feature-handbook.md) · [02 §14](02-architecture-handbook.md)

### Feature — Idempotency

API client → `Idempotency-Key` on create → Redis/memory store. → [03 §15](03-business-feature-handbook.md) · [02 §18](02-architecture-handbook.md)

---

## D. Decision flashcards

| Decision | One line | Deep |
| --- | --- | --- |
| Sessions not JWT | Browser staff UI | [05 §2](05-interview-handbook.md) |
| Redis ≠ cache | Idempotency only | [05 §4](05-interview-handbook.md) |
| Spring events | Isolate notify failures | [05 §5](05-interview-handbook.md) |
| Manual CD | Self-hosted Mac | [05 §7](05-interview-handbook.md) |
| H2 + SQL Server | Speed vs fidelity | [05 §6](05-interview-handbook.md) |
| Local Terraform | IaC practice, no cloud | [01 §13](01-repository-handbook.md) |

---

## E. Commands (memorize)

```bash
cd sp2-springboot
mvn spring-boot:run -Dspring-boot.run.profiles=dev
mvn clean verify
# coverage: target/site/jacoco/index.html
cp .env.example .env && docker-compose up -d
cd ../infra/local && terraform init && terraform plan
```

---

## F. Honest gaps (say these)

- No cloud prod; staging is local Mac  
- Applicant APIs `permitAll`  
- Mock notifications  
- Cache/sessions not Redis  
- Trivy report-only  
- Some events defined but unpublished  

List: [20-maintenance-and-future-enhancement.md](20-maintenance-and-future-enhancement.md) · [01 §18](01-repository-handbook.md)

---

## G. 30-minute timer

| Min | Focus |
| --- | --- |
| 0–5 | Pitch + state machine (A) |
| 5–15 | Tech cards you are weak on (B) |
| 15–25 | Feature cards + flashcards (C–D) |
| 25–30 | Gaps + commands (E–F); skim [05](05-interview-handbook.md) strong answers |

---

*Cheat sheet only — not a substitute for handbooks 00–04.*
