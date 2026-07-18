# M13 — Docker and CI/CD / Docker 與 CI／CD

## Why This Module Matters

### English

Delivery honesty: manual staging deploy and local-only Terraform.

### 中文

交付誠實度：手動 staging 部署與僅本機 Terraform。

## Files to Open First

- `sp2-springboot/docker/app/Dockerfile` — Multi-stage Temurin 17 image.  
  `sp2-springboot/docker/app/Dockerfile` — 多階段 Temurin 17 映像。
- `sp2-springboot/docker-compose.yml` — SQL Server + app; no Redis service.  
  `sp2-springboot/docker-compose.yml` — SQL Server 與 app；無 Redis 服務。
- `.github/workflows/ci.yml` — CI jobs; deploy-staging on workflow_dispatch.  
  `.github/workflows/ci.yml` — CI 工作；deploy-staging 僅 workflow_dispatch。
- `sp2-springboot/docs/decisions/0004-use-github-actions.md` — CI/CD ADR.  
  `sp2-springboot/docs/decisions/0004-use-github-actions.md` — CI／CD ADR。
- `sp2-springboot/docs/decisions/0005-use-terraform-local.md` — Local Terraform ADR.  
  `sp2-springboot/docs/decisions/0005-use-terraform-local.md` — 本機 Terraform ADR。

## Primary Question

### English

How does code reach staging?

### 中文

程式如何到達 staging？

## Suggested Answer

### English

GitHub Actions runs build-test, code-quality, dependency-scan, and build-and-push-image. deploy-staging runs only when the workflow is started with workflow_dispatch on the configured self-hosted runner. Push alone does not auto-deploy staging.

### 中文

GitHub Actions 執行 build-test、code-quality、dependency-scan、build-and-push-image。deploy-staging 僅在 workflow_dispatch 且於設定的 self-hosted runner 上手動觸發。僅 push 不會自動部署 staging。

## Follow-up Question

### English

What does infra/local Terraform create?

### 中文

infra/local 的 Terraform 建立什麼？

## Follow-up Answer

### English

hashicorp/local resources such as generated local documentation files. It does not provision AWS/GCP/Azure resources.

### 中文

hashicorp/local 資源，例如產生本機說明檔。不建立 AWS／GCP／Azure 資源。

## Interview Tip

### English

Why asked: stop cloud CD overclaim.
Answer first: manual deploy-staging.
Keywords: workflow_dispatch, compose without Redis, ADR 0004/0005.
Follow-ups: Trivy exit code, Dockerfile stages.

### 中文

提問原因：阻止雲端 CD 超宣稱。
先答：手動 deploy-staging。
關鍵詞：workflow_dispatch、compose 無 Redis、ADR 0004／0005。
追問：Trivy exit code、Dockerfile 階段。

## Open Book Navigation

- [Delivery limitations](../open-book/topics/12-delivery-and-limitations.md)  
  [交付與限制](../open-book/topics/12-delivery-and-limitations.md)
