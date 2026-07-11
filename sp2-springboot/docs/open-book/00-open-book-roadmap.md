# Interview Open Book Roadmap

Planning document only. **No implementation phase has started.**

This revision shrinks scope after audit: one unified `topics/` folder (no parallel `architecture/` + `interview/` pages), Critical/High source-map pages only, exact paths and Q IDs, target **35–50** Open Book files.

Repository SoT: `sp2-springboot` sources, config, tests, Docker, plus monorepo `.github/workflows/` and `infra/local/`. Interview SoT: `docs/handbook/09`–`12`.

---

## 1. Objective

Build a small **Interview Open Book** for fast recall:

- open an important class page and speak (30s / 2m / 5m where needed)
- open one topic page and cover implementation, flow, trade-offs, and follow-ups
- sketch a whiteboard from a short pack
- navigate Q IDs → class/topic pages without duplicating handbooks

Prefer **linking** SDD/ADR/handbook depth over copying it.

---

## 2. Existing Documentation Inventory

Reuse; do not fork.

| Path | Role |
| --- | --- |
| `sp2-springboot/README.md` | Runbook / stack entry |
| `sp2-springboot/docs/README.md` | Docs index (still missing `09`–`12` / open-book links) |
| `sp2-springboot/docs/handbook/00-project-overview.md` | Executive overview |
| `sp2-springboot/docs/handbook/01-repository-handbook.md` | Repo wiki |
| `sp2-springboot/docs/handbook/02-architecture-handbook.md` | Feature → layer → file |
| `sp2-springboot/docs/handbook/03-business-feature-handbook.md` | Business capabilities |
| `sp2-springboot/docs/handbook/04-technology-handbook.md` | Technology chapters |
| `sp2-springboot/docs/handbook/05-interview-handbook.md` | Older interview framing (superseded for drill by `09`–`12` + Open Book) |
| `sp2-springboot/docs/handbook/06-system-design-handbook.md` | Evolution discussion |
| `sp2-springboot/docs/handbook/07-cheat-sheet.md` | Short sheet (overlaps `12`) |
| `sp2-springboot/docs/handbook/08-file-reference-handbook.md` | File locator |
| `sp2-springboot/docs/handbook/09-interview-source-map-300.md` | Validated Q001–Q300 + appendix |
| `sp2-springboot/docs/handbook/10-core-interview-questions-30.md` | Core 30 |
| `sp2-springboot/docs/handbook/11-primary-interview-questions-100.md` | Primary 100 |
| `sp2-springboot/docs/handbook/12-interview-quick-review.md` | 30-minute bullets |
| `sp2-springboot/docs/design/00-sdd-overview.md` | SDD index |
| `sp2-springboot/docs/design/01-system-context.md` | System context |
| `sp2-springboot/docs/design/02-architecture-design.md` | Architecture SDD |
| `sp2-springboot/docs/design/03-package-structure.md` | Packages |
| `sp2-springboot/docs/design/04-domain-model.md` | Domain SDD |
| `sp2-springboot/docs/design/05-database-design.md` | DB / Flyway design |
| `sp2-springboot/docs/design/06-api-specification.md` | API SDD |
| `sp2-springboot/docs/design/07-security-design.md` | Security SDD |
| `sp2-springboot/docs/design/08-workflow-design.md` | Workflow SDD |
| `sp2-springboot/docs/design/09-module-design.md` | Modules |
| `sp2-springboot/docs/design/10-error-handling.md` | Errors |
| `sp2-springboot/docs/design/11-audit-logging.md` | Audit SDD |
| `sp2-springboot/docs/design/12-cache-design.md` | Cache SDD |
| `sp2-springboot/docs/design/13-scheduler-design.md` | Scheduler SDD |
| `sp2-springboot/docs/design/14-report-design.md` | Reports |
| `sp2-springboot/docs/design/15-file-upload-design.md` | Uploads |
| `sp2-springboot/docs/design/16-testing-strategy.md` | Testing SDD |
| `sp2-springboot/docs/design/17-deployment-design.md` | Deploy SDD |
| `sp2-springboot/docs/design/18-coding-standards.md` | Standards |
| `sp2-springboot/docs/design/19-cursor-implementation-roadmap.md` | Implementation roadmap |
| `sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md` | Maintenance / future |
| `sp2-springboot/docs/decisions/README.md` | ADR index |
| `sp2-springboot/docs/decisions/0001-use-clean-architecture.md` | Clean Architecture ADR |
| `sp2-springboot/docs/decisions/0002-use-ddd.md` | DDD ADR |
| `sp2-springboot/docs/decisions/0003-use-redis-idempotency.md` | Redis idempotency ADR |
| `sp2-springboot/docs/decisions/0004-use-github-actions.md` | GitHub Actions ADR |
| `sp2-springboot/docs/decisions/0005-use-terraform-local.md` | Local Terraform ADR |
| `sp2-springboot/docs/decisions/0006-session-over-jwt.md` | Session vs JWT ADR |
| `sp2-springboot/docs/decisions/0007-h2-vs-sqlserver.md` | H2 vs SQL Server ADR |
| `sp2-springboot/docs/onboarding/README.md` | Onboarding index |
| `sp2-springboot/docs/onboarding/00-first-day.md` | First day |
| `sp2-springboot/docs/onboarding/01-local-development-setup.md` | Local setup |
| `sp2-springboot/docs/onboarding/02-how-to-run-the-project.md` | How to run |
| `sp2-springboot/docs/onboarding/03-debugging-guide.md` | Debugging |
| `sp2-springboot/docs/onboarding/04-code-reading-order.md` | Reading order |
| `sp2-springboot/docs/diagrams/README.md` | Diagram inventory |
| `.github/workflows/ci.yml` | CI/CD workflow |
| `.github/workflows/markdown.yml` | Markdown lint workflow |
| `.github/workflows/terraform.yml` | Terraform workflow |
| `infra/local/main.tf` | Local Terraform main |
| `infra/local/variables.tf` | Local Terraform variables |
| `infra/local/outputs.tf` | Local Terraform outputs |
| `infra/local/README.md` | Local Terraform notes |
| `sp2-springboot/docker-compose.yml` | Compose topology |
| `sp2-springboot/docker/app/Dockerfile` | App image |
| `sp2-springboot/pom.xml` | Maven build |
| `sp2-springboot/src/main/resources/application.yml` | Base config |

