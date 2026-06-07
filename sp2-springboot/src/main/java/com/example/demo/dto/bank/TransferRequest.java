package com.example.demo.dto.bank;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TransferRequest {
	
	@NotBlank(message = "fromAccountNo 不可為空")
	private String fromAccountNo;
	
	@NotBlank(message = "toAccountNo 不可為空")
	private String toAccountNo;
	
	@NotNull(message = "amount 不可為空")
	@DecimalMin(value = "0.01", inclusive = true, message = "轉帳金額需 > 0")
	private BigDecimal amount;
	
	@Size(max = 255, message = "remark 長度不可 > 255")
	private String remark;

	public String getFromAccountNo() {
		return fromAccountNo;
	}

	public void setFromAccountNo(String fromAccountNo) {
		this.fromAccountNo = fromAccountNo;
	}

	public String getToAccountNo() {
		return toAccountNo;
	}

	public void setToAccountNo(String toAccountNo) {
		this.toAccountNo = toAccountNo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	

}
