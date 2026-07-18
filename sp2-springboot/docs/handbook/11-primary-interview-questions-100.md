# Primary Interview Questions (100)

Highest-priority subset of the validated 300-question source map.
Original numbering retained. All content derived from `09-interview-source-map-300.md`.

---

## Q005 (from 300)

### Question

The repository has no automatic promotion to staging — deployment requires workflow_dispatch. Why is that design intentional?

### Answer summary

Staging 環境是本機 Mac（self-hosted runner），不是雲端常駐服務，不能由 Push 自動觸發，否則每次 push 到 main 都會嘗試部署到可能不在線上的 Mac。workflow_dispatch 讓部署時機完全由人工決定，避免自動觸發打斷 local Docker Compose 運行中的狀態。另外，staging 部署步驟需要 MSSQL_SA_PASSWORD、GHCR_USERNAME、GHCR_TOKEN 等 secret，手動觸發可以確保有人知道環境就緒再部署。正確描述：CI 是自動的（push/PR 觸發），CD 是手動的（workflow_dispatch）。

### Related files

- `.github/workflows/ci.yml`

### Related classes

- Documentation-level question

### Related technologies

- GitHub Actions

---

## Q017 (from 300)

### Question

Spring Boot 3.4.2 is pinned in pom.xml. What changed from Spring Boot 2.x to 3.x that is most relevant to this project?

### Answer summary

Spring Boot 3.x 最相關的變化：Jakarta EE 10 命名空間替換（javax.* → jakarta.*），pom.xml 中的 import 全部是 jakarta.servlet.*、jakarta.validation.*。Spring Security 6.x（隨 Boot 3 升級）的 HttpSecurity 配置從 method chaining 改為 lambda DSL（csrf(csrf -> ...)、sessionManagement(session -> ...)）。@SpringBootTest 預設行為改變（WebEnvironment.DEFINED_PORT vs MOCK）。Actuator 的 health endpoint 格式調整。SpringDoc OpenAPI 也從 2.x 升到 3.x。

### Related files

- `sp2-springboot/pom.xml`
- `sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java`

### Related classes

- `SecurityConfig.securityFilterChain()`

### Related technologies

- Spring Boot

---

## Q024 (from 300)

### Question

The project uses @Async for audit log writing. What configuration is needed for @Async to work, and where is it in this project?

### Answer summary

@Async 生效需要：@EnableAsync（在某個 @Configuration 類別上，此 project 中在 AsyncConfig 或 entry point 上），且呼叫者必須透過 Spring proxy 呼叫（同一個 class 內的 self-invocation 會繞過 proxy，@Async 無效）。AuditLogWriter.saveAsync() 被 AuditAspect 呼叫，AuditAspect 是獨立 bean，透過 Spring proxy 注入 AuditLogWriter，所以 @Async 正常生效。AsyncConfig 通常也定義自訂 Executor bean（ThreadPoolTaskExecutor），設定核心執行緒數、佇列大小、thread name prefix，方便 log 追蹤與資源控管。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/common/config/AsyncConfig.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditLogWriter.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditAspect.java`

### Related classes

- `AuditLogWriter.saveAsync()`
- `AsyncConfig`

### Related technologies

- Spring Boot
- Logging/MDC

---

## Q025 (from 300)

### Question

The project configures SessionCreationPolicy.IF_REQUIRED. What does this mean, and when would you change it to STATELESS?

### Answer summary

IF_REQUIRED：Spring Security 僅在需要時建立 session（使用者認證成功後、或需要存儲 SecurityContext 時）。這是 Thymeleaf server-rendered portal 的正確選擇，因為 browser 需要維持登入狀態。STATELESS：每個請求都必須攜帶認證憑據（如 Bearer token），Spring Security 不建立也不讀取 session，適合 pure REST API + JWT 場景。何時切換：若日後為行動端或第三方服務增加 JWT filter chain（/api/v2/**），該 filter chain 設定 STATELESS，而 Thymeleaf 的 filter chain 繼續使用 IF_REQUIRED，兩個 filter chain 並存。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java`
- `sp2-springboot/docs/decisions/0006-session-over-jwt.md`

### Related classes

- `SecurityConfig.securityFilterChain()`

### Related technologies

- Spring Boot

---

## Q027 (from 300)

### Question

The project follows Clean Architecture with a strict dependency rule. Which direction do dependencies flow, and how is this enforced?

### Answer summary

依賴方向：presentation → application → domain ← infrastructure。Domain 不依賴任何外層：Application（aggregate）只 import JDK 和 Lombok，沒有 @Entity、沒有 Spring 注解。Infrastructure 依賴 domain port（ApplicationRepository、OtpRepository 等介面），實作這些 port 而非被 domain 所知。目前的執行方式：package 命名約定 + code review，沒有自動化工具（如 ArchUnit）在 CI 強制驗證。ADR 0001 承認有少數 leak（@Service 出現在接近 domain 的地方）。未來改善方向：加入 ArchUnit 規則，讓 CI 能自動拒絕違反依賴方向的 PR。

### Related files

- `sp2-springboot/docs/decisions/0001-use-clean-architecture.md`
- `sp2-springboot/docs/design/02-architecture-design.md`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationRepositoryImpl.java`

### Related classes

- Documentation-level question

### Related technologies

- Hexagonal

---

## Q028 (from 300)

### Question

What is the "Ports and Adapters" pattern, and where does TLBank implement it?

### Answer summary

Ports and Adapters（Hexagonal Architecture）：Port 是 domain 定義的介面（interface），Adapter 是 infrastructure 實作該介面的具體類別。Port 讓 domain 依賴抽象而非具體實作，外部技術可以替換而不影響 domain 邏輯。TLBank 的實例：Port = ApplicationRepository（domain package 中的介面），Adapter = ApplicationRepositoryImpl（infrastructure package，實作 Port，內部使用 ApplicationJpaRepository）。同樣的 pattern：OtpRepository / OtpRepositoryImpl、IdempotencyStore / RedisIdempotencyStore / InMemoryIdempotencyStore、EmailSender / MockEmailSender、SmsSender / MockSmsSender、DocumentStorageService / LocalDocumentStorageService。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/repository/ApplicationRepository.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationRepositoryImpl.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/idempotency/IdempotencyStore.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/idempotency/RedisIdempotencyStore.java`

### Related classes

- Documentation-level question

### Related technologies

- Hexagonal

---

## Q029 (from 300)

### Question

ApplicationRepositoryImpl maps between ApplicationEntity (JPA) and Application (domain aggregate). Why is this mapping necessary, and what is the cost?

### Answer summary

Mapping 的必要性：domain aggregate Application 不帶任何 JPA 注解（@Entity、@Column 等），才能在不依賴資料庫的情況下做 unit test。ApplicationEntity 帶 JPA 注解，負責對應資料庫 schema。若兩個合一（在 aggregate 上直接加 @Entity），domain test 就需要啟動 Spring Data JPA 整個 stack。Mapping 的成本：每次 save() 和 findById() 都需要手動或工具協助的 entity ↔ domain 轉換，程式碼量增加（toDomain()、toEntity()、applyToEntity() 方法）。MapStruct 在 pom.xml 中有 classpath 但目前未使用，手動 mapping 是目前的實作。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationRepositoryImpl.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationEntity.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java`

### Related classes

- `ApplicationRepositoryImpl.toDomain() (private)`
- `ApplicationRepositoryImpl.toEntity() (private)`
- `ApplicationRepositoryImpl.applyToEntity() (private)`

### Related technologies

- JPA/Hibernate
- DDD
- Hexagonal

---

## Q030 (from 300)

### Question

The domain layer has a service package containing WorkflowDomainService. What distinguishes a domain service from an application service?

### Answer summary

Domain service（WorkflowDomainService）：包含跨多個 aggregate 或不適合放在單一 aggregate 上的業務規則；不持有狀態；屬於 domain layer，不依賴 Spring（理論上），但 WorkflowDomainService 標有 @Service（ADR 0001 中承認的 Spring leak）。Application service（ApplicationAppService、ReviewAppService 等）：協調 use case 流程，呼叫 domain aggregate 方法，與 repository port 互動，處理 transaction，轉換 DTO；是 port 的呼叫端，不含業務規則本身。主要區別：domain service 描述「業務規則是什麼」，application service 描述「how to execute a use case」。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/domain/service/WorkflowDomainService.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java`

### Related classes

- `WorkflowDomainService`
- `ApplicationAppService`

### Related technologies

- GitHub Actions
- Hexagonal

---

## Q031 (from 300)

### Question

How does the presentation layer prevent business logic from leaking into controllers?

### Answer summary

防止業務邏輯進入 controller 的機制：Controller 只做三件事：@Valid 觸發 JSR-303 validation（欄位格式）、委派給一個 application service 方法、將結果包進 ApiResponse 回傳。業務規則（狀態轉換、OTP 驗證、文件完整性）全部在 domain aggregate 和 application service 中執行。Exception 翻譯在 GlobalExceptionHandler 集中處理，controller 不需要 try-catch。只有薄薄一層 DTO mapping（controller 接收 CreateApplicationRequest，轉成 application service 理解的參數）。任何在 controller 中出現的 if 判斷業務條件，都是 red flag。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/v1/ApplicationApiController.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/advice/GlobalExceptionHandler.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java`

### Related classes

- `GlobalExceptionHandler`

### Related technologies

- Hexagonal

---

## Q032 (from 300)

### Question

What are the known architectural impurities (leaks) in this codebase, and how would you fix them?

### Answer summary

ADR 0001 明確承認的 impurity：@Service annotation 出現在接近 domain layer 的地方（WorkflowDomainService），使 domain 有 Spring 依賴。MapStruct 在 classpath 但未用，手動 mapping 有重複程式碼的風險。部分 Spring 類型（如 Pageable）可能出現在 domain port 介面（ApplicationRepository），使 domain 依賴 Spring Data 的分頁 abstraction。修復方式：用純 Java 定義 WorkflowDomainService（不加 @Service），改由 @Configuration 的 @Bean 方法建立實例並注入；將 Pageable 從 domain port 移除，改用自訂的 SearchCriteria 值物件；若 mapping 增加，引入 MapStruct。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/domain/service/WorkflowDomainService.java`
- `sp2-springboot/docs/decisions/0001-use-clean-architecture.md`

### Related classes

- Documentation-level question

### Related technologies

- Hexagonal

---

## Q033 (from 300)

### Question

How do the common and security packages fit into the Clean Architecture layering?

### Answer summary

common：跨切關注點（cross-cutting concerns），不屬於任何業務 layer，不依賴業務 layer（common 可被所有 layer 引用）。包含：ApiResponse（HTTP 回應信封）、ErrorCode、BusinessException、WorkflowException、AuditAspect、AsyncConfig、CommonConfig、工具類別（MaskingUtil、DateUtil）。security：同為跨切關注點，依賴 domain layer（需要知道 Role enum、User aggregate），也依賴 infrastructure layer（UserRepositoryImpl 提供 UserDetails 給 Spring Security）。security 不是 hexagonal 的標準 layer，是 Spring Security 整合的特殊位置。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/common/config/SchedulingConfig.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/common/exception/ErrorCode.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java`
- `sp2-springboot/docs/design/02-architecture-design.md`

### Related classes

- `ApiResponse`
- `ErrorCode`
- `SecurityConfig`

### Related technologies

- Spring Security
- Hexagonal

---

## Q034 (from 300)

### Question

How does TLBank handle the mapping between domain aggregates and JPA entities for the WorkflowHistory embedded in Application?

### Answer summary

Application aggregate 包含 List<WorkflowHistory> 作為子值物件列表（記錄每次狀態轉換）。對應的 JPA 結構：ApplicationEntity 包含 List<WorkflowHistoryEntity>（一對多關係），WorkflowHistoryEntity 有 @ManyToOne 關聯回 ApplicationEntity。ApplicationRepositoryImpl.toDomain() 將 WorkflowHistoryEntity list 轉換為 WorkflowHistory domain 物件列表；toEntity() 反向轉換。每次 Application.save() 時，若有新的 WorkflowHistory（狀態轉換後 transitionTo() 產生的），applyToEntity() 需要正確同步新增的 history 到 entity，避免 JPA orphan removal 問題。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationRepositoryImpl.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/WorkflowHistoryEntity.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/WorkflowHistory.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java`

### Related classes

- `ApplicationRepositoryImpl.toDomain() (private)`
- `ApplicationRepositoryImpl.applyToEntity() (private)`
- `WorkflowHistoryEntity`

### Related technologies

- JPA/Hibernate
- GitHub Actions
- DDD
- Hexagonal

---

## Q035 (from 300)

### Question

The application layer has both a top-level idempotency sub-package and a dto sub-package. What does each contain, and why are they in application rather than infrastructure?

### Answer summary

application.idempotency：IdempotencyService（use case 協調器），負責 idempotency key 解析、SHA-256 hash 計算、lock 取得、cache 命中判斷、response 重建。依賴 IdempotencyStore port（在 infrastructure 中有 Redis 和 memory 兩種實作）。application.dto：request/response DTO 類別（CreateApplicationRequest、LoginResponse 等），定義 API 邊界的資料形狀，與 domain aggregate 解耦（controller 傳 DTO 給 application service，service 拆解後操作 domain）。兩者都在 application 而非 infrastructure，因為它們是 use case 層的關注點：協調業務流程（idempotency 是 use case 的 orchestration concern，DTO 是 use case 的 I/O contract），不是技術實作細節。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/application/idempotency/IdempotencyService.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/application/dto/request/CreateApplicationRequest.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/application/dto/response/LoginResponse.java`

### Related classes

- `IdempotencyService`
- `CreateApplicationRequest`

### Related technologies

- Redis/Idempotency
- Hexagonal

---

## Q037 (from 300)

### Question

If you had to add a new feature — say, credit limit assignment after approval — where would each piece of code live in the layered architecture?

### Answer summary

Domain layer：Application aggregate 新增 assignCreditLimit(CreditLimit limit) 方法；CreditLimit value object（record with validation）；ApplicationStatus.APPROVED 可能需要額外狀態或 flag。Application layer：ApplicationAppService 新增 assignCreditLimit(ApplicationId, CreditLimitRequest) use case 方法；DTO CreditLimitRequest。Infrastructure layer：若需要外部信評 API，新增 CreditScoringService port 和對應 adapter；若只是更新 DB 欄位，ApplicationRepositoryImpl 已有 save()，不需改動 port。Presentation layer：ApplicationApiController 新增 endpoint（PUT /api/v1/applications/{id}/credit-limit）；@Auditable 加在 service 方法。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/v1/ApplicationApiController.java`

### Related classes

- Documentation-level question

### Related technologies

- Hexagonal

---

## Q038 (from 300)

### Question

The NotificationEventHandler and ReviewEventHandler are described as "natural seams" for future broker integration. Why?

### Answer summary

