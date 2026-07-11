# ApplicationRepositoryImpl

- [Back to Open Book Home](../../README.md)
- [Back to Source Map Index](../README.md)
- Previous Critical Class: [ApplicationStatus](../domain/ApplicationStatus.md)
- Next Critical Class: [ApplicationEntity](ApplicationEntity.md)
- Related Topics: [topics/](../../topics/README.md)
- Related Questions: [09-interview-source-map-300.md](../../../handbook/09-interview-source-map-300.md)

---

## One-Sentence Summary

JPA adapter implementing the domain `ApplicationRepository` port with entity ↔ aggregate mapping.

## 中文一句話

基礎設施適配器：把 `Application` 聚合同 `ApplicationEntity` 互轉並透過 Spring Data 存取。

## Why This Class Exists

Keep JPA out of the domain while still loading/saving the aggregate.

## Responsibilities

- `findById` / `save` / `findByStatus`
- Map embeddables, documents, workflow histories
- Resolve product code ↔ numeric `product_id`

## Runtime Execution Flow

Save path:

1. Find existing entity by `applicationNo` or create new.
2. `applyToEntity` / `toEntity` copies fields.
3. Sync histories/documents append-only for new items.
4. `applicationJpaRepository.save` then `toDomain`.

## Dependencies

### Depends On

- `ApplicationJpaRepository`, `CardProductJpaRepository`
- `ApplicationEntity` and child entities

### Called By

- Application services using `ApplicationRepository`

### Calls

- Spring Data JPA repositories; private mappers

## Important Public Methods

### `Optional<Application> findById(ApplicationId id)`

- **Purpose:** Load aggregate by business id
- **Input:** ApplicationId
- **Output:** `Optional<Application>`

### `Application save(Application application)`

- **Purpose:** Insert or update mapped entity
- **Input:** aggregate
- **Output:** reloaded aggregate
- **Side effects:** Persists applications + cascaded children

### `List<Application> findByStatus(ApplicationStatus status)`

- **Purpose:** Query by status
- **Input:** status
- **Output:** list of aggregates

## Design Decisions

- Port in domain package; impl in infrastructure
- Histories/documents sync by appending new domain items beyond existing collection size
- Product resolved by code first, then numeric id fallback

## Trade-offs and Alternatives

- Manual mapping vs MapStruct — explicit mapping is interview-visible
- No dedicated unit test class for this adapter (covered indirectly)

## Related Classes

- [Application](../domain/Application.md), [ApplicationEntity](ApplicationEntity.md), domain port `ApplicationRepository`

## Related Configuration

- Datasource / JPA via Spring profiles ([application.yml](../../../../src/main/resources/application.yml), [application-dev.yml](../../../../src/main/resources/application-dev.yml))
- Flyway migrations under `src/main/resources/db/migration/`

## Related Tests

- **No dedicated `ApplicationRepositoryImplTest`**
- Indirect: [ApplicationAppServiceTest.java](../../../../src/test/java/com/tlbank/lending/application/application/ApplicationAppServiceTest.java), [ApplicationFlowIntegrationTest.java](../../../../src/test/java/com/tlbank/lending/application/ApplicationFlowIntegrationTest.java)

## Related ADRs and Design Documents

- [0001-use-clean-architecture.md](../../../decisions/0001-use-clean-architecture.md)
- [05-database-design.md](../../../design/05-database-design.md)

## Related Interview Questions

[`Q027`](../../../handbook/09-interview-source-map-300.md#Q027), [`Q028`](../../../handbook/09-interview-source-map-300.md#Q028), [`Q029`](../../../handbook/09-interview-source-map-300.md#Q029), [`Q034`](../../../handbook/09-interview-source-map-300.md#Q034), [`Q092`](../../../handbook/09-interview-source-map-300.md#Q092), [`Q101`](../../../handbook/09-interview-source-map-300.md#Q101), [`Q102`](../../../handbook/09-interview-source-map-300.md#Q102), [`Q107`](../../../handbook/09-interview-source-map-300.md#Q107), [`Q210`](../../../handbook/09-interview-source-map-300.md#Q210), [`Q258`](../../../handbook/09-interview-source-map-300.md#Q258), [`Q297`](../../../handbook/09-interview-source-map-300.md#Q297)

## 30-Second Explanation

`ApplicationRepositoryImpl` is the JPA adapter for `ApplicationRepository`. It maps between the domain aggregate and `ApplicationEntity`, including documents and workflow history.

## 2-Minute Explanation

Explain port/adapter: services depend on the interface. Save merges into an existing row by `application_no`. Product id bridging uses card product codes. Mapping keeps domain free of Jakarta annotations.

## 5-Minute Deep Explanation

Walk `toDomain`/`toEntity`, append-only sync, and why that can miss in-place edits to old history rows. Mention missing dedicated unit tests as a documentation honesty point.

## 中文口語重點

- domain port／infrastructure impl
- `application_no` 對業務 ID
- history／document 採增量 append
- 無專屬單元測試類別

## Whiteboard Sketch

- **What to draw:** Domain ←port→ Impl → JPA → DB
- **Drawing order:** port first
- **Narration order:** find vs save

## Common Follow-Up Questions

- Why not put `@Entity` on the domain class?
- How are collections synced?

## Common Mistakes

- Calling this a domain service
- Claiming MapStruct is used

## Current Limitations

- No dedicated unit test for mapping edge cases
- Append-only sync assumptions

## Source File

[ApplicationRepositoryImpl.java](../../../../src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationRepositoryImpl.java)
