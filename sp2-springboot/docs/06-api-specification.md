# 06 – API Specification

## 1. Conventions

- Base path: `/api/v1`
- All responses are wrapped in the standard envelope `ApiResponse<T>`:

```json
{
  "success": true,
  "data": { },
  "errorCode": null,
  "message": null,
  "fieldErrors": null,
  "timestamp": "2026-06-28T10:15:30"
}

```

On failure:

```json
{
  "success": false,
  "data": null,
  "errorCode": "APPLICATION_NOT_FOUND",
  "message": "Application not found: APP-20260628101500-1234",
  "fieldErrors": null,
  "timestamp": "2026-06-28T10:15:30"
}

```

- Validation failures additionally populate `fieldErrors`: `[{ "field": "applicant.mobile", "message": "..." }]`.
- Paginated list endpoints wrap content in `PageResponse<T>`: `{ content, page, size, totalElements, totalPages }`.
- Interactive documentation is served via springdoc-openapi at `/swagger-ui.html` (enabled in `dev`/`staging`,
  disabled in `prod` — see `17-deployment-design.md`).

- Authentication is **session-based** (see `07-security-design.md`), not bearer-token based. All endpoints
  below note their required role.

- Every endpoint additionally documents standard error responses via the `@StandardApiResponses` annotation:
  `200/400/401/403/404`.

## 2. Applications — `/api/v1/applications` (public)

| Method | Path | Auth | Description |
| --- | --- | --- | --- |
| `POST` | `/api/v1/applications` | None | Create a new application draft (`status=INIT`) |
| `GET` | `/api/v1/applications/{applicationId}` | None | Retrieve application detail (PII masked) |
| `POST` | `/api/v1/applications/{applicationId}/documents` | None | Upload one document (multipart) |
| `POST` | `/api/v1/applications/{applicationId}/actions/submit` | None | Submit for review |
| `POST` | `/api/v1/applications/{applicationId}/actions/cancel` | None | Cancel the application |

### `POST /api/v1/applications`

Supports an optional `Idempotency-Key` request header (see `09-module-design.md` §6). Request body:

```json
{
  "applicant": {
    "fullName": "John Doe",
    "nationalId": "A123456789",
    "mobile": "0912345678",
    "email": "user@example.com",
    "dateOfBirth": "1990-01-01",
    "address": { "city": "...", "district": "...", "street": "...", "zipCode": "..." }
  },
  "cardProductId": "CARD-PRODUCT-ID"
}

```

Response `data` → `ApplicationSummaryResponse { applicationId, status, createdAt }`. HTTP `201 Created`.

### `GET /api/v1/applications/{applicationId}`

Response `data` → `ApplicationDetailResponse`:

```json
{
  "applicationId": "APP-20260628101500-1234",
  "maskedApplicant": {
    "fullName": "John Doe",
    "maskedNationalId": "A123****89",
    "maskedMobile": "0912****78",
    "maskedEmail": "us****@example.com",
    "dateOfBirth": "1990-01-01",
    "city": "...", "district": "...", "street": "...", "zipCode": "..."
  },
  "cardProductName": "...",
  "status": "OTP_VERIFIED",
  "workflowHistories": [ { "historyId": 1, "fromStatus": "INIT", "toStatus": "OTP_VERIFIED", "operator": "APPLICANT", "remark": "OTP verified", "operatedAt": "..." } ],
  "documents": [ { "documentType": "NATIONAL_ID", "fileName": "id.jpg", "uploadedAt": "..." } ],
  "submittedAt": null,
  "createdAt": "..."
}

```

### `POST /api/v1/applications/{applicationId}/documents`

Multipart request: `documentType` (`NATIONAL_ID`|`INCOME_PROOF`|`RESIDENCE_PROOF`) + `file` (PDF/JPG/PNG, max
size from `UPLOAD.max.size.mb` system parameter, default 10 MB). Response `data` →
`DocumentUploadResponse { documentType, fileName, uploadedAt }`.

### `POST /api/v1/applications/{applicationId}/actions/submit`

No body. Transitions `DOCUMENT_UPLOADED → SUBMITTED`, publishes `ApplicationSubmittedEvent` (auto-creates a
`ReviewCase`, sends a "received" notification). Response `data` → `ApplicationSummaryResponse`.

