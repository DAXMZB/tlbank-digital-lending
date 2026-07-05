# 18 – Coding Standards

These standards are extracted from the patterns already consistently applied throughout the existing
codebase. Any new code (written by a human or by an AI coding agent following
`19-cursor-implementation-roadmap.md`) must follow them so the codebase stays internally consistent.

## 1. Layering Rules (Non-Negotiable)

1. Classes under `domain.*` must **never** import `org.springframework.*` (except
   `org.springframework.stereotype.Service`/`@Component` on `WorkflowDomainService`, which is the one
   accepted exception because it's a stateless domain service with no framework *behavior* dependency — it
   only uses the annotation for wiring), `jakarta.persistence.*`, or any web/HTTP type. Lombok annotations
   (`@Getter`, `@Builder`, `@RequiredArgsConstructor`) are fine — they are compile-time-only and produce no
   runtime framework dependency.
2. `infrastructure.persistence.*` classes implement `domain.*.repository.*` interfaces; they are the
   **only** place a JPA `@Entity` may exist.
3. `presentation.*` controllers depend only on `application.*` services and `common.response.*`/`common.exception.*`
   types — never directly on an `infrastructure.persistence.*` class or a repository port.
4. New cross-cutting behavior (logging, auditing, metrics) is implemented as a Spring AOP aspect in
   `common.*`, not by duplicating calls inside every application service.

## 2. Naming Conventions

See `03-package-structure.md` §3 for the full table. Highlights:

- Business identifiers are `record`s named `<Aggregate>Id` with a compact constructor that validates format
  and a static `generate()` and/or `of(value)` factory — never a bare `String` or `Long` passed around as an
  identifier once it leaves persistence code.
- JPA entities are always suffixed `Entity`; JPA `@Embeddable` types are always suffixed `Embeddable`.
- A repository **port** (interface, in `domain`) has no suffix beyond `Repository`; its **adapter**
  (implementation, in `infrastructure`) is suffixed `RepositoryImpl`.
- Application services are suffixed `AppService` (not `Service` alone, to avoid ambiguity with domain
  services and infrastructure `*Service` ports like `DocumentStorageService`/`NotificationService`).
- Use-case input/output records are suffixed `Command`/`Response` in the application layer, and
  `Request`/`Response` at the presentation boundary (a presentation `*Request` is mapped into an application
  `*Command` inside the controller or directly passed if the shapes coincide — see `OtpApiController`).

## 3. DTO / Command / Response Separation

Three distinct categories must not be collapsed into one:

| Category | Lives in | Validated with | Purpose |
| --- | --- | --- | --- |
| Request DTO | `application.dto.request` or `presentation.api.v1.<feature>` | `jakarta.validation` annotations (`@NotBlank`, `@Valid`, `@Pattern`, etc.) | Shape of the HTTP request body |
| Command | `application.<feature>.service` | Not separately validated (already validated upstream) | Input to a single use-case method, decoupled from HTTP concerns |
| Response | `application.<feature>.service` or `application.dto.response` | n/a (output only) | Shape returned to the controller; the controller wraps it in `ApiResponse` |

A `Request` and a `Command` are kept as **separate types** even when their fields are identical (e.g.
`SendOtpRequest` vs. `SendOtpCommand`), because they answer different questions: "what can the wire send us"
(future versioning point) vs. "what does this use case need" (stable contract for the application layer).

## 4. Lombok Usage Conventions

