# Core Interview Questions (30)

Derived from `docs/handbook/09-interview-source-map-300.md` (single source of truth).
Questions are selected, not rewritten. Short answers compress the source-map notes for a 30–60 second spoken response.

Selection emphasis: architecture, Spring Boot, Spring Security, JPA, transactions, SQL, REST, testing, Redis, Docker, CI, exception handling, logging, domain design.

---

## 1. Q027 — The project follows Clean Architecture with a strict dependency rule. Which direction do dependencies flow, and how is this enforced?

**Category:** Clean / Hexagonal Architecture · **Difficulty:** Intermediate · **Status:** Documentation-only

### Question

The project follows Clean Architecture with a strict dependency rule. Which direction do dependencies flow, and how is this enforced?

### Short answer (30~60 seconds)

依賴方向：presentation → application → domain ← infrastructure。Domain 不依賴任何外層：Application（aggregate）只 import JDK 和 Lombok，沒有 @Entity、沒有 Spring 注解。Infrastructure 依賴 domain port（ApplicationRepository、OtpRepository 等介面），實作這些 port 而非被 domain 所知。目前的執行方式：package 命名約定 + code review，沒有自動化工具（如 ArchUnit）在 CI 強制驗證。ADR 0001 承認有少數 leak（@Service 出現在接近 domain 的地方）。未來改善方向：加入 ArchUnit 規則，讓 CI 能自動拒絕違反依賴方向的 PR。

### Key files

- `sp2-springboot/docs/decisions/0001-use-clean-architecture.md`
- `sp2-springboot/docs/design/02-architecture-design.md`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationRepositoryImpl.java`

### Possible follow-up questions

- Can you open Application.java and show me that it has no @Entity annotation?
- What ArchUnit rule would you write to enforce the dependency direction?
- What is the difference between Clean Architecture and Hexagonal Architecture?

### Common mistakes

- Answering from documentation tone without citing verified source files or classes from the source map.
- Claiming a perfect Clean Architecture with no leaks.

---

## 2. Q028 — What is the "Ports and Adapters" pattern, and where does TLBank implement it?

**Category:** Clean / Hexagonal Architecture · **Difficulty:** Intermediate · **Status:** Documentation-only

### Question

What is the "Ports and Adapters" pattern, and where does TLBank implement it?

### Short answer (30~60 seconds)

Ports and Adapters（Hexagonal Architecture）：Port 是 domain 定義的介面（interface），Adapter 是 infrastructure 實作該介面的具體類別。Port 讓 domain 依賴抽象而非具體實作，外部技術可以替換而不影響 domain 邏輯。TLBank 的實例：Port = ApplicationRepository（domain package 中的介面），Adapter = ApplicationRepositoryImpl（infrastructure package，實作 Port，內部使用 ApplicationJpaRepository）。

### Key files

- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/repository/ApplicationRepository.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationRepositoryImpl.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/idempotency/IdempotencyStore.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/idempotency/RedisIdempotencyStore.java`

### Possible follow-up questions

- Why is ApplicationRepository in the domain package rather than infrastructure?
- How would you swap LocalDocumentStorageService for an S3 adapter without changing the domain?
- What is the "driving" versus "driven" port distinction in hexagonal architecture?

### Common mistakes

- Answering from documentation tone without citing verified source files or classes from the source map.
- Treating Redis as a general application cache or session store.
- Claiming a perfect Clean Architecture with no leaks.

---

## 3. Q029 — ApplicationRepositoryImpl maps between ApplicationEntity (JPA) and Application (domain aggregate). Why is this mapping necessary, and what is the cost?

**Category:** Clean / Hexagonal Architecture · **Difficulty:** Advanced · **Status:** Verified

### Question

ApplicationRepositoryImpl maps between ApplicationEntity (JPA) and Application (domain aggregate). Why is this mapping necessary, and what is the cost?

### Short answer (30~60 seconds)

Runtime: Request enters presentation layer (REST or Thymeleaf controller) → Application service orchestrates domain aggregate and ports → Infrastructure adapter persists or calls external/mock dependency. Mapping 的必要性：domain aggregate Application 不帶任何 JPA 注解（@Entity、@Column 等），才能在不依賴資料庫的情況下做 unit test。ApplicationEntity 帶 JPA 注解，負責對應資料庫 schema。若兩個合一（在 aggregate 上直接加 @Entity），domain test 就需要啟動 Spring Data JPA 整個 stack。Mapping 的成本：每次 save() 和 findById() 都需要手動或工具協助的 entity ↔ domain 轉換，程式碼量增加（toDomain()、toEntity()、applyToEntity() 方法）。MapStruct 在 pom.xml 中有 classpath 但目前未使用，手動 mapping 是目前的實作。

### Key files

- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationRepositoryImpl.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationEntity.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java`

### Possible follow-up questions

- What would break if you added @Entity directly to Application?
- MapStruct is on the classpath but unused. When would you add it?
- How does applyToEntity() differ from toEntity(), and why are both needed?

### Common mistakes

- Claiming a perfect Clean Architecture with no leaks.

---

## 4. Q032 — What are the known architectural impurities (leaks) in this codebase, and how would you fix them?

**Category:** Clean / Hexagonal Architecture · **Difficulty:** Advanced · **Status:** Documentation-only

### Question

What are the known architectural impurities (leaks) in this codebase, and how would you fix them?

### Short answer (30~60 seconds)

ADR 0001 明確承認的 impurity：@Service annotation 出現在接近 domain layer 的地方（WorkflowDomainService），使 domain 有 Spring 依賴。MapStruct 在 classpath 但未用，手動 mapping 有重複程式碼的風險。部分 Spring 類型（如 Pageable）可能出現在 domain port 介面（ApplicationRepository），使 domain 依賴 Spring Data 的分頁 abstraction。修復方式：用純 Java 定義 WorkflowDomainService（不加 @Service），改由 @Configuration 的 @Bean 方法建立實例並注入；將 Pageable 從 domain port 移除，改用自訂的 SearchCriteria 值物件；

### Key files

- `sp2-springboot/src/main/java/com/tlbank/lending/domain/service/WorkflowDomainService.java`
- `sp2-springboot/docs/decisions/0001-use-clean-architecture.md`

### Possible follow-up questions

- How would ArchUnit help catch these leaks in CI automatically?
- Is the @Service annotation on WorkflowDomainService a compile-time or runtime dependency?
- What is the business risk of these architectural impurities in a production system?

### Common mistakes

- Answering from documentation tone without citing verified source files or classes from the source map.
- Claiming a perfect Clean Architecture with no leaks.

---

## 5. Q017 — Spring Boot 3.4.2 is pinned in pom.xml. What changed from Spring Boot 2.x to 3.x that is most relevant to this project?

**Category:** Spring Boot 3.x · **Difficulty:** Intermediate · **Status:** Verified

### Question

Spring Boot 3.4.2 is pinned in pom.xml. What changed from Spring Boot 2.x to 3.x that is most relevant to this project?

### Short answer (30~60 seconds)

Runtime: Browser or client posts credentials to form login processing URL → DaoAuthenticationProvider validates via UserDetailsServiceImpl and BCryptPasswordEncoder → LoginSuccessHandler / LoginFailureHandler write audit and return JSON or redirect. Spring Boot 3.x 最相關的變化：Jakarta EE 10 命名空間替換（javax.* → jakarta.*），pom.xml 中的 import 全部是 jakarta.servlet.*、jakarta.validation.*。Spring Security 6.x（隨 Boot 3 升級）的 HttpSecurity 配置從 method chaining 改為 lambda DSL（csrf(csrf -> ...)、sessionManagement(session -> ...)）。@SpringBootTest 預設行為改變（WebEnvironment.DEFINED_PORT vs MOCK）。Actuator 的 health endpoint 格式調整。SpringDoc OpenAPI 也從 2.x 升到 3.x。

### Key files

- `sp2-springboot/pom.xml`
- `sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java`

### Possible follow-up questions

- Is there any javax.* import remaining in the codebase that would indicate an incomplete migration?
- What does @EnableMethodSecurity replace from Spring Security 5?
- What would break if you downgraded to Spring Boot 2.7 without code changes?

### Common mistakes

- Giving a generic textbook answer that is not grounded in this repository.
- Missing the interview angle: Tests awareness of migration concerns; many teams are mid-migration from Boot 2 to Boot 3.

---

## 6. Q025 — The project configures SessionCreationPolicy.IF_REQUIRED. What does this mean, and when would you change it to STATELESS?

**Category:** Spring Boot 3.x · **Difficulty:** Intermediate · **Status:** Verified

### Question

The project configures SessionCreationPolicy.IF_REQUIRED. What does this mean, and when would you change it to STATELESS?

### Short answer (30~60 seconds)

Runtime: Browser or client posts credentials to form login processing URL → DaoAuthenticationProvider validates via UserDetailsServiceImpl and BCryptPasswordEncoder → LoginSuccessHandler / LoginFailureHandler write audit and return JSON or redirect. IF_REQUIRED：Spring Security 僅在需要時建立 session（使用者認證成功後、或需要存儲 SecurityContext 時）。這是 Thymeleaf server-rendered portal 的正確選擇，因為 browser 需要維持登入狀態。STATELESS：每個請求都必須攜帶認證憑據（如 Bearer token），Spring Security 不建立也不讀取 session，適合 pure REST API + JWT 場景。何時切換：若日後為行動端或第三方服務增加 JWT filter chain（/api/v2/**），該 filter chain 設定 STATELESS，而 Thymeleaf 的 filter chain 繼續使用 IF_REQUIRED，兩個 filter chain 並存。

### Key files

- `sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java`
- `sp2-springboot/docs/decisions/0006-session-over-jwt.md`

### Possible follow-up questions

- What is NEVER policy, and when would you use it?
- How does SessionCreationPolicy.STATELESS interact with @SessionAttributes?
- Can you have two SecurityFilterChain beans with different session policies?

### Common mistakes

- Assuming JWT is the current auth mechanism.

---

## 7. Q087 — Why was session-based auth chosen over JWT? Under what future circumstances would you add a JWT filter chain alongside the existing session chain?

**Category:** Session Authentication vs JWT · **Difficulty:** Advanced · **Status:** Verified

### Question

Why was session-based auth chosen over JWT? Under what future circumstances would you add a JWT filter chain alongside the existing session chain?

### Short answer (30~60 seconds)

Runtime: Browser or client posts credentials to form login processing URL → DaoAuthenticationProvider validates via UserDetailsServiceImpl and BCryptPasswordEncoder → LoginSuccessHandler / LoginFailureHandler write audit and return JSON or redirect. 選擇 session 的理由（ADR 0006）：Staff portal 是 Thymeleaf server-rendered（browser form login），session 提供即時登出和 maximumSessions(1) 控制，比 JWT 的 token invalidation 問題（JWT 無法立即撤銷）更適合這個場景。加入 JWT filter chain 的時機：當需要支援 mobile app 或第三方服務消費 TLBank API（不用 browser session）；

### Key files

- `sp2-springboot/docs/decisions/0006-session-over-jwt.md`
- `sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md`
- `sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java`

### Possible follow-up questions

- What is the JWT token invalidation problem, and how do blocklists solve it?
- What is the @Order annotation's role when you have two SecurityFilterChain beans?
- How would refresh tokens work in a JWT setup for this system?

### Common mistakes

- Assuming JWT is the current auth mechanism.

---

## 8. Q077 — Spring Security BCryptPasswordEncoder is configured with strength 12. What does the strength parameter control, and what is the performance trade-off?

**Category:** Spring Security · **Difficulty:** Intermediate · **Status:** Verified

### Question

Spring Security BCryptPasswordEncoder is configured with strength 12. What does the strength parameter control, and what is the performance trade-off?

### Short answer (30~60 seconds)

Runtime: Browser or client posts credentials to form login processing URL → DaoAuthenticationProvider validates via UserDetailsServiceImpl and BCryptPasswordEncoder → LoginSuccessHandler / LoginFailureHandler write audit and return JSON or redirect. BCrypt strength 12：strength 參數（work factor）決定 bcrypt 的 cost，即 2^12 = 4096 次的 key schedule 迭代。CPU work factor：strength 每增加 1，計算時間約 ×2。在現代硬體上 strength 12 大約需要 100–300 ms 驗證一個密碼，strength 10（Spring 預設）約 25–75 ms。Security vs UX 取捨：較高的 strength 使 brute-force 攻擊更慢（攻擊者試 1000 個密碼需要 100–300 秒），但登入時每次驗證也需要 100–300 ms。對銀行應用場景（有嚴格的安全要求），strength 12 是合理的選擇。若用戶量大（1000 並發登入），每個登入請求占用 300 ms CPU 可能成為瓶頸。

### Key files

- `sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java`

### Possible follow-up questions

- What happens if you increase BCrypt strength from 12 to 14 in a live system?
- How does BCryptPasswordEncoder.matches() work? Does it require the same strength to verify?
- What is Argon2 and when would it be preferred over BCrypt?

### Common mistakes

- Giving a generic textbook answer that is not grounded in this repository.
- Missing the interview angle: BCrypt work factor is a security engineering question with real performance implications; tests whether the candidate understands the trade-off.

---

## 9. Q095 — ApplicationJpaRepository uses both JPQL (@Query with entity names) and native SQL (nativeQuery = true). Why mix the two?

**Category:** JPA and Hibernate · **Difficulty:** Intermediate · **Status:** Verified

### Question

ApplicationJpaRepository uses both JPQL (@Query with entity names) and native SQL (nativeQuery = true). Why mix the two?

### Short answer (30~60 seconds)

Runtime: Request enters presentation layer (REST or Thymeleaf controller) → Application service orchestrates domain aggregate and ports → Infrastructure adapter persists or calls external/mock dependency. 混用的理由：JPQL 查詢（SELECT COUNT(a) FROM ApplicationEntity a WHERE a.createdAt >= :start）：使用 JPA entity 名稱和欄位名稱，與底層 table/column 命名無關，若重命名 table 或欄位（加 @Column(name="...")），JPQL 不受影響，適合簡單的計數查詢。Native SQL 查詢（SELECT status, COUNT(*) FROM applications WHERE... GROUP BY status）：使用真實 table 和 column 名稱，適合聚合報表查詢（GROUP BY），特別是當 JPQL 的聚合語法比 native SQL 繁瑣時，或需要特定資料庫函數（SQL Server 特有語法）。

### Key files

- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationJpaRepository.java`

