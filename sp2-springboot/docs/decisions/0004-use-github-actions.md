# ADR 0004 — Use GitHub Actions for CI and Manual CD

- **Status:** Accepted
- **Module:** monorepo workflows under `.github/workflows/`
- **Code anchors:** `.github/workflows/ci.yml`, `.github/workflows/terraform.yml`, `.github/workflows/markdown.yml`

## Context

The backend must be verified on every relevant change, images should be publishable, and staging runs on a **local self-hosted Mac** rather than a cloud account. Inbound SSH to that Mac is undesirable.

See [../../README.md](../../README.md) § CI/CD, [../handbook/01-repository-handbook.md](../handbook/01-repository-handbook.md) §12, and [../design/17-deployment-design.md](../design/17-deployment-design.md).

## Decision

- **CI (automatic):** on push/PR affecting `sp2-springboot/**`, run `mvn clean verify`, Trivy filesystem scan (**report-only**, `exit-code: 0`), and on `main` build/push `ghcr.io/<owner>/tlbank-backend` tags.
- **CD (manual):** `workflow_dispatch` job on a **self-hosted macOS** runner writes Compose config, pulls the GHCR image, and restarts the local SQL Server + app stack.
- Separate workflows validate Terraform (`infra/local`) and Markdown lint.

## Alternatives Considered

| Alternative | Why not chosen |
| --- | --- |
| Auto-deploy on every `main` push | Staging is a personal Mac; deploys must stay intentional |
| Cloud CD (ECS/GKE/AKS) | Out of current portfolio scope — no cloud prod in repo |
| Jenkins / self-hosted CI only | GitHub-hosted runners already fit Maven/Trivy/GHCR |

## Trade-offs

| Gain | Cost |
| --- | --- |
| Reproducible verify + image publish | No blue/green or canary |
| Outbound-only self-hosted runner | Operator must trigger deploy manually |
| Trivy visibility without blocking learning velocity | HIGH/CRITICAL findings do not fail CI today |

## Consequences

- Everyday confidence comes from `mvn clean verify` in CI.
- Image availability on GHCR does not imply staging was updated.
- Interview answers should distinguish CI vs CD clearly ([../handbook/05-interview-handbook.md](../handbook/05-interview-handbook.md) §7).

## Future Improvements

- Make Trivy blocking when the vulnerability baseline is acceptable.
- Add health-gated automated promote to a real non-prod cloud environment.
- Replace or supplement the single self-hosted runner with cloud runners + OIDC deploy.
