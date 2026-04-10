package com.example.demo.dto.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class MemberRegisterRequest {

    @NotBlank(message = "帳號不可為空")
    @Size(min = 4, max = 30, message = "帳號長度需介於4到30字")
    private String username;

    @NotBlank(message = "密碼不可為空")
    @Size(min = 6, max = 50, message = "密碼長度需介於6到50字")
    private String password;

    @NotBlank(message = "姓名不可為空")
    private String name;

    @NotBlank(message = "地址不可為空") // 【修改】補上 address
    private String address;

    @NotBlank(message = "電話不可為空") // 【修改】補上 phone
    @Pattern(regexp = "^\\d{8}$", message = "電話格式錯誤，請輸入 8 位數字")
    private String phone;

    @NotBlank(message = "手機不可為空") // 【修改】補上 mobile
    @Pattern(regexp = "^09\\d{8}$", message = "手機格式錯誤，請輸入 10 位數字")
    private String mobile;

    @NotBlank(message = "Email不可為空")
    @Email(message = "Email格式不正確")
    private String email;

    @NotBlank(message = "驗證碼不可為空") // 【修改】補上 vertifyCode，對應你目前 service 的寫法
    private String vertifyCode;
    
    

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

    public String getAddress() { // 【修改】
        return address;
    }

    public void setAddress(String address) { // 【修改】
        this.address = address;
    }

    public String getPhone() { // 【修改】
        return phone;
    }

    public void setPhone(String phone) { // 【修改】
        this.phone = phone;
    }

    public String getMobile() { // 【修改】
        return mobile;
    }

    public void setMobile(String mobile) { // 【修改】
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVertifyCode() { // 【修改】
        return vertifyCode;
    }

    public void setVertifyCode(String vertifyCode) { // 【修改】
        this.vertifyCode = vertifyCode;
    }
}