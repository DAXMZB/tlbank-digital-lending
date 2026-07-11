# Interview Quick Review

Last-minute cheat sheet (≈30 minutes). Derived only from `09-interview-source-map-300.md`.
Bullets only — open the source map for full answers.

---

## Architecture

- **Q031:** 防止業務邏輯進入 controller 的機制：Controller 只做三件事：@Valid 觸發 JSR-303 validation（欄位格式）、委派給一個 application service 方法、將結果包進 ApiResponse 回傳。 — `ApplicationApiController.java`, `GlobalExceptionHandler.java`
- **Q033:** common：跨切關注點（cross-cutting concerns），不屬於任何業務 layer，不依賴業務 layer（common 可被所有 layer 引用）。 — `SchedulingConfig.java`, `ErrorCode.java`
- **Q035:** application.idempotency：IdempotencyService（use case 協調器），負責 idempotency key 解析、SHA-256 hash 計算、lock 取得、cache 命中判斷、response 重建。 — `IdempotencyService.java`, `CreateApplicationRequest.java`
- **Q029:** Mapping 的必要性：domain aggregate Application 不帶任何 JPA 注解（@Entity、@Column 等），才能在不依賴資料庫的情況下做 unit test。ApplicationEntity 帶 JPA 注解，負責對應資料庫 schema。 — `ApplicationRepositoryImpl.java`, `ApplicationEntity.java`
- **Q030:** Domain service（WorkflowDomainService）：包含跨多個 aggregate 或不適合放在單一 aggregate 上的業務規則；不持有狀態； — `WorkflowDomainService.java`, `ApplicationAppService.java`
- **Q034:** Application aggregate 包含 `List<WorkflowHistory>` 作為子值物件列表（記錄每次狀態轉換）。對應的 JPA 結構：ApplicationEntity 包含 `List<WorkflowHistoryEntity>`（一對多關係），WorkflowHistoryEn… — `ApplicationRepositoryImpl.java`, `WorkflowHistoryEntity.java`
- **Q284:** 依賴分析：CacheManagementService 注入 CachedCardProductRepository（具體 infrastructure class），而非 CardProductRepository（domain port interface）。 — `CacheManagementService.java`, `CachedCardProductRepository.java`
- **Q103:** 「最新」而非「任意」的理由：sendOtp() 的流程是先取消現有 pending OTP，再建立新 OTP。但若取消動作在 DB 還沒 commit 或有 race condition，可能存在多筆 PENDING OTP。 — `OtpJpaRepository.java`, `OtpAppService.java`
- **Q107:** 重複的 pattern：兩個 impl 都有 toDomain(Entity entity)、toEntity(Domain domain)、applyToEntity(Entity existing, Domain domain) 三個 mapping 方法，邏輯結構相似但具體欄位不同。 — `ApplicationRepositoryImpl.java`, `OtpRepositoryImpl.java`
- **Q109:** 發布時機：ReviewAppService.approveCase() 方法（或類似名稱）：(1) 讀取 ReviewCase；(2) 呼叫 reviewCase.approve(operator, remark)（aggregate 內部狀態轉換）；(3) 讀取相關的 Application； — `ReviewAppService.java`, `ApplicationApprovedEvent.java`
- **Q027:** 依賴方向：presentation → application → domain ← infrastructure。Domain 不依賴任何外層：Application（aggregate）只 import JDK 和 Lombok，沒有 @Entity、沒有 Spring 注解。 — `0001-use-clean-architecture.md`, `02-architecture-design.md`
- **Q028:** Ports and Adapters（Hexagonal Architecture）：Port 是 domain 定義的介面（interface），Adapter 是 infrastructure 實作該介面的具體類別。 — `ApplicationRepository.java`, `ApplicationRepositoryImpl.java`
- **Q276:** Response DTO 位置的一致性分析：CreateApplicationRequest、CancelApplicationRequest 等 request DTO 在 application.dto.request； — `ApplicationSummaryResponse.java`, `DocumentUploadResponse.java`
- **Q288:** Cohesion 分析：7 public methods：createApplication()、getApplication()、uploadDocuments()、submitApplication()、cancelApplication()、findAllEnabledProducts()、t… — `ApplicationAppService.java`

## Spring

