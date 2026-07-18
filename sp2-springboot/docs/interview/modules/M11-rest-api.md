# M11 — REST API / REST API

## Why This Module Matters

### English

HTTP adapters must stay thin. Interviewers check whether workflow rules leaked into controllers.

### 中文

HTTP 適配器須保持精簡。面試官檢查工作流規則是否洩漏進 controller。

## Files to Open First

- `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/v1/ApplicationApiController.java` — Applications REST edge.  
  `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/v1/ApplicationApiController.java` — 申請 REST 入口。
- `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/advice/GlobalExceptionHandler.java` — Exception mapping.  
  `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/advice/GlobalExceptionHandler.java` — 例外映射。
- `sp2-springboot/src/main/java/com/tlbank/lending/common/response/ApiResponse.java` — Response envelope.  
  `sp2-springboot/src/main/java/com/tlbank/lending/common/response/ApiResponse.java` — 回應包絡。

## Primary Question

### English

What does ApplicationApiController do versus ApplicationAppService?

### 中文

ApplicationApiController 與 ApplicationAppService 分工為何？

## Suggested Answer

### English

The controller maps HTTP: optional Idempotency-Key on create, get, multipart upload, submit, cancel. It returns ApiResponse. ApplicationAppService owns use-case orchestration and @Transactional. Domain owns status transitions.

### 中文

Controller 負責 HTTP 對應：建立時可選 Idempotency-Key、查詢、multipart 上傳、提交、取消，並回傳 ApiResponse。ApplicationAppService 負責用例編排與 @Transactional。領域層負責狀態轉移。

## Follow-up Question

### English

How are errors returned?

### 中文

錯誤如何回傳？

## Follow-up Answer

### English

GlobalExceptionHandler maps BusinessException, WorkflowException, validation, and security exceptions to ApiResponse.error with appropriate HTTP status.

### 中文

GlobalExceptionHandler 將 BusinessException、WorkflowException、校驗與安全性例外映射為 ApiResponse.error 與對應 HTTP 狀態。

## Interview Tip

### English

Why asked: layering at the edge.
Answer first: thin controller.
Keywords: Idempotency-Key, ApiResponse, GlobalExceptionHandler.
Follow-ups: permitAll, OpenAPI paths.

### 中文

提問原因：邊界分層。
先答：精簡 controller。
關鍵詞：Idempotency-Key、ApiResponse、GlobalExceptionHandler。
追問：permitAll、OpenAPI 路徑。

## Open Book Navigation

- [Request lifecycle](../open-book/topics/02-request-lifecycle.md)  
  [請求生命週期](../open-book/topics/02-request-lifecycle.md)
- [ApplicationApiController source-map](../open-book/source-map/presentation/ApplicationApiController.md)  
  [ApplicationApiController 類別地圖](../open-book/source-map/presentation/ApplicationApiController.md)
- [GlobalExceptionHandler source-map](../open-book/source-map/presentation/GlobalExceptionHandler.md)  
  [GlobalExceptionHandler 類別地圖](../open-book/source-map/presentation/GlobalExceptionHandler.md)
