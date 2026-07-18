# Interview Cheat Sheet — TLBank

One-page rapid recall. Each item is bilingual.

單頁快速回憶。每一項皆為中英對照。

## 1. State Machine

狀態機。

| FROM | TO | Method |
| --- | --- | --- |
| INIT | OTP_VERIFIED | `Application.verifyOtp` |
| INIT | CANCELLED | `Application.cancel` |
| OTP_VERIFIED | DOCUMENT_UPLOADED | `Application.uploadDocuments` (when status is OTP_VERIFIED) |
| OTP_VERIFIED | CANCELLED | `Application.cancel` |
| DOCUMENT_UPLOADED | DOCUMENT_UPLOADED | `Application.uploadDocuments` (no transition; append docs) |
| DOCUMENT_UPLOADED | SUBMITTED | `Application.submit` |
| DOCUMENT_UPLOADED | CANCELLED | `Application.cancel` |
| SUBMITTED | UNDER_REVIEW | `Application.startReview` |
| UNDER_REVIEW | APPROVED | `Application.approve` |
| UNDER_REVIEW | REJECTED | `Application.reject` |

Cancel is allowed only from INIT, OTP_VERIFIED, or DOCUMENT_UPLOADED.

`cancel` 僅允許自 INIT、OTP_VERIFIED、DOCUMENT_UPLOADED。

Gate: `ApplicationStatus.canTransitionTo` / private `transitionTo`. Illegal transition → `WorkflowException` → HTTP 409 `INVALID_WORKFLOW_TRANSITION`.

閘道：`canTransitionTo`／私有 `transitionTo`。非法轉移 → `WorkflowException` → HTTP 409 `INVALID_WORKFLOW_TRANSITION`。

## 2. Security Filter Chain (Configured Order)

安全性過濾鏈（`SecurityConfig` 設定順序）。

1. `MdcLoggingFilter` — before `UsernamePasswordAuthenticationFilter`  
   `MdcLoggingFilter` — 置於 `UsernamePasswordAuthenticationFilter` 之前

2. CSRF — ignore `/api/**`  
   CSRF — 忽略 `/api/**`

3. `authorizeHttpRequests` — permitAll / roles / authenticated  
   `authorizeHttpRequests` — permitAll／角色／需認證

4. Form login — `/api/v1/auth/login`  
   表單登入 — `/api/v1/auth/login`

5. Logout — `/api/v1/auth/logout`, invalidate session, delete `JSESSIONID`  
   登出 — `/api/v1/auth/logout`，失效 session，刪除 `JSESSIONID`

6. Session — `IF_REQUIRED`, `maximumSessions(1)`, `SessionRegistryImpl`  
   Session — `IF_REQUIRED`、`maximumSessions(1)`、`SessionRegistryImpl`

7. Exception handling — entry point + access denied handler  
   例外處理 — entry point 與 access denied handler

## 3. Layer Dependency Direction

層依賴方向。

```text
presentation ──► application ──► domain
                      ▲              ▲
                 security       infrastructure
                      │              │
                      └──── common ──┘

domain ◄── ports ◄── infrastructure adapters
```

`domain` must not depend on infrastructure, Spring Web, or JPA adapters.

`domain` 不得依賴 infrastructure、Spring Web 或 JPA adapter。

## 4. Key Class → Concept → Layer

關鍵類別對照。

| Class | Role (EN) | Role（中文） | Layer |
| --- | --- | --- | --- |
| `Application` | Aggregate + status verbs | 聚合與狀態動詞 | domain |
| `ApplicationStatus` | `ALLOWED_TRANSITIONS` map | 允許轉移表 | domain |
| `ApplicationAppService` | Use-case + `@Transactional` | 用例編排與交易 | application |
| `IdempotencyService` | Idempotent create | 建立申請冪等 | application |
| `ApplicationRepositoryImpl` | Port adapter + mapping | Port 適配與對應 | infrastructure |
| `RedisIdempotencyStore` | Redis idempotency store | Redis 冪等儲存 | infrastructure |
| `InMemoryCacheStore` | Product/param cache | 產品／參數快取 | infrastructure |
| `SecurityConfig` | Session filter chain | Session 過濾鏈 | security |
| `AuditAspect` | `@Auditable` AOP | 稽核切面 | common |
| `GlobalExceptionHandler` | Exception → `ApiResponse` | 例外映射 | presentation |
| `ApplicationApiController` | REST applications API | 申請 REST API | presentation |
| `LocalDocumentStorageService` | Local FS uploads | 本機檔案上傳 | infrastructure |

