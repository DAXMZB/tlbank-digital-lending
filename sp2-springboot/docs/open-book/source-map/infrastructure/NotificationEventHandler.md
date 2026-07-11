# NotificationEventHandler

- [Back to Open Book Home](../../README.md)
- [Back to Source Map Index](../README.md)
- Previous Critical Class: [LocalDocumentStorageService](LocalDocumentStorageService.md)
- Next Critical Class: —
- Related Topics: [topics/](../../topics/README.md)
- Related Questions: [09-interview-source-map-300.md](../../../handbook/09-interview-source-map-300.md)

---

## One-Sentence Summary

Spring `@EventListener` that turns application lifecycle events into notification service calls (mock SMS/email underneath).

## 中文一句話

監聽申請送出／核准／拒絕事件並呼叫通知服務；通道為 mock，失敗只打 warn 不回滾業務。

## Why This Class Exists

Decouple notification side effects from `ApplicationAppService` / `ReviewAppService` transactions' core writes via events.

## Responsibilities

- Handle `ApplicationSubmittedEvent`, `ApplicationApprovedEvent`, `ApplicationRejectedEvent`
- Delegate to `NotificationService`
- Swallow notification exceptions after warn logs

## Runtime Execution Flow

1. Service publishes domain event after save.
2. Spring delivers event to listener methods.
3. NotificationService sends mock SMS/email templates.
4. On error: log warn; business TX already committed independently of this listener unless sharing TX semantics (default listener runs in caller thread after publish — still does not rethrow).

## Dependencies

### Depends On

- `NotificationService` (impl uses mock senders)

### Called By

- Spring event mechanism after publish from app services

### Calls

- `sendApplicationSubmittedNotification` / approved / rejected

## Important Public Methods

### `void onApplicationSubmitted(ApplicationSubmittedEvent event)`

- **Purpose:** Notify submit
- **Input:** event
- **Side effects:** Mock notify; errors logged

### `void onApplicationApproved(ApplicationApprovedEvent event)`

- **Purpose:** Notify approval
- **Input:** event
- **Side effects:** Mock notify; errors logged

### `void onApplicationRejected(ApplicationRejectedEvent event)`

- **Purpose:** Notify rejection
- **Input:** event
- **Side effects:** Mock notify; errors logged

## Design Decisions

- Try/catch per listener so notification outages do not explode the publisher path after commit attempts
- Uses Spring `ApplicationEventPublisher`, not a custom bus class

## Trade-offs and Alternatives

- Mock adapters vs real SMS/email providers — mocks only in this repository
- Sync listeners vs `@Async` — sync unless configured otherwise

## Related Classes

- [ApplicationAppService](../application/ApplicationAppService.md), [ReviewAppService](../application/ReviewAppService.md), `MockSmsSender`, `MockEmailSender`, `NotificationServiceImpl`
- Sibling `ReviewEventHandler` creates `ReviewCase` on submit

## Related Configuration

- Notification templates/parameters via notification module (mock)
- No Twilio/SendGrid properties as real integrations

## Related Tests

- [NotificationEventHandlerTest.java](../../../../src/test/java/com/tlbank/lending/infrastructure/event/NotificationEventHandlerTest.java)

## Related ADRs and Design Documents

- [09-module-design.md](../../../design/09-module-design.md)
- [08-workflow-design.md](../../../design/08-workflow-design.md)

## Related Interview Questions

[`Q002`](../../../handbook/09-interview-source-map-300.md#Q002), [`Q038`](../../../handbook/09-interview-source-map-300.md#Q038), [`Q044`](../../../handbook/09-interview-source-map-300.md#Q044), [`Q099`](../../../handbook/09-interview-source-map-300.md#Q099), [`Q100`](../../../handbook/09-interview-source-map-300.md#Q100), [`Q109`](../../../handbook/09-interview-source-map-300.md#Q109), [`Q130`](../../../handbook/09-interview-source-map-300.md#Q130), [`Q233`](../../../handbook/09-interview-source-map-300.md#Q233)

## 30-Second Explanation

`NotificationEventHandler` listens for submit/approve/reject events and calls `NotificationService`. In this repo those notifications are mocks; failures are logged, not turned into real carrier sends.

## 2-Minute Explanation

Tie to who publishes events, contrast with `ReviewEventHandler`, and stress mock vs real. Mention try/catch policy.

## 5-Minute Deep Explanation

Discuss transaction boundaries around event publish, whether listeners should be async for production, and current **Not implemented** real providers.

## 中文口語重點

- `@EventListener` 三支
- 底下是 mock SMS／email
- 例外吃掉打 warn
- 不是雲端通知服務

## Whiteboard Sketch

- **What to draw:** AppService publish → Listener → NotificationService → Mock senders
- **Drawing order:** event types as labeled arrows
- **Narration order:** mock caveat first

## Common Follow-Up Questions

- Are notifications transactional with approve?
- Difference from ReviewEventHandler?

## Common Mistakes

- Describing Twilio/SendGrid as integrated
- Saying a custom `DomainEventPublisher` class exists

## Current Limitations

- Mock-only channels
- Errors do not retry/dead-letter

## Source File

[NotificationEventHandler.java](../../../../src/main/java/com/tlbank/lending/infrastructure/event/NotificationEventHandler.java)
