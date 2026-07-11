# ADR 0006 — Prefer Session Authentication over JWT

- **Status:** Accepted
- **Module:** `sp2-springboot`
- **Code anchors:** `security.config.SecurityConfig` (`SessionCreationPolicy.IF_REQUIRED`, `maximumSessions(1)`), form login to `/api/v1/auth/login`, handlers under `security.handler`

## Context

Primary staff UX is **server-rendered Thymeleaf** (admin/review). Applicants use a browser apply flow. The system needs logout, session timeout, and single concurrent session control without building a token client stack first.

See [../design/07-security-design.md](../design/07-security-design.md) and [../handbook/03-business-feature-handbook.md](../handbook/03-business-feature-handbook.md) § Login.

## Decision

- Use **Spring Security form login** with **server-side HTTP sessions**.
- Store passwords with **BCrypt (strength 12)**.
- Enforce **`maximumSessions(1)`** via session management configuration.
- Keep CSRF enabled for web form flows; API CSRF posture follows `SecurityConfig` (API CSRF relaxed for `/api/**` as implemented).
- Protect admin/review routes with roles; applicant apply/OTP/application APIs are currently `permitAll` for the demo.

## Alternatives Considered

| Alternative | Why not chosen (for now) |
| --- | --- |
| JWT / OAuth2 resource server everywhere | Heavier for Thymeleaf logout/session UX |
| Stateless JWT for staff UI | Poor fit for server-rendered forms without extra BFF work |
| External IdP only | Out of current portfolio scope |

## Trade-offs

| Gain | Cost |
| --- | --- |
| Simple logout and session invalidation | Sessions are JVM-local — not multi-instance ready |
| Concurrent session control works with `SessionRegistry` | Horizontal scale needs Spring Session (e.g. Redis) |
| Matches browser portals | Applicant APIs open for demo — not production-safe |

## Consequences

- Security story in interviews should contrast sessions (UI) vs future JWT (`/api/v2/**`) without rewriting the UI chain ([../design/20-maintenance-and-future-enhancement.md](../design/20-maintenance-and-future-enhancement.md) §3.1).
- Load-balanced replicas are blocked until sessions are externalized ([../handbook/06-system-design-handbook.md](../handbook/06-system-design-handbook.md)).

## Future Improvements

- Add a second filter chain for token-based API clients.
- Externalize sessions (Spring Session + Redis).
- Tighten applicant endpoints, add OTP rate limiting, strengthen password policy beyond `@Size(min = 8)` where applicable.