### Possible follow-up questions

- What would happen to the native SQL queries if the applications table were renamed?
- How would a Spring Data Projection interface change the return type of the native query?
- Could @NamedNativeQuery on the entity class improve maintainability?

### Common mistakes

- Giving a generic textbook answer that is not grounded in this repository.
- Missing the interview angle: Knowing when to use JPQL vs native SQL is a practical JPA judgment call; tests whether the candidate can articulate the trade-off.

---

## 10. Q034 — How does TLBank handle the mapping between domain aggregates and JPA entities for the WorkflowHistory embedded in Application?

**Category:** Clean / Hexagonal Architecture · **Difficulty:** Advanced · **Status:** Verified

### Question

How does TLBank handle the mapping between domain aggregates and JPA entities for the WorkflowHistory embedded in Application?

### Short answer (30~60 seconds)

Runtime: Request enters presentation layer (REST or Thymeleaf controller) → Application service orchestrates domain aggregate and ports → Infrastructure adapter persists or calls external/mock dependency. Application aggregate 包含 List<WorkflowHistory> 作為子值物件列表（記錄每次狀態轉換）。對應的 JPA 結構：ApplicationEntity 包含 List<WorkflowHistoryEntity>（一對多關係），WorkflowHistoryEntity 有 @ManyToOne 關聯回 ApplicationEntity。ApplicationRepositoryImpl.toDomain() 將 WorkflowHistoryEntity list 轉換為 WorkflowHistory domain 物件列表；toEntity() 反向轉換。

### Key files

- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationRepositoryImpl.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/WorkflowHistoryEntity.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/WorkflowHistory.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java`

### Possible follow-up questions

- What JPA cascade type would you use for WorkflowHistoryEntity?
- What happens if the domain Application has new WorkflowHistory entries that the entity does not yet know about?
- How would orphanRemoval affect the history list if you accidentally called clear() on it?

### Common mistakes

- Claiming a perfect Clean Architecture with no leaks.

---

## 11. Q127 — ApplicationAppService.createApplication() has @Transactional but getApplication() has @Transactional(readOnly = true). Why does even a read-only query need a transaction?

**Category:** Transactions · **Difficulty:** Advanced · **Status:** Verified

### Question

ApplicationAppService.createApplication() has @Transactional but getApplication() has @Transactional(readOnly = true). Why does even a read-only query need a transaction?

### Short answer (30~60 seconds)

Runtime: Request enters presentation layer (REST or Thymeleaf controller) → Application service orchestrates domain aggregate and ports → Infrastructure adapter persists or calls external/mock dependency. Read-only 也需要 transaction 的原因：(1) JPA Lazy Loading：getApplication() 讀取 ApplicationEntity 後呼叫 entity.getWorkflowHistories()（lazy collection），若沒有 open session，就拋 LazyInitializationException；@Transactional 確保整個方法執行期間 Hibernate session 保持 open，lazy load 可以在 session 內完成；

### Key files

- `sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationEntity.java`

### Possible follow-up questions

- What is open-session-in-view and why is it considered an anti-pattern?
- Does @Transactional(readOnly = true) propagate to a called service method with @Transactional?
- How would @EntityGraph or JOIN FETCH eliminate the need for @Transactional in a simple read method?

### Common mistakes

- Ignoring where @Transactional actually sits (app service vs repository/scheduler).

---

## 12. Q131 — @Transactional is on the application service methods, not on the domain methods. Why this placement?

**Category:** Transactions · **Difficulty:** Intermediate · **Status:** Verified

### Question

@Transactional is on the application service methods, not on the domain methods. Why this placement?

### Short answer (30~60 seconds)

Runtime: Request enters presentation layer (REST or Thymeleaf controller) → Application service orchestrates domain aggregate and ports → Infrastructure adapter persists or calls external/mock dependency. @Transactional 在 application service 的理由：(1) Transaction 是 use case 的 concern：一個 use case（如 submitApplication()）需要在單一 ACID transaction 中完成多個 DB 操作（save Application、save OTP、emit event）；use case 的範圍就是 transaction 的範圍，由 application service 控制；(2) Domain 純粹性：domain aggregate 方法（application.submit()）是純業務邏輯，不應知道「我是否在 transaction 中」；加了 @Transactional 就讓 domain 依賴 Spring，違反 clean architecture；

### Key files

- `sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java`

### Possible follow-up questions

- Can @Transactional on a Spring bean call a domain method that modifies state outside the transaction?
- What propagation behavior does @Transactional use by default?
- If Application were a Spring @Service bean, would putting @Transactional on it be appropriate?

### Common mistakes

- Ignoring where @Transactional actually sits (app service vs repository/scheduler).

---

## 13. Q114 — V3__create_applications.sql in the H2 migration sets status DEFAULT 'DRAFT', but ApplicationStatus enum has no DRAFT constant. Is this a bug?

**Category:** SQL Server and H2 · **Difficulty:** Advanced · **Status:** Documentation-only

### Question

V3__create_applications.sql in the H2 migration sets status DEFAULT 'DRAFT', but ApplicationStatus enum has no DRAFT constant. Is this a bug?

### Short answer (30~60 seconds)

分析：H2 migration V3__create_applications.sql：status VARCHAR(30) NOT NULL DEFAULT 'DRAFT'，但 ApplicationStatus enum 是：INIT, OTP_VERIFIED, DOCUMENT_UPLOADED, SUBMITTED, UNDER_REVIEW, APPROVED, REJECTED, CANCELLED，沒有 DRAFT。影響：DEFAULT 'DRAFT' 只在直接 INSERT 時不提供 status 欄位才生效。

### Key files

- `sp2-springboot/src/main/resources/db/migration/V3__create_applications.sql`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationStatus.java`

### Possible follow-up questions

- How would you fix this inconsistency — in the SQL migration or in the domain enum?
- Would ddl-auto: validate catch this inconsistency? Why or why not?
- What Flyway migration would you write to fix the DEFAULT value?

### Common mistakes

- Answering from documentation tone without citing verified source files or classes from the source map.

---

## 14. Q111 — H2 is configured with MODE=MSSQLServer in application-dev.yml. What SQL compatibility does this provide, and what are its limits?

**Category:** SQL Server and H2 · **Difficulty:** Intermediate · **Status:** Documentation-only

### Question

H2 is configured with MODE=MSSQLServer in application-dev.yml. What SQL compatibility does this provide, and what are its limits?

### Short answer (30~60 seconds)

MODE=MSSQLServer 提供的相容性：H2 模擬 SQL Server 的一些語法特性，如 ISNULL() 函數（SQL Server 的 null 替換）、TOP N 語法（H2 預設是 LIMIT N）、@@IDENTITY 函數、DATETIME2 資料型別（部分支援）。H2 connection URL：jdbc:h2:mem:tlbank_lending;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE;MODE=MSSQLServer。限制（已知不完全相容）：(1) GETDATE() 函數（SQL Server）vs CURRENT_TIMESTAMP（ANSI SQL，H2 支援）；(2) IDENTITY(1,1) 語法（SQL Server PRIMARY KEY 定義）vs GENERATED BY DEFAULT AS IDENTITY（H2）；

### Key files

- `sp2-springboot/src/main/resources/application-dev.yml`
- `sp2-springboot/src/main/resources/db/migration/V1__create_users.sql`
- `sp2-springboot/src/main/resources/db/migration-sqlserver/V1__create_users.sql`

### Possible follow-up questions

- What would happen if you accidentally used GETDATE() in the H2 migration scripts?
- How do the two V1__create_users.sql files differ between H2 and SQL Server?
- What is the risk of writing a query that passes H2 tests but fails in SQL Server?

### Common mistakes

- Answering from documentation tone without citing verified source files or classes from the source map.

---

## 15. Q067 — ApplicationApiController.getApplication() returns ApiResponse<ApplicationDetailResponse> directly, not ResponseEntity<ApiResponse<...>>. Why does only the create endpoint use ResponseEntity?

**Category:** REST API Design and OpenAPI · **Difficulty:** Intermediate · **Status:** Verified

### Question

ApplicationApiController.getApplication() returns ApiResponse<ApplicationDetailResponse> directly, not ResponseEntity<ApiResponse<...>>. Why does only the create endpoint use ResponseEntity?

### Short answer (30~60 seconds)

Runtime: Request enters presentation layer (REST or Thymeleaf controller) → Application service orchestrates domain aggregate and ports → Infrastructure adapter persists or calls external/mock dependency. ResponseEntity 的使用場景：createApplication() 用 ResponseEntity.status(HttpStatus.CREATED).body(...) 是因為需要設置 HTTP 201 狀態碼（資源被建立），也因為 IdempotencyService.execute() 的回傳型別就是 ResponseEntity<ApiResponse<T>>（需要重建 idempotency 快取中的 HTTP status）。getApplication() 直接回傳 ApiResponse<ApplicationDetailResponse>，Spring MVC 會自動以 HTTP 200 回應，因為查詢操作不需要自訂 status code，@ResponseBody 由 @RestController 隱含提供。

### Key files

- `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/v1/ApplicationApiController.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/application/idempotency/IdempotencyService.java`

### Possible follow-up questions

