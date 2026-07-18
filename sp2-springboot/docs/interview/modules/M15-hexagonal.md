# M15 — Hexagonal Architecture / 六邊形架構

## Why This Module Matters

### English

Ports in domain, adapters in infrastructure, with known leaks named explicitly.

### 中文

Port 在 domain，adapter 在 infrastructure，並明確指出已知洩漏。

## Files to Open First

- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/repository/ApplicationRepository.java` — Persistence port.  
  `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/repository/ApplicationRepository.java` — 持久化 port。
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationRepositoryImpl.java` — JPA adapter.  
  `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationRepositoryImpl.java` — JPA 適配器。
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/storage/LocalDocumentStorageService.java` — Local FS adapter.  
  `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/storage/LocalDocumentStorageService.java` — 本機檔案適配器。
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/notification/MockSmsSender.java` — Mock notification adapter.  
  `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/notification/MockSmsSender.java` — Mock 通知適配器。

## Primary Question

### English

Identify a port and its adapter.

### 中文

指出一組 port 與其 adapter。

## Suggested Answer

### English

ApplicationRepository is the port. ApplicationRepositoryImpl is the JPA adapter. LocalDocumentStorageService adapts uploads to tlbank.upload.base-path. MockSmsSender/MockEmailSender adapt notifications under tlbank.notification.mode=mock.

### 中文

ApplicationRepository 為 port。ApplicationRepositoryImpl 為 JPA adapter。LocalDocumentStorageService 將上傳適配到 tlbank.upload.base-path。MockSmsSender／MockEmailSender 在 tlbank.notification.mode=mock 下適配通知。

## Follow-up Question

### English

Where does hexagonal layering leak?

### 中文

六邊形分層何處洩漏？

## Follow-up Answer

### English

CacheManagementService depends on concrete CachedCardProductRepository. ReportAppService depends on concrete Excel/Pdf generators. Those are review findings, not denials.

### 中文

CacheManagementService 依賴具體 CachedCardProductRepository。ReportAppService 依賴具體 Excel／Pdf generator。此為審查發現，不應否認。

## Interview Tip

### English

Why asked: ports/adapters fluency.
Answer first: repository port pair.
Keywords: mock notify, local FS, ADR 0001.
Follow-ups: swapping adapters, impurity fixes.

### 中文

提問原因：port／adapter 流暢度。
先答：儲存庫 port 配對。
關鍵詞：mock 通知、本機檔案、ADR 0001。
追問：替換 adapter、雜質修正。

## Open Book Navigation

- [Architecture topic](../open-book/topics/01-architecture.md)  
  [架構主題](../open-book/topics/01-architecture.md)
- [LocalDocumentStorageService source-map](../open-book/source-map/infrastructure/LocalDocumentStorageService.md)  
  [LocalDocumentStorageService 類別地圖](../open-book/source-map/infrastructure/LocalDocumentStorageService.md)
