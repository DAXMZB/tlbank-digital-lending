# ApplicationEntity

- [Back to Open Book Home](../../README.md)
- [Back to Source Map Index](../README.md)
- Previous Critical Class: [ApplicationRepositoryImpl](ApplicationRepositoryImpl.md)
- Next Critical Class: [SecurityConfig](../security/SecurityConfig.md)
- Related Topics: [topics/README.md](../../topics/README.md) (bodies **Pending** — Phase 3)
- Related Questions: [09-interview-source-map-300.md](../../../handbook/09-interview-source-map-300.md)

---
## One-Sentence Summary

JPA `@Entity` for table `applications`, including embeds and cascaded children.

## 中文一句話

`applications` 表的 JPA 實體：狀態用 `EnumType.STRING`，申請人／地址為 embed，文件與 history 一對多。

## Why This Class Exists

Persistence model separate from the domain aggregate.

## Responsibilities

- Map columns: `application_no`, `product_id`, `status`, `submitted_at`, …
- Embed `ApplicantEmbeddable` / `AddressEmbeddable`
- One-to-many `workflowHistories` and `documents` with cascade ALL

## Runtime Execution Flow

Not invoked directly by business code — `ApplicationRepositoryImpl` reads/writes it through Spring Data.

## Dependencies

### Depends On

- `BaseEntity`, Jakarta Persistence, Lombok
- `ApplicationStatus` enum (domain enum reused in persistence)

### Called By

- `ApplicationJpaRepository` / `ApplicationRepositoryImpl`

### Calls

- JPA lifecycle via provider

## Important Public Methods

Lombok `@Getter`/`@Setter` generate accessors. No custom business methods.

Notable members:

- `Long id` — DB identity
- `String applicationNo` — business key
- `ApplicationStatus status` — `@Enumerated(STRING)`
- embeds + collections above

## Design Decisions

- STRING enum storage (not ORDINAL)
- Surrogate `id` plus unique `application_no`
- Lazy one-to-many collections

## Trade-offs and Alternatives

- Sharing `ApplicationStatus` enum with domain couples packages slightly but avoids duplicate enums
- Alternative: separate persistence enum — not chosen

## Related Classes

- [ApplicationRepositoryImpl](ApplicationRepositoryImpl.md), [Application](../domain/Application.md)

## Related Configuration

- JPA/Hibernate + Flyway (`V3__create_applications.sql` and later alter migrations)

## Related Tests

- **No dedicated `ApplicationEntityTest`**
- Schema exercised via integration/app service tests

## Related ADRs and Design Documents

- [0007-h2-vs-sqlserver.md](../../../decisions/0007-h2-vs-sqlserver.md)
- [05-database-design.md](../../../design/05-database-design.md)

## Related Interview Questions

[`Q029`](../../../handbook/09-interview-source-map-300.md#Q029), [`Q089`](../../../handbook/09-interview-source-map-300.md#Q089), [`Q090`](../../../handbook/09-interview-source-map-300.md#Q090), [`Q091`](../../../handbook/09-interview-source-map-300.md#Q091), [`Q092`](../../../handbook/09-interview-source-map-300.md#Q092), [`Q127`](../../../handbook/09-interview-source-map-300.md#Q127), [`Q259`](../../../handbook/09-interview-source-map-300.md#Q259)

## 30-Second Explanation

`ApplicationEntity` is the JPA mapping for applications. Status is stored as a string enum; applicant/address are embeds; histories and documents cascade.

## 2-Minute Explanation

Contrast with domain `Application`: entity has DB `id` and `product_id` long FK-ish field, while domain uses `ApplicationId` and `CardProductId`. Repository maps between them.

## 5-Minute Deep Explanation

Discuss cascade/lazy defaults, why STRING enums matter for Flyway stability, and coupling from reusing the domain enum in the entity.

## 中文口語重點

- 表名 `applications`
- `EnumType.STRING`
- 業務鍵 `application_no`
- 無業務方法，只有對應欄位

## Whiteboard Sketch

- **What to draw:** table boxes + embed + child tables
- **Drawing order:** parent row then children
- **Narration order:** keys then enums then relations

## Common Follow-Up Questions

- ORDINAL vs STRING?
- Why both `id` and `application_no`?

## Common Mistakes

- Treating the entity as the aggregate root in domain talk
- Inventing business methods on the entity

## Current Limitations

- No dedicated entity unit tests
- Domain enum imported into persistence package

## Source File

[ApplicationEntity.java](../../../../src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationEntity.java)