- How does Spring MVC determine the HTTP status when you return a non-ResponseEntity from a @RestController?
- What @ResponseStatus annotation could replace ResponseEntity.status(CREATED) on the method?
- Could getApplication() ever need to return 204 No Content? When?

### Common mistakes

- Treating Redis as a general application cache or session store.

---

## 16. Q300 — GlobalExceptionHandler maps domain and validation failures to ApiResponse. How are WorkflowException and validation errors represented?

**Category:** Validation and Exception Handling · **Difficulty:** Intermediate · **Status:** Verified

### Question

GlobalExceptionHandler maps domain and validation failures to ApiResponse. How are WorkflowException and validation errors represented?

### Short answer (30~60 seconds)

Runtime: Controller/service throws or validation fails → GlobalExceptionHandler selects @ExceptionHandler method → Returns ApiResponse.error(...) with ErrorCode and HTTP status. GlobalExceptionHandler 位於 presentation.api.advice，將例外轉成統一 ApiResponse 與 HTTP 狀態。工作流非法轉換走 WorkflowException；Bean Validation 失敗則回傳欄位錯誤資訊。錯誤碼集中於 ErrorCode，避免控制器各自組裝錯誤格式。

### Key files

- `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/advice/GlobalExceptionHandler.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/common/exception/ErrorCode.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/common/response/ApiResponse.java`

### Possible follow-up questions

- Where is WorkflowException thrown?
- How does @Valid on DTOs reach this handler?
- Why keep ApiResponse as a shared envelope?

### Common mistakes

- Giving a generic textbook answer that is not grounded in this repository.
- Missing the interview angle: Confirms API error-contract knowledge grounded in this project exception layer.

---

## 17. Q250 — SecurityIntegrationTest covers login and authorization. Which behaviors should a candidate expect it to assert?

**Category:** Spring Security · **Difficulty:** Intermediate · **Status:** Verified

### Question

SecurityIntegrationTest covers login and authorization. Which behaviors should a candidate expect it to assert?

### Short answer (30~60 seconds)

Runtime: Test boots Spring Security test context → MockMvc posts form login and secured URLs → Assertions check status codes and JSON error contracts. SecurityIntegrationTest 以 MockMvc 驗證登入成功/失敗、角色授權與工作階段相關行為，密碼對應 dev seed 的測試常數。此測試鎖定 SecurityConfig 與 handler 合約，屬於安全回歸的主要自動化防線。

### Key files

- `sp2-springboot/src/test/java/com/tlbank/lending/security/SecurityIntegrationTest.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java`

### Possible follow-up questions

- How are roles mapped from user_roles?
- What does maximumSessions(1) do on concurrent login?
- Why are some applicant APIs permitAll?

### Common mistakes

- Giving a generic textbook answer that is not grounded in this repository.
- Missing the interview angle: Links security design answers to the actual automated proof in the repo.

---

## 18. Q155 — ExcelReportGeneratorTest creates an XSSFWorkbook from the generated bytes to verify the sheet structure. What does this test strategy reveal about testing output formats?

**Category:** Testing (JUnit + Mockito + MockMvc) · **Difficulty:** Intermediate · **Status:** Verified

### Question

ExcelReportGeneratorTest creates an XSSFWorkbook from the generated bytes to verify the sheet structure. What does this test strategy reveal about testing output formats?

### Short answer (30~60 seconds)

Runtime: Domain event published after workflow milestone → NotificationEventHandler invokes NotificationServiceImpl → MockSmsSender / MockEmailSender log mock delivery (tlbank.notification.mode=mock). 測試策略的啟示：ExcelReportGeneratorTest 不只驗證「方法不拋 exception」，也驗證 output 的結構（workbook.getNumberOfSheets() == 2、sheet name 是 "Summary" 和 "By Product"）和內容（所有 ApplicationStatus 值都出現在 Summary sheet）。使用 Apache POI 本身解析 output：new XSSFWorkbook(new ByteArrayInputStream(content)) 讀回剛生成的 bytes，直接操作 Sheet、Row、Cell，是「round-trip testing」——用同一個工具驗證自己的 output。

### Key files

- `sp2-springboot/src/test/java/com/tlbank/lending/infrastructure/report/ExcelReportGeneratorTest.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/report/ExcelReportGenerator.java`

### Possible follow-up questions

- How would you test PdfReportGenerator without parsing the PDF bytes in detail?
- What is a snapshot test and could it work for Excel report verification?
- What happens in the test if a new ApplicationStatus is added to the enum?

### Common mistakes

- Describing mock notification adapters as real SMS/email integrations.

---

## 19. Q132 — IdempotencyService.execute() acquires a lock with tryAcquireLock() and always releases it in finally. Why is finally critical here?

**Category:** Redis and Idempotency · **Difficulty:** Advanced · **Status:** Verified

### Question

IdempotencyService.execute() acquires a lock with tryAcquireLock() and always releases it in finally. Why is finally critical here?

### Short answer (30~60 seconds)

Runtime: Controller receives optional Idempotency-Key header on create application → IdempotencyService.execute() hashes request body and looks up IdempotencyStore.find() → On miss, tryAcquireLock(); run create use case; save response snapshot; releaseLock(). finally 的必要性：lock 在 try 區塊取得（idempotencyStore.tryAcquireLock(lockKey, LOCK_TTL_SECONDS)），業務邏輯（action.get()）在 try 內執行，finally 確保 idempotencyStore.releaseLock(lockKey) 不論業務邏輯是否拋 exception 都執行。若沒有 finally：若 action.get() 拋 exception（如 DB 錯誤），lock 不會被釋放，lockKey 在 Redis 中的 TTL（30 秒）到期前，任何相同 idempotency key 的重試都會得到「A request with this Idempotency-Key is already in progress」的錯誤，即使前一個請求已失敗。

### Key files

- `sp2-springboot/src/main/java/com/tlbank/lending/application/idempotency/IdempotencyService.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/idempotency/RedisIdempotencyStore.java`

### Possible follow-up questions

