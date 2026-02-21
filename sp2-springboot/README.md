📣 心情社群平台 (Social Interaction Platform)
這是一個基於 Spring Boot 3 與 MySQL 8.0 構建的互動式社群系統，專注於處理 RDBMS 多表關聯設計、RESTful API 安全性控管及容器化部署實踐。本專案已全面導入 Docker 容器化技術，並落實 Secret Management 資安規範，確保開發環境與部署環境的高度一致性與安全性。

🌟 核心技術亮點
1. 容器化運維與資安管理 (Docker & Security)
環境變數隔離：落實 12-Factor App 規範，透過 .env 檔案與 Docker Compose 注入環境變數，確保資料庫憑證與 SMTP 密鑰不進入版本控制系統。

服務編排自動化：實作多容器協作架構，利用 depends_on 管理 Spring Boot 與 MySQL 的啟動依賴，並配置持久化數據卷 (Volumes) 確保資料安全性。

2. 全自動化身分服務 (Identity & Mail Service)
密碼重設流：整合 Spring Mail (SMTP) 與 Google App Password 機制，實作具備身分校驗的非對稱密碼重置邏輯。

Session 同步機制：結合後端 HttpSession 與前端 AJAX 主動驗證，實作伺服器重啟即自動登出的安全機制，確保客戶端與伺服器狀態高度一致。

3. 多表關聯與 Nesting 回應系統
JPA 深度實作：利用 @OneToMany 與 @ManyToOne 實作 Member、Post 與 Comment 的三層巢狀關聯，並透過 @OrderBy 優化留言的時間排序。

循環引用防護：精確配置 Jackson 序列化策略，解決雙向關聯遞迴問題，提供穩定的 JSON 數據接口。

4. 前端交互與分頁優化
非同步數據加載：利用 jQuery AJAX 與 ES6 Template Literals 實作 SPA (Single Page Application) 般的流暢體驗，包含無刷新發文、即時留言與分頁切換。

數據傳輸優化：整合 Spring Data JPA 的分頁機制，顯著降低大數據量下的伺服器負載與網絡延遲。

🛠️ 技術棧 (Tech Stack)
Backend: Java 17, Spring Boot 3, Spring Data JPA, Spring Security (BCrypt)

Database: MySQL 8.0

DevOps: Docker, Docker Compose, Secret Management (.env)

Mail Service: JavaMailSender (SMTP over TLS)

Frontend: jQuery, AJAX, Bootstrap (UI), HTML5/CSS3

🚀 快速啟動 (Quick Start)
1. 準備環境變數
為了系統安全，請在專案根目錄建立 .env 檔案，並參考以下內容進行配置：

Plaintext
# 資料庫設定
DB_NAME=Company
DB_ROOT_PASSWORD=您的密碼

# Gmail SMTP 發信設定
GMAIL_USER=您的Gmail@gmail.com
GMAIL_PWD=您的16位元應用程式密碼
2. 編譯與運行
Bash
# 編譯產出 jar 檔
mvn clean package -DskipTests

# 一鍵啟動容器化環境
docker compose up --build -d
3. 訪問系統
前端頁面: http://localhost:8080/index.html

API 文檔: http://localhost:8080/swagger-ui/index.html (若有導入)

日誌監控: docker logs -f spring-app