## 5. `@Transactional` Placement

`@Transactional` 放置規則。

`@Transactional` defines the transaction boundary of a use case.

`@Transactional` 定義用例交易邊界。

1. Place on application services (`ApplicationAppService`, `OtpAppService`, `ReviewAppService`), not controllers.  
   放在 application service，不放在 controller。

2. Reads often use `readOnly = true`. `spring.jpa.open-in-view=false`.  
   讀取常設 `readOnly = true`。`open-in-view` 為 false。

3. `AuditLogWriter.saveAsync` uses `PROPAGATION_REQUIRES_NEW` (isolated from business TX).  
   `AuditLogWriter.saveAsync` 使用 `REQUIRES_NEW`，與業務交易隔離。

## 6. Redis Key Patterns

Redis 鍵模式（僅冪等）。

```text
prefix:       tlbank.idempotency.key-prefix = "idempotency:applications:"
storage key:  {key-prefix}{Idempotency-Key}
lock key:     {storageKey}:lock
lock TTL:     30s
entry TTL:    ttl-hours * 3600 (default 24h)
store:        tlbank.idempotency.store = redis | memory
```

Redis is not used for product cache, HTTP sessions, or OTP storage.

Redis 不用於產品快取、HTTP session 或 OTP 儲存。

## 7. Test Class → Type

測試類別對照。

| Test class | Type (EN) | Type（中文） |
| --- | --- | --- |
| `ApplicationStatusTest` / `ApplicationTest` | Domain unit | 領域單元 |
| `ApplicationAppServiceTest` / `IdempotencyServiceTest` | Mockito unit | Mockito 單元 |
| `AuditAspectTest` | Spring + sync `@Async` Executor | Spring 並同步化 `@Async` |
| `ApplicationFlowIntegrationTest` | Flow IT | 流程整合 |
| `ApplicationIdempotencyIntegrationTest` | IT (memory store) | 整合（記憶體冪等） |
| `SecurityIntegrationTest` | Security IT | 安全性整合 |
| `OtpCleanupSchedulerTest` | Scheduler test | 排程測試 |

## 8. Known Limitations

已知限制。

1. Notifications = `MockSmsSender` / `MockEmailSender`.  
   通知為 mock 適配器。

2. Product cache = `InMemoryCacheStore`; sessions = JVM `SessionRegistryImpl` (not JWT / not Redis Session).  
   產品快取為記憶體；session 為 JVM 內 `SessionRegistryImpl`（非 JWT／非 Redis Session）。

3. Uploads = local filesystem (`tlbank.upload.base-path`).  
   上傳為本機檔案系統。

4. Staging deploy = `workflow_dispatch` only; Terraform `infra/local` = `hashicorp/local` only.  
   Staging 部署僅手動觸發；Terraform 僅 local provider。

5. Dual `@EnableScheduling` (`SchedulingConfig` + `SchedulerConfig`); schedulers are in-process, not leader-elected.  
   雙重 `@EnableScheduling`；排程在應用行程內，無領導者選舉。

## Open Book Navigation

- [topics/04-domain-and-workflow.md](../open-book/topics/04-domain-and-workflow.md)  
  [領域與流程](../open-book/topics/04-domain-and-workflow.md)

- [topics/03-security.md](../open-book/topics/03-security.md)  
  [安全性](../open-book/topics/03-security.md)

- [topics/07-redis-idempotency.md](../open-book/topics/07-redis-idempotency.md)  
  [Redis 冪等](../open-book/topics/07-redis-idempotency.md)
