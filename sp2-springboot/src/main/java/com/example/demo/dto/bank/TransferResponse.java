package com.example.demo.dto.bank;

import java.math.BigDecimal;

public class TransferResponse {
	private String transactionNo;
	private String fromAccountNo;
	private String toAccountNo;
	private BigDecimal amount;
	private BigDecimal fromBalanceAfter;
	private BigDecimal toBalanceAfter;
	private String remark;
	private String message;

	public String getTransactionNo() {
		return transactionNo;
	}

	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}

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

	public BigDecimal getFromBalanceAfter() {
		return fromBalanceAfter;
	}

	public void setFromBalanceAfter(BigDecimal fromBalanceAfter) {
		this.fromBalanceAfter = fromBalanceAfter;
	}

	public BigDecimal getToBalanceAfter() {
		return toBalanceAfter;
	}

	public void setToBalanceAfter(BigDecimal toBalanceAfter) {
		this.toBalanceAfter = toBalanceAfter;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
