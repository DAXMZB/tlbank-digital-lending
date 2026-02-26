package com.example.demo.entity;

import java.math.BigDecimal; // 【重點】導入精度計算類別

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "order_items")
@Data
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	@JsonBackReference //代表這是從的一方，呼應 Orders.java
	private Orders order; //屬於哪一筆訂單
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product; // 買了什麼商品
	// 將價格從 Integer/Double 改為 BigDecimal
	private Integer quantity; 	// 買了多少
	// precision: 總位數, scale: 小數位數。12,2 代表最大可存 9,999,999,999.99
	@Column(precision = 12, scale = 2)
	private BigDecimal price;		// 買的單價
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
}
