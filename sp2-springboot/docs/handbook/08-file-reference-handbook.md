# TLBank File Reference Handbook

Navigation guide to locate source quickly. **Does not re-document behavior** — each row points at the authoritative handbook section.

Base package: `com.tlbank.lending`  
Root: `sp2-springboot/src/main/java/com/tlbank/lending/`

| Companion | Use for |
| --- | --- |
| [02-architecture-handbook.md](02-architecture-handbook.md) | Full flows, Mermaid, file lists per feature |
| [03-business-feature-handbook.md](03-business-feature-handbook.md) | Business rules, security, interview Qs |
| [04-technology-handbook.md](04-technology-handbook.md) | Tech-centric file lists |
| [01-repository-handbook.md](01-repository-handbook.md) | Repo map, ports index |
| [00-project-overview.md](00-project-overview.md) | Orientation |
| [20-maintenance-and-future-enhancement.md](20-maintenance-and-future-enhancement.md) | Known gaps |

---

## 1. Layer → package map

| Layer | Package prefix | Purpose |
| --- | --- | --- |
| Boot | `TlbankLendingApplication` | Entry |
| Presentation | `presentation.api.v1`, `presentation.web`, `presentation.api.advice` | REST, Thymeleaf, errors |
| Application | `application.<feature>.service`, `application.dto`, `application.idempotency` | Use cases |
| Domain | `domain.<aggregate>`, `domain.event`, `domain.service` | Rules, ports, events |
| Infrastructure | `infrastructure.persistence`, `cache`, `idempotency`, `notification`, `storage`, `scheduler`, `report`, `event` | Adapters |
| Security | `security.config`, `security.service`, `security.handler` | AuthN/Z |
| Common | `common.audit`, `common.config`, `common.exception`, `common.response` | Cross-cutting |

Folder tree: [02-architecture-handbook.md](02-architecture-handbook.md) § Repository Folder Map.

---

## 2. Business feature → code locator

Path shorthand: packages under `com.tlbank.lending`. Deep dive = architecture § linked.

### FileRef — Login

| Field | Location |
| --- | --- |
| Purpose | Staff session authentication |
| Entry controller | `presentation.web.AuthController`; Security form login |
| Related packages | `security.*`, `presentation.web` |
| Related classes | `SecurityConfig`, `UserDetailsServiceImpl`, `LoginSuccessHandler`, `LoginFailureHandler`, `LogoutSuccessHandlerImpl` |
| Configuration | `SecurityConfig.java`, `application.yml` (`server.servlet.session.timeout`) |
| Execution flow | [02 §1 Authentication Execution Flow](02-architecture-handbook.md) |
| Dependencies | `UserJpaRepository`, audit writers |
| Deep | [03 § Login](03-business-feature-handbook.md) |

### FileRef — User Management

| Field | Location |
| --- | --- |
| Purpose | Admin create/enable internal users |
| Entry controller | `presentation.api.v1.UserManagementApiController`, `presentation.web.AdminController` |
| Related packages | `application.user.service`, `domain.user`, `infrastructure.persistence.user` |
| Related classes | `UserAppService`, `User`, `UserRepository`, `UserRepositoryImpl` |
| Configuration | Security URL/`@PreAuthorize` ADMIN |
| Execution flow | [02 §2](02-architecture-handbook.md) |
| Dependencies | `UserRepository` → JPA |
| Deep | [03 § User Management](03-business-feature-handbook.md) |

### FileRef — Card Product Catalog

| Field | Location |
| --- | --- |
| Purpose | List enabled card products |
| Entry controller | `presentation.api.v1.CardProductApiController`, `presentation.web.ApplicationWebController` |
| Related packages | `domain.product`, `application.application.service`, `infrastructure.persistence.product`, `infrastructure.cache` |
| Related classes | `CardProduct`, `CardProductRepository`, `CachedCardProductRepository` (`@Primary`), `ApplicationAppService` |
| Configuration | Cache keys via `CacheStore` |
| Execution flow | [02 §3](02-architecture-handbook.md) |
| Dependencies | `CardProductRepository` → cache decorator → JPA |
| Deep | [03 § Card Product Catalog](03-business-feature-handbook.md) |

### FileRef — Application Create / Query

| Field | Location |
| --- | --- |
| Purpose | Start and read applications |
| Entry controller | `presentation.api.v1.ApplicationApiController`, `presentation.web.ApplicationWebController` |
| Related packages | `application.application.service`, `application.idempotency`, `domain.application` |
| Related classes | `ApplicationAppService`, `Application`, `ApplicationStatus`, `ApplicationRepository`, `IdempotencyService` |
| Configuration | `tlbank.idempotency.*` in YAML profiles |
| Execution flow | [02 §4–5](02-architecture-handbook.md) |
| Dependencies | `ApplicationRepository`, `IdempotencyStore`, product port |
| Deep | [03 § Card Application](03-business-feature-handbook.md) |