Draft `docs/drafts/interview-question-catalog-draf.md` is **not** SoT.

---

## 3. Documentation Gaps

Still missing for interview prep (verified):

1. No Open Book pages yet (only this roadmap under `docs/open-book/`).
2. No class-oriented 30s/2m/5m scripts.
3. No unified topic pages that combine implementation + spoken answers + whiteboard hints.
4. No whiteboard pack for onsite sketching.
5. Weak Q-ID → explainable-page navigation ( `09` appendix is evidence, not a speaking script).
6. `docs/README.md` omits handbook `09`–`12` and Open Book.
7. Overlap among handbook `05` / `07` / `12` without a single Open Book hierarchy.

---

## 4. Important Class Inventory

### 4.1 Critical — dedicated source-map pages (14)

All paths verified present.

| Class | Path | Why | Related Q IDs | Dedicated page |
| --- | --- | --- | --- | --- |
| `Application` | `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java` | Aggregate root | Q014, Q015, Q027, Q029, Q034, Q037, Q041, Q042, Q045, Q049, Q051, Q053 | Yes |
| `ApplicationAppService` | `sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java` | Application orchestration, `@Transactional`, events | Q030, Q031, Q037, Q094, Q096, Q100, Q106, Q127, Q131, Q151, Q227, Q252 | Yes |
| `ApplicationStatus` | `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationStatus.java` | State machine | Q009, Q012, Q014, Q042, Q051, Q052, Q055, Q059, Q114, Q145, Q243, Q246 | Yes |
| `ApplicationRepositoryImpl` | `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationRepositoryImpl.java` | Port adapter / mapping | Q027, Q028, Q029, Q034, Q092, Q101, Q102, Q107, Q210, Q258, Q297 | Yes |
| `ApplicationEntity` | `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationEntity.java` | JPA model | Q029, Q089, Q090, Q091, Q092, Q127, Q259 | Yes |
| `SecurityConfig` | `sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java` | Session security filter chain | Q017, Q020, Q023, Q025, Q033, Q070, Q077, Q081, Q083, Q084, Q085, Q087, Q250 | Yes |
| `IdempotencyService` | `sp2-springboot/src/main/java/com/tlbank/lending/application/idempotency/IdempotencyService.java` | Idempotent execute | Q009, Q011, Q035, Q063, Q067, Q132, Q269 | Yes |
| `RedisIdempotencyStore` | `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/idempotency/RedisIdempotencyStore.java` | Redis idempotency only | Q016, Q028, Q132, Q134, Q229, Q268 | Yes |
| `OtpAppService` | `sp2-springboot/src/main/java/com/tlbank/lending/application/otp/service/OtpAppService.java` | OTP send/verify | Q046, Q103, Q135, Q137, Q138, Q139, Q150, Q164, Q204, Q205, Q251, Q272 | Yes |
| `ReviewAppService` | `sp2-springboot/src/main/java/com/tlbank/lending/application/review/service/ReviewAppService.java` | Approve/reject orchestration | Q104, Q109, Q110, Q130, Q148, Q209, Q214 | Yes |
| `ReviewCase` | `sp2-springboot/src/main/java/com/tlbank/lending/domain/review/ReviewCase.java` | Review aggregate | Q041, Q045, Q049, Q145, Q146, Q147, Q232, Q273 | Yes |
| `AuditAspect` | `sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditAspect.java` | AOP audit | Q024, Q072, Q073, Q074, Q076, Q135, Q277, Q282, Q291 | Yes |
| `LocalDocumentStorageService` | `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/storage/LocalDocumentStorageService.java` | Local file storage | Q008, Q053, Q106, Q177, Q178, Q179, Q180, Q181, Q182, Q208, Q216, Q255, Q298 | Yes |
| `NotificationEventHandler` | `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/event/NotificationEventHandler.java` | Event → mock notify | Q002, Q038, Q044, Q099, Q100, Q109, Q130, Q233 | Yes |