- What is the risk of a very long-running action.get() combined with a 30-second lock TTL?
- How does redisTemplate.opsForValue().setIfAbsent(lockKey, "1", Duration) implement SETNX semantics?
- What is Lua scripting in Redis, and why would it make lock/unlock more atomic?

### Common mistakes

- Treating Redis as a general application cache or session store.

---

## 20. Q268 — RedisIdempotencyStore is activated by @ConditionalOnProperty(name = "tlbank.idempotency.store", havingValue = "redis"). What happens when this property is absent — which IdempotencyStore bean is active?

**Category:** Redis and Idempotency · **Difficulty:** Intermediate · **Status:** Verified

### Question

RedisIdempotencyStore is activated by @ConditionalOnProperty(name = "tlbank.idempotency.store", havingValue = "redis"). What happens when this property is absent — which IdempotencyStore bean is active?

### Short answer (30~60 seconds)

Runtime: Controller receives optional Idempotency-Key header on create application → IdempotencyService.execute() hashes request body and looks up IdempotencyStore.find() → On miss, tryAcquireLock(); run create use case; save response snapshot; releaseLock(). Bean 啟動邏輯分析：RedisIdempotencyStore：@ConditionalOnProperty(name = "tlbank.idempotency.store", havingValue = "redis")，沒有 matchIfMissing = true，所以若 property 不存在 → bean 不啟動。InMemoryIdempotencyStore（或 InMemoryIdempotencyStore）：需要查看其 @Conditional 設定。若 in-memory store 有 @ConditionalOnProperty(..., matchIfMissing = true)，則 property 不存在時 in-memory store 是 default。

### Key files

- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/idempotency/RedisIdempotencyStore.java`
- `sp2-springboot/src/main/resources/application-dev.yml`

### Possible follow-up questions

- What exception would occur if neither RedisIdempotencyStore nor InMemoryIdempotencyStore was active?
- How would you verify in a test that InMemoryIdempotencyStore is the active bean?
- Could you use @Profile("dev") instead of @ConditionalOnProperty for the same effect?

### Common mistakes

- Treating Redis as a general application cache or session store.

---

## 21. Q183 — The Dockerfile uses a two-stage build. What are the benefits, and what specific images are used for each stage?

**Category:** Docker and Docker Compose · **Difficulty:** Intermediate · **Status:** Documentation-only

### Question

The Dockerfile uses a two-stage build. What are the benefits, and what specific images are used for each stage?

### Short answer (30~60 seconds)

Runtime: docker compose up builds/runs SQL Server and app services → App container uses staging profile and SQL Server migrations → Healthcheck hits actuator endpoint (currently auth-gated). 兩階段 build 的好處：第一階段（builder）：FROM eclipse-temurin:17-jdk AS builder，包含完整 JDK（javac、maven 工具）+ 所有 build artifacts，編譯和打包 JAR。第二階段（runtime）：FROM eclipse-temurin:17-jre AS runtime，只包含 JRE（執行 .class 不需要 javac、jlink 等工具），COPY --from=builder /workspace/target/*.jar app.jar 複製 JAR。好處：(1) 大幅縮小最終 image 大小（JDK 約 500MB，JRE 約 180MB）；(2) 安全性：JDK 的 javac、jarsigner 等工具不在 runtime image 中，減少攻擊面；

### Key files

- `sp2-springboot/docker/app/Dockerfile`

### Possible follow-up questions

- What is the difference between JDK and JRE? Why is JRE sufficient for the runtime stage?
- What is Eclipse Temurin (Adoptium), and why use it over openjdk:17?
- How much smaller is the runtime image compared to building in a single stage?

### Common mistakes

- Answering from documentation tone without citing verified source files or classes from the source map.

---

## 22. Q191 — The CI pipeline has 5 jobs with needs dependencies. Draw the dependency graph and identify the critical path.

**Category:** GitHub Actions CI/CD · **Difficulty:** Intermediate · **Status:** Documentation-only

### Question

The CI pipeline has 5 jobs with needs dependencies. Draw the dependency graph and identify the critical path.

### Short answer (30~60 seconds)

Runtime: Push/PR triggers CI jobs in .github/workflows/ci.yml → build-test runs mvn clean verify; later jobs scan and may push GHCR image → deploy-staging runs only on workflow_dispatch against self-hosted macOS runner. 5 job 依賴圖：build-test → code-quality（needs: build-test）→ 無後繼（僅止於此）；build-test → dependency-scan（needs: build-test）→ 無後繼；build-test + code-quality + dependency-scan → build-and-push-image（needs: [build-test, code-quality, dependency-scan]）→ deploy-staging（needs: build-and-push-image，且只在 workflow_dispatch 執行）。並行：code-quality 和 dependency-scan 在 build-test 完成後並行執行。

### Key files

- `.github/workflows/ci.yml`

### Possible follow-up questions

- What would happen if code-quality failed but dependency-scan succeeded?
- How would you parallelize further to reduce the total pipeline time?
- Why does deploy-staging need build-and-push-image rather than just build-test?

### Common mistakes

- Answering from documentation tone without citing verified source files or classes from the source map.
- Calling staging deployment fully automatic CD.

---

## 23. Q005 — The repository has no automatic promotion to staging — deployment requires workflow_dispatch. Why is that design intentional?

**Category:** Project Overview and One-Repository Strategy · **Difficulty:** Intermediate · **Status:** Documentation-only

### Question

The repository has no automatic promotion to staging — deployment requires workflow_dispatch. Why is that design intentional?

### Short answer (30~60 seconds)

Staging 環境是本機 Mac（self-hosted runner），不是雲端常駐服務，不能由 Push 自動觸發，否則每次 push 到 main 都會嘗試部署到可能不在線上的 Mac。workflow_dispatch 讓部署時機完全由人工決定，避免自動觸發打斷 local Docker Compose 運行中的狀態。另外，staging 部署步驟需要 MSSQL_SA_PASSWORD、GHCR_USERNAME、GHCR_TOKEN 等 secret，手動觸發可以確保有人知道環境就緒再部署。正確描述：CI 是自動的（push/PR 觸發），CD 是手動的（workflow_dispatch）。

### Key files

- `.github/workflows/ci.yml`

### Possible follow-up questions

- What would need to change to make deployment automatic to a cloud environment?
- How would you add a rollback mechanism to the current deploy step?
- Why does deploy-staging have if: github.event_name == 'workflow_dispatch' as a guard?

### Common mistakes

- Answering from documentation tone without citing verified source files or classes from the source map.
- Calling staging deployment fully automatic CD.

---

## 24. Q234 — MdcLoggingFilter puts request correlation fields into MDC. What is logged, and what is still missing for production observability?

**Category:** Observability and Logging · **Difficulty:** Intermediate · **Status:** Verified

### Question

MdcLoggingFilter puts request correlation fields into MDC. What is logged, and what is still missing for production observability?

### Short answer (30~60 seconds)

Runtime: Request hits filter chain; MdcLoggingFilter populates MDC → Controllers/services log with correlation fields → Filter clears MDC after request completes. MdcLoggingFilter 在請求進入時寫入 MDC（如 requestId、username），供 Logback pattern 輸出。現況以單機日誌為主，維護文件指出後續才會引入 OpenTelemetry 分散式追蹤與 Micrometer 指標；不可把現況說成已具備完整 observability stack。

### Key files

- `sp2-springboot/src/main/java/com/tlbank/lending/security/filter/MdcLoggingFilter.java`
- `sp2-springboot/src/main/resources/logback-spring.xml`
- `sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md`

### Possible follow-up questions

- Where is the filter registered in the security chain?
- What fields appear in the console pattern?
- What would OpenTelemetry add beyond MDC?

### Common mistakes

- Giving a generic textbook answer that is not grounded in this repository.
- Missing the interview angle: Checks honest scoping of logging versus full observability platforms.

---

## 25. Q042 — Application is the aggregate root. What responsibility does it have for maintaining invariants?

**Category:** Domain-Driven Design · **Difficulty:** Intermediate · **Status:** Verified

### Question

Application is the aggregate root. What responsibility does it have for maintaining invariants?

### Short answer (30~60 seconds)

Runtime: Request enters presentation layer (REST or Thymeleaf controller) → Application service orchestrates domain aggregate and ports → Infrastructure adapter persists or calls external/mock dependency. Application 作為 aggregate root 的 invariant 責任：(1) 狀態轉換合法性：transitionTo() 驗證 ALLOWED_TRANSITIONS，非法轉換拋 WorkflowException，aggregate 自身保護狀態一致性，不依賴外部 service 來判斷合法性。(2) 文件完整性：submit() 呼叫 validateRequiredDocuments()，確保所有 DocumentType（NATIONAL_ID、INCOME_PROOF、RESIDENCE_PROOF）都已上傳，否則拋 BusinessException(INCOMPLETE_DOCUMENTS)。(3) 取消限制：cancel() 只在 CANCELLABLE_STATUSES（INIT、OTP_VERIFIED、DOCUMENT_UPLOADED）允許，已 SUBMITTED 的申請不可取消。

### Key files

- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationStatus.java`

