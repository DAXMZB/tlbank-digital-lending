# M27 — Behavioral / 行為面試

## Why This Module Matters

### English

Behavioral answers anchored on ADRs and documented debts.

### 中文

行為題回答錨定 ADR 與已記錄債務。

## Files to Open First

- `sp2-springboot/docs/decisions/0006-session-over-jwt.md` — Contested trade-off example.  
  `sp2-springboot/docs/decisions/0006-session-over-jwt.md` — 爭議取捨範例。
- `sp2-springboot/docs/decisions/0003-use-redis-idempotency.md` — Narrow Redis scope.  
  `sp2-springboot/docs/decisions/0003-use-redis-idempotency.md` — 收窄 Redis 範圍。
- `sp2-springboot/docs/decisions/0005-use-terraform-local.md` — Learning-scoped infra.  
  `sp2-springboot/docs/decisions/0005-use-terraform-local.md` — 學習範圍基礎建設。
- `sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md` — Public debt list.  
  `sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md` — 公開債務清單。

## Primary Question

### English

Describe a technical trade-off recorded in an ADR.

### 中文

說明一項寫入 ADR 的技術取捨。

## Suggested Answer

### English

ADR 0006 chooses session over JWT for the Thymeleaf portal, accepting weaker multi-instance session sharing. ADR 0003 keeps Redis on create-idempotency only. ADR 0005 keeps Terraform on hashicorp/local without cloud provision.

### 中文

ADR 0006 為 Thymeleaf portal 選擇 session 而非 JWT，接受多實例 session 共享較弱。ADR 0003 將 Redis 限於建立申請冪等。ADR 0005 將 Terraform 留在 hashicorp/local，不進行雲端佈建。

## Follow-up Question

### English

How should an unknown area be handled in interview?

### 中文

面試中對未知區域應如何處理？

## Follow-up Answer

### English

State what is known, what is not known, and how to investigate by opening the class, test, or ADR. Do not invent cloud features.

### 中文

陳述已知、未知，以及如何以開啟類別、測試或 ADR 調查。不虛構雲端功能。

## Interview Tip

### English

Why asked: ownership and honesty.
Answer first: name ADR number + trade-off.
Keywords: mock notify, dual scheduling debt.
Follow-ups: disagreement handling, scope control.

### 中文

提問原因：擁有感與誠實度。
先答：點名 ADR 編號 + 取捨。
關鍵詞：mock 通知、雙排程債務。
追問：意見不合處理、範圍控制。

## Open Book Navigation

- [Limitations](../open-book/topics/12-delivery-and-limitations.md)  
  [限制](../open-book/topics/12-delivery-and-limitations.md)
- [Open Book home](../open-book/README.md)  
  [Open Book 首頁](../open-book/README.md)
