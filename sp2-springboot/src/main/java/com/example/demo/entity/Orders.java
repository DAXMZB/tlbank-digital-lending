package com.example.demo.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.dto.enums.OrderStatus;
import com.example.demo.dto.enums.PaymentType;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "order_no", unique = true, nullable = false)
	private String orderNo; // 訂單編號 (可用UUID 或時間戳

	// 設計 Orders 與 Member 的多對一關係，確保每一筆訂單都能追溯到特定的會員
	@ManyToOne // 多筆訂單對應一個會員
	@JoinColumn(name = "member_id")
	private Member member;
	// 增加與明細的一對多關係
	// 必須補上這兩個註解，告訴 JPA 這是一個「一對多」的關係註解。
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference // 代表這是主的一方 (呼應 OrderItem.java
	private List<OrderItem> items = new ArrayList();

	// 將價格從 Integer/Double 改為 BigDecimal
	// precision: 總位數, scale: 小數位數。12,2 代表最大可存 9,999,999,999.99
	@Column(precision = 12, scale = 2)
	private BigDecimal totalAmount; // 總金額

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private OrderStatus status; // 狀態：Unpaid(代付款), Paid(已付款), Cacelled(已取消)

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private PaymentType paymentType; // 支付方式：CreditCard, LinePay, ATM

	@Column(nullable = false, updatable = false)
	private LocalDateTime orderTime;

	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(nullable = false)
	private LocalDateTime updatedAt;

	@PrePersist
	protected void onCreate() {
		LocalDateTime now = LocalDateTime.now();
		this.orderTime = now; // 記錄訂單成立的時間。
		this.createdAt = now; // 紀錄訂單寫入資料庫的時間
		this.updatedAt = now; // 紀錄最後更新的時間
		if (this.status == null) {
			this.status = OrderStatus.UNPAID;
		}
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

//	@PrePersist
//	protected void onOrder() {
//		orderTime = LocalDateTime.now();
//		if (status == null)
//			status = "unpaid";
//	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public LocalDateTime getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(LocalDateTime orderTime) {
		this.orderTime = orderTime;
	}

}
