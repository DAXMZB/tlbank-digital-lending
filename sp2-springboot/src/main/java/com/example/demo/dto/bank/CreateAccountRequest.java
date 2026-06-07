package com.example.demo.dto.bank;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateAccountRequest {
	
	@NotNull(message = "memberNO 不可為空")
	private Integer memberNo;
	
	@NotBlank(message = "accountName 不可為空")
	private String accountName;
	
	@DecimalMin(value = "0.0", inclusive = true, message = "initialBalance 不可小於 0")
	private BigDecimal initialBalance;

	public Integer getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public BigDecimal getInitialBalance() {
		return initialBalance;
	}

	public void setInitialBalance(BigDecimal initialBalance) {
		this.initialBalance = initialBalance;
	}
}
