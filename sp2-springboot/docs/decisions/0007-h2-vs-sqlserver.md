# ADR 0007 — Use H2 for Dev/Tests and SQL Server for Staging

- **Status:** Accepted
- **Module:** `sp2-springboot`
- **Code anchors:** `application-dev.yml` (H2 `MODE=MSSQLServer`), `application-staging.yml` / `application-prod.yml` (SQL Server), Flyway trees under `src/main/resources/db/migration` and `db/migration-sqlserver`

## Context

Engineers need fast local iteration and CI tests, while staging should exercise a production-like SQL dialect and Dockerized database. Hibernate is configured with schema **validation**, not auto-update.

See [../design/05-database-design.md](../design/05-database-design.md), [../design/17-deployment-design.md](../design/17-deployment-design.md), and [../../README.md](../../README.md) § Environment Profiles.

## Decision

| Profile | Database | Flyway locations (as configured) |
| --- | --- | --- |
| `dev` / tests | H2 in-memory | `db/migration` (+ `db/dev-seed` on dev) |
| `staging` / `prod` config | Microsoft SQL Server | `db/migration-sqlserver` |

- Keep **two migration trees** aligned by version as the project evolves.
- Run automated tests against H2 (`mvn clean verify`).
- Run Docker Compose staging against SQL Server.

## Alternatives Considered

| Alternative | Why not chosen |
| --- | --- |
| SQL Server for every local run | Slower feedback; heavier laptop/CI setup |
| Postgres everywhere | Staging Compose and docs target SQL Server |
| Hibernate `ddl-auto=update` in staging | Unsafe schema drift; Flyway is source of truth |

## Trade-offs

| Gain | Cost |
| --- | --- |
| Fast `dev` and CI | Dual maintenance of Flyway scripts |
| Staging closer to SQL Server semantics | H2 compatibility mode is not a perfect substitute |
| Clear profile story | No Testcontainers SQL Server stage yet |

## Consequences

- Developers must know which profile they are on before debugging SQL.
- Schema changes require updates in **both** migration directories when both environments matter.
- `prod` profile exists in config but is not deployed by this repository’s CD.

## Future Improvements

- Add a CI stage with Testcontainers SQL Server ([../design/20-maintenance-and-future-enhancement.md](../design/20-maintenance-and-future-enhancement.md)).
- Reduce dialect drift (shared fixtures, generation, or stricter review checklist).
- Document a single “schema change” checklist in onboarding when the team grows.