### FileRef — Document Upload

| Field | Location |
| --- | --- |
| Purpose | Attach supporting documents |
| Entry controller | `presentation.api.v1.ApplicationApiController` |
| Related packages | `domain.application`, `infrastructure.storage`, `application.application.service` |
| Related classes | `ApplicationAppService`, `DocumentInfo`, `LocalDocumentStorageService` |
| Configuration | Upload directory settings in YAML |
| Execution flow | [02 §6](02-architecture-handbook.md) |
| Dependencies | File storage + `ApplicationRepository` |
| Deep | [03 § Document Upload](03-business-feature-handbook.md) · [../design/15-file-upload-design.md](../design/15-file-upload-design.md) |

### FileRef — Application Submit / Cancel

| Field | Location |
| --- | --- |
| Purpose | Enter review queue or abandon |
| Entry controller | `presentation.api.v1.ApplicationApiController`, web apply controllers |
| Related packages | `domain.application`, `domain.event`, `infrastructure.event` |
| Related classes | `ApplicationAppService`, `ApplicationSubmittedEvent`, `ReviewEventHandler` |
| Configuration | None specific |
| Execution flow | [02 §7–8](02-architecture-handbook.md) |
| Dependencies | Domain transitions → event → review case |
| Deep | [03 § Card Application](03-business-feature-handbook.md) · [../design/08-workflow-design.md](../design/08-workflow-design.md) |

### FileRef — OTP Send / Verify

| Field | Location |
| --- | --- |
| Purpose | Mobile OTP identity step |
| Entry controller | `presentation.api.v1.OtpApiController`, `presentation.web.ApplicationWebController` |
| Related packages | `application.otp.service`, `domain.otp`, `infrastructure.persistence.otp`, `infrastructure.notification` |
| Related classes | `OtpAppService`, `OtpRecord`, `OtpRepository`, `NotificationServiceImpl` |
| Configuration | OTP params via `system_parameters` / `SystemParameterService` |
| Execution flow | [02 §9–10](02-architecture-handbook.md) |
| Dependencies | `OtpRepository`, `ApplicationRepository`, notification sender |
| Deep | [03 § OTP Verification](03-business-feature-handbook.md) |

### FileRef — Credit Review / Approval

| Field | Location |
| --- | --- |
| Purpose | Reviewer queue, approve/reject |
| Entry controller | `presentation.api.v1.ReviewApiController`, `presentation.web.ReviewController` |
| Related packages | `application.review.service`, `domain.review`, `domain.application`, `infrastructure.persistence.review` |
| Related classes | `ReviewAppService`, `ReviewCase`, `ReviewCaseRepository`, `Application` |
| Configuration | Security REVIEWER/ADMIN |
| Execution flow | [02 §11](02-architecture-handbook.md) |
| Dependencies | Review + application ports; domain events on decision |
| Deep | [03 § Review / Approval](03-business-feature-handbook.md) |

### FileRef — System Parameters

| Field | Location |
| --- | --- |
| Purpose | Tunable runtime settings |
| Entry controller | `presentation.api.v1.SystemParameterApiController`, `AdminController` |
| Related packages | `application.parameter.service`, `domain.parameter`, `infrastructure.persistence.parameter`, `infrastructure.cache` |
| Related classes | `SystemParameterService`, `SystemParameter`, `SystemParameterRepository` |
| Configuration | Seeded parameters; cache eviction on update |
| Execution flow | [02 §12](02-architecture-handbook.md) |
| Dependencies | Parameter port + `CacheStore` |
| Deep | [03 § System Parameters](03-business-feature-handbook.md) |

### FileRef — Audit Log

| Field | Location |
| --- | --- |
| Purpose | Cross-cutting action trail |
| Entry controller | `presentation.api.v1.AuditLogApiController`, `AdminController` |
| Related packages | `application.audit.service`, `common.audit`, `infrastructure.persistence` (audit) |
| Related classes | `AuditLogService`, `@Auditable` aspect, login handlers |
| Configuration | `AsyncConfig` for async writes |
| Execution flow | [02 §13](02-architecture-handbook.md) |
| Dependencies | `AuditLogRepository` |
| Deep | [03 § Audit Logging](03-business-feature-handbook.md) · [../design/11-audit-logging.md](../design/11-audit-logging.md) |

### FileRef — Cache Management

| Field | Location |
| --- | --- |
| Purpose | Admin evict/refresh caches |
| Entry controller | `presentation.api.v1.CacheManagementApiController` |
| Related packages | `application.cache.service`, `infrastructure.cache` |
| Related classes | `CacheManagementService`, `InMemoryCacheStore`, `CachedCardProductRepository` |
| Configuration | None beyond cache bean wiring |
| Execution flow | [02 §14](02-architecture-handbook.md) |
| Dependencies | `SystemParameterService`, product cache, `CacheStore` |
| Deep | [03 § Cache Management](03-business-feature-handbook.md) |