- **Q017:** Spring Boot 3.x 最相關的變化：Jakarta EE 10 命名空間替換（`javax.*` → `jakarta.*`），pom.xml 中的 import 全部是 `jakarta.servlet.*`、`jakarta.validation.*`。 — `pom.xml`, `SecurityConfig.java`
- **Q025:** IF_REQUIRED：Spring Security 僅在需要時建立 session（使用者認證成功後、或需要存儲 SecurityContext 時）。這是 Thymeleaf server-rendered portal 的正確選擇，因為 browser 需要維持登入狀態。 — `SecurityConfig.java`, `0006-session-over-jwt.md`
- **Q024:** @Async 生效需要：@EnableAsync（在某個 @Configuration 類別上，此 project 中在 AsyncConfig 或 entry point 上），且呼叫者必須透過 Spring proxy 呼叫（同一個 class 內的 self-invocation 會繞過 pr… — `AsyncConfig.java`, `AuditLogWriter.java`
- **Q018:** @SpringBootApplication 是組合 annotation，等同於 @Configuration + @EnableAutoConfiguration + @ComponentScan(basePackages = "com.tlbank.lending")。 — `TlbankLendingApplication.java`, `AsyncConfig.java`
- **Q020:** Swagger UI 路徑：/swagger-ui/**、/swagger-ui.html、/v3/api-docs/**。SecurityConfig 中這些路徑被設定為 permitAll()，任何人都可以在不登入的情況下存取。 — `SwaggerConfig.java`, `SecurityConfig.java`
- **Q019:** ddl-auto: validate：Hibernate 啟動時掃描所有 @Entity 類別，驗證資料庫中的 table 和 column 是否與 entity 對應，但不做任何 DDL 修改（不 create、不 alter、不 drop）。 — `application.yml`, `V1__create_users.sql`
- **Q022:** CommonConfig 通常包含跨層共享的 utility bean：ObjectMapper bean（自訂 Jackson 序列化配置，供 IdempotencyService、RedisIdempotencyStore 等注入使用）； — `CommonConfig.java`, `OtpCleanupScheduler.java`
- **Q023:** @EnableMethodSecurity 啟用 @PreAuthorize、@PostAuthorize、@Secured、@RolesAllowed 等方法級別的安全注解（Spring Security 6 替代舊版 @EnableGlobalMethodSecurity）。 — `SecurityConfig.java`
- **Q026:** @SpringBootTest：加載整個 Spring Application Context（所有 bean、所有 auto-configuration），適合整合測試，確認多個 layer 協同運作。速度較慢。 — `ApplicationFlowIntegrationTest.java`, `SecurityIntegrationTest.java`
- **Q021:** Actuator 在此 project 的主要用途：Docker Compose 的 app service 使用 Actuator healthcheck 作為 container health endpoint（healthcheck.test 可能呼叫 /actuator/health）。 — `application.yml`, `docker-compose.yml`
- **Q009:** record：MobileNumber、Email、ApplicationId、IdempotencyEntry、request DTOs（不可變值物件，自動產生 constructor / equals / hashCode）。 — `MobileNumber.java`, `Email.java`
- **Q012:** Map.of() 限制：最多 10 個 key-value 對（超過要用 Map.ofEntries()）；key 和 value 不得為 null；傳回的 Map 不可修改（UnsupportedOperationException on put）；迭代順序未保證。 — `ApplicationStatus.java`

## Security

- **Q077:** BCrypt strength 12：strength 參數（work factor）決定 bcrypt 的 cost，即 2^12 = 4096 次的 key schedule 迭代。CPU work factor：strength 每增加 1，計算時間約 ×2。 — `SecurityConfig.java`
- **Q080:** OncePerRequestFilter 的保證：在同一個 HTTP request 的生命週期中，doFilterInternal() 只被呼叫一次，即使 Spring 的 forward/include dispatch 也不會重複執行（標準 Filter 在 forward 時可能被再次呼叫）… — `MdcLoggingFilter.java`, `logback-spring.xml`
- **Q082:** LoginResponseMode.prefersJson() 的判斷邏輯（推測，驗證需查看 source）：通常檢查 Accept header 是否包含 application/json（REST client 會帶這個 header），或 Content-Type 是否是 applicatio… — `LoginSuccessHandler.java`, `LoginResponseMode.java`
- **Q083:** Spring Security 的 role 慣例：hasRole("USER") 在 SpEL 中等同於 hasAuthority("ROLE_USER")。DaoAuthenticationProvider 期望 GrantedAuthority 以 ROLE_ 前綴，而 hasRole("X"… — `UserDetailsServiceImpl.java`, `SecurityConfig.java`
- **Q084:** 用戶體驗：用戶 A 在電腦登入後，用手機也登入同一帳號（因為 maximumSessions(1)，手機登入成功後，電腦端的 session 被標記為 expired）。 — `SessionExpiredStrategy.java`, `SecurityConfig.java`
- **Q096:** Masking 的應用點：ApplicationAppService.getApplication() 呼叫 toMaskedApplicant(application.getApplicant())，建立 MaskedApplicantResponse，其中 mobile = MaskingUti… — `MaskingUtil.java`, `ApplicationAppService.java`
- **Q250:** SecurityIntegrationTest 以 MockMvc 驗證登入成功/失敗、角色授權與工作階段相關行為，密碼對應 dev seed 的測試常數。此測試鎖定 SecurityConfig 與 handler 合約，屬於安全回歸的主要自動化防線。 — `SecurityIntegrationTest.java`, `SecurityConfig.java`
- **Q261:** JSON 為預設的理由：HTTP 的 Accept: */* 表示「任何格式都可以接受」，但實際上不同 client 的 behavior：(1) curl：預設發送 Accept: */*，期望 JSON 回應（REST API 測試場景）； — `LoginResponseMode.java`, `LoginSuccessHandler.java`
- **Q267:** operator 欄位的設置路徑：ReviewApiController.approveCase() 收到 HTTP request 後，從 Spring Security context 取出當前 authenticated user：SecurityContextHolder.getContex… — `ApproveCaseCommand.java`, `ReviewApiController.java`
- **Q078:** 處理流程：loadUserByUsername() 拋 DisabledException（Spring Security 的 AuthenticationException 子類別）。 — `UserDetailsServiceImpl.java`, `LoginFailureHandler.java`
- **Q079:** 觀察：LoginSuccessHandler.onAuthenticationSuccess() 直接呼叫 auditLogRepository.save(AuditLog.builder()...build())（同步），而其他業務操作透過 AuditAspect + AuditLogWriter… — `LoginSuccessHandler.java`, `AuditLogWriter.java`
- **Q081:** 配置方式：SecurityConfig 中 csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"))，CSRF protection 對 /api/** 的請求不啟用。 — `SecurityConfig.java`, `0006-session-over-jwt.md`

## Redis

- **Q134:** TTL 實現差異：RedisIdempotencyStore.save(key, entry, ttlSeconds)：呼叫 redisTemplate.opsForValue().set(key, json, Duration.ofSeconds(ttlSeconds))，TTL 由 Redis … — `InMemoryIdempotencyStore.java`, `RedisIdempotencyStore.java`
- **Q268:** Bean 啟動邏輯分析：RedisIdempotencyStore：@ConditionalOnProperty(name = "tlbank.idempotency.store", havingValue = "redis")，沒有 matchIfMissing = true，所以若 proper… — `RedisIdempotencyStore.java`, `application-dev.yml`
- **Q132:** finally 的必要性：lock 在 try 區塊取得（idempotencyStore.tryAcquireLock(lockKey, LOCK_TTL_SECONDS)），業務邏輯（action.get()）在 try 內執行，finally 確保 idempotencyStore.relea… — `IdempotencyService.java`, `RedisIdempotencyStore.java`
- **Q011:** 整合測試以 @ActiveProfiles("dev") 搭配 test 資源覆寫，將 tlbank.idempotency.store 設為 memory。測試覆蓋相同 Idempotency-Key 重送回傳、內容衝突與鎖定語意，不依賴本機 Redis。 — `ApplicationIdempotencyIntegrationTest.java`, `application-dev.yml`
- **Q063:** Optional 的設計：@RequestHeader(value = "Idempotency-Key", required = false) String idempotencyKey，header 缺失時 idempotencyKey 為 null。 — `ApplicationApiController.java`, `IdempotencyService.java`
- **Q016:** IdempotencyEntry 是 record（requestHash、httpStatus、responseBody 三個 component）。Jackson 序列化 record：預設以 component name 為 JSON key，反序列化時需要 @JsonCreator 或 Ja… — `IdempotencyEntry.java`, `RedisIdempotencyStore.java`
- **Q269:** Type erasure 的問題：objectMapper.readValue(entry.responseBody(), ApiResponse.class) 只告訴 Jackson「反序列化為 ApiResponse」，但 `ApiResponse<T>` 的泛型參數 T 在 runtime 因 t… — `IdempotencyService.java`

## JPA

- **Q029:** Mapping 的必要性：domain aggregate Application 不帶任何 JPA 注解（@Entity、@Column 等），才能在不依賴資料庫的情況下做 unit test。ApplicationEntity 帶 JPA 注解，負責對應資料庫 schema。 — `ApplicationRepositoryImpl.java`, `ApplicationEntity.java`
- **Q090:** EnumType.STRING vs ORDINAL：ORDINAL 儲存 enum 的 ordinal 值（0, 1, 2...），STRING 儲存 enum 名稱字串（"INIT"、"OTP_VERIFIED"...）。 — `ApplicationEntity.java`
- **Q091:** 兩種 ID 的理由：id（BIGINT IDENTITY PRIMARY KEY）是資料庫的技術 ID（surrogate key），用於 JPA entity 的 primary key，外鍵關聯（workflow_histories.application_id）等內部 join。 — `ApplicationEntity.java`, `ApplicationJpaRepository.java`
- **Q093:** @Modifying 的作用：標記此查詢為 DML（UPDATE/DELETE），而非 SELECT。Spring Data JPA 的 @Query 預設假設是 SELECT，@Modifying 告訴它這是修改操作，返回受影響的行數（int）而非結果集。 — `OtpJpaRepository.java`, `OtpCleanupScheduler.java`
- **Q095:** 混用的理由：JPQL 查詢（SELECT COUNT(a) FROM ApplicationEntity a WHERE a.createdAt >= :start）：使用 JPA entity 名稱和欄位名稱，與底層 table/column 命名無關，若重命名 table 或欄位（加 @Colu… — `ApplicationJpaRepository.java`
- **Q257:** fromStatus 為 null 的情況分析：Application 的第一次狀態轉換是 verifyOtp() → INIT → OTP_VERIFIED，此時 fromStatus = INIT（不是 null）。 — `WorkflowHistory.java`, `WorkflowHistoryEntity.java`
- **Q259:** @ElementCollection EAGER vs @OneToMany LAZY：UserEntity.roles（`Set<String>`）使用 @ElementCollection(fetch = FetchType.EAGER)：(1) 角色集合很小（通常 1-3 個 role），EAGE… — `UserEntity.java`, `ApplicationEntity.java`
- **Q270:** Domain/Infrastructure 的 roles 型別 gap：Domain User.roles（`Set<Role>`）：Role 是 enum（ROLE_ADMIN、ROLE_REVIEWER、ROLE_USER）。 — `User.java`, `Role.java`
- **Q067:** ResponseEntity 的使用場景：createApplication() 用 ResponseEntity.status(HttpStatus.CREATED).body(...) 是因為需要設置 HTTP 201 狀態碼（資源被建立），也因為 IdempotencyService.exec… — `ApplicationApiController.java`, `IdempotencyService.java`
- **Q092:** LAZY 解決的問題：每次 findByApplicationNo() 加載 ApplicationEntity 時，若 workflowHistories 和 documents 是 EAGER，Hibernate 會自動 JOIN 加載所有子集合（即使 caller 只需要 applicatio… — `ApplicationEntity.java`, `ApplicationRepositoryImpl.java`
- **Q094:** readOnly = true 的 Hibernate 最佳化：(1) Hibernate 跳過 dirty checking（不追蹤 entity 變更，transaction commit 時不掃描所有 entity 是否有修改），節省 CPU； — `ApplicationAppService.java`, `UserDetailsServiceImpl.java`
- **Q258:** @ManyToOne(fetch = LAZY) 的 JPA 問題：WorkflowHistoryEntity 有 @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "application_id") ApplicationEntity ap… — `WorkflowHistoryEntity.java`, `ApplicationRepositoryImpl.java`

## SQL

- **Q095:** 混用的理由：JPQL 查詢（SELECT COUNT(a) FROM ApplicationEntity a WHERE a.createdAt >= :start）：使用 JPA entity 名稱和欄位名稱，與底層 table/column 命名無關，若重命名 table 或欄位（加 @Colu… — `ApplicationJpaRepository.java`
- **Q068:** Native query 的風險：(1) 資料庫方言耦合：GROUP BY status、GROUP BY product_id 在 H2 和 SQL Server 語法差異小，但複雜查詢可能不相容（H2 MODE=MSSQLServer 只是部分相容），導致 H2 dev 環境通過但 SQL Se… — `ApplicationJpaRepository.java`
- **Q112:** H2 console 的安全性：在 SecurityConfig.securityFilterChain()，H2 console 路徑 /h2-console/** 是 permitAll()，不需要登入即可存取。 — `application-dev.yml`, `SecurityConfig.java`
- **Q116:** H2 在 dev/test 環境的特性：H2 是 in-memory 資料庫，資料量極少（seed data 幾十筆），query 速度本身就很快，index 對效能的影響微乎其微。H2 中缺少 index 不影響開發體驗，但會減少 migration script 的維護負擔。 — `V1__create_users.sql`, `V1__create_users.sql`
- **Q019:** ddl-auto: validate：Hibernate 啟動時掃描所有 @Entity 類別，驗證資料庫中的 table 和 column 是否與 entity 對應，但不做任何 DDL 修改（不 create、不 alter、不 drop）。 — `application.yml`, `V1__create_users.sql`
- **Q117:** 45 字元的理由：IPv4 最長表示：255.255.255.255（15 字元）。IPv6 完整表示（full form）：2001:0db8:85a3:0000:0000:8a2e:0370:7334（39 字元）。 — `AuditLog.java`, `V14__reshape_audit_logs_for_sprint9.sql`
- **Q111:** MODE=MSSQLServer 提供的相容性：H2 模擬 SQL Server 的一些語法特性，如 ISNULL() 函數（SQL Server 的 null 替換）、TOP N 語法（H2 預設是 LIMIT N）、@@IDENTITY 函數、DATETIME2 資料型別（部分支援）。 — `application-dev.yml`, `V1__create_users.sql`
- **Q113:** DB_CLOSE_DELAY=-1：H2 in-memory 資料庫預設在最後一個 JDBC connection 關閉時自動刪除資料庫。DB_CLOSE_DELAY=-1 讓資料庫在 JVM 整個生命週期內保持存在，不因為 connection 關閉而消失。 — `application-dev.yml`
- **Q115:** H2 語法（H2 ANSI SQL）：id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY，這是 ISO SQL 標準的 identity column 語法，H2 在 MODE=MSSQLServer 下也支援它。 — `V1__create_users.sql`, `V1__create_users.sql`
- **Q118:** DROP + CREATE 的適用場景：在開發迭代早期（sprint 9 之前的 audit_logs 表結構還在演化），決定完全重設 audit log schema（重命名欄位、移除舊欄位）。 — `V14__reshape_audit_logs_for_sprint9.sql`
- **Q119:** Flyway 位置的設定：application-dev.yml：spring.flyway.locations: classpath:db/migration,classpath:db/dev-seed（H2 migration + seed data）。 — `application-dev.yml`, `application-staging.yml`
- **Q123:** 結果：應用程式啟動時 Flyway 執行 validate（預設行為）：計算修改後的 migration 檔案的 checksum，與 flyway_schema_history 中記錄的原始 checksum 比較，兩者不符，Flyway 拋 FlywayValidationError: Migr… — `V1__create_users.sql`, `application.yml`

## Docker

- **Q183:** 兩階段 build 的好處：第一階段（builder）：FROM eclipse-temurin:17-jdk AS builder，包含完整 JDK（javac、maven 工具）+ 所有 build artifacts，編譯和打包 JAR。 — `Dockerfile`
- **Q184:** 非 root user 的安全理由：若 container 以 root（UID 0）執行：(1) 容器逃逸（container escape）：若攻擊者透過應用程式漏洞（如 RCE）在 container 內執行任意命令，以 root 執行的攻擊者有最大的容器內權限，更容易利用 kernel 漏洞… — `Dockerfile`
- **Q186:** 獨立 db-init container 的設計理由：sqlserver container：Microsoft SQL Server image 的 entrypoint 是 SQL Server 服務本身（sqlservr），不能同時執行初始化 SQL script（啟動時只做 SQL Serv… — `docker-compose.yml`, `01-init-database.sql`
- **Q187:** SELECT 1 作為 healthcheck 的理由：(1) 極輕量：執行一條不掃描任何 table 的常數 query，幾乎沒有 CPU 和 I/O 消耗，不影響資料庫效能； — `docker-compose.yml`
- **Q188:** 沒有 service_healthy 的問題：若 depends_on.sqlserver 只有 condition: service_started（預設），Docker Compose 在 sqlserver container 的 process 啟動後立即啟動 app container（不… — `docker-compose.yml`
- **Q190:** Named volume vs bind mount：Named volume（sqlserver-data:/var/opt/mssql）：Docker 自行管理 volume 位置（通常在 /var/lib/docker/volumes/），container-to-host 隔離，跨 cont… — `docker-compose.yml`
- **Q285:** -Pstaging Maven profile 的作用：在 pom.xml 中通常有 `<profiles>` section，staging profile 可以：(1) 設定 spring.profiles.active=staging（讓 Spring Boot 在 Docker containe… — `Dockerfile`, `pom.xml`
- **Q185:** JVM flags 的作用：-XX:+UseContainerSupport（Java 10+ 預設 enabled，Java 8u191+ 加入）：讓 JVM 尊重 container 的 cgroup 記憶體限制（--memory flag），而不是讀取 host 的總記憶體。 — `Dockerfile`
- **Q189:** 動態生成 docker-compose.yml 的理由：CI pipeline 在 self-hosted macOS runner 上執行，deploy 時需要將 GHCR image（ghcr.io/.../tlbank-backend:latest）注入 docker-compose.yml … — `ci.yml`, `docker-compose.yml`

## Testing

- **Q155:** 測試策略的啟示：ExcelReportGeneratorTest 不只驗證「方法不拋 exception」，也驗證 output 的結構（workbook.getNumberOfSheets() == 2、sheet name 是 "Summary" 和 "By Product"）和內容（所有 Ap… — `ExcelReportGeneratorTest.java`, `ExcelReportGenerator.java`
- **Q149:** 問題背景：AuditLogWriter.saveAsync() 是 @Async 方法，在默認的 async thread pool 中執行。測試中，主 test thread 呼叫 auditableTestService.success(applicationId) 後，audit log 的寫… — `AuditAspectTest.java`, `AuditLogWriter.java`
- **Q273:** Exhaustive parameterized test 的設計：ReviewCaseTest 需要驗證所有合法的 transition 和所有非法的 transition： java@ParameterizedTest @MethodSource("legalTransitions") void… — `ReviewCase.java`, `ApplicationStatusTest.java`
- **Q011:** 整合測試以 @ActiveProfiles("dev") 搭配 test 資源覆寫，將 tlbank.idempotency.store 設為 memory。測試覆蓋相同 Idempotency-Key 重送回傳、內容衝突與鎖定語意，不依賴本機 Redis。 — `ApplicationIdempotencyIntegrationTest.java`, `application-dev.yml`
- **Q154:** 分離測試類別的理由：SystemParameterServiceTest 測試業務邏輯（getValue() 找到 / 找不到 parameter → 拋 exception；updateParameter() 更新 DB）。 — `SystemParameterServiceTest.java`, `SystemParameterServiceCacheTest.java`
- **Q150:** 直接修改 DB 的必要性：OtpAppService.sendOtp() 使用 SecureRandom 生成隨機 OTP，測試無法預知這個 random code。為了測試 OTP verify 流程，測試需要知道 OTP 值。 — `ApplicationFlowIntegrationTest.java`, `OtpAppService.java`
- **Q151:** @ExtendWith(MockitoExtension.class) 的意義：只啟動 Mockito 的 JUnit 5 extension，不啟動 Spring context，所有依賴都是 @Mock 或 @InjectMocks，測試以毫秒級速度執行。 — `ApplicationAppServiceTest.java`, `ApplicationAppService.java`
- **Q152:** ArgumentCaptor vs 直接比較的場景：當方法傳入的參數是一個新建立的複雜物件（如 OtpRecord.builder()...build()），這個物件在呼叫端動態建構，不能在測試中預先建立完全相同的實例（OtpRecord 沒有覆寫 equals()），verify(mock, ti… — `OtpAppServiceTest.java`
- **Q153:** @TempDir 的機制：JUnit 5 在每個測試方法（或 test class，取決於 annotation 位置）執行前，自動建立一個臨時目錄（OS temp 資料夾中），測試執行完成後自動刪除，無論測試 pass 或 fail。 — `LocalDocumentStorageServiceTest.java`
- **Q156:** @Sql vs JdbcTemplate 的取捨：@Sql(statements = {"INSERT INTO..."}) 是 Spring Test 的 annotation，在 test method 執行前（或後）自動執行 SQL 語句，完成後可自動 rollback（若 @Transact… — `ReviewApiControllerTest.java`, `ApplicationFlowIntegrationTest.java`
- **Q296:** 測試充分性的評估框架：133 個 test methods 覆蓋了 domain unit tests（state machine exhaustive、value object validation）、application service unit tests（每個 use case 的 hap… — `16-testing-strategy.md`, `pom.xml`
- **Q283:** Smoke test 的作用：TlbankLendingApplicationTests 通常只有：@SpringBootTest + @ActiveProfiles("dev") 在 class level，測試 method 可能是空的（@Test void contextLoads() {}）… — `TlbankLendingApplicationTests.java`

## CI/CD

- **Q030:** Domain service（WorkflowDomainService）：包含跨多個 aggregate 或不適合放在單一 aggregate 上的業務規則；不持有狀態； — `WorkflowDomainService.java`, `ApplicationAppService.java`
- **Q034:** Application aggregate 包含 `List<WorkflowHistory>` 作為子值物件列表（記錄每次狀態轉換）。對應的 JPA 結構：ApplicationEntity 包含 `List<WorkflowHistoryEntity>`（一對多關係），WorkflowHistoryEn… — `ApplicationRepositoryImpl.java`, `WorkflowHistoryEntity.java`
- **Q057:** 不加 @Builder.Default 的後果：Lombok @Builder 生成的 builder 預設對 List 欄位不初始化（值為 null），除非 field 有初始值且加了 @Builder.Default。 — `Application.java`
- **Q058:** 由 aggregate 自身生成：transitionTo() 是 private 方法，在驗證通過後自動建立 WorkflowHistory（fromStatus、toStatus、operator、remark、operatedAt）並加入 workflowHistories。 — `Application.java`, `WorkflowHistory.java`
- **Q071:** 區別：BusinessException：業務邏輯錯誤，帶有 ErrorCode enum（如 INCOMPLETE_DOCUMENTS、OTP_EXPIRED）和描述訊息，映射到多種 HTTP status（400、404、409 等）由 switch expression 決定。 — `BusinessException.java`, `WorkflowException.java`
- **Q257:** fromStatus 為 null 的情況分析：Application 的第一次狀態轉換是 verifyOtp() → INIT → OTP_VERIFIED，此時 fromStatus = INIT（不是 null）。 — `WorkflowHistory.java`, `WorkflowHistoryEntity.java`
- **Q300:** GlobalExceptionHandler 位於 presentation.api.advice，將例外轉成統一 ApiResponse 與 HTTP 狀態。工作流非法轉換走 WorkflowException；Bean Validation 失敗則回傳欄位錯誤資訊。 — `GlobalExceptionHandler.java`, `ErrorCode.java`
- **Q092:** LAZY 解決的問題：每次 findByApplicationNo() 加載 ApplicationEntity 時，若 workflowHistories 和 documents 是 EAGER，Hibernate 會自動 JOIN 加載所有子集合（即使 caller 只需要 applicatio… — `ApplicationEntity.java`, `ApplicationRepositoryImpl.java`
- **Q258:** @ManyToOne(fetch = LAZY) 的 JPA 問題：WorkflowHistoryEntity 有 @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "application_id") ApplicationEntity ap… — `WorkflowHistoryEntity.java`, `ApplicationRepositoryImpl.java`
- **Q040:** ArchUnit：Java 函式庫，可以寫成 JUnit 測試驗證 class import 依賴關係。例如：noClasses().that().resideInPackage("..domain..").should().dependOnClassesThat().resideInPackage… — `pom.xml`, `0001-use-clean-architecture.md`

## Terraform

- **Q008:** 最低改動清單：Terraform 需要加入 AWS（或 GCP/Azure）provider，建立 ECS/EC2、RDS、ElastiCache，替換 local provider 的 hashicorp/local。 — `main.tf`, `application-prod.yml`
- **Q199:** Local provider 的教學目的：hashicorp/local provider 讓 Terraform 管理本地文件（local_file resource 生成 generated-staging-env.md）。 — `main.tf`, `variables.tf`
- **Q198:** terraform fmt -check 在 CI 的意義：terraform fmt 是 Terraform 的標準格式化工具（類似 gofmt 或 prettier），對齊縮排、括號間距、argument 順序等。-check flag：不修改文件，只驗證文件是否已格式化； — `terraform.yml`, `main.tf`
- **Q295:** infra/local 以 local provider 產生本機文件產物（例如環境說明），CI 的 terraform.yml 做 fmt/validate/plan。不建立 AWS/Azure/GCP 資源，也不能描述成雲端基礎設施或正式環境佈建。 — `main.tf`, `terraform.yml`
- **Q241:** 學習策略（以 Terraform 為例）：這個 project 的 Terraform 學習是典型的「limited-scope, documented progress」方式：(1) 目標先行：先定義「我需要知道 state/plan/apply workflow，以便未來能說明 IaC 實踐」—… — `main.tf`, `0005-use-terraform-local.md`

## Business Flow

- **Q042:** Application 作為 aggregate root 的 invariant 責任：(1) 狀態轉換合法性：transitionTo() 驗證 ALLOWED_TRANSITIONS，非法轉換拋 WorkflowException，aggregate 自身保護狀態一致性，不依賴外部 servi… — `Application.java`, `ApplicationStatus.java`
- **Q251:** ThreadLocalRandom vs SecureRandom 的選擇：ApplicationId.generate() 使用 ThreadLocalRandom.current().nextInt(1000, 10000) 生成 4 位隨機後綴（1000–9999），加上 14 位時間戳（yy… — `ApplicationId.java`, `OtpAppService.java`
- **Q254:** Value object 組合的優點：Applicant 的 mobile 欄位是 MobileNumber 型別（而非 String），email 欄位是 Email 型別（而非 String）。 — `Applicant.java`, `MobileNumber.java`
- **Q256:** ID 生成位置的分析：在 ApplicationAppService.createApplication() 中：ApplicationId id = ApplicationId.generate()（或 ApplicationId.generate() 直接作為 Application.build… — `ApplicationAppService.java`, `ApplicationId.java`
- **Q047:** Domain event 是 plain Java（如 record 或 simple class），只帶業務資料（applicationId、timestamp 等），沒有 Spring 或 Jackson 注解。 — `ApplicationApprovedEvent.java`, `ApplicationSubmittedEvent.java`
- **Q271:** updateLastLoginAt() 的存在理由：LoginSuccessHandler.onAuthenticationSuccess() 呼叫 userJpaRepository.findByUsername(username) 取 UserEntity，然後直接呼叫 user.updateL… — `User.java`, `LoginSuccessHandler.java`
- **Q051:** 合法轉換（ALLOWED_TRANSITIONS map）：INIT → OTP_VERIFIED 或 CANCELLED；OTP_VERIFIED → DOCUMENT_UPLOADED 或 CANCELLED；DOCUMENT_UPLOADED → SUBMITTED 或 CANCELLED； — `ApplicationStatus.java`, `Application.java`
- **Q052:** canTransitionTo(null) 的防禦性檢查：方法開頭有 if (next == null) { return false; }，避免後續 ALLOWED_TRANSITIONS.get(this) 返回的 Set 呼叫 contains(null) 拋 NullPointerExcep… — `ApplicationStatus.java`
- **Q054:** 順序關鍵性：validateRequiredDocuments() 先執行，若拋 BusinessException(INCOMPLETE_DOCUMENTS)，狀態轉換的 transitionTo(SUBMITTED, ...) 就不會執行，Application 保持在 DOCUMENT_UPL… — `Application.java`
- **Q056:** 無 Spring context 的實現：ApplicationTest 是純 JUnit 5（@Test 注解即可），沒有 @SpringBootTest、@ExtendWith(SpringExtension.class) 或任何 Spring test 注解。 — `ApplicationTest.java`, `Application.java`
- **Q057:** 不加 @Builder.Default 的後果：Lombok @Builder 生成的 builder 預設對 List 欄位不初始化（值為 null），除非 field 有初始值且加了 @Builder.Default。 — `Application.java`
- **Q058:** 由 aggregate 自身生成：transitionTo() 是 private 方法，在驗證通過後自動建立 WorkflowHistory（fromStatus、toStatus、operator、remark、operatedAt）並加入 workflowHistories。 — `Application.java`, `WorkflowHistory.java`
- **Q266:** Single-value enum 的設計意圖：OtpPurpose 目前只有 APPLICATION_VERIFICATION 一個值，但定義為 enum 而非 boolean flag 或 String constant。 — `OtpPurpose.java`, `OtpRecord.java`
- **Q053:** 業務場景：申請人先上傳一份文件（轉換到 DOCUMENT_UPLOADED），發現格式不對，刪除重傳另一份同類型文件，或補充上傳額外文件。目前的 uploadDocuments() 邏輯：if (status == OTP_VERIFIED) 才轉換狀態，if (status == DOCUMENT… — `Application.java`, `LocalDocumentStorageService.java`
- **Q055:** 設計意圖：ALLOWED_TRANSITIONS 表示「從某個狀態可以合法轉換到哪些狀態」，這是狀態機的通用規則。但 cancel() 是個特殊的業務操作，語意是「申請人主動放棄」，而非純粹的狀態機轉換。 — `Application.java`, `ApplicationStatus.java`
- **Q059:** ALLOWED_TRANSITIONS.get(APPROVED) 返回 null（key 不存在）。canTransitionTo() 的邏輯：`Set<ApplicationStatus> allowed = ALLOWED_TRANSITIONS.get(this)` → 當 this == AP… — `ApplicationStatus.java`

## Must-not-overclaim

- **Q268:** Bean 啟動邏輯分析：RedisIdempotencyStore：@ConditionalOnProperty(name = "tlbank.idempotency.store", havingValue = "redis")，沒有 matchIfMissing = true…
- **Q132:** finally 的必要性：lock 在 try 區塊取得（idempotencyStore.tryAcquireLock(lockKey, LOCK_TTL_SECONDS)），業務邏輯（action.get()）在 try 內執行，finally 確保 idempotencyS…
- **Q005:** Staging 環境是本機 Mac（self-hosted runner），不是雲端常駐服務，不能由 Push 自動觸發，否則每次 push 到 main 都會嘗試部署到可能不在線上的 Mac。
- **Q191:** 5 job 依賴圖：build-test → code-quality（needs: build-test）→ 無後繼（僅止於此）；build-test → dependency-scan（needs: build-test）→ 無後繼；
- **Q295:** infra/local 以 local provider 產生本機文件產物（例如環境說明），CI 的 terraform.yml 做 fmt/validate/plan。不建立 AWS/Azure/GCP 資源，也不能描述成雲端基礎設施或正式環境佈建。
- **Q087:** 選擇 session 的理由（ADR 0006）：Staff portal 是 Thymeleaf server-rendered（browser form login），session 提供即時登出和 maximumSessions(1) 控制，比 JWT 的 token in…
- **Q161:** matchIfMissing = true：若 application.yml 或任何 active profile 的配置中沒有 tlbank.notification.mode 屬性，則 @ConditionalOnProperty 的條件視為 matched（即條件成立）…
- **Q086:** 產品與系統參數快取走 InMemoryCacheStore；CachedCardProductRepository 以 @Primary 裝飾 CardProductRepository。CacheManagementService 提供管理端驅逐/刷新。