**Critical count: 14** (internally consistent with the table above).

Related types covered **inside** these pages (not separate): `ApplicationRepository`, `IdempotencyStore`, `InMemoryIdempotencyStore`, `ApplicationEventPublisher` (Spring), domain events published from app services.

### 4.2 High — dedicated source-map pages (8)

| Class | Path | Why | Related Q IDs | Dedicated page |
| --- | --- | --- | --- | --- |
| `OtpRecord` | `sp2-springboot/src/main/java/com/tlbank/lending/domain/otp/OtpRecord.java` | OTP domain rules | Q045, Q046, Q060, Q136, Q138, Q140, Q235, Q266 | Yes |
| `AuditLogWriter` | `sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditLogWriter.java` | Audit persistence / isolation | Q024, Q074, Q079, Q128, Q149, Q281, Q291 | Yes |
| `GlobalExceptionHandler` | `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/advice/GlobalExceptionHandler.java` | API error mapping | Q009, Q013, Q031, Q070, Q071, Q072, Q075, Q265, Q300 | Yes |
| `ApplicationApiController` | `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/v1/ApplicationApiController.java` | REST + idempotency header | Q031, Q037, Q062, Q063, Q064, Q065, Q066, Q067, Q177, Q278, Q286 | Yes |
| `WorkflowDomainService` | `sp2-springboot/src/main/java/com/tlbank/lending/domain/service/WorkflowDomainService.java` | Domain service vs entity | Q030, Q032, Q225 | Yes |
| `CachedCardProductRepository` | `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/cache/CachedCardProductRepository.java` | Cache decorator / impurity | Q039, Q086, Q108, Q212, Q284 | Yes |
| `OtpCleanupScheduler` | `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/scheduler/OtpCleanupScheduler.java` | Scheduled cleanup | Q022, Q060, Q093, Q129, Q140, Q168, Q170 | Yes |
| `ReportAppService` | `sp2-springboot/src/main/java/com/tlbank/lending/application/report/service/ReportAppService.java` | Excel/PDF orchestration | Q133, Q171, Q173, Q176, Q209, Q231 | Yes |

