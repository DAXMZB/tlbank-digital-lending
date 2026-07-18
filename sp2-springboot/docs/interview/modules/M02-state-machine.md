# M02 — State Machine / 狀態機

## Why This Module Matters

### English

Interviewers use the credit-application lifecycle to test whether invariants live in the domain or in controllers.

### 中文

面試官以信用卡申請生命週期檢驗不變量位於領域層或 controller。

## Files to Open First

- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationStatus.java` — ALLOWED_TRANSITIONS map.  
  `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationStatus.java` — 允許轉移表。
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java` — Status verbs and private transitionTo.  
  `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java` — 狀態動詞與私有 transitionTo。
- `sp2-springboot/src/main/java/com/tlbank/lending/common/exception/WorkflowException.java` — Illegal transition type.  
  `sp2-springboot/src/main/java/com/tlbank/lending/common/exception/WorkflowException.java` — 非法轉移例外。
- `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/advice/GlobalExceptionHandler.java` — Maps WorkflowException to HTTP 409.  
  `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/advice/GlobalExceptionHandler.java` — 將 WorkflowException 映射為 HTTP 409。

## Primary Question

### English

How does an application move from INIT to APPROVED?

### 中文

申請如何自 INIT 到達 APPROVED？

## Suggested Answer

### English

Transitions are gated by ApplicationStatus.ALLOWED_TRANSITIONS. Aggregate methods verifyOtp, uploadDocuments, submit, startReview, then approve or reject call private transitionTo. INIT cannot jump directly to APPROVED. Illegal moves throw WorkflowException, mapped to HTTP 409.

### 中文

轉移由 ApplicationStatus.ALLOWED_TRANSITIONS 管制。聚合方法 verifyOtp、uploadDocuments、submit、startReview，再 approve 或 reject，皆走私有 transitionTo。INIT 不能直接到 APPROVED。非法轉移拋 WorkflowException，映射為 HTTP 409。

## Follow-up Question

### English

Can cancel run after SUBMITTED?

### 中文

SUBMITTED 之後能否 cancel？

## Follow-up Answer

### English

No. cancel is limited to INIT, OTP_VERIFIED, and DOCUMENT_UPLOADED via CANCELLABLE_STATUSES.

### 中文

否。cancel 僅限 INIT、OTP_VERIFIED、DOCUMENT_UPLOADED（CANCELLABLE_STATUSES）。

## Interview Tip

### English

Why asked: domain ownership of invariants.
Answer first: table in ApplicationStatus, verbs on Application.
Keywords: ALLOWED_TRANSITIONS, WorkflowException, 409.
Follow-ups: WorkflowHistory, WorkflowDomainService callers.

### 中文

提問原因：檢驗不變量是否在領域。
先答：表在 ApplicationStatus，動詞在 Application。
關鍵詞：ALLOWED_TRANSITIONS、WorkflowException、409。
追問：WorkflowHistory、WorkflowDomainService 呼叫端。

## Open Book Navigation

- [Domain and workflow topic](../open-book/topics/04-domain-and-workflow.md)  
  [領域與流程主題](../open-book/topics/04-domain-and-workflow.md)
- [ApplicationStatus source-map](../open-book/source-map/domain/ApplicationStatus.md)  
  [ApplicationStatus 類別地圖](../open-book/source-map/domain/ApplicationStatus.md)
- [Application source-map](../open-book/source-map/domain/Application.md)  
  [Application 類別地圖](../open-book/source-map/domain/Application.md)
