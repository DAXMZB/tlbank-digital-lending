#!/usr/bin/env python3
"""Generate Phase 2 Critical Open Book source-map pages."""

from __future__ import annotations

from pathlib import Path

ROOT = Path("/Users/lidong/Documents/Project2/sp2-springboot/docs/open-book/source-map")
QBASE = "../../../handbook/09-interview-source-map-300.md"

ORDER = [
    ("domain", "Application"),
    ("application", "ApplicationAppService"),
    ("domain", "ApplicationStatus"),
    ("infrastructure", "ApplicationRepositoryImpl"),
    ("infrastructure", "ApplicationEntity"),
    ("security", "SecurityConfig"),
    ("application", "IdempotencyService"),
    ("infrastructure", "RedisIdempotencyStore"),
    ("application", "OtpAppService"),
    ("application", "ReviewAppService"),
    ("domain", "ReviewCase"),
    ("common", "AuditAspect"),
    ("infrastructure", "LocalDocumentStorageService"),
    ("infrastructure", "NotificationEventHandler"),
]


def q_links(ids: list[str]) -> str:
    return ", ".join(f"[`{q}`]({QBASE}#{q})" for q in ids)


def nav_block(i: int) -> str:
    folder, name = ORDER[i]
    prev = ORDER[i - 1] if i > 0 else None
    nxt = ORDER[i + 1] if i < len(ORDER) - 1 else None

    def rel(item):
        if not item:
            return None
        f, n = item
        return f"../{f}/{n}.md" if f != folder else f"{n}.md"

    lines = [
        f"# {name}",
        "",
        "- [Back to Open Book Home](../../README.md)",
        "- [Back to Source Map Index](../README.md)",
    ]
    if prev:
        lines.append(f"- Previous Critical Class: [{prev[1]}]({rel(prev)})")
    else:
        lines.append("- Previous Critical Class: —")
    if nxt:
        lines.append(f"- Next Critical Class: [{nxt[1]}]({rel(nxt)})")
    else:
        lines.append("- Next Critical Class: —")
    lines += [
        "- Related Topics: [topics/README.md](../../topics/README.md) (bodies **Pending** — Phase 3)",
        f"- Related Questions: [{QBASE.split('/')[-1]}]({QBASE})",
        "",
        "---",
        "",
    ]
    return "\n".join(lines)


def method(sig, purpose, **kw) -> str:
    lines = [f"### `{sig}`", "", f"- **Purpose:** {purpose}"]
    mapping = [
        ("input", "Input"),
        ("output", "Output"),
        ("biz", "Business meaning"),
        ("tx", "Transaction behavior"),
        ("sec", "Security behavior"),
        ("side", "Side effects"),
    ]
    for key, label in mapping:
        if kw.get(key):
            lines.append(f"- **{label}:** {kw[key]}")
    lines.append("")
    return "\n".join(lines)


def write(folder: str, name: str, body: str, index: int) -> None:
    path = ROOT / folder / f"{name}.md"
    path.parent.mkdir(parents=True, exist_ok=True)
    text = nav_block(index) + body.strip() + "\n"
    # markdown hygiene
    text = "\n".join(line.rstrip() for line in text.splitlines()) + "\n"
    path.write_text(text, encoding="utf-8")
    print("wrote", path.relative_to(ROOT.parent.parent.parent.parent))


