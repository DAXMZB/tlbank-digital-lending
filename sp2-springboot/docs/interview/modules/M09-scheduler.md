# M09 — Scheduler / 排程

## Why This Module Matters

### English

In-process @Scheduled jobs and dual @EnableScheduling debt.

### 中文

行程內 @Scheduled 任務與雙重 @EnableScheduling 債務。

## Files to Open First

- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/scheduler/OtpCleanupScheduler.java` — OTP expiry cron.  
  `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/scheduler/OtpCleanupScheduler.java` — OTP 過期 cron。
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/scheduler/CacheRefreshScheduler.java` — Cache refresh cron.  
  `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/scheduler/CacheRefreshScheduler.java` — 快取刷新 cron。
- `sp2-springboot/src/main/java/com/tlbank/lending/common/config/SchedulingConfig.java` — One @EnableScheduling.  
  `sp2-springboot/src/main/java/com/tlbank/lending/common/config/SchedulingConfig.java` — 其一 @EnableScheduling。
- `sp2-springboot/src/main/java/com/tlbank/lending/common/config/SchedulerConfig.java` — Second @EnableScheduling debt.  
  `sp2-springboot/src/main/java/com/tlbank/lending/common/config/SchedulerConfig.java` — 其二 @EnableScheduling 債務。

## Primary Question

### English

What scheduled jobs exist?

### 中文

有哪些排程任務？

## Suggested Answer

### English

OtpCleanupScheduler.cleanupExpiredOtps marks expired OTPs transactionally on tlbank.scheduler.otp-cleanup.cron. CacheRefreshScheduler refreshes in-memory caches on tlbank.scheduler.cache-refresh.cron. Jobs run inside the Spring process, not as external cloud CronJobs.

### 中文

OtpCleanupScheduler.cleanupExpiredOtps 依 tlbank.scheduler.otp-cleanup.cron 交易性標記過期 OTP。CacheRefreshScheduler 依 tlbank.scheduler.cache-refresh.cron 刷新記憶體快取。任務在 Spring 行程內執行，非外部雲端 CronJob。

## Follow-up Question

### English

Why are there two @EnableScheduling classes?

### 中文

為何有兩個 @EnableScheduling 類別？

## Follow-up Answer

### English

SchedulingConfig and SchedulerConfig both enable scheduling. That duplication is known debt, not a feature.

### 中文

SchedulingConfig 與 SchedulerConfig 皆啟用排程。此重複為已知債務，非功能。

## Interview Tip

### English

Why asked: ops honesty.
Answer first: in-process crons + property keys.
Keywords: markExpiredBefore, dual EnableScheduling.
Follow-ups: multi-instance duplicate fires, ShedLock (not present).

### 中文

提問原因：營運誠實度。
先答：行程內 cron 與設定鍵。
關鍵詞：markExpiredBefore、雙重 EnableScheduling。
追問：多實例重複觸發、ShedLock（未實作）。

## Open Book Navigation

- [Delivery limitations topic](../open-book/topics/12-delivery-and-limitations.md)  
  [交付與限制主題](../open-book/topics/12-delivery-and-limitations.md)
- [OtpCleanupScheduler source-map](../open-book/source-map/infrastructure/OtpCleanupScheduler.md)  
  [OtpCleanupScheduler 類別地圖](../open-book/source-map/infrastructure/OtpCleanupScheduler.md)