**Precondition (Sprint 17):** all three document types (`NATIONAL_ID`, `INCOME_PROOF`, `RESIDENCE_PROOF`) must
already be uploaded; otherwise HTTP `400` with `errorCode` `INCOMPLETE_DOCUMENTS`.

### `POST /api/v1/applications/{applicationId}/actions/cancel`

```json
{ "reason": "Changed my mind" }

```

Only allowed from `INIT`, `OTP_VERIFIED`, `DOCUMENT_UPLOADED`. Response `data` → `ApplicationSummaryResponse`.

## 3. Products — `/api/v1/products` (public)

| Method | Path | Auth | Description |
| --- | --- | --- | --- |
| `GET` | `/api/v1/products` | None | List all enabled card products (cached) |

Response `data` → `List<CardProductResponse>`, each with `productId`, `productCode`, `productName`,
`cardType`, `features: [{ featureKey, featureValue }]`, `createdAt`.

## 4. OTP — `/api/v1/otp` (public)

| Method | Path | Auth | Description |
| --- | --- | --- | --- |
| `POST` | `/api/v1/otp/actions/send` | None | Generate + send an OTP for a mobile number |
| `POST` | `/api/v1/otp/actions/verify` | None | Verify an OTP code, advances application status |

### `POST /api/v1/otp/actions/send`

```json
{ "applicationId": "APP-20260628101500-1234", "mobile": "0912345678", "purpose": "APPLICATION_VERIFICATION" }

```

Response `data` → `OtpResponse { maskedMobile, expiredAt, remainingRetry }`. The plaintext OTP is **never**
returned in the API response — it is only delivered via the (mocked) SMS/email channel.

### `POST /api/v1/otp/actions/verify`

```json
{ "applicationId": "APP-20260628101500-1234", "mobile": "0912345678", "otpCode": "123456" }

```

Response `data` → `VerifyOtpResponse { verified, applicationId }`. Errors: `OTP_EXPIRED`, `OTP_MISMATCH`,
`OTP_RETRY_EXCEEDED`, `INVALID_WORKFLOW_TRANSITION`.

## 5. Credit Review — `/api/v1/review/cases` (role: `REVIEWER` or `ADMIN`)

| Method | Path | Description |
| --- | --- | --- |
| `GET` | `/api/v1/review/cases` | Paginated search (filters: `status`, `applicantName`, `productId`, `dateFrom`, `dateTo`, `page`, `size`) |
| `GET` | `/api/v1/review/cases/{reviewCaseId}` | Case detail (includes masked applicant + linked application) |
| `POST` | `/api/v1/review/cases/{reviewCaseId}/actions/approve` | Approve `{ "remark": "..." }` |
| `POST` | `/api/v1/review/cases/{reviewCaseId}/actions/reject` | Reject `{ "remark": "..." }` |
| `POST` | `/api/v1/review/cases/{reviewCaseId}/remarks` | Add remark `{ "content": "..." }` → `201 Created` |

`GET /api/v1/review/cases` response `data` → `PageResponse<ReviewCaseSummaryResponse>`. Case detail response
`data` → `ReviewCaseDetailResponse` (review status + remarks + the full masked application snapshot,
workflow history, and documents — i.e. everything a reviewer needs in one call).

## 6. Admin — Users — `/api/v1/admin/users` (role: `ADMIN`)

| Method | Path | Description |
| --- | --- | --- |
| `GET` | `/api/v1/admin/users` | List all internal users |
| `POST` | `/api/v1/admin/users` | Create internal user `{ username, password (min 8 chars), fullName, email, roles: ["ADMIN"\|"REVIEWER"\|"USER"] }` |
| `GET` | `/api/v1/admin/users/{userId}` | Get user by business `userId` (`USR-XXXXXXXX`) |
| `PUT` | `/api/v1/admin/users/{userId}/status?enabled=true\|false` | Enable/disable a user account |

Response `data` → `UserResponse { userId, username, fullName, email, roles, enabled, lastLoginAt, createdAt }`.
Error `DUPLICATE_USERNAME` → HTTP `409`.

## 7. Admin — System Parameters — `/api/v1/admin/system-parameters` (role: `ADMIN`)

| Method | Path | Description |
| --- | --- | --- |
| `GET` | `/api/v1/admin/system-parameters?group=OTP` | List enabled parameters, optionally filtered by group |
| `PUT` | `/api/v1/admin/system-parameters/{paramId}` | Update a parameter's value `{ "paramValue": "..." }` |

