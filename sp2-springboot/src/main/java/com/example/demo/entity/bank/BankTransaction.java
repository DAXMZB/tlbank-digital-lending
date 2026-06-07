package com.example.demo.entity.bank;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.demo.dto.enums.bank.TransactionType;

import jakarta.persistence.*;

// This entity store immutable transaction trail data for auditing
@Entity
@Table(name = "bank_transation")
public class BankTransaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "tx_no", nullable = false, unique = true, length = 40)
	private String txNo;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "transaction_type", nullable = false, length = 30)
	private TransactionType transactiontype;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id", nullable = false)
	private BankAccount account;
	
	@Column(name = "computerparty_account_no", length = 30)
	private String computerpartyAccountNo;
	
	@Column(nullable = false, precision = 19, scale = 2)
	private BigDecimal amount;
	
	@Column(name = "balance_before", nullable = false, precision = 19, scale = 2)
	private BigDecimal balanceBefore;
	
	@Column(name = "balance_after", nullable = false, precision = 19, scale= 2)
	private BigDecimal balanceAfter;
	
	@Column(length = 255)
	private String remark;
	
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt; 
	
	public void onCreate() {
		this.createdAt = LocalDateTime.now();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTxNo() {
		return txNo;
	}

	public void setTxNo(String txNo) {
		this.txNo = txNo;
	}

	public TransactionType getTransactiontype() {
		return transactiontype;
	}

	public void setTransactiontype(TransactionType transactiontype) {
		this.transactiontype = transactiontype;
	}

	public BankAccount getAccount() {
		return account;
	}

	public void setAccount(BankAccount account) {
		this.account = account;
	}

	public String getComputerpartyAccountNo() {
		return computerpartyAccountNo;
	}

	public void setComputerpartyAccountNo(String computerpartyAccountNo) {
		this.computerpartyAccountNo = computerpartyAccountNo;
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
