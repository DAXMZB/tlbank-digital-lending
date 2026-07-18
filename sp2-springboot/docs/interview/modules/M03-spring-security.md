# M03 — Spring Security / Spring Security

## Why This Module Matters

### English

Security questions separate session auth (current) from JWT (not implemented). ADR 0006 is the decision anchor.

### 中文

安全性問題用以區分 session 認證（現況）與 JWT（未實作）。決策錨點為 ADR 0006。

## Files to Open First

- `sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java` — SecurityFilterChain and session settings.  
  `sp2-springboot/src/main/java/com/tlbank/lending/security/config/SecurityConfig.java` — SecurityFilterChain 與 session 設定。
- `sp2-springboot/docs/decisions/0006-session-over-jwt.md` — Session-over-JWT ADR.  
  `sp2-springboot/docs/decisions/0006-session-over-jwt.md` — Session 優於 JWT 的 ADR。
- `sp2-springboot/src/main/java/com/tlbank/lending/security/filter/MdcLoggingFilter.java` — MDC correlation filter.  
  `sp2-springboot/src/main/java/com/tlbank/lending/security/filter/MdcLoggingFilter.java` — MDC 關聯過濾器。
- `sp2-springboot/src/test/java/com/tlbank/lending/security/SecurityIntegrationTest.java` — Security integration tests.  
  `sp2-springboot/src/test/java/com/tlbank/lending/security/SecurityIntegrationTest.java` — 安全性整合測試。

## Primary Question

### English

Is authentication JWT or session? Show the evidence.

### 中文

認證是 JWT 還是 session？請出示依據。

## Suggested Answer

### English

Session authentication. SecurityConfig uses SessionCreationPolicy.IF_REQUIRED, SessionRegistryImpl, and maximumSessions(1). Form login posts to /api/v1/auth/login. ADR 0006 documents the choice for the Thymeleaf portal. There is no JWT filter chain in the running configuration.

### 中文

為 session 認證。SecurityConfig 使用 SessionCreationPolicy.IF_REQUIRED、SessionRegistryImpl、maximumSessions(1)。表單登入處理 URL 為 /api/v1/auth/login。ADR 0006 記錄 Thymeleaf portal 的選擇。執行中設定無 JWT filter chain。

## Follow-up Question

### English

Why is CSRF ignored for /api/**?

### 中文

為何 CSRF 對 /api/** 忽略？

## Follow-up Answer

### English

API clients are not browser form posts with CSRF tokens. csrf.ignoringRequestMatchers("/api/**") is configured in SecurityConfig. That is a demo-oriented exemption and should be named as a risk for browser-based API calls.

### 中文

API 用戶端通常不是帶 CSRF token 的瀏覽器表單。SecurityConfig 設定 csrf.ignoringRequestMatchers("/api/**")。此為演示取向豁免，對瀏覽器呼叫 API 的風險應主動說明。

## Interview Tip

### English

Why asked: catch JWT overclaim.
Answer first: session + ADR 0006.
Keywords: IF_REQUIRED, maximumSessions(1), BCrypt(12), permitAll surfaces.
Follow-ups: multi-instance sessions, permitAll applicant APIs.

### 中文

提問原因：抓住 JWT 超宣稱。
先答：session + ADR 0006。
關鍵詞：IF_REQUIRED、maximumSessions(1)、BCrypt(12)、permitAll 範圍。
追問：多實例 session、申請人 API permitAll。

## Open Book Navigation

- [Security topic](../open-book/topics/03-security.md)  
  [安全性主題](../open-book/topics/03-security.md)
- [SecurityConfig source-map](../open-book/source-map/security/SecurityConfig.md)  
  [SecurityConfig 類別地圖](../open-book/source-map/security/SecurityConfig.md)
