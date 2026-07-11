# Local Development Setup

Prerequisites and workspace setup for `sp2-springboot`. Commands and profile details stay authoritative in [../../README.md](../../README.md); this page is the checklist.

## 1. Required tools

| Tool | Why |
| --- | --- |
| JDK **17** | `pom.xml` / CI Temurin 17 |
| Maven Wrapper | `./mvnw` in module (or system Maven 3.9+) |
| Git | Clone / PR workflow |
| Docker Desktop (optional but recommended) | Compose staging with SQL Server |
| Redis (optional for `dev`) | `application-dev.yml` sets `tlbank.idempotency.store=redis` |

Optional: IDE with Spring/Boot support (IntelliJ IDEA recommended).

## 2. Repository layout (what you care about)

```text
Project2/                         # git root
├── .github/workflows/            # CI/CD
├── infra/local/                  # Terraform local provider
└── sp2-springboot/               # ★ this module
    ├── src/main/java/com/tlbank/lending/
    ├── src/main/resources/
    ├── src/test/
    ├── docker-compose.yml
    ├── pom.xml
    └── docs/
```

More detail: [../handbook/01-repository-handbook.md](../handbook/01-repository-handbook.md) §2.

## 3. Clone and enter module

```bash
cd sp2-springboot
./mvnw -v
java -version   # expect 17.x
```

## 4. Choose a runtime path

| Path | Use when | Next doc section |
| --- | --- | --- |
| **A — Docker Compose** | Want SQL Server + app like staging | [02-how-to-run-the-project.md](02-how-to-run-the-project.md) § Option A |
| **B — Maven `dev`** | Fast H2 loop in IDE/CLI | [02-how-to-run-the-project.md](02-how-to-run-the-project.md) § Option B |

Decision background: [../decisions/0007-h2-vs-sqlserver.md](../decisions/0007-h2-vs-sqlserver.md).

## 5. Redis note for `dev`

If you run `dev` and exercise **idempotent application create**, start Redis on `localhost:6379` or expect connection errors on that path. Tests use an in-memory store via test resources.

Background: [../decisions/0003-use-redis-idempotency.md](../decisions/0003-use-redis-idempotency.md).

## 6. IDE tips

- Mark `sp2-springboot` as the Maven project root.
- Use Active Profile `dev` for local Run Configurations.
- Open [../handbook/08-file-reference-handbook.md](../handbook/08-file-reference-handbook.md) when hunting packages.

## 7. Verify tooling

```bash
./mvnw -q -DskipTests compile
./mvnw -q test -Dtest=ApplicationTest
```

Full suite belongs in [02-how-to-run-the-project.md](02-how-to-run-the-project.md).

## Next

→ [02-how-to-run-the-project.md](02-how-to-run-the-project.md)
