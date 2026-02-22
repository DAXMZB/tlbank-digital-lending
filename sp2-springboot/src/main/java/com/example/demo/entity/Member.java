package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size; // 必須引入這個包

@Entity // Tell Hibernate this is an Entity
@Table(name = "member")
public class Member {
	public Integer getMemberNo() {
		return memberNo;
	}

	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment
	private Integer memberNo;
	
	@NotBlank(message = "帳號不可為空") // 自動檢查 null 與 空字串
	@Column(name = "username", length = 50)
	private String username;

	@NotBlank(message = "密碼不可為空") // 自動檢查 null 與 空字串
	@Size(min = 8, message = "密碼長度最少為8位")
	@Column(length = 100)
	private String password;
	
	@NotBlank(message = "名字不可為空") // 自動檢查 null 與 空字串
	@Column(length = 50)
	private String name;
	@NotBlank(message = "地址不可為空") // 自動檢查 null 與 空字串
	private String address; // Column with Address default is 255
	
	@NotBlank(message = "電話不可為空")
	@Column(length = 20)
	private String phone;
	
	@NotBlank(message = "手機不可為空")
	@Column(length = 20)
	private String mobile;
	
	@NotBlank(message = "email 不可為空")
	@Column(length = 100)
	private String email;

	@Transient // 僅用於接收前端資料，不對應資料庫欄位
	private String vertifyCode;

	public String getVertifyCode() {
		return vertifyCode;
	}

	public void setVertifyCode(String vertifyCode) {
		this.vertifyCode = vertifyCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