目前的 event 機制：domain events（ApplicationApprovedEvent、ApplicationSubmittedEvent 等）透過 Spring ApplicationEventPublisher 發布，event handler 在同一個 JVM process 內的 @EventListener 處理，完全 in-process，沒有網路跳轉。為何是 broker 的自然接縫：event handler 已經是 domain logic（application service）與 side effect（通知、review 建立）的分離點。若要接 Kafka/RabbitMQ，只需將 @EventListener 替換為向 broker publish message 的 adapter，domain aggregate 發 event 的方式不變。NotificationEventHandler 負責 SMS/Email mock，ReviewEventHandler 負責建立 ReviewCase，兩者職責清晰，是低耦合的接縫。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/event/NotificationEventHandler.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/event/ReviewEventHandler.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/event/ApplicationApprovedEvent.java`
- `sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md`

### Related classes

- Documentation-level question

### Related technologies

- Hexagonal

---

## Q039 (from 300)

### Question

How is CachedCardProductRepository structured, and what does it demonstrate about the Ports and Adapters pattern?

### Answer summary

CachedCardProductRepository 是 CardProductRepository port 的 decorator 實作：包裝另一個 CardProductRepository（JPA 實作），在外層加入 cache 邏輯（先查 CacheStore，cache miss 才查 JPA，查到後存入 cache）。這展示 Ports and Adapters 的 composability：port 介面允許多個 adapter，adapter 可以互相裝飾而不改變 domain 的呼叫方式。Domain 和 application service 只知道 CardProductRepository 介面，不知道底層是 JPA、cache、還是兩者組合。Spring @Primary 或 @Qualifier 決定 injection 時用哪個實作。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/cache/CachedCardProductRepository.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/product/repository/CardProductRepository.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/product/CardProductRepositoryImpl.java`

### Related classes

- Documentation-level question

### Related technologies

- Hexagonal

---

## Q040 (from 300)

### Question

What would you need to add to enforce the dependency rule automatically in CI?

### Answer summary

ArchUnit：Java 函式庫，可以寫成 JUnit 測試驗證 class import 依賴關係。例如：noClasses().that().resideInPackage("..domain..").should().dependOnClassesThat().resideInPackage("..infrastructure..") 驗證 domain 不依賴 infrastructure。加入 pom.xml dependency（com.tngtech.archunit:archunit-junit5），在 test class 中撰寫規則，mvn verify 時自動執行。好處：CI 會在任何違反依賴方向的 PR 合入時自動 fail，不需要 code review 人工發現。成本：需要了解 ArchUnit DSL；初期設定需要處理已知 exception（如 WorkflowDomainService 的 @Service leak），可用 ignoreDependency() 記錄已知例外。

### Related files

- `sp2-springboot/pom.xml`
- `sp2-springboot/docs/decisions/0001-use-clean-architecture.md`

### Related classes

- Documentation-level question

### Related technologies

- Hexagonal

---

## Q041 (from 300)

### Question

TLBank uses "DDD-lite." What does that mean, and which full DDD tactical patterns are missing?

### Answer summary

DDD-lite：採用 DDD 的部分 tactical pattern，不做完整的 DDD 實作。此 project 有的：aggregate（Application、ReviewCase）、value object（MobileNumber、Email、ApplicationId）、domain event（ApplicationApprovedEvent 等）、repository port（ApplicationRepository）、domain service（WorkflowDomainService）。此 project 缺少或簡化的：完整的 AggregateRoot base class（沒有統一的 domain event collection 機制，event 由 application service 或 aggregate method 觸發）；Entity 與 Aggregate Root 的嚴格區分（ReviewCase 是否真的是 aggregate root 還是 entity 可以討論）；Bounded Context 的明確 mapping 和 Context Map；Anti-Corruption Layer（ACL）。

### Related files

- `sp2-springboot/docs/decisions/0002-use-ddd.md`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/review/ReviewCase.java`

### Related classes

- Documentation-level question

### Related technologies

- DDD

---

## Q042 (from 300)

### Question

Application is the aggregate root. What responsibility does it have for maintaining invariants?

### Answer summary

Application 作為 aggregate root 的 invariant 責任：(1) 狀態轉換合法性：transitionTo() 驗證 ALLOWED_TRANSITIONS，非法轉換拋 WorkflowException，aggregate 自身保護狀態一致性，不依賴外部 service 來判斷合法性。(2) 文件完整性：submit() 呼叫 validateRequiredDocuments()，確保所有 DocumentType（NATIONAL_ID、INCOME_PROOF、RESIDENCE_PROOF）都已上傳，否則拋 BusinessException(INCOMPLETE_DOCUMENTS)。(3) 取消限制：cancel() 只在 CANCELLABLE_STATUSES（INIT、OTP_VERIFIED、DOCUMENT_UPLOADED）允許，已 SUBMITTED 的申請不可取消。Invariant 的校驗在 aggregate 內部，application service 呼叫 aggregate 方法，不繞過 invariant 直接操作 entity。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationStatus.java`

### Related classes

- `Application.submit()`
- `Application.cancel()`
- `Application.transitionTo() (private)`
- `Application.validateRequiredDocuments() (private)`

### Related technologies

- DDD

---

## Q043 (from 300)

### Question

What is the difference between ApplicationId, CardProductId, and ReviewCaseId? Why are they not just String or UUID?

### Answer summary

ApplicationId、CardProductId、ReviewCaseId 都是 value object（record），封裝 ID 的格式和驗證邏輯。優點：型別安全，findById(ApplicationId id) 和 findById(ReviewCaseId id) 是不同的型別，不會意外互換；自文件化，方法簽名明確說明預期哪種 ID；集中 validation（ApplicationId 若有格式規則，在 compact constructor 驗證）。若只用 String：findById(String applicationId) 與 findById(String reviewCaseId) 在呼叫端難以區分，compiler 不會報錯，但 runtime 可能傳錯。這是 DDD 中「Primitive Obsession」anti-pattern 的解決方案。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationId.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/CardProductId.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/review/ReviewCaseId.java`

### Related classes

- Documentation-level question

### Related technologies

- DDD

---

## Q045 (from 300)

### Question

What are the five domain aggregates or main entity clusters in TLBank, and what are their boundaries?

### Answer summary

主要 aggregate / entity cluster：(1) Application（申請人信用卡申請，包含 Applicant、WorkflowHistory、DocumentInfo 作為 child entity / value object）；(2) OtpRecord（OTP 驗證記錄，獨立生命週期，有 OtpStatus、purpose、expiry、retry count）；(3) ReviewCase（審核工作，關聯 Application，有 ReviewRemark 子集合）；(4) CardProduct（卡片產品資訊，包含 ProductFeature 子集合，唯讀為主）；(5) User（系統用戶，有 Role，由 ADMIN 管理）；SystemParameter（系統參數，簡單的 key-value，可能是 entity 而非完整 aggregate）。Aggregate 邊界：跨 aggregate 的存取透過 ID 引用（Application 用 CardProductId 而非直接持有 CardProduct），不直接導航。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/otp/OtpRecord.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/review/ReviewCase.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/product/CardProduct.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/user/User.java`

### Related classes

- Documentation-level question

### Related technologies

- JPA/Hibernate
- DDD

---

## Q047 (from 300)

### Question

Domain events in TLBank are plain Java objects with no framework annotations. What are the implications of this design?

### Answer summary

Domain event 是 plain Java（如 record 或 simple class），只帶業務資料（applicationId、timestamp 等），沒有 Spring 或 Jackson 注解。意義：(1) domain event 可被 domain unit test 直接建立和驗證，不需要 Spring context；(2) event handler 可以在不改動 domain event class 的情況下更換（將 @EventListener 替換為 Kafka producer）；(3) event class 是 domain 語言的一部分，不洩漏 infrastructure concern。限制：若 event 需要跨服務傳輸（Kafka），需要在 infrastructure layer 做 domain event → message DTO 的轉換，不能直接序列化 domain event 到 topic（除非有意設計相容性）。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/domain/event/ApplicationApprovedEvent.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/event/ApplicationSubmittedEvent.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/event/OtpGeneratedEvent.java`

### Related classes

- `ApplicationApprovedEvent`
- `ApplicationSubmittedEvent`

### Related technologies

- DDD

---

## Q048 (from 300)

### Question

Applicant is embedded inside Application. Is Applicant an entity or a value object? How do you tell?

### Answer summary

判斷 Entity vs Value Object：Entity 有 identity（ID），生命週期獨立，可更改。Value Object 沒有 identity，以值相等，通常不可變，描述 aggregate 的某個屬性。Applicant 在此 project 中：包含姓名、身份證號、聯絡資訊（MobileNumber、Email、Address）等描述性資料，沒有獨立的 ApplicantId，不在 Application 外部被單獨查找或修改，是 Application aggregate 的屬性描述。因此 Applicant 是 value object（或 embeddable value object cluster）。JPA 層面：Applicant 對應 ApplicantEmbeddable（@Embeddable），embed 到 ApplicationEntity table，沒有自己的 primary key。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Applicant.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicantEmbeddable.java`

### Related classes

- Documentation-level question

### Related technologies

- JPA/Hibernate
- DDD

---

## Q051 (from 300)

### Question

Walk me through every valid state transition in ApplicationStatus. Which transitions are intentionally missing, and why?

### Answer summary

合法轉換（ALLOWED_TRANSITIONS map）：INIT → OTP_VERIFIED 或 CANCELLED；OTP_VERIFIED → DOCUMENT_UPLOADED 或 CANCELLED；DOCUMENT_UPLOADED → SUBMITTED 或 CANCELLED；SUBMITTED → UNDER_REVIEW；UNDER_REVIEW → APPROVED 或 REJECTED。刻意缺少的轉換：APPROVED/REJECTED/CANCELLED 不在 map 中作為 key，代表這三個狀態是終止態，不可再轉換。SUBMITTED → CANCELLED 不允許（已進入審核佇列的申請不讓申請人自行撤回）。UNDER_REVIEW → CANCELLED 不允許（審核中不可中途取消）。APPROVED → REJECTED 不允許（已核准不可反悔，需走其他業務流程）。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationStatus.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java`

### Related classes

- `ApplicationStatus.ALLOWED_TRANSITIONS (private static)`
- `ApplicationStatus.canTransitionTo()`

### Related technologies

- State machine

---

## Q052 (from 300)

### Question

ApplicationStatus.canTransitionTo() returns false for null. Why is this defensive check necessary?

### Answer summary

canTransitionTo(null) 的防禦性檢查：方法開頭有 if (next == null) { return false; }，避免後續 ALLOWED_TRANSITIONS.get(this) 返回的 Set 呼叫 contains(null) 拋 NullPointerException（EnumSet 不允許 null 元素，contains(null) 在 EnumSet 中會拋 NPE）。更深層的理由：呼叫端（Application.transitionTo()）傳入 next 參數，若未做驗證直接呼叫 canTransitionTo(null)，就會在 EnumSet.contains(null) 處拋出非預期的 NPE，而非業務可理解的 WorkflowException。提前 return false 讓呼叫端收到可預期的「不允許轉換」行為，再由 transitionTo() 拋出 WorkflowException，錯誤語意更清楚。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationStatus.java`

### Related classes

- `ApplicationStatus.canTransitionTo()`

### Related technologies

- State machine

---

## Q053 (from 300)

### Question

Application.uploadDocuments() allows calling it twice from DOCUMENT_UPLOADED. What business scenario does this support, and what risk does it introduce?

### Answer summary

業務場景：申請人先上傳一份文件（轉換到 DOCUMENT_UPLOADED），發現格式不對，刪除重傳另一份同類型文件，或補充上傳額外文件。目前的 uploadDocuments() 邏輯：if (status == OTP_VERIFIED) 才轉換狀態，if (status == DOCUMENT_UPLOADED) 則跳過狀態轉換直接 addAll。風險：(1) 可以無限次上傳，documentInfos list 只增不減，同一 DocumentType 可能存在多份文件。validateRequiredDocuments() 用 Set 收集 type，只確認 type 存在，不驗重複。(2) 理論上可以上傳超過預期數量的文件，如果存儲後端是磁碟（LocalDocumentStorageService），攻擊者可反覆上傳耗盡磁碟空間。(3) 重複上傳同 type 的文件沒有 deduplication 或 overwrite 機制，審核員可能看到多份同類型文件而產生混淆。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/storage/LocalDocumentStorageService.java`

### Related classes

- `Application.uploadDocuments()`
- `Application.validateRequiredDocuments() (private)`

### Related technologies

- State machine

---

## Q054 (from 300)

### Question

Application.submit() calls validateRequiredDocuments() before the state transition. Why is this ordering critical?

### Answer summary

順序關鍵性：validateRequiredDocuments() 先執行，若拋 BusinessException(INCOMPLETE_DOCUMENTS)，狀態轉換的 transitionTo(SUBMITTED, ...) 就不會執行，Application 保持在 DOCUMENT_UPLOADED 狀態。若順序顛倒（先 transitionTo，後 validateRequiredDocuments()），一旦驗證失敗，狀態已被改成 SUBMITTED，但業務規則要求文件不完整時不能 SUBMIT，造成 aggregate 進入不一致狀態。transitionTo() 還會在 workflowHistories 新增記錄，若先轉換後驗證失敗，history 會記錄一筆「成功轉換到 SUBMITTED」的錯誤記錄。這是 aggregate 設計的重要原則：先驗證所有前置條件，再執行有副作用的操作（狀態改變、history 新增）。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java`

### Related classes

- `Application.submit()`
- `Application.validateRequiredDocuments() (private)`
- `Application.transitionTo() (private)`

### Related technologies

- State machine

---

## Q055 (from 300)

### Question

Why does Application.cancel() not use ALLOWED_TRANSITIONS the way transitionTo() does? It checks CANCELLABLE_STATUSES instead.

### Answer summary

