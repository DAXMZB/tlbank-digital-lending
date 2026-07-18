# M05 — JPA / Hibernate / JPA／Hibernate

## Why This Module Matters

### English

Separating Application from ApplicationEntity is a standard junior deep-dive in this repository.

### 中文

區分 Application 與 ApplicationEntity 是本倉庫常見初階深挖點。

## Files to Open First

- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationEntity.java` — JPA entity mapping.  
  `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationEntity.java` — JPA 實體對應。
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationRepositoryImpl.java` — Manual mapping adapter.  
  `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationRepositoryImpl.java` — 手動對應適配器。
- `sp2-springboot/docs/decisions/0007-h2-vs-sqlserver.md` — H2 vs SQL Server ADR.  
  `sp2-springboot/docs/decisions/0007-h2-vs-sqlserver.md` — H2 與 SQL Server ADR。

## Primary Question

### English

Why do both Application and ApplicationEntity exist?

### 中文

為何同時存在 Application 與 ApplicationEntity？

## Suggested Answer

### English

Application is the persistence-free aggregate. ApplicationEntity maps the applications table with STRING enums and LAZY one-to-many children. ApplicationRepositoryImpl performs toDomain/toEntity mapping. ddl-auto is validate; Flyway owns schema.

### 中文

Application 是無持久化注解的聚合。ApplicationEntity 對應 applications 表，enum 為 STRING，子集合為 LAZY。ApplicationRepositoryImpl 執行 toDomain／toEntity。ddl-auto 為 validate；結構由 Flyway 管理。

## Follow-up Question

### English

What FetchType is used for workflowHistories?

### 中文

workflowHistories 的 FetchType 為何？

## Follow-up Answer

### English

LAZY on the OneToMany. With open-in-view false, accessing the collection outside the transaction risks LazyInitializationException.

### 中文

OneToMany 為 LAZY。在 open-in-view false 下，於交易外存取集合可能觸發 LazyInitializationException。

## Interview Tip

### English

Why asked: DDD vs JPA boundary.
Answer first: aggregate vs entity split.
Keywords: LAZY, EnumType.STRING, validate, Flyway.
Follow-ups: N+1, surrogate vs business id.

### 中文

提問原因：DDD 與 JPA 邊界。
先答：聚合與實體分離。
關鍵詞：LAZY、EnumType.STRING、validate、Flyway。
追問：N+1、代理鍵與業務編號。

## Open Book Navigation

- [JPA and SQL topic](../open-book/topics/05-jpa-and-sql.md)  
  [JPA 與 SQL 主題](../open-book/topics/05-jpa-and-sql.md)
- [ApplicationEntity source-map](../open-book/source-map/infrastructure/ApplicationEntity.md)  
  [ApplicationEntity 類別地圖](../open-book/source-map/infrastructure/ApplicationEntity.md)
- [ApplicationRepositoryImpl source-map](../open-book/source-map/infrastructure/ApplicationRepositoryImpl.md)  
  [ApplicationRepositoryImpl 類別地圖](../open-book/source-map/infrastructure/ApplicationRepositoryImpl.md)
