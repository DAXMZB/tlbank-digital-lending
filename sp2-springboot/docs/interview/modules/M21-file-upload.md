# M21 — File Upload / 檔案上傳

## Why This Module Matters

### English

Local filesystem document storage and DocumentType gates on submit.

### 中文

本機檔案文件儲存，以及提交時的 DocumentType 閘道。

## Files to Open First

- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/storage/LocalDocumentStorageService.java` — Local FS storage.  
  `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/storage/LocalDocumentStorageService.java` — 本機檔案儲存。
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/DocumentType.java` — Required document enum.  
  `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/DocumentType.java` — 必要文件列舉。
- `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/v1/ApplicationApiController.java` — Multipart upload endpoint.  
  `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/v1/ApplicationApiController.java` — Multipart 上傳端點。

## Primary Question

### English

Where are uploaded files stored?

### 中文

上傳檔案存在何處？

## Suggested Answer

### English

Local disk via LocalDocumentStorageService using tlbank.upload.base-path (default ./uploads). Object storage is not implemented.

### 中文

經 LocalDocumentStorageService 寫入本機磁碟，路徑為 tlbank.upload.base-path（預設 ./uploads）。物件儲存未實作。

## Follow-up Question

### English

Which document types are required before submit?

### 中文

提交前需要哪些文件類型？

## Follow-up Answer

### English

DocumentType enum values NATIONAL_ID, INCOME_PROOF, and RESIDENCE_PROOF. Application.submit validates required documents before transitioning to SUBMITTED.

### 中文

DocumentType 列舉值 NATIONAL_ID、INCOME_PROOF、RESIDENCE_PROOF。Application.submit 在轉為 SUBMITTED 前驗證必要文件。

## Interview Tip

### English

Why asked: storage honesty + domain gate.
Answer first: local FS path property.
Keywords: DocumentType, uploadDocuments, base-path.
Follow-ups: multi-instance disk divergence.

### 中文

提問原因：儲存誠實度 + 領域閘道。
先答：本機路徑設定。
關鍵詞：DocumentType、uploadDocuments、base-path。
追問：多實例磁碟不一致。

## Open Book Navigation

- [LocalDocumentStorageService source-map](../open-book/source-map/infrastructure/LocalDocumentStorageService.md)  
  [LocalDocumentStorageService 類別地圖](../open-book/source-map/infrastructure/LocalDocumentStorageService.md)
- [Domain and workflow](../open-book/topics/04-domain-and-workflow.md)  
  [領域與流程](../open-book/topics/04-domain-and-workflow.md)
