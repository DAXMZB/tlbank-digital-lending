# 19 – Cursor Implementation Roadmap

## How to Use This Document

This roadmap breaks the platform into 16 sprints, in the same order the actual codebase evolved (the Flyway
migration filenames `V11__extend_applications_for_sprint5.sql`, `V12__..._for_sprint6.sql`,
`V13__..._for_sprint8.sql`, and `V14__..._for_sprint9.sql` are literally named after this sprint plan,
confirming the mapping below). Each sprint section is self-contained and gives Cursor (or any AI coding
agent) everything needed to implement or verify that slice of the system against the rest of this `/docs`
folder.

**Before Sprint 1**, point Cursor at the whole `/docs` folder as context. **Before every sprint**, paste the
sprint's "Cursor Prompt" verbatim; it already references the specific docs and files Cursor needs to read.
After each sprint, verify the acceptance criteria before moving to the next one — later sprints assume
earlier ones are in place (e.g. Sprint 5 assumes Sprint 1's `BaseEntity`/`ApiResponse` and Sprint 2's security
context already exist).

---

## Sprint 1 — Project Bootstrap

**Goal:** Scaffold a Clean Architecture / DDD package skeleton, Maven build, dual-database configuration
(H2 + SQL Server), and the first seven schema migrations, producing a deployable empty shell.

**Business objective:** Establish a credible enterprise foundation before any business feature exists, so
every later sprint adds *behavior* rather than *infrastructure*.

**Technical objective:**

- Spring Boot 3.4.x / Java 17 Maven project with the dependency set in `00-sdd-overview.md` §4.
- Package skeleton: `domain`, `application`, `infrastructure`, `presentation`, `security`, `common`, each
  with a `package-info.java`.

- Cross-cutting primitives: `ApiResponse`, `PageResponse`, `FieldErrorDetail`, `ErrorCode`,
  `BusinessException`, `BaseEntity`.

- Dual Flyway migration sets (H2 + SQL Server) for the seven foundational tables.
- Profile-specific `application*.yml` files.

**Files to create:**

```
pom.xml
src/main/java/com/tlbank/lending/TlbankLendingApplication.java
src/main/java/com/tlbank/lending/package-info.java
src/main/java/com/tlbank/lending/common/entity/BaseEntity.java
src/main/java/com/tlbank/lending/common/response/{ApiResponse,PageResponse,FieldErrorDetail}.java
src/main/java/com/tlbank/lending/common/exception/{ErrorCode,BusinessException}.java
src/main/java/com/tlbank/lending/common/config/{JpaConfig,CommonConfig,SwaggerConfig,StandardApiResponses}.java
src/main/resources/application.yml
src/main/resources/application-dev.yml
src/main/resources/application-staging.yml
src/main/resources/application-prod.yml
src/main/resources/db/migration/V1..V7__*.sql   (H2 syntax)
src/main/resources/db/migration-sqlserver/V1..V7__*.sql   (T-SQL syntax)

```

**Packages to modify:** N/A (initial creation).

**Acceptance criteria:**

- `./mvnw clean compile` succeeds.
- `./mvnw spring-boot:run -Dspring-boot.run.profiles=dev` starts cleanly; H2 console reachable at
  `/h2-console`; Swagger UI reachable at `/swagger-ui.html`.

- Flyway reports `V1`–`V7` applied successfully against H2 on startup.
- `ApiResponse.success(...)`/`ApiResponse.error(...)` compile and serialize to the envelope shown in
  `06-api-specification.md` §1.

