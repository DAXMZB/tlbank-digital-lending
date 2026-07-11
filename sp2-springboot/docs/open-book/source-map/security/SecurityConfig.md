# SecurityConfig

- [Back to Open Book Home](../../README.md)
- [Back to Source Map Index](../README.md)
- Previous Critical Class: [ApplicationEntity](../infrastructure/ApplicationEntity.md)
- Next Critical Class: [IdempotencyService](../application/IdempotencyService.md)
- Related Topics: [topics/README.md](../../topics/README.md) (bodies **Pending** — Phase 3)
- Related Questions: [09-interview-source-map-300.md](../../../handbook/09-interview-source-map-300.md)

---
## One-Sentence Summary

Session-based Spring Security filter chain: form login, roles, CSRF carve-outs, MDC filter, max one session.

## 中文一句話

Session 認證主設定：表單登入、角色授權、API CSRF 忽略、MDC 濾器、同時僅一 session。

## Why This Class Exists

Centralize HTTP security for web + `/api/v1/**` without JWT in the current codebase.

See [0006-session-over-jwt.md](../../../decisions/0006-session-over-jwt.md).

## Responsibilities

- Beans: `PasswordEncoder` (BCrypt strength 12), `AuthenticationManager`, `SessionRegistry`, `SecurityFilterChain`
- Authorize request matchers (public apply/OTP/products vs REVIEWER/ADMIN)
- Form login/logout handlers; session IF_REQUIRED; maximumSessions(1)

## Runtime Execution Flow

1. Request hits filter chain; `MdcLoggingFilter` runs before username/password filter.
2. AuthorizeHttpRequests decides permit/role/authenticated.
3. Unauthenticated protected calls use entry point; forbidden uses access denied handler.
4. Login posts to `/api/v1/auth/login`; success/failure handlers run.
5. Logout invalidates session and deletes `JSESSIONID`.

## Dependencies

### Depends On

- `UserDetailsServiceImpl`, login/logout/access handlers, `MdcLoggingFilter`, `Environment`

### Called By

- Spring Boot security auto-config / context refresh
- Exercised by [SecurityIntegrationTest.java](../../../../src/test/java/com/tlbank/lending/security/SecurityIntegrationTest.java) (context), not a unit test named SecurityConfigTest

### Calls

- `HttpSecurity` DSL APIs

## Important Public Methods

### `PasswordEncoder passwordEncoder()`

- **Purpose:** BCryptPasswordEncoder(12)
- **Output:** PasswordEncoder bean


### `AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder)`

- **Purpose:** DaoAuthenticationProvider + ProviderManager
- **Output:** AuthenticationManager


### `SessionRegistry sessionRegistry()`

- **Purpose:** In-memory session registry for max sessions
- **Output:** SessionRegistry


### `SecurityFilterChain securityFilterChain(HttpSecurity http, SessionRegistry sessionRegistry)`

- **Purpose:** Main HTTP security DSL
- **Output:** SecurityFilterChain
- **Security behavior:** Defines public vs REVIEWER/ADMIN routes; session policy; CSRF ignore /api/**


## Design Decisions

- Session cookies, not JWT
- Many applicant APIs `permitAll` (portfolio choice — discuss as limitation)
- CSRF ignored for `/api/**`
- Dev profile disables frame options for H2 console

## Trade-offs and Alternatives

- JWT/stateless API — explicitly not chosen (ADR 0006)
- Tightening permitAll on applications would be a production hardening step

## Related Classes

- Handlers under `security/handler`, `MdcLoggingFilter`, `UserDetailsServiceImpl`
- Future topic `topics/03-security.md` (**Pending**)

## Related Configuration

- Spring Security defaults + profile-specific headers
- No Redis session store in this project

## Related Tests

- [SecurityIntegrationTest.java](../../../../src/test/java/com/tlbank/lending/security/SecurityIntegrationTest.java)
- **No `SecurityConfigTest` unit class**

## Related ADRs and Design Documents

- [0006-session-over-jwt.md](../../../decisions/0006-session-over-jwt.md)
- [07-security-design.md](../../../design/07-security-design.md)

## Related Interview Questions

[`Q017`](../../../handbook/09-interview-source-map-300.md#Q017), [`Q020`](../../../handbook/09-interview-source-map-300.md#Q020), [`Q023`](../../../handbook/09-interview-source-map-300.md#Q023), [`Q025`](../../../handbook/09-interview-source-map-300.md#Q025), [`Q033`](../../../handbook/09-interview-source-map-300.md#Q033), [`Q070`](../../../handbook/09-interview-source-map-300.md#Q070), [`Q077`](../../../handbook/09-interview-source-map-300.md#Q077), [`Q081`](../../../handbook/09-interview-source-map-300.md#Q081), [`Q083`](../../../handbook/09-interview-source-map-300.md#Q083), [`Q084`](../../../handbook/09-interview-source-map-300.md#Q084), [`Q085`](../../../handbook/09-interview-source-map-300.md#Q085), [`Q087`](../../../handbook/09-interview-source-map-300.md#Q087), [`Q112`](../../../handbook/09-interview-source-map-300.md#Q112), [`Q203`](../../../handbook/09-interview-source-map-300.md#Q203), [`Q207`](../../../handbook/09-interview-source-map-300.md#Q207), [`Q208`](../../../handbook/09-interview-source-map-300.md#Q208), [`Q250`](../../../handbook/09-interview-source-map-300.md#Q250)

## 30-Second Explanation

`SecurityConfig` builds a session SecurityFilterChain: BCrypt(12), form login to `/api/v1/auth/login`, role rules for review/admin, CSRF ignored on `/api/**`, and a single concurrent session.

## 2-Minute Explanation

Call out permitAll on OTP/products/many application routes versus REVIEWER/ADMIN for review/admin. Mention MDC filter placement and IF_REQUIRED sessions. Stress: not JWT, not Redis sessions.

## 5-Minute Deep Explanation

Walk matcher order, exception handling, logout cookie deletion, and production risks of broad permitAll. Link ADR 0006 for the session choice.

## 中文口語重點

- Session，不是 JWT
- BCrypt strength 12
- `/api/**` CSRF ignore
- maximumSessions(1)
- 申請相關多為 permitAll（需當限制說明）

## Whiteboard Sketch

- **What to draw:** filters → authorize → login/session
- **Drawing order:** MDC, authn, authz, handlers
- **Narration order:** public vs role routes

## Common Follow-Up Questions

- Why ignore CSRF on APIs?
- How would JWT change this class?
- What does maximumSessions(1) do?

## Common Mistakes

- Saying Redis backs sessions
- Claiming all APIs require authentication

## Current Limitations

- Broad permitAll on applicant APIs
- Session registry is in-memory (not clustered)

## Source File

[SecurityConfig.java](../../../../src/main/java/com/tlbank/lending/security/config/SecurityConfig.java)
