# M30 — Self Assessment / 自我評估

## Why This Module Matters

### English

Readiness gate tying Tier 1 checklist items to modules and Open Book.

### 中文

就緒閘道：將 Tier 1 檢核項連回模組與 Open Book。

## Files to Open First

- `sp2-springboot/docs/interview/00-strategy.md` — Interview arc.  
  `sp2-springboot/docs/interview/00-strategy.md` — 面試流程。
- `sp2-springboot/docs/interview/progress-checklist.md` — 48-hour checklist.  
  `sp2-springboot/docs/interview/progress-checklist.md` — 48 小時檢核。
- `sp2-springboot/docs/decisions/0001-use-clean-architecture.md` — Architecture ADR.  
  `sp2-springboot/docs/decisions/0001-use-clean-architecture.md` — 架構 ADR。
- `sp2-springboot/docs/decisions/0003-use-redis-idempotency.md` — Redis scope ADR.  
  `sp2-springboot/docs/decisions/0003-use-redis-idempotency.md` — Redis 範圍 ADR。
- `sp2-springboot/docs/decisions/0006-session-over-jwt.md` — Session ADR.  
  `sp2-springboot/docs/decisions/0006-session-over-jwt.md` — Session ADR。

## Primary Question

### English

Which topics must be explainable in 90 seconds without notes?

### 中文

哪些主題須在不看筆記下以 90 秒說明？

## Suggested Answer

### English

Architecture dependency direction (M01), ApplicationStatus transitions (M02), session over JWT (M19), Redis idempotency-only scope (M06), OTP with mocks (M20), and production gaps (M25). Anchor with ADRs 0001, 0003, and 0006.

### 中文

架構依賴方向（M01）、ApplicationStatus 轉移（M02）、session 優於 JWT（M19）、Redis 僅冪等（M06）、OTP 與 mock（M20）、生產缺口（M25）。以 ADR 0001、0003、0006 為錨點。

## Follow-up Question

### English

List claims that must never appear.

### 中文

列出絕不可出現的宣稱。

## Follow-up Answer

### English

JWT implemented; Redis as product cache or session store; cloud Terraform from infra/local; real SMS vendor integration; automatic cloud production CD on every push.

### 中文

已實作 JWT；Redis 作為產品快取或 session 儲存；infra/local 雲端 Terraform；真實簡訊商整合；每次 push 自動雲端正式環境 CD。

## Interview Tip

### English

Why asked: final readiness.
Answer first: timed Tier 1 list.
Keywords: progress-checklist, forbidden claims.
Follow-ups: which module to re-read tonight.

### 中文

提問原因：最終就緒。
先答：計時 Tier 1 清單。
關鍵詞：progress-checklist、禁止宣稱。
追問：今晚重讀哪個模組。

## Open Book Navigation

- [Open Book home](../open-book/README.md)  
  [Open Book 首頁](../open-book/README.md)
- [Progress checklist](progress-checklist.md)  
  [進度檢核](progress-checklist.md)
- [Follow-up map](../follow-up-map.md)  
  [追問地圖](../follow-up-map.md)