**Cursor Prompt:**
> Read `/docs/00-sdd-overview.md`, `/docs/02-architecture-design.md`, `/docs/03-package-structure.md`, and
> `/docs/05-database-design.md` (sections for tables `users`, `user_roles`, `card_products`,
> `product_features`, `applications`, `workflow_histories`, `application_documents`, `otp_records`,
> `review_cases`, `review_remarks`, `audit_logs`, `system_parameters`). Bootstrap a Spring Boot 3.4.x / Java
> 17 Maven project at the package root `com.tlbank.lending`, following exactly the layered package skeleton
> described in `03-package-structure.md` (with a `package-info.java` in every package per
> `18-coding-standards.md` §9). Create the shared response envelope (`ApiResponse`, `PageResponse`,
> `FieldErrorDetail`), the error model (`ErrorCode`, `BusinessException`), and `BaseEntity` for JPA auditing,
> exactly as specified in `10-error-handling.md` and `05-database-design.md`. Add Flyway migrations `V1`
> through `V7` creating the seven foundational tables, in **two parallel sets**: H2-syntax under
> `db/migration` and SQL-Server-syntax under `db/migration-sqlserver`, matching the final column/constraint
> definitions in `05-database-design.md` §3 (note: at this stage, build the *original* V1–V7 shapes before
> the later extension migrations — don't add V8+ columns yet, those belong to later sprints). Configure
> `application.yml` + per-profile files (`dev`, `staging`, `prod`) per `17-deployment-design.md` §5. Do not
> implement any business feature yet — this sprint only produces the empty, runnable shell.

---

## Sprint 2 — Security & Login

**Goal:** Session-based authentication and role-based authorization for internal users.

**Business objective:** Protect reviewer and admin capabilities behind real authentication before any of
those capabilities are built, so every subsequent sprint can rely on `hasRole(...)`/`hasAnyRole(...)` from
day one.

**Technical objective:** Implement the full `security.*` package and the `domain.user` aggregate, wired to
`users`/`user_roles` from Sprint 1, plus migration `V8` (last login tracking).

**Files to create:**

```
src/main/java/com/tlbank/lending/domain/user/{User,UserId,Role}.java
src/main/java/com/tlbank/lending/domain/user/repository/UserRepository.java
src/main/java/com/tlbank/lending/infrastructure/persistence/user/{UserEntity,UserJpaRepository,UserRepositoryImpl}.java
src/main/java/com/tlbank/lending/security/config/SecurityConfig.java
src/main/java/com/tlbank/lending/security/service/UserDetailsServiceImpl.java
src/main/java/com/tlbank/lending/security/model/AuthenticatedUser.java
src/main/java/com/tlbank/lending/security/handler/{LoginSuccessHandler,LoginFailureHandler,LogoutSuccessHandlerImpl,CustomAuthenticationEntryPoint,CustomAccessDeniedHandler,SessionExpiredStrategy}.java
src/main/java/com/tlbank/lending/security/util/{JsonResponseWriter,LoginResponseMode}.java
src/main/java/com/tlbank/lending/security/filter/MdcLoggingFilter.java
src/main/java/com/tlbank/lending/application/dto/response/LoginResponse.java
src/main/java/com/tlbank/lending/presentation/web/AuthController.java
src/main/resources/db/migration/V8__add_user_last_login.sql (+ -sqlserver equivalent)
src/main/resources/db/dev-seed/V100__seed_test_data.sql (admin/reviewer1/applicant1 dev accounts)

```

**Packages to modify:** `common.audit` (add `AuditLog`/`AuditLogRepository`/`AuditIpResolver` early, since
the login handlers write audit entries directly — see `11-audit-logging.md` §1; the full `@Auditable`/
`AuditAspect` mechanism can wait for Sprint 9, but the entity and repository must exist now).

**Acceptance criteria:**

- `POST /api/v1/auth/login` with valid credentials returns `200` + `ApiResponse<LoginResponse>` (JSON
  client) or a `302` redirect to the role-appropriate landing page (browser client) — per
  `07-security-design.md` §3.

- Invalid credentials → `401` + `ApiResponse.error(UNAUTHORIZED, ...)`.
- Unauthenticated request to an `hasRole('ADMIN')` path → `401` (API) or redirect (browser); authenticated
  but wrong role → `403`.

- A second login for the same user invalidates the first session (`maximumSessions(1)`).
- `users.last_login_at` updates on every successful login.
- `audit_logs` receives `USER_LOGIN`/`USER_LOGIN_FAILED`/`USER_LOGOUT` rows.

**Cursor Prompt:**
> Read `/docs/07-security-design.md` in full, plus `/docs/04-domain-model.md` §5 (User aggregate) and
> `/docs/05-database-design.md` §3.1–3.2. Implement the `domain.user` aggregate (`User`, `UserId`, `Role`)
> with no Spring/JPA dependency, its repository port, and the `infrastructure.persistence.user` JPA adapter,
> exactly matching the entity mapping described in `05-database-design.md` §5 (business `user_id` as the
> JPA `@Id`, numeric `id` read-only). Implement `security.config.SecurityConfig` with the exact URL
> authorization matrix and session settings in `07-security-design.md` §2, plus all six handler classes and
> the `LoginResponseMode`/`JsonResponseWriter` JSON-vs-redirect dispatch logic described in §3. Wire
> `MdcLoggingFilter` before `UsernamePasswordAuthenticationFilter`. Add Flyway migration `V8` (both H2 and
> SQL Server sets) and a dev-only seed migration creating three accounts: `admin`/`ADMIN`,
> `reviewer1`/`REVIEWER`, `applicant1`/`USER`, all with password `Password123!` (BCrypt-hashed). Each login
> handler must write an audit entry directly via an `AuditLogRepository` (introduce the minimal `AuditLog`
> entity now per `11-audit-logging.md` §1 — the full `@Auditable` AOP mechanism comes in Sprint 9). Follow
> `18-coding-standards.md` for naming and layering. Add unit/integration tests per `16-testing-strategy.md`
> covering successful login, failed login, role-based access denial, and concurrent-session invalidation.

---

## Sprint 3 — User Management

**Goal:** Admin CRUD-style management of internal user accounts.

**Business objective:** Let an administrator onboard reviewers/admins and disable compromised or departed
accounts without direct database access.

**Technical objective:** Application service + REST/web surface over the `User` aggregate built in Sprint 2,
plus migration `V9` introducing the externally-stable business identifier.

**Files to create:**

```
src/main/java/com/tlbank/lending/application/user/service/{UserAppService,CreateUserCommand,UpdateUserStatusCommand,UserResponse}.java
src/main/java/com/tlbank/lending/application/dto/request/CreateUserRequest.java
src/main/java/com/tlbank/lending/presentation/api/v1/UserManagementApiController.java
src/main/java/com/tlbank/lending/presentation/web/AdminController.java   (initial: #users only)
src/main/resources/templates/admin/users.html
src/main/resources/db/migration/V9__add_user_business_id.sql (+ -sqlserver equivalent)

```

**Packages to modify:** `security.service.UserDetailsServiceImpl` (no change expected, but verify it still
resolves correctly once `user_id` becomes the JPA `@Id`).

**Acceptance criteria:**

- `POST /api/v1/admin/users` creates a user with a generated `USR-XXXXXXXX` business ID; duplicate username
  → `409 DUPLICATE_USERNAME`.

- `GET /api/v1/admin/users` and `GET /api/v1/admin/users/{userId}` return data per
  `06-api-specification.md` §6.

- `PUT /api/v1/admin/users/{userId}/status?enabled=false` disables the account; a subsequent login attempt
  for that user fails.

- All three endpoints require `ROLE_ADMIN` (verify both via the global matcher and `@PreAuthorize`).

**Cursor Prompt:**
> Read `/docs/09-module-design.md` §2 (User Management) and `/docs/06-api-specification.md` §6. Add Flyway
> migration `V9` (both sets) adding the `user_id` business identifier column to `users`, backfilling
> existing rows, and making it `UNIQUE NOT NULL`, exactly as described in `05-database-design.md` §2/§3.1.
> Implement `UserAppService` with `createUser`, `updateStatus`, `findAll`, `findById`, following the
> Command/Response separation rules in `18-coding-standards.md` §3. Add `UserManagementApiController` under
> `/api/v1/admin/users` with `@PreAuthorize("hasRole('ADMIN')")`, matching the request/response shapes in
> `06-api-specification.md` §6 exactly. Add a minimal Thymeleaf `admin/users.html` page and the matching
> `AdminController#users` method. Use the existing shared `PasswordEncoder` bean from Sprint 2 to hash new
> passwords — never hash inside the domain `User` aggregate itself. Write Mockito-based unit tests for
> `UserAppService` (happy path + duplicate-username path) per `16-testing-strategy.md`.

---

## Sprint 4 — System Parameter Module

**Goal:** Externalize tunable configuration (initially just CRUD; OTP/cache/upload consumers arrive in their
own sprints) as database-backed, admin-editable parameters.

**Business objective:** Let administrators tune operational behavior (limits, durations) without a
redeployment.

**Technical objective:** `domain.parameter` aggregate + repository, application service, REST/web admin
surface, and migration `V10` adding grouping support on top of the `system_parameters` table from Sprint 1.

**Files to create:**

```
src/main/java/com/tlbank/lending/domain/parameter/{SystemParameter,SystemParameterRepository}.java
src/main/java/com/tlbank/lending/infrastructure/persistence/parameter/{SystemParameterEntity,SystemParameterJpaRepository,SystemParameterRepositoryImpl}.java
src/main/java/com/tlbank/lending/application/parameter/service/{SystemParameterService,SystemParameterResponse,UpdateParameterCommand}.java
src/main/java/com/tlbank/lending/presentation/api/v1/SystemParameterApiController.java
src/main/resources/templates/admin/system-parameters.html
src/main/resources/db/migration/V10__extend_system_parameters.sql (+ -sqlserver equivalent)

```

**Packages to modify:** `presentation.web.AdminController` (add `#systemParameters`).

**Acceptance criteria:**

- `GET /api/v1/admin/system-parameters?group=OTP` returns only `OTP`-grouped parameters.
- `PUT /api/v1/admin/system-parameters/{paramId}` updates a value; updating with a blank value is rejected
  by the domain aggregate (`IllegalArgumentException` → surfaced as a `400`).

- Uniqueness is enforced on `(param_group, param_key)`, not `param_key` alone.

**Cursor Prompt:**
> Read `/docs/09-module-design.md` §7 and `/docs/05-database-design.md` §3.11. Add Flyway migration `V10`
> (both sets) exactly as described in `05-database-design.md` §2 (add `param_group`, `enabled`,
> `created_at`; re-key the unique constraint to `(param_group, param_key)`; rename the two `otp.*` keys to
> `expiry.minutes`/`max.retry` — note these are later renamed again in the OTP sprint to `expire_minutes`/
> `max_retry`, so don't over-fit the renaming logic, just match this migration's literal SQL). Implement the
> `SystemParameter` aggregate (`updateValue` rejecting blanks) and its repository port/adapter. Implement
> `SystemParameterService` with `getValue` (throws `PARAMETER_NOT_FOUND` if missing — no cache yet, that's
> Sprint 10), `getValueOrDefault`, `getIntValue`, `findByGroup`, `findAllEnabled`, `update`. Add
> `SystemParameterApiController` under `/api/v1/admin/system-parameters` per `06-api-specification.md` §7,
> and an admin Thymeleaf page grouping parameters by `param_group`. Follow `18-coding-standards.md`.

---

## Sprint 5 — Credit Card Application Module

**Goal:** The platform's core capability — an applicant can browse products and create/track an
application through document upload and submission.

**Business objective:** Deliver the primary revenue-generating user journey: intake of a credit card
application.

**Technical objective:** `domain.application` aggregate + value objects, `domain.product` (card catalog),
`ApplicationAppService`, public REST + Thymeleaf web flow, idempotent application creation, and migration
`V11` (the migration literally named for this sprint).

**Files to create:**

```
src/main/java/com/tlbank/lending/domain/application/{Application,Applicant,Address,MobileNumber,Email,ApplicationId,ApplicationStatus,CardProductId,DocumentInfo,DocumentType,WorkflowHistory}.java
src/main/java/com/tlbank/lending/domain/application/repository/ApplicationRepository.java
src/main/java/com/tlbank/lending/domain/product/{CardProduct,CardType,ProductFeature}.java
src/main/java/com/tlbank/lending/domain/product/repository/CardProductRepository.java
src/main/java/com/tlbank/lending/infrastructure/persistence/application/*.java
src/main/java/com/tlbank/lending/infrastructure/persistence/product/*.java
src/main/java/com/tlbank/lending/application/application/service/*.java   (ApplicationAppService + Summary/Detail/Masked*/WorkflowHistory/DocumentInfo/CardProduct/ProductFeature *Response)
src/main/java/com/tlbank/lending/application/dto/request/{CreateApplicationRequest,ApplicantRequest,AddressRequest,CancelApplicationRequest}.java
src/main/java/com/tlbank/lending/application/idempotency/IdempotencyService.java
src/main/java/com/tlbank/lending/infrastructure/idempotency/{IdempotencyStore,IdempotencyEntry,InMemoryIdempotencyStore,RedisIdempotencyStore}.java
src/main/java/com/tlbank/lending/presentation/api/v1/{ApplicationApiController,CardProductApiController}.java
src/main/java/com/tlbank/lending/presentation/web/ApplicationWebController.java
src/main/resources/templates/{home,products/list,application/form}.html
src/main/resources/db/migration/V11__extend_applications_for_sprint5.sql (+ -sqlserver equivalent)

```

**Packages to modify:** `common.util.MaskingUtil` (introduce now if not already present, since
`ApplicationAppService` needs it immediately for `toMaskedApplicant`).

**Acceptance criteria:**

- `POST /api/v1/applications` creates an application in `INIT` status; repeating the same request with the
  same `Idempotency-Key` header returns the identical `applicationId` rather than creating a duplicate; the
  same key with a *different* body returns `409 IDEMPOTENCY_KEY_CONFLICT`.

- `GET /api/v1/applications/{id}` never returns raw national ID, full mobile number, or full email — only
  masked forms.

- `GET /api/v1/products` returns only `enabled=true` products.
- Document upload and submit/cancel actions are reachable per `06-api-specification.md` §2 even though
  document storage itself (Sprint 14) and full workflow guarding (Sprint 7) land in later sprints — wire the
  call sites now using a temporary direct-to-disk stub if needed, to be replaced cleanly in Sprint 14.

**Cursor Prompt:**
> Read `/docs/04-domain-model.md` §2–§3 and §6, `/docs/05-database-design.md` §2 and §3.5/§3.6/§3.7,
> `/docs/06-api-specification.md` §2–§3, and `/docs/09-module-design.md` §3. Implement the `Application`
> aggregate and its value objects exactly as specified — every value object validates in its compact
> constructor (see `18-coding-standards.md` §5), and `Application` exposes only named behavior methods
> (`verifyOtp`, `uploadDocuments`, `submit`, `startReview`, `approve`, `reject`, `cancel`), never a generic
> setter. Implement the `CardProduct` aggregate as a simpler, mostly-read model. Add Flyway migration `V11`
> (both sets) exactly per `05-database-design.md` §2, adding the applicant/address snapshot columns to
> `applications` and `card_type`/`enabled` to `card_products`. Implement `ApplicationAppService` per
> `09-module-design.md` §3, including PII masking via `MaskingUtil` on every outbound response. Implement
> `IdempotencyService` and both `IdempotencyStore` adapters exactly as described (note: `RedisIdempotencyStore`
> only activates when `tlbank.idempotency.store=redis`; `InMemoryIdempotencyStore` when `=memory` — both are
> `@ConditionalOnProperty`-gated, never both active at once). Wire `Idempotency-Key` header support into
> `POST /api/v1/applications` only. Add the public REST controllers and the Thymeleaf product list / apply
> form pages. Do not implement OTP verification logic yet (Sprint 6) or real document storage (Sprint 14) —
> stub those call sites minimally so the application compiles and the happy path through `INIT` status
> works end-to-end.

---

## Sprint 6 — OTP Verification Module

**Goal:** Mobile OTP send/verify, advancing `Application` from `INIT` to `OTP_VERIFIED`.

**Business objective:** Establish lightweight applicant identity verification without requiring account
creation.

**Technical objective:** `domain.otp` aggregate, `OtpAppService`, notification stub, migration `V12` (again,
literally named for this sprint).

**Files to create:**

```
src/main/java/com/tlbank/lending/domain/otp/{OtpRecord,OtpPurpose,OtpStatus,VerifyResult}.java
src/main/java/com/tlbank/lending/domain/otp/repository/OtpRepository.java
src/main/java/com/tlbank/lending/infrastructure/persistence/otp/{OtpRecordEntity,OtpJpaRepository,OtpRepositoryImpl}.java
src/main/java/com/tlbank/lending/application/otp/service/{OtpAppService,SendOtpCommand,VerifyOtpCommand,OtpResponse,VerifyOtpResponse}.java
src/main/java/com/tlbank/lending/application/dto/request/{SendOtpRequest,VerifyOtpRequest}.java
src/main/java/com/tlbank/lending/presentation/api/v1/OtpApiController.java
src/main/resources/templates/application/otp.html
src/main/resources/db/migration/V12__extend_otp_records_for_sprint6.sql (+ -sqlserver equivalent)

```

**Packages to modify:** `application.application.service.ApplicationAppService` is not modified directly;
`presentation.web.ApplicationWebController` gains the `/apply/otp` page wiring.

**Acceptance criteria:**

- Sending a second OTP for the same mobile cancels the first `PENDING` record.
- Verify enforces, in order: expiry → retry-limit → code-match, per `08-workflow-design.md` §4.
- Successful verify transitions the linked `Application` `INIT → OTP_VERIFIED` and is idempotent if called
  again while already `OTP_VERIFIED`.

- `OTP.expire_minutes`/`OTP.max_retry` are read from `SystemParameterService` (Sprint 4), not hardcoded.

**Cursor Prompt:**
> Read `/docs/04-domain-model.md` §7, `/docs/08-workflow-design.md` §4, `/docs/06-api-specification.md` §4,
> and `/docs/09-module-design.md` §4. Implement the `OtpRecord` aggregate with `verify(inputCode, maxRetry,
> clock)` enforcing exactly the expiry → retry → mismatch precedence described in
> `08-workflow-design.md` §4 — this ordering is load-bearing, do not reorder the checks. Add Flyway migration
> `V12` (both sets) exactly per `05-database-design.md` §2, switching `otp_records` to mobile-based
> verification (`mobile`, `status`, `verified_at` columns; drop the legacy boolean `verified` column). Use
> `SecureRandom` (never `Math.random()`) to generate the 6-digit code. Implement `OtpAppService.sendOtp`
> (cancels any existing pending OTP for the mobile first) and `verifyOtp` (on success, calls
> `Application.verifyOtp("APPLICANT")` and saves it via `ApplicationRepository`). Read `expire_minutes`/
> `max_retry` from the `SystemParameterService` built in Sprint 4, with in-code defaults of 5 and 3
> respectively. Wire a temporary direct call to a `NotificationService` interface for OTP delivery (the
> real mock SMS/email adapters arrive in Sprint 11 — define the interface now if it doesn't exist yet, with
> a no-op/log-only stub implementation). Add `OtpApiController` per `06-api-specification.md` §4 and the
> `/apply/otp` Thymeleaf page. Add `@Auditable(OTP_SEND)`/`@Auditable(OTP_VERIFY_SUCCESS)` annotations now
> even though the full `AuditAspect` mechanism is built in Sprint 9 — define a no-op `Auditable` annotation
> if needed and replace it with the real one in Sprint 9 without changing call sites.

---

## Sprint 7 — Workflow Engine

**Goal:** Make the application/review state machines explicit, centrally enforced, and independently
testable.

**Business objective:** Guarantee that no code path — present or future — can ever move an application or
review case into a business-invalid state, which is a compliance-relevant guarantee for a lending platform.

**Technical objective:** Formalize `ApplicationStatus.ALLOWED_TRANSITIONS`, extract `WorkflowDomainService`,
introduce `WorkflowException`, and backfill exhaustive transition tests.

**Files to create:**

```
src/main/java/com/tlbank/lending/domain/service/WorkflowDomainService.java
src/main/java/com/tlbank/lending/common/exception/WorkflowException.java
src/test/java/com/tlbank/lending/domain/application/ApplicationStatusTest.java
src/test/java/com/tlbank/lending/domain/service/WorkflowDomainServiceTest.java

```

**Packages to modify:** `domain.application.ApplicationStatus` (add `ALLOWED_TRANSITIONS` map +
`canTransitionTo`), `domain.application.Application` (route every transition through the shared check and
append `WorkflowHistory` in the same method, per `04-domain-model.md` §3.2), `domain.review.ReviewCase`
(apply the equivalent, simpler inline transition table from `04-domain-model.md` §4).

**Acceptance criteria:**

- Every entry in the transition table in `08-workflow-design.md` §1 is covered by a passing test, and every
  *disallowed* transition (e.g. `INIT → SUBMITTED` directly) throws `WorkflowException` mapped to HTTP `409`.

- `ApplicationAppService`/`ReviewAppService` contain **no** manual `if (status == ...)` pre-checks before
  calling an aggregate transition method — the aggregate is the single source of truth.

**Cursor Prompt:**
> Read `/docs/04-domain-model.md` §3.2/§10, `/docs/08-workflow-design.md` in full, and
> `/docs/09-module-design.md` §6. Refactor (or implement, if not already present from earlier sprints)
> `ApplicationStatus` to declare an explicit `ALLOWED_TRANSITIONS` map and `canTransitionTo(next)` method,
> exactly matching the transition table in `08-workflow-design.md` §1. Add `WorkflowException` extending
> `BusinessException` with a fixed `ErrorCode.INVALID_WORKFLOW_TRANSITION`, mapped to HTTP 409 in
> `GlobalExceptionHandler`. Add `WorkflowDomainService.validateTransition(from, to)` as a reusable,
> independently-testable check. Ensure every `Application` transition method (`verifyOtp`, `uploadDocuments`,
> `submit`, `startReview`, `approve`, `reject`, `cancel`) calls the shared `transitionTo` helper that both
> validates and appends a `WorkflowHistory` entry in one atomic step — never validate and mutate as two
> separate statements. Apply the equivalent (simpler) pattern to `ReviewCase`. Write exhaustive parametrized
> tests asserting every cell of the transition table, both allowed and disallowed, per
> `16-testing-strategy.md` §2.

---

## Sprint 8 — Credit Review Module

**Goal:** Give Credit Reviewers a queue, decisioning actions, and remark history per submitted application.

**Business objective:** Enable the human-in-the-loop credit decision that is the platform's core value
proposition beyond simple intake.

**Technical objective:** `domain.review` aggregate, `ReviewAppService`, REST/web surfaces, domain events
connecting submission to review-case creation, and migration `V13` (named for this sprint).

**Files to create:**

```
src/main/java/com/tlbank/lending/domain/review/{ReviewCase,ReviewCaseId,ReviewStatus,ReviewRemark,ReviewCaseSearchCriteria}.java
src/main/java/com/tlbank/lending/domain/review/repository/ReviewCaseRepository.java
src/main/java/com/tlbank/lending/domain/event/{ApplicationSubmittedEvent,ApplicationApprovedEvent,ApplicationRejectedEvent,ApplicationCancelledEvent}.java
src/main/java/com/tlbank/lending/infrastructure/persistence/review/*.java
src/main/java/com/tlbank/lending/infrastructure/event/ReviewEventHandler.java
src/main/java/com/tlbank/lending/application/review/service/*.java   (ReviewAppService + Add/Approve/Reject *Command + *Response types)
src/main/java/com/tlbank/lending/presentation/api/v1/ReviewApiController.java
src/main/java/com/tlbank/lending/presentation/api/v1/review/{AddRemarkRequest,ApproveReviewRequest,RejectReviewRequest}.java
src/main/java/com/tlbank/lending/presentation/web/ReviewController.java
src/main/resources/templates/review/{list,detail}.html
src/main/resources/db/migration/V13__extend_review_cases_for_sprint8.sql (+ -sqlserver equivalent)

```

**Packages to modify:** `application.application.service.ApplicationAppService.submitApplication` (publish
`ApplicationSubmittedEvent` via `ApplicationEventPublisher`, if not already wired in Sprint 5).

**Acceptance criteria:**

- Submitting an application automatically creates exactly one `PENDING` `ReviewCase` (via the event
  handler) — no controller or service explicitly constructs a `ReviewCase`.

- Approving/rejecting a case also drives the linked `Application` to the matching terminal status in the
  same transaction, calling `startReview()` implicitly first if it's still `SUBMITTED`.

- `GET /api/v1/review/cases` supports all filters in `06-api-specification.md` §5 and is paginated.
- All review endpoints require `hasAnyRole('REVIEWER','ADMIN')`.

**Cursor Prompt:**
> Read `/docs/04-domain-model.md` §4 and §11, `/docs/08-workflow-design.md` §2/§3/§5, and
> `/docs/06-api-specification.md` §5. Implement the `ReviewCase` aggregate with `createFor(applicationId)` as
> its only construction path (no public constructor used directly by application code), plus `startReview`,
> `approve`, `reject`, `addRemark`. Add the domain events listed above and `ReviewEventHandler`, an
> `@EventListener` that creates a `ReviewCase` on `ApplicationSubmittedEvent`. Add Flyway migration `V13`
> (both sets) exactly per `05-database-design.md` §2. Implement `ReviewAppService` exactly per the
> orchestration described in `09-module-design.md` §5 — note that `approveCase`/`rejectCase` must call
> `ensureApplicationUnderReview` before driving the application to its terminal state, reusing
> `WorkflowDomainService`/`Application.startReview` from Sprint 7. Add the REST and Thymeleaf surfaces.
> Ensure every PII field in case summaries/detail responses is masked, mirroring the `Application` module's
> approach (`18-coding-standards.md` §8).

---

## Sprint 9 — Audit Log Module

**Goal:** Declarative, automatic audit logging for every business-significant use case, with PII-safe detail
capture, plus an admin query API.

**Business objective:** Provide the compliance/traceability record a lending platform needs for every
sensitive action — who did what, when, from where, with what outcome.

**Technical objective:** `common.audit.*` AOP mechanism, async write-behind persistence, and migration `V14`
(named for this sprint), which **replaces** the `audit_logs` shape introduced in Sprint 1.

**Files to create:**

```
src/main/java/com/tlbank/lending/common/audit/{Auditable,AuditAspect,AuditAction,AuditContext,AuditDetailBuilder,AuditLog,AuditLogRepository,AuditLogWriter,AuditIpResolver}.java
src/main/java/com/tlbank/lending/application/audit/service/{AuditLogService,AuditLogResponse,NotificationLogResponse}.java
src/main/java/com/tlbank/lending/presentation/api/v1/{AuditLogApiController,NotificationLogApiController}.java
src/main/resources/templates/admin/{audit-logs,notifications}.html
src/main/resources/db/migration/V14__reshape_audit_logs_for_sprint9.sql (+ -sqlserver equivalent)

```

**Packages to modify:** `common.config.AsyncConfig` (`@EnableAsync` for `AuditLogWriter.saveAsync`); every
application service method that should be audited gets `@Auditable(action = AuditAction.X)` (see
`11-audit-logging.md` §4 for the full mapping) — if Sprints 5/6/8 already added placeholder
`@Auditable` annotations, point them at the real annotation now.

**Acceptance criteria:**

- `audit_logs` is dropped and recreated to the final shape (`username`, `action`, `ip_address`, `result`,
  `detail`, `created_at`) — verify existing login-handler direct-write code (Sprint 2) still compiles
  against the new entity shape.

- A failing `@Auditable` method still produces an audit row with `result=FAILURE` and the exception message
  as `detail` — verify via `AuditAspectTest` against a minimal fixture service.

- `AuditDetailBuilder` never lets a raw password, OTP code, or national ID reach the `detail` column —
  verify with a unit test asserting masked output for each sensitive argument shape in
  `11-audit-logging.md` §3.

- `GET /api/v1/admin/audit-logs` and `GET /api/v1/admin/notifications` work per
  `06-api-specification.md` §11–§12.

**Cursor Prompt:**
> Read `/docs/11-audit-logging.md` in full. Implement `Auditable` (annotation), `AuditAspect` (`@Around`
> advice keyed on `@annotation(auditable)`), `AuditContext` (thread-local, cleared in the aspect's `finally`
> block), `AuditDetailBuilder` (masking rules exactly as in §3), `AuditLog`/`AuditLogRepository`, and
> `AuditLogWriter` (async, `REQUIRES_NEW` transaction, swallows persistence failures with a `WARN` log —
> audit logging must never fail or roll back the business operation it's observing). Add Flyway migration
> `V14` (both sets) — note this migration **drops and recreates** `audit_logs`, so update the existing
> `AuditLog` entity's column mapping accordingly and confirm the Sprint 2 login handlers still compile.
> Apply `@Auditable` to exactly the methods listed in `11-audit-logging.md` §4. Implement
> `AuditLogService.search` and `searchNotificationAttempts` exactly per §5–§6 (notification logs are
> *derived* from audit logs by action-type filter, not a separate table). Add the two admin REST controllers
> and Thymeleaf pages. Write `AuditAspectTest` against a minimal fixture service annotated with `@Auditable`
> to verify both success and failure paths produce correct rows.

---

## Sprint 10 — Cache Module

**Goal:** In-process caching for card products and system parameters, with admin visibility and refresh.

**Business objective:** Keep the highest-traffic read paths (product browsing, OTP/upload parameter reads)
fast without introducing an external cache dependency for a single-instance deployment.

**Technical objective:** `infrastructure.cache.*` abstraction + decorator, `CacheManagementService`, admin
API.

**Files to create:**

```
src/main/java/com/tlbank/lending/infrastructure/cache/{CacheStore,CacheEntry,CacheKeys,CacheTtlProvider,InMemoryCacheStore,CachedCardProductRepository}.java
src/main/java/com/tlbank/lending/application/cache/service/{CacheManagementService,CacheRefreshResponse,CacheStatsResponse}.java
src/main/java/com/tlbank/lending/presentation/api/v1/CacheManagementApiController.java

```

**Packages to modify:** `application.parameter.service.SystemParameterService.getValue` (route through
`CacheStore`, per `12-cache-design.md` §6); seed data — add a `CACHE.ttl_seconds` row to `system_parameters`.

**Acceptance criteria:**

- `CachedCardProductRepository` is the bean actually injected wherever `CardProductRepository` is requested
  (verify with `@Primary`/`@Qualifier` wiring); the plain JPA adapter remains reachable only via
  `@Qualifier("cardProductRepositoryImpl")`.

- A product read after a TTL expiry is a cache miss that repopulates the cache.
- `POST /api/v1/admin/cache/refresh*` and `GET /api/v1/admin/cache/stats` behave exactly per
  `06-api-specification.md` §8 and `12-cache-design.md` §7.

- The background `@Scheduled` sweep in `InMemoryCacheStore` removes expired entries without needing a read
  to trigger eviction.

**Cursor Prompt:**
> Read `/docs/12-cache-design.md` in full. Implement `CacheStore` and `InMemoryCacheStore` exactly as
> specified, including the `@Scheduled(fixedDelay = 60_000)` expired-entry sweep. Implement `CacheKeys`,
> `CacheTtlProvider` (reading `CACHE.ttl_seconds` from `SystemParameterRepository` **directly**, not through
> `SystemParameterService`, to avoid a circular caching dependency — see §4). Implement
> `CachedCardProductRepository` as a `@Primary` decorator wrapping the existing JPA-backed
> `CardProductRepository` (qualify the JPA adapter bean as `cardProductRepositoryImpl` if it isn't already).
> Wire `SystemParameterService.getValue` to check the cache first per §6. Implement
> `CacheManagementService`/`CacheManagementApiController` per `06-api-specification.md` §8. Add a seed row
> `system_parameters(param_group='CACHE', param_key='ttl_seconds', param_value='21600')`. Write a unit test
> for `InMemoryCacheStore` covering put/get/expiry/eviction using an injected fixed `Clock`.

---

## Sprint 11 — Notification Module

**Goal:** Deliver SMS/email notifications at OTP, submission, approval, and rejection milestones via
fully-mocked channels.

**Business objective:** Keep applicants informed at every milestone, while keeping the platform free of any
real third-party credentials or dependencies (important for an open portfolio project).

**Technical objective:** `infrastructure.notification.*` ports/mocks, `NotificationServiceImpl`,
`NotificationEventHandler` wiring the domain events from Sprint 8 (and the submission event from Sprint 5)
to actual delivery.

**Files to create:**

```
src/main/java/com/tlbank/lending/infrastructure/notification/{SmsSender,EmailSender,SmsMessage,EmailMessage,MockSmsSender,MockEmailSender,NotificationTemplate}.java
src/main/java/com/tlbank/lending/application/notification/service/{NotificationService,NotificationServiceImpl}.java
src/main/java/com/tlbank/lending/infrastructure/event/NotificationEventHandler.java

```

**Packages to modify:** `application.otp.service.OtpAppService.sendOtp` (replace the Sprint 6 stub with a
real call to `NotificationService.sendOtpNotification`).

**Acceptance criteria:**

- `tlbank.notification.mode=mock` (default) activates `MockSmsSender`/`MockEmailSender`, which log instead
  of calling any real provider.

- A failed notification (simulate by throwing inside a test double) is caught and logged, never propagated —
  verify the triggering business transaction still commits successfully.

- OTP codes never appear in plaintext in any log line outside the OTP record's own persistence (verify
  `MockSmsSender` redacts the code in its log statement).

- All four milestone notifications (OTP, submitted, approved, rejected) use the centralized
  `NotificationTemplate` strings, not inline string literals.

**Cursor Prompt:**
> Read `/docs/09-module-design.md` §9 and `/docs/11-audit-logging.md` §7 (OTP code handling). Implement the
> `SmsSender`/`EmailSender` ports and their `Mock*` adapters gated by
> `@ConditionalOnProperty(name = "tlbank.notification.mode", havingValue = "mock", matchIfMissing = true)`.
> Implement `NotificationTemplate` with the message templates for OTP, submitted, approved, and rejected
> notifications (SMS + email subject/body each). Implement `NotificationServiceImpl`, catching and logging
> (never rethrowing) any send failure per notification attempt — a notification failure must never roll back
> the application/review workflow transaction that triggered it. Implement `NotificationEventHandler`
> listening for `ApplicationSubmittedEvent`, `ApplicationApprovedEvent`, `ApplicationRejectedEvent`. Replace
> the Sprint 6 OTP notification stub with a real call to `NotificationService.sendOtpNotification`. Ensure
> `MockSmsSender` redacts any 6-digit OTP code pattern from its log output even though the code is the whole
> point of the message. Write a Mockito-based test verifying a thrown exception inside `SmsSender`/
> `EmailSender` does not propagate out of `NotificationServiceImpl`.

---

## Sprint 12 — Report Module

**Goal:** On-demand Excel/PDF daily statistics export for administrators.

**Business objective:** Give operations/management a simple, offline-shareable operational snapshot without
building a full BI/dashboard product.

**Technical objective:** `ReportDataService` aggregation, `ExcelReportGenerator` (Apache POI),
`PdfReportGenerator` (iText 7), `ReportAppService`, REST + web trigger page.

**Files to create:**

```
src/main/java/com/tlbank/lending/application/report/service/{ReportDataService,ReportAppService,DailyStatisticsData,ReportFormat}.java
src/main/java/com/tlbank/lending/application/dto/request/GenerateReportRequest.java
src/main/java/com/tlbank/lending/infrastructure/report/{ExcelReportGenerator,PdfReportGenerator}.java
src/main/java/com/tlbank/lending/presentation/api/v1/ReportApiController.java
src/main/resources/templates/admin/reports.html

```

**Packages to modify:** `presentation.web.AdminController` (add `#reports`).

**Acceptance criteria:**

- `POST /api/v1/reports/daily-statistics` with `format=EXCEL` returns a valid, openable `.xlsx` with
  "Summary" and "By Product" sheets; `format=PDF` returns a valid, openable PDF with matching content.

- `statusCounts` always includes every `ApplicationStatus` value, even ones with zero applications that day.
- The action is recorded via `@Auditable(action = AuditAction.REPORT_EXPORT)`.
- An empty product map renders a `"No applications" / 0` row rather than an empty table in both formats.

**Cursor Prompt:**
> Read `/docs/14-report-design.md` in full. Implement `ReportDataService.buildDailyStatistics(date)` exactly
> per §3, pre-seeding every `ApplicationStatus` to zero before overlaying actual counts. Implement
> `ExcelReportGenerator` (Apache POI, two sheets) and `PdfReportGenerator` (iText 7, two tables) per §4–§5,
> including the identical percentage-formatting behavior in both (duplication here is acceptable per
> `20-maintenance-and-future-enhancement.md` — do not prematurely abstract it). Implement `ReportAppService`
> with `@Auditable(action = AuditAction.REPORT_EXPORT)` and `getFileName(date, format)`. Implement
> `ReportApiController.generateDailyStatistics` returning the raw byte array with the correct
> `Content-Type`/`Content-Disposition` headers per `06-api-specification.md` §9 (this is the one endpoint
> that does **not** use the `ApiResponse` envelope). Add the admin Thymeleaf trigger page. Write a unit test
> for `ExcelReportGenerator` asserting the workbook has exactly two sheets with the expected header rows.

---

## Sprint 13 — Scheduler Module

**Goal:** Background maintenance (OTP cleanup, cache refresh, daily statistics logging), each also
manually triggerable by an administrator.

**Business objective:** Keep operational hygiene (expired OTPs, stale cache, daily visibility) automatic
without manual operator intervention, while still giving operators an on-demand override.

**Technical objective:** Three `@Scheduled` jobs sharing their execution logic with a manual-trigger REST
API.

**Files to create:**

```
src/main/java/com/tlbank/lending/infrastructure/scheduler/{OtpCleanupScheduler,CacheRefreshScheduler,DailyStatisticsScheduler}.java
src/main/java/com/tlbank/lending/presentation/api/v1/SchedulerApiController.java
src/main/java/com/tlbank/lending/common/config/SchedulingConfig.java

```

**Packages to modify:** `domain.otp.repository.OtpRepository` (add `markExpiredBefore`, if not already
present from Sprint 6), `application.yml`/`application-dev.yml` (add `tlbank.scheduler.*.cron` properties
per `13-scheduler-design.md` §2).

**Acceptance criteria:**

- Each scheduled job's manual-trigger endpoint (`/api/v1/admin/schedulers/*/run`) calls the **exact same**
  method the `@Scheduled` annotation invokes — no duplicated logic.

- A thrown exception inside any scheduled job is caught, logged at `WARN`, and does not prevent the next
  scheduled execution.

- `OtpCleanupScheduler` correctly transitions overdue `PENDING` OTPs to `EXPIRED` without affecting already
  `VERIFIED`/`CANCELLED` records.

**Cursor Prompt:**
> Read `/docs/13-scheduler-design.md` in full. Implement the three scheduler classes, each following the
> identical defensive try/catch/log pattern in §5 — no scheduled method may let an exception propagate.
> Configure cron properties per §2, including the faster `dev`-profile override for OTP cleanup. Implement
> `SchedulerApiController` with three manual-trigger endpoints, each calling the corresponding scheduler's
> public method directly (do not duplicate the job logic in the controller). Verify `@EnableScheduling` is
> active (consolidate the existing `SchedulingConfig`/`SchedulerConfig` duplication if both already exist
> from earlier sprints — see `20-maintenance-and-future-enhancement.md`). Write unit tests for
> `OtpCleanupScheduler` and `CacheRefreshScheduler` using a fixed `Clock` and a mocked repository/cache to
> assert both the success-path summary and the failure-path warning log (no exception escapes).

---

## Sprint 14 — File Upload Module

**Goal:** Real document storage (replacing the Sprint 5 stub) with validation against business rules.

**Business objective:** Collect the supporting evidence (ID, income, residence proof) a credit decision
needs, safely and durably.

**Technical objective:** `DocumentStorageService` port + local filesystem adapter, validation against
system parameters, integration into `Application.uploadDocuments`.

**Files to create:**

```
src/main/java/com/tlbank/lending/infrastructure/storage/{DocumentStorageService,LocalDocumentStorageService}.java
src/main/resources/templates/application/upload.html

```

**Packages to modify:** `application.application.service.ApplicationAppService.uploadDocuments` (replace the
Sprint 5 stub with real `DocumentStorageService.validate`/`store` calls), `application.dto.response.
DocumentUploadResponse` (introduce if not already present).

**Acceptance criteria:**

- Uploading an empty file, a disallowed extension (anything other than `jpg`/`jpeg`/`png`/`pdf`), or an
  oversized file (above `UPLOAD.max.size.mb`, default 10) each return `400 DOCUMENT_UPLOAD_FAILED` with a
  clear message and **no file is written to disk**.

- A successful upload writes the file under `{basePath}/{applicationId}/` with a server-generated filename
  (never the client-supplied filename) and records the original filename + relative path in
  `application_documents`.

- The first successful upload while `OTP_VERIFIED` transitions the application to `DOCUMENT_UPLOADED`;
  subsequent uploads while already `DOCUMENT_UPLOADED` only append, without re-transitioning.

**Cursor Prompt:**
> Read `/docs/15-file-upload-design.md` in full. Implement `DocumentStorageService` (port) and
> `LocalDocumentStorageService` (adapter) exactly per §4–§5: extension whitelist `{jpg, jpeg, png, pdf}`,
> size limit sourced from `SystemParameterService.getIntValue("UPLOAD", "max.size.mb", 10)`, eager directory
> creation via `@PostConstruct`, and server-generated filenames in the pattern
> `{documentType}_{yyyyMMddHHmmss}.{ext}`. Replace any earlier stub in
> `ApplicationAppService.uploadDocuments` with real `validate()` then `store()` calls, then append the
> resulting `DocumentInfo` to the aggregate via `Application.uploadDocuments(docs, "APPLICANT")`. Add a seed
> row `system_parameters(param_group='UPLOAD', param_key='max.size.mb', param_value='10')` if not already
> present. Build the Thymeleaf upload page showing which document types are still missing per §1. Write a
> unit test for `LocalDocumentStorageService` covering: empty file, disallowed extension, oversized file,
> and a successful store (asserting the returned relative path format).

---

## Sprint 15 — Docker & Deployment

**Goal:** Make the platform runnable as a containerized stack against SQL Server, matching production-like
infrastructure.

**Business objective:** Demonstrate the project is a deployable, ops-ready artifact — not just code that
runs in one developer's IDE.

**Technical objective:** Multi-stage Docker build, `docker-compose.yml` orchestration, SQL Server init
scripts, environment variable contract, and convenience scripts.

**Files to create:**

```
docker/app/Dockerfile
docker/sqlserver/init/{01-init-database.sql,02-create-app-user.sql}
docker-compose.yml
.env.example
scripts/{prepare-dev.sh,start-dev.sh,verify.sh}
src/main/resources/db/migration-sqlserver/V100__seed_staging_data.sql

```

**Packages to modify:** none (infrastructure-only sprint); confirm `pom.xml`'s `staging` Maven profile (added
in Sprint 1) builds the JAR the Dockerfile expects.

**Acceptance criteria:**

- `docker-compose up -d` (after `cp .env.example .env`) brings up `sqlserver` → `db-init` (runs once,
  completes successfully) → `app`, in that dependency order, verified via each service's health check.

- `./scripts/verify.sh` against the running stack reports `200` for both `/actuator/health` and
  `/api/v1/products`.

- The application container runs as a non-root user; the final image does not contain the Maven/JDK build
  toolchain (only the JRE).

- Swagger UI is reachable on the `staging`-profile container, per `17-deployment-design.md` §5.

**Cursor Prompt:**
> Read `/docs/17-deployment-design.md` in full. Create `docker/app/Dockerfile` as a two-stage build
> (`eclipse-temurin:17-jdk-alpine` builder → `eclipse-temurin:17-jre-alpine` runtime, non-root `tlbank` user,
> `/app/uploads` and `/app/logs` volumes, the exact JVM flags in §2). Create
> `docker/sqlserver/init/01-init-database.sql` (creates the `TLBankLending` database and a `tlbank_app`
> login) and `02-create-app-user.sql` (grants that login `db_owner` inside the database) — both must be
> idempotent (`IF NOT EXISTS` guards). Create `docker-compose.yml` with the three services and exact
> `depends_on`/health-check configuration in §3. Create `.env.example` documenting every variable in §4 with
> safe placeholder values (never real secrets). Create `scripts/start-dev.sh`/`prepare-dev.sh`/`verify.sh`
> per §8. Add `V100__seed_staging_data.sql` under `db/migration-sqlserver` seeding the three Docker/staging
> demo accounts in §6 (`admin`/`reviewer`/`user01`, password `Password@123`, BCrypt-hashed). Finally, delete
> any legacy single-stage root-level `Dockerfile` not referenced by `docker-compose.yml`, per §7.

---

## Sprint 16 — Testing & Refactoring

**Goal:** Bring the full test pyramid up to the standard described in `16-testing-strategy.md`, close known
gaps, and pay down the technical debt accumulated across Sprints 1–15.

**Business objective:** Ship a portfolio-quality, defensible codebase — one that withstands interview-level
scrutiny of both correctness and craftsmanship.

**Technical objective:** Complete unit/integration test coverage, JaCoCo wiring, and the specific
refactors/fixes catalogued in `20-maintenance-and-future-enhancement.md`.

**Files to create/modify (representative, not exhaustive):**

```
src/test/java/com/tlbank/lending/**/*Test.java   (fill remaining gaps per 16-testing-strategy.md §2)
src/test/java/com/tlbank/lending/application/ApplicationFlowIntegrationTest.java
src/test/java/com/tlbank/lending/application/ReviewFlowIntegrationTest.java
src/test/java/com/tlbank/lending/application/ApplicationIdempotencyIntegrationTest.java
src/test/java/com/tlbank/lending/security/SecurityIntegrationTest.java
pom.xml   (confirm JaCoCo plugin + exclusions per 16-testing-strategy.md §6)

```

**Packages to modify:** any package referenced by the open items in
`20-maintenance-and-future-enhancement.md` §2 (e.g. `GlobalExceptionHandler`'s `REVIEW_CASE_NOT_FOUND`
mapping, `SystemParameterService.update`'s missing cache eviction, consolidating
`SchedulingConfig`/`SchedulerConfig`, removing the legacy root `Dockerfile`).

**Acceptance criteria:**

- `./mvnw clean verify` passes with zero failing tests and produces a JaCoCo report at
  `target/site/jacoco/index.html`.

- Every module in `09-module-design.md` has at least one corresponding test class exercising its primary
  use case.

- `ApplicationFlowIntegrationTest` exercises the full happy path: create → OTP send/verify → upload all
  three document types → submit → (as a reviewer) approve, asserting the application reaches `APPROVED` and
  exactly one `ReviewCase` was created.

- `SecurityIntegrationTest` covers: successful login (JSON), failed login, role-gated 403, and
  unauthenticated 401, for at least one endpoint each from the public, `REVIEWER`, and `ADMIN` tiers.

- All known-gap items explicitly listed in `20-maintenance-and-future-enhancement.md` §2 are either fixed or
  explicitly re-confirmed as deliberately deferred with a one-line justification added to that document.

**Cursor Prompt:**
> Read `/docs/16-testing-strategy.md` and `/docs/20-maintenance-and-future-enhancement.md` in full, plus
> `/docs/09-module-design.md` as the checklist of what must be covered. Fill in any missing unit tests per
> the test-category table in `16-testing-strategy.md` §2 (domain, application-service, infrastructure
> layers), following the naming and mocking conventions in §3 and §5. Add or complete the four full-stack
> integration tests listed above using `@SpringBootTest` + `@AutoConfigureMockMvc` + `@ActiveProfiles("dev")`,
> covering the full happy-path and at least one failure path each. Confirm the JaCoCo plugin configuration in
> `pom.xml` matches §6 exactly (same exclusions). Then work through every item in
> `20-maintenance-and-future-enhancement.md` §2 ("Known Issues / Technical Debt") one at a time: either fix
> it with an accompanying test, or, if a fix is out of scope for this sprint, leave it documented with a
> one-line rationale for deferring it. Do not introduce new business features in this sprint — it is
> exclusively about closing test/quality gaps in what Sprints 1–15 already built.

---

## Sprint 17 — Application Integrity & Operational Correctness

**Goal:** Close intake-boundary gaps so incomplete applications cannot enter review, admin parameter
changes take effect immediately, document uploads are auditable, and review-case-not-found returns the
correct HTTP status.

**Business objective:** Guarantee that the review queue only receives complete applications and that
operational tuning (OTP expiry, upload limits) and evidence collection are trustworthy.

**Technical objective:** Four targeted fixes in existing domain/application/cross-cutting layers — no new
modules, no architecture change.

**Files modified:**

```
src/main/java/com/tlbank/lending/domain/application/Application.java
src/main/java/com/tlbank/lending/common/exception/ErrorCode.java
src/main/java/com/tlbank/lending/presentation/api/advice/GlobalExceptionHandler.java
src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java
src/main/java/com/tlbank/lending/application/parameter/service/SystemParameterService.java
(+ corresponding tests and SDD updates)

```

**Acceptance criteria:**

- `Application.submit()` rejects when any `DocumentType` is missing → `INCOMPLETE_DOCUMENTS` (HTTP 400).
- `SystemParameterService.update()` evicts the matching cache key immediately.
- `ApplicationAppService.uploadDocuments` is `@Auditable(DOCUMENT_UPLOAD)`.
- `REVIEW_CASE_NOT_FOUND` maps to HTTP 404.
- Unit and integration tests cover the submit guard and incomplete-document REST path.
