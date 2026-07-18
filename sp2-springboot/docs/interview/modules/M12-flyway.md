# M12 — Flyway / Flyway

## Why This Module Matters

### English

Schema ownership and dual migration trees (H2 vs SQL Server) per ADR 0007.

### 中文

結構擁有權與雙 migration 樹（H2 與 SQL Server），見 ADR 0007。

## Files to Open First

- `sp2-springboot/src/main/resources/application.yml` — Flyway enabled; ddl-auto validate.  
  `sp2-springboot/src/main/resources/application.yml` — 啟用 Flyway；ddl-auto validate。
- `sp2-springboot/src/main/resources/application-dev.yml` — migration + dev-seed locations.  
  `sp2-springboot/src/main/resources/application-dev.yml` — migration 與 dev-seed 位置。
- `sp2-springboot/docs/decisions/0007-h2-vs-sqlserver.md` — Dual DB ADR.  
  `sp2-springboot/docs/decisions/0007-h2-vs-sqlserver.md` — 雙資料庫 ADR。

## Primary Question

### English

Who owns schema changes—Hibernate or Flyway?

### 中文

結構變更由 Hibernate 還是 Flyway 擁有？

## Suggested Answer

### English

Flyway owns versioned SQL. spring.jpa.hibernate.ddl-auto is validate only. Editing an already-applied script causes checksum mismatch on next startup.

### 中文

Flyway 擁有版本化 SQL。spring.jpa.hibernate.ddl-auto 僅 validate。修改已套用腳本會在下次啟動造成 checksum 不符。

## Follow-up Question

### English

Why two migration location trees?

### 中文

為何有兩套 migration 位置？

## Follow-up Answer

### English

Dev uses classpath:db/migration plus db/dev-seed on H2. Staging/prod use classpath:db/migration-sqlserver for SQL Server. H2 MODE=MSSQLServer is partial compatibility only.

### 中文

dev 使用 classpath:db/migration 與 db/dev-seed（H2）。staging／prod 使用 classpath:db/migration-sqlserver（SQL Server）。H2 MODE=MSSQLServer 僅部分相容。

## Interview Tip

### English

Why asked: migration discipline.
Answer first: Flyway + validate.
Keywords: checksum, locations, ADR 0007.
Follow-ups: seed data scope, repair options.

### 中文

提問原因：遷移紀律。
先答：Flyway + validate。
關鍵詞：checksum、locations、ADR 0007。
追問：seed 範圍、修復方式。

## Open Book Navigation

- [JPA and SQL topic](../open-book/topics/05-jpa-and-sql.md)  
  [JPA 與 SQL 主題](../open-book/topics/05-jpa-and-sql.md)