### FileRef — Notifications

| Field | Location |
| --- | --- |
| Purpose | Mock SMS/email + admin log view |
| Entry controller | `presentation.api.v1.NotificationLogApiController` (query); send via handlers |
| Related packages | `application.notification.service`, `infrastructure.notification`, `infrastructure.event` |
| Related classes | `NotificationServiceImpl`, `NotificationEventHandler`, `MockSmsSender`, `MockEmailSender`, `NotificationTemplate` |
| Configuration | `tlbank.notification.mode=mock` |
| Execution flow | [02 §15](02-architecture-handbook.md) |
| Dependencies | Domain events / OTP service → mock senders; audit for log view |
| Deep | [03 § Notification](03-business-feature-handbook.md) |

### FileRef — Report Export

| Field | Location |
| --- | --- |
| Purpose | Daily statistics Excel/PDF |
| Entry controller | `presentation.api.v1.ReportApiController`, `AdminController` |
| Related packages | `application.report.service`, `infrastructure.report` |
| Related classes | `ReportAppService`, `ReportDataService`, `ExcelReportGenerator`, `PdfReportGenerator` |
| Configuration | None specific |
| Execution flow | [02 §16](02-architecture-handbook.md) |
| Dependencies | JPA repos (bypass domain), POI/iText |
| Deep | [03 § Report](03-business-feature-handbook.md) · [../design/14-report-design.md](../design/14-report-design.md) |

### FileRef — Schedulers

| Field | Location |
| --- | --- |
| Purpose | Background cleanup / refresh / stats |
| Entry controller | `presentation.api.v1.SchedulerApiController` (manual run) |
| Related packages | `infrastructure.scheduler`, `common.config` |
| Related classes | `OtpCleanupScheduler`, `CacheRefreshScheduler`, `DailyStatisticsScheduler` |
| Configuration | `SchedulingConfig` / `SchedulerConfig`, `tlbank.scheduler.*.cron` |
| Execution flow | [02 §17](02-architecture-handbook.md) |
| Dependencies | OTP repo, parameter/cache services, report data |
| Deep | [03 § Scheduler](03-business-feature-handbook.md) · [../design/13-scheduler-design.md](../design/13-scheduler-design.md) |

### FileRef — Idempotency

| Field | Location |
| --- | --- |
| Purpose | Safe retries on application create |
| Entry controller | `ApplicationApiController` (`Idempotency-Key` header) |
| Related packages | `application.idempotency`, `infrastructure.idempotency` |
| Related classes | `IdempotencyService`, `IdempotencyStore`, `RedisIdempotencyStore`, `InMemoryIdempotencyStore`, `IdempotencyEntry` |
| Configuration | `tlbank.idempotency.ttl-hours`, `key-prefix`, `store` (`redis`/`memory`) |
| Execution flow | [02 §18](02-architecture-handbook.md) |
| Dependencies | Redis or in-memory map; wraps create use case |
| Deep | [03 § Idempotency](03-business-feature-handbook.md) |

---

## 3. Technology → code locator

