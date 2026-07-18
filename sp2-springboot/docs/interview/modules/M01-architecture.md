# M01 — Architecture / 架構

## Why This Module Matters

### English

This module covers the modular monolith layout and dependency rule used in TLBank. Interviewers open packages early to test whether layering is intentional.

### 中文

本模組說明 TLBank 的模組化單體分層與依賴規則。面試官常在開場開啟套件結構，檢驗分層是否為有意設計。

## Files to Open First

- `sp2-springboot/docs/decisions/0001-use-clean-architecture.md` — Layering ADR.  
  `sp2-springboot/docs/decisions/0001-use-clean-architecture.md` — 分層決策紀錄。
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java` — Domain aggregate without Spring/JPA annotations.  
  `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/Application.java` — 無 Spring／JPA 注解的領域聚合。
- `sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java` — Application-layer orchestration.  
  `sp2-springboot/src/main/java/com/tlbank/lending/application/application/service/ApplicationAppService.java` — 應用層用例編排。
- `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationRepositoryImpl.java` — Infrastructure adapter for the repository port.  
  `sp2-springboot/src/main/java/com/tlbank/lending/infrastructure/persistence/application/ApplicationRepositoryImpl.java` — 儲存庫 port 的基礎設施適配器。

## Primary Question

### English

Walk through the package structure. Where does business logic live?

### 中文

請說明套件結構。業務邏輯位於何處？

## Suggested Answer

### English

TLBank is a modular monolith with packages presentation, application, domain, infrastructure, security, and common. Business rules live on domain aggregates such as Application. Application services orchestrate use cases and transactions. Infrastructure adapters implement domain ports. Controllers stay thin.

### 中文

TLBank 為模組化單體，套件含 presentation、application、domain、infrastructure、security、common。業務規則位於領域聚合（例如 Application）。Application service 編排用例與交易。Infrastructure adapter 實作 domain port。Controller 保持精簡。

## Follow-up Question

### English

Point to one dependency-rule impurity in this repository.

### 中文

指出倉庫中一處依賴規則雜質。

## Follow-up Answer

### English

CacheManagementService injects CachedCardProductRepository concretely instead of depending only on a port. ReportAppService also depends on concrete Excel/Pdf generators. Those leaks are known and should be named rather than denied.

### 中文

CacheManagementService 直接注入 CachedCardProductRepository 具體類，而非只依賴 port。ReportAppService 亦直接依賴 Excel／Pdf generator。此類洩漏應主動說明，而非否認。

## Interview Tip

### English

Why the interviewer asks: to test architecture vocabulary and honesty about leaks.
Answer first: modular monolith + dependency direction.
Keywords: domain, port, adapter, ApplicationAppService, ADR 0001.
Likely follow-ups: impurities, transaction placement, microservices split.

### 中文

面試官目的：檢驗架構詞彙與對雜質的誠實度。
先答：模組化單體與依賴方向。
關鍵詞：domain、port、adapter、ApplicationAppService、ADR 0001。
常見追問：雜質、交易放置、是否拆微服務。

## Secondary Question

### English

Is this microservices or a modular monolith? Why?

### 中文

此系統是微服務還是模組化單體？原因為何？

## Suggested Answer (Secondary)

### English

It is one Spring Boot deployable—a modular monolith. Package seams exist, but there is a single process and shared database access through adapters. A split would be an evolution topic, not the current topology.

### 中文

現況為單一 Spring Boot 可部署單元，即模組化單體。套件邊界存在，但行程單一，並透過 adapter 存取資料庫。拆分為演進議題，非現況拓撲。

## Follow-up Question (Secondary)

### English

Which package boundaries would become service boundaries later?

### 中文

日後哪些套件邊界可能成為服務邊界？

## Follow-up Answer (Secondary)

### English

Review, OTP, reporting, and notification adapters are natural cut lines. Operational prerequisites such as shared session store and cache coherence are still single-node today.

### 中文

審核、OTP、報表與通知適配器是自然切割線。共享 session 與快取一致性等營運前提在現況仍屬單節點。

## Interview Tip (Secondary)

### English

Why asked: to stop overclaiming microservices.
Answer first: one deployable.
Keywords: modular monolith, ADR 0001.
Follow-ups: scaling limits, dual surface.

### 中文

提問原因：避免超宣稱微服務。
先答：單一可部署單元。
關鍵詞：modular monolith、ADR 0001。
追問：擴容限制、雙表面。

## Open Book Navigation

- [Architecture topic](../open-book/topics/01-architecture.md)  
  [架構主題](../open-book/topics/01-architecture.md)
- [ApplicationAppService source-map](../open-book/source-map/application/ApplicationAppService.md)  
  [ApplicationAppService 類別地圖](../open-book/source-map/application/ApplicationAppService.md)
- [Application source-map](../open-book/source-map/domain/Application.md)  
  [Application 類別地圖](../open-book/source-map/domain/Application.md)
