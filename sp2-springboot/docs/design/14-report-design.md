# 14 – Report Generation Design

## 1. Purpose

Give administrators a downloadable, point-in-time snapshot of application volume and outcome for a given
calendar day, in either Excel or PDF, for offline analysis or sharing outside the system.

## 2. Pipeline

```mermaid
flowchart LR
    A[POST /api/v1/reports/daily-statistics] --> B[ReportAppService.generateDailyStatisticsReport]
    B --> C[ReportDataService.buildDailyStatistics(date)]
    C --> D[(ApplicationJpaRepository)]
    C --> E[(CardProductJpaRepository)]
    C --> F[DailyStatisticsData]
    F --> G{format}
    G -- EXCEL --> H[ExcelReportGenerator]
    G -- PDF --> I[PdfReportGenerator]
    H --> J[byte[]]
    I --> J[byte[]]
    J --> K[HTTP response: binary + Content-Disposition]

```

## 3. Data Aggregation (`ReportDataService`)

`buildDailyStatistics(date)` runs three repository-level aggregations scoped to
`[date 00:00, date+1 00:00)`:

| Query | Result |
| --- | --- |
| `countByCreatedAtBetween` | Total applications created that day |
| `countByStatusAndCreatedAtBetween` (`GROUP BY status`) | Count per `ApplicationStatus`, with **every** enum value pre-seeded to `0` so the report always shows a complete status breakdown even for statuses with no applications that day |
| `countByCardProductIdAndCreatedAtBetween` (`GROUP BY product_id`) | Count per product, with the product ID resolved to a human-readable `productName` via `CardProductJpaRepository`, falling back to `"Product-<id>"` if the product was since deleted |

Result type: `DailyStatisticsData(reportDate, totalApplications, statusCounts, productCounts)`.

## 4. Excel Generation (`ExcelReportGenerator`, Apache POI)

Produces an `.xlsx` workbook with two sheets:

| Sheet | Content |
| --- | --- |
| `Summary` | Title row, report date, total applications, then a bold header row (`Status`, `Count`, `Percentage`) followed by one row per `ApplicationStatus` with a computed percentage of the daily total |
| `By Product` | Bold header row (`Product`, `Count`) followed by one row per product; shows a `"No applications"` / `0` row if the product map is empty rather than an empty sheet |

Columns are auto-sized (`sheet.autoSizeColumn`) after population. The workbook is written to a
`ByteArrayOutputStream` and returned as `byte[]` — no temp file ever touches disk.

## 5. PDF Generation (`PdfReportGenerator`, iText 7)

Produces a single-page-style PDF with: a centered bold title, report date, total count, a "Status
Breakdown" table (`Status`/`Count`/`%`, same percentage calculation as the Excel sheet for consistency), a
"Product Breakdown" table (`Product`/`Count`, with the same empty-state fallback row), and a right-aligned
generation timestamp footer.

Both generators **independently** implement the identical percentage formula
(`count * 100.0 / total`, formatted `%.2f%%`, `"0.00%"` when total is zero) — duplicated rather than shared,
which is flagged as a minor DRY opportunity in `20-maintenance-and-future-enhancement.md` (a shared
`ReportFormattingUtil` would remove the duplication without changing behavior).

## 6. API Contract

`POST /api/v1/reports/daily-statistics` (role: `ADMIN`)

Request:

```json
{ "reportDate": "2026-06-27", "format": "EXCEL" }

```

Response: raw binary body, **not** wrapped in `ApiResponse` (the only endpoint in the platform that returns a
non-JSON success body), with:

```
Content-Type: application/octet-stream   (EXCEL)  |  application/pdf   (PDF)
Content-Disposition: attachment; filename="daily-statistics-20260627.xlsx"

```

The filename is built by `ReportAppService.getFileName(date, format)` using `DateTimeFormatter.BASIC_ISO_DATE`
(`yyyyMMdd`) plus the matching extension (`xlsx`/`pdf`).

## 7. Auditing

`generateDailyStatisticsReport` is annotated `@Auditable(action = AuditAction.REPORT_EXPORT)` — every report
download is recorded with the requesting admin's username, IP, and the sanitized request detail
(`reportDate`, `format`) via the standard `AuditDetailBuilder` path described in `11-audit-logging.md`.

## 8. Why Generation Is Synchronous (Not a Background Job)

Given the report only aggregates a single day's `applications` rows (bounded, indexed by `created_at` and
`status`), synchronous on-request generation is appropriately simple and fast; there is no need for an async
job + polling/download-link pattern at this data volume. If reports were extended to multi-month ranges or
much larger datasets, the natural evolution (see `20-maintenance-and-future-enhancement.md`) would be an
async job queue with a notification when the file is ready, reusing the same `ReportDataService` aggregation
logic.
