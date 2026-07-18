# M23 — Domain Events / 領域事件

## Why This Module Matters

### English

Spring ApplicationEventPublisher and sync listeners; mock notification side effects.

### 中文

Spring ApplicationEventPublisher 與同步監聽；通知副作用為 mock。

## Files to Open First

- `sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java` — Publishes submit event.  
  `sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java` — 發布提交事件。
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/event/NotificationEventHandler.java` — Notification listener.  
  `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/event/NotificationEventHandler.java` — 通知監聽。
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/event/ReviewEventHandler.java` — Creates ReviewCase on submit.  
  `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/event/ReviewEventHandler.java` — 提交時建立 ReviewCase。

## Primary Question

### English

Which events are published today?

### 中文

現況發布哪些事件？

## Suggested Answer

### English

ApplicationSubmittedEvent from submit; approved/rejected events from ReviewAppService. Publishing uses Spring ApplicationEventPublisher. There is no custom DomainEventPublisher class. ApplicationCancelledEvent and OtpGeneratedEvent exist as types but are not published in the current main path.

### 中文

提交發布 ApplicationSubmittedEvent；核准／拒絕由 ReviewAppService 發布。使用 Spring ApplicationEventPublisher。無自訂 DomainEventPublisher 類別。ApplicationCancelledEvent 與 OtpGeneratedEvent 有型別定義，但現況主路徑未發布。

## Follow-up Question

### English

Are listeners asynchronous or AFTER_COMMIT?

### 中文

監聽是否非同步或 AFTER_COMMIT？

## Follow-up Answer

### English

Handlers use synchronous @EventListener. @TransactionalEventListener(AFTER_COMMIT) is not the current wiring. Notification failures are caught and logged in NotificationEventHandler.

### 中文

Handler 使用同步 @EventListener。現況未接 @TransactionalEventListener(AFTER_COMMIT)。NotificationEventHandler 對通知失敗捕捉並記錄。

## Interview Tip

### English

Why asked: events without broker fantasy.
Answer first: Spring events + two handlers.
Keywords: ReviewCase creation, mock notify.
Follow-ups: outbox (not present), cancelled event debt.

### 中文

提問原因：事件且無 broker 幻想。
先答：Spring 事件 + 兩個 handler。
關鍵詞：建立 ReviewCase、mock 通知。
追問：outbox（未實作）、取消事件債務。

## Open Book Navigation

- [Events and notifications](../open-book/topics/09-events-and-notifications.md)  
  [事件與通知](../open-book/topics/09-events-and-notifications.md)
- [NotificationEventHandler source-map](../open-book/source-map/infrastructure/NotificationEventHandler.md)  
  [NotificationEventHandler 類別地圖](../open-book/source-map/infrastructure/NotificationEventHandler.md)
