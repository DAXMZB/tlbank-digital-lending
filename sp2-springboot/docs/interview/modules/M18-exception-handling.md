# M18 — Exception Handling / 例外處理

## Why This Module Matters

### English

Central ApiResponse mapping in GlobalExceptionHandler.

### 中文

由 GlobalExceptionHandler 集中映射 ApiResponse。

## Files to Open First

- `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/advice/GlobalExceptionHandler.java` — @RestControllerAdvice.  
  `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/advice/GlobalExceptionHandler.java` — @RestControllerAdvice。
- `sp2-springboot/src/main/java/com/tlbank/lending/common/exception/ErrorCode.java` — Machine-readable codes.  
  `sp2-springboot/src/main/java/com/tlbank/lending/common/exception/ErrorCode.java` — 機器可讀錯誤碼。
- `sp2-springboot/src/main/java/com/tlbank/lending/common/exception/WorkflowException.java` — Workflow conflict type.  
  `sp2-springboot/src/main/java/com/tlbank/lending/common/exception/WorkflowException.java` — 工作流衝突類型。

## Primary Question

### English

How does GlobalExceptionHandler choose HTTP status for BusinessException?

### 中文

GlobalExceptionHandler 如何為 BusinessException 選擇 HTTP 狀態？

## Suggested Answer

### English

A switch on ErrorCode maps conflict codes to 409, not-found family to 404, and other business errors to 400. WorkflowException maps to 409 INVALID_WORKFLOW_TRANSITION. Validation is 400; access denied 403; authentication 401; catch-all 500 SYSTEM_ERROR without stack in the body.

### 中文

依 ErrorCode 的 switch：衝突碼 409、找不到資源族 404、其他業務錯誤 400。WorkflowException 為 409 INVALID_WORKFLOW_TRANSITION。校驗 400；授權 403；認證 401；其餘 500 SYSTEM_ERROR，body 不含 stack。

## Follow-up Question

### English

Should services catch BusinessException to build HTTP responses?

### 中文

服務是否應捕捉 BusinessException 以組 HTTP 回應？

## Follow-up Answer

### English

No. Services throw typed exceptions; the advice layer translates to HTTP.

### 中文

否。服務拋出型別化例外；由 advice 層轉成 HTTP。

## Interview Tip

### English

Why asked: consistent API errors.
Answer first: ErrorCode-driven mapping.
Keywords: 409 workflow, SYSTEM_ERROR.
Follow-ups: field validation shape, anonymous actors.

### 中文

提問原因：一致的 API 錯誤。
先答：ErrorCode 驅動映射。
關鍵詞：409 workflow、SYSTEM_ERROR。
追問：欄位校驗形狀、匿名操作者。

## Open Book Navigation

- [Request lifecycle](../open-book/topics/02-request-lifecycle.md)  
  [請求生命週期](../open-book/topics/02-request-lifecycle.md)
- [GlobalExceptionHandler source-map](../open-book/source-map/presentation/GlobalExceptionHandler.md)  
  [GlobalExceptionHandler 類別地圖](../open-book/source-map/presentation/GlobalExceptionHandler.md)