**High count: 8.**

### 4.3 Related-class coverage only (no dedicated page)

Explain under a parent Critical/High page or a topic page:

| Class | Path | Covered under |
| --- | --- | --- |
| `TlbankLendingApplication` | `sp2-springboot/src/main/java/com/tlbank/lending/TlbankLendingApplication.java` | topics/01-architecture |
| `JpaConfig` | `sp2-springboot/src/main/java/com/tlbank/lending/common/config/JpaConfig.java` | topics/05-jpa-and-sql |
| `ApiResponse` | `sp2-springboot/src/main/java/com/tlbank/lending/common/response/ApiResponse.java` | GlobalExceptionHandler / ApplicationApiController |
| `BusinessException` | `sp2-springboot/src/main/java/com/tlbank/lending/common/exception/BusinessException.java` | GlobalExceptionHandler |
| `ErrorCode` | `sp2-springboot/src/main/java/com/tlbank/lending/common/exception/ErrorCode.java` | GlobalExceptionHandler |
| `ApplicationId` | `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationId.java` | Application |
| `ApplicationApprovedEvent` | `sp2-springboot/src/main/java/com/tlbank/lending/domain/event/ApplicationApprovedEvent.java` | ReviewAppService / topics/09-events-and-notifications |
| `UserDetailsServiceImpl` | `sp2-springboot/src/main/java/com/tlbank/lending/security/service/UserDetailsServiceImpl.java` | SecurityConfig |
| `SystemParameterService` | `sp2-springboot/src/main/java/com/tlbank/lending/application/parameter/service/SystemParameterService.java` | topics/08-cache |
| `CacheRefreshScheduler` | `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/scheduler/CacheRefreshScheduler.java` | OtpCleanupScheduler topic / topics/10-audit-logging schedulers note — prefer topics/12-delivery-and-limitations + topics/08-cache |
| `NotificationServiceImpl` | `sp2-springboot/src/main/java/com/tlbank/lending/application/notification/service/NotificationServiceImpl.java` | NotificationEventHandler |
| `MockSmsSender` | `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/notification/MockSmsSender.java` | topics/09-events-and-notifications |
| `MockEmailSender` | `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/notification/MockEmailSender.java` | topics/09-events-and-notifications |
| `ExcelReportGenerator` | `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/report/ExcelReportGenerator.java` | ReportAppService |
| `PdfReportGenerator` | `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/report/PdfReportGenerator.java` | ReportAppService |
| `InMemoryCacheStore` | `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/cache/InMemoryCacheStore.java` | CachedCardProductRepository / topics/08-cache |
| `InMemoryIdempotencyStore` | `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/idempotency/InMemoryIdempotencyStore.java` | RedisIdempotencyStore / IdempotencyService |
| `IdempotencyStore` | `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/idempotency/IdempotencyStore.java` | IdempotencyService |
| `MdcLoggingFilter` | `sp2-springboot/src/main/java/com/tlbank/lending/security/filter/MdcLoggingFilter.java` | SecurityConfig / topics/03-security (Q080, Q218, Q234, Q237) |
| `LoginSuccessHandler` | `sp2-springboot/src/main/java/com/tlbank/lending/security/handler/LoginSuccessHandler.java` | SecurityConfig |
| `LoginFailureHandler` | `sp2-springboot/src/main/java/com/tlbank/lending/security/handler/LoginFailureHandler.java` | SecurityConfig |
| `ReviewEventHandler` | `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/event/ReviewEventHandler.java` | NotificationEventHandler / topics/09 |
| `SchedulingConfig` | `sp2-springboot/src/main/java/com/tlbank/lending/common/config/SchedulingConfig.java` | topics/12-delivery-and-limitations (Q018, Q033, Q167, Q192) |
| `SchedulerConfig` | `sp2-springboot/src/main/java/com/tlbank/lending/common/config/SchedulerConfig.java` | same |
| `OtpApiController` | `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/v1/OtpApiController.java` | OtpAppService (HTTP edge; Q205) |
| `ReviewApiController` | `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/v1/ReviewApiController.java` | ReviewAppService |
| `ApplicationJpaRepository` | `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationJpaRepository.java` | ApplicationRepositoryImpl / topics/05 (Q068, Q091, Q095, Q102) |
| `AuditDetailBuilder` | `sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditDetailBuilder.java` | AuditAspect (Q201) |