def main() -> None:
    pages = []

    # 0 Application
    pages.append(
        (
            "domain",
            "Application",
            f"""
## One-Sentence Summary

Domain aggregate root for a credit-card application: status transitions and workflow history live here.

## 中文一句話

申請聚合根：狀態轉移與 workflow history 寫在領域層，不含 Spring 註解。

## Why This Class Exists

Centralize lifecycle rules so application services orchestrate use cases without owning the transition table.

Do not restate Clean Architecture here — see [0001-use-clean-architecture.md](../../../decisions/0001-use-clean-architecture.md) and future `topics/01-architecture.md` (**Pending**).

## Responsibilities

- Hold identity, applicant, product id, status, documents, histories, timestamps
- Enforce transitions through `ApplicationStatus.canTransitionTo`
- Validate all `DocumentType` values before submit
- Append `WorkflowHistory` on each successful transition

## Runtime Execution Flow

1. An application service loads or builds `Application`.
2. A domain verb runs (`verifyOtp`, `uploadDocuments`, `submit`, …).
3. Private `transitionTo` checks `canTransitionTo` or throws `WorkflowException`.
4. History is appended; `status` updates.
5. The repository adapter persists the aggregate.

```mermaid
stateDiagram-v2
  [*] --> INIT
  INIT --> OTP_VERIFIED
  INIT --> CANCELLED
  OTP_VERIFIED --> DOCUMENT_UPLOADED
  OTP_VERIFIED --> CANCELLED
  DOCUMENT_UPLOADED --> SUBMITTED
  DOCUMENT_UPLOADED --> CANCELLED
  SUBMITTED --> UNDER_REVIEW
  UNDER_REVIEW --> APPROVED
  UNDER_REVIEW --> REJECTED
```

## Dependencies

### Depends On

- `ApplicationStatus`, value objects, `WorkflowHistory`, `DocumentInfo`
- `BusinessException`, `WorkflowException`, `ErrorCode`

### Called By

- `ApplicationAppService`, `OtpAppService`, `ReviewAppService`
- `ApplicationTest` and flow tests

### Calls

- `ApplicationStatus.canTransitionTo`
- private `validateRequiredDocuments`, `transitionTo`

## Important Public Methods

{method('void verifyOtp(String operator)', 'INIT → OTP_VERIFIED', input='operator label', output='void', biz='OTP accepted', side='Workflow history')}

{method('void uploadDocuments(List<DocumentInfo> docs, String operator)', 'Add documents; may OTP_VERIFIED → DOCUMENT_UPLOADED', input='docs, operator', output='void', biz='Collect required docs', side='Status transition only from OTP_VERIFIED; further uploads stay in DOCUMENT_UPLOADED')}

{method('void submit(String operator)', 'DOCUMENT_UPLOADED → SUBMITTED after required-doc check', input='operator', output='void', biz='Ready for review', side='Sets submittedAt; event publish happens in app service after save')}

{method('void startReview(String operator)', 'SUBMITTED → UNDER_REVIEW', input='operator', output='void', biz='Review started')}

{method('void approve(String operator, String remark)', 'UNDER_REVIEW → APPROVED', input='operator, remark', output='void', biz='Approved')}

{method('void reject(String operator, String remark)', 'UNDER_REVIEW → REJECTED', input='operator, remark', output='void', biz='Rejected')}

{method('void cancel(String operator, String reason)', 'Cancel only from INIT / OTP_VERIFIED / DOCUMENT_UPLOADED', input='operator, reason', output='void', biz='Early abandon')}

## Design Decisions

- Transition map on `ApplicationStatus`, verbs on the aggregate
- Required documents = entire `DocumentType` enum
- Operator is a plain string

## Trade-offs and Alternatives

- Rich domain vs anemic JPA entity — clearer rules, more mapping code
- Alternative: external state-machine library — not used

## Related Classes

- [ApplicationStatus](ApplicationStatus.md), [ApplicationAppService](../application/ApplicationAppService.md), [ApplicationRepositoryImpl](../infrastructure/ApplicationRepositoryImpl.md), [ReviewAppService](../application/ReviewAppService.md), [OtpAppService](../application/OtpAppService.md)

## Related Configuration

- None (pure domain)

## Related Tests

- [ApplicationTest.java](../../../../src/test/java/com/tlbank/lending/domain/application/ApplicationTest.java)
- [ApplicationStatusTest.java](../../../../src/test/java/com/tlbank/lending/domain/application/ApplicationStatusTest.java)
- Indirect: [ApplicationFlowIntegrationTest.java](../../../../src/test/java/com/tlbank/lending/application/ApplicationFlowIntegrationTest.java), [ApplicationAppServiceTest.java](../../../../src/test/java/com/tlbank/lending/application/application/ApplicationAppServiceTest.java)

## Related ADRs and Design Documents

- [0002-use-ddd.md](../../../decisions/0002-use-ddd.md)
- [04-domain-model.md](../../../design/04-domain-model.md)
- [08-workflow-design.md](../../../design/08-workflow-design.md)

## Related Interview Questions

{q_links(['Q014','Q015','Q027','Q029','Q034','Q037','Q041','Q042','Q045','Q049','Q051','Q053','Q054','Q055','Q056'])}

## 30-Second Explanation

`Application` owns the credit application lifecycle. Methods like `verifyOtp` and `submit` call a private `transitionTo` that checks `ApplicationStatus.canTransitionTo` and writes workflow history. Illegal moves throw `WorkflowException`.

## 2-Minute Explanation

Services load the aggregate, call a verb, then save. Submit validates every `DocumentType`. Cancel works only in early statuses. Approve/reject require `UNDER_REVIEW`. Persistence and Spring events stay outside this class.

## 5-Minute Deep Explanation

Walk the forward path and CANCELLED exits. Note `uploadDocuments` transitions only from `OTP_VERIFIED`, then accepts more files. Contrast with `ReviewCase` status. Point to `ApplicationRepositoryImpl` for mapping. Defer hexagonal lecture to ADRs/topics.

## 中文口語重點

- 狀態機在領域物件，不在 Controller
- `transitionTo` 失敗丟 `WorkflowException`
- submit 前檢查全部文件類型
- 可取消：INIT／OTP_VERIFIED／DOCUMENT_UPLOADED

## Whiteboard Sketch

- **What to draw:** status boxes + allowed arrows
- **Drawing order:** forward path, then CANCELLED, then terminal approve/reject
- **Narration order:** caller → check → history → save

## Common Follow-Up Questions

- Where is the transition table?
- What if a document type is missing on submit?
- Why is `transitionTo` private?

## Common Mistakes

- Claiming Spring annotations on this class
- Allowing cancel after SUBMITTED
- Putting transition rules only in services

## Current Limitations

- Operator is not a typed identity
- No saga beyond the surrounding service transaction

## Source File

[Application.java](../../../../src/main/java/com/tlbank/lending/domain/application/Application.java)
""",
        )
    )

    # 1 ApplicationAppService
    pages.append(
        (
            "application",
            "ApplicationAppService",
            f"""
## One-Sentence Summary

Application-layer orchestrator for create/get/upload/submit/cancel and product listing.

## 中文一句話

申請用例服務：組裝領域物件、存庫、上傳檔案、送出事件；多數寫操作有 `@Transactional`。

## Why This Class Exists

Translate HTTP/DTO use cases into domain operations without putting workflow rules in controllers.

## Responsibilities

- Create INIT applications for enabled products
- Read details with masked PII
- Upload documents via `DocumentStorageService`
- Submit (publish `ApplicationSubmittedEvent`) and cancel
- List enabled card products

## Runtime Execution Flow

Typical submit path:

1. Controller calls `submitApplication`.
2. Load aggregate; `application.submit("APPLICANT")`.
3. `applicationRepository.save`.
4. Publish `ApplicationSubmittedEvent` (review case + notifications react elsewhere).
5. Return summary DTO.

## Dependencies

### Depends On

- `ApplicationRepository`, `CardProductRepository`, `DocumentStorageService`
- `ApplicationEventPublisher`, `Clock`
- Domain types + DTOs

### Called By

- `ApplicationApiController` (and related web flows)
- `ApplicationAppServiceTest`

### Calls

- `Application` verbs, repositories, `documentStorageService`, `eventPublisher.publishEvent`

## Important Public Methods

{method('ApplicationSummaryResponse createApplication(CreateApplicationRequest request)', 'Create INIT application', input='create request', output='summary', biz='Start application', tx='@Transactional', side='Persists new aggregate')}

{method('ApplicationDetailResponse getApplication(String applicationId)', 'Load detail with masking', input='application id', output='detail DTO', tx='@Transactional(readOnly = true)', sec='Masks national id / mobile / email in response')}

{method('DocumentUploadResponse uploadDocuments(String applicationId, DocumentType documentType, MultipartFile file)', 'Validate/store file then domain upload', input='id, type, multipart', output='upload DTO', tx='@Transactional', sec='@Auditable(DOCUMENT_UPLOAD)', side='Filesystem write + DB save')}

{method('ApplicationSummaryResponse submitApplication(String applicationId)', 'Submit for review', input='id', output='summary', tx='@Transactional', sec='@Auditable(APPLICATION_SUBMIT)', side='Save + ApplicationSubmittedEvent')}

{method('ApplicationSummaryResponse cancelApplication(String applicationId, String reason, String operator)', 'Cancel early-stage application', input='id, reason, operator', output='summary', tx='@Transactional', sec='@Auditable(APPLICATION_CANCEL)')}

{method('List<CardProductResponse> findAllEnabledProducts()', 'List sellable products', output='product DTOs', tx='@Transactional(readOnly = true)')}

## Design Decisions

- Masking on read model, not in the aggregate
- Events after successful save
- Storage behind `DocumentStorageService` port (local impl in production profile of this repo)

## Trade-offs and Alternatives

- Application service as transaction boundary vs repository TX — TX sits here
- Operator often hard-coded `"APPLICANT"` for applicant-driven flows

## Related Classes

- [Application](../domain/Application.md), [LocalDocumentStorageService](../infrastructure/LocalDocumentStorageService.md), [IdempotencyService](IdempotencyService.md) (controller wrap), [NotificationEventHandler](../infrastructure/NotificationEventHandler.md)

## Related Configuration

- Upload path used by storage collaborator: `tlbank.upload.base-path` in [application.yml](../../../../src/main/resources/application.yml) / [application-dev.yml](../../../../src/main/resources/application-dev.yml)

## Related Tests

- [ApplicationAppServiceTest.java](../../../../src/test/java/com/tlbank/lending/application/application/ApplicationAppServiceTest.java)
- [ApplicationFlowIntegrationTest.java](../../../../src/test/java/com/tlbank/lending/application/ApplicationFlowIntegrationTest.java)

## Related ADRs and Design Documents

- [06-api-specification.md](../../../design/06-api-specification.md)
- [08-workflow-design.md](../../../design/08-workflow-design.md)
- [15-file-upload-design.md](../../../design/15-file-upload-design.md)

## Related Interview Questions

{q_links(['Q030','Q031','Q037','Q094','Q096','Q100','Q106','Q127','Q131','Q151','Q227','Q252','Q256','Q274','Q281'])}

## 30-Second Explanation

`ApplicationAppService` is the use-case layer for applications. It creates INIT apps, uploads files through storage, submits with an event, and cancels — usually inside `@Transactional`, with `@Auditable` on write paths.

## 2-Minute Explanation

Create checks the product is enabled, builds the aggregate, saves. Get masks PII. Upload validates/stores then calls `application.uploadDocuments`. Submit calls domain `submit`, saves, publishes `ApplicationSubmittedEvent` so review/notification handlers can run. Cancel delegates to domain cancel rules.

## 5-Minute Deep Explanation

Discuss readOnly vs write transactions, audit annotations, why events are not published from the domain entity, and how idempotency wraps create at the controller via `IdempotencyService` rather than inside this class.

## 中文口語重點

- TX 在 app service，不在 domain
- submit 後才 `publishEvent`
- upload 走 `DocumentStorageService`
- get 回傳已脫敏欄位

## Whiteboard Sketch

- **What to draw:** Controller → AppService → Domain → Repository / Storage / EventPublisher
- **Drawing order:** left-to-right layers
- **Narration order:** create vs submit differences

## Common Follow-Up Questions

- Why publish events in the service, not the entity?
- What does `@Auditable` do here?
- Where is idempotency applied?

## Common Mistakes

- Saying this class contains the state machine table
- Claiming JWT auth for these endpoints without reading `SecurityConfig`
- Assuming file storage is S3 (local FS in this repo)

## Current Limitations

- Applicant operator string is simplistic
- Product lookup failure is `PRODUCT_NOT_FOUND` business error

## Source File

[ApplicationAppService.java](../../../../src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java)
""",
        )
    )

    # 2 ApplicationStatus
    pages.append(
        (
            "domain",
            "ApplicationStatus",
            f"""
## One-Sentence Summary

Enum plus allowed-transition map for application lifecycle states.

## 中文一句話

申請狀態列舉與允許轉移表；`canTransitionTo` 是狀態機查表入口。

## Why This Class Exists

Keep transition legality declarative and testable beside the aggregate verbs.

## Responsibilities

- Declare statuses: INIT … CANCELLED
- Expose `canTransitionTo`
- Hold `ALLOWED_TRANSITIONS` map (terminal APPROVED/REJECTED/CANCELLED have no outbound edges in the map)

## Runtime Execution Flow

1. Aggregate calls `status.canTransitionTo(next)`.
2. Lookup `ALLOWED_TRANSITIONS.get(this)`.
3. Return whether `next` is contained (null next → false).

## Dependencies

### Depends On

- Java `EnumSet` / `Map`

### Called By

- `Application.transitionTo`
- `ApplicationStatusTest`, domain tests

### Calls

- None beyond JDK collections

## Important Public Methods

{method('boolean canTransitionTo(ApplicationStatus next)', 'Whether next status is allowed from this', input='next status (nullable)', output='boolean', biz='Guards illegal workflow moves')}

Enum constants themselves are the public API surface.

## Design Decisions

- Explicit map instead of a library
- CANCELLED reachable only from early states; APPROVED/REJECTED only from UNDER_REVIEW

## Trade-offs and Alternatives

- Enum map is readable for interviews; less flexible than a configurable workflow engine

## Related Classes

- [Application](Application.md)

## Related Configuration

- None

## Related Tests

- [ApplicationStatusTest.java](../../../../src/test/java/com/tlbank/lending/domain/application/ApplicationStatusTest.java)
- [ApplicationTest.java](../../../../src/test/java/com/tlbank/lending/domain/application/ApplicationTest.java)

## Related ADRs and Design Documents

- [08-workflow-design.md](../../../design/08-workflow-design.md)
- [04-domain-model.md](../../../design/04-domain-model.md)

## Related Interview Questions

{q_links(['Q009','Q012','Q014','Q042','Q051','Q052','Q055','Q059','Q114','Q145','Q243','Q246'])}

## 30-Second Explanation

`ApplicationStatus` is the enum behind the application state machine. `canTransitionTo` reads a static map of allowed next states.

## 2-Minute Explanation

List the eight values and the forward path. Emphasize CANCELLED exits and that APPROVED/REJECTED are terminals in the map. `Application` is what mutates status after the check.

## 5-Minute Deep Explanation

Compare with `ReviewStatus` on `ReviewCase`. Mention Flyway/JPA store the enum as STRING on `ApplicationEntity`. Discuss adding a new status would require map + aggregate + DB migration.

## 中文口語重點

- 查表在 `ALLOWED_TRANSITIONS`
- `next == null` → false
- 終態沒有出邊

## Whiteboard Sketch

- **What to draw:** same diagram as Application page
- **Drawing order:** map entries first
- **Narration order:** query API then callers

## Common Follow-Up Questions

- Can SUBMITTED go to CANCELLED?
- How is the enum persisted?

## Common Mistakes

- Inventing extra transitions not in the map
- Confusing review status with application status

## Current Limitations

- No compensating transitions (e.g. re-open rejected)

## Source File

[ApplicationStatus.java](../../../../src/main/java/com/tlbank/lending/domain/application/ApplicationStatus.java)
""",
        )
    )

    # 3 ApplicationRepositoryImpl
    pages.append(
        (
            "infrastructure",
            "ApplicationRepositoryImpl",
            f"""
## One-Sentence Summary

JPA adapter implementing the domain `ApplicationRepository` port with entity ↔ aggregate mapping.

## 中文一句話

基礎設施適配器：把 `Application` 聚合同 `ApplicationEntity` 互轉並透過 Spring Data 存取。

## Why This Class Exists

Keep JPA out of the domain while still loading/saving the aggregate.

## Responsibilities

- `findById` / `save` / `findByStatus`
- Map embeddables, documents, workflow histories
- Resolve product code ↔ numeric `product_id`

## Runtime Execution Flow

Save path:

1. Find existing entity by `applicationNo` or create new.
2. `applyToEntity` / `toEntity` copies fields.
3. Sync histories/documents append-only for new items.
4. `applicationJpaRepository.save` then `toDomain`.

## Dependencies

### Depends On

- `ApplicationJpaRepository`, `CardProductJpaRepository`
- `ApplicationEntity` and child entities

### Called By

- Application services using `ApplicationRepository`

### Calls

- Spring Data JPA repositories; private mappers

## Important Public Methods

{method('Optional<Application> findById(ApplicationId id)', 'Load aggregate by business id', input='ApplicationId', output='Optional<Application>')}

{method('Application save(Application application)', 'Insert or update mapped entity', input='aggregate', output='reloaded aggregate', side='Persists applications + cascaded children')}

{method('List<Application> findByStatus(ApplicationStatus status)', 'Query by status', input='status', output='list of aggregates')}

## Design Decisions

- Port in domain package; impl in infrastructure
- Histories/documents sync by appending new domain items beyond existing collection size
- Product resolved by code first, then numeric id fallback

## Trade-offs and Alternatives

- Manual mapping vs MapStruct — explicit mapping is interview-visible
- No dedicated unit test class for this adapter (covered indirectly)

## Related Classes

- [Application](../domain/Application.md), [ApplicationEntity](ApplicationEntity.md), domain port `ApplicationRepository`

## Related Configuration

- Datasource / JPA via Spring profiles ([application.yml](../../../../src/main/resources/application.yml), [application-dev.yml](../../../../src/main/resources/application-dev.yml))
- Flyway migrations under `src/main/resources/db/migration/`

## Related Tests

- **No dedicated `ApplicationRepositoryImplTest`**
- Indirect: [ApplicationAppServiceTest.java](../../../../src/test/java/com/tlbank/lending/application/application/ApplicationAppServiceTest.java), [ApplicationFlowIntegrationTest.java](../../../../src/test/java/com/tlbank/lending/application/ApplicationFlowIntegrationTest.java)

## Related ADRs and Design Documents

- [0001-use-clean-architecture.md](../../../decisions/0001-use-clean-architecture.md)
- [05-database-design.md](../../../design/05-database-design.md)

## Related Interview Questions

{q_links(['Q027','Q028','Q029','Q034','Q092','Q101','Q102','Q107','Q210','Q258','Q297'])}

## 30-Second Explanation

`ApplicationRepositoryImpl` is the JPA adapter for `ApplicationRepository`. It maps between the domain aggregate and `ApplicationEntity`, including documents and workflow history.

## 2-Minute Explanation

Explain port/adapter: services depend on the interface. Save merges into an existing row by `application_no`. Product id bridging uses card product codes. Mapping keeps domain free of Jakarta annotations.

## 5-Minute Deep Explanation

Walk `toDomain`/`toEntity`, append-only sync, and why that can miss in-place edits to old history rows. Mention missing dedicated unit tests as a documentation honesty point.

## 中文口語重點

- domain port／infrastructure impl
- `application_no` 對業務 ID
- history／document 採增量 append
- 無專屬單元測試類別

## Whiteboard Sketch

- **What to draw:** Domain ←port→ Impl → JPA → DB
- **Drawing order:** port first
- **Narration order:** find vs save

## Common Follow-Up Questions

- Why not put `@Entity` on the domain class?
- How are collections synced?

## Common Mistakes

- Calling this a domain service
- Claiming MapStruct is used

## Current Limitations

- No dedicated unit test for mapping edge cases
- Append-only sync assumptions

## Source File

[ApplicationRepositoryImpl.java](../../../../src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationRepositoryImpl.java)
""",
        )
    )

    # 4 ApplicationEntity
    pages.append(
        (
            "infrastructure",
            "ApplicationEntity",
            f"""
## One-Sentence Summary

JPA `@Entity` for table `applications`, including embeds and cascaded children.

## 中文一句話

`applications` 表的 JPA 實體：狀態用 `EnumType.STRING`，申請人／地址為 embed，文件與 history 一對多。

## Why This Class Exists

Persistence model separate from the domain aggregate.

## Responsibilities

- Map columns: `application_no`, `product_id`, `status`, `submitted_at`, …
- Embed `ApplicantEmbeddable` / `AddressEmbeddable`
- One-to-many `workflowHistories` and `documents` with cascade ALL

## Runtime Execution Flow

Not invoked directly by business code — `ApplicationRepositoryImpl` reads/writes it through Spring Data.

## Dependencies

### Depends On

- `BaseEntity`, Jakarta Persistence, Lombok
- `ApplicationStatus` enum (domain enum reused in persistence)

### Called By

- `ApplicationJpaRepository` / `ApplicationRepositoryImpl`

### Calls

- JPA lifecycle via provider

## Important Public Methods

Lombok `@Getter`/`@Setter` generate accessors. No custom business methods.

Notable members:

- `Long id` — DB identity
- `String applicationNo` — business key
- `ApplicationStatus status` — `@Enumerated(STRING)`
- embeds + collections above

## Design Decisions

- STRING enum storage (not ORDINAL)
- Surrogate `id` plus unique `application_no`
- Lazy one-to-many collections

## Trade-offs and Alternatives

- Sharing `ApplicationStatus` enum with domain couples packages slightly but avoids duplicate enums
- Alternative: separate persistence enum — not chosen

## Related Classes

- [ApplicationRepositoryImpl](ApplicationRepositoryImpl.md), [Application](../domain/Application.md)

## Related Configuration

- JPA/Hibernate + Flyway (`V3__create_applications.sql` and later alter migrations)

## Related Tests

- **No dedicated `ApplicationEntityTest`**
- Schema exercised via integration/app service tests

## Related ADRs and Design Documents

- [0007-h2-vs-sqlserver.md](../../../decisions/0007-h2-vs-sqlserver.md)
- [05-database-design.md](../../../design/05-database-design.md)

## Related Interview Questions

{q_links(['Q029','Q089','Q090','Q091','Q092','Q127','Q259'])}

## 30-Second Explanation

`ApplicationEntity` is the JPA mapping for applications. Status is stored as a string enum; applicant/address are embeds; histories and documents cascade.

## 2-Minute Explanation

Contrast with domain `Application`: entity has DB `id` and `product_id` long FK-ish field, while domain uses `ApplicationId` and `CardProductId`. Repository maps between them.

## 5-Minute Deep Explanation

Discuss cascade/lazy defaults, why STRING enums matter for Flyway stability, and coupling from reusing the domain enum in the entity.

## 中文口語重點

- 表名 `applications`
- `EnumType.STRING`
- 業務鍵 `application_no`
- 無業務方法，只有對應欄位

## Whiteboard Sketch

- **What to draw:** table boxes + embed + child tables
- **Drawing order:** parent row then children
- **Narration order:** keys then enums then relations

## Common Follow-Up Questions

- ORDINAL vs STRING?
- Why both `id` and `application_no`?

## Common Mistakes

- Treating the entity as the aggregate root in domain talk
- Inventing business methods on the entity

## Current Limitations

- No dedicated entity unit tests
- Domain enum imported into persistence package

## Source File

[ApplicationEntity.java](../../../../src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationEntity.java)
""",
        )
    )

    # 5 SecurityConfig
    pages.append(
        (
            "security",
            "SecurityConfig",
            f"""
## One-Sentence Summary

Session-based Spring Security filter chain: form login, roles, CSRF carve-outs, MDC filter, max one session.

## 中文一句話

Session 認證主設定：表單登入、角色授權、API CSRF 忽略、MDC 濾器、同時僅一 session。

## Why This Class Exists

Centralize HTTP security for web + `/api/v1/**` without JWT in the current codebase.

See [0006-session-over-jwt.md](../../../decisions/0006-session-over-jwt.md).

## Responsibilities

- Beans: `PasswordEncoder` (BCrypt strength 12), `AuthenticationManager`, `SessionRegistry`, `SecurityFilterChain`
- Authorize request matchers (public apply/OTP/products vs REVIEWER/ADMIN)
- Form login/logout handlers; session IF_REQUIRED; maximumSessions(1)

## Runtime Execution Flow

1. Request hits filter chain; `MdcLoggingFilter` runs before username/password filter.
2. AuthorizeHttpRequests decides permit/role/authenticated.
3. Unauthenticated protected calls use entry point; forbidden uses access denied handler.
4. Login posts to `/api/v1/auth/login`; success/failure handlers run.
5. Logout invalidates session and deletes `JSESSIONID`.

## Dependencies

### Depends On

- `UserDetailsServiceImpl`, login/logout/access handlers, `MdcLoggingFilter`, `Environment`

### Called By

- Spring Boot security auto-config / context refresh
- Exercised by [SecurityIntegrationTest.java](../../../../src/test/java/com/tlbank/lending/security/SecurityIntegrationTest.java) (context), not a unit test named SecurityConfigTest

### Calls

- `HttpSecurity` DSL APIs

## Important Public Methods

{method('PasswordEncoder passwordEncoder()', 'BCryptPasswordEncoder(12)', output='PasswordEncoder bean')}

{method('AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder)', 'DaoAuthenticationProvider + ProviderManager', output='AuthenticationManager')}

{method('SessionRegistry sessionRegistry()', 'In-memory session registry for max sessions', output='SessionRegistry')}

{method('SecurityFilterChain securityFilterChain(HttpSecurity http, SessionRegistry sessionRegistry)', 'Main HTTP security DSL', output='SecurityFilterChain', sec='Defines public vs REVIEWER/ADMIN routes; session policy; CSRF ignore /api/**')}

## Design Decisions

- Session cookies, not JWT
- Many applicant APIs `permitAll` (portfolio choice — discuss as limitation)
- CSRF ignored for `/api/**`
- Dev profile disables frame options for H2 console

## Trade-offs and Alternatives

- JWT/stateless API — explicitly not chosen (ADR 0006)
- Tightening permitAll on applications would be a production hardening step

## Related Classes

- Handlers under `security/handler`, `MdcLoggingFilter`, `UserDetailsServiceImpl`
- Future topic `topics/03-security.md` (**Pending**)

## Related Configuration

- Spring Security defaults + profile-specific headers
- No Redis session store in this project

## Related Tests

- [SecurityIntegrationTest.java](../../../../src/test/java/com/tlbank/lending/security/SecurityIntegrationTest.java)
- **No `SecurityConfigTest` unit class**

## Related ADRs and Design Documents

- [0006-session-over-jwt.md](../../../decisions/0006-session-over-jwt.md)
- [07-security-design.md](../../../design/07-security-design.md)

## Related Interview Questions

{q_links(['Q017','Q020','Q023','Q025','Q033','Q070','Q077','Q081','Q083','Q084','Q085','Q087','Q112','Q203','Q207','Q208','Q250'])}

## 30-Second Explanation

`SecurityConfig` builds a session SecurityFilterChain: BCrypt(12), form login to `/api/v1/auth/login`, role rules for review/admin, CSRF ignored on `/api/**`, and a single concurrent session.

## 2-Minute Explanation

Call out permitAll on OTP/products/many application routes versus REVIEWER/ADMIN for review/admin. Mention MDC filter placement and IF_REQUIRED sessions. Stress: not JWT, not Redis sessions.

## 5-Minute Deep Explanation

Walk matcher order, exception handling, logout cookie deletion, and production risks of broad permitAll. Link ADR 0006 for the session choice.

## 中文口語重點

- Session，不是 JWT
- BCrypt strength 12
- `/api/**` CSRF ignore
- maximumSessions(1)
- 申請相關多為 permitAll（需當限制說明）

## Whiteboard Sketch

- **What to draw:** filters → authorize → login/session
- **Drawing order:** MDC, authn, authz, handlers
- **Narration order:** public vs role routes

## Common Follow-Up Questions

- Why ignore CSRF on APIs?
- How would JWT change this class?
- What does maximumSessions(1) do?

## Common Mistakes

- Saying Redis backs sessions
- Claiming all APIs require authentication

## Current Limitations

- Broad permitAll on applicant APIs
- Session registry is in-memory (not clustered)

## Source File

[SecurityConfig.java](../../../../src/main/java/com/tlbank/lending/security/config/SecurityConfig.java)
""",
        )
    )

    # 6 IdempotencyService
    pages.append(
        (
            "application",
            "IdempotencyService",
            f"""
## One-Sentence Summary

Coordinates idempotent API execution: same key+body replays; same key+different body conflicts; optional blank key bypasses.

## 中文一句話

冪等協調器：同 Key 同 Body 回放；同 Key 不同 Body 409；無 Key 直接執行。

## Why This Class Exists

Protect create-like endpoints from double submits using `IdempotencyStore` (Redis or in-memory).

Redis here is **idempotency storage**, not cache/session — [0003-use-redis-idempotency.md](../../../decisions/0003-use-redis-idempotency.md).

## Responsibilities

- Hash request body (SHA-256 of JSON)
- Find cached entry / acquire lock / run action / save entry / release lock
- Rebuild `ResponseEntity<ApiResponse<T>>` from stored JSON

## Runtime Execution Flow

1. Blank key → `action.get()` immediately.
2. Else `find(storageKey)`; hash mismatch → `IDEMPOTENCY_KEY_CONFLICT`.
3. Hit → rebuild cached response.
4. Miss → `tryAcquireLock`; failure → `IDEMPOTENCY_KEY_IN_PROGRESS`.
5. Run action; `save` entry with TTL; always `releaseLock` in finally.

## Dependencies

### Depends On

- `IdempotencyStore`, `ObjectMapper`
- Properties `tlbank.idempotency.ttl-hours`, `tlbank.idempotency.key-prefix`

### Called By

- API controllers wrapping create (e.g. applications create with `Idempotency-Key`)

### Calls

- Store `find` / `tryAcquireLock` / `save` / `releaseLock`
- private `hashRequest`, `serializeBody`, `rebuildResponse`

## Important Public Methods

{method('ResponseEntity<ApiResponse<T>> execute(String idempotencyKey, Object requestBody, Supplier<ResponseEntity<ApiResponse<T>>> action)', 'Idempotent execute wrapper', input='key, body, action supplier', output='HTTP ApiResponse entity', biz='Deduplicate creates', side='May read/write idempotency store + lock keys')}

## Design Decisions

- 30s lock TTL constant
- Entry TTL from `ttl-hours` (default 24)
- Prefix keys for namespace isolation

## Trade-offs and Alternatives

- Optional key (compatibility) vs mandatory key
- Store abstraction allows Redis or memory without changing this class

## Related Classes

- [RedisIdempotencyStore](../infrastructure/RedisIdempotencyStore.md), `InMemoryIdempotencyStore`, `IdempotencyStore`, `IdempotencyEntry`

## Related Configuration

- [application.yml](../../../../src/main/resources/application.yml) — `tlbank.idempotency.ttl-hours`, `key-prefix`
- [application-dev.yml](../../../../src/main/resources/application-dev.yml) — `tlbank.idempotency.store: redis`

## Related Tests

- [IdempotencyServiceTest.java](../../../../src/test/java/com/tlbank/lending/application/idempotency/IdempotencyServiceTest.java)
- [ApplicationIdempotencyIntegrationTest.java](../../../../src/test/java/com/tlbank/lending/application/ApplicationIdempotencyIntegrationTest.java)

## Related ADRs and Design Documents

- [0003-use-redis-idempotency.md](../../../decisions/0003-use-redis-idempotency.md)

## Related Interview Questions

{q_links(['Q009','Q011','Q035','Q063','Q067','Q132','Q269'])}

## 30-Second Explanation

`IdempotencyService.execute` skips work when the key is blank; otherwise it caches successful responses by key and body hash, conflicts on hash mismatch, and uses a short Redis/memory lock while the first call runs.

## 2-Minute Explanation

Explain storage key = prefix + key, lock key suffix `:lock`, SHA-256 body hash, and rebuild path. Stress Redis store is only for idempotency entries.

## 5-Minute Deep Explanation

Discuss race with lock TTL, what happens if serialization fails, and why controllers own the header while this service stays generic.

## 中文口語重點

- 空白 Key → 不冪等
- 衝突 ErrorCode：IDEMPOTENCY_KEY_CONFLICT／IN_PROGRESS
- Lock TTL 30 秒；資料 TTL 預設 24 小時
- Redis ≠ session／cache

## Whiteboard Sketch

- **What to draw:** find → lock → action → save → unlock
- **Drawing order:** happy path then conflict branches
- **Narration order:** header optional caveat first

## Common Follow-Up Questions

- What if two requests race?
- Why hash the body?
- Memory vs Redis store?

## Common Mistakes

- Calling Redis a response cache for all GETs
- Saying keys are mandatory

## Current Limitations

- Relies on store TTL for cleanup
- Lock TTL fixed at 30 seconds

## Source File

[IdempotencyService.java](../../../../src/main/java/com/tlbank/lending/application/idempotency/IdempotencyService.java)
""",
        )
    )

    # 7 RedisIdempotencyStore
    pages.append(
        (
            "infrastructure",
            "RedisIdempotencyStore",
            f"""
## One-Sentence Summary

Redis `IdempotencyStore` using `StringRedisTemplate` SET/GET/SETNX/DELETE with JSON entries.

## 中文一句話

Redis 冪等儲存實作：`@ConditionalOnProperty(store=redis)`，只服務冪等，不是一般快取。

## Why This Class Exists

Provide durable-enough idempotency records when `tlbank.idempotency.store=redis`.

## Responsibilities

- `find` / `save` entry JSON with TTL
- `tryAcquireLock` via `setIfAbsent`
- `releaseLock` via delete

## Runtime Execution Flow

Used only through `IdempotencyService` when this bean is active.

## Dependencies

### Depends On

- `StringRedisTemplate`, `ObjectMapper`

### Called By

- `IdempotencyService` (when Redis store bean present)

### Calls

- Redis value operations

## Important Public Methods

{method('Optional<IdempotencyEntry> find(String key)', 'GET JSON and deserialize', output='Optional entry')}

{method('void save(String key, IdempotencyEntry entry, long ttlSeconds)', 'SET JSON with TTL', side='Writes Redis key')}

{method('boolean tryAcquireLock(String lockKey, long ttlSeconds)', 'SETNX-style lock', output='true if acquired')}

{method('void releaseLock(String lockKey)', 'DELETE lock key')}

## Design Decisions

- Conditional bean on property `redis`
- Fail soft on deserialize (log warn, empty)
- Fail hard on save serialize errors

## Trade-offs and Alternatives

- Sibling `InMemoryIdempotencyStore` for `store=memory` / tests
- Not a general Spring Cache manager

## Related Classes

- [IdempotencyService](../application/IdempotencyService.md), `InMemoryIdempotencyStore`, `IdempotencyStore`

## Related Configuration

- `tlbank.idempotency.store=redis` in [application-dev.yml](../../../../src/main/resources/application-dev.yml)
- Redis connection under Spring Data Redis settings in the same profiles
- Compose Redis service: [docker-compose.yml](../../../../docker-compose.yml)

## Related Tests

- **No dedicated `RedisIdempotencyStoreTest`**
- Behavior covered via [IdempotencyServiceTest.java](../../../../src/test/java/com/tlbank/lending/application/idempotency/IdempotencyServiceTest.java) (typically with memory/mock store) and [ApplicationIdempotencyIntegrationTest.java](../../../../src/test/java/com/tlbank/lending/application/ApplicationIdempotencyIntegrationTest.java) depending on test profile

## Related ADRs and Design Documents

- [0003-use-redis-idempotency.md](../../../decisions/0003-use-redis-idempotency.md)

## Related Interview Questions

{q_links(['Q016','Q028','Q132','Q134','Q229','Q268'])}

## 30-Second Explanation

`RedisIdempotencyStore` persists idempotency entries and locks in Redis when `tlbank.idempotency.store=redis`. It is not used for HTTP sessions or product cache.

## 2-Minute Explanation

Show SET with TTL for entries and setIfAbsent for locks. Mention the memory alternative and conditional beans.

## 5-Minute Deep Explanation

Discuss deserialize warn path, lock deletion, and operational need for Redis in dev compose. Contrast with `InMemoryCacheStore` for products/parameters.

## 中文口語重點

- 條件：`store=redis`
- SETNX 鎖；TTL 清資料
- 不是 session，也不是商品快取

## Whiteboard Sketch

- **What to draw:** IdempotencyService → Redis keys (entry + lock)
- **Drawing order:** entry key then lock key
- **Narration order:** property switch memory/redis

## Common Follow-Up Questions

- What if Redis is down?
- Difference from cache store?

## Common Mistakes

- “We use Redis for sessions”
- “Redis caches all API responses”

## Current Limitations

- No dedicated store unit test in repo
- Soft-fail find may hide corruption until conflicts appear

## Source File

[RedisIdempotencyStore.java](../../../../src/main/java/com/tlbank/lending/infrastructure/idempotency/RedisIdempotencyStore.java)
""",
        )
    )

    # 8 OtpAppService
    pages.append(
        (
            "application",
            "OtpAppService",
            f"""
## One-Sentence Summary

Transactional OTP send/verify use cases; updates application to OTP_VERIFIED on successful verify.

## 中文一句話

OTP 發送／驗證用例：參數驅動過期與重試；驗證成功可把申請從 INIT 轉 OTP_VERIFIED。

## Why This Class Exists

Isolate OTP rules and notifications from controllers while coordinating `OtpRecord` + `Application`.

## Responsibilities

- Cancel previous PENDING OTP for mobile on send
- Generate 6-digit code; persist; notify (mock channels underneath)
- Verify code; transition application when INIT

## Runtime Execution Flow

Send:

1. Cancel latest pending OTP for mobile if any.
2. Read expire/retry params from `SystemParameterService`.
3. Save new PENDING `OtpRecord`; put code in `AuditContext`; notify.

Verify:

1. Load latest pending OTP or `OTP_EXPIRED`.
2. `otpRecord.verify(...)`; save.
3. Load application; if INIT → `verifyOtp`; if already OTP_VERIFIED OK; else workflow error.

## Dependencies

### Depends On

- `OtpRepository`, `ApplicationRepository`, `NotificationService`, `SystemParameterService`, `Clock`

### Called By

- OTP API controller
- `OtpAppServiceTest`

### Calls

- Domain `OtpRecord` / `Application` methods; notification port

## Important Public Methods

{method('OtpResponse sendOtp(SendOtpCommand command)', 'Issue new OTP', input='mobile/purpose/applicationId command', output='masked mobile, expiry, maxRetry', tx='@Transactional', sec='@Auditable(OTP_SEND)', side='DB writes + notification send + audit context otpCode')}

{method('VerifyOtpResponse verifyOtp(VerifyOtpCommand command)', 'Verify OTP and maybe advance application', input='mobile, code, applicationId', output='success flag + applicationId', tx='@Transactional', sec='@Auditable(OTP_VERIFY_SUCCESS) — failures remapped in AuditAspect', side='May update application status')}

## Design Decisions

- SecureRandom 6-digit codes
- Parameters `OTP/expire_minutes`, `OTP/max_retry`
- Notification failures are still after persistence of OTP (send path)

## Trade-offs and Alternatives

- Notifications are mock implementations in this repo — not real SMS gateways
- Audit stores context carefully; detail builder sanitizes sensitive fields

## Related Classes

- [Application](../domain/Application.md), `OtpRecord` (High source-map **Pending** Phase 4), [AuditAspect](../common/AuditAspect.md), `NotificationService` / mocks

## Related Configuration

- System parameters table/service defaults (expire 5, max retry 3 if missing)
- Security permits `/api/v1/otp/**` in [SecurityConfig](../security/SecurityConfig.md)

## Related Tests

- [OtpAppServiceTest.java](../../../../src/test/java/com/tlbank/lending/application/otp/OtpAppServiceTest.java)

## Related ADRs and Design Documents

- [08-workflow-design.md](../../../design/08-workflow-design.md)
- [03-business-feature-handbook.md](../../../handbook/03-business-feature-handbook.md)

## Related Interview Questions

{q_links(['Q046','Q103','Q135','Q137','Q138','Q139','Q150','Q164','Q204','Q205','Q251','Q272','Q277'])}

## 30-Second Explanation

`OtpAppService` sends OTPs by cancelling old pending records, saving a new code, and notifying. Verify checks the code then moves an INIT application to OTP_VERIFIED.

## 2-Minute Explanation

Cover parameter-driven TTL/retry, SecureRandom, audit annotations, and the status guard on verify. Mention mock notifications.

## 5-Minute Deep Explanation

Discuss AuditAspect failure action remap for OTP verify, why application must be INIT, and rate-limiting as **Not implemented** beyond business max retry.

## 中文口語重點

- 發送前取消舊 PENDING
- 驗證成功才可能改 Application 狀態
- 通知是 mock
- `@Transactional` 包住寫入

## Whiteboard Sketch

- **What to draw:** send vs verify sequences
- **Drawing order:** send first
- **Narration order:** parameters then domain

## Common Follow-Up Questions

- What if application is already SUBMITTED?
- Where is the OTP code logged/audited?

## Common Mistakes

- Calling SMS a real telecom integration
- Forgetting old PENDING cancellation

## Current Limitations

- No dedicated rate limit filter on send endpoint
- Mock notification channels

## Source File

[OtpAppService.java](../../../../src/main/java/com/tlbank/lending/application/otp/service/OtpAppService.java)
""",
        )
    )

    # 9 ReviewAppService
    pages.append(
        (
            "application",
            "ReviewAppService",
            f"""
## One-Sentence Summary

Review use cases: search/detail, start review, approve/reject with dual-aggregate updates and domain events.

## 中文一句話

徵審用例：同時更新 `ReviewCase` 與 `Application`，核准／拒絕後發佈領域事件。

## Why This Class Exists

Coordinate credit review without putting multi-aggregate rules in controllers.

## Responsibilities

- Search/page review cases; detail with masked applicant
- Start review on case + application when needed
- Approve/reject both aggregates; publish approved/rejected events
- Add remarks

## Runtime Execution Flow

Approve:

1. Load `ReviewCase` + linked `Application`.
2. `reviewCase.approve`; ensure application UNDER_REVIEW; `application.approve`.
3. Save both.
4. Publish `ApplicationApprovedEvent`.

## Dependencies

### Depends On

- `ReviewCaseRepository`, `ApplicationRepository`, `CardProductRepository`, `ApplicationEventPublisher`, `Clock`

### Called By

- `ReviewApiController` / web review controllers
- `ReviewAppServiceTest`, `ReviewFlowIntegrationTest`

### Calls

- Domain verbs on both aggregates; event publish

## Important Public Methods

{method('PageResponse<ReviewCaseSummaryResponse> searchCases(ReviewCaseSearchCriteria criteria, Pageable pageable)', 'Paged search', tx='@Transactional(readOnly = true)')}

{method('ReviewCaseDetailResponse getCaseDetail(String reviewCaseId)', 'Detail + application projection', tx='@Transactional(readOnly = true)', sec='Masks applicant fields in DTO')}

{method('void startCaseReview(String reviewCaseId, String operator)', 'Start review on case/application when still pending/submitted', tx='@Transactional')}

{method('void approveCase(ApproveCaseCommand command)', 'Approve both aggregates + event', tx='@Transactional', sec='@Auditable(APPLICATION_APPROVE)', side='ApplicationApprovedEvent')}

{method('void rejectCase(RejectCaseCommand command)', 'Reject both aggregates + event', tx='@Transactional', sec='@Auditable(APPLICATION_REJECT)', side='ApplicationRejectedEvent')}

{method('ReviewRemarkResponse addRemark(AddRemarkCommand command)', 'Append remark', tx='@Transactional')}

## Design Decisions

- Dual write in one transaction
- `ensureApplicationUnderReview` auto-starts application review if still SUBMITTED
- Events after successful saves

## Trade-offs and Alternatives

- Single TX across two aggregates vs eventual consistency — chosen for simplicity
- Review case creation happens in `ReviewEventHandler` on submit, not here

## Related Classes

- [ReviewCase](../domain/ReviewCase.md), [Application](../domain/Application.md), [NotificationEventHandler](../infrastructure/NotificationEventHandler.md), `ReviewEventHandler`

## Related Configuration

- Security: `/api/v1/review/**` requires REVIEWER or ADMIN ([SecurityConfig](../security/SecurityConfig.md))

## Related Tests

- [ReviewAppServiceTest.java](../../../../src/test/java/com/tlbank/lending/application/review/ReviewAppServiceTest.java)
- [ReviewFlowIntegrationTest.java](../../../../src/test/java/com/tlbank/lending/application/ReviewFlowIntegrationTest.java)

## Related ADRs and Design Documents

- [08-workflow-design.md](../../../design/08-workflow-design.md)
- [07-security-design.md](../../../design/07-security-design.md)

## Related Interview Questions

{q_links(['Q104','Q109','Q110','Q130','Q148','Q209','Q214'])}

## 30-Second Explanation

`ReviewAppService` runs credit review. Approve/reject update both `ReviewCase` and `Application` in one transaction, then publish domain events for notifications.

## 2-Minute Explanation

Describe search/detail, startCaseReview gates, and why submit creates the case via `ReviewEventHandler`. Mention role-restricted APIs.

## 5-Minute Deep Explanation

Discuss dual-aggregate TX risks, event ordering, and masking in summaries. Contrast method names `approveCase`/`rejectCase` with domain `approve`/`reject`.

## 中文口語重點

- 兩個聚合同一 TX
- 事件在 save 之後
- ReviewCase 多半在 submit 事件時建立
- API 需 REVIEWER／ADMIN

## Whiteboard Sketch

- **What to draw:** ReviewCase ∥ Application boxes updated together → events
- **Drawing order:** load, mutate, save, publish
- **Narration order:** security roles first

## Common Follow-Up Questions

- What if application save fails after review case approve in memory?
- Who creates the ReviewCase?

## Common Mistakes

- Inventing method names `approve`/`reject` on the service
- Forgetting the application aggregate update

## Current Limitations

- No saga/compensation beyond DB transaction rollback
- Relies on event handler to create cases on submit

## Source File

[ReviewAppService.java](../../../../src/main/java/com/tlbank/lending/application/review/service/ReviewAppService.java)
""",
        )
    )

    # 10 ReviewCase
    pages.append(
        (
            "domain",
            "ReviewCase",
            f"""
## One-Sentence Summary

Review aggregate: PENDING → UNDER_REVIEW → APPROVED/REJECTED with remarks.

## 中文一句話

徵審聚合根：狀態與備註在領域內；由 `ReviewEventHandler` 在申請送出後建立。

## Why This Class Exists

Separate credit-review workflow from the application lifecycle while remaining linked by `applicationId`.

## Responsibilities

- Factory `createFor`
- Assign reviewer; start/approve/reject; add remarks
- Enforce review status transitions

## Runtime Execution Flow

1. `ReviewEventHandler` hears `ApplicationSubmittedEvent` → `ReviewCase.createFor` → save.
2. Reviewers call service methods that delegate to domain verbs.
3. Approve/reject require `UNDER_REVIEW` and set `reviewedAt`.

## Dependencies

### Depends On

- `ReviewCaseId`, `ReviewStatus`, `ReviewRemark`, `WorkflowException`

### Called By

- `ReviewAppService`, `ReviewEventHandler`
- `ReviewCaseTest`

### Calls

- private `transitionTo`, `addRemarkInternal`

## Important Public Methods

{method('static ReviewCase createFor(String applicationId)', 'Factory for PENDING case', input='application id string', output='new ReviewCase')}

{method('void assign(String username)', 'Set assignedTo', input='username')}

{method('void startReview(String operator)', 'PENDING → UNDER_REVIEW + remark', input='operator')}

{method('void approve(String operator, String remark)', 'UNDER_REVIEW → APPROVED', input='operator, remark', side='reviewedAt now')}

{method('void reject(String operator, String remark)', 'UNDER_REVIEW → REJECTED', input='operator, remark', side='reviewedAt now')}

{method('void addRemark(String content, String operator)', 'Append remark without status change')}

## Design Decisions

- Remarks auto-added on start/approve/reject
- Stricter approve/reject precondition than application aggregate (must already be UNDER_REVIEW)

## Trade-offs and Alternatives

- Could embed review inside `Application` — split keeps bounded contexts clearer for interviews

## Related Classes

- [ReviewAppService](../application/ReviewAppService.md), [Application](Application.md), `ReviewEventHandler`

## Related Configuration

- None directly

## Related Tests

- [ReviewCaseTest.java](../../../../src/test/java/com/tlbank/lending/domain/review/ReviewCaseTest.java)
- [ReviewAppServiceTest.java](../../../../src/test/java/com/tlbank/lending/application/review/ReviewAppServiceTest.java)
- [ReviewFlowIntegrationTest.java](../../../../src/test/java/com/tlbank/lending/application/ReviewFlowIntegrationTest.java)

## Related ADRs and Design Documents

- [08-workflow-design.md](../../../design/08-workflow-design.md)
- [0002-use-ddd.md](../../../decisions/0002-use-ddd.md)

## Related Interview Questions

{q_links(['Q041','Q045','Q049','Q145','Q146','Q147','Q232','Q273'])}

## 30-Second Explanation

`ReviewCase` is the review aggregate. It starts pending, moves under review, then approve/reject with remarks. It is created when an application is submitted.

## 2-Minute Explanation

Contrast `ReviewStatus` vs `ApplicationStatus`. Explain factory and why approve requires UNDER_REVIEW. Point to event handler creation.

## 5-Minute Deep Explanation

Discuss consistency with `ReviewAppService.ensureApplicationUnderReview` and remark list growth.

## 中文口語重點

- createFor → PENDING
- approve／reject 必須 UNDER_REVIEW
- 與 Application 用 applicationId 關聯

## Whiteboard Sketch

- **What to draw:** ReviewStatus mini state machine
- **Drawing order:** PENDING → UNDER_REVIEW → terminals
- **Narration order:** creation event then verbs

## Common Follow-Up Questions

- Who calls `createFor`?
- Can you approve from PENDING?

## Common Mistakes

- Merging review status into ApplicationStatus
- Saying controller creates the case directly

## Current Limitations

- No re-assign workflow beyond `assign`
- No multi-reviewer concurrency controls in domain

## Source File

[ReviewCase.java](../../../../src/main/java/com/tlbank/lending/domain/review/ReviewCase.java)
""",
        )
    )

    # 11 AuditAspect
    pages.append(
        (
            "common",
            "AuditAspect",
            f"""
## One-Sentence Summary

AOP `@Around` advice that writes SUCCESS/FAILURE audit logs for `@Auditable` methods.

## 中文一句話

攔截 `@Auditable`：成功／失敗寫入稽核；OTP 驗證失敗會改寫成 OTP_VERIFY_FAILED。

## Why This Class Exists

Cross-cutting audit without cluttering every use case.

## Responsibilities

- Resolve username from SecurityContext (or ANONYMOUS)
- Resolve client IP
- On success: async save SUCCESS with sanitized detail
- On failure: async save FAILURE (with OTP action remap); rethrow
- Clear `AuditContext` in finally

## Runtime Execution Flow

1. Match `@annotation(auditable)`.
2. `proceed()`.
3. Success → `auditLogWriter.saveAsync(...)`.
4. Exception → remap action if OTP verify; save FAILURE; throw.
5. `AuditContext.clear()`.

## Dependencies

### Depends On

- `AuditLogWriter`, `HttpServletRequest`
- `AuditDetailBuilder`, `AuditIpResolver`, `AuditContext`

### Called By

- Spring AOP runtime for `@Auditable` services (OTP, application, review, …)

### Calls

- `joinPoint.proceed`, `auditLogWriter.saveAsync`

## Important Public Methods

{method('Object audit(ProceedingJoinPoint joinPoint, Auditable auditable)', 'Around advice entry', input='join point + annotation', output='proceed result or throw', sec='Reads SecurityContext username', side='Persists audit rows asynchronously via writer')}

## Design Decisions

- Failure action remap only for `OTP_VERIFY_SUCCESS` → `OTP_VERIFY_FAILED`
- Detail from args + `AuditContext` suffix
- Exception messages truncated to 500 chars

## Trade-offs and Alternatives

- Async writer isolation vs sync audit in same TX — writer uses separate concerns (see High page later)
- AOP magic can surprise; annotation keeps call sites clean

## Related Classes

- `AuditLogWriter`, `AuditDetailBuilder`, `Auditable`, services with `@Auditable`

## Related Configuration

- Spring AOP proxying (Boot auto-config)
- No special property beyond datasource for audit tables

## Related Tests

- [AuditAspectTest.java](../../../../src/test/java/com/tlbank/lending/common/audit/AuditAspectTest.java)
- Helper [AuditableTestService.java](../../../../src/test/java/com/tlbank/lending/common/audit/AuditableTestService.java)

## Related ADRs and Design Documents

- [11-audit-logging.md](../../../design/11-audit-logging.md)

## Related Interview Questions

{q_links(['Q024','Q072','Q073','Q074','Q076','Q135','Q277','Q282','Q291'])}

## 30-Second Explanation

`AuditAspect` wraps `@Auditable` methods, writes SUCCESS or FAILURE audit logs with user/IP/detail, and remaps OTP verify failures to a dedicated action.

## 2-Minute Explanation

Explain anonymous users, detail builder sanitization, and finally clearing AuditContext so OTP codes do not leak across requests.

## 5-Minute Deep Explanation

Discuss interaction with `AuditLogWriter` transaction settings and why audit must not swallow business exceptions.

## 中文口語重點

- `@Around @Auditable`
- 失敗仍拋出原例外
- OTP 驗證失敗 action 會改寫
- finally 清 `AuditContext`

## Whiteboard Sketch

- **What to draw:** advice around service method → AuditLogWriter
- **Drawing order:** before/after/throw paths
- **Narration order:** success then failure remap

## Common Follow-Up Questions

- Does audit rollback with business TX?
- How is OTP code prevented in logs?

## Common Mistakes

- Saying audit replaces business error handling
- Forgetting ANONYMOUS path

## Current Limitations

- Relies on HTTP request bean (web thread assumption)
- Async semantics depend on `AuditLogWriter` implementation

## Source File

[AuditAspect.java](../../../../src/main/java/com/tlbank/lending/common/audit/AuditAspect.java)
""",
        )
    )

    # 12 LocalDocumentStorageService
    pages.append(
        (
            "infrastructure",
            "LocalDocumentStorageService",
            f"""
## One-Sentence Summary

Local filesystem `DocumentStorageService`: validate extension/size, store under `tlbank.upload.base-path`.

## 中文一句話

本機檔案上傳實作：副檔名／大小檢查後寫入設定目錄，回傳相對路徑。

## Why This Class Exists

Portfolio-friendly storage without cloud object storage.

**Not** S3/GCS unless added later — currently local disk only.

## Responsibilities

- `@PostConstruct` ensure base directory
- Validate empty/extension/size (param `UPLOAD/max.size.mb`, default 10)
- Store as `applicationId/TYPE_timestamp.ext`

## Runtime Execution Flow

1. `validate` checks file.
2. Build target dir `base/applicationId`.
3. Copy stream with REPLACE_EXISTING.
4. Return relative path string for DB `DocumentInfo`.

## Dependencies

### Depends On

- `SystemParameterService`, `Clock`
- `@Value tlbank.upload.base-path`

### Called By

- `ApplicationAppService.uploadDocuments` via `DocumentStorageService` port

### Calls

- `Files.createDirectories` / `Files.copy`

## Important Public Methods

{method('void validate(MultipartFile file)', 'Extension + size + non-empty checks', input='multipart file', side='May throw BusinessException DOCUMENT_UPLOAD_FAILED')}

{method('String store(String applicationId, DocumentType documentType, MultipartFile file)', 'Persist file and return relative path', input='applicationId, type, file', output='relative path', side='Writes disk; re-validates')}

## Design Decisions

- Allowed extensions: jpg/jpeg/png/pdf
- Absolute normalized base path
- Relative path stored in domain/DB, not absolute

## Trade-offs and Alternatives

- Local disk vs S3 — local chosen for this repo
- Size limit from system parameters enables runtime tuning

## Related Classes

- [ApplicationAppService](../application/ApplicationAppService.md), port `DocumentStorageService`

## Related Configuration

- `tlbank.upload.base-path` in [application.yml](../../../../src/main/resources/application.yml) (`./uploads`)
- [application-dev.yml](../../../../src/main/resources/application-dev.yml) (`.../uploads/dev`)
- [application-staging.yml](../../../../src/main/resources/application-staging.yml) (`APP_UPLOAD_PATH`)

## Related Tests

- [LocalDocumentStorageServiceTest.java](../../../../src/test/java/com/tlbank/lending/infrastructure/storage/LocalDocumentStorageServiceTest.java)

## Related ADRs and Design Documents

- [15-file-upload-design.md](../../../design/15-file-upload-design.md)

## Related Interview Questions

{q_links(['Q008','Q053','Q106','Q177','Q178','Q179','Q180','Q181','Q182','Q208','Q216','Q255','Q298'])}

## 30-Second Explanation

`LocalDocumentStorageService` stores uploads on local disk under a configured base path after checking type and size. It returns a relative path for the application aggregate.

## 2-Minute Explanation

Cover allowed extensions, parameter-driven max size, directory layout, and why staging uses env path. Emphasize not cloud object storage.

## 5-Minute Deep Explanation

Discuss REPLACE_EXISTING, path normalization, and virus scanning as **Not implemented**.

## 中文口語重點

- 本機磁碟，不是 S3
- 副檔名白名單
- 路徑回傳相對字串
- base-path 依 profile

## Whiteboard Sketch

- **What to draw:** AppService → Storage → filesystem folders
- **Drawing order:** validate then store
- **Narration order:** config property first

## Common Follow-Up Questions

- Where is the max size configured?
- What is stored in DB vs disk?

## Common Mistakes

- Claiming cloud bucket integration
- Forgetting validate is also called from store

## Current Limitations

- Single-node filesystem (no shared volume story beyond staging path config)
- No malware scanning

## Source File

[LocalDocumentStorageService.java](../../../../src/main/java/com/tlbank/lending/infrastructure/storage/LocalDocumentStorageService.java)
""",
        )
    )

    # 13 NotificationEventHandler
    pages.append(
        (
            "infrastructure",
            "NotificationEventHandler",
            f"""
## One-Sentence Summary

Spring `@EventListener` that turns application lifecycle events into notification service calls (mock SMS/email underneath).

## 中文一句話

監聽申請送出／核准／拒絕事件並呼叫通知服務；通道為 mock，失敗只打 warn 不回滾業務。

## Why This Class Exists

Decouple notification side effects from `ApplicationAppService` / `ReviewAppService` transactions' core writes via events.

## Responsibilities

- Handle `ApplicationSubmittedEvent`, `ApplicationApprovedEvent`, `ApplicationRejectedEvent`
- Delegate to `NotificationService`
- Swallow notification exceptions after warn logs

## Runtime Execution Flow

1. Service publishes domain event after save.
2. Spring delivers event to listener methods.
3. NotificationService sends mock SMS/email templates.
4. On error: log warn; business TX already committed independently of this listener unless sharing TX semantics (default listener runs in caller thread after publish — still does not rethrow).

## Dependencies

### Depends On

- `NotificationService` (impl uses mock senders)

### Called By

- Spring event mechanism after publish from app services

### Calls

- `sendApplicationSubmittedNotification` / approved / rejected

## Important Public Methods

{method('void onApplicationSubmitted(ApplicationSubmittedEvent event)', 'Notify submit', input='event', side='Mock notify; errors logged')}

{method('void onApplicationApproved(ApplicationApprovedEvent event)', 'Notify approval', input='event', side='Mock notify; errors logged')}

{method('void onApplicationRejected(ApplicationRejectedEvent event)', 'Notify rejection', input='event', side='Mock notify; errors logged')}

## Design Decisions

- Try/catch per listener so notification outages do not explode the publisher path after commit attempts
- Uses Spring `ApplicationEventPublisher`, not a custom bus class

## Trade-offs and Alternatives

- Mock adapters vs real SMS/email providers — mocks only in this repository
- Sync listeners vs `@Async` — sync unless configured otherwise

## Related Classes

- [ApplicationAppService](../application/ApplicationAppService.md), [ReviewAppService](../application/ReviewAppService.md), `MockSmsSender`, `MockEmailSender`, `NotificationServiceImpl`
- Sibling `ReviewEventHandler` creates `ReviewCase` on submit

## Related Configuration

- Notification templates/parameters via notification module (mock)
- No Twilio/SendGrid properties as real integrations

## Related Tests

- [NotificationEventHandlerTest.java](../../../../src/test/java/com/tlbank/lending/infrastructure/event/NotificationEventHandlerTest.java)

## Related ADRs and Design Documents

- [09-module-design.md](../../../design/09-module-design.md)
- [08-workflow-design.md](../../../design/08-workflow-design.md)

## Related Interview Questions

{q_links(['Q002','Q038','Q044','Q099','Q100','Q109','Q130','Q233'])}

## 30-Second Explanation

`NotificationEventHandler` listens for submit/approve/reject events and calls `NotificationService`. In this repo those notifications are mocks; failures are logged, not turned into real carrier sends.

## 2-Minute Explanation

Tie to who publishes events, contrast with `ReviewEventHandler`, and stress mock vs real. Mention try/catch policy.

## 5-Minute Deep Explanation

Discuss transaction boundaries around event publish, whether listeners should be async for production, and current **Not implemented** real providers.

## 中文口語重點

- `@EventListener` 三支
- 底下是 mock SMS／email
- 例外吃掉打 warn
- 不是雲端通知服務

## Whiteboard Sketch

- **What to draw:** AppService publish → Listener → NotificationService → Mock senders
- **Drawing order:** event types as labeled arrows
- **Narration order:** mock caveat first

## Common Follow-Up Questions

- Are notifications transactional with approve?
- Difference from ReviewEventHandler?

## Common Mistakes

- Describing Twilio/SendGrid as integrated
- Saying a custom `DomainEventPublisher` class exists

## Current Limitations

- Mock-only channels
- Errors do not retry/dead-letter

## Source File

[NotificationEventHandler.java](../../../../src/main/java/com/tlbank/lending/infrastructure/event/NotificationEventHandler.java)
""",
        )
    )

    assert len(pages) == 14
    for i, (folder, name, body) in enumerate(pages):
        write(folder, name, body, i)

    print("DONE", len(pages))


if __name__ == "__main__":
    main()
