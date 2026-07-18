# M22 — Report / 報表

## Why This Module Matters

### English

Daily statistics export via Excel/PDF generators and audited orchestration.

### 中文

以 Excel／PDF 產生器匯出日統計，並經稽核編排。

## Files to Open First

- `sp2-springboot/src/main/java/com/tlbank/lending/application/report/service/ReportAppService.java` — Report orchestration.  
  `sp2-springboot/src/main/java/com/tlbank/lending/application/report/service/ReportAppService.java` — 報表編排。
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/report/ExcelReportGenerator.java` — POI Excel bytes.  
  `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/report/ExcelReportGenerator.java` — POI Excel 位元組。
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/report/PdfReportGenerator.java` — PDF bytes.  
  `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/report/PdfReportGenerator.java` — PDF 位元組。

## Primary Question

### English

How is a daily statistics report produced?

### 中文

日統計報表如何產生？

## Suggested Answer

### English

ReportAppService.generateDailyStatisticsReport builds data through ReportDataService, then calls ExcelReportGenerator or PdfReportGenerator and returns byte[]. The method is @Auditable(REPORT_EXPORT). Bytes are not written through LocalDocumentStorageService.

### 中文

ReportAppService.generateDailyStatisticsReport 經 ReportDataService 組資料，再呼叫 ExcelReportGenerator 或 PdfReportGenerator 並回傳 byte[]。方法帶 @Auditable(REPORT_EXPORT)。位元組不經 LocalDocumentStorageService 寫入。

## Follow-up Question

### English

Is depending on concrete generators a layering leak?

### 中文

直接依賴具體產生器是否為分層洩漏？

## Follow-up Answer

### English

Yes. The application service injects infrastructure generator classes directly. That is a known impurity similar to the cache management concrete dependency.

### 中文

是。application service 直接注入 infrastructure 產生器類別。此為已知雜質，類似快取管理的具體類依賴。

## Interview Tip

### English

Why asked: reporting + layering honesty.
Answer first: orchestrate then render bytes.
Keywords: ReportFormat, REPORT_EXPORT, in-memory bytes.
Follow-ups: admin role on ReportApiController.

### 中文

提問原因：報表 + 分層誠實度。
先答：編排後渲染位元組。
關鍵詞：ReportFormat、REPORT_EXPORT、記憶體位元組。
追問：ReportApiController 的管理角色。

## Open Book Navigation

- [ReportAppService source-map](../open-book/source-map/application/ReportAppService.md)  
  [ReportAppService 類別地圖](../open-book/source-map/application/ReportAppService.md)
- [Audit topic](../open-book/topics/10-audit-logging.md)  
  [稽核主題](../open-book/topics/10-audit-logging.md)