設計意圖：ALLOWED_TRANSITIONS 表示「從某個狀態可以合法轉換到哪些狀態」，這是狀態機的通用規則。但 cancel() 是個特殊的業務操作，語意是「申請人主動放棄」，而非純粹的狀態機轉換。用 CANCELLABLE_STATUSES（EnumSet.of(INIT, OTP_VERIFIED, DOCUMENT_UPLOADED)）明確表示「在哪些狀態下申請人有權取消」，這是業務規則的顯式宣告，比隱含在 ALLOWED_TRANSITIONS 的 CANCELLED key 中更容易理解。注意 ALLOWED_TRANSITIONS 的 INIT 和 OTP_VERIFIED 的 value 也包含 CANCELLED，表示可以 via transitionTo 到 CANCELLED，但 cancel() 用自己的 set 做門檻判斷，使得 DOCUMENT_UPLOADED → CANCELLED 雖然不在 ALLOWED_TRANSITIONS，卻可以透過 cancel() 達成（cancel() 直接呼叫 transitionTo(CANCELLED, ...)，而 transitionTo() 呼叫 canTransitionTo() 驗證，所以 DOCUMENT_UPLOADED → CANCELLED 必須也在 ALLOWED_TRANSITIONS 中才能成功）。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationStatus.java`

### Related classes

- `Application.cancel()`
- `Application`
- `ApplicationStatus.ALLOWED_TRANSITIONS (private static)`

### Related technologies

- State machine

---

## Q056 (from 300)

### Question

ApplicationTest runs with no Spring context. How is this achieved, and what does it tell you about the domain layer design?

### Answer summary

無 Spring context 的實現：ApplicationTest 是純 JUnit 5（@Test 注解即可），沒有 @SpringBootTest、@ExtendWith(SpringExtension.class) 或任何 Spring test 注解。Application aggregate 是純 Java 類別（@Getter、@Builder 是 Lombok，compile-time，無 runtime Spring 依賴），沒有 @Entity、@Service 等 Spring/JPA 注解，不需要 Spring context 就能實例化。測試直接用 Application.builder()...build() 建立 aggregate，呼叫方法，用 AssertJ 驗證狀態。這告訴：domain layer 的設計達到了 clean architecture 的核心目標——業務邏輯可以在不啟動任何框架的情況下快速、獨立地測試，test execution 速度快（毫秒級），不需要資料庫或 Spring container。

### Related files

- `sp2-springboot/src/test/java/com/tlbank/lending/domain/application/ApplicationTest.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java`

### Related classes

- `Application`

### Related technologies

- Testing
- State machine

---

## Q057 (from 300)

### Question

workflowHistories is annotated with @Builder.Default in Application. What happens without this annotation, and why does it matter for the state machine?

### Answer summary

不加 @Builder.Default 的後果：Lombok @Builder 生成的 builder 預設對 List 欄位不初始化（值為 null），除非 field 有初始值且加了 @Builder.Default。若 workflowHistories = null，Application.transitionTo() 呼叫 workflowHistories.add(WorkflowHistory...) 會拋 NullPointerException。@Builder.Default 確保 workflowHistories = new ArrayList<>() 即使在 builder pattern 下也能正確初始化。在 state machine context 中，每次狀態轉換都必須能記錄 history，若 list 為 null 就整個 workflow 崩潰，影響業務操作的原子性。同理 documentInfos 也有相同的 @Builder.Default。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java`

### Related classes

- `Application`
- `Application.transitionTo() (private)`

### Related technologies

- GitHub Actions
- State machine

---

## Q058 (from 300)

### Question

WorkflowHistory is created inside transitionTo(). Who owns the responsibility for generating the history record — the aggregate or the caller?

### Answer summary

由 aggregate 自身生成：transitionTo() 是 private 方法，在驗證通過後自動建立 WorkflowHistory（fromStatus、toStatus、operator、remark、operatedAt）並加入 workflowHistories。這是正確的設計：aggregate 是業務不變式的守護者，history 是狀態轉換的一部分，不應讓 caller（application service）有機會「轉換狀態但忘記記錄 history」或「記錄錯誤的 history」。若改為由 caller 建立 history 並傳入 aggregate，就打破了封裝，讓業務規則分散在 application service 和 aggregate 之間。目前設計讓 Application.approve("reviewer", "Documents look good") 一個呼叫完成狀態轉換 + history 記錄，caller 不需要知道內部細節。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/WorkflowHistory.java`

### Related classes

- `Application.transitionTo() (private)`
- `WorkflowHistory`

### Related technologies

- GitHub Actions
- DDD
- State machine

---

## Q059 (from 300)

### Question

APPROVED and REJECTED are not keys in ALLOWED_TRANSITIONS. What is the implication for canTransitionTo() when called on these statuses?

### Answer summary

ALLOWED_TRANSITIONS.get(APPROVED) 返回 null（key 不存在）。canTransitionTo() 的邏輯：Set<ApplicationStatus> allowed = ALLOWED_TRANSITIONS.get(this) → 當 this == APPROVED 時 allowed == null，然後 return allowed != null && allowed.contains(next) → null != null 為 false，直接回傳 false。這意味著對任何狀態 next，APPROVED.canTransitionTo(next) 恆為 false，REJECTED.canTransitionTo(next) 恆為 false，CANCELLED.canTransitionTo(next) 恆為 false。這是終止態（terminal state）的正確實作：不需要在 map 中為這些狀態加 {APPROVED, emptySet()}，缺少 key 本身就代表「沒有任何合法的後繼狀態」。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationStatus.java`

### Related classes

- `ApplicationStatus.canTransitionTo()`
- `ApplicationStatus.ALLOWED_TRANSITIONS (private static)`

### Related technologies

- State machine

---

## Q060 (from 300)

### Question

OtpRecord.verify() injects a Clock parameter rather than calling LocalDateTime.now() directly. How does this design appear in OtpCleanupScheduler, and why is Clock consistently used?

### Answer summary

一致性的 Clock 使用：OtpRecord.verify() 接受 Clock clock 參數，呼叫 LocalDateTime.now(clock) 和 expiredAt.isBefore(LocalDateTime.now(clock))。OtpCleanupScheduler 注入 Clock clock bean，在 cleanupExpiredOtps() 中以 LocalDateTime.now(clock) 作為 cutoff 傳給 otpRepository.markExpiredBefore()。LocalDocumentStorageService 和 InMemoryCacheStore 也都注入 Clock。好處：測試中注入 Clock.fixed(Instant, ZoneId) 可以凍結時間，驗證「恰好在 expiry 前 1 秒」和「恰好在 expiry 後 1 秒」的邊界條件，不依賴系統時間，測試可重現。Clock bean 在 CommonConfig 中定義為 Clock.systemDefaultZone()，所有需要時間的地方用同一個 bean。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/domain/otp/OtpRecord.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/scheduler/OtpCleanupScheduler.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/common/config/CommonConfig.java`

### Related classes

- `OtpRecord.verify()`
- `OtpRecord.isExpired()`
- `OtpCleanupScheduler.cleanupExpiredOtps()`

### Related technologies

- State machine

---

## Q061 (from 300)

### Question

All REST endpoints return ApiResponse<T> as a wrapper. What fields does this envelope contain, and what design decision does it represent?

### Answer summary

ApiResponse<T> 欄位（record）：boolean success（成功/失敗旗標）、T data（payload，失敗時為 null）、String errorCode（ErrorCode.name()，成功時為 null）、String message（描述訊息）、List<FieldErrorDetail> fieldErrors（validation 錯誤時的欄位細節）、LocalDateTime timestamp（回應產生時間）。設計決策：統一信封（consistent envelope）讓 API client 可以靠 success 欄位快速判斷，不需要依賴 HTTP status code 的語意；錯誤時 errorCode 是 machine-readable 的字串（如 "INCOMPLETE_DOCUMENTS"），client 可以根據它做精確的錯誤處理邏輯。對比：純 HTTP status code 方案需要 client 解析 4xx 的 body 才能知道具體錯誤，ApiResponse 的 errorCode 更明確。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/common/response/ApiResponse.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/common/response/FieldErrorDetail.java`

### Related classes

- `ApiResponse.success()`
- `ApiResponse.error()`

### Related technologies

- REST/OpenAPI

---

## Q064 (from 300)

### Question

StandardApiResponses is used as a meta-annotation on controller methods. What does it do, and why is it a better pattern than repeating @ApiResponse annotations?

### Answer summary

@StandardApiResponses 是自訂的 composite annotation（@Target(ElementType.METHOD)），將常用的 Swagger/OpenAPI @ApiResponse 組合在一起（如 200、400、401、403、409、500 的標準文件說明）。優點：避免在每個 controller method 上重複相同的 6-7 個 @ApiResponse 注解，減少 boilerplate；修改標準回應說明時只需改 StandardApiResponses，不需要找遍所有 controller；讓 controller method 的注解更簡潔，focus 在業務語意（@Operation(summary = "Create application")），而非重複的文件模板。在 OpenAPI 文件中效果與逐個寫相同，但程式碼可維護性更高。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/common/config/StandardApiResponses.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/v1/ApplicationApiController.java`

### Related classes

- `ApplicationApiController.createApplication()`

### Related technologies

- REST/OpenAPI

---

## Q067 (from 300)

### Question

ApplicationApiController.getApplication() returns ApiResponse<ApplicationDetailResponse> directly, not ResponseEntity<ApiResponse<...>>. Why does only the create endpoint use ResponseEntity?

### Answer summary

ResponseEntity 的使用場景：createApplication() 用 ResponseEntity.status(HttpStatus.CREATED).body(...) 是因為需要設置 HTTP 201 狀態碼（資源被建立），也因為 IdempotencyService.execute() 的回傳型別就是 ResponseEntity<ApiResponse<T>>（需要重建 idempotency 快取中的 HTTP status）。getApplication() 直接回傳 ApiResponse<ApplicationDetailResponse>，Spring MVC 會自動以 HTTP 200 回應，因為查詢操作不需要自訂 status code，@ResponseBody 由 @RestController 隱含提供。這個設計遵循「只在需要時才用 ResponseEntity」原則：若 HTTP 200 就足夠，直接回傳 response body 讓 code 更簡潔。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/v1/ApplicationApiController.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/application/idempotency/IdempotencyService.java`

### Related classes

- `ApplicationApiController.createApplication()`
- `ApplicationApiController.getApplication()`

### Related technologies

- JPA/Hibernate
- REST/OpenAPI

---

## Q070 (from 300)

### Question

GlobalExceptionHandler catches AccessDeniedException. Why is catching Spring Security exceptions in a @RestControllerAdvice necessary?

### Answer summary

必要性：Spring Security 的 AccessDeniedException 和 AuthenticationException 預設由 ExceptionTranslationFilter 攔截，轉發到 AccessDeniedHandler/AuthenticationEntryPoint。但 @RestControllerAdvice 的 @ExceptionHandler 只能捕捉 dispatcher servlet 層的 exception，而 security filter 在 dispatcher 之前執行。這裡 GlobalExceptionHandler 能捕捉到 AccessDeniedException，表示 exception 是從 controller 或 service 拋出的（例如 @PreAuthorize 失敗），不是從 filter 層。若是 URL-based 存取控制拒絕（filter 層），則走 CustomAccessDeniedHandler。兩個 handler 共存，確保不論 exception 從哪層拋出都有一致的 JSON 回應格式。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/advice/GlobalExceptionHandler.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/security/handler/CustomAccessDeniedHandler.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java`

### Related classes

- `GlobalExceptionHandler.handleAccessDeniedException()`
- `CustomAccessDeniedHandler`

### Related technologies

- Spring Security
- REST/OpenAPI
- Validation/Exceptions

---

## Q071 (from 300)

### Question

WorkflowException and BusinessException are two separate exception classes. When is each used, and how does GlobalExceptionHandler treat them differently?

### Answer summary

區別：BusinessException：業務邏輯錯誤，帶有 ErrorCode enum（如 INCOMPLETE_DOCUMENTS、OTP_EXPIRED）和描述訊息，映射到多種 HTTP status（400、404、409 等）由 switch expression 決定。WorkflowException：狀態機轉換錯誤，語意更窄（不合法的狀態轉換），不帶 ErrorCode enum（因為所有狀態機錯誤都映射到同一個 INVALID_WORKFLOW_TRANSITION code）。GlobalExceptionHandler 的差異：handleBusinessException() 用 switch on ex.getErrorCode() 映射 HTTP status；handleWorkflowException() 固定回 HTTP 409 CONFLICT，使用 ErrorCode.INVALID_WORKFLOW_TRANSITION。分離設計讓 caller 可以 catch 特定類型，也讓 API 回應語意更清晰。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/common/exception/BusinessException.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/common/exception/WorkflowException.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/advice/GlobalExceptionHandler.java`

### Related classes

- `GlobalExceptionHandler.handleBusinessException()`
- `GlobalExceptionHandler.handleWorkflowException()`

### Related technologies

- GitHub Actions
- Validation/Exceptions

---

## Q072 (from 300)

### Question

GlobalExceptionHandler truncates exception messages at 500 characters in the audit log path, but not in the API response. Is this correct?

### Answer summary

現象：AuditAspect.truncateMessage(ex.getMessage()) 將 exception message 截斷至 500 字元，但 GlobalExceptionHandler 的 ApiResponse.error(code, ex.getMessage(), ...) 直接使用完整的 message。截斷在 audit log 的理由：audit log 的 detail 欄位可能有 VARCHAR 長度限制（資料庫 schema 設定），截斷避免 JPA/JDBC 的 DataException: Value too long for column。API response 中不截斷：回應給 client 的 message 應該完整，以便 client 理解錯誤。潛在問題：若 exception message 本身含有 PII 或 internal stack trace，完整 message 暴露給 API client 可能是安全風險。GlobalExceptionHandler.handleException() 捕捉 unhandled Exception 時，log.error("Unhandled exception", ex) 記錄 stack trace，但只回傳 SYSTEM_ERROR，不洩漏 internal detail——這是正確的做法。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditAspect.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/advice/GlobalExceptionHandler.java`

### Related classes

- `AuditAspect.truncateMessage() (private)`
- `GlobalExceptionHandler.handleBusinessException()`
- `GlobalExceptionHandler.handleException()`

### Related technologies

- Validation/Exceptions
- Logging/MDC

---

## Q073 (from 300)

### Question

@Auditable is a custom annotation with a single action() attribute. How is the annotation processor (AOP aspect) discovered at runtime?

### Answer summary

運行時發現機制：@Auditable 有 @Retention(RetentionPolicy.RUNTIME) 確保 annotation 在 runtime 可被 reflection 讀取；AuditAspect 是 @Component，Spring 掃描到它；AuditAspect 有 @Aspect（AspectJ annotation），Spring AOP 子系統識別為切面；pointcut 是 @Around("@annotation(auditable)")，Spring AOP 在 proxy 中攔截所有帶 @Auditable 的方法呼叫，並注入具體的 Auditable 實例（可直接讀取 auditable.action()）。@EnableAspectJAutoProxy 由 Spring Boot AutoConfiguration 自動啟用。所以整個鏈：@EnableAspectJAutoProxy → 掃描 @Aspect → proxy 包裝目標 bean → 方法呼叫時執行 @Around advice。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/common/audit/Auditable.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditAspect.java`

### Related classes

- `AuditAspect.audit()`

### Related technologies

- Validation/Exceptions
- Logging/MDC

---

## Q074 (from 300)

### Question

What happens in AuditAspect when the audited method throws an exception? Is the audit log written?

### Answer summary

