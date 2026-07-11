# ADR 0005 — Use Terraform with the Local Provider

- **Status:** Accepted
- **Module:** `infra/local/` (monorepo root)
- **Code anchors:** `infra/local/*.tf`, `.github/workflows/terraform.yml`

## Context

The repository needs an Infrastructure-as-Code practice loop (`init` / `fmt` / `validate` / `plan`, state handling) without creating billable cloud resources.

See [../../README.md](../../README.md) § Infrastructure as Code and [../handbook/04-technology-handbook.md](../handbook/04-technology-handbook.md) § Terraform.

## Decision

- Keep Terraform under `infra/local/`.
- Use the **`hashicorp/local`** provider to generate documentation artifacts (e.g. staging environment notes such as `generated-staging-env.md`).
- Validate Terraform in CI via `.github/workflows/terraform.yml`.
- **Do not** provision AWS, Azure, GCP, or other cloud infrastructure from this configuration.

## Alternatives Considered

| Alternative | Why not chosen |
| --- | --- |
| Real AWS/Azure modules now | Cost and credentials exceed portfolio goals |
| Skip IaC entirely | Misses a common interview and team skill |
| Only shell scripts for staging | Weaker reproducibility and CI validation story |

## Trade-offs

| Gain | Cost |
| --- | --- |
| Real Terraform workflow in CI | No actual servers/networks created |
| Safe for public portfolio clones | Must not be described as “cloud IaC production” |
| Clear upgrade path to cloud providers later | Local provider patterns differ from cloud modules |

## Consequences

- Staging runtime remains Docker Compose on a local machine, not Terraform-managed VMs.
- ADRs and README must stay honest: this is IaC **practice**, not cloud operations.
- Future cloud work should add new roots/modules rather than overloading `infra/local` silently.

## Future Improvements

- Introduce a separate `infra/cloud/` (or env-specific) stack when a real provider is adopted.
- Wire outputs to deployment workflows (image tag, connection strings via secrets managers).
- Add `terraform apply` automation only behind explicit environments and approvals.
