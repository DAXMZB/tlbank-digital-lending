# ADR 0003 — Use Redis for Application-Create Idempotency

- **Status:** Accepted
- **Module:** `sp2-springboot`
- **Code anchors:** `application.idempotency.IdempotencyService`, `infrastructure.idempotency.IdempotencyStore`, `RedisIdempotencyStore`, `InMemoryIdempotencyStore`
- **Config:** `tlbank.idempotency.*` in `application.yml` / `application-dev.yml`

## Context

Clients may retry `POST /api/v1/applications`. Without idempotency, retries can create duplicate applications. The portfolio also needs a concrete place to demonstrate distributed-style locks and TTL without claiming full multi-node readiness.

See [../handbook/01-repository-handbook.md](../handbook/01-repository-handbook.md) §8 and [../handbook/03-business-feature-handbook.md](../handbook/03-business-feature-handbook.md) § Idempotency.

## Decision

- Accept optional `Idempotency-Key` on application create.
- Route through `IdempotencyService` backed by pluggable `IdempotencyStore`.
- Use **`RedisIdempotencyStore`** when `tlbank.idempotency.store=redis` (`application-dev.yml`).
- Use **`InMemoryIdempotencyStore`** when `store=memory` (tests / fallback).
- Keep **application cache** on `InMemoryCacheStore` and **sessions** in the JVM — Redis is not used for those today.

## Alternatives Considered

| Alternative | Why not chosen (for now) |
| --- | --- |
| DB unique constraint only | Weaker replay of the original HTTP response body/status |
| Always in-memory store | Does not demonstrate Redis TTL/lock behavior |
| Redis for cache + sessions + idempotency immediately | Blurs teaching scope; Compose stack does not include Redis |

## Trade-offs

| Gain | Cost |
| --- | --- |
| Safe create retries with lock + cached response semantics | `dev` expects Redis at `localhost:6379` for that path |
| Clear port for swapping stores | Docker Compose does not run Redis; staging may omit store config |
| Narrow Redis story for interviews | Horizontal scale still blocked by in-memory cache/sessions |

## Consequences

- Idempotency keys use configured prefix (default style `idempotency:applications:`) and TTL hours from YAML.
- Conflicting body hash vs in-progress lock map to dedicated API errors (see API / business handbooks).
- Production-like environments must provision Redis (or choose `memory` knowingly).

## Future Improvements

- Add Redis to Compose / staging config when idempotency is required there.
- Add `RedisCacheStore` and Spring Session Redis when scaling beyond one instance ([../design/20-maintenance-and-future-enhancement.md](../design/20-maintenance-and-future-enhancement.md)).
- Optional Testcontainers Redis coverage in CI.
