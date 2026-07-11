# Diagrams

Visual documentation for the TLBank Digital Lending Platform (`sp2-springboot`).

This folder is the **canonical place for diagrams** used in design reviews, onboarding, and interviews. Prefer linking here from handbooks and ADRs instead of scattering one-off images across the repo.

## What belongs here

| Category | Examples |
| --- | --- |
| System Context | Actors, external systems, trust boundaries |
| Architecture | Layer / hexagonal dependency views |
| ERD | Tables and relationships |
| Sequence Diagrams | Apply, OTP, submit, review, approve |
| Workflow | Application and review state machines |
| Deployment | CI/CD, Compose staging, runner topology |
| Infrastructure | Local Terraform / future cloud sketches |

## Formats

Use whichever format fits the audience:

- **Mermaid** — preferred for docs that live in Git (renders on GitHub)
- **Draw.io** (`.drawio` / `.svg`) — editable architecture boards
- **PlantUML** — sequence / component diagrams in CI-friendly text
- **PNG / SVG** — exported snapshots for slides or READMEs

## Related documentation

| Need | Start here |
| --- | --- |
| Existing Mermaid in handbooks | [../handbook/02-architecture-handbook.md](../handbook/02-architecture-handbook.md) |
| System context (SDD) | [../design/01-system-context.md](../design/01-system-context.md) |
| Workflow / state machine | [../design/08-workflow-design.md](../design/08-workflow-design.md) |
| Deployment design | [../design/17-deployment-design.md](../design/17-deployment-design.md) |
| Scale / future topology | [../handbook/06-system-design-handbook.md](../handbook/06-system-design-handbook.md) |

## Conventions

1. Name files by category and topic, e.g. `architecture-layers.mmd`, `workflow-application-status.mmd`.
2. Keep source (Mermaid / Draw.io / PlantUML) in Git; commit PNG only when a rendered asset is required.
3. Every diagram should state **as-is** vs **target** when it describes a future design.
4. Do not invent production topology that is not documented in [../handbook/00-project-overview.md](../handbook/00-project-overview.md) or [../design/20-maintenance-and-future-enhancement.md](../design/20-maintenance-and-future-enhancement.md).

## Status

Starter folder. Add diagram sources here as they are extracted from handbooks or newly authored.
