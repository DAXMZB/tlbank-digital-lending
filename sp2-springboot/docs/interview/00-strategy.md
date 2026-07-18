# Interview Strategy

## How Trend Micro Interviews Work

A typical backend interview for a Java engineer runs approximately 60 minutes and follows this arc:

趨勢科技 Java 後端面試約 60 分鐘，常見流程如下：

1. Project walkthrough (10 min) — interviewer opens code and requests an explanation  
   專案走讀（約 10 分鐘）— 面試官開啟程式碼並要求說明

2. Deep dive on one topic (20 min) — state machine, security, or transaction  
   單一主題深挖（約 20 分鐘）— 狀態機、安全性或交易

3. Follow-up chain (15 min) — each answer surfaces the next question  
   追問鏈（約 15 分鐘）— 每個回答引出下一題

4. Design question (10 min) — scale this system or change a requirement  
   設計題（約 10 分鐘）— 擴容或變更需求

5. Behavioral close (5 min) — one or two situational questions  
   行為題收尾（約 5 分鐘）— 一至兩題情境題

Each design decision in the repository must be explainable as an intentional trade-off. Listing features is insufficient. Connecting implementation to rationale is required.

倉庫中的設計決策須能說明為有意的取捨。僅列功能不足。實作必須能連回理由。

## What Interviewers Actually Test

面試官實際觀察的訊號如下。

| Skill | Primary Signal | Secondary Signal |
| --- | --- | --- |
| Java | Record usage, switch expression, EnumSet | Collection choice, null handling |
| Spring | `@Transactional` placement, `@Around` AOP | `@ConditionalOnProperty`, profiles |
| Architecture | Dependency direction, port/adapter | ArchUnit awareness |
| Concurrency | ThreadLocal lifecycle, ConcurrentHashMap | `@Async` propagation |
| Testing | Test pyramid shape, `@Async` in tests | Testcontainers awareness |
| Security | Session vs JWT trade-off | BCrypt cost, CSRF exemption |
| Databases | JPA FetchType, N+1, Flyway checksum | SQL Server vs H2 differences |

| 技能 | 主要訊號 | 次要訊號 |
| --- | --- | --- |
| Java | record、switch expression、EnumSet | 集合選擇、null 處理 |
| Spring | `@Transactional` 放置、`@Around` AOP | `@ConditionalOnProperty`、profile |
| 架構 | 依賴方向、port／adapter | ArchUnit 意識 |
| 並發 | ThreadLocal 生命週期、ConcurrentHashMap | `@Async` 傳遞 |
| 測試 | 測試金字塔、`@Async` 測試 | Testcontainers 意識 |
| 安全性 | Session 與 JWT 取捨 | BCrypt cost、CSRF 豁免 |
| 資料庫 | JPA FetchType、N+1、Flyway checksum | SQL Server 與 H2 差異 |

## Answering Under Pressure

When a class is difficult to recall:

當某個類別一時無法即時回憶：

- State the design intent first ("this class is responsible for...")  
  先陳述設計意圖（「此類別負責…」）

- Open the file and narrate what the code does  
  開啟原始檔並逐段說明行為

- Connect implementation to the ADR that explains why  
  將實作連回說明決策的 ADR

When the answer is unknown:

當答案未知：

- State what is known  
  陳述已知部分

- State what is not known  
  陳述未知邊界

- State how the gap would be investigated  
  陳述調查缺口的方法（開啟檔案、測試、設定）

Pretending to know is detectable and costly. Stating the boundary of knowledge is acceptable.

假裝已知會被識破且代價高。清楚標示知識邊界可接受。

## Open Book Navigation

- [topics/01-architecture.md](../open-book/topics/01-architecture.md)  
  [架構主題](../open-book/topics/01-architecture.md)

- [source-map/README.md](../open-book/source-map/README.md)  
  [類別地圖](../open-book/source-map/README.md)