| Technology | Start here | Config / artifacts | Deep |
| --- | --- | --- | --- |
| Java 17 | entire `src/main/java` | `pom.xml` | [04 Ch.1](04-technology-handbook.md) |
| Spring Boot | `TlbankLendingApplication` | `application*.yml` | [04 Ch.2](04-technology-handbook.md) |
| Spring MVC | `presentation.api.v1.*` | — | [04 Ch.3](04-technology-handbook.md) |
| Spring Security | `security.config.SecurityConfig` | `application.yml` session | [04 Ch.4](04-technology-handbook.md) |
| BCrypt | `SecurityConfig` password encoder | — | [04 Ch.5](04-technology-handbook.md) |
| JPA / Hibernate | `infrastructure.persistence.**` | `ddl-auto: validate` | [04 Ch.6–7](04-technology-handbook.md) |
| Flyway | `src/main/resources/db/migration*` | Flyway YAML | [04 Ch.8](04-technology-handbook.md) |
| H2 | — | `application-dev.yml` | [04 Ch.9](04-technology-handbook.md) |
| SQL Server | — | `application-staging.yml`, Compose | [04 Ch.10](04-technology-handbook.md) |
| Thymeleaf | `presentation.web.*`, `resources/templates` | — | [04 Ch.11](04-technology-handbook.md) |
| Validation | `application.dto.request.*` | — | [04 Ch.12](04-technology-handbook.md) |
| AOP audit | `common.audit.*` | `AsyncConfig` | [04 Ch.13](04-technology-handbook.md) |
| Events | `domain.event.*`, `infrastructure.event.*` | — | [04 Ch.14](04-technology-handbook.md) |
| Scheduling | `infrastructure.scheduler.*` | `tlbank.scheduler.*` | [04 Ch.15](04-technology-handbook.md) |
| Async | `common.config.AsyncConfig` | — | [04 Ch.16](04-technology-handbook.md) |
| Actuator | — | `management.*` YAML | [04 Ch.17](04-technology-handbook.md) |
| SpringDoc | — | springdoc YAML | [04 Ch.18](04-technology-handbook.md) |
| Redis | `infrastructure.idempotency.RedisIdempotencyStore` | `application-dev.yml` | [04 Ch.19](04-technology-handbook.md) |
| In-memory cache | `infrastructure.cache.InMemoryCacheStore` | — | [04 Ch.20](04-technology-handbook.md) |
| File storage | `infrastructure.storage.LocalDocumentStorageService` | upload path YAML | [04 Ch.21](04-technology-handbook.md) |
| POI / iText | `infrastructure.report.*` | — | [04 Ch.22–23](04-technology-handbook.md) |
| Maven / JaCoCo | `pom.xml` | `mvnw` | [04 Ch.25–26](04-technology-handbook.md) |
| Tests | `src/test/java/**` | `src/test/resources/application-dev.yml` | [04 Ch.27](04-technology-handbook.md) |
| Docker | `docker/app/Dockerfile`, `docker-compose.yml` | `.env.example` | [04 Ch.28–29](04-technology-handbook.md) |
| CI/CD | `../../../.github/workflows/ci.yml` | GHCR image names | [04 Ch.30–32](04-technology-handbook.md) |
| Terraform | `../../../infra/local/` | local provider | [04 Ch.33](04-technology-handbook.md) |
| Logging | `resources/logback-spring.xml` | MDC filter in security/common | [04 Ch.35](04-technology-handbook.md) |

---

## 4. Repository ports → implementations

| Port | Implementation | Notes |
| --- | --- | --- |
| `ApplicationRepository` | `ApplicationRepositoryImpl` | Manual mapping |
| `ReviewCaseRepository` | `ReviewCaseRepositoryImpl` | |
| `UserRepository` | `UserRepositoryImpl` | |
| `OtpRepository` | `OtpRepositoryImpl` | |
| `CardProductRepository` | `CardProductRepositoryImpl` + `@Primary` `CachedCardProductRepository` | |
| `SystemParameterRepository` | `SystemParameterRepositoryImpl` | |
| `IdempotencyStore` | `RedisIdempotencyStore` / `InMemoryIdempotencyStore` | Property-gated |
| `AuditLogRepository` | Spring Data JPA | No domain port |

Full table: [01-repository-handbook.md](01-repository-handbook.md) §3.4 · [02-architecture-handbook.md](02-architecture-handbook.md) § Repository Port Index.

---

## 5. Configuration file index

| File | Purpose |
| --- | --- |
| `src/main/resources/application.yml` | Defaults: session, Flyway, schedulers, idempotency TTL, mock notify |
| `application-dev.yml` | H2, Redis idempotency, Swagger |
| `application-staging.yml` | SQL Server env vars |
| `application-prod.yml` | SQL Server, Swagger off |
| `logback-spring.xml` | Logging |
| `docker-compose.yml` | Local staging stack |
| `.env.example` | Compose env template |
| `../../../.github/workflows/ci.yml` | Build/test/scan/push/deploy |
| `../../../infra/local/*.tf` | Local Terraform practice |

Also: [02-architecture-handbook.md](02-architecture-handbook.md) § Configuration File Index · [01-repository-handbook.md](01-repository-handbook.md) §14.

---

## 6. Quick “where is X?”

| Question | Open first |
| --- | --- |
| Invalid status transition | `domain.application.Application`, `ApplicationStatus` |
| Login success audit | `security.handler.LoginSuccessHandler` |
| Idempotency lock | `application.idempotency.IdempotencyService` |
| Review case after submit | `infrastructure.event.ReviewEventHandler` |
| Mock SMS | `infrastructure.notification.MockSmsSender` |
| OTP expiry job | `infrastructure.scheduler.OtpCleanupScheduler` |
| Global API errors | `presentation.api.advice.GlobalExceptionHandler` |
| Security matrix | `security.config.SecurityConfig` · [../design/07-security-design.md](../design/07-security-design.md) |

---

## 7. How to use this handbook

1. Find the **feature or technology** section above.  
2. Jump to the listed **class/package**.  
3. For sequence diagrams and full file paths, open the linked **[02](02-architecture-handbook.md)** section.  
4. For “why”, open **[03](03-business-feature-handbook.md)** / **[04](04-technology-handbook.md)** / **[05](05-interview-handbook.md)**.

---

*Locator only. Behavior and design rationale stay in handbooks 00–06 and 20.*
