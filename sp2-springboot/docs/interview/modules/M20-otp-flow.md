# M20 — OTP Flow / OTP 流程

## Why This Module Matters

### English

OTP domain rules, mock notification delivery, and cleanup scheduling.

### 中文

OTP 領域規則、mock 通知投遞與清理排程。

## Files to Open First

- `sp2-springboot/src/main/java/com/tlbank/lending/application/otp/service/OtpAppService.java` — Send/verify use cases.  
  `sp2-springboot/src/main/java/com/tlbank/lending/application/otp/service/OtpAppService.java` — 發送／驗證用例。
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/otp/OtpRecord.java` — Verify/expire/cancel rules.  
  `sp2-springboot/src/main/java/com/tlbank/lending/domain/otp/OtpRecord.java` — 驗證／過期／取消規則。
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/scheduler/OtpCleanupScheduler.java` — Batch expiry.  
  `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/scheduler/OtpCleanupScheduler.java` — 批次過期。
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/notification/MockSmsSender.java` — Mock SMS adapter.  
  `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/notification/MockSmsSender.java` — Mock 簡訊適配器。

## Primary Question

### English

Walk through sendOtp and verifyOtp.

### 中文

說明 sendOtp 與 verifyOtp。

## Suggested Answer

### English

sendOtp cancels prior PENDING OTP, reads OTP expire/retry parameters, persists OtpRecord, and notifies through NotificationService (mock adapters). verifyOtp loads the pending record, runs OtpRecord.verify, and may call Application.verifyOtp when status is INIT.

### 中文

sendOtp 取消既有 PENDING OTP、讀取過期／重試參數、持久化 OtpRecord，並經 NotificationService 通知（mock 適配器）。verifyOtp 載入 pending 記錄、執行 OtpRecord.verify，並在申請為 INIT 時可能呼叫 Application.verifyOtp。

## Follow-up Question

### English

Are SMS messages real?

### 中文

簡訊是否為真實發送？

## Follow-up Answer

### English

No. MockSmsSender and MockEmailSender are active under tlbank.notification.mode=mock (default).

### 中文

否。在 tlbank.notification.mode=mock（預設）下，MockSmsSender 與 MockEmailSender 生效。

## Interview Tip

### English

Why asked: domain OTP + honesty on mocks.
Answer first: OtpRecord rules then mock notify.
Keywords: OTP_MISMATCH, OTP_EXPIRED, cleanup scheduler.
Follow-ups: parameter keys, Redis misconception.

### 中文

提問原因：OTP 領域規則 + mock 誠實度。
先答：OtpRecord 規則，再說明 mock 通知。
關鍵詞：OTP_MISMATCH、OTP_EXPIRED、清理排程。
追問：參數鍵、Redis 誤解。

## Open Book Navigation

- [Domain and workflow](../open-book/topics/04-domain-and-workflow.md)  
  [領域與流程](../open-book/topics/04-domain-and-workflow.md)
- [Events and notifications](../open-book/topics/09-events-and-notifications.md)  
  [事件與通知](../open-book/topics/09-events-and-notifications.md)
- [OtpAppService source-map](../open-book/source-map/application/OtpAppService.md)  
  [OtpAppService 類別地圖](../open-book/source-map/application/OtpAppService.md)
- [OtpRecord source-map](../open-book/source-map/domain/OtpRecord.md)  
  [OtpRecord 類別地圖](../open-book/source-map/domain/OtpRecord.md)
