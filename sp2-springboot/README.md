# 心情社群平台 (Social Interaction Platform)

這是一個基於 **Spring Boot 3** 與 **MySQL** 構建的微型社群系統，具備完整的心情發佈、分頁載入、巢狀回應與安全權限控管功能。

## 🌟 核心功能亮點
* **互動式心情系統**: 實作 RESTful API 進行心情發佈，並透過 AJAX 達成局部 UI 更新。
* **多表關聯回應功能**: 利用 JPA 的 `@OneToMany` 與 `@ManyToOne` 實作貼文與回應的雙向關聯，並解決 JSON 循環引用問題。
* **安全性身分校驗**: 後端實作嚴格的 ID 權限檢查，確保使用者只能刪除屬於自己的貼文或回應。
* **效能優化與分頁**: 整合 Spring Data JPA 的 `Pageable` 並開啟 `VIA_DTO` 序列化模式，顯著提升大數據量下的讀取效能。
* **全域異常處理**: 透過 `@RestControllerAdvice` 統一管理 HTTP 狀態碼與業務邏輯異常。

## 🏗️ 系統架構
專案採用典型的 **MVC (Model-View-Controller)** 三層式架構設計：
1. **Controller**: 處理 REST 請求，遵循 RESTful API 規範。
2. **Service**: 封裝核心業務邏輯，確保資料一致性 (`@Transactional`)。
3. **Repository**: 利用 Spring Data JPA 進行高效的資料庫持久化操作。

## 🛠️ 技術棧
* **Language**: Java 17
* **Framework**: Spring Boot 3, Spring Data JPA, Spring Security
* **Database**: MySQL
* **Frontend**: jQuery (AJAX), HTML5, CSS3
* **Tools**: Maven, Git, Eclipse IDE