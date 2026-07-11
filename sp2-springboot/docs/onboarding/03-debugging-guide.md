# Debugging Guide

Common failure modes and where to look. Prefer logs + linked design docs over guessing.

## 1. App will not start

| Symptom | Check |
| --- | --- |
| Wrong Java version | `java -version` must be 17 |
| Port 8080 busy | Stop other processes or change port in run config |
| Maven dependency errors | `./mvnw -U clean compile` |
| Profile missing | Ensure `dev` or Compose `staging` is actually active |

Config entry points: `src/main/resources/application.yml` and `application-*.yml`.

## 2. Redis / idempotency errors on `dev`

**Symptom:** failures around application create when `Idempotency-Key` is used, or Redis connection refused.

**Cause:** `application-dev.yml` sets `tlbank.idempotency.store=redis`.

**Actions:**

1. Start Redis on `localhost:6379`, or  
2. Avoid that code path while debugging other features, or  
3. Understand test profile uses in-memory store — do not assume test YAML equals `dev` YAML.

Detail: [../decisions/0003-use-redis-idempotency.md](../decisions/0003-use-redis-idempotency.md) · code: `IdempotencyService`, `RedisIdempotencyStore`.

## 3. Docker Compose / SQL Server issues

| Symptom | Check |
| --- | --- |
| Container unhealthy | `docker compose ps`, container logs |
| Flyway errors | SQL Server migration scripts under `db/migration-sqlserver/` |
| Auth to DB | `.env` vs `.env.example` values |
| App up, DB empty/wrong | Confirm `staging` profile and migration locations |

Design: [../design/17-deployment-design.md](../design/17-deployment-design.md) · [../design/05-database-design.md](../design/05-database-design.md).

## 4. Security / login problems

| Symptom | Check |
| --- | --- |
| 401/403 on admin/review | Role and session — `SecurityConfig`, demo users in README |
| CSRF on browser forms | Web CSRF enabled; API posture differs — [../design/07-security-design.md](../design/07-security-design.md) |
| Kicked on second login | `maximumSessions(1)` is intentional — [../decisions/0006-session-over-jwt.md](../decisions/0006-session-over-jwt.md) |
| Applicant API open | `permitAll` on apply paths is a **demo** choice, not a bug to “fix” casually |

## 5. Workflow / business rule errors

| Symptom | Check |
| --- | --- |
| HTTP 409 / workflow errors | `Application.transitionTo()`, `ApplicationStatus` allowed edges |
| Cannot upload / submit | Status prerequisites — [../design/08-workflow-design.md](../design/08-workflow-design.md) |
| OTP failures | Expiry / retry limits via system parameters — OTP feature in [../handbook/03-business-feature-handbook.md](../handbook/03-business-feature-handbook.md) |

## 6. Tests failing locally

```bash
./mvnw clean verify
```

- Prefer fixing with the same profile CI uses (see `ci.yml`).
- Domain failures → unit tests under `src/test/java/**` near aggregates.
- Security/API failures → `*IntegrationTest` classes.
- Strategy: [../design/16-testing-strategy.md](../design/16-testing-strategy.md).

## 7. Where logs and health live

- Actuator health: `/actuator/health`
- Logging: `logback-spring.xml`, MDC correlation filter (see technology handbook logging chapter)
- Audit trail: `audit_logs` / admin audit APIs — [../design/11-audit-logging.md](../design/11-audit-logging.md)

## 8. Still stuck?

1. Reproduce with the smallest command from [02-how-to-run-the-project.md](02-how-to-run-the-project.md).  
2. Locate the feature in [../handbook/08-file-reference-handbook.md](../handbook/08-file-reference-handbook.md).  
3. Read the matching section in [../handbook/02-architecture-handbook.md](../handbook/02-architecture-handbook.md).  
4. Check whether the issue is a **known gap**: [../design/20-maintenance-and-future-enhancement.md](../design/20-maintenance-and-future-enhancement.md).

## Next

→ [04-code-reading-order.md](04-code-reading-order.md)
