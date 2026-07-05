# 03 – Package Structure

## 1. Root Package

Base package: `com.tlbank.lending`

```
com.tlbank.lending
├── TlbankLendingApplication.java        # Spring Boot entry point (@SpringBootApplication)
├── application/                          # Use cases, DTOs, application services
├── common/                                # Cross-cutting: exceptions, response envelope, audit, config, util
├── domain/                                # Aggregates, value objects, domain services, repository ports, events
├── infrastructure/                        # Adapters: JPA, cache, idempotency, notification, report, scheduler, storage, event listeners
├── presentation/                          # REST + Thymeleaf controllers, global exception advice
└── security/                              # Spring Security configuration, handlers, principal model

```

## 2. Full Package Tree

```
application/
├── application/service/        # Credit card application use cases (ApplicationAppService + response DTOs)
├── audit/service/               # Audit & notification log query services
├── cache/service/                # Cache admin use cases
├── dto/request/                  # Shared inbound request DTOs (Create/Cancel Application, CreateUser, SendOtp, VerifyOtp, GenerateReport)
├── dto/response/                 # Shared outbound DTOs (LoginResponse, DocumentUploadResponse)
├── idempotency/                   # IdempotencyService (application-layer coordinator)
├── notification/service/         # NotificationService port + impl
├── otp/service/                   # OTP send/verify use cases
├── parameter/service/             # System parameter read/update use cases
├── report/service/                # Report data aggregation + report use cases
├── review/service/                # Credit review case use cases
└── user/service/                  # Internal user management use cases

common/
├── audit/        # Auditable annotation, AuditAspect, AuditContext, AuditLog entity + repository, masking helpers
├── config/        # JpaConfig, AsyncConfig, SchedulingConfig, SchedulerConfig, SwaggerConfig, StandardApiResponses, CommonConfig
├── entity/         # BaseEntity (created_at/updated_at auditing superclass)
├── exception/      # ErrorCode, BusinessException, WorkflowException
├── response/        # ApiResponse, PageResponse, FieldErrorDetail
└── util/             # MaskingUtil, DateUtil

domain/
├── application/                 # Application aggregate + value objects (Address, Applicant, ApplicationId,
│   │                              ApplicationStatus, CardProductId, DocumentInfo, DocumentType, Email,
│   │                              MobileNumber, WorkflowHistory)
│   └── repository/               # ApplicationRepository port
├── event/                        # Domain events (ApplicationSubmitted/Approved/Rejected/Cancelled, OtpGenerated)
├── otp/                          # OtpRecord aggregate, OtpPurpose, OtpStatus, VerifyResult
│   └── repository/                # OtpRepository port
├── parameter/                    # SystemParameter aggregate + SystemParameterRepository port (same package)
├── product/                      # CardProduct aggregate, CardType, ProductFeature
│   └── repository/                # CardProductRepository port
├── review/                       # ReviewCase aggregate, ReviewCaseId, ReviewRemark, ReviewStatus, ReviewCaseSearchCriteria
│   └── repository/                # ReviewCaseRepository port
├── service/                      # WorkflowDomainService (domain service)
└── user/                         # User aggregate, UserId, Role
    └── repository/                # UserRepository port

infrastructure/
├── cache/             # CacheStore port, CacheEntry, CacheKeys, CacheTtlProvider, InMemoryCacheStore,
│                        CachedCardProductRepository (decorator)
├── event/              # ReviewEventHandler, NotificationEventHandler (@EventListener)
├── idempotency/        # IdempotencyStore port, IdempotencyEntry, InMemoryIdempotencyStore, RedisIdempotencyStore
├── notification/        # EmailSender/SmsSender ports, EmailMessage/SmsMessage, MockEmailSender/MockSmsSender,
│                          NotificationTemplate
├── persistence/
│   ├── application/      # ApplicationEntity, ApplicantEmbeddable, AddressEmbeddable, WorkflowHistoryEntity,
│   │                       ApplicationDocumentEntity, ApplicationJpaRepository, ApplicationRepositoryImpl
│   ├── otp/                # OtpRecordEntity, OtpJpaRepository, OtpRepositoryImpl
│   ├── parameter/           # SystemParameterEntity, SystemParameterJpaRepository, SystemParameterRepositoryImpl
│   ├── product/             # CardProductEntity, ProductFeatureEntity, CardProductJpaRepository, CardProductRepositoryImpl
│   ├── review/               # ReviewCaseEntity, ReviewRemarkEntity, ReviewCaseJpaRepository, ReviewCaseRepositoryImpl
│   └── user/                  # UserEntity, UserJpaRepository, UserRepositoryImpl
├── report/             # ExcelReportGenerator, PdfReportGenerator
├── scheduler/           # OtpCleanupScheduler, CacheRefreshScheduler, DailyStatisticsScheduler
└── storage/             # DocumentStorageService port, LocalDocumentStorageService

presentation/
├── api/
│   ├── advice/           # GlobalExceptionHandler
│   └── v1/                # ApplicationApiController, CardProductApiController, OtpApiController,
│                            ReviewApiController (+ review/ request DTOs), UserManagementApiController,
│                            SystemParameterApiController, CacheManagementApiController, ReportApiController,
│                            SchedulerApiController, AuditLogApiController, NotificationLogApiController
└── web/                  # AuthController, ApplicationWebController, ReviewController, AdminController

security/
├── config/    # SecurityConfig
├── filter/     # MdcLoggingFilter
├── handler/     # LoginSuccessHandler, LoginFailureHandler, LogoutSuccessHandlerImpl,
│                 CustomAuthenticationEntryPoint, CustomAccessDeniedHandler, SessionExpiredStrategy
├── model/       # AuthenticatedUser
├── service/      # UserDetailsServiceImpl
└── util/         # JsonResponseWriter, LoginResponseMode

```

