package com.example.demo.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "product")
@Data
public class Product {
	
	@Version // 新增 JPA 樂觀鎖機制，底層自動檢查 version 欄位
	private Integer version;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "商品不可為空")
	@Column(nullable = false)
	private String productName;

	@Column(length = 1000)
	private String description; // 商品描述

	// 將價格從 Integer/Double 改為 BigDecimal
	@Min(value = 0, message = "價格不可低於0")
	private BigDecimal price;
	
	@Min(value = 0, message = "庫存不可低於0")
	private Integer stock; // 補上庫存量
	
	private String imgUrl; // 商品圖片路徑

	@Column(updatable = false)
	private LocalDateTime createTime;

	@PrePersist 
	//利用 JPA 生命週期註解自動填入 createTime，不需要在 Service 層手動設定
	protected void onCreate() {
		createTime = LocalDateTime.now();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
}