| Annotation | Where used | Why |
| --- | --- | --- |
| `@Getter` | Aggregates, entities | Read-only field access without hand-written boilerplate |
| `@Builder` (+ `@Builder.Default` for collections) | Aggregates, entities | Expressive, immutable-feeling construction even though the underlying class is mutable for JPA's sake |
| `@RequiredArgsConstructor` | Spring-managed beans with `final` fields | Constructor injection without boilerplate; never field injection (`@Autowired` on a field) anywhere in the codebase |
| `@Slf4j` | Any class that logs | Avoids manual `LoggerFactory.getLogger(...)` boilerplate |
| `@NoArgsConstructor` / `@AllArgsConstructor` | JPA entities only | Required by Hibernate; domain aggregates do **not** need these since they're never reflectively instantiated by a framework |
| `@Setter` | JPA entities only | Domain aggregates are intentionally **not** `@Setter` — all mutation happens through named behavior methods (`enable()`, `submit()`, `approve()`, etc.), never a generic setter, to keep business rules from being bypassed |
| `@UtilityClass` | Stateless helpers (`MaskingUtil`, `NotificationTemplate`, `AuditContext`, `CacheKeys` via `private` constructor instead) | Enforces non-instantiability and static access cleanly |

## 5. Records for Value Objects

Every value object is a Java `record` with validation in its compact constructor, e.g.:

```java
public record MobileNumber(String value) {
    private static final Pattern FORMAT = Pattern.compile("^09\\d{8}$");
    public MobileNumber {
        if (value == null || !FORMAT.matcher(value).matches()) {
            throw new IllegalArgumentException("Invalid mobile number format: " + value);
        }
    }
    public String masked() { ... }
    public static MobileNumber of(String value) { return new MobileNumber(value); }
}
```

This guarantees **no invalid value object can ever exist in memory** — validation happens at construction,
not as a separate, skippable step.

## 6. Aggregate Behavior Rules

- All state mutation happens through named, intention-revealing methods (`submit`, `cancel`, `approve`), each
  of which both validates the transition (via the enum's transition table or an explicit status check) and
  records any required audit/history value object (`WorkflowHistory`, `ReviewRemark`) **in the same method
  call** — never as two separate steps that could be called out of order.
- An aggregate never directly references another aggregate's object — only its identifier (`String`/`*Id`
  value object). Cross-aggregate consistency is achieved at the application-service or event-handler level,
  never by holding a live reference between aggregates.

## 7. Exception & Error Code Rules

See `10-error-handling.md` §6 for the full checklist when introducing a new error condition. In short:
throw `BusinessException`/`WorkflowException` with an `ErrorCode`, never a raw `RuntimeException`, for any
condition the API contract should expose to a caller.

## 8. PII Masking Rule

Any field carrying personally identifiable information (mobile number, national ID, email, full name) must
be passed through the matching `MaskingUtil` method before it is: (a) returned in an API response DTO meant
for anything other than the data's own owner, (b) written into an audit log detail string, or (c) logged at
any level. New PII fields introduced in the future must add a corresponding masking method to `MaskingUtil`
rather than ad-hoc masking logic at the call site.

## 9. Javadoc / `package-info.java` Convention

- Every package has a `package-info.java` with a one-paragraph Javadoc summary of its responsibility (see
  the existing files for tone/length — typically one sentence).
- Every public class has a one-to-two-sentence class-level Javadoc stating its role, written in the same
  voice as existing classes (e.g. *"Aggregate root representing a credit card application and its workflow
  lifecycle."*).
- Public methods that enforce a non-obvious business rule (e.g. `OtpRecord.verify`) document the specific
  exceptions/`ErrorCode`s they can throw and under what condition, using `@throws` Javadoc tags.

## 10. Testing Conventions

See `16-testing-strategy.md` §3 for full conventions (naming, fixed `Clock`, mocking policy). The short
version: domain logic gets plain JUnit 5 tests with no Spring context; application services get Mockito unit
tests; cross-module behavior gets a `@SpringBootTest`/`MockMvc` integration test against H2.

## 11. What "Done" Means for a New Use Case

A new use case is not complete until it has: (1) any new domain behavior/value object with validation and a
unit test, (2) an application service method with a Mockito-based unit test covering at least the happy path
and one failure path, (3) a controller method delegating to it, (4) an entry in `06-api-specification.md`,
(5) `@Auditable` if the action is business-significant (see `11-audit-logging.md` for which actions qualify),
and (6) PII masking applied to anything returned to a caller, per §8 above.
