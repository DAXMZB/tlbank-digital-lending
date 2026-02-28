package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;
import com.example.demo.service.MessageService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dto.LoginRequest;
import com.example.demo.entity.Member;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid; // 必須引入，Spring 才會在接收到 JSON 時啟動檢查。

@RestController // RestFul API
@RequestMapping("/api/members")
public class MemberController {
	@Autowired
	private MemberService memberService; // 注入sevice

	@Autowired
    private MessageService messageService; // ✅ 注入 MessageService	
	@GetMapping("/check") // 檢查帳號是否存在
	public ResponseEntity<String> checkUsername(@RequestParam String username) {
		// 呼叫 Service，若重複會直接在 Service 拋出 MemberException
		memberService.isUsernameExists(username);
		// 如果執行到這
		return ResponseEntity.ok(messageService.getMessage("member-msg-check-ok"));
	}

	@PostMapping("/register")
	public ResponseEntity<String> register(@Valid @RequestBody Member m) {
		// 1. @Valid 幫你擋掉格式錯誤（如密碼太短）
		// 2. 呼叫 Service，如果帳號重複，Service 會直接拋出異常
		String result = memberService.rigister(m);
		// 3. 程式能執行到這行，代表一切順利，直接回傳 200 OK
		return ResponseEntity.ok(result);

//		400 Bad Request	格式錯誤：資料本身不符合規則。
//	    401 			未經授權
//		409 Conflict	資料衝突：格式對，但資料庫已存在。
//		200 OK 			請求成功

	}
	// 為了實現職責分離並優化校驗邏輯，引入 LoginRequest DTO 替代原始的 Member Entity 處理登入請求
	// 避免登入行為觸發註冊階段的非空約束（如地址、電話），確保 API 在不同業務場景下的校驗精確性。」
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginReq, HttpSession session) {// 加上 @Valid
		// 呼叫 Service 時輸入 DTO 的內容
		Optional<Member> memberOpt = memberService.login(loginReq.getUsername(), loginReq.getPassword());
		if (memberOpt.isPresent()) {
			Member member = memberOpt.get(); // 取得 member 物件
			// 將資訊存入伺服器端的 Session
			session.setAttribute("user", member);
			return ResponseEntity.ok(member);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(messageService.getMessage("member-error-login-fail"));

	}

	@PostMapping("/logout")
	public ResponseEntity<String> logout(HttpSession session) {
		// 1. 讓伺服器的 Session 失效
		session.invalidate();
		return ResponseEntity.ok(messageService.getMessage("member-msg-logout-ok"));
	}

	@GetMapping("/list")
//	@RequestParam：從 URL 網址抓取參數（例如 ?page=1&size=5）。
//	defaultValue = "0"：如果前端沒傳 page，預設顯示第 1 頁（索引從 0 開始）。
//	defaultValue = "10"：如果前端沒傳 size，預設每頁顯示 10 筆資料。
	public ResponseEntity<Page<Member>> getMemberList(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return ResponseEntity.ok(memberService.getAllMembers(page, size));
	}

	@GetMapping("/info")
	public ResponseEntity<?> getMemberInfo(HttpSession session) {
		// 檢查驗證伺服器 Session 裡沒有 user
		Object user = session.getAttribute("user");
		if (user == null) {
			// 如果伺服器重啟或 Session 過期，回傳401
			// ✅ 調整：Session 過期訊息動態化
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body(messageService.getMessage("member-msg-session-expired"));
		}
		return ResponseEntity.ok(user);
	}
	
	@PostMapping("/forgot-password")
	public ResponseEntity<String> forgotPassword(@RequestParam String username, @RequestParam String email){
		// 呼叫 Service 執行核對、重設與發信邏輯
		memberService.resetPassword(username, email);
		// ✅ 調整：重設密碼成功訊息動態化
        return ResponseEntity.ok(messageService.getMessage("member-msg-password-reset"));
	}
	
	@PostMapping("/send-code")
	public ResponseEntity<String> sendCode(@RequestParam String email){
		memberService.sendRegistrationCode(email);
		// ✅ 調整：發送驗證碼訊息動態化
        return ResponseEntity.ok(messageService.getMessage("member-msg-code-sent"));
	}

}
