# ReviewAppService

- [Back to Open Book Home](../../README.md)
- [Back to Source Map Index](../README.md)
- Previous Critical Class: [OtpAppService](OtpAppService.md)
- Next Critical Class: [ReviewCase](../domain/ReviewCase.md)
- Related Topics: [topics/](../../topics/README.md)
- Related Questions: [09-interview-source-map-300.md](../../../handbook/09-interview-source-map-300.md)

---

## One-Sentence Summary

Review use cases: search/detail, start review, approve/reject with dual-aggregate updates and domain events.

## 中文一句話

徵審用例：同時更新 `ReviewCase` 與 `Application`，核准／拒絕後發佈領域事件。

## Why This Class Exists

Coordinate credit review without putting multi-aggregate rules in controllers.

## Responsibilities

- Search/page review cases; detail with masked applicant
- Start review on case + application when needed
- Approve/reject both aggregates; publish approved/rejected events
- Add remarks

## Runtime Execution Flow

Approve:

1. Load `ReviewCase` + linked `Application`.
2. `reviewCase.approve`; ensure application UNDER_REVIEW; `application.approve`.
3. Save both.
4. Publish `ApplicationApprovedEvent`.

## Dependencies

### Depends On

- `ReviewCaseRepository`, `ApplicationRepository`, `CardProductRepository`, `ApplicationEventPublisher`, `Clock`

### Called By

- `ReviewApiController` / web review controllers
- `ReviewAppServiceTest`, `ReviewFlowIntegrationTest`

### Calls

- Domain verbs on both aggregates; event publish

## Important Public Methods

### `PageResponse<ReviewCaseSummaryResponse> searchCases(ReviewCaseSearchCriteria criteria, Pageable pageable)`

- **Purpose:** Paged search
- **Transaction behavior:** @Transactional(readOnly = true)

### `ReviewCaseDetailResponse getCaseDetail(String reviewCaseId)`

- **Purpose:** Detail + application projection
- **Transaction behavior:** @Transactional(readOnly = true)
- **Security behavior:** Masks applicant fields in DTO

### `void startCaseReview(String reviewCaseId, String operator)`

- **Purpose:** Start review on case/application when still pending/submitted
- **Transaction behavior:** @Transactional

### `void approveCase(ApproveCaseCommand command)`

- **Purpose:** Approve both aggregates + event
- **Transaction behavior:** @Transactional
- **Security behavior:** @Auditable(APPLICATION_APPROVE)
- **Side effects:** ApplicationApprovedEvent

### `void rejectCase(RejectCaseCommand command)`

- **Purpose:** Reject both aggregates + event
- **Transaction behavior:** @Transactional
- **Security behavior:** @Auditable(APPLICATION_REJECT)
- **Side effects:** ApplicationRejectedEvent

### `ReviewRemarkResponse addRemark(AddRemarkCommand command)`

- **Purpose:** Append remark
- **Transaction behavior:** @Transactional

## Design Decisions

- Dual write in one transaction
- `ensureApplicationUnderReview` auto-starts application review if still SUBMITTED
- Events after successful saves

## Trade-offs and Alternatives

- Single TX across two aggregates vs eventual consistency — chosen for simplicity
- Review case creation happens in `ReviewEventHandler` on submit, not here

## Related Classes

- [ReviewCase](../domain/ReviewCase.md), [Application](../domain/Application.md), [NotificationEventHandler](../infrastructure/NotificationEventHandler.md), `ReviewEventHandler`

## Related Configuration

- Security: `/api/v1/review/**` requires REVIEWER or ADMIN ([SecurityConfig](../security/SecurityConfig.md))

## Related Tests

- [ReviewAppServiceTest.java](../../../../src/test/java/com/tlbank/lending/application/review/ReviewAppServiceTest.java)
- [ReviewFlowIntegrationTest.java](../../../../src/test/java/com/tlbank/lending/application/ReviewFlowIntegrationTest.java)

## Related ADRs and Design Documents

- [08-workflow-design.md](../../../design/08-workflow-design.md)
- [07-security-design.md](../../../design/07-security-design.md)

## Related Interview Questions

[`Q104`](../../../handbook/09-interview-source-map-300.md#Q104), [`Q109`](../../../handbook/09-interview-source-map-300.md#Q109), [`Q110`](../../../handbook/09-interview-source-map-300.md#Q110), [`Q130`](../../../handbook/09-interview-source-map-300.md#Q130), [`Q148`](../../../handbook/09-interview-source-map-300.md#Q148), [`Q209`](../../../handbook/09-interview-source-map-300.md#Q209), [`Q214`](../../../handbook/09-interview-source-map-300.md#Q214)

## 30-Second Explanation

`ReviewAppService` runs credit review. Approve/reject update both `ReviewCase` and `Application` in one transaction, then publish domain events for notifications.

## 2-Minute Explanation

Describe search/detail, startCaseReview gates, and why submit creates the case via `ReviewEventHandler`. Mention role-restricted APIs.

## 5-Minute Deep Explanation

Discuss dual-aggregate TX risks, event ordering, and masking in summaries. Contrast method names `approveCase`/`rejectCase` with domain `approve`/`reject`.

## 中文口語重點

- 兩個聚合同一 TX
- 事件在 save 之後
- ReviewCase 多半在 submit 事件時建立
- API 需 REVIEWER／ADMIN

## Whiteboard Sketch

- **What to draw:** ReviewCase ∥ Application boxes updated together → events
- **Drawing order:** load, mutate, save, publish
- **Narration order:** security roles first

## Common Follow-Up Questions

- What if application save fails after review case approve in memory?
- Who creates the ReviewCase?

## Common Mistakes

- Inventing method names `approve`/`reject` on the service
- Forgetting the application aggregate update

## Current Limitations

- No saga/compensation beyond DB transaction rollback
- Relies on event handler to create cases on submit

## Source File

[ReviewAppService.java](../../../../src/main/java/com/tlbank/lending/application/review/service/ReviewAppService.java)
