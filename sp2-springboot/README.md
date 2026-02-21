📣 心情社群平台 (Social Interaction Platform)
這是一個基於 Spring Boot 3 與 MySQL 構建的互動式社群系統，專注於處理 RDBMS 多表關聯設計、RESTful API 安全性控管及分頁數據傳輸優化。
本專案已全面導入 Docker 容器化技術，確保開發與部署環境的高度一致性。

🌟 核心技術亮點
1. 多表關聯與巢狀回應系統
。實體關聯設計：利用 JPA 的 @OneToMany 與 @ManyToOne 實作 Member、Post 與 Comment 的雙向關聯。
。效能優化抓取：配置 FetchType.EAGER 與 @OrderBy 確保心情與回應資料在單次請求中高效加載且順序正確。
。循環引用防護：透過 @JsonIgnore 解決 Jackson 序列化時的雙向關聯遞迴問題，確保 JSON 結構穩定。

2. 安全性權限與異常控管
。精確身分校驗：在 Service 層實作嚴格的權限檢查，比對 memberNo 確保使用者僅具備刪除本人內容之權限。
。統一異常處理：透過 @RestControllerAdvice 建立 GlobalExceptionHandler，將業務邏輯異常轉化為語意明確的 HTTP 狀態碼 (如 403 Forbidden)。

3. 分頁查詢與 API 數據優化
。高效分頁機制：整合 Spring Data JPA 的 Pageable 與 VIA_DTO 序列化模式，顯著優化大量數據下的加載效率。
。API 調試實踐：熟練運用 Postman 進行介面測試，精確排查 400 Bad Request 與 405 Method Not Allowed 等常見通訊錯誤。

4. 非同步前端交互
。SPA 互動體驗：利用 jQuery AJAX 實作無刷新心情發佈與留言回應，提升使用者操作流暢度。
。動態 UI 渲染：透過 ES6 Template Literals 動態生成具備唯一識別 ID 的 DOM 元素，精確控制特定貼文的回應區域。

🏗️ 系統架構
。Controller: 遵循 RESTful 規範設計路由，處理參數校驗與數據分發。
。Service: 封裝業務核心邏輯，並利用 @Transactional 確保資料操作的原子性。
。Repository: 透過 Spring Data JPA 介面進行資料庫持久化，支援動態條件查詢。

🛠️ 技術棧 (Tech Stack)
。後端: Java 17, Spring Boot 3, Spring Data JPA, Spring Security
。資料庫: MySQL
。前端: HTML5, CSS3, jQuery (AJAX)
。開發工具: Maven, Git/GitHub, Postman, Eclipse IDE

## 🚀 快速啟動 (Quick Start)

## 🐳 如何透過 Docker 快速運行 (Quick Start)

本專案已完全容器化，您可以跳過繁瑣的資料庫安裝與環境配置。

### 1. 編譯專案
在專案根目錄下，使用 Maven 產出最新的 `.jar` 執行檔：
`mvn clean package -DskipTests`

### 2. 啟動服務
執行以下指令，Docker 將自動建立 MySQL 資料庫與 Java 執行環境：
`docker compose up --build -d`

### 3. 訪問作品
* 網頁連結: `http://localhost:8080/index.html`
* 資料庫管理: 您可使用 DBeaver 連線 `localhost:3306` (密碼設定見 docker-compose.yml)。

### 4. 常見問題排查
若連結無法開啟，請使用以下指令檢查日誌：
`docker logs -f spring-app`

