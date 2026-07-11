# How to Run the Project

Canonical quick start: [../../README.md](../../README.md) § Quick Start. This page adds profile context and verification pointers.

Working directory unless noted: `sp2-springboot/`.

## Option A — Docker Compose (SQL Server + app)

```bash
cp .env.example .env
# Edit .env if needed
docker-compose up -d
```

| Check | URL / command |
| --- | --- |
| App | <http://localhost:8080> |
| Health | `curl http://localhost:8080/actuator/health` |
| Script | `chmod +x scripts/verify.sh && ./scripts/verify.sh` |

- Profile: **`staging`**
- DB: SQL Server (Compose)
- Migrations: `db/migration-sqlserver/`
- Demo users: README § Demo accounts

Design notes: [../design/17-deployment-design.md](../design/17-deployment-design.md).

## Option B — Local Maven (H2)

```bash
# Optional helper: scripts/start-dev.sh
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
# or: mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

| Check | URL / notes |
| --- | --- |
| App | <http://localhost:8080> |
| H2 console | <http://localhost:8080/h2-console> |
| Swagger | Enabled on `dev` (see springdoc path in running app / README) |
| Redis | Required for idempotent create when `tlbank.idempotency.store=redis` |

## Profiles (summary)

| Profile | Database | Typical use |
| --- | --- | --- |
| `dev` | H2 | Local coding |
| `staging` | SQL Server | Compose / manual CD |
| `prod` | SQL Server (env) | Config only — not deployed by this repo |

Full table: [../../README.md](../../README.md) § Environment Profiles · ADR [../decisions/0007-h2-vs-sqlserver.md](../decisions/0007-h2-vs-sqlserver.md).

## Tests

```bash
./mvnw clean verify
```

- Coverage report: `target/site/jacoco/index.html`
- Strategy: [../design/16-testing-strategy.md](../design/16-testing-strategy.md)
- Counts / categories: [../handbook/01-repository-handbook.md](../handbook/01-repository-handbook.md) §9

## CI vs local

| Action | Local | CI |
| --- | --- | --- |
| `mvn clean verify` | Yes | Yes (on relevant paths) |
| Trivy | Optional | Report-only in `ci.yml` |
| Image push | No | On `main` to GHCR |
| Staging deploy | Compose on your machine | Manual `workflow_dispatch` on self-hosted runner |

ADR: [../decisions/0004-use-github-actions.md](../decisions/0004-use-github-actions.md).

## Terraform (optional learning)

```bash
cd ../infra/local
terraform init
terraform plan
```

Does **not** create cloud resources — [../decisions/0005-use-terraform-local.md](../decisions/0005-use-terraform-local.md).

## Next

→ [03-debugging-guide.md](03-debugging-guide.md)
