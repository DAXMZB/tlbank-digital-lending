# Progress Checklist

Self-assessment gate for Trend Micro Java Backend Engineer (Junior) interview preparation.

趨勢科技 Java 後端工程師（初階）面試準備自我檢核。

Use this checklist 48 hours before the interview.  
面試前 48 小時使用本檢核表。

Any unchecked Tier 1 item requires another full review of the corresponding module.  
任一未勾選的 Tier 1 項目，須重讀對應模組。

## Tier 1 — Must Be Able to Explain Without Opening Files

Tier 1 — 不開啟檔案即須能說明。

- [ ] The dependency direction between domain, application, infrastructure, presentation  
      domain、application、infrastructure、presentation 之間的依賴方向

- [ ] All states in `ApplicationStatus` and their transitions  
      `ApplicationStatus` 全部狀態及其轉移

- [ ] Why session was chosen over JWT  
      為何選擇 session 而非 JWT

- [ ] What `@Transactional(readOnly = true)` does at the database / persistence level  
      `@Transactional(readOnly = true)` 在持久化層的作用

- [ ] What SETNX-style lock acquisition guarantees in the Redis idempotency path  
      Redis 冪等路徑中類似 SETNX 取鎖所保證的行為

- [ ] The difference between `@Around` and `@Before` in AOP  
      AOP 中 `@Around` 與 `@Before` 的差異

- [ ] What `ConcurrentHashMap` guarantees and what it does not  
      `ConcurrentHashMap` 保證與不保證的內容

- [ ] Why `Application` has no `@Entity`  
      為何 `Application` 沒有 `@Entity`

## Tier 2 — Must Be Able to Explain by Opening the File

Tier 2 — 開啟檔案後須能逐段說明。

- [ ] `ApplicationRepositoryImpl.save()` line by line  
      逐行說明 `ApplicationRepositoryImpl.save()`

- [ ] `IdempotencyService.execute()` flow  
      `IdempotencyService.execute()` 流程

- [ ] `SecurityConfig.securityFilterChain()` full method  
      `SecurityConfig.securityFilterChain()` 方法全文

- [ ] `AuditAspect` around-advice try/catch/finally structure  
      `AuditAspect` 環繞通知的 try／catch／finally 結構

- [ ] `ApplicationStatus.ALLOWED_TRANSITIONS` definition  
      `ApplicationStatus.ALLOWED_TRANSITIONS` 定義

## Tier 3 — Aware, Can Discuss Trade-offs

Tier 3 — 知悉現況與演進取捨（多數為未實作或僅文件層）。

- [ ] ArchUnit enforcement of dependency rules  
      以 ArchUnit 強制依賴規則（演進／意識項）

- [ ] Testcontainers for SQL Server integration tests  
      以 Testcontainers 進行 SQL Server 整合測試（演進／意識項）

- [ ] Spring Session + Redis for horizontal scaling  
      Spring Session + Redis 水平擴展（演進／意識項；現況為 JVM `SessionRegistry`）

- [ ] `@TransactionalEventListener` vs `@EventListener`  
      `@TransactionalEventListener` 與 `@EventListener` 差異（現況多為同步 `@EventListener`）

- [ ] ShedLock for distributed scheduler safety  
      ShedLock 分散式排程安全（演進／意識項；現況為行程內 `@Scheduled`）

## Open Book Navigation

- [topics/01-architecture.md](../open-book/topics/01-architecture.md)  
  [架構](../open-book/topics/01-architecture.md)

- [topics/03-security.md](../open-book/topics/03-security.md)  
  [安全性](../open-book/topics/03-security.md)

- [modules/M30-self-assessment.md](modules/M30-self-assessment.md)  
  [自我評估模組](modules/M30-self-assessment.md)
