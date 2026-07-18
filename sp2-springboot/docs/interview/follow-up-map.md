# Follow-Up Map

Question chains for second-pass drill. Each node is bilingual.

追問鏈，供第二輪演練。每個節點皆為中英對照。

---

## Chain 1 — Architecture Dependency Direction

架構依賴方向。

Q: Why does domain not depend on infrastructure in this project?  
Q：為何本專案的 domain 不依賴 infrastructure？

  → Where is that rule written, and is ArchUnit enforcing it in CI?  
    → 該規則寫於何處？CI 是否以 ArchUnit 強制？

    → If ArchUnit is absent, what still proves the rule in source?  
      → 若無 ArchUnit，原始碼中仍以何證明該規則？

    → Point to one place the rule is already broken.  
      → 指出一處規則已被破壞的位置。

  → How does `ApplicationRepository` illustrate a port versus an adapter?  
    → `ApplicationRepository` 如何說明 port 與 adapter？

    → Who calls the port—controller or application service?  
      → 誰呼叫 port—controller 或 application service？

    → Where does entity↔aggregate mapping live?  
      → entity 與 aggregate 對應位於何處？

  → If `CacheManagementService` injects `CachedCardProductRepository`, what is wrong?  
    → 若 `CacheManagementService` 注入 `CachedCardProductRepository`，問題為何？

    presentation（展示）
            ↓
    application（應用服務）
            ↓
    domain ports（領域埠）
            ↑
    infrastructure adapters（基礎設施適配器）

---

## Chain 2 — Transaction Propagation

交易傳播。

Q: Where should `@Transactional` be placed, and why not on the controller?  
Q：`@Transactional` 應置於何處？為何不放在 controller？

  → What does `readOnly=true` change for Hibernate dirty checking?  
    → `readOnly=true` 對 Hibernate dirty checking 有何影響？

  → What fails if `open-in-view` is false and a LAZY collection is touched outside the service?  
    → 若 `open-in-view` 為 false，於 service 外觸及 LAZY 集合會如何失敗？

  → How does `AuditLogWriter` interact with the business transaction?  
    → `AuditLogWriter` 與業務交易如何互動？

    → What is `PROPAGATION_REQUIRES_NEW` doing inside `saveAsync`?  
      → `saveAsync` 內的 `REQUIRES_NEW` 作用為何？

    → Can a failed audit roll back `ApplicationAppService.submit`?  
      → audit 失敗能否回滾 `submit`？

    Controller（控制器）
            ↓
    Application Service + @Transactional（應用服務與交易）
            ↓
    Domain / Ports（領域／埠）
            ↓
    AuditLogWriter @Async + REQUIRES_NEW（稽核非同步新交易）

---

## Chain 3 — Session Scaling

Session 擴容。

Q: Authentication is session-based—what happens with three app instances behind a load balancer?  
Q：認證為 session—三個應用實例置於負載平衡後方會如何？

  → Where does `SessionRegistryImpl` store sessions?  
    → `SessionRegistryImpl` 將 session 存在何處？

    → Is Spring Session Redis implemented?  
      → 是否已實作 Spring Session Redis？

    → What does ADR 0006 say about JWT instead?  
      → ADR 0006 對 JWT 的說明為何？

  → What does `maximumSessions(1)` do on a single node?  
    → 單節點上 `maximumSessions(1)` 行為為何？

    Browser（瀏覽器）
            ↓
    Form login / JSESSIONID（表單登入）
            ↓
    SessionRegistryImpl（JVM 內）
            ↓
    No shared store across nodes（節點間無共享儲存）

---

## Chain 4 — State Machine Transition

狀態機轉移。

Q: How does an application move from INIT to APPROVED?  
Q：申請如何自 INIT 移至 APPROVED？

  → Which method forbids INIT → APPROVED directly?  
    → 哪個方法禁止 INIT 直接到 APPROVED？

    → What exception type is thrown?  
      → 拋出何種例外？

    → What HTTP status does `GlobalExceptionHandler` return?  
      → `GlobalExceptionHandler` 回傳何種 HTTP 狀態？

  → Can cancel run after SUBMITTED?  
    → SUBMITTED 之後能否 cancel？

    INIT → OTP_VERIFIED → DOCUMENT_UPLOADED → SUBMITTED → UNDER_REVIEW → APPROVED|REJECTED
    （cancel 僅早期狀態）