Thin tests (`ApplicationTest`, `AuditAspectTest`, `ExcelReportGeneratorTest`, etc.): list under **Related Tests** on parent pages only.

Representative tests to cite (exist):

- `sp2-springboot/src/test/java/com/tlbank/lending/application/ApplicationFlowIntegrationTest.java`
- `sp2-springboot/src/test/java/com/tlbank/lending/application/ApplicationIdempotencyIntegrationTest.java`
- `sp2-springboot/src/test/java/com/tlbank/lending/security/SecurityIntegrationTest.java`
- `sp2-springboot/src/test/java/com/tlbank/lending/domain/application/ApplicationTest.java`
- `sp2-springboot/src/test/java/com/tlbank/lending/application/idempotency/IdempotencyServiceTest.java`
- `sp2-springboot/src/test/java/com/tlbank/lending/common/audit/AuditAspectTest.java`

**No custom `DomainEventPublisher` class exists** — publishing uses Spring `ApplicationEventPublisher` in app services.

---

## 5. Unified Topic Inventory (`topics/`)

**12 topic pages.** One page per theme. No second folder for the same theme.

Each page must include:

- Current Implementation
- Runtime Flow
- Design Decisions
- Trade-offs
- Alternatives
- Production Considerations
- Related Classes
- Related Tests
- Related Question IDs
- 30-Second Explanation
- 2-Minute Explanation
- Whiteboard Sketch
- Common Follow-Up Questions
- Current Limitations

| File | Why | Reuse | Anchor classes | Exact Q IDs | Mermaid |
| --- | --- | --- | --- | --- | --- |
| `topics/01-architecture.md` | Hexagonal / dependency rule / leaks | ADR 0001, design/02, handbook/02 | ApplicationAppService, ApplicationRepositoryImpl, SecurityConfig | Q027, Q028, Q029, Q030, Q031, Q032, Q033, Q034, Q035 | Yes |
| `topics/02-request-lifecycle.md` | HTTP → security → app → domain → DB | design/06 | ApplicationApiController, SecurityConfig, ApplicationAppService | Q031, Q062, Q063, Q064, Q065, Q066, Q067 | Yes |
| `topics/03-security.md` | Session auth, roles, CSRF, MDC | ADR 0006, design/07 | SecurityConfig, MdcLoggingFilter, LoginSuccessHandler | Q017, Q020, Q023, Q025, Q077, Q080, Q081, Q083, Q084, Q085, Q087, Q234, Q250 | Yes |
| `topics/04-domain-and-workflow.md` | DDD-lite, aggregates, state machine, review | ADR 0002, design/04, design/08 | Application, ApplicationStatus, ReviewCase, OtpRecord | Q041, Q042, Q045, Q046, Q047, Q051, Q052, Q053, Q054, Q055, Q056, Q057, Q058, Q059, Q060, Q145, Q146, Q147, Q148 | Yes |
| `topics/05-jpa-and-sql.md` | Entity mapping, JPQL/native, Flyway, H2 vs SQL Server | ADR 0007, design/05 | ApplicationEntity, ApplicationRepositoryImpl, ApplicationJpaRepository | Q068, Q089, Q090, Q091, Q092, Q093, Q094, Q095, Q111, Q112, Q113, Q114, Q118, Q119, Q120, Q121, Q122, Q123, Q124, Q125, Q126, Q257, Q258, Q259, Q260 | Yes |
| `topics/06-transactions.md` | Where `@Transactional` sits | app services + schedulers | ApplicationAppService, ReviewAppService, OtpCleanupScheduler | Q127, Q129, Q130, Q131, Q272 | Yes |
| `topics/07-redis-idempotency.md` | Redis = idempotency store, not cache/session | ADR 0003 | IdempotencyService, RedisIdempotencyStore, InMemoryIdempotencyStore | Q011, Q016, Q035, Q063, Q067, Q132, Q134, Q268, Q269 | Yes |
| `topics/08-cache.md` | In-memory cache only | design/12 | InMemoryCacheStore, CachedCardProductRepository, CacheManagementService | Q039, Q086, Q097, Q108, Q141, Q142, Q143, Q144, Q212, Q215, Q228, Q284 | Yes |
| `topics/09-events-and-notifications.md` | Spring events + mock SMS/email | design/08–09 | ApplicationAppService, ReviewAppService, NotificationEventHandler, MockSmsSender, MockEmailSender | Q002, Q038, Q044, Q047, Q099, Q100, Q109, Q130, Q161, Q162, Q163, Q164, Q165, Q166, Q233 | Yes |
| `topics/10-audit-logging.md` | AOP audit + sanitization | design/11 | AuditAspect, AuditLogWriter, AuditDetailBuilder | Q024, Q072, Q073, Q074, Q076, Q149, Q201, Q277, Q281, Q282, Q291 | Yes |
| `topics/11-testing.md` | Unit / IT / MockMvc / JaCoCo | design/16 | ApplicationFlowIntegrationTest, SecurityIntegrationTest, ApplicationIdempotencyIntegrationTest | Q011, Q026, Q149, Q150, Q151, Q152, Q153, Q154, Q155, Q156, Q157, Q158, Q159, Q160, Q250 | Yes |
| `topics/12-delivery-and-limitations.md` | Docker, CI jobs, manual deploy, local Terraform, dual scheduling, non-prod claims | ADR 0004, 0005; design/13, 17; `ci.yml`; `infra/local` | Dockerfile, compose, SchedulingConfig, SchedulerConfig | Q005, Q018, Q033, Q167, Q168, Q169, Q170, Q183, Q184, Q185, Q186, Q187, Q188, Q189, Q190, Q191, Q192, Q193, Q194, Q195, Q196, Q197, Q198, Q199, Q223, Q224, Q225, Q226, Q227, Q228, Q229, Q230, Q290, Q295 | Yes |

