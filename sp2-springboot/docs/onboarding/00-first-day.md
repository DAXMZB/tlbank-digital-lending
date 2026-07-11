# First Day

Goal: understand what TLBank is, where docs live, and how to run a smoke check — without memorizing every module.

## 1. What you are joining

TLBank is a **modular monolith** Spring Boot backend for a fictional credit-card application and review flow. Staging is a **local Mac + Docker** setup; there is no cloud production environment in this repository.

Read once:

1. [../handbook/00-project-overview.md](../handbook/00-project-overview.md) — executive overview  
2. [../../README.md](../../README.md) — highlights, limitations, quick start  
3. [../decisions/README.md](../decisions/README.md) — why key choices were made  

## 2. Mental model (10 minutes)

```text
Applicant: products → apply → OTP → documents → submit
Reviewer:  review case → approve / reject
Admin:     users, parameters, cache, audit, reports, schedulers
```

State machine (simplified): see [../handbook/00-project-overview.md](../handbook/00-project-overview.md) §7 or [../design/08-workflow-design.md](../design/08-workflow-design.md).

Architecture rule: `presentation → application → domain ← infrastructure`  
Detail: [../decisions/0001-use-clean-architecture.md](../decisions/0001-use-clean-architecture.md).

## 3. Doc categories (do not read everything today)

| If you need… | Open |
| --- | --- |
| “Where is feature X?” | [../handbook/08-file-reference-handbook.md](../handbook/08-file-reference-handbook.md) |
| Layer / sequence detail | [../handbook/02-architecture-handbook.md](../handbook/02-architecture-handbook.md) |
| Business rules | [../handbook/03-business-feature-handbook.md](../handbook/03-business-feature-handbook.md) |
| Stack depth | [../handbook/04-technology-handbook.md](../handbook/04-technology-handbook.md) |
| Formal design | [../design/00-sdd-overview.md](../design/00-sdd-overview.md) |
| Known gaps | [../design/20-maintenance-and-future-enhancement.md](../design/20-maintenance-and-future-enhancement.md) |

## 4. First-day checklist

- [ ] JDK 17 available; can run Maven Wrapper from `sp2-springboot/`
- [ ] Skim [01-local-development-setup.md](01-local-development-setup.md)
- [ ] Run either Docker Compose **or** `dev` profile per [02-how-to-run-the-project.md](02-how-to-run-the-project.md)
- [ ] Hit `/actuator/health` successfully
- [ ] Run `mvn clean verify` once (or start it before leaving)
- [ ] Open `SecurityConfig` and `ApplicationStatus` (see [04-code-reading-order.md](04-code-reading-order.md))

## 5. People / process notes

- CI is the source of truth for “does it build?” — `.github/workflows/ci.yml`
- Deploy to staging is **manual** (`workflow_dispatch`) — [../decisions/0004-use-github-actions.md](../decisions/0004-use-github-actions.md)
- Ask before treating demo credentials or open applicant APIs as production-safe — they are not

## Next

→ [01-local-development-setup.md](01-local-development-setup.md)
