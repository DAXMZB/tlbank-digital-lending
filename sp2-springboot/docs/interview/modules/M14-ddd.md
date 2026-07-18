# M14 — DDD / 領域驅動設計

## Why This Module Matters

### English

DDD-lite aggregates and value objects per ADR 0002—not event sourcing.

### 中文

依 ADR 0002 的 DDD-lite 聚合與值物件—非事件溯源實作。

## Files to Open First

- `sp2-springboot/docs/decisions/0002-use-ddd.md` — DDD-lite ADR.  
  `sp2-springboot/docs/decisions/0002-use-ddd.md` — DDD-lite ADR。
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java` — Application aggregate.  
  `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java` — Application 聚合。
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/review/ReviewCase.java` — Review aggregate.  
  `sp2-springboot/src/main/java/com/tlbank/lending/domain/review/ReviewCase.java` — Review 聚合。
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/otp/OtpRecord.java` — OTP aggregate.  
  `sp2-springboot/src/main/java/com/tlbank/lending/domain/otp/OtpRecord.java` — OTP 聚合。

## Primary Question

### English

Which aggregates exist and what invariants do they protect?

### 中文

有哪些聚合？各自保護什麼不變量？

## Suggested Answer

### English

Application protects lifecycle transitions and required documents on submit. ReviewCase protects review assignment and approve/reject. OtpRecord protects expiry, retry ceiling, and code match. Application services orchestrate across aggregates.

### 中文

Application 保護生命週期轉移與提交時必要文件。ReviewCase 保護分派與核准／拒絕。OtpRecord 保護過期、重試上限與碼比對。Application service 跨聚合編排。

## Follow-up Question

### English

Is this DDD with outbox and CQRS?

### 中文

是否為含 outbox 與 CQRS 的 DDD？

## Follow-up Answer

### English

No. ADR 0002 describes DDD-lite inside a modular monolith. There is no event-sourcing store or CQRS read model in the current code.

### 中文

否。ADR 0002 描述模組化單體內的 DDD-lite。現況無事件溯源儲存或 CQRS 讀模型。

## Interview Tip

### English

Why asked: vocabulary without overclaim.
Answer first: three aggregates + ADR 0002.
Keywords: invariant, value object, application service.
Follow-ups: anemic vs rich model, ReviewAppService dual write.

### 中文

提問原因：詞彙正確且不超宣稱。
先答：三個聚合 + ADR 0002。
關鍵詞：不變量、值物件、application service。
追問：貧血與充血模型、ReviewAppService 雙寫。

## Open Book Navigation

- [Domain and workflow](../open-book/topics/04-domain-and-workflow.md)  
  [領域與流程](../open-book/topics/04-domain-and-workflow.md)
- [ReviewCase source-map](../open-book/source-map/domain/ReviewCase.md)  
  [ReviewCase 類別地圖](../open-book/source-map/domain/ReviewCase.md)
- [OtpRecord source-map](../open-book/source-map/domain/OtpRecord.md)  
  [OtpRecord 類別地圖](../open-book/source-map/domain/OtpRecord.md)