**Topic page count: 12.**

Removed from prior plan: separate `architecture/*` and `interview/*` duplicates (security, redis, testing, docker, CI/CD, terraform, DDD, transactions, limitations, evolution).

---

## 6. Planned Folder Structure

```text
sp2-springboot/docs/open-book/
├── 00-open-book-roadmap.md    # this file
├── README.md                  # 30min / 2h / full-day paths
├── source-map/                # Critical + High class pages only
├── topics/                    # unified theme pages (one each)
├── whiteboards/               # short sketch pack
└── indexes/                   # class / Q / study indexes
```

| Folder | Purpose |
| --- | --- |
| `source-map/` | Class-first speaking scripts |
| `topics/` | Cross-cutting interview themes (implementation + answers) |
| `whiteboards/` | Sketch-only memory aids |
| `indexes/` | Navigation; no second explanations |

Do **not** create these files in this phase except this roadmap.

---

## 7. Scope Targets

| Kind | Count |
| --- | ---: |
| Critical source-map pages | 14 |
| High source-map pages | 8 |
| Unified topic pages | 12 |
| Whiteboard pages | 6 |
| Index / review pages | 5 |
| README | 1 |
| Roadmap (this file) | 1 |
| **Estimated total** | **~47** (within 35–50) |

---

## 8. Phase Plan

All statuses: **Pending**.

### Phase 1 — Foundation and Navigation

- **Objective:** Open Book entry + timed study paths linking `09`–`12`.
- **Files:** create `sp2-springboot/docs/open-book/README.md`.
- **Estimated file count:** 1
- **Dependencies:** none
- **Validation:** README links to `09`–`12` resolve; describes 30 min / 2 h / full day
- **Stop condition:** Owner knows what to open first without reading the full roadmap
- **Recommended commit message:** `docs(open-book): add Open Book README and study paths`
- **Status:** Pending

### Phase 2 — Critical Source Map Pages