### Possible follow-up questions

- What happens if ApplicationAppService tries to move an application from APPROVED back to SUBMITTED?
- Why is transitionTo() private? What would break if it were public?
- Could you write a unit test that verifies APPROVED → SUBMITTED throws WorkflowException without any Spring context?

### Common mistakes

- Giving a generic textbook answer that is not grounded in this repository.
- Missing the interview angle: Invariant enforcement is the core value proposition of aggregates; tests whether the candidate understands why business rules belong in the domain.

---

## 26. Q051 — Walk me through every valid state transition in ApplicationStatus. Which transitions are intentionally missing, and why?

**Category:** Application State Machine · **Difficulty:** Intermediate · **Status:** Verified

### Question

Walk me through every valid state transition in ApplicationStatus. Which transitions are intentionally missing, and why?

### Short answer (30~60 seconds)

Runtime: Use-case calls Application behavior method (submit/cancel/verifyOtp/uploadDocuments/...) → Application.transitionTo() (private) consults ApplicationStatus.canTransitionTo() → Illegal edge throws WorkflowException; legal edge updates status and appends WorkflowHistory. 合法轉換（ALLOWED_TRANSITIONS map）：INIT → OTP_VERIFIED 或 CANCELLED；OTP_VERIFIED → DOCUMENT_UPLOADED 或 CANCELLED；DOCUMENT_UPLOADED → SUBMITTED 或 CANCELLED；SUBMITTED → UNDER_REVIEW；UNDER_REVIEW → APPROVED 或 REJECTED。刻意缺少的轉換：APPROVED/REJECTED/CANCELLED 不在 map 中作為 key，代表這三個狀態是終止態，不可再轉換。SUBMITTED → CANCELLED 不允許（已進入審核佇列的申請不讓申請人自行撤回）。UNDER_REVIEW → CANCELLED 不允許（審核中不可中途取消）。APPROVED → REJECTED 不允許（已核准不可反悔，需走其他業務流程）。

### Key files

- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationStatus.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java`

### Possible follow-up questions

- What HTTP status does the API return when a client sends an invalid state transition?
- Why is CANCELLED reachable from only three statuses and not from SUBMITTED?
- If the business required allowing applicants to withdraw after submission, where exactly would you add that transition?

### Common mistakes

- Giving a generic textbook answer that is not grounded in this repository.
- Missing the interview angle: Reading a state machine and explaining business intent from code is a key backend skill; tests whether the candidate can map code to business rules.

---

## 27. Q047 — Domain events in TLBank are plain Java objects with no framework annotations. What are the implications of this design?

**Category:** Domain-Driven Design · **Difficulty:** Advanced · **Status:** Verified

### Question

Domain events in TLBank are plain Java objects with no framework annotations. What are the implications of this design?

### Short answer (30~60 seconds)

Runtime: Request enters presentation layer (REST or Thymeleaf controller) → Application service orchestrates domain aggregate and ports → Infrastructure adapter persists or calls external/mock dependency. Domain event 是 plain Java（如 record 或 simple class），只帶業務資料（applicationId、timestamp 等），沒有 Spring 或 Jackson 注解。意義：(1) domain event 可被 domain unit test 直接建立和驗證，不需要 Spring context；(2) event handler 可以在不改動 domain event class 的情況下更換（將 @EventListener 替換為 Kafka producer）；(3) event class 是 domain 語言的一部分，不洩漏 infrastructure concern。

### Key files

- `sp2-springboot/src/main/java/com/tlbank/lending/domain/event/ApplicationApprovedEvent.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/event/ApplicationSubmittedEvent.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/event/OtpGeneratedEvent.java`

### Possible follow-up questions

- What data should a domain event always include?
- How would you serialize a domain event to JSON for Kafka without adding Jackson annotations to the domain event itself?
- Should domain events be immutable? Why?

### Common mistakes

- Giving a generic textbook answer that is not grounded in this repository.
- Missing the interview angle: Tests understanding of how to keep domain events framework-agnostic; relevant to event-driven architecture evolution.

---

## 28. Q052 — ApplicationStatus.canTransitionTo() returns false for null. Why is this defensive check necessary?

**Category:** Application State Machine · **Difficulty:** Intermediate · **Status:** Verified

### Question

ApplicationStatus.canTransitionTo() returns false for null. Why is this defensive check necessary?

### Short answer (30~60 seconds)

Runtime: Use-case calls Application behavior method (submit/cancel/verifyOtp/uploadDocuments/...) → Application.transitionTo() (private) consults ApplicationStatus.canTransitionTo() → Illegal edge throws WorkflowException; legal edge updates status and appends WorkflowHistory. canTransitionTo(null) 的防禦性檢查：方法開頭有 if (next == null) { return false; }，避免後續 ALLOWED_TRANSITIONS.get(this) 返回的 Set 呼叫 contains(null) 拋 NullPointerException（EnumSet 不允許 null 元素，contains(null) 在 EnumSet 中會拋 NPE）。更深層的理由：呼叫端（Application.transitionTo()）傳入 next 參數，若未做驗證直接呼叫 canTransitionTo(null)，就會在 EnumSet.contains(null) 處拋出非預期的 NPE，而非業務可理解的 WorkflowException。

### Key files

- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationStatus.java`

### Possible follow-up questions

- What would happen without the null check if you called INIT.canTransitionTo(null)?
- Does HashSet.contains(null) behave differently from EnumSet.contains(null)?
- Would adding @NonNull to the parameter signature be a better approach?

### Common mistakes

- Giving a generic textbook answer that is not grounded in this repository.
- Missing the interview angle: Defensive null handling is a hallmark of production-quality code; tests whether the candidate can explain why a one-line null check is architecturally.

---

## 29. Q070 — GlobalExceptionHandler catches AccessDeniedException. Why is catching Spring Security exceptions in a @RestControllerAdvice necessary?

**Category:** Validation and Exception Handling · **Difficulty:** Advanced · **Status:** Verified

### Question

GlobalExceptionHandler catches AccessDeniedException. Why is catching Spring Security exceptions in a @RestControllerAdvice necessary?

### Short answer (30~60 seconds)

Runtime: Controller/service throws or validation fails → GlobalExceptionHandler selects @ExceptionHandler method → Returns ApiResponse.error(...) with ErrorCode and HTTP status. 必要性：Spring Security 的 AccessDeniedException 和 AuthenticationException 預設由 ExceptionTranslationFilter 攔截，轉發到 AccessDeniedHandler/AuthenticationEntryPoint。但 @RestControllerAdvice 的 @ExceptionHandler 只能捕捉 dispatcher servlet 層的 exception，而 security filter 在 dispatcher 之前執行。這裡 GlobalExceptionHandler 能捕捉到 AccessDeniedException，表示 exception 是從 controller 或 service 拋出的（例如 @PreAuthorize 失敗），不是從 filter 層。

### Key files

- `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/advice/GlobalExceptionHandler.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/security/handler/CustomAccessDeniedHandler.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java`

### Possible follow-up questions

- What is the difference between AuthenticationException and AccessDeniedException?
- How does Spring Security decide whether to invoke the AuthenticationEntryPoint or AccessDeniedHandler?
- Would removing handleAccessDeniedException from GlobalExceptionHandler break anything?

### Common mistakes

- Giving a generic textbook answer that is not grounded in this repository.
- Missing the interview angle: The dual-handler pattern for security exceptions is subtle and reveals deep Spring Security knowledge.

---

## 30. Q031 — How does the presentation layer prevent business logic from leaking into controllers?

**Category:** Clean / Hexagonal Architecture · **Difficulty:** Intermediate · **Status:** Verified

### Question

How does the presentation layer prevent business logic from leaking into controllers?

### Short answer (30~60 seconds)

Runtime: Controller/service throws or validation fails → GlobalExceptionHandler selects @ExceptionHandler method → Returns ApiResponse.error(...) with ErrorCode and HTTP status. 防止業務邏輯進入 controller 的機制：Controller 只做三件事：@Valid 觸發 JSR-303 validation（欄位格式）、委派給一個 application service 方法、將結果包進 ApiResponse 回傳。業務規則（狀態轉換、OTP 驗證、文件完整性）全部在 domain aggregate 和 application service 中執行。Exception 翻譯在 GlobalExceptionHandler 集中處理，controller 不需要 try-catch。只有薄薄一層 DTO mapping（controller 接收 CreateApplicationRequest，轉成 application service 理解的參數）。任何在 controller 中出現的 if 判斷業務條件，都是 red flag。

### Key files

- `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/v1/ApplicationApiController.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/advice/GlobalExceptionHandler.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java`

### Possible follow-up questions

- Show me an example controller method. Does it contain any business logic?
- What would happen if you moved the OTP verification logic into the controller?
- How does @RestControllerAdvice work, and why is it preferable to try-catch in each controller?

### Common mistakes

- Claiming a perfect Clean Architecture with no leaks.

---