exception 情況下的 audit 行為：AuditAspect.audit() 的 @Around 邏輯：try { Object result = joinPoint.proceed(); auditLogWriter.saveAsync(SUCCESS log); return result; } catch (Throwable ex) { auditLogWriter.saveAsync(FAILURE log); throw ex; } finally { AuditContext.clear(); }。也就是說：exception 拋出時，catch 區塊執行 auditLogWriter.saveAsync(FAILURE log)，記錄失敗的 audit log，然後 throw ex 重新拋出，讓 GlobalExceptionHandler 繼續處理。AuditContext.clear() 在 finally 確保清除，不論成功或失敗。這個設計保證了每個 @Auditable 操作都有一筆 audit log（成功或失敗），不會有漏記。resolveFailureAction() 還能為特定 action（如 OTP_VERIFY_SUCCESS）映射到不同的失敗 action（OTP_VERIFY_FAILED）。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditAspect.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditLogWriter.java`

### Related classes

- `AuditAspect.audit()`
- `AuditAspect.resolveFailureAction() (private)`
- `AuditLogWriter.saveAsync()`

### Related technologies

- Validation/Exceptions
- Logging/MDC

---

## Q075 (from 300)

### Question

FieldErrorDetail is used for validation errors. What information does it carry, and how is it populated?

### Answer summary

FieldErrorDetail 是一個 record（或 class）包含：field（失敗驗證的欄位名稱，如 "applicant.fullName"）、message（constraint message，如 "must not be blank"）。填入方式：GlobalExceptionHandler.handleValidationException() 接收 MethodArgumentNotValidException，呼叫 ex.getBindingResult().getFieldErrors()，每個 FieldError 映射為 new FieldErrorDetail(error.getField(), error.getDefaultMessage())，最後包在 ApiResponse.error(VALIDATION_FAILED, "Validation failed", fieldErrors) 中。巢狀欄位錯誤的 field 名稱是 dot-notation（applicant.mobile），不是扁平的 mobile，讓 client 可以定位到具體的巢狀欄位。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/common/response/FieldErrorDetail.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/advice/GlobalExceptionHandler.java`

### Related classes

- `GlobalExceptionHandler.handleValidationException()`

### Related technologies

- Validation/Exceptions

---

## Q076 (from 300)

### Question

AuditIpResolver is used to extract the client IP address. Why is this non-trivial, and what does the resolver need to handle?

### Answer summary

IP 解析的複雜性：簡單的 request.getRemoteAddr() 只能取到直接連線的 IP（在 reverse proxy/load balancer 後面會是 proxy 的 IP，不是真實 client IP）。AuditIpResolver 需要優先讀取 HTTP header，依序嘗試：X-Forwarded-For（最常見，proxy 轉發鏈）、X-Real-IP（Nginx 直接設定）、Proxy-Client-IP、WL-Proxy-Client-IP 等，最後才 fallback 到 request.getRemoteAddr()。X-Forwarded-For 可能包含多個 IP（client, proxy1, proxy2），需要取第一個（最左邊）才是原始 client。安全考量：client 可以偽造這些 header，所以在真實 production 環境，應只信任來自受信任 proxy 的 X-Forwarded-For。此處用於 audit log，偽造的 IP 會被記錄，不影響業務邏輯安全性，但 audit 的可信度降低。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditIpResolver.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditAspect.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/security/handler/LoginSuccessHandler.java`

### Related classes

- `AuditIpResolver.resolveClientIp()`

### Related technologies

- Validation/Exceptions
- Logging/MDC

---

## Q077 (from 300)

### Question

Spring Security BCryptPasswordEncoder is configured with strength 12. What does the strength parameter control, and what is the performance trade-off?

### Answer summary

BCrypt strength 12：strength 參數（work factor）決定 bcrypt 的 cost，即 2^12 = 4096 次的 key schedule 迭代。CPU work factor：strength 每增加 1，計算時間約 ×2。在現代硬體上 strength 12 大約需要 100–300 ms 驗證一個密碼，strength 10（Spring 預設）約 25–75 ms。Security vs UX 取捨：較高的 strength 使 brute-force 攻擊更慢（攻擊者試 1000 個密碼需要 100–300 秒），但登入時每次驗證也需要 100–300 ms。對銀行應用場景（有嚴格的安全要求），strength 12 是合理的選擇。若用戶量大（1000 並發登入），每個登入請求占用 300 ms CPU 可能成為瓶頸。BCryptPasswordEncoder 是 Spring Security 的預設推薦，不使用 MD5/SHA1。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java`

### Related classes

- `SecurityConfig.passwordEncoder()`

### Related technologies

- Spring Security

---

## Q078 (from 300)

### Question

UserDetailsServiceImpl throws DisabledException when a user's isEnabled() returns false. How does Spring Security handle this, and what does the client receive?

### Answer summary

處理流程：loadUserByUsername() 拋 DisabledException（Spring Security 的 AuthenticationException 子類別）。DaoAuthenticationProvider 捕捉到 DisabledException，傳給 AuthenticationManager，最終觸發 LoginFailureHandler.onAuthenticationFailure()。LoginFailureHandler 接收 AuthenticationException，將其轉換為 HTTP 401 JSON response（ApiResponse.error(UNAUTHORIZED, "Invalid username or password", null)）。注意：LoginFailureHandler 可能對所有 AuthenticationException 回傳相同的 generic message，不區分「帳號不存在」和「帳號被停用」，避免 user enumeration（攻擊者無法透過不同的錯誤訊息判斷帳號是否存在）。SecurityIntegrationTest.login_withDisabledUser_shouldReturn401Json() 驗證此行為。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/security/service/UserDetailsServiceImpl.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/security/handler/LoginFailureHandler.java`
- `sp2-springboot/src/test/java/com/tlbank/lending/security/SecurityIntegrationTest.java`

### Related classes

- `UserDetailsServiceImpl.loadUserByUsername()`
- `LoginFailureHandler.onAuthenticationFailure()`

### Related technologies

- Spring Security
- Validation/Exceptions

---

## Q079 (from 300)

### Question

LoginSuccessHandler writes the audit log synchronously (auditLogRepository.save()) rather than using AuditLogWriter.saveAsync(). Is this intentional, and what is the risk?

### Answer summary

觀察：LoginSuccessHandler.onAuthenticationSuccess() 直接呼叫 auditLogRepository.save(AuditLog.builder()...build())（同步），而其他業務操作透過 AuditAspect + AuditLogWriter.saveAsync() 非同步寫入。可能的理由：login 操作本身是在 Spring Security filter chain 中執行，不在 dispatcher servlet 的 @Async context 中，直接同步寫更可靠。風險：(1) 若 auditLogRepository.save() 失敗（DB 不可用），login 流程可能拋出 exception（雖然 handler 內可能有 try-catch），影響登入成功的回應；(2) 寫 audit log 占用了 login request 的回應時間。改善方式：將 audit log 寫入也抽到 AuditLogWriter.saveAsync()，login 的 audit 不應阻塞回應。此外，LoginSuccessHandler 還更新了 user.updateLastLoginAt(now)（synchronous DB write），這是更必要的同步操作。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/security/handler/LoginSuccessHandler.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditLogWriter.java`

### Related classes

- `LoginSuccessHandler.onAuthenticationSuccess()`
- `AuditLogWriter.saveAsync()`

### Related technologies

- Spring Security
- Logging/MDC

---

## Q080 (from 300)

### Question

MdcLoggingFilter extends OncePerRequestFilter. What does OncePerRequestFilter guarantee, and why does it matter for MDC?

### Answer summary

OncePerRequestFilter 的保證：在同一個 HTTP request 的生命週期中，doFilterInternal() 只被呼叫一次，即使 Spring 的 forward/include dispatch 也不會重複執行（標準 Filter 在 forward 時可能被再次呼叫）。對 MDC 的重要性：MdcLoggingFilter 在 doFilterInternal() 開頭設置 MDC.put(REQUEST_ID, UUID...)，finally 清除 MDC.clear()。若使用標準 Filter，forward dispatch 可能導致 doFilterInternal 再次被呼叫，覆寫 requestId（UUID 會不同），或在 finally 過早清除 MDC，使後續 log 沒有 correlation ID。OncePerRequestFilter 確保整個 request chain（包含 forward）使用同一個 requestId，所有 log 都能被關聯回同一個 request。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/security/filter/MdcLoggingFilter.java`
- `sp2-springboot/src/main/resources/logback-spring.xml`

### Related classes

- `MdcLoggingFilter.doFilterInternal()`

### Related technologies

- Spring Security
- Logging/MDC

---

## Q081 (from 300)

### Question

CSRF is disabled for /api/** but enabled for web form endpoints. How is this configured, and is it safe?

### Answer summary

配置方式：SecurityConfig 中 csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"))，CSRF protection 對 /api/** 的請求不啟用。API 端點（/api/v1/applications、/api/v1/otp）是 stateless JSON API，client（如 browser SPA、mobile app）使用 fetch/axios 發送 JSON request，不是 browser form submit，CSRF 的 threat model 不完全適用（攻擊者無法偽造 CSRF request 並攜帶 JSON body，因為 browser 的 CORS policy 阻止跨域讀取 response）。Web form 端點（/login、Thymeleaf pages）使用 browser form submit，必須有 CSRF token 防止 cross-site form submission。安全疑慮：目前 applicant API（POST /api/v1/applications）是 permitAll（不需登入），攻擊者不能利用 CSRF 盜用 authenticated session 來建立申請。若 admin/reviewer API 的 session-based authentication 與 CSRF 豁免共存，則有 CSRF 攻擊風險。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java`
- `sp2-springboot/docs/decisions/0006-session-over-jwt.md`

### Related classes

- `SecurityConfig.securityFilterChain()`

### Related technologies

- Spring Security

---

## Q082 (from 300)

### Question

LoginSuccessHandler uses LoginResponseMode.prefersJson() to decide between a JSON response and a redirect. What determines whether a request prefers JSON?

### Answer summary

LoginResponseMode.prefersJson() 的判斷邏輯（推測，驗證需查看 source）：通常檢查 Accept header 是否包含 application/json（REST client 會帶這個 header），或 Content-Type 是否是 application/json/application/x-www-form-urlencoded（form login 是後者），或 X-Requested-With: XMLHttpRequest header（傳統 Ajax 請求）。Browser 直接訪問 login page 後 submit form：Accept: text/html，回傳 redirect。Frontend SPA 或 REST client 送 login request：Accept: application/json，回傳 JSON LoginResponse。這個 dual-mode 設計讓同一個 login endpoint 服務 browser form（重定向到 dashboard）和 REST API client（JSON 回應），避免為兩種用途維護兩個端點。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/security/handler/LoginSuccessHandler.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/security/util/LoginResponseMode.java`

### Related classes

- `LoginSuccessHandler.onAuthenticationSuccess()`
- `LoginResponseMode.prefersJson()`

### Related technologies

- Spring Security

---

## Q083 (from 300)

### Question

UserDetailsServiceImpl.toSpringRole() maps "APPLICANT" to "ROLE_USER" instead of "ROLE_APPLICANT". Why?

### Answer summary

Spring Security 的 role 慣例：hasRole("USER") 在 SpEL 中等同於 hasAuthority("ROLE_USER")。DaoAuthenticationProvider 期望 GrantedAuthority 以 ROLE_ 前綴，而 hasRole("X") 自動加前綴判斷。SecurityConfig 中用 hasRole("REVIEWER")、hasRole("ADMIN")，代碼儲存的 role string 是 "REVIEWER"、"ADMIN"。若直接用 "ROLE_APPLICANT" 但程式碼用 hasRole("APPLICANT")，就是 ROLE_APPLICANT mapping，這也可以。這裡選擇 "ROLE_USER" 的原因：applicant 是系統的一般使用者，ROLE_USER 是 Spring Security 慣用的普通用戶 role，語意上合理；也允許 anyRequest().authenticated() 對 REVIEWER/ADMIN 生效，而不特別提及 APPLICANT 這個 role（因為 applicant 的 API 都是 permitAll）。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/security/service/UserDetailsServiceImpl.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java`

### Related classes

- `UserDetailsServiceImpl.toSpringRole() (private)`

### Related technologies

- Spring Security

---

## Q084 (from 300)

### Question

SessionExpiredStrategy returns HTTP 401 with a specific message when a concurrent login invalidates the existing session. What user experience does this create?

### Answer summary

用戶體驗：用戶 A 在電腦登入後，用手機也登入同一帳號（因為 maximumSessions(1)，手機登入成功後，電腦端的 session 被標記為 expired）。當電腦端的下一個請求到達時，Spring Security 的 ConcurrentSessionFilter 偵測到 session expired，呼叫 SessionExpiredStrategy.onExpiredSessionDetected()，回傳 HTTP 401 JSON：{"success": false, "errorCode": "UNAUTHORIZED", "message": "Session expired due to concurrent login"}。電腦端的 SPA/browser 接收到 401 後，應顯示提示（如「您的帳號已在其他地方登入，請重新登入」）並導向登入頁。這個設計讓 reviewer 和 admin 無法同時在多個裝置操作，符合銀行系統的安全要求（防止 session sharing 或未授權的多點登入）。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/security/handler/SessionExpiredStrategy.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java`

### Related classes

- `SessionExpiredStrategy.onExpiredSessionDetected()`

### Related technologies

- Spring Security

---

## Q085 (from 300)

### Question

Applicant-facing APIs (POST /api/v1/applications, POST /api/v1/otp/**) are permitAll. Is this a security risk, and what would you change for production?

### Answer summary

安全風險：目前任何人可以不登入就建立 Application、發送 OTP，沒有任何 rate limiting、身份驗證或 bot 保護。風險項目：(1) OTP 濫用：攻擊者可以對任意手機號碼反覆發送 OTP，造成 SMS spam 和費用爆增；(2) 申請洪水：惡意大量建立 Application，耗盡資料庫空間；(3) 身份冒用：任何人可以用他人的手機號碼建立申請。生產環境應加入：OTP 發送頻率限制（如每個手機號碼每分鐘最多 1 次）；CAPTCHA 或 bot 偵測；applicant.nationalId 格式驗證在 API 層（目前可能只在 domain value object 層）；考慮要求申請人有基本身份（如先認證 email 或 phone）才能發起申請。ADR 0006 和 README 都明確承認 applicant endpoints 是 demo-safe 設定。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java`
- `sp2-springboot/docs/decisions/0006-session-over-jwt.md`

### Related classes

- `SecurityConfig.securityFilterChain()`

### Related technologies

- Spring Security

---

## Q087 (from 300)

### Question

Why was session-based auth chosen over JWT? Under what future circumstances would you add a JWT filter chain alongside the existing session chain?

### Answer summary

