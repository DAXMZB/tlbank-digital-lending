# M04 — Transaction / 交易

## Why This Module Matters

### English

Placement of @Transactional and isolation of audit writes are frequent deep-dive topics.

### 中文

@Transactional 放置位置與稽核寫入隔離是常見深挖主題。

## Files to Open First

- `sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java` — Service-level @Transactional.  
  `sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java` — 服務層 @Transactional。
- `sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditLogWriter.java` — REQUIRES_NEW inside @Async.  
  `sp2-springboot/src/main/java/com/tlbank/lending/common/audit/AuditLogWriter.java` — @Async 內 REQUIRES_NEW。
- `sp2-springboot/src/main/resources/application.yml` — open-in-view=false.  
  `sp2-springboot/src/main/resources/application.yml` — open-in-view=false。

## Primary Question

### English

Where should @Transactional sit in this codebase?

### 中文

本倉庫中 @Transactional 應置於何處？

## Suggested Answer

### English

On application services such as ApplicationAppService, OtpAppService, and ReviewAppService—not on controllers. Reads often use readOnly=true. spring.jpa.open-in-view is false, so LAZY loading must complete inside the service boundary.

### 中文

置於 ApplicationAppService、OtpAppService、ReviewAppService 等 application service，而非 controller。讀取常設 readOnly=true。spring.jpa.open-in-view 為 false，故 LAZY 載入須在 service 邊界內完成。

## Follow-up Question

### English

Can AuditLogWriter failure roll back a successful submit?

### 中文

AuditLogWriter 失敗能否回滾已成功的 submit？

## Follow-up Answer

### English

Not on the writer path. saveAsync runs @Async with PROPAGATION_REQUIRES_NEW and swallows errors after warn. Business commit and audit durability are intentionally separated.

### 中文

在 writer 路徑上不會。saveAsync 以 @Async 搭配 REQUIRES_NEW，失敗僅 warn。業務提交與稽核耐久性刻意分離。

## Interview Tip

### English

Why asked: transaction literacy.
Answer first: application service boundary.
Keywords: readOnly, open-in-view false, REQUIRES_NEW.
Follow-ups: event listener timing, scheduler @Transactional.

### 中文

提問原因：檢驗交易觀念。
先答：application service 邊界。
關鍵詞：readOnly、open-in-view false、REQUIRES_NEW。
追問：事件監聽時機、排程 @Transactional。

## Open Book Navigation

- [Transactions topic](../open-book/topics/06-transactions.md)  
  [交易主題](../open-book/topics/06-transactions.md)
- [AuditLogWriter source-map](../open-book/source-map/common/AuditLogWriter.md)  
  [AuditLogWriter 類別地圖](../open-book/source-map/common/AuditLogWriter.md)
