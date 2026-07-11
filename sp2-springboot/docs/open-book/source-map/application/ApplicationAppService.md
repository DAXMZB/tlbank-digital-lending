# ApplicationAppService

- [Back to Open Book Home](../../README.md)
- [Back to Source Map Index](../README.md)
- Previous Critical Class: [Application](../domain/Application.md)
- Next Critical Class: [ApplicationStatus](../domain/ApplicationStatus.md)
- Related Topics: [topics/README.md](../../topics/README.md) (bodies **Pending** — Phase 3)
- Related Questions: [09-interview-source-map-300.md](../../../handbook/09-interview-source-map-300.md)

---
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

### `ApplicationSummaryResponse createApplication(CreateApplicationRequest request)`

- **Purpose:** Create INIT application
- **Input:** create request
- **Output:** summary
- **Business meaning:** Start application
- **Transaction behavior:** @Transactional
- **Side effects:** Persists new aggregate


### `ApplicationDetailResponse getApplication(String applicationId)`

- **Purpose:** Load detail with masking
- **Input:** application id
- **Output:** detail DTO
- **Transaction behavior:** @Transactional(readOnly = true)
- **Security behavior:** Masks national id / mobile / email in response


### `DocumentUploadResponse uploadDocuments(String applicationId, DocumentType documentType, MultipartFile file)`

- **Purpose:** Validate/store file then domain upload
- **Input:** id, type, multipart
- **Output:** upload DTO
- **Transaction behavior:** @Transactional
- **Security behavior:** @Auditable(DOCUMENT_UPLOAD)
- **Side effects:** Filesystem write + DB save


### `ApplicationSummaryResponse submitApplication(String applicationId)`

- **Purpose:** Submit for review
- **Input:** id
- **Output:** summary
- **Transaction behavior:** @Transactional
- **Security behavior:** @Auditable(APPLICATION_SUBMIT)
- **Side effects:** Save + ApplicationSubmittedEvent


### `ApplicationSummaryResponse cancelApplication(String applicationId, String reason, String operator)`

- **Purpose:** Cancel early-stage application
- **Input:** id, reason, operator
- **Output:** summary
- **Transaction behavior:** @Transactional
- **Security behavior:** @Auditable(APPLICATION_CANCEL)


### `List<CardProductResponse> findAllEnabledProducts()`

- **Purpose:** List sellable products
- **Output:** product DTOs
- **Transaction behavior:** @Transactional(readOnly = true)


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

[`Q030`](../../../handbook/09-interview-source-map-300.md#Q030), [`Q031`](../../../handbook/09-interview-source-map-300.md#Q031), [`Q037`](../../../handbook/09-interview-source-map-300.md#Q037), [`Q094`](../../../handbook/09-interview-source-map-300.md#Q094), [`Q096`](../../../handbook/09-interview-source-map-300.md#Q096), [`Q100`](../../../handbook/09-interview-source-map-300.md#Q100), [`Q106`](../../../handbook/09-interview-source-map-300.md#Q106), [`Q127`](../../../handbook/09-interview-source-map-300.md#Q127), [`Q131`](../../../handbook/09-interview-source-map-300.md#Q131), [`Q151`](../../../handbook/09-interview-source-map-300.md#Q151), [`Q227`](../../../handbook/09-interview-source-map-300.md#Q227), [`Q252`](../../../handbook/09-interview-source-map-300.md#Q252), [`Q256`](../../../handbook/09-interview-source-map-300.md#Q256), [`Q274`](../../../handbook/09-interview-source-map-300.md#Q274), [`Q281`](../../../handbook/09-interview-source-map-300.md#Q281)

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
