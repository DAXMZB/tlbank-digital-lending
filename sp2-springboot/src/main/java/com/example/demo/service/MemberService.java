package com.example.demo.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.example.demo.entity.Member;

public interface MemberService {
	// 定義註冊
	String rigister(Member member);

	// 定義登入
	Optional<Member> login(String username, String password);

	// 定義檢查帳號
	boolean isUsernameExists(String username);

	// 介面新增
	Page<Member> getAllMembers(int page, int size);
	
	// 重設密碼
	void resetPassword(String username, String email);
	
	// 發送註冊驗證碼
	void sendRegistrationCode(String email);
	
	// 驗證驗證碼是否正確
	boolean verifyRegistrationCode(String email, String code);
}
