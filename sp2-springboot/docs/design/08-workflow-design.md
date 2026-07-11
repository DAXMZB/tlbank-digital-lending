# 08 – Workflow Design

## 1. Application Status State Machine

`ApplicationStatus` enforces a strict, explicit transition table (`ALLOWED_TRANSITIONS` in the enum itself —
see `04-domain-model.md` §3). Any other transition throws `WorkflowException` (HTTP `409`,
`INVALID_WORKFLOW_TRANSITION`).

```mermaid
stateDiagram-v2
    [*] --> INIT : createApplication()
    INIT --> OTP_VERIFIED : verifyOtp()
    INIT --> CANCELLED : cancel()
    OTP_VERIFIED --> DOCUMENT_UPLOADED : uploadDocuments()
    OTP_VERIFIED --> CANCELLED : cancel()
    DOCUMENT_UPLOADED --> SUBMITTED : submit()
    DOCUMENT_UPLOADED --> CANCELLED : cancel()
    SUBMITTED --> UNDER_REVIEW : startReview()
    UNDER_REVIEW --> APPROVED : approve()
    UNDER_REVIEW --> REJECTED : reject()
    APPROVED --> [*]
    REJECTED --> [*]
    CANCELLED --> [*]

```

Notes:

- `DOCUMENT_UPLOADED` is also a **valid self-loop target** in practice: `Application.uploadDocuments()`
  permits being called again while already in `DOCUMENT_UPLOADED` (to add more documents), without that
  being a *status transition* — it only appends to `documentInfos` once already in that status.

- `CANCELLED`, `APPROVED`, and `REJECTED` are terminal; no further transition is defined from them.
- `startReview()` is triggered automatically as part of the **review approve/reject flow**
  (`ReviewAppService.ensureApplicationUnderReview`) — a reviewer does not need to call a separate "start
  review" step on the *application* before approving/rejecting; calling `ReviewApiController`'s approve or
  reject endpoint transparently drives the application from `SUBMITTED` to `UNDER_REVIEW` to
  `APPROVED`/`REJECTED` within the same use case if needed. The explicit `startReview` web/API action exists
  primarily for the **review case's own** `PENDING → UNDER_REVIEW` transition (see §2) and to let a reviewer
  mark a case as "being worked on" before a final decision.

## 2. Review Case Status State Machine

`ReviewCase.reviewStatus` (`ReviewStatus`) is a second, related but independent state machine:

```mermaid
stateDiagram-v2
    [*] --> PENDING : ReviewCase.createFor(applicationId)\n(triggered by ApplicationSubmittedEvent)
    PENDING --> UNDER_REVIEW : startReview()
    UNDER_REVIEW --> APPROVED : approve()
    UNDER_REVIEW --> REJECTED : reject()
    APPROVED --> [*]
    REJECTED --> [*]

```

`addRemark()` is allowed in any `ReviewStatus` and does not change status.

## 3. Combined Lifecycle (Application ↔ ReviewCase)

| Application status | Typical ReviewCase status | Trigger |
| --- | --- | --- |
| `INIT` | *(no review case yet)* | `createApplication` |
| `OTP_VERIFIED` | *(no review case yet)* | `verifyOtp` |
| `DOCUMENT_UPLOADED` | *(no review case yet)* | `uploadDocuments` |
| `SUBMITTED` | `PENDING` | `submit` → publishes `ApplicationSubmittedEvent` → `ReviewEventHandler` creates the `ReviewCase` |
| `UNDER_REVIEW` | `UNDER_REVIEW` | reviewer calls `startReview`, or implicitly via approve/reject |
| `APPROVED` | `APPROVED` | reviewer calls `approveCase` |
| `REJECTED` | `REJECTED` | reviewer calls `rejectCase` |
| `CANCELLED` | *(unaffected — review case, if any, is left as-is)* | applicant calls `cancelApplication` |

## 4. End-to-End Sequence: OTP Verification

```mermaid
sequenceDiagram
    participant A as Applicant
    participant API as OtpApiController
    participant SVC as OtpAppService
    participant OTPREPO as OtpRepository
    participant APPREPO as ApplicationRepository
    participant NOTIF as NotificationService

    A->>API: POST /api/v1/otp/actions/send
    API->>SVC: sendOtp(command)
    SVC->>OTPREPO: findLatestPendingByMobile() -> cancel previous if present
    SVC->>SVC: read OTP.expire_minutes, OTP.max_retry from SystemParameterService
    SVC->>SVC: generate 6-digit code (SecureRandom)
    SVC->>OTPREPO: save(OtpRecord PENDING)
    SVC->>NOTIF: sendOtpNotification(mobile, code, expireMinutes)
    SVC-->>API: OtpResponse(maskedMobile, expiredAt, remainingRetry)
    API-->>A: 200 OK

    A->>API: POST /api/v1/otp/actions/verify {otpCode}
    API->>SVC: verifyOtp(command)
    SVC->>OTPREPO: findLatestPendingByMobile()
    SVC->>SVC: otpRecord.verify(code, maxRetry, clock)
    alt expired
        SVC-->>API: BusinessException(OTP_EXPIRED)
    else retry exceeded
        SVC-->>API: BusinessException(OTP_RETRY_EXCEEDED)
    else mismatch
        SVC-->>API: BusinessException(OTP_MISMATCH), retryCount++
    else success
        SVC->>APPREPO: load Application, application.verifyOtp("APPLICANT")
        SVC->>APPREPO: save(Application status=OTP_VERIFIED)
        SVC-->>API: VerifyOtpResponse(verified=true)
    end
    API-->>A: 200 OK / 4xx error

```

Precedence inside `OtpRecord.verify()` is significant and intentional: **expiry is checked first** (and
marks the record `EXPIRED` as a side effect even on a verify attempt), **then retry-limit**, **then code
match** (which increments `retryCount` on mismatch). This ordering means a user who waits too long always
gets `OTP_EXPIRED` rather than a stale `OTP_MISMATCH`, and a user who has exhausted retries gets a clear
`OTP_RETRY_EXCEEDED` rather than one more confusing mismatch message.

## 5. End-to-End Sequence: Submit → Auto Review Case → Approve

```mermaid
sequenceDiagram
    participant A as Applicant
    participant APPAPI as ApplicationApiController
    participant APPSVC as ApplicationAppService
    participant EVT as ApplicationEventPublisher
    participant REVH as ReviewEventHandler
    participant NOTIFH as NotificationEventHandler
    participant R as Credit Reviewer
    participant REVAPI as ReviewApiController
    participant REVSVC as ReviewAppService

    A->>APPAPI: POST /applications/{id}/actions/submit
    APPAPI->>APPSVC: submitApplication(id)
    APPSVC->>APPSVC: application.submit("APPLICANT") [DOCUMENT_UPLOADED -> SUBMITTED]
    APPSVC->>EVT: publish ApplicationSubmittedEvent
    EVT->>REVH: onApplicationSubmitted
    REVH->>REVH: ReviewCase.createFor(applicationId) [status=PENDING]
    EVT->>NOTIFH: onApplicationSubmitted
    NOTIFH->>NOTIFH: sendApplicationSubmittedNotification (SMS+email, best-effort)
    APPSVC-->>APPAPI: ApplicationSummaryResponse
    APPAPI-->>A: 200 OK

    R->>REVAPI: GET /review/cases?status=PENDING
    R->>REVAPI: POST /review/cases/{id}/actions/approve {remark}
    REVAPI->>REVSVC: approveCase(command)
    REVSVC->>REVSVC: reviewCase.approve(operator, remark) [UNDER_REVIEW? else implicit startReview first]
    REVSVC->>REVSVC: ensureApplicationUnderReview() -> application.startReview() if still SUBMITTED
    REVSVC->>REVSVC: application.approve(operator, remark) [-> APPROVED]
    REVSVC->>EVT: publish ApplicationApprovedEvent
    EVT->>NOTIFH: onApplicationApproved -> notify applicant
    REVSVC-->>REVAPI: void
    REVAPI-->>R: 200 OK

```

## 6. Failure Handling Within Workflows

- Every aggregate transition method validates the precondition itself and throws `WorkflowException`
  (mapped to HTTP `409`) on violation — controllers and application services never pre-check status with
  `if` statements; they simply call the method and let the domain enforce the rule. This keeps the rule in
  exactly one place.

- Notification failures (`NotificationEventHandler`) are caught and logged (`log.warn`) rather than
  propagated — a failed SMS/email must never roll back or fail the underlying workflow transition, since the
  transition is the business-critical fact and the notification is a best-effort side effect.

- `ReviewEventHandler.onApplicationSubmitted` runs as a default (synchronous, same-transaction-boundary)
  Spring `@EventListener`. If review-case creation were to fail, it would currently roll back the triggering
  transaction along with the application submission — this coupling is called out as a improvement
  opportunity in `20-maintenance-and-future-enhancement.md` (candidate: `@TransactionalEventListener(phase =
  AFTER_COMMIT)`).
