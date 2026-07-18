# M29 — Debugging / 除錯

## Why This Module Matters

### English

Correlation via MDC and follow-the-owner paths for OTP and idempotency failures.

### 中文

以 MDC 關聯，並沿擁有者路徑排查 OTP 與冪等失敗。

## Files to Open First

- `sp2-springboot/src/main/java/com/tlbank/lending/security/filter/MdcLoggingFilter.java` — requestId MDC.  
  `sp2-springboot/src/main/java/com/tlbank/lending/security/filter/MdcLoggingFilter.java` — requestId MDC。
- `sp2-springboot/src/main/java/com/tlbank/lending/application/idempotency/IdempotencyService.java` — 409 diagnosis.  
  `sp2-springboot/src/main/java/com/tlbank/lending/application/idempotency/IdempotencyService.java` — 409 診斷。
- `sp2-springboot/src/main/java/com/tlbank/lending/application/otp/service/OtpAppService.java` — OTP verify path.  
  `sp2-springboot/src/main/java/com/tlbank/lending/application/otp/service/OtpAppService.java` — OTP 驗證路徑。
- `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/advice/GlobalExceptionHandler.java` — HTTP mapping.  
  `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/advice/GlobalExceptionHandler.java` — HTTP 映射。

## Primary Question

### English

Create returns 409. How to debug?

### 中文

建立回傳 409。如何除錯？

## Suggested Answer

### English

Read ErrorCode: IDEMPOTENCY_KEY_CONFLICT versus IDEMPOTENCY_KEY_IN_PROGRESS. Inspect IdempotencyService hash compare and lock acquisition. Confirm store mode redis versus memory. Use MDC requestId to correlate logs.

### 中文

讀取 ErrorCode：IDEMPOTENCY_KEY_CONFLICT 或 IDEMPOTENCY_KEY_IN_PROGRESS。檢查 IdempotencyService 的 hash 比對與取鎖。確認 store 為 redis 或 memory。以 MDC requestId 關聯日誌。

## Follow-up Question

### English

OTP verify always mismatches. Steps?

### 中文

OTP 驗證總是不符。步驟？

## Follow-up Answer

### English

Confirm latest PENDING OtpRecord, max_retry parameter, whether cleanup marked expired, and that SMS is mock-only so carrier traces will not exist.

### 中文

確認最新 PENDING OtpRecord、max_retry 參數、清理排程是否已標記過期，以及簡訊僅 mock、不存在電信商追蹤。

## Interview Tip

### English

Why asked: structured debugging.
Answer first: ErrorCode then owning service.
Keywords: MDC, lockKey, OtpRecord.verify.
Follow-ups: missing audit row async path.

### 中文

提問原因：結構化除錯。
先答：ErrorCode 再找擁有服務。
關鍵詞：MDC、lockKey、OtpRecord.verify。
追問：稽核缺列的非同步路徑。

## Open Book Navigation

- [Idempotency](../open-book/topics/07-redis-idempotency.md)  
  [冪等](../open-book/topics/07-redis-idempotency.md)
- [Request lifecycle](../open-book/topics/02-request-lifecycle.md)  
  [請求生命週期](../open-book/topics/02-request-lifecycle.md)
