package com.example.demo.service.impl;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Member;
import com.example.demo.exception.MemberException;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;
import com.example.demo.service.MessageService;

import jakarta.servlet.http.HttpSession;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberRepository repo;
	// 建立加密實例
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	private HttpSession session; // 注入 Session
	@Autowired
	private MessageService messageService; // ✅ 注入訊息服務

	@Override
	public String rigister(Member m) {
		// TODO Auto-generated method stub

		// 取得 Session 中的驗證碼與過期時間
		String sessionCode = (String) session.getAttribute("reg_code_" + m.getEmail());
		Long expiryTime = (Long) session.getAttribute("reg_code_expiry_" + m.getEmail());
		long currentTime = System.currentTimeMillis();

		// 檢查驗證碼是否過期
		if (expiryTime == null || currentTime > expiryTime) {
			// 過期的話清除掉 Sesssion
			session.removeAttribute("reg_code_" + m.getEmail());
			session.removeAttribute("reg_code_expiry_" + m.getEmail());
			// ✅ 調整：驗證碼過期訊息
			throw new MemberException(messageService.getMessage("member-error-code-expired"));
		}

		// 1. 驗證碼比對
		if (sessionCode == null || !sessionCode.equals(m.getVertifyCode())) {
			// ✅ 調整：驗證碼錯誤訊息
			throw new MemberException(messageService.getMessage("member-error-code-invalid"));
		}

		// 2. 帳號重複驗證
		// 這裡就是「HTTP 狀態碼轉換」：根據訊息字串決定要給 409 還是 400
		if (repo.existsByUsername(m.getUsername())) {
			// ✅ 調整：帳號重複訊息
			throw new MemberException(messageService.getMessage("member-error-username-duplicate"));// 409
		}

		// 3. 密碼加密
		String encodedPassword = passwordEncoder.encode(m.getPassword());
		m.setPassword(encodedPassword);// 將加密後的字串塞回物件
		repo.save(m);

		// 註冊成功後移除 Session 中的驗證碼，防止重複使用
		session.removeAttribute("reg_code_" + m.getEmail());
		session.removeAttribute("reg_code_expiry_" + m.getEmail());

		// ✅ 調整：註冊成功訊息
		return messageService.getMessage("member-msg-register-ok");
	}

	@Override
	public Optional<Member> login(String username, String password) {
		// TODO Auto-generated method stub
		// 因為資料庫現在存的是「亂碼」，不能直接用 findByUsernameAndPassword。必須先根據帳號取出資料，再比對密碼。
		// 1.先用帳號找人
		// ✅ 調整：帳號不存在訊息
		Member member = repo.findByUsername(username)
				.orElseThrow(() -> new MemberException(messageService.getMessage("member-error-username-notfound")));

		// 2.使用 matches 比對 (原始密碼，資料庫加密密碼)
		if (passwordEncoder.matches(password, member.getPassword())) {
			return Optional.of(member);
		} else {
			// ✅ 調整：密碼錯誤訊息
			throw new MemberException(messageService.getMessage("member-error-password-invalid"));
		}

	}

	@Override
	public boolean isUsernameExists(String username) {
		// TODO Auto-generated method stub
		if (repo.existsByUsername(username)) {
			// 如果存在，直接拋出異常，訊息包含重複或存在
			// 這裡就是「HTTP 狀態碼轉換」：根據訊息字串決定要給 409 還是 400
			// ✅ 調整：帳號已存在訊息
			throw new MemberException(messageService.getMessage("member-error-username-duplicate"));// 400
		}
		return false;
	}

	@Override
	public Page<Member> getAllMembers(int page, int size) {
		// TODO Auto-generated method stub
		// page 為頁碼 (從 0 開始)，size 為每頁幾筆資料
		Pageable pageable = PageRequest.of(page, size);
//		呼叫 repo.findAll(pageable) 時：
//		自動分頁：根據傳入的 page 和 size，自動在 SQL 後面加上 LIMIT 與 OFFSET (以 MySQL 為例)。
//		自動計算總數：它會同步執行一條 SELECT COUNT(*) 的指令。
//		封裝結果：最後將資料清單與總筆數資訊包裝成一個 Page<T> 物件回傳。
		return repo.findAll(pageable);// 呼叫 findAll 並傳入分頁參數

	}

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void resetPassword(String username, String email) {
		// 1. 核對資料
		// ✅ 調整：核對失敗訊息
		Member member = repo.findByUsernameAndEmail(username, email)
				.orElseThrow(() -> new MemberException(messageService.getMessage("member-error-reset-fail")));

		// 2. 產生隨機 8 位 密碼
		String newPwd = UUID.randomUUID().toString().substring(0, 8);

		// 3. 更新資料庫
		member.setPassword(passwordEncoder.encode(newPwd));
		repo.save(member);

		// 4. 發送郵件
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		// ✅ 調整：郵件主旨動態化
		message.setSubject(messageService.getMessage("member-mail-reset-subject"));
		// ✅ 調整：郵件內文使用 String.format 填充姓名與新密碼
		message.setText(String.format(messageService.getMessage("member-mail-reset-body"), member.getName(), newPwd));
		mailSender.send(message);
	}

	@Override
	public void sendRegistrationCode(String email) {
		// 基本格式校驗 (防範 Log 中的 553 5.1.3 錯誤)
		if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
			throw new MemberException(messageService.getMessage("member-error-email-format"));
		}
		// 1. 檢查 Email 是否已被註冊
		if (repo.existsByEmail(email)) {
			// ✅ 調整：Email 已註冊訊息
			throw new MemberException(messageService.getMessage("member-error-email-registered"));
		}

		// 2. 產生 6 位隨機數字驗證碼
		String code = String.format("%06d", new Random().nextInt(1000000));

		// 取得目前時間並加上 5 分鐘 (30,000 毫秒)
		long expiryTime = System.currentTimeMillis() + (300000); // 5 * 60 * 1000

		// 3. 存入 Session (Key 使用 email 確保唯一)
		// session.setAttribute("標籤名", 內容)：這是在櫃子裡放進一個盒子，並在盒子上貼一個特定的標籤。
		session.setAttribute("reg_code_" + email, code); // 在 set 的時候貼的是 "reg_code_eric@gmail.com"
		session.setAttribute("reg_code_expiry_" + email, expiryTime); // 貼上標籤 及 過期時間

		// 4. SimpleMailMessage
		//  發送郵件並捕捉潛在異常
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(email);
			message.setSubject(messageService.getMessage("member-mail-code-subject"));
			message.setText(String.format(messageService.getMessage("member-mail-code-body"), code));
			mailSender.send(message);
		} catch (org.springframework.mail.MailSendException e) {
			// ✅ 捕捉 Log 中出現的郵件發送失敗異常
			System.err.println("郵件發送失敗詳情: " + e.getMessage());
			throw new MemberException(messageService.getMessage("member-error-email-invalid-address"));
		} catch (Exception e) {
			// ✅ 捕捉其他系統級郵件錯誤
			throw new MemberException(messageService.getMessage("member-error-mail-service"));
		}

	}

	@Override
	public boolean verifyRegistrationCode(String email, String code) {
		// TODO Auto-generated method stub
		// session.getAttribute("標籤名")：告訴櫃子管理員：「我要拿標籤叫『XXX』的那個盒子內容」。
		String sessionCode = (String) session.getAttribute("reg_code_" + email);
		return code.equals(sessionCode);
	}

}
