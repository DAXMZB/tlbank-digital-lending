# M25 — Production Gaps / 生產缺口

## Why This Module Matters

### English

Explicit non-production boundaries that must not be overclaimed in interview.

### 中文

面試中不可超宣稱的明確非生產邊界。

## Files to Open First

- `sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md` — Debt and enhancement list.  
  `sp2-springboot/docs/design/20-maintenance-and-future-enhancement.md` — 債務與強化清單。
- `sp2-springboot/docs/decisions/0005-use-terraform-local.md` — Local Terraform only.  
  `sp2-springboot/docs/decisions/0005-use-terraform-local.md` — 僅本機 Terraform。
- `sp2-springboot/docs/open-book/topics/12-delivery-and-limitations.md` — Limitations topic.  
  `sp2-springboot/docs/open-book/topics/12-delivery-and-limitations.md` — 限制主題。

## Primary Question

### English

Name production gaps that must not be overclaimed.

### 中文

列出不可超宣稱的生產缺口。

## Suggested Answer

### English

Mock notifications; in-memory product cache; JVM SessionRegistry; local FS uploads; Terraform hashicorp/local only; staging deploy via workflow_dispatch; dual @EnableScheduling. Redis is idempotency when configured, not product cache or session store.

### 中文

Mock 通知；記憶體產品快取；JVM SessionRegistry；本機檔案上傳；Terraform 僅 hashicorp/local；staging 以 workflow_dispatch 部署；雙重 @EnableScheduling。Redis 在設定啟用時僅冪等，非產品快取或 session 儲存。

## Follow-up Question

### English

Is cloud CI/CD automatic on every push?

### 中文

是否每次 push 都自動雲端 CI／CD？

## Follow-up Answer

### English

CI runs on configured triggers. deploy-staging is manual workflow_dispatch. There is no automatic cloud production promotion in the documented current flow.

### 中文

CI 依設定觸發。deploy-staging 為手動 workflow_dispatch。文件所述現況流程無自動雲端正式環境晉升。

## Interview Tip

### English

Why asked: credibility gate.
Answer first: five concrete gaps.
Keywords: mock, in-memory, local TF, manual deploy.
Follow-ups: which gap to harden first.

### 中文

提問原因：可信度閘道。
先答：五項具體缺口。
關鍵詞：mock、記憶體、本機 TF、手動部署。
追問：優先硬化哪一項。

## Open Book Navigation

- [Delivery limitations](../open-book/topics/12-delivery-and-limitations.md)  
  [交付與限制](../open-book/topics/12-delivery-and-limitations.md)
- [Notifications](../open-book/topics/09-events-and-notifications.md)  
  [通知](../open-book/topics/09-events-and-notifications.md)