Response `data` → `SystemParameterResponse { paramId, paramGroup, paramKey, paramValue, description, enabled }`.

## 8. Admin — Cache — `/api/v1/admin/cache` (role: `ADMIN`)

| Method | Path | Description |
| --- | --- | --- |
| `POST` | `/api/v1/admin/cache/refresh` | Refresh all caches (parameters + products) |
| `POST` | `/api/v1/admin/cache/refresh/system-parameters` | Refresh only the parameter cache |
| `POST` | `/api/v1/admin/cache/refresh/products` | Refresh only the card product cache |
| `GET` | `/api/v1/admin/cache/stats` | Current key count + estimated memory usage |

Response `data` → `CacheRefreshResponse { refreshedCount }` or `CacheStatsResponse { keyCount, estimatedMemoryBytes }`.

## 9. Admin — Reports — `/api/v1/reports` (role: `ADMIN`)

| Method | Path | Description |
| --- | --- | --- |
| `POST` | `/api/v1/reports/daily-statistics` | Generate + download a daily statistics report |

Request: `{ "reportDate": "2026-06-27", "format": "EXCEL" | "PDF" }`. Response: raw binary body
(`application/octet-stream` for Excel, `application/pdf` for PDF) with
`Content-Disposition: attachment; filename="daily-statistics-20260627.xlsx"` — **not** wrapped in
`ApiResponse` since the payload is a file, not JSON.

## 10. Admin — Schedulers — `/api/v1/admin/schedulers` (role: `ADMIN`)

| Method | Path | Description |
| --- | --- | --- |
| `POST` | `/api/v1/admin/schedulers/otp-cleanup/run` | Manually trigger OTP cleanup |
| `POST` | `/api/v1/admin/schedulers/cache-refresh/run` | Manually trigger cache refresh |
| `POST` | `/api/v1/admin/schedulers/daily-stats/run?date=2026-06-27` | Manually trigger daily statistics for a date |

Response `data` → a plain confirmation string (e.g. `"OTP cleanup triggered"`).

## 11. Admin — Audit Logs — `/api/v1/admin/audit-logs` (role: `ADMIN`)

| Method | Path | Description |
| --- | --- | --- |
| `GET` | `/api/v1/admin/audit-logs?username=&action=&dateFrom=&dateTo=&page=&size=` | Paginated, filtered audit log search |

Response `data` → `PageResponse<AuditLogResponse>`, each entry: `{ logId, username, action, ipAddress, result, detail, createdAt }`.

## 12. Admin — Notification Logs — `/api/v1/admin/notifications` (role: `ADMIN`)

| Method | Path | Description |
| --- | --- | --- |
| `GET` | `/api/v1/admin/notifications?page=&size=` | Paginated notification-related audit entries (OTP send, submit, approve, reject) |

Response `data` → `PageResponse<NotificationLogResponse>`, each entry: `{ logId, username, action, result, detail, otpCode, createdAt }` — `otpCode` is only populated for `OTP_SEND` entries and is extracted from the masked audit detail for display convenience in the admin UI; it is never the raw plaintext code from a live OTP record.

## 13. Auth — `/api/v1/auth` (form login, see `07-security-design.md`)

| Method | Path | Description |
| --- | --- | --- |
| `POST` | `/api/v1/auth/login` | Spring Security form login processing URL (`username`, `password` form fields) |
| `POST` | `/api/v1/auth/logout` | Logout, invalidates session |

These are **not** `@RestController` endpoints; they are configured directly on `SecurityFilterChain` and
handled by `LoginSuccessHandler` / `LoginFailureHandler` / `LogoutSuccessHandlerImpl`, which return either a
JSON `ApiResponse` (when the client `Accept`s JSON) or a browser redirect (see `LoginResponseMode`).

## 14. Error Code → HTTP Status Quick Reference

See `10-error-handling.md` for the full table. Common ones used across these endpoints: `400` (validation,
most `BusinessException`s), `401` (`UNAUTHORIZED`), `403` (`FORBIDDEN`), `404` (`*_NOT_FOUND`), `409`
(`INVALID_WORKFLOW_TRANSITION`, `DUPLICATE_USERNAME`, `IDEMPOTENCY_KEY_CONFLICT`,
`IDEMPOTENCY_KEY_IN_PROGRESS`), `500` (`SYSTEM_ERROR`).
