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

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.demo.entity.Member;
import jakarta.validation.Valid; // 必須引入，Spring 才會在接收到 JSON 時啟動檢查。

@RestController // RestFul API
@RequestMapping("/api/members")
public class MemberController {
	@Autowired
	private MemberService memberService; // 注入sevice

	@GetMapping("/check") // 檢查帳號是否存在
	public ResponseEntity<String> checkUsername(@RequestParam String username) {
		// 呼叫 Service，若重複會直接在 Service 拋出 MemberException
		memberService.isUsernameExists(username);
		// 如果執行到這
		return ResponseEntity.ok("此帳號可以使用");
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

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody Member m) {// 加上 @Valid
		// 如果失敗，Service會拋出異常，之後的程式碼即不會處理
		Optional member = memberService.login(m.getUsername(), m.getPassword());
		return ResponseEntity.ok(member);

	}

	@GetMapping("/list")
//	@RequestParam：從 URL 網址抓取參數（例如 ?page=1&size=5）。
//	defaultValue = "0"：如果前端沒傳 page，預設顯示第 1 頁（索引從 0 開始）。
//	defaultValue = "10"：如果前端沒傳 size，預設每頁顯示 10 筆資料。
	public ResponseEntity<Page<Member>> getMemberList(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return ResponseEntity.ok(memberService.getAllMembers(page, size));
	}

}