## 3. Naming Conventions Observed

| Suffix / Pattern                       | Meaning                                                        | Example                                            |
| -------------------------------------- | -------------------------------------------------------------- | -------------------------------------------------- |
| `*Id` (record)                         | Self-validating business identifier value object               | `ApplicationId`, `UserId`, `ReviewCaseId`          |
| `*Entity`                              | JPA-mapped persistence model (infrastructure only)             | `ApplicationEntity`, `UserEntity`                  |
| `*Embeddable`                          | JPA `@Embeddable` mapped inside an entity                      | `ApplicantEmbeddable`, `AddressEmbeddable`         |
| `*JpaRepository`                       | Spring Data JPA interface (infrastructure only)                | `ApplicationJpaRepository`                         |
| `*RepositoryImpl`                      | Adapter implementing a domain repository port                  | `ApplicationRepositoryImpl`                        |
| `*Repository` (no suffix, in `domain`) | Repository **port** (interface)                                | `ApplicationRepository`, `OtpRepository`           |
| `*AppService`                          | Application-layer use-case orchestrator                        | `ApplicationAppService`, `OtpAppService`           |
| `*Command`                             | Application-layer input parameter object for a single use case | `SendOtpCommand`, `ApproveCaseCommand`             |
| `*Response`                            | Application-layer output DTO                                   | `ApplicationSummaryResponse`, `UserResponse`       |
| `*Request`                             | Presentation-layer inbound request body                        | `CreateApplicationRequest`, `ApproveReviewRequest` |
| `package-info.java`                    | One-paragraph Javadoc describing the package's responsibility  | present in every package                           |

## 4. Where to Add New Code

| If you are adding...                                | Put it in...                                                                                                                                                 |
| --------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| A new business rule on an existing aggregate        | The aggregate class itself in `domain.<feature>`                                                                                                             |
| A new aggregate-spanning rule                       | A new domain service in `domain.service`                                                                                                                     |
| A new use case (e.g. "resend OTP")                  | A new method on the relevant `*AppService` in `application.<feature>.service`, plus a `*Command`/`*Response` if needed                                       |
| A new REST endpoint                                 | `presentation.api.v1`, calling exactly one application service method                                                                                        |
| A new web page                                      | `presentation.web`, calling the same application services as the REST layer                                                                                  |
| A new persistence need                              | A new JPA entity + `*JpaRepository` + `*RepositoryImpl` in `infrastructure.persistence.<feature>`, implementing the existing or a new domain repository port |
| A new external integration (e.g. real SMS provider) | A new adapter in `infrastructure.notification` implementing `SmsSender`/`EmailSender`, selected via a Spring profile or `@ConditionalOnProperty`             |
| A new error condition                               | A new `ErrorCode` constant + mapping in `GlobalExceptionHandler` (see `10-error-handling.md`)                                                                |
