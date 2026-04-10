package com.example.demo.entity.bank;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.demo.dto.enums.bank.AccountStatus;
import com.example.demo.entity.Member;

import jakarta.persistence.*;

@Entity
@Table
public class BankAccount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "account_no", nullable = false, unique = true, length = 30)
	private String accountNo;
	
	@ManyToOne // Means many BankAccount belong one member
	@JoinColumn(name = "member_no", nullable = false)
	private Member member;
	
	@Column(name = "account_name", nullable = false, length = 100)
	private String acountName;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private AccountStatus status;
	
	@Column(nullable = false, precision = 19, scale = 2)
	private BigDecimal balance;
	
	@Version
	private Integer version;
	
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;
	
	@Column(name = "update_at", nullable = false)
	private LocalDateTime updatedAt;
	
	@PrePersist
	public void onCreate() {
		LocalDateTime now = LocalDateTime.now();
		this.createdAt = now;
		this.updatedAt = now;
		
		if (status == null) {
			this.status = AccountStatus.ACTIVE;
		}
		
		if (balance == null) {
			this.balance = BigDecimal.ZERO;
		}
		
	}
	
	public void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getAcountName() {
		return acountName;
	}

	public void setAcountName(String acountName) {
		this.acountName = acountName;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
}