---

## Chain 5 — Idempotency Race Condition

冪等競態。

Q: Two parallel POSTs reuse the same Idempotency-Key—what happens?  
Q：兩個並行 POST 重用同一 Idempotency-Key—結果為何？

  → How is the lock key formed from the storage key?  
    → lock key 如何由 storage key 組成？

    → What is `LOCK_TTL_SECONDS`?  
      → `LOCK_TTL_SECONDS` 為多少？

    → What `ErrorCode` is thrown if the lock is not acquired?  
      → 未取得鎖時拋出哪個 `ErrorCode`？

  → Same key, different body hash—what `ErrorCode`?  
    → 同 key、不同 body hash—哪個 `ErrorCode`？

    Idempotency-Key
            ↓
    storageKey = prefix + key（儲存鍵）
            ↓
    lockKey = storageKey + ":lock"（鎖鍵，SETNX 語意）
            ↓
    execute / save entry / finally release（執行／存檔／釋放）

---

## Chain 6 — AOP Proxy Limitation

AOP 代理限制。

Q: Why might `@Auditable` appear to do nothing when a service method calls another method in the same class?  
Q：為何同類別內部互調時 `@Auditable` 可能看似未生效？

  → What is the Spring AOP proxy self-invocation limitation?  
    → Spring AOP 代理自我呼叫限制為何？

  → Does `AuditAspect` swallow `BusinessException` after writing FAILURE?  
    → 寫入 FAILURE 後，`AuditAspect` 是否吞掉 `BusinessException`？

  → How does `AuditAspectTest` make `@Async` deterministic?  
    → `AuditAspectTest` 如何使 `@Async` 可斷言？

    External caller（外部呼叫）
            ↓
    Spring proxy + @Around（代理與環繞通知）
            ↓
    Target method（目標方法）
            ✗ same-class this.method() bypasses proxy（同類 this 呼叫繞過代理）

---

## Chain 7 — Flyway Checksum

Flyway checksum。

Q: A developer edits `V1__create_users.sql` after it already ran on staging—what happens at next startup?  
Q：staging 已套用後仍修改 `V1__create_users.sql`—下次啟動會如何？

  → Which component validates migration checksums?  
    → 哪個元件驗證 migration checksum？

    → How is that different from `ddl-auto=validate`?  
      → 與 `ddl-auto=validate` 差異為何？

  → Dev uses `db/migration` + `db/dev-seed`; staging uses `db/migration-sqlserver`—why two trees?  
    → 為何存在兩套 migration 樹？

    Flyway script on disk（磁碟腳本）
            ↓
    checksum vs flyway_schema_history（與歷史表比對）
            ↓
    mismatch → startup failure（不符 → 啟動失敗）

---

## Chain 8 — Cache Eviction

快取驅逐。

Q: An admin refreshes card-product cache—what code path runs, and what is not Redis?  
Q：管理員刷新卡片產品快取—走哪條路徑？何者不是 Redis？

  → Which class is `@Primary` over `CardProductRepository`?  
    → 哪個類別以 `@Primary` 裝飾 `CardProductRepository`？

  → Where does TTL come from?  
    → TTL 來自何處？

  → Two app instances refresh independently—what consistency property is missing?  
    → 兩實例各自刷新—缺少何種一致性？

    CacheManagementService / CacheRefreshScheduler（管理或排程）
            ↓
    CachedCardProductRepository.refreshCache（裝飾器刷新）
            ↓
    InMemoryCacheStore（進程內 ConcurrentHashMap）
            ✗ not RedisIdempotencyStore（非 Redis 冪等儲存）

## Open Book Navigation

- [topics/01-architecture.md](../open-book/topics/01-architecture.md)  
  [架構](../open-book/topics/01-architecture.md)

- [topics/06-transactions.md](../open-book/topics/06-transactions.md)  
  [交易](../open-book/topics/06-transactions.md)

- [topics/07-redis-idempotency.md](../open-book/topics/07-redis-idempotency.md)  
  [冪等](../open-book/topics/07-redis-idempotency.md)

- [topics/08-cache.md](../open-book/topics/08-cache.md)  
  [快取](../open-book/topics/08-cache.md)
