# 00 – Software Design Document Overview

## 1. Document Purpose

This Software Design Document (SDD) describes the design of **TLBank Digital Lending Platform**, a fictional
enterprise-style credit card application and credit review backend built with Java 17 and Spring Boot 3.

This document set is the single source of truth for:

- Engineers extending or maintaining the codebase
- Reviewers/interviewers evaluating the design as a portfolio project
- AI coding assistants (e.g. Cursor) that need authoritative context before generating or modifying code

> **⚠️ Fictional Project Disclaimer**
> "TLBank" is a fictional brand created solely for this portfolio project. It does **not** represent any real
> bank, financial institution, or company. All table names, business rules, identifiers, sample data, and
> domain language in this document are invented for learning and demonstration purposes only and must not be
> interpreted as describing any real production system, internal schema, or proprietary business logic.

## 2. Project Summary

| Item | Description |
|---|---|
| Project name | TLBank Digital Lending Platform |
| Domain | Digital credit card application intake, identity verification, and credit review |
| Project type | Fictional portfolio / learning project (GitHub + resume + interview discussion) |
| Primary users | Applicants (public), Credit Reviewers (internal), Administrators (internal) |
| Architecture style | Clean Architecture / Hexagonal Architecture + Domain-Driven Design (DDD) |
| Delivery model | Single deployable Spring Boot application (modular monolith) |
| Base package | `com.tlbank.lending` |
| Artifact name | `tlbank-lending` |

## 3. Business Goal (Fictional Narrative)

TLBank Digital Lending Platform allows a prospective customer to:

1. Browse available credit card products
2. Submit a digital credit card application with personal and contact information
3. Verify their identity via a mobile OTP (one-time password)
4. Upload supporting documents (ID, income proof, residence proof)
5. Submit the completed application for credit review
6. Receive SMS/email notifications at each key milestone

Internally, a **Credit Reviewer** picks up submitted applications as **review cases**, evaluates them, adds
remarks, and approves or rejects them. An **Administrator** manages internal users, system parameters, caches,
audit logs, notification logs, and exports operational reports.

## 4. Technology Stack

| Layer | Technology |
|---|---|
| Language / Runtime | Java 17 |
| Application framework | Spring Boot 3.4.x |
| Build tool | Maven (`mvnw` wrapper included) |
| Persistence | Spring Data JPA / Hibernate |
| Database (production/staging) | Microsoft SQL Server 2022 |
| Database (dev/test) | H2 (in-memory, `MODE=MSSQLServer` compatibility) |
| Schema migration | Flyway (separate migration sets for H2 and SQL Server) |
| Security | Spring Security 6 (session-based form login) |
| View layer | Thymeleaf + Bootstrap 5 |
| API documentation | springdoc-openapi (Swagger UI / OpenAPI 3) |
| Object mapping | MapStruct, Lombok |
| Caching | Custom in-process cache abstraction (`CacheStore`) |
| Idempotency store | Redis (`spring-boot-starter-data-redis`) with in-memory fallback |
| Report generation | Apache POI (Excel), iText 7 (PDF) |
| Testing | JUnit 5, Mockito, Spring Boot Test, Spring Security Test |
| Containerization | Docker (multi-stage build), Docker Compose |
| Observability | Spring Boot Actuator, SLF4J + Logback with MDC correlation IDs |

## 5. Architectural Principles

The codebase is intentionally engineered to demonstrate enterprise backend practices:

- **Clean / Hexagonal Architecture** – dependencies point inward; `domain` has no framework dependency on
  Spring, JPA, or web concerns. `infrastructure` and `presentation` depend on `domain` and `application`,
  never the reverse.
- **Domain-Driven Design** – aggregates (`Application`, `User`, `ReviewCase`, `CardProduct`, `OtpRecord`,
  `SystemParameter`), value objects (`ApplicationId`, `MobileNumber`, `Email`, `Address`, `Applicant`), domain
  events, and domain services (`WorkflowDomainService`) model the business language directly in code.
- **SOLID principles** – repository ports (interfaces) defined in `domain`, implemented by adapters in
  `infrastructure`; single-responsibility application services per use case; dependency inversion via
  constructor injection (`@RequiredArgsConstructor`).
- **Layered / Controller → Service → Repository** – REST and web controllers are thin; all business logic
  lives in application services and domain aggregates; repositories are the only persistence access point.
- **Cross-cutting concerns as aspects** – audit logging (`AuditAspect`), global exception translation
  (`GlobalExceptionHandler`), and idempotency (`IdempotencyService`) are implemented orthogonally so business
  code stays focused on its use case.

## 6. Document Map

| # | Document | Covers |
|---|---|---|
| 00 | sdd-overview | This document |
| 01 | system-context | Actors, external systems, context diagram |
| 02 | architecture-design | Clean Architecture layering, dependency rules |
| 03 | package-structure | Full package tree and conventions |
| 04 | domain-model | Aggregates, value objects, domain events |
| 05 | database-design | ER model, tables, migrations, indexes |
| 06 | api-specification | REST API contract per controller |
| 07 | security-design | AuthN/AuthZ, session management, handlers |
| 08 | workflow-design | Application & review state machines, sequence diagrams |
| 09 | module-design | Per-module design for all 16 functional modules |
| 10 | error-handling | Error codes, exception hierarchy, response envelope |
| 11 | audit-logging | Audit aspect, async writer, masking |
| 12 | cache-design | Cache abstraction, TTL, refresh, admin API |
| 13 | scheduler-design | Scheduled jobs, cron config, manual triggers |
| 14 | report-design | Excel/PDF report generation pipeline |
| 15 | file-upload-design | Document storage, validation rules |
| 16 | testing-strategy | Test pyramid, tooling, coverage policy |
| 17 | deployment-design | Docker, Compose, profiles, environment variables |
| 18 | coding-standards | Naming, layering rules, Lombok/DTO conventions |
| 19 | cursor-implementation-roadmap | Sprint-by-sprint build plan with Cursor prompts |
| 20 | maintenance-and-future-enhancement | Known debt, roadmap, versioning policy |

## 7. How to Use This Document Set

- **New contributors** should read `00` → `04` before touching code.
- **Cursor / AI coding agents** should be pointed at the whole `/docs` folder as context, then follow
  `19-cursor-implementation-roadmap.md` sprint by sprint, validating against `18-coding-standards.md` and the
  acceptance criteria of each sprint.
- **Module owners** should treat `09-module-design.md` as the canonical description of "what exists and why"
  for their module, and update it whenever behavior changes.

## 8. Document Conventions

- All identifiers in examples (table names, parameter keys, business IDs) are fictional and originate from
  this codebase only.
- Sequence and state diagrams use [Mermaid](https://mermaid.js.org/) syntax and render natively on GitHub.
- HTTP examples use the project's standard envelope, `ApiResponse<T>` (see `06-api-specification.md`).
