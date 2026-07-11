# ReviewCase

- [Back to Open Book Home](../../README.md)
- [Back to Source Map Index](../README.md)
- Previous Critical Class: [ReviewAppService](../application/ReviewAppService.md)
- Next Critical Class: [AuditAspect](../common/AuditAspect.md)
- Related Topics: [topics/](../../topics/README.md)
- Related Questions: [09-interview-source-map-300.md](../../../handbook/09-interview-source-map-300.md)

---

## One-Sentence Summary

Review aggregate: PENDING → UNDER_REVIEW → APPROVED/REJECTED with remarks.

## 中文一句話

徵審聚合根：狀態與備註在領域內；由 `ReviewEventHandler` 在申請送出後建立。

## Why This Class Exists

Separate credit-review workflow from the application lifecycle while remaining linked by `applicationId`.

## Responsibilities

- Factory `createFor`
- Assign reviewer; start/approve/reject; add remarks
- Enforce review status transitions

## Runtime Execution Flow

1. `ReviewEventHandler` hears `ApplicationSubmittedEvent` → `ReviewCase.createFor` → save.
2. Reviewers call service methods that delegate to domain verbs.
3. Approve/reject require `UNDER_REVIEW` and set `reviewedAt`.

## Dependencies

### Depends On

- `ReviewCaseId`, `ReviewStatus`, `ReviewRemark`, `WorkflowException`

### Called By

- `ReviewAppService`, `ReviewEventHandler`
- `ReviewCaseTest`

### Calls

- private `transitionTo`, `addRemarkInternal`

## Important Public Methods

### `static ReviewCase createFor(String applicationId)`

- **Purpose:** Factory for PENDING case
- **Input:** application id string
- **Output:** new ReviewCase

### `void assign(String username)`

- **Purpose:** Set assignedTo
- **Input:** username

### `void startReview(String operator)`

- **Purpose:** PENDING → UNDER_REVIEW + remark
- **Input:** operator

### `void approve(String operator, String remark)`

- **Purpose:** UNDER_REVIEW → APPROVED
- **Input:** operator, remark
- **Side effects:** reviewedAt now

### `void reject(String operator, String remark)`

- **Purpose:** UNDER_REVIEW → REJECTED
- **Input:** operator, remark
- **Side effects:** reviewedAt now

### `void addRemark(String content, String operator)`

- **Purpose:** Append remark without status change

## Design Decisions

- Remarks auto-added on start/approve/reject
- Stricter approve/reject precondition than application aggregate (must already be UNDER_REVIEW)

## Trade-offs and Alternatives

- Could embed review inside `Application` — split keeps bounded contexts clearer for interviews

## Related Classes

- [ReviewAppService](../application/ReviewAppService.md), [Application](Application.md), `ReviewEventHandler`

## Related Configuration

- None directly

## Related Tests

- [ReviewCaseTest.java](../../../../src/test/java/com/tlbank/lending/domain/review/ReviewCaseTest.java)
- [ReviewAppServiceTest.java](../../../../src/test/java/com/tlbank/lending/application/review/ReviewAppServiceTest.java)
- [ReviewFlowIntegrationTest.java](../../../../src/test/java/com/tlbank/lending/application/ReviewFlowIntegrationTest.java)

## Related ADRs and Design Documents

- [08-workflow-design.md](../../../design/08-workflow-design.md)
- [0002-use-ddd.md](../../../decisions/0002-use-ddd.md)

## Related Interview Questions

[`Q041`](../../../handbook/09-interview-source-map-300.md#Q041), [`Q045`](../../../handbook/09-interview-source-map-300.md#Q045), [`Q049`](../../../handbook/09-interview-source-map-300.md#Q049), [`Q145`](../../../handbook/09-interview-source-map-300.md#Q145), [`Q146`](../../../handbook/09-interview-source-map-300.md#Q146), [`Q147`](../../../handbook/09-interview-source-map-300.md#Q147), [`Q232`](../../../handbook/09-interview-source-map-300.md#Q232), [`Q273`](../../../handbook/09-interview-source-map-300.md#Q273)

## 30-Second Explanation

`ReviewCase` is the review aggregate. It starts pending, moves under review, then approve/reject with remarks. It is created when an application is submitted.

## 2-Minute Explanation

Contrast `ReviewStatus` vs `ApplicationStatus`. Explain factory and why approve requires UNDER_REVIEW. Point to event handler creation.

## 5-Minute Deep Explanation

Discuss consistency with `ReviewAppService.ensureApplicationUnderReview` and remark list growth.

## 中文口語重點

- createFor → PENDING
- approve／reject 必須 UNDER_REVIEW
- 與 Application 用 applicationId 關聯

## Whiteboard Sketch

- **What to draw:** ReviewStatus mini state machine
- **Drawing order:** PENDING → UNDER_REVIEW → terminals
- **Narration order:** creation event then verbs

## Common Follow-Up Questions

- Who calls `createFor`?
- Can you approve from PENDING?

## Common Mistakes

- Merging review status into ApplicationStatus
- Saying controller creates the case directly

## Current Limitations

- No re-assign workflow beyond `assign`
- No multi-reviewer concurrency controls in domain

## Source File

[ReviewCase.java](../../../../src/main/java/com/tlbank/lending/domain/review/ReviewCase.java)
