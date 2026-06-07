package com.example.demo.dto.bank;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionResponse {

	private String txNo;
	private String transactionType;
	private String accountNo;
	private String counterpartyAccountNo;
	private BigDecimal amount;
	private BigDecimal balanceBefore;
	private BigDecimal balanceAfter;
	private String remark;
	private LocalDateTime createdAt;

	public String getTxNo() {
		return txNo;
	}

	public void setTxNo(String txNo) {
		this.txNo = txNo;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getCounterpartyAccountNo() {
		return counterpartyAccountNo;
	}

	public void setCounterpartyAccountNo(String counterpartyAccountNo) {
		this.counterpartyAccountNo = counterpartyAccountNo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getBalanceBefore() {
		return balanceBefore;
	}

	public void setBalanceBefore(BigDecimal balanceBefore) {
		this.balanceBefore = balanceBefore;
	}

	public BigDecimal getBalanceAfter() {
		return balanceAfter;
	}

	public void setBalanceAfter(BigDecimal balanceAfter) {
		this.balanceAfter = balanceAfter;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