選擇 session 的理由（ADR 0006）：Staff portal 是 Thymeleaf server-rendered（browser form login），session 提供即時登出和 maximumSessions(1) 控制，比 JWT 的 token invalidation 問題（JWT 無法立即撤銷）更適合這個場景。加入 JWT filter chain 的時機：當需要支援 mobile app 或第三方服務消費 TLBank API（不用 browser session）；API v2（/api/v2/**）可以新增第二個 SecurityFilterChain bean，設定 SessionCreationPolicy.STATELESS，加入 JWT BearerTokenAuthenticationFilter，而 /login、Thymeleaf admin 路徑繼續走 session chain。兩個 filter chain 可以並存（Spring Security 透過 @Order 決定優先級）。ADR 0006 明確提到這是 roadmap 項目。

### Related files

- `sp2-springboot/docs/decisions/0006-session-over-jwt.md`
- `sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md`
- `sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java`

### Related classes

- `SecurityConfig.securityFilterChain()`
- `SessionExpiredStrategy.onExpiredSessionDetected()`

### Related technologies

- Spring Security

---

## Q090 (from 300)

### Question

ApplicationEntity uses @Enumerated(EnumType.STRING) for the status field. Why not EnumType.ORDINAL?

### Answer summary

EnumType.STRING vs ORDINAL：ORDINAL 儲存 enum 的 ordinal 值（0, 1, 2...），STRING 儲存 enum 名稱字串（"INIT"、"OTP_VERIFIED"...）。ORDINAL 的危險：若 enum 定義中插入一個新的常數（如在 INIT 和 OTP_VERIFIED 之間插入 EMAIL_VERIFIED），所有既有資料庫 ordinal 就會錯位（原來的 OTP_VERIFIED = 1 變成 EMAIL_VERIFIED），現有資料就讀錯了，且問題不在 compile time 發現。STRING 的保護：資料庫儲存 "OTP_VERIFIED" 字串，不受 enum 順序改變影響，只有在重命名 enum constant 時才需要 migration。銀行應用的申請狀態不應因程式碼重構而讀錯，所以 EnumType.STRING 是正確選擇。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationEntity.java`

### Related classes

- `ApplicationEntity`

### Related technologies

- JPA/Hibernate

---

## Q091 (from 300)

### Question

ApplicationJpaRepository has a method findByApplicationNo(String applicationNo). Why is there a applicationNo separate from the id primary key?

### Answer summary

兩種 ID 的理由：id（BIGINT IDENTITY PRIMARY KEY）是資料庫的技術 ID（surrogate key），用於 JPA entity 的 primary key，外鍵關聯（workflow_histories.application_id）等內部 join。applicationNo（VARCHAR(30)，UNIQUE）是業務 ID（business key），格式如 APP-20260628170759-2980，暴露給外部世界（API response、URL path /api/v1/applications/APP-...、用戶看得到的申請編號）。分離的好處：(1) 技術 ID（BIGINT）不暴露給外部，防止 sequential enumeration（攻擊者不能猜測 id=1,2,3... 來存取他人申請）；(2) 業務 ID 有可讀格式（帶時間戳），用戶容易識別；(3) 可以跨 migration 更改技術 ID 生成策略而不影響 API contract。ApplicationId.generate() 生成帶時間戳的業務 ID。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationEntity.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationJpaRepository.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationId.java`

### Related classes

- `ApplicationEntity`
- `ApplicationJpaRepository`

### Related technologies

- JPA/Hibernate

---

## Q092 (from 300)

### Question

ApplicationEntity maps workflowHistories and documents with FetchType.LAZY. What problem does this solve, and what new problem does it create?

### Answer summary

LAZY 解決的問題：每次 findByApplicationNo() 加載 ApplicationEntity 時，若 workflowHistories 和 documents 是 EAGER，Hibernate 會自動 JOIN 加載所有子集合（即使 caller 只需要 application status），增加不必要的 DB 查詢和記憶體使用。LAZY 確保子集合只在第一次存取時才加載（lazy initialization）。引入的問題：LazyInitializationException（could not initialize proxy - no Session）：在 session 關閉後（transaction 結束後）存取 lazy 集合會拋此例外。在 ApplicationRepositoryImpl.toDomain() 中，需要存取 entity.getWorkflowHistories() 來建立 domain object，這必須在 JPA session 還活著（transaction 開啟）的情況下執行。@Transactional 的邊界必須涵蓋整個 repository method，確保 lazy load 在 session 存續期間完成。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationEntity.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationRepositoryImpl.java`

### Related classes

- `ApplicationEntity`
- `ApplicationRepositoryImpl.toDomain() (private)`

### Related technologies

- JPA/Hibernate
- GitHub Actions

---

## Q093 (from 300)

### Question

@Modifying appears on OtpJpaRepository.markExpiredBefore(). What does @Modifying do, and what does @Transactional on the caller provide?

### Answer summary

@Modifying 的作用：標記此查詢為 DML（UPDATE/DELETE），而非 SELECT。Spring Data JPA 的 @Query 預設假設是 SELECT，@Modifying 告訴它這是修改操作，返回受影響的行數（int）而非結果集。若沒有 @Modifying，執行 UPDATE/DELETE query 會拋 InvalidDataAccessApiUsageException。@Transactional 的角色：@Modifying 操作必須在 transaction 中執行，否則 JPA 拋錯。OtpCleanupScheduler.cleanupExpiredOtps() 標有 @Transactional，確保 markExpiredBefore() 在 transaction boundary 內。若 scheduler 沒有 @Transactional，需要由 repository 方法自己提供 transaction（可在 @Modifying method 上加 @Transactional）。clearAutomatically = true（可選）在 @Modifying 上清除 JPA first-level cache，避免 stale data 問題。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/otp/OtpJpaRepository.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/scheduler/OtpCleanupScheduler.java`

### Related classes

- `OtpJpaRepository`
- `OtpCleanupScheduler.cleanupExpiredOtps()`

### Related technologies

- JPA/Hibernate
- Transactions

---

## Q094 (from 300)

### Question

@Transactional(readOnly = true) is used on query methods in the application service. What optimizations does Hibernate apply for read-only transactions?

### Answer summary

readOnly = true 的 Hibernate 最佳化：(1) Hibernate 跳過 dirty checking（不追蹤 entity 變更，transaction commit 時不掃描所有 entity 是否有修改），節省 CPU；(2) Hibernate 可以提示底層 JDBC driver 使用唯讀模式（Connection.setReadOnly(true)），某些 driver 和資料庫會因此使用較低的 isolation level 或不獲取寫鎖；(3) Spring DataSourceTransactionManager 在某些 connection pool 配置下可以優化連線選擇（如從 read replica 取連線）。不是 magic read-only lock：readOnly = true 不阻止你呼叫 entityManager.persist()，但 dirty checking 跳過意味著即使修改了 entity，也不會 flush 到資料庫（除非顯式 flush）。在此 project 中：ApplicationAppService.getApplication() 有 @Transactional(readOnly = true)，適合只查詢、不修改的 use case。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/security/service/UserDetailsServiceImpl.java`

### Related classes

- `ApplicationAppService.getApplication()`
- `UserDetailsServiceImpl.loadUserByUsername()`

### Related technologies

- JPA/Hibernate
- Transactions

---

## Q095 (from 300)

### Question

ApplicationJpaRepository uses both JPQL (@Query with entity names) and native SQL (nativeQuery = true). Why mix the two?

### Answer summary

混用的理由：JPQL 查詢（SELECT COUNT(a) FROM ApplicationEntity a WHERE a.createdAt >= :start）：使用 JPA entity 名稱和欄位名稱，與底層 table/column 命名無關，若重命名 table 或欄位（加 @Column(name="...")），JPQL 不受影響，適合簡單的計數查詢。Native SQL 查詢（SELECT status, COUNT(*) FROM applications WHERE... GROUP BY status）：使用真實 table 和 column 名稱，適合聚合報表查詢（GROUP BY），特別是當 JPQL 的聚合語法比 native SQL 繁瑣時，或需要特定資料庫函數（SQL Server 特有語法）。取捨：JPQL 更 portable（換資料庫無需改），native SQL 更直接（複雜聚合更簡單）。報表類查詢傾向 native SQL，CRUD 類查詢傾向 JPQL，這是常見的 pattern。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationJpaRepository.java`

### Related classes

- `ApplicationJpaRepository`

### Related technologies

- JPA/Hibernate
- SQL/Flyway

---

## Q096 (from 300)

### Question

MaskingUtil masks national IDs, mobile numbers, emails, and names before they appear in API responses. Where exactly in the code is masking applied?

### Answer summary

Masking 的應用點：ApplicationAppService.getApplication() 呼叫 toMaskedApplicant(application.getApplicant())，建立 MaskedApplicantResponse，其中 mobile = MaskingUtil.maskMobile(applicant.getMobileNumber().value())、nationalId = MaskingUtil.maskNationalId(applicant.getNationalId())、name = MaskingUtil.maskName(applicant.getFullName())，email 也類似。Domain 層的 MobileNumber.masked() 也提供 masking 方法，但 masking 邏輯主要在 application service 的 response mapping 階段執行。Domain aggregate Application.getApplicant() 返回的是未 masked 的真實資料，audit log 中記錄的也可能是 masked version（透過 AuditDetailBuilder）。注意：PII 只在 API response（outbound）被 mask，不在 log、audit log 的 detail 欄位中 mask（需確認 AuditDetailBuilder 是否也有 masking）。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/common/util/MaskingUtil.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/MaskedApplicantResponse.java`

### Related classes

- `MaskingUtil.maskMobile()`
- `MaskingUtil.maskNationalId()`
- `ApplicationAppService.toMaskedApplicant() (private)`

### Related technologies

- Spring Security

---

## Q102 (from 300)

### Question

ApplicationRepositoryImpl.save() checks if an entity with the same applicationNo already exists before deciding to insert or update. Why not use JpaRepository.save() directly?

### Answer summary

不直接用 JpaRepository.save() 的理由：JpaRepository.save() 的邏輯是：entity.getId() == null → INSERT，entity.getId() != null → merge（UPDATE）。問題：domain aggregate Application 不持有資料庫的 surrogate id（BIGINT），只有業務 applicationId（ApplicationId value object，對應 applicationNo 字串）。在 toDomain() 轉換後，domain object 不知道 JPA 的數字 id，因此無法直接用 JPA 的 id-based save 判斷是 INSERT 還是 UPDATE。ApplicationRepositoryImpl.save() 的解法：先 findByApplicationNo(application.getApplicationId().value())，找到存在的 entity 就 applyToEntity(existing, application) update，找不到就 toEntity(application) 新建，再 applicationJpaRepository.save(entity)。這是 domain id 與 surrogate key 分離設計的必然代價。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationRepositoryImpl.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationJpaRepository.java`

### Related classes

- `ApplicationRepositoryImpl.save()`
- `ApplicationJpaRepository`

### Related technologies

- JPA/Hibernate
- Hexagonal

---

## Q103 (from 300)

### Question

OtpRepositoryImpl has findLatestPendingByMobile(). Why does it return the latest (most recent) OTP rather than any pending OTP?

### Answer summary

「最新」而非「任意」的理由：sendOtp() 的流程是先取消現有 pending OTP，再建立新 OTP。但若取消動作在 DB 還沒 commit 或有 race condition，可能存在多筆 PENDING OTP。findTopByMobileAndStatusOrderByCreatedAtDesc() 取最新（最近建立）的 PENDING OTP，確保 verify 時驗證的是最後送出的 OTP code，不是舊的。若返回任意一筆，可能讓用戶拿舊碼驗證失敗（碰巧取到舊的）或拿到已過期的舊碼。Spring Data JPA 的 findTop...By...OrderBy...Desc 是 derived query，findTop 限制返回一筆，OrderByCreatedAtDesc 確保取最新的，不需要手寫 JPQL。這也與 sendOtp() 的設計搭配：send OTP 時先 cancel 舊的，verify OTP 時取最新的，確保一致性。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/otp/OtpJpaRepository.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/application/otp/service/OtpAppService.java`

### Related classes

- `OtpJpaRepository`
- `OtpAppService.sendOtp()`

### Related technologies

- Testing
- Hexagonal

---

## Q104 (from 300)

### Question

ReviewCaseRepository.search() accepts a Pageable parameter. Should Pageable appear in the domain port interface? What are the alternatives?

### Answer summary

爭議點：Pageable 是 Spring Data 的類別（org.springframework.data.domain.Pageable），若 domain port interface ReviewCaseRepository.search() 的參數是 Pageable，則 domain layer 隱性依賴了 Spring Data，違反 Clean Architecture 的依賴規則。ADR 0001 承認有此類 leak。替代方案：(1) 自訂 PageCriteria value object（offset, limit, sortBy, sortDirection），domain port 接受 PageCriteria；adapter 在 ReviewCaseRepositoryImpl 中將 PageCriteria 轉換為 Spring 的 PageRequest；(2) 讓 port 只有 findAll(ReviewCaseSearchCriteria) 返回完整 list，分頁邏輯在 application service 處理（不適合大資料量）。目前選擇 Pageable 是 pragmatic trade-off：Spring Data 的 Page<T> 提供 totalElements、totalPages 等分頁 metadata，自訂 PageCriteria 不省多少工，且分頁是「how to query」的 infrastructure 關心點，不是業務規則。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/domain/review/repository/ReviewCaseRepository.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/application/review/service/ReviewAppService.java`
- `sp2-springboot/docs/decisions/0001-use-clean-architecture.md`

### Related classes

- `ReviewCaseRepository`
- `ReviewAppService.searchCases()`

### Related technologies

- Hexagonal

---

## Q107 (from 300)

### Question

Both OtpRepositoryImpl and ApplicationRepositoryImpl follow the same pattern: domain port → JPA repository → entity. Is there any code duplication, and how might it be reduced?

### Answer summary

重複的 pattern：兩個 impl 都有 toDomain(Entity entity)、toEntity(Domain domain)、applyToEntity(Entity existing, Domain domain) 三個 mapping 方法，邏輯結構相似但具體欄位不同。潛在重複：若日後有 8-10 個 aggregate，每個都有相同三方法的 impl，維護成本上升。減少重複的選項：(1) MapStruct：定義 @Mapper interface，自動生成 mapping 程式碼，已在 pom.xml 的 classpath（ADR 0001 提到但未啟用），是已知的 technical debt；(2) 抽象 base class BaseRepositoryImpl<Domain, Entity, Id> 提供通用的 save 邏輯，子類別只需提供 mapping 方法（template method pattern）；(3) 接受目前的重複，因為每個 aggregate 的 mapping 複雜度不同（Application 有巢狀 Applicant、WorkflowHistory，OtpRecord 相對簡單），過度抽象可能降低可讀性。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationRepositoryImpl.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/otp/OtpRepositoryImpl.java`
- `sp2-springboot/pom.xml`

### Related classes

- `ApplicationRepositoryImpl.toDomain() (private)`
- `OtpRepositoryImpl.toDomain() (private)`

### Related technologies

- JPA/Hibernate
- Hexagonal

---

## Q109 (from 300)

### Question

ReviewAppService publishes ApplicationApprovedEvent after approving a review case. Where in the service does this happen, and what triggers it?

### Answer summary

發布時機：ReviewAppService.approveCase() 方法（或類似名稱）：(1) 讀取 ReviewCase；(2) 呼叫 reviewCase.approve(operator, remark)（aggregate 內部狀態轉換）；(3) 讀取相關的 Application；(4) 呼叫 application.approve(operator, remark)（Application aggregate 狀態轉換到 APPROVED）；(5) applicationRepository.save(application)；(6) reviewCaseRepository.save(reviewCase)；(7) eventPublisher.publishEvent(new ApplicationApprovedEvent(applicationId, mobile, email))。事件在所有 DB 操作完成後、transaction commit 前發布（Spring 的 ApplicationEventPublisher 同步發布，@EventListener 在同一 thread 執行）。NotificationEventHandler 捕捉 ApplicationApprovedEvent 發送通知，使用 try-catch 確保通知失敗不影響 transaction commit。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/application/review/service/ReviewAppService.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/event/ApplicationApprovedEvent.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/event/NotificationEventHandler.java`

### Related classes

- `ReviewAppService.approveCase()`

### Related technologies

- Hexagonal

---

## Q110 (from 300)

### Question

ReviewAppService calls both ApplicationRepository and ReviewCaseRepository in a single transaction. What are the consistency guarantees and risks?

### Answer summary

單一 transaction 的保證：@Transactional 包裹整個 approveCase() 方法，Application.approve() 和 ReviewCase.approve() 的 DB 寫入在同一個 ACID transaction，若任何一個 save 失敗，整個 transaction rollback，兩個 aggregate 都不會部分更新。一致性風險：兩個 aggregate 的 approval 狀態必須同時提交（all-or-nothing），但業務上 Application.status = APPROVED 和 ReviewCase.status = APPROVED 理應同步，這符合設計。進階問題：若 ApplicationRepository.save() 成功但 ReviewCaseRepository.save() 失敗，transaction rollback 讓兩者都回滾——這是正確的。另一個風險：事件（ApplicationApprovedEvent）在 transaction 內發布，若 NotificationEventHandler 的 try-catch 沒有，notification 失敗可能影響 transaction。@TransactionalEventListener(phase = AFTER_COMMIT) 可讓 event 在 transaction commit 後才執行，更安全。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/application/review/service/ReviewAppService.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/repository/ApplicationRepository.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/review/repository/ReviewCaseRepository.java`

### Related classes

- `ReviewAppService.approveCase()`

### Related technologies

- Transactions
- Hexagonal

---

## Q111 (from 300)

### Question

H2 is configured with MODE=MSSQLServer in application-dev.yml. What SQL compatibility does this provide, and what are its limits?

### Answer summary

MODE=MSSQLServer 提供的相容性：H2 模擬 SQL Server 的一些語法特性，如 ISNULL() 函數（SQL Server 的 null 替換）、TOP N 語法（H2 預設是 LIMIT N）、@@IDENTITY 函數、DATETIME2 資料型別（部分支援）。H2 connection URL：jdbc:h2:mem:tlbank_lending;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE;MODE=MSSQLServer。限制（已知不完全相容）：(1) GETDATE() 函數（SQL Server）vs CURRENT_TIMESTAMP（ANSI SQL，H2 支援）；(2) IDENTITY(1,1) 語法（SQL Server PRIMARY KEY 定義）vs GENERATED BY DEFAULT AS IDENTITY（H2）；(3) 複雜的 T-SQL 特性（TRY...CATCH、GOTO、OUTPUT clause）；(4) DATETIME2 精度差異。因此有兩個 Flyway migration 資料夾：db/migration（H2 相容語法）和 db/migration-sqlserver（原生 SQL Server 語法）。

### Related files

- `sp2-springboot/src/main/resources/application-dev.yml`
- `sp2-springboot/src/main/resources/db/migration/V1__create_users.sql`
- `sp2-springboot/src/main/resources/db/migration-sqlserver/V1__create_users.sql`

### Related classes

- Documentation-level question

### Related technologies

- SQL/Flyway

---

## Q114 (from 300)

### Question

V3__create_applications.sql in the H2 migration sets status DEFAULT 'DRAFT', but ApplicationStatus enum has no DRAFT constant. Is this a bug?

### Answer summary

分析：H2 migration V3__create_applications.sql：status VARCHAR(30) NOT NULL DEFAULT 'DRAFT'，但 ApplicationStatus enum 是：INIT, OTP_VERIFIED, DOCUMENT_UPLOADED, SUBMITTED, UNDER_REVIEW, APPROVED, REJECTED, CANCELLED，沒有 DRAFT。影響：DEFAULT 'DRAFT' 只在直接 INSERT 時不提供 status 欄位才生效。在程式碼中，ApplicationAppService.createApplication() 明確設定 status(ApplicationStatus.INIT)，ApplicationRepositoryImpl.toEntity() 將 ApplicationStatus.INIT 轉換到 entity 的 status 欄位，不會觸發資料庫 DEFAULT。若有人直接 INSERT applications 而不提供 status（如手動 SQL 插入），會在 DB 儲存 "DRAFT"，但 Hibernate 讀取時找不到對應的 ApplicationStatus.DRAFT，拋 exception。這是 schema 和 domain 不一致的技術債，可能是 schema 演化過程中的遺留（舊版叫 DRAFT，後改為 INIT），應修正為 DEFAULT 'INIT'。

### Related files

- `sp2-springboot/src/main/resources/db/migration/V3__create_applications.sql`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationStatus.java`

### Related classes

- Documentation-level question

### Related technologies

- SQL/Flyway
- State machine

---

## Q127 (from 300)

### Question

ApplicationAppService.createApplication() has @Transactional but getApplication() has @Transactional(readOnly = true). Why does even a read-only query need a transaction?

### Answer summary

Read-only 也需要 transaction 的原因：(1) JPA Lazy Loading：getApplication() 讀取 ApplicationEntity 後呼叫 entity.getWorkflowHistories()（lazy collection），若沒有 open session，就拋 LazyInitializationException；@Transactional 確保整個方法執行期間 Hibernate session 保持 open，lazy load 可以在 session 內完成；(2) Transaction isolation：@Transactional(readOnly = true) 給資料庫 hint，可用較低的 isolation level（如 READ COMMITTED），某些資料庫在 read-only transaction 下可以路由到 read replica；(3) Hibernate first-level cache（session cache）：在 transaction 內，同一 entity 的多次查詢只打一次 DB（identity map），read-only transaction 可以共享此 cache。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationEntity.java`

### Related classes

- `ApplicationAppService.getApplication()`
- `ApplicationEntity`

### Related technologies

- Transactions

---

## Q128 (from 300)

### Question

AuditLogWriter.saveAsync() uses PROPAGATION_REQUIRES_NEW. What does this mean for the surrounding transaction?

### Answer summary

PROPAGATION_REQUIRES_NEW：requiresNewTransaction = new TransactionTemplate(transactionManager) 設定 PROPAGATION_REQUIRES_NEW，執行時暫停（suspend）當前 thread 上的任何活躍 transaction，開始一個全新的獨立 transaction 來執行 auditLogRepository.save(auditLog)，完成後 commit 新 transaction，然後恢復被暫停的原始 transaction。業務意義：audit log 的寫入與業務操作的 transaction 隔離。若業務 transaction rollback（如申請提交失敗），audit log 已在獨立 transaction 中 committed，不會隨業務 rollback 一起消失。這確保了：即使業務操作失敗，失敗的 audit log（result = "FAILURE"）仍被保留，audit trail 完整。若使用預設的 PROPAGATION_REQUIRED，audit log 與業務在同一 transaction，business rollback 時 audit log 也會 rollback，失去審計記錄。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditLogWriter.java`

### Related classes

- `AuditLogWriter.saveAsync()`

### Related technologies

- Transactions
- Logging/MDC

---

## Q129 (from 300)

### Question

OtpCleanupScheduler.cleanupExpiredOtps() is annotated with @Transactional. What would happen without it?

### Answer summary

沒有 @Transactional 的後果：otpRepository.markExpiredBefore(cutoff) 最終呼叫 OtpJpaRepository.markExpiredBefore()，這是 @Modifying + @Query（UPDATE 語句）。@Modifying 的執行需要一個活躍的 JPA transaction。若沒有 @Transactional 在 scheduler 方法上，Spring Data JPA 的 repository method 在沒有 outer transaction 的情況下，根據 default propagation（PROPAGATION_REQUIRED），會建立自己的 transaction 並在方法結束後 commit。實際上這在多數情況下仍可工作，但有微妙差異：(1) JPA 的 auto-transaction 管理不如明確聲明的 @Transactional 清晰；(2) 若 markExpiredBefore() 的實作使用了 JPA session 的 first-level cache（entity manager），沒有 outer transaction 時 session 的 lifecycle 更短，某些 cache 行為不同；(3) 明確聲明 @Transactional 讓 log（@Slf4j）和 transaction 邊界清晰，try-catch 也明確包裹整個 scheduler 邏輯。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/scheduler/OtpCleanupScheduler.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/otp/OtpJpaRepository.java`

### Related classes

- `OtpCleanupScheduler.cleanupExpiredOtps()`
- `OtpJpaRepository`

### Related technologies

- Transactions

---

## Q130 (from 300)

### Question

ReviewAppService.approveCase() calls applicationRepository.save() and reviewCaseRepository.save(). What happens to the domain event if the second save fails?

### Answer summary

失敗場景：假設 applicationRepository.save(application) 成功（Application 狀態改為 APPROVED），但 reviewCaseRepository.save(reviewCase) 拋 exception：(1) @Transactional 的 rollback：exception 傳播出 approveCase() 方法，Spring 的 TransactionInterceptor 偵測到 exception，觸發 rollback，兩個 save 都撤銷，DB 狀態回到 approve 之前——這是正確的 ACID 行為；(2) 事件：eventPublisher.publishEvent(ApplicationApprovedEvent) 在 exception 之前已執行，NotificationEventHandler 已在同一 thread 處理（Spring 同步事件），若通知已發出（mock SMS log 已寫），但 DB transaction rollback，業務上出現「通知已發出但申請未真正 approve」的不一致。這正是 @TransactionalEventListener(phase = AFTER_COMMIT) 可以解決的問題——等 transaction commit 成功後才發送通知，若 rollback 則通知不發出。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/application/review/service/ReviewAppService.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/event/NotificationEventHandler.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/event/ApplicationApprovedEvent.java`

### Related classes

- `ReviewAppService.approveCase()`

### Related technologies

- Transactions
- DDD

---

## Q131 (from 300)

### Question

@Transactional is on the application service methods, not on the domain methods. Why this placement?

### Answer summary

@Transactional 在 application service 的理由：(1) Transaction 是 use case 的 concern：一個 use case（如 submitApplication()）需要在單一 ACID transaction 中完成多個 DB 操作（save Application、save OTP、emit event）；use case 的範圍就是 transaction 的範圍，由 application service 控制；(2) Domain 純粹性：domain aggregate 方法（application.submit()）是純業務邏輯，不應知道「我是否在 transaction 中」；加了 @Transactional 就讓 domain 依賴 Spring，違反 clean architecture；(3) Spring AOP proxy 限制：@Transactional 需要 Spring proxy，domain objects 通常不是 Spring bean（Application 是 plain Java object，不是 @Service），proxy 機制不適用於 new 出來的 domain objects；(4) 可測試性：domain unit test 無需 transaction 管理，application service test 可以 mock repository，integration test 才需要真實 transaction。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java`

### Related classes

- `ApplicationAppService.createApplication()`
- `Application.submit()`

### Related technologies

- Transactions

---

## Q132 (from 300)

### Question

IdempotencyService.execute() acquires a lock with tryAcquireLock() and always releases it in finally. Why is finally critical here?

### Answer summary

finally 的必要性：lock 在 try 區塊取得（idempotencyStore.tryAcquireLock(lockKey, LOCK_TTL_SECONDS)），業務邏輯（action.get()）在 try 內執行，finally 確保 idempotencyStore.releaseLock(lockKey) 不論業務邏輯是否拋 exception 都執行。若沒有 finally：若 action.get() 拋 exception（如 DB 錯誤），lock 不會被釋放，lockKey 在 Redis 中的 TTL（30 秒）到期前，任何相同 idempotency key 的重試都會得到「A request with this Idempotency-Key is already in progress」的錯誤，即使前一個請求已失敗。LOCK_TTL_SECONDS = 30 作為 safety net：即使 releaseLock() 自身也失敗（Redis 不可用），lock 最多 30 秒後自動過期，不會永久鎖住。這是分散式 lock 的標準 acquire-try-finally-release pattern。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/application/idempotency/IdempotencyService.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/idempotency/RedisIdempotencyStore.java`

### Related classes

- `IdempotencyService.execute()`
- `RedisIdempotencyStore.tryAcquireLock()`
- `RedisIdempotencyStore.releaseLock()`

### Related technologies

- Redis/Idempotency

---

## Q134 (from 300)

### Question

InMemoryIdempotencyStore is used for tests and when Redis is unavailable. How does its TTL enforcement differ from Redis?

### Answer summary

TTL 實現差異：RedisIdempotencyStore.save(key, entry, ttlSeconds)：呼叫 redisTemplate.opsForValue().set(key, json, Duration.ofSeconds(ttlSeconds))，TTL 由 Redis 服務器在指定時間後自動刪除 key，完全 server-side，不需要 JVM 做任何事。InMemoryIdempotencyStore.save(key, entry, ttlSeconds)：entries.put(key, new TimedEntry(entry, Instant.now().plusSeconds(ttlSeconds)))，TTL 是 TimedEntry.expiresAt，過期判斷在 find() 時（timed.expiresAt().isBefore(Instant.now())）做 lazy eviction，過期後 entry 在下次被 find() 時才被移除（或由 cleanupExpiredEntries() 主動清除）。差異：Redis 的過期是 exact（服務器計時），JVM 記憶體的過期是 lazy（需要被查詢才清除）；Redis TTL 對 JVM restart 有效（資料在 Redis 中），in-memory TTL 在 JVM 重啟後消失。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/idempotency/InMemoryIdempotencyStore.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/idempotency/RedisIdempotencyStore.java`

### Related classes

- `InMemoryIdempotencyStore.find()`
- `InMemoryIdempotencyStore.save()`

### Related technologies

- Testing
- Redis/Idempotency

---

## Q155 (from 300)

### Question

ExcelReportGeneratorTest creates an XSSFWorkbook from the generated bytes to verify the sheet structure. What does this test strategy reveal about testing output formats?

### Answer summary

測試策略的啟示：ExcelReportGeneratorTest 不只驗證「方法不拋 exception」，也驗證 output 的結構（workbook.getNumberOfSheets() == 2、sheet name 是 "Summary" 和 "By Product"）和內容（所有 ApplicationStatus 值都出現在 Summary sheet）。使用 Apache POI 本身解析 output：new XSSFWorkbook(new ByteArrayInputStream(content)) 讀回剛生成的 bytes，直接操作 Sheet、Row、Cell，是「round-trip testing」——用同一個工具驗證自己的 output。這揭示了測試輸出格式（Excel、PDF）的關鍵策略：不要只 assert content.length > 0（太弱，任何垃圾 bytes 都會通過），應解析並驗證業務相關的 content（correct sheet names、expected data rows、status names present）。PdfReportGenerator 可以用 iText 的 PdfReader 做類似驗證，但 PDF 內容提取較複雜，實際測試中可能只驗證 PDF metadata。

### Related files

- `sp2-springboot/src/test/java/com/tlbank/lending/infrastructure/report/ExcelReportGeneratorTest.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/report/ExcelReportGenerator.java`

### Related classes

- `ExcelReportGeneratorTest`
- `ExcelReportGenerator.generateDailyStatistics()`

### Related technologies

- Testing

---

## Q183 (from 300)

### Question

The Dockerfile uses a two-stage build. What are the benefits, and what specific images are used for each stage?

### Answer summary

兩階段 build 的好處：第一階段（builder）：FROM eclipse-temurin:17-jdk AS builder，包含完整 JDK（javac、maven 工具）+ 所有 build artifacts，編譯和打包 JAR。第二階段（runtime）：FROM eclipse-temurin:17-jre AS runtime，只包含 JRE（執行 .class 不需要 javac、jlink 等工具），COPY --from=builder /workspace/target/*.jar app.jar 複製 JAR。好處：(1) 大幅縮小最終 image 大小（JDK 約 500MB，JRE 約 180MB）；(2) 安全性：JDK 的 javac、jarsigner 等工具不在 runtime image 中，減少攻擊面；(3) Maven dependencies 和 source code 不在 runtime image 中（只有 JAR），不洩漏 source。特殊選擇：eclipse-temurin:17-jdk（Eclipse Temurin = Adoptium project 的 OpenJDK distribution，非 Oracle JDK，license 友好）；非 Alpine base（Alpine 的 musl libc 可能與某些 Java library 不相容，Temurin on Debian/Ubuntu 更穩定）。

### Related files

- `sp2-springboot/docker/app/Dockerfile`

### Related classes

- Documentation-level question

### Related technologies

- Docker

---

## Q191 (from 300)

### Question

The CI pipeline has 5 jobs with needs dependencies. Draw the dependency graph and identify the critical path.

### Answer summary

5 job 依賴圖：build-test → code-quality（needs: build-test）→ 無後繼（僅止於此）；build-test → dependency-scan（needs: build-test）→ 無後繼；build-test + code-quality + dependency-scan → build-and-push-image（needs: [build-test, code-quality, dependency-scan]）→ deploy-staging（needs: build-and-push-image，且只在 workflow_dispatch 執行）。並行：code-quality 和 dependency-scan 在 build-test 完成後並行執行。Critical path（最長路徑）：build-test → code-quality 或 dependency-scan（兩者並行，取較慢的）→ build-and-push-image → deploy-staging。若 code-quality 耗時 3 分鐘、dependency-scan 耗時 5 分鐘（Trivy 較慢），critical path 是 build-test + dependency-scan + build-and-push-image + deploy-staging。code-quality 不在 critical path（只要它比 dependency-scan 快就不影響總時間）。

### Related files

- `.github/workflows/ci.yml`

### Related classes

- Documentation-level question

### Related technologies

- GitHub Actions

---

## Q234 (from 300)

### Question

MdcLoggingFilter puts request correlation fields into MDC. What is logged, and what is still missing for production observability?

### Answer summary

MdcLoggingFilter 在請求進入時寫入 MDC（如 requestId、username），供 Logback pattern 輸出。現況以單機日誌為主，維護文件指出後續才會引入 OpenTelemetry 分散式追蹤與 Micrometer 指標；不可把現況說成已具備完整 observability stack。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/security/filter/MdcLoggingFilter.java`
- `sp2-springboot/src/main/resources/logback-spring.xml`
- `sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md`

### Related classes

- `MdcLoggingFilter`
- `SecurityConfig.securityFilterChain()`

### Related technologies

- Logging/MDC

---

## Q250 (from 300)

### Question

SecurityIntegrationTest covers login and authorization. Which behaviors should a candidate expect it to assert?

### Answer summary

SecurityIntegrationTest 以 MockMvc 驗證登入成功/失敗、角色授權與工作階段相關行為，密碼對應 dev seed 的測試常數。此測試鎖定 SecurityConfig 與 handler 合約，屬於安全回歸的主要自動化防線。

### Related files

- `sp2-springboot/src/test/java/com/tlbank/lending/security/SecurityIntegrationTest.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java`

### Related classes

- `SecurityConfig.securityFilterChain()`
- `LoginSuccessHandler`
- `LoginFailureHandler`

### Related technologies

- Spring Security
- Testing

---

## Q251 (from 300)

### Question

ApplicationId.generate() uses ThreadLocalRandom rather than SecureRandom. Is this safe, and why the different choice from OTP generation?

### Answer summary

ThreadLocalRandom vs SecureRandom 的選擇：ApplicationId.generate() 使用 ThreadLocalRandom.current().nextInt(1000, 10000) 生成 4 位隨機後綴（1000–9999），加上 14 位時間戳（yyyyMMddHHmmss）。OtpAppService.generateOtpCode() 使用 SecureRandom 生成 6 位 OTP。差異的合理性：ApplicationId 是業務識別碼（APP-20260614162447-7823），目的是「在同一時間戳內產生不同的 applicationId 避免衝突」，不是密碼學用途；攻擊者猜出 applicationId 的後 4 位也只能查詢已知的 applicationId（且目前 GET /api/v1/applications/{id} 是 permitAll()）。OTP 的用途完全不同：若可預測，攻擊者可以「猜」OTP 繞過身份驗證，必須密碼學安全。ThreadLocalRandom 的好處：高性能（每個 thread 有獨立的 state，無 contention），比 SecureRandom 快幾個數量級，適合高頻 ID 生成。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationId.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/application/otp/service/OtpAppService.java`

### Related classes

- `ApplicationId.generate()`
- `OtpAppService`

### Related technologies

- DDD

---

## Q252 (from 300)

### Question

MobileNumber validates with the pattern ^09\d{8}$. What does this enforce, and what real phone numbers would it reject?

### Answer summary

^09\d{8}$ 強制規則：以 09 開頭（台灣手機號碼前綴）+ 後 8 位數字，共 10 位。合法範例：0912345678、0987654321、0900000000。會被拒絕的情況：(1) 固網電話（02-12345678，04-12345678）；(2) 國碼前綴（+886-912345678，886912345678）；(3) 有連字符的格式（0912-345-678）；(4) 國際手機號碼（非台灣）。業務範圍：TLBank 是台灣的銀行 platform，僅限台灣手機號碼是合理的業務決定。驗證在 MobileNumber value object 的 compact constructor 中執行（throw new IllegalArgumentException），在 domain 層就拒絕，不依賴 controller 的 @Valid。ApplicantRequest.mobile 是 @NotBlank String，但 String → MobileNumber 的轉換（在 ApplicationAppService.toApplicant() 中呼叫 MobileNumber.of(request.mobile())）才做 pattern 驗證。這意味著 controller 只做 @NotBlank，實際 format 驗證在 application service 轉換時執行，若失敗拋 IllegalArgumentException，需要 GlobalExceptionHandler 處理。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/MobileNumber.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java`

### Related classes

- `ApplicationAppService.toApplicant() (private)`
- `MobileNumber.of()`

### Related technologies

- Validation/Exceptions

---

## Q254 (from 300)

### Question

Applicant is a record with a field of type MobileNumber (itself a record). What is the advantage of composing domain value objects this way?

### Answer summary

Value object 組合的優點：Applicant 的 mobile 欄位是 MobileNumber 型別（而非 String），email 欄位是 Email 型別（而非 String）。優點：(1) Type-safety composition：在任何接受 Applicant 的方法中，applicant.mobile() 永遠是一個已驗證的台灣手機號碼，不可能是 "invalid"；若是 String 欄位，每個接受 Applicant.mobile 的 caller 都需要自己驗證；(2) 業務行為封裝：MobileNumber.masked() 方法可以在任何持有 MobileNumber 的地方呼叫（applicant.mobile().masked()），不需要在 ApplicationAppService 中重複 MaskingUtil.maskMobile(applicant.mobile())；(3) 語意明確：applicant.mobile() 的型別簽名就說明「這是一個有效的台灣手機號」，比 applicant.mobile() 返回 String 需要讀文件才知道是否已驗證；(4) Testability：ApplicationTest.sampleApplicant() 用 MobileNumber.of("0912345678") 建立，測試建立時就驗證，不需要額外 assert。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Applicant.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/MobileNumber.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Email.java`

### Related classes

- `MobileNumber.masked()`

### Related technologies

- DDD

---

## Q256 (from 300)

### Question

ApplicationId.generate() is called inside ApplicationAppService.createApplication(), not inside the Application aggregate. Is this the right placement?

### Answer summary

ID 生成位置的分析：在 ApplicationAppService.createApplication() 中：ApplicationId id = ApplicationId.generate()（或 ApplicationId.generate() 直接作為 Application.builder().applicationId(ApplicationId.generate()).build() 的參數）。為什麼在 service 而非在 aggregate factory：(1) Application.builder() 是 Lombok builder，不是工廠方法，呼叫 ApplicationAppService 的職責就是 orchestrate「建立一個 Application」這個 use case，生成 ID 是 orchestration 的一部分；(2) 若 ID 生成在 Application static factory 方法（如 Application.create(Applicant, CardProductId)），aggregate 自帶 ThreadLocalRandom 依賴，這在某些 DDD 純粹主義觀點中屬於 aggregate 知道太多 infrastructure concern（ID 生成策略）；

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationId.java`

### Related classes

- `ApplicationAppService.createApplication()`
- `ApplicationId.generate()`

### Related technologies

- DDD

---

## Q257 (from 300)

### Question

WorkflowHistory has a fromStatus field that could be null for the very first transition from INIT. Is this correctly modeled?

### Answer summary

fromStatus 為 null 的情況分析：Application 的第一次狀態轉換是 verifyOtp() → INIT → OTP_VERIFIED，此時 fromStatus = INIT（不是 null）。實際上每次呼叫 transitionTo(next, operator, remark) 都有 fromStatus = this.status（當前狀態），而 Application 在 builder 中設 status(ApplicationStatus.INIT)，所以第一次轉換的 fromStatus 是 INIT，不是 null。但資料庫 schema（V3__create_applications.sql 中 workflow_histories.from_status VARCHAR(30)，沒有 NOT NULL）允許 null。若 Application 建立後立刻被 load 然後 save() 而沒有狀態轉換，workflowHistories 是空的，不會有 null fromStatus 的 row。WorkflowHistoryEntity.fromStatus 是 @Enumerated(EnumType.STRING) 的 ApplicationStatus 型別，若資料庫中存 null，Hibernate 會讀出 null，但 WorkflowHistory.fromStatus 欄位是 nullable（@Getter + @Builder，Lombok 不加 non-null）。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/WorkflowHistory.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/WorkflowHistoryEntity.java`
- `sp2-springboot/src/main/resources/db/migration/V3__create_applications.sql`

### Related classes

- `Application.transitionTo() (private)`
- `WorkflowHistoryEntity`

### Related technologies

- JPA/Hibernate
- GitHub Actions

---

## Q258 (from 300)

### Question

WorkflowHistoryEntity has a @ManyToOne(fetch = FetchType.LAZY) back-reference to ApplicationEntity. What JPA issue can arise from this?

### Answer summary

@ManyToOne(fetch = LAZY) 的 JPA 問題：WorkflowHistoryEntity 有 @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "application_id") ApplicationEntity application，即每個 history 記錄都有一個 lazy reference 回到其所屬的 Application entity。當 ApplicationRepositoryImpl.toDomain() 從 ApplicationEntity.workflowHistories 取 list 並轉換：迭代 workflowHistoryEntity 時，若 code 嘗試存取 historyEntity.getApplication()（即反向導航回 ApplicationEntity），就會觸發 lazy load，在 session 存活時沒問題，但在 session 關閉後觸發 LazyInitializationException。目前 ApplicationRepositoryImpl.toDomain() 只從 history entity 取 fromStatus、toStatus、operator、comment、actionAt，不存取 application 欄位，所以沒有問題。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/WorkflowHistoryEntity.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationRepositoryImpl.java`

### Related classes

- `WorkflowHistoryEntity`
- `ApplicationRepositoryImpl.toDomain() (private)`

### Related technologies

- JPA/Hibernate
- GitHub Actions

---

## Q259 (from 300)

### Question

UserEntity uses @ElementCollection(fetch = FetchType.EAGER) for roles, while ApplicationEntity uses @OneToMany(fetch = FetchType.LAZY). Explain the different design choices.

### Answer summary

@ElementCollection EAGER vs @OneToMany LAZY：UserEntity.roles（Set<String>）使用 @ElementCollection(fetch = FetchType.EAGER)：(1) 角色集合很小（通常 1-3 個 role），EAGER load 不增加顯著 overhead；(2) 每次 Spring Security loadUserByUsername() 都需要 roles（UserDetailsServiceImpl 立即呼叫 user.getRoles()），若是 LAZY，就要確保在 session 存活時存取，使用 EAGER 更安全；(3) @ElementCollection 比 @OneToMany 更簡單（role 是 value，不是 entity，不需要自己的 ID）。ApplicationEntity.workflowHistories、.documents（@OneToMany LAZY）：(1) History 和 documents 可能有幾十筆，EAGER load 會讓每次查詢 Application 都 JOIN 大量子集合；(2) 許多查詢（如 list 申請、status 查詢）不需要 history，LAZY 可以避免不必要的 DB I/O；(3) Mapping code（toDomain()）在 transaction 內存取 lazy collection，安全。核心原則：EAGER 適合小型、始終需要的集合；LAZY 適合大型、按需存取的集合。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/user/UserEntity.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationEntity.java`

### Related classes

- `UserEntity`
- `ApplicationEntity`

### Related technologies

- JPA/Hibernate

---

## Q260 (from 300)

### Question

UserEntity uses a String userId as @Id (the business key USR-xxxxxxxx) rather than a surrogate BIGINT. Why is this different from other entities like ApplicationEntity?

### Answer summary

UserEntity 使用 String 作為 @Id 的特殊性：其他 entity（ApplicationEntity、OtpRecordEntity、WorkflowHistoryEntity）都使用 BIGINT IDENTITY surrogate key 作為 @Id，並有獨立的 business key（如 applicationNo）。UserEntity 的 @Id 是 String userId（格式 USR-xxxxxxxx），這讓 @Id 直接是業務 key。影響：(1) Hibernate ID 生成：@GeneratedValue 不適用於 String @Id（預設 SEQUENCE/IDENTITY 是 numeric），UserId.generate() 在 application code 中生成，再 set 到 entity，不依賴 DB 生成；(2) FK 引用：WorkflowHistoryEntity 的 @JoinColumn(name = "action_by") 和其他引用 users.id 的 FK——users table 有兩個 ID：業務 user_id VARCHAR(50) 和內部 id BIGINT (IDENTITY)。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/user/UserEntity.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/user/UserId.java`

### Related classes

- `UserEntity`

### Related technologies

- JPA/Hibernate

---

## Q261 (from 300)

### Question

LoginResponseMode.prefersJson() defaults to true when the Accept header is null, blank, or */*. Why is JSON the safer default?

