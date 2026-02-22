package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // 讓Spring 自動掃描並處理Controller異常 <-- 這是「統一處理」的靈魂
public class GlobalExceptionHandler {
	@ExceptionHandler(MemberException.class) // <-- 捕捉所有 Service 拋出的 MemberException
	public ResponseEntity<String> handleMemberException(MemberException ex) {
		// 這裡保留原來的邏輯，根據不同業務錯誤回報不同訊息
		return ResponseEntity.badRequest().body(ex.getMessage());
	}

	// 400 Bad Request 格式錯誤：資料本身不符合規則。
	// 401 UNAUTHORIZED 未經授權
	// 409 Conflict 資料衝突：格式對，但資料庫已存在。
	// 200 OK 請求成功
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		// 取得第一個錯誤訊息內容
		String firstError = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();

		// 檢查 errorMsg 是否精確等於 DTO 裡面的那兩句話
		if (firstError.equals("帳號不可為空") || firstError.equals("密碼不可為空")) {
			return ResponseEntity.badRequest().body(firstError);
		}
		// 邏輯判斷：如果是「空」相關的，就統一名稱；否則（如長度不足）就顯示具體內容
		if (firstError.contains("空")) {
			return ResponseEntity.badRequest().body("所有欄位需填寫完畢不可為空");
		}

		// 如果是密碼長度不足等等，就顯示該爛為具體 message
		return ResponseEntity.badRequest().body(firstError);
	}
	// Spring Boot 錯誤攔截順序
	// 1. 第一關：Member 實體校驗 (Entity/Format Level)當前端按下
	// ok 發送 JSON 時，Spring 的 DispatcherServlet 會先嘗試將 JSON 轉換為 Member 物件。
	// 觸發點：Controller 方法參數上的 @Valid 註解。
	// 行為：Spring 會檢查 Member.java 裡的註解（如 @NotBlank, @Size）。
	// 結果：如果這裡失敗（例如地址沒填），會直接拋出 MethodArgumentNotValidException，程式根本不會進入 Service 層。

	// 2. 第二關：Controller 邏輯接收如果第一關通過了，Controller 才會正式接到這個 m 物件，並呼叫
	// memberService.rigister(m)。

	// 3. 第三關：Service 業務校驗 (Service/Logic Level)進入 MemberServiceImpl.java
	// 後，才會執行你寫的邏輯檢查。
	// 觸發點：你手動寫的 throw new MemberException(...)。行為：檢查驗證碼是否過期、帳號是否重複。結果：如果這裡失敗，會拋出
	// MemberException，
	// 並由 GlobalExceptionHandler 的另一個方法捕捉。

	// 📊 總結順序表順序檢查層級負責檢查的程式碼產生的 Exception你的 Handler 處理方式
	// 1格式/欄位Member.java 裡的 @NotBlankMethodArgumentNotValidException檢查是否包含「空」
	// ，回傳統一訊息或長度提示
	// 2業務/邏輯ServiceImpl 裡的 if...throwMemberException直接回傳具體錯誤（如：驗證碼過期）
}