- **Objective:** 14 Critical class pages.
- **Files:** `source-map/` pages for every Critical class in §4.1 (one file per class, or one file covering a verified pair only if filenames stay unique and both classes are titled in-page).
- **Estimated file count:** 14
- **Dependencies:** Phase 1
- **Validation:** class path exists; cited methods exist; Q IDs exist in `09`; Redis not described as cache/session
- **Stop condition:** Owner can explain application create, security session, idempotency, review, OTP, audit, storage, and event notify from source-map alone
- **Recommended commit message:** `docs(open-book): add 14 critical source-map pages`
- **Status:** Pending

### Phase 3 — Unified Topic Pages

- **Objective:** 12 `topics/*.md` pages with required sections.
- **Files:** all files listed in §5.
- **Estimated file count:** 12
- **Dependencies:** Phase 2 (link Critical classes)
- **Validation:** no theme duplicated under another folder; every Q ID listed exists; Mermaid matches real classes/jobs
- **Stop condition:** Core interview themes rehearsable from `topics/` in under two hours
- **Recommended commit message:** `docs(open-book): add 12 unified topic pages`
- **Status:** Done

### Phase 4 — High-Priority Source Map Pages

- **Objective:** 8 High class pages.
- **Files:** `source-map/` for every High class in §4.2.
- **Estimated file count:** 8
- **Dependencies:** Phases 2–3
- **Validation:** no new Medium dedicated pages; generators/mocks stay related-only
- **Stop condition:** High inventory complete
- **Recommended commit message:** `docs(open-book): add high-priority source-map pages`
- **Status:** Pending

### Phase 5 — Whiteboards and Memory Aids

- **Objective:** Sketch pack for onsite drawing.
- **Files (6):**
  - `whiteboards/01-layer-dependency.md`
  - `whiteboards/02-application-create-flow.md`
  - `whiteboards/03-security-filter-chain.md`
  - `whiteboards/04-idempotency-redis.md`
  - `whiteboards/05-review-approve-flow.md`
  - `whiteboards/06-ci-job-graph.md`
- **Estimated file count:** 6
- **Dependencies:** Phase 3
- **Validation:** each node maps to a real class or `ci.yml` job (`build-test`, `code-quality`, `dependency-scan`, `build-and-push-image`, `deploy-staging`)
- **Stop condition:** Six sketches cover most onsite draw requests
- **Recommended commit message:** `docs(open-book): add whiteboard sketch pack`
- **Status:** Pending

### Phase 6 — Cross-Linking and Indexes

- **Objective:** Navigation + minimal docs index updates.
- **Files:**
  - `indexes/by-class.md`
  - `indexes/by-question.md`
  - `indexes/study-paths.md`
  - `indexes/core-30-map.md`
  - `indexes/must-not-overclaim.md`
  - update `sp2-springboot/docs/open-book/README.md`
  - update `sp2-springboot/docs/README.md` to link Open Book + handbook `09`–`12`
- **Estimated file count:** 5 new + 2 updates
- **Dependencies:** Phases 2–5
- **Validation:** core 30 IDs from `10-core-interview-questions-30.md` resolve; primary 100 IDs have ≥1 target; no broken relative links
- **Stop condition:** 30 min / 2 h / full day paths work end-to-end
- **Recommended commit message:** `docs(open-book): add indexes and cross-links`
- **Status:** Pending

### Phase 7 — Selective JavaDoc Improvements

- **Objective:** Optional class-level JavaDoc on Critical types only when Open Book repeatedly restates missing intent.
- **Files:** up to 14 Critical `*.java` files (cap); prefer none if Open Book is enough.
- **Estimated file count:** 0–14 Java edits
- **Dependencies:** Phases 2–3
- **Validation:** JavaDoc matches Open Book; no new production claims; tests still pass
- **Stop condition:** Stop if Open Book already sufficient
- **Recommended commit message:** `docs: align critical class JavaDoc with Open Book`
- **Status:** Pending

### Phase 8 — Quality Audit

- **Objective:** Link check, claim audit, Markdownlint, timed dry-run.
- **Files:** fix only; no new topics unless Critical gap
- **Estimated file count:** N/A (edits)
- **Dependencies:** Phases 1–6 (7 optional)
- **Validation:** Definition of Done checklist
- **Stop condition:** 30-minute and 2-hour dry runs without invented facts
- **Recommended commit message:** `docs(open-book): quality audit and claim cleanup`
- **Status:** Pending

---

## 9. File Templates

### Source Map Page

```markdown
# <ClassName>

## One Sentence Summary
## Why This Class Exists
## Responsibilities
## Runtime Execution Flow
## Dependencies
## Important Public Methods
## Design Decisions
## Trade-offs
## Related Classes
## Related Tests
## Related Interview Questions
## 30-Second Explanation
## 2-Minute Explanation
## 5-Minute Deep Explanation
## Whiteboard Sketch
## Common Mistakes
## Current Limitations
```

### Topic Page

```markdown
# <Topic>

## Current Implementation
## Runtime Flow
## Design Decisions
## Trade-offs
## Alternatives
## Production Considerations
## Related Classes
## Related Tests
## Related Question IDs
## 30-Second Explanation
## 2-Minute Explanation
## Whiteboard Sketch
## Common Follow-Up Questions
## Current Limitations
```

(Optional Mermaid under Runtime Flow or Whiteboard Sketch.)

### Whiteboard Page

```markdown
# <Sketch Name>

## When to Draw This
## Nodes and Edges
## Mermaid or ASCII
## What to Say While Drawing
## Related Classes
## Related Question IDs
```

---

## 10. Writing Rules

- English: concise, repository-specific.
- Chinese notes (if used): personal technical style; forbidden: `你的` `我的` `我們` `建議使用` `採用最新` `最佳實務` `AI` `身為工程師` `值得注意的是`.
- No promotional wording; no textbook filler.
- No invented classes/methods/files/cloud/production behavior.
- Missing behavior → **Not implemented in the current repository.**
- Hard rules from code:
  - Redis via `RedisIdempotencyStore` / `tlbank.idempotency.store` — **not** general cache or sessions
  - Cache → `InMemoryCacheStore`
  - Notifications → mock senders
  - Staging deploy → `workflow_dispatch` on `deploy-staging` in `.github/workflows/ci.yml`
  - Terraform → `hashicorp/local` under `infra/local/`

---

## 11. Risk Controls

| Risk | Mitigation |
| --- | --- |
| Scope creep | Hard caps in §7; Medium = related-only |
| Topic duplication | Single `topics/` folder; ban parallel architecture/interview trees |
| Broken links | Phase 6 checker; relative paths only |
| Invented methods | Verify signatures in source before publish |
| Stale docs | Link SDD/ADR; summarize lightly |
| JavaDoc noise | Phase 7 optional and capped |
| Overclaiming | `indexes/must-not-overclaim.md` + topic limitations sections |

---

## 12. Definition of Done

1. All **14 Critical** classes have source-map pages.
2. All **8 High** classes have source-map pages.
3. All **12** topic pages exist; no duplicate theme elsewhere.
4. Core 30 question IDs link to ≥1 Open Book page.
5. Primary 100 IDs appear in `indexes/by-question.md`.
6. Quick Review / README link into Open Book depth.
7. No broken internal links; Markdownlint clean for Open Book.
8. No invented implementation claims.
9. Timed paths work: **30 min** / **2 h** / **full day**.
10. Total Open Book Markdown files remain roughly **35–50** (excluding optional JavaDoc).

---

## 13. Audit Corrections Applied in This Revision

| Issue | Fix |
| --- | --- |
| Separate `architecture/` + `interview/` duplicates | Replaced by unified `topics/` (12 pages) |
| Critical count 14 vs claimed 18 | Fixed Critical list = **14** |
| ~38 dedicated Yes pages | Cut to **14 + 8 = 22** source-map pages |
| Trivial dedicated pages | Demoted to related-class coverage (§4.3) |
| Vague Q sets | Replaced with exact IDs from `09` |
| Shorthand paths (`0001`, `onboarding/00`, `*.tf`) | Exact files listed in §2 |
| ~75–90 file estimate | Retargeted to **~47** |

---

## 14. Recommended Next Step

Execute **Phase 1 — Foundation and Navigation** only: create `sp2-springboot/docs/open-book/README.md`. Do not create `source-map/` or `topics/` until Phase 1 is accepted.
