# OtpCleanupScheduler

- [Back to Open Book Home](../../README.md)
- [Back to Source Map Index](../README.md)
- Previous High Class: [CachedCardProductRepository](CachedCardProductRepository.md)
- Next High Class: [ReportAppService](../application/ReportAppService.md)
- Related Topics: [12-delivery-and-limitations](../../topics/12-delivery-and-limitations.md), [06-transactions](../../topics/06-transactions.md)
- Related Questions: [09-interview-source-map-300.md](../../../handbook/09-interview-source-map-300.md)

---

## One-Sentence Summary

Cron-driven job that marks expired PENDING OTPs via `OtpRepository.markExpiredBefore`.

## 中文一句話

排程標記過期 OTP；cron 可設定；例外只 warn；與產品快取刷新是不同工作。

## Why This Class Exists

Housekeeping for OTP rows that were never verified. Separates batch expire from request-time `OtpRecord.verify` expiry checks.

Delivery/schedulers: [topics/12-delivery-and-limitations.md](../../topics/12-delivery-and-limitations.md). TX on schedulers: [topics/06-transactions.md](../../topics/06-transactions.md).

## Responsibilities

- Run on `tlbank.scheduler.otp-cleanup.cron`
- Transactionally mark expired OTPs before now
- Log and swallow unexpected failures

## Runtime Execution Flow

1. Scheduler trigger (enabled by `@EnableScheduling` configs).
2. `cleanupExpiredOtps` opens `@Transactional`.
3. `otpRepository.markExpiredBefore(LocalDateTime.now(clock))`.
4. On exception → `log.warn` (no rethrow).

```mermaid
flowchart TD
  CRON[cron tlbank.scheduler.otp-cleanup.cron] --> JOB[OtpCleanupScheduler]
  JOB --> TX[@Transactional]
  TX --> REPO[OtpRepository.markExpiredBefore]
  REPO --> DB[(otp table)]

```

## Dependencies

### Depends On

- `OtpRepository`, `Clock`

### Called By

- Spring scheduling infrastructure

### Calls

- `markExpiredBefore`

## Important Public Methods

### `void cleanupExpiredOtps()`

- **Purpose:** Batch-expire pending OTPs
- **Transaction behavior:** @Transactional on scheduler method
- **Side effects:** DB status updates; errors swallowed

## Design Decisions

- Cron from configuration (dev vs default differ)
- Fail-soft logging
- Uses injectable `Clock`

## Trade-offs and Alternatives

- Dual `@EnableScheduling` (`SchedulingConfig` + `SchedulerConfig`) is known debt — see delivery topic
- Alternative: DB TTL job external to app — not used

## Related Classes

- Domain peer: [OtpRecord](../domain/OtpRecord.md)
- Sibling (grouped mention only, no dedicated page): `CacheRefreshScheduler`
- Enablement: `SchedulingConfig`, `SchedulerConfig` under `common/config`

## Related Configuration

- `tlbank.scheduler.otp-cleanup.cron`
  - `application.yml`: `0 */5 * * * *`
  - `application-dev.yml`: `0 */1 * * * *`

- Sibling key (related): `tlbank.scheduler.cache-refresh.cron`

## Related Tests

- [OtpCleanupSchedulerTest.java](../../../../src/test/java/com/tlbank/lending/infrastructure/scheduler/OtpCleanupSchedulerTest.java)
- Sibling: [CacheRefreshSchedulerTest.java](../../../../src/test/java/com/tlbank/lending/infrastructure/scheduler/CacheRefreshSchedulerTest.java)

## Related ADRs and Design Documents

- [13-scheduler-design.md](../../../design/13-scheduler-design.md)

## Related Interview Questions

[`Q022`](../../../handbook/09-interview-source-map-300.md#Q022), [`Q060`](../../../handbook/09-interview-source-map-300.md#Q060), [`Q093`](../../../handbook/09-interview-source-map-300.md#Q093), [`Q129`](../../../handbook/09-interview-source-map-300.md#Q129), [`Q140`](../../../handbook/09-interview-source-map-300.md#Q140), [`Q168`](../../../handbook/09-interview-source-map-300.md#Q168), [`Q170`](../../../handbook/09-interview-source-map-300.md#Q170)

## 30-Second Explanation

`OtpCleanupScheduler` periodically marks expired OTPs in the database. It is transactional, cron-configured, and swallows errors after logging. It is not the product cache refresher.

## 2-Minute Explanation

Name the property key and the repository method. Contrast with request-time expire inside `OtpRecord.verify`. Mention dual `@EnableScheduling` debt without claiming cloud schedulers.

## 5-Minute Deep Explanation

Discuss `@Transactional` on scheduled methods and clock injection for tests. Link delivery topic for CI/ops. Do not invent Kubernetes CronJobs as current.

## 中文口語重點

- DB 標記過期，不是清 Redis
- 與 CacheRefreshScheduler 分開
- 雙 EnableScheduling 是債

## Whiteboard Sketch

- **What to draw:** cron → scheduler → repository → DB
- **Drawing order:** config property first
- **Narration order:** trigger → TX → markExpiredBefore

## Common Follow-Up Questions

- How often in dev vs default?
- Does this delete rows?
- Relation to CacheRefreshScheduler?

## Common Mistakes

- Saying it clears Redis OTP keys (OTP is not in Redis)
- Merging cache refresh into this class
- Claiming automatic cloud cron outside the app

## Current Limitations

- Exceptions swallowed (silent skip until logs reviewed)
- Dual scheduling enablement debt

## Source File

[OtpCleanupScheduler.java](../../../../src/main/java/com/tlbank/lending/infrastructure/scheduler/OtpCleanupScheduler.java)
