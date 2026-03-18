package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.example.demo.dto.member.MemberRegisterRequest;
import com.example.demo.dto.member.MemberResponse;
import com.example.demo.entity.Member;

public interface MemberService {
	String rigister(MemberRegisterRequest m); // 註冊

    Optional<Member> login(String username, String password); // 登入

    boolean isUsernameExists(String username); // 檢查帳號

    List<MemberResponse> getAllMembers(); // 查全部會員

    MemberResponse getMemberById(Integer id); // 查單一會員

    void resetPassword(String username, String email); // 重設密碼

    void sendRegistrationCode(String email); // 發送驗證碼

    boolean verifyRegistrationCode(String email, String code); // 驗證代碼

}
