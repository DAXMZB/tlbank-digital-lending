# ApplicationStatus

- [Back to Open Book Home](../../README.md)
- [Back to Source Map Index](../README.md)
- Previous Critical Class: [ApplicationAppService](../application/ApplicationAppService.md)
- Next Critical Class: [ApplicationRepositoryImpl](../infrastructure/ApplicationRepositoryImpl.md)
- Related Topics: [topics/](../../topics/README.md)
- Related Questions: [09-interview-source-map-300.md](../../../handbook/09-interview-source-map-300.md)

---

## One-Sentence Summary

Enum plus allowed-transition map for application lifecycle states.

## 中文一句話

申請狀態列舉與允許轉移表；`canTransitionTo` 是狀態機查表入口。

## Why This Class Exists

Keep transition legality declarative and testable beside the aggregate verbs.

## Responsibilities

- Declare statuses: INIT … CANCELLED
- Expose `canTransitionTo`
- Hold `ALLOWED_TRANSITIONS` map (terminal APPROVED/REJECTED/CANCELLED have no outbound edges in the map)

## Runtime Execution Flow

1. Aggregate calls `status.canTransitionTo(next)`.
2. Lookup `ALLOWED_TRANSITIONS.get(this)`.
3. Return whether `next` is contained (null next → false).

## Dependencies

### Depends On

- Java `EnumSet` / `Map`

### Called By

- `Application.transitionTo`
- `ApplicationStatusTest`, domain tests

### Calls

- None beyond JDK collections

## Important Public Methods

### `boolean canTransitionTo(ApplicationStatus next)`

- **Purpose:** Whether next status is allowed from this
- **Input:** next status (nullable)
- **Output:** boolean
- **Business meaning:** Guards illegal workflow moves

Enum constants themselves are the public API surface.

## Design Decisions

- Explicit map instead of a library
- CANCELLED reachable only from early states; APPROVED/REJECTED only from UNDER_REVIEW

## Trade-offs and Alternatives

- Enum map is readable for interviews; less flexible than a configurable workflow engine

## Related Classes

- [Application](Application.md)

## Related Configuration

- None

## Related Tests

- [ApplicationStatusTest.java](../../../../src/test/java/com/tlbank/lending/domain/application/ApplicationStatusTest.java)
- [ApplicationTest.java](../../../../src/test/java/com/tlbank/lending/domain/application/ApplicationTest.java)

## Related ADRs and Design Documents

- [08-workflow-design.md](../../../design/08-workflow-design.md)
- [04-domain-model.md](../../../design/04-domain-model.md)

## Related Interview Questions

[`Q009`](../../../handbook/09-interview-source-map-300.md#Q009), [`Q012`](../../../handbook/09-interview-source-map-300.md#Q012), [`Q014`](../../../handbook/09-interview-source-map-300.md#Q014), [`Q042`](../../../handbook/09-interview-source-map-300.md#Q042), [`Q051`](../../../handbook/09-interview-source-map-300.md#Q051), [`Q052`](../../../handbook/09-interview-source-map-300.md#Q052), [`Q055`](../../../handbook/09-interview-source-map-300.md#Q055), [`Q059`](../../../handbook/09-interview-source-map-300.md#Q059), [`Q114`](../../../handbook/09-interview-source-map-300.md#Q114), [`Q145`](../../../handbook/09-interview-source-map-300.md#Q145), [`Q243`](../../../handbook/09-interview-source-map-300.md#Q243), [`Q246`](../../../handbook/09-interview-source-map-300.md#Q246)

## 30-Second Explanation

`ApplicationStatus` is the enum behind the application state machine. `canTransitionTo` reads a static map of allowed next states.

## 2-Minute Explanation

List the eight values and the forward path. Emphasize CANCELLED exits and that APPROVED/REJECTED are terminals in the map. `Application` is what mutates status after the check.

## 5-Minute Deep Explanation

Compare with `ReviewStatus` on `ReviewCase`. Mention Flyway/JPA store the enum as STRING on `ApplicationEntity`. Discuss adding a new status would require map + aggregate + DB migration.

## 中文口語重點

- 查表在 `ALLOWED_TRANSITIONS`
- `next == null` → false
- 終態沒有出邊

## Whiteboard Sketch

- **What to draw:** same diagram as Application page
- **Drawing order:** map entries first
- **Narration order:** query API then callers

## Common Follow-Up Questions

- Can SUBMITTED go to CANCELLED?
- How is the enum persisted?

## Common Mistakes

- Inventing extra transitions not in the map
- Confusing review status with application status

## Current Limitations

- No compensating transitions (e.g. re-open rejected)

## Source File

[ApplicationStatus.java](../../../../src/main/java/com/tlbank/lending/domain/application/ApplicationStatus.java)
