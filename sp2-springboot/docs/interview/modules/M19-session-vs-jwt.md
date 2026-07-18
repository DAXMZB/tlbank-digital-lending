# M19 — Session vs JWT / Session 與 JWT

## Why This Module Matters

### English

ADR 0006 decision: session for Thymeleaf portal; JWT not implemented.

### 中文

ADR 0006 決策：Thymeleaf portal 使用 session；JWT 未實作。

## Files to Open First

- `sp2-springboot/docs/decisions/0006-session-over-jwt.md` — Decision record.  
  `sp2-springboot/docs/decisions/0006-session-over-jwt.md` — 決策紀錄。
- `sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java` — Session filter chain.  
  `sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java` — Session 過濾鏈。
- `sp2-springboot/src/main/java/com/tlbank/lending/presentation/web/AuthController.java` — Login page controller.  
  `sp2-springboot/src/main/java/com/tlbank/lending/presentation/web/AuthController.java` — 登入頁 controller。

## Primary Question

### English

Why session instead of JWT in this project?

### 中文

本專案為何使用 session 而非 JWT？

## Suggested Answer

### English

The staff portal is Thymeleaf with form login. Server-side sessions support logout and maximumSessions(1) without a token denylist. ADR 0006 records the trade-off: simpler browser UX versus no shared session store across instances. JWT is not present in the security filter chain.

### 中文

員工 portal 為 Thymeleaf 表單登入。伺服器端 session 支援登出與 maximumSessions(1)，無需 token 黑名單。ADR 0006 記錄取捨：瀏覽器體驗較直接，但實例間無共享 session 儲存。安全性過濾鏈中不存在 JWT。

## Follow-up Question

### English

What breaks with multiple app instances?

### 中文

多應用實例會出什麼問題？

## Follow-up Answer

### English

SessionRegistryImpl is per JVM. Without sticky sessions or Spring Session, login state does not cohere across nodes. Spring Session Redis is not implemented.

### 中文

SessionRegistryImpl 為每 JVM。無黏性 session 或 Spring Session 時，登入狀態無法跨節點一致。Spring Session Redis 未實作。

## Interview Tip

### English

Why asked: security trade-off literacy.
Answer first: ADR 0006 + Thymeleaf.
Keywords: IF_REQUIRED, maximumSessions(1), no JWT.
Follow-ups: CSRF on /api/**, future token API as design talk only.

### 中文

提問原因：安全性取捨素養。
先答：ADR 0006 + Thymeleaf。
關鍵詞：IF_REQUIRED、maximumSessions(1)、無 JWT。
追問：/api/** 的 CSRF、未來 token API 僅屬設計討論。

## Open Book Navigation

- [Security topic](../open-book/topics/03-security.md)  
  [安全性主題](../open-book/topics/03-security.md)
- [SecurityConfig source-map](../open-book/source-map/security/SecurityConfig.md)  
  [SecurityConfig 類別地圖](../open-book/source-map/security/SecurityConfig.md)