### Answer summary

JSON 為預設的理由：HTTP 的 Accept: */* 表示「任何格式都可以接受」，但實際上不同 client 的 behavior：(1) curl：預設發送 Accept: */*，期望 JSON 回應（REST API 測試場景）；(2) Swagger UI / Postman：發送 Accept: application/json，明確要 JSON；(3) Browser form submit：Accept: text/html,application/xhtml+xml,...，明確包含 text/html——這是 browser 的 default，會進入 redirect path；(4) Spring Boot test 的 MockMvc：預設 Accept: */*，SecurityIntegrationTest 需要 JSON response，prefersJson() 返回 true 確保測試可以 jsonPath("$.success") assert。若默認 redirect（false）：curl 用戶 login 會被重定向到 /admin/users（HTML page），不是 JSON，breaking change for API clients。JSON 是更安全的 default 因為：大多數非 browser client 期望 JSON，redirect 對它們沒有意義（HTTP client 可能不 follow redirect）。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/security/util/LoginResponseMode.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/security/handler/LoginSuccessHandler.java`

### Related classes

- `LoginResponseMode.prefersJson()`

### Related technologies

- Spring Security

---

## Q266 (from 300)

### Question

OtpPurpose is a single-value enum with only APPLICATION_VERIFICATION. Why use an enum for a single value?

### Answer summary

Single-value enum 的設計意圖：OtpPurpose 目前只有 APPLICATION_VERIFICATION 一個值，但定義為 enum 而非 boolean flag 或 String constant。理由：(1) 擴展性：未來可能加入 PASSWORD_RESET（用戶忘記密碼）、ACCOUNT_BINDING（綁定新手機）、HIGH_VALUE_TRANSACTION（大額交易確認），enum 比 String 更安全（compile-time exhaustiveness check）；(2) 型別安全：OtpRecord.purpose（OtpPurpose）和 SendOtpCommand.purpose（OtpPurpose）型別明確，不是 String "APPLICATION_VERIFICATION"，防止 typo；(3) DB 儲存：otp_records.purpose VARCHAR(30) 儲存 "APPLICATION_VERIFICATION"，未來加新 value 只需加 enum constant + 不需要 DB migration（只要 VARCHAR 夠長）；(4) 文件化：enum 是 self-documenting，看到 OtpPurpose 就知道這個系統 OTP 有哪些用途，比 String constant 更 discoverables。Single-value enum 是常見的「設計為未來而非現在」的技術，符合 YAGNI 和 Open-Closed 的平衡。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/domain/otp/OtpPurpose.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/otp/OtpRecord.java`

### Related classes

- `OtpPurpose`

### Related technologies

- DDD

---

## Q267 (from 300)

### Question

ApproveCaseCommand is a record with reviewCaseId, remark, and operator. Who sets the operator field and how is it secured?

### Answer summary

operator 欄位的設置路徑：ReviewApiController.approveCase() 收到 HTTP request 後，從 Spring Security context 取出當前 authenticated user：SecurityContextHolder.getContext().getAuthentication().getName() 或 @AuthenticationPrincipal AuthenticatedUser user，得到 username。Controller 建立 ApproveCaseCommand(reviewCaseId, request.remark(), username)，username 就是 operator。安全性：/api/v1/review/** 需要 hasAnyRole("REVIEWER", "ADMIN")（SecurityConfig 的 URL 規則 + method-level @PreAuthorize），未登入的 request 不能到達 approveCase() endpoint；登入後的 username 來自 Spring Security（已被驗證的 UserDetails.getUsername()），不是來自 request body（client 不能偽造 operator）。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/application/review/service/ApproveCaseCommand.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/v1/ReviewApiController.java`

### Related classes

- `SecurityConfig.securityFilterChain()`
- `SecurityConfig.passwordEncoder()`
- `LoginSuccessHandler.onAuthenticationSuccess()`

### Related technologies

- Spring Security

---

## Q268 (from 300)

### Question

RedisIdempotencyStore is activated by @ConditionalOnProperty(name = "tlbank.idempotency.store", havingValue = "redis"). What happens when this property is absent — which IdempotencyStore bean is active?

### Answer summary

Bean 啟動邏輯分析：RedisIdempotencyStore：@ConditionalOnProperty(name = "tlbank.idempotency.store", havingValue = "redis")，沒有 matchIfMissing = true，所以若 property 不存在 → bean 不啟動。InMemoryIdempotencyStore（或 InMemoryIdempotencyStore）：需要查看其 @Conditional 設定。若 in-memory store 有 @ConditionalOnProperty(..., matchIfMissing = true)，則 property 不存在時 in-memory store 是 default。application-dev.yml 設定：tlbank.idempotency.store: redis，表示 dev 環境啟用 Redis store（需要 Redis 在 localhost:6379 可用）。若沒有這個 property（如純測試環境沒有設定），in-memory store 生效，IdempotencyService 使用 in-memory 實作。這個設計讓：需要 Redis 的環境明確 opt-in（設定 property）；不需要 Redis 的環境（如純 unit test）自動使用 in-memory，不需要啟動 Redis container。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/idempotency/RedisIdempotencyStore.java`
- `sp2-springboot/src/main/resources/application-dev.yml`

### Related classes

- `IdempotencyService.execute()`
- `IdempotencyStore.tryAcquireLock()`
- `IdempotencyStore.releaseLock()`
- `RedisIdempotencyStore`
- `InMemoryIdempotencyStore`

### Related technologies

- Redis/Idempotency

---

## Q270 (from 300)

### Question

User.roles is a Set<Role> where Role is an enum, but UserEntity.roles is Set<String>. How is the mapping handled, and what is the risk of this gap?

### Answer summary

Domain/Infrastructure 的 roles 型別 gap：Domain User.roles（Set<Role>）：Role 是 enum（ROLE_ADMIN、ROLE_REVIEWER、ROLE_USER）。Infrastructure UserEntity.roles（Set<String>）：@ElementCollection + @Column(name = "role") 儲存 String（如 "ADMIN"、"REVIEWER"、"APPLICANT"）。Mapping 路徑：UserDetailsServiceImpl.loadUserByUsername() 用 UserEntity.getRoles()（Set<String>）傳給 toSpringRole(String role) 做 Spring authority 轉換，不走 User domain aggregate。若有 UserRepository → UserRepositoryImpl.toDomain() 將 UserEntity.roles（Set<String>）轉成 User.roles（Set<Role>）：需要 Role.valueOf(roleString) 或 switch 轉換，若 DB 中有 "INVALID_ROLE" 字串，Role.valueOf() 拋 IllegalArgumentException。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/domain/user/User.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/user/Role.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/user/UserEntity.java`

### Related classes

- `User`
- `UserEntity`
- `UserDetailsServiceImpl.toSpringRole() (private)`

### Related technologies

- JPA/Hibernate
- Hexagonal

---

## Q271 (from 300)

### Question

What is the purpose of the User.updateLastLoginAt() method on the domain aggregate, and does it violate any DDD principle?

### Answer summary

updateLastLoginAt() 的存在理由：LoginSuccessHandler.onAuthenticationSuccess() 呼叫 userJpaRepository.findByUsername(username) 取 UserEntity，然後直接呼叫 user.updateLastLoginAt(now)，再 userJpaRepository.save(user)。這裡的 user 是 UserEntity，不是 domain User aggregate。User domain aggregate 也有 lastLoginAt（@Getter）但 LoginSuccessHandler 沒有透過 domain User aggregate 來更新——它直接操作 JPA entity。分析：在 LoginSuccessHandler 中，更新 lastLoginAt 發生在 Spring Security filter chain 中，UserAppService 沒有對應的 updateLastLogin(username) use case，所以沒有透過 domain aggregate 路徑。若嚴格遵守 DDD，應該有 UserAppService.recordLogin(userId) → user.recordLastLoginAt(now) → userRepository.save(user) 的完整路徑。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/domain/user/User.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/security/handler/LoginSuccessHandler.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/user/UserEntity.java`

### Related classes

- `UserEntity.updateLastLoginAt()`
- `User`

### Related technologies

- DDD

---

## Q272 (from 300)

### Question

The OtpAppService.sendOtp() method cancels any existing PENDING OTP and creates a new one in a single @Transactional. What is the transaction isolation implication?

### Answer summary

Transaction isolation 的影響：sendOtp() 的 @Transactional 預設 isolation level 是 READ_COMMITTED（Spring 的 default，繼承 DB driver 設定；SQL Server 的 default 也是 READ_COMMITTED）。操作序列：findLatestPendingByMobile() → cancel existing → save(existing) → create new → save(newOtp)——這些操作在同一個 transaction T1 中。若另一個 transaction T2 在 T1 commit 前也做 findLatestPendingByMobile()：在 READ_COMMITTED 下，T2 看不到 T1 尚未 commit 的 cancel 操作（T1 的 UPDATE 是 uncommitted），T2 也找到「原始 PENDING OTP」並嘗試 cancel + create。這正是前面提到的 race condition（Q137）。解決方式：升高 isolation level 到 SERIALIZABLE（資源成本高）；或用 pessimistic lock：SELECT ... FOR UPDATE NOWAIT 讓第一個 transaction lock 住 OTP record，第二個 transaction 立即失敗；或 Redis distributed lock。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/application/otp/service/OtpAppService.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/otp/OtpJpaRepository.java`

### Related classes

- `OtpAppService.sendOtp()`

### Related technologies

- Transactions

---

## Q276 (from 300)

### Question

The ApplicationSummaryResponse and ApplicationDetailResponse are in the application.application.service package rather than application.dto. Is this placement consistent with the rest of the codebase?

### Answer summary

Response DTO 位置的一致性分析：CreateApplicationRequest、CancelApplicationRequest 等 request DTO 在 application.dto.request；DocumentUploadResponse 等 response DTO 在 application.dto.response。但 ApplicationSummaryResponse、ApplicationDetailResponse、WorkflowHistoryResponse、MaskedApplicantResponse 在 application.application.service（與 ApplicationAppService 同一個 package）。可能的設計意圖：讓 response 類別和生產它們的 service 在同一個 package（高內聚），減少 cross-package import；ApplicationSummaryResponse 只由 ApplicationAppService 使用，放在 service package 減少 public API surface。不一致的影響：若其他 service 也需要 ApplicationSummaryResponse（如 ReviewAppService 在 summary view 中使用），就需要跨 package import，這個設計的 cohesion 假設就失效了。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationSummaryResponse.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/application/dto/response/DocumentUploadResponse.java`

### Related classes

- Documentation-level question

### Related technologies

- REST/OpenAPI
- Hexagonal

---

## Q281 (from 300)

### Question

How would you explain the difference between @Transactional on a Spring service method and @Transactional in an @Async context, using TLBank's AuditLogWriter as an example?

### Answer summary

兩種 @Transactional 上下文的差異（AuditLogWriter 為例）：正常同步 @Transactional（ApplicationAppService.createApplication()）：Spring AOP proxy 攔截方法呼叫，在呼叫前 open transaction（從 DataSource connection pool 取 connection），方法結束後 commit/rollback。@Async + PROPAGATION_REQUIRES_NEW（AuditLogWriter.saveAsync()）：saveAsync() 被標記 @Async，Spring 在一個 thread pool thread 上執行它；在 async thread 上，沒有 caller 的 transaction（不同 thread 沒有 transaction propagation）；TransactionTemplate(transactionManager) 的 PROPAGATION_REQUIRES_NEW 在 async thread 上 explicitly begin a new transaction，執行 auditLogRepository.save(auditLog)，commit，完成。關鍵差異：(1) 同步 @Transactional：transaction 附著在 calling thread；

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditLogWriter.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java`

### Related classes

- `AuditLogWriter.saveAsync()`
- `ApplicationAppService.createApplication()`

### Related technologies

- Transactions
- Logging/MDC

---

## Q284 (from 300)

### Question

CacheManagementService accesses CachedCardProductRepository directly rather than through the CardProductRepository port. Is this a Clean Architecture violation?

### Answer summary

依賴分析：CacheManagementService 注入 CachedCardProductRepository（具體 infrastructure class），而非 CardProductRepository（domain port interface）。Clean Architecture 的 violation 判斷：CacheManagementService 在 application.cache.service package，屬於 application layer；CachedCardProductRepository 在 infrastructure.cache package，屬於 infrastructure layer。Application layer 直接依賴 infrastructure class（不是 port interface）確實是違反依賴方向的 leak。理由分析：CacheManagementService.refreshProducts() 呼叫 cachedCardProductRepository.refreshCache()，這個 refreshCache() 方法是 CachedCardProductRepository 的特有方法（不在 CardProductRepository port 中），沒有 port 可以依賴。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/application/cache/service/CacheManagementService.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/cache/CachedCardProductRepository.java`

### Related classes

- `CachedCardProductRepository.refreshCache()`

### Related technologies

- Hexagonal

---

## Q288 (from 300)

### Question

ApplicationAppService has 7 public methods. What cohesion principle does this suggest, and when should it be split?

### Answer summary

Cohesion 分析：7 public methods：createApplication()、getApplication()、uploadDocuments()、submitApplication()、cancelApplication()、findAllEnabledProducts()、toMaskedApplicant()（private helper）。這些方法都屬於「Credit Card Application lifecycle」這個同一個 use case 範圍，沒有明顯的 sub-domain 混淆。findAllEnabledProducts() 稍微偏離（是 product catalog，不是 application lifecycle），但從 applicant 的角度（申請時需要查詢可用產品），放在 ApplicationAppService 是合理的使用者旅程視角。Single Responsibility Principle：若 7 個方法都圍繞同一個 business process（申請的建立、進展、查詢），責任是 cohesive 的。分拆的 trigger：(1) 某個方法的 dependency 集合（注入的 collaborator）和其他方法完全不重疊；(2) 某些方法的 change frequency 明顯不同（product catalog 很少改，application flow 常改）；(3) 某個 use case 有獨立的 team 負責（在 TLBank 單人 portfolio 場景，不適用）。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java`

### Related classes

- Documentation-level question

### Related technologies

- Hexagonal

---

## Q300 (from 300)

### Question

GlobalExceptionHandler maps domain and validation failures to ApiResponse. How are WorkflowException and validation errors represented?

### Answer summary

GlobalExceptionHandler 位於 presentation.api.advice，將例外轉成統一 ApiResponse 與 HTTP 狀態。工作流非法轉換走 WorkflowException；Bean Validation 失敗則回傳欄位錯誤資訊。錯誤碼集中於 ErrorCode，避免控制器各自組裝錯誤格式。

### Related files

- `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/advice/GlobalExceptionHandler.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/common/exception/ErrorCode.java`
- `sp2-springboot/src/main/java/com/tlbank/lending/common/response/ApiResponse.java`

### Related classes

- `GlobalExceptionHandler`
- `ErrorCode`
- `ApiResponse`

### Related technologies

- REST/OpenAPI
- GitHub Actions
- Validation/Exceptions

---
