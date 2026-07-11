# LocalDocumentStorageService

- [Back to Open Book Home](../../README.md)
- [Back to Source Map Index](../README.md)
- Previous Critical Class: [AuditAspect](../common/AuditAspect.md)
- Next Critical Class: [NotificationEventHandler](NotificationEventHandler.md)
- Related Topics: [topics/README.md](../../topics/README.md) (bodies **Pending** — Phase 3)
- Related Questions: [09-interview-source-map-300.md](../../../handbook/09-interview-source-map-300.md)

---
## One-Sentence Summary

Local filesystem `DocumentStorageService`: validate extension/size, store under `tlbank.upload.base-path`.

## 中文一句話

本機檔案上傳實作：副檔名／大小檢查後寫入設定目錄，回傳相對路徑。

## Why This Class Exists

Portfolio-friendly storage without cloud object storage.

**Not** S3/GCS unless added later — currently local disk only.

## Responsibilities

- `@PostConstruct` ensure base directory
- Validate empty/extension/size (param `UPLOAD/max.size.mb`, default 10)
- Store as `applicationId/TYPE_timestamp.ext`

## Runtime Execution Flow

1. `validate` checks file.
2. Build target dir `base/applicationId`.
3. Copy stream with REPLACE_EXISTING.
4. Return relative path string for DB `DocumentInfo`.

## Dependencies

### Depends On

- `SystemParameterService`, `Clock`
- `@Value tlbank.upload.base-path`

### Called By

- `ApplicationAppService.uploadDocuments` via `DocumentStorageService` port

### Calls

- `Files.createDirectories` / `Files.copy`

## Important Public Methods

### `void validate(MultipartFile file)`

- **Purpose:** Extension + size + non-empty checks
- **Input:** multipart file
- **Side effects:** May throw BusinessException DOCUMENT_UPLOAD_FAILED


### `String store(String applicationId, DocumentType documentType, MultipartFile file)`

- **Purpose:** Persist file and return relative path
- **Input:** applicationId, type, file
- **Output:** relative path
- **Side effects:** Writes disk; re-validates


## Design Decisions

- Allowed extensions: jpg/jpeg/png/pdf
- Absolute normalized base path
- Relative path stored in domain/DB, not absolute

## Trade-offs and Alternatives

- Local disk vs S3 — local chosen for this repo
- Size limit from system parameters enables runtime tuning

## Related Classes

- [ApplicationAppService](../application/ApplicationAppService.md), port `DocumentStorageService`

## Related Configuration

- `tlbank.upload.base-path` in [application.yml](../../../../src/main/resources/application.yml) (`./uploads`)
- [application-dev.yml](../../../../src/main/resources/application-dev.yml) (`.../uploads/dev`)
- [application-staging.yml](../../../../src/main/resources/application-staging.yml) (`APP_UPLOAD_PATH`)

## Related Tests

- [LocalDocumentStorageServiceTest.java](../../../../src/test/java/com/tlbank/lending/infrastructure/storage/LocalDocumentStorageServiceTest.java)

## Related ADRs and Design Documents

- [15-file-upload-design.md](../../../design/15-file-upload-design.md)

## Related Interview Questions

[`Q008`](../../../handbook/09-interview-source-map-300.md#Q008), [`Q053`](../../../handbook/09-interview-source-map-300.md#Q053), [`Q106`](../../../handbook/09-interview-source-map-300.md#Q106), [`Q177`](../../../handbook/09-interview-source-map-300.md#Q177), [`Q178`](../../../handbook/09-interview-source-map-300.md#Q178), [`Q179`](../../../handbook/09-interview-source-map-300.md#Q179), [`Q180`](../../../handbook/09-interview-source-map-300.md#Q180), [`Q181`](../../../handbook/09-interview-source-map-300.md#Q181), [`Q182`](../../../handbook/09-interview-source-map-300.md#Q182), [`Q208`](../../../handbook/09-interview-source-map-300.md#Q208), [`Q216`](../../../handbook/09-interview-source-map-300.md#Q216), [`Q255`](../../../handbook/09-interview-source-map-300.md#Q255), [`Q298`](../../../handbook/09-interview-source-map-300.md#Q298)

## 30-Second Explanation

`LocalDocumentStorageService` stores uploads on local disk under a configured base path after checking type and size. It returns a relative path for the application aggregate.

## 2-Minute Explanation

Cover allowed extensions, parameter-driven max size, directory layout, and why staging uses env path. Emphasize not cloud object storage.

## 5-Minute Deep Explanation

Discuss REPLACE_EXISTING, path normalization, and virus scanning as **Not implemented**.

## 中文口語重點

- 本機磁碟，不是 S3
- 副檔名白名單
- 路徑回傳相對字串
- base-path 依 profile

## Whiteboard Sketch

- **What to draw:** AppService → Storage → filesystem folders
- **Drawing order:** validate then store
- **Narration order:** config property first

## Common Follow-Up Questions

- Where is the max size configured?
- What is stored in DB vs disk?

## Common Mistakes

- Claiming cloud bucket integration
- Forgetting validate is also called from store

## Current Limitations

- Single-node filesystem (no shared volume story beyond staging path config)
- No malware scanning

## Source File

[LocalDocumentStorageService.java](../../../../src/main/java/com/tlbank/lending/infrastructure/storage/LocalDocumentStorageService.java)
