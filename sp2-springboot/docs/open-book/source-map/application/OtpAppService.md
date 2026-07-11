# OtpAppService

- [Back to Open Book Home](../../README.md)
- [Back to Source Map Index](../README.md)
- Previous Critical Class: [RedisIdempotencyStore](../infrastructure/RedisIdempotencyStore.md)
- Next Critical Class: [ReviewAppService](ReviewAppService.md)
- Related Topics: [topics/](../../topics/README.md)
- Related Questions: [09-interview-source-map-300.md](../../../handbook/09-interview-source-map-300.md)

---

## One-Sentence Summary

Transactional OTP send/verify use cases; updates application to OTP_VERIFIED on successful verify.

## 中文一句話

OTP 發送／驗證用例：參數驅動過期與重試；驗證成功可把申請從 INIT 轉 OTP_VERIFIED。

## Why This Class Exists

Isolate OTP rules and notifications from controllers while coordinating `OtpRecord` + `Application`.

## Responsibilities

- Cancel previous PENDING OTP for mobile on send
- Generate 6-digit code; persist; notify (mock channels underneath)
- Verify code; transition application when INIT

## Runtime Execution Flow

Send:

1. Cancel latest pending OTP for mobile if any.
2. Read expire/retry params from `SystemParameterService`.
3. Save new PENDING `OtpRecord`; put code in `AuditContext`; notify.

Verify:

1. Load latest pending OTP or `OTP_EXPIRED`.
2. `otpRecord.verify(...)`; save.
3. Load application; if INIT → `verifyOtp`; if already OTP_VERIFIED OK; else workflow error.

## Dependencies

### Depends On

- `OtpRepository`, `ApplicationRepository`, `NotificationService`, `SystemParameterService`, `Clock`

### Called By

- OTP API controller
- `OtpAppServiceTest`

### Calls

- Domain `OtpRecord` / `Application` methods; notification port

## Important Public Methods

### `OtpResponse sendOtp(SendOtpCommand command)`

- **Purpose:** Issue new OTP
- **Input:** mobile/purpose/applicationId command
- **Output:** masked mobile, expiry, maxRetry
- **Transaction behavior:** @Transactional
- **Security behavior:** @Auditable(OTP_SEND)
- **Side effects:** DB writes + notification send + audit context otpCode

### `VerifyOtpResponse verifyOtp(VerifyOtpCommand command)`

- **Purpose:** Verify OTP and maybe advance application
- **Input:** mobile, code, applicationId
- **Output:** success flag + applicationId
- **Transaction behavior:** @Transactional
- **Security behavior:** @Auditable(OTP_VERIFY_SUCCESS) — failures remapped in AuditAspect
- **Side effects:** May update application status

## Design Decisions

- SecureRandom 6-digit codes
- Parameters `OTP/expire_minutes`, `OTP/max_retry`
- Notification failures are still after persistence of OTP (send path)

## Trade-offs and Alternatives

- Notifications are mock implementations in this repo — not real SMS gateways
- Audit stores context carefully; detail builder sanitizes sensitive fields

## Related Classes

- [Application](../domain/Application.md), [OtpRecord](../domain/OtpRecord.md), [AuditAspect](../common/AuditAspect.md), `NotificationService` / mocks

## Related Configuration

- System parameters table/service defaults (expire 5, max retry 3 if missing)
- Security permits `/api/v1/otp/**` in [SecurityConfig](../security/SecurityConfig.md)

## Related Tests

- [OtpAppServiceTest.java](../../../../src/test/java/com/tlbank/lending/application/otp/OtpAppServiceTest.java)

## Related ADRs and Design Documents

- [08-workflow-design.md](../../../design/08-workflow-design.md)
- [03-business-feature-handbook.md](../../../handbook/03-business-feature-handbook.md)

## Related Interview Questions

[`Q046`](../../../handbook/09-interview-source-map-300.md#Q046), [`Q103`](../../../handbook/09-interview-source-map-300.md#Q103), [`Q135`](../../../handbook/09-interview-source-map-300.md#Q135), [`Q137`](../../../handbook/09-interview-source-map-300.md#Q137), [`Q138`](../../../handbook/09-interview-source-map-300.md#Q138), [`Q139`](../../../handbook/09-interview-source-map-300.md#Q139), [`Q150`](../../../handbook/09-interview-source-map-300.md#Q150), [`Q164`](../../../handbook/09-interview-source-map-300.md#Q164), [`Q204`](../../../handbook/09-interview-source-map-300.md#Q204), [`Q205`](../../../handbook/09-interview-source-map-300.md#Q205), [`Q251`](../../../handbook/09-interview-source-map-300.md#Q251), [`Q272`](../../../handbook/09-interview-source-map-300.md#Q272), [`Q277`](../../../handbook/09-interview-source-map-300.md#Q277)

## 30-Second Explanation

`OtpAppService` sends OTPs by cancelling old pending records, saving a new code, and notifying. Verify checks the code then moves an INIT application to OTP_VERIFIED.

## 2-Minute Explanation

Cover parameter-driven TTL/retry, SecureRandom, audit annotations, and the status guard on verify. Mention mock notifications.

## 5-Minute Deep Explanation

Discuss AuditAspect failure action remap for OTP verify, why application must be INIT, and rate-limiting as **Not implemented** beyond business max retry.

## 中文口語重點

- 發送前取消舊 PENDING
- 驗證成功才可能改 Application 狀態
- 通知是 mock
- `@Transactional` 包住寫入

## Whiteboard Sketch

- **What to draw:** send vs verify sequences
- **Drawing order:** send first
- **Narration order:** parameters then domain

## Common Follow-Up Questions

- What if application is already SUBMITTED?
- Where is the OTP code logged/audited?

## Common Mistakes

- Calling SMS a real telecom integration
- Forgetting old PENDING cancellation

## Current Limitations

- No dedicated rate limit filter on send endpoint
- Mock notification channels

## Source File

[OtpAppService.java](../../../../src/main/java/com/tlbank/lending/application/otp/service/OtpAppService.java)
