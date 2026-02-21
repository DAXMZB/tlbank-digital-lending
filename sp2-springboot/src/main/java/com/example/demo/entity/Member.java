package com.example.demo.entity;

import jakarta.persistence.*;
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

	@Column(name = "username", length = 50)
	private String username;

	@Size(min = 8, message = "密碼長度最少為8位")
	@Column(length = 100)
	private String password;

	@Column(length = 50)
	private String name;

	private String address; // Column with Address default is 255

	@Column(length = 20)
	private String phone;

	@Column(length = 20)
	private String mobile;
	
	@Column(length = 100)
	private String email;

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
