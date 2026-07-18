# M24 — Dual Surface / 雙表面

## Why This Module Matters

### English

REST JSON API and Thymeleaf portal sharing application services.

### 中文

REST JSON API 與 Thymeleaf portal 共用 application service。

## Files to Open First

- `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/v1/ApplicationApiController.java` — REST surface.  
  `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/v1/ApplicationApiController.java` — REST 表面。
- `sp2-springboot/src/main/java/com/tlbank/lending/presentation/web/ApplicationWebController.java` — Thymeleaf applicant UI.  
  `sp2-springboot/src/main/java/com/tlbank/lending/presentation/web/ApplicationWebController.java` — Thymeleaf 申請人介面。
- `sp2-springboot/src/main/java/com/tlbank/lending/presentation/web/ReviewController.java` — Thymeleaf review UI.  
  `sp2-springboot/src/main/java/com/tlbank/lending/presentation/web/ReviewController.java` — Thymeleaf 審核介面。

## Primary Question

### English

What are the two presentation surfaces?

### 中文

兩種展示表面為何？

## Suggested Answer

### English

presentation.api.v1 hosts @RestController JSON APIs. presentation.web hosts @Controller Thymeleaf pages for apply, login, review, and admin. Both call the same application services.

### 中文

presentation.api.v1 承載 @RestController JSON API。presentation.web 承載申請、登入、審核、管理的 @Controller Thymeleaf 頁面。兩者呼叫相同 application service。

## Follow-up Question

### English

How does security treat the two surfaces?

### 中文

安全性如何對待兩種表面？

## Follow-up Answer

### English

One session-based SecurityFilterChain. CSRF is ignored for /api/**. Form login serves the portal UX described in ADR 0006.

### 中文

單一 session 基礎 SecurityFilterChain。CSRF 對 /api/** 忽略。表單登入服務 ADR 0006 所述 portal 體驗。

## Interview Tip

### English

Why asked: stop API-only overclaim.
Answer first: REST + Thymeleaf share services.
Keywords: dual surface, session, CSRF ignore scope.
Follow-ups: which features are API-only.

### 中文

提問原因：阻止僅 API 超宣稱。
先答：REST + Thymeleaf 共用服務。
關鍵詞：雙表面、session、CSRF 忽略範圍。
追問：哪些功能僅 API。

## Open Book Navigation

- [Request lifecycle](../open-book/topics/02-request-lifecycle.md)  
  [請求生命週期](../open-book/topics/02-request-lifecycle.md)
- [Security](../open-book/topics/03-security.md)  
  [安全性](../open-book/topics/03-security.md)
