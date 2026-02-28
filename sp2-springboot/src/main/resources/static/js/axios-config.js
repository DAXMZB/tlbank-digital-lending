// /js/axios-config.js
// 設定全域攔截器
axios.interceptors.response.use(
	response => response,
	error => {
		//「自動登出」的關鍵
		// 當收到 401 錯誤（代表伺服器認定你已登出），立刻執行以下動作
		if (error.response && error.response.status === 401) {
			alert("連線逾時或伺服器已重啟，請重新登入");
			sessionStorage.clear();
			window.location.href = "login.html";			
		}
		return Promise.reject(error);
	}
);

/* ### 🛠️ 2. 每個分頁的 `mounted` 都要「主動出擊」
攔截器（Interceptor）是「被動」的，它必須在有 API 請求發生且失敗時才會觸發。
* **為什麼要加 `/api/members/info`？** 因為如果使用者只是停留在頁面沒點擊任何按鈕，前端不會與後端溝通。
* **做法**：在如 `productList.html`、`orderList.html` 的 `mounted` 裡都加上這段 `try-catch`，確保使用者「一進入該頁面」就會立刻驗證 Session 是否過期。



### 🛠️ 3. 攔截器內的 `handleLogout` 邏輯
請確保你的 `axios-config.js` 內部確實執行了 `sessionStorage.clear()`，這樣才能把瀏覽器裡殘留的 `userName` 徹底清乾淨，防止 UI 顯示錯誤。

--- */
