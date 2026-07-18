# M26 — System Design / 系統設計

## Why This Module Matters

### English

As-is modular monolith constraints versus evolution docs that are not built.

### 中文

現況模組化單體約束，對照尚未建置的演進文件。

## Files to Open First

- `sp2-springboot/docs/handbook/06-system-design-handbook.md` — As-is vs evolution.  
  `sp2-springboot/docs/handbook/06-system-design-handbook.md` — 現況與演進。
- `sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md` — Maintenance backlog.  
  `sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md` — 維護待辦。
- `sp2-springboot/docs/decisions/0001-use-clean-architecture.md` — Current boundary.  
  `sp2-springboot/docs/decisions/0001-use-clean-architecture.md` — 現況邊界。

## Primary Question

### English

Scale to three instances—what fails first?

### 中文

擴到三個實例—何者先失效？

## Suggested Answer

### English

JVM sessions, in-memory cache coherence, local upload disks, and duplicate in-process schedulers. Start design answers from these as-is constraints, then cite handbook 06 / design 20 as evolution intent—not as running topology.

### 中文

JVM session、記憶體快取一致性、本機上傳磁碟，以及行程內排程重複觸發。設計回答須從這些現況約束出發，再引用 handbook 06／design 20 作為演進意圖—而非執行中拓撲。

## Follow-up Question

### English

Would splitting microservices tomorrow fix those issues?

### 中文

明日拆微服務能否直接解決？

## Follow-up Answer

### English

Not by itself. Shared session/cache/storage and job leadership must be solved at the adapter/ops layer. The codebase remains a modular monolith today.

### 中文

無法單靠拆分解決。共享 session／快取／儲存與任務領導權須在 adapter／營運層處理。現況仍為模組化單體。

## Interview Tip

### English

Why asked: design from reality.
Answer first: name as-is breakages.
Keywords: handbook 06, design 20, not implemented.
Follow-ups: outbox, object storage, Spring Session.

### 中文

提問原因：從現實做設計。
先答：點名現況破損點。
關鍵詞：handbook 06、design 20、未實作。
追問：outbox、物件儲存、Spring Session。

## Open Book Navigation

- [Delivery limitations](../open-book/topics/12-delivery-and-limitations.md)  
  [交付與限制](../open-book/topics/12-delivery-and-limitations.md)
- [Architecture](../open-book/topics/01-architecture.md)  
  [架構](../open-book/topics/01-architecture.md)
