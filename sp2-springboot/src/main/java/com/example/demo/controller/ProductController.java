package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	// 取得商品清單 API
	@GetMapping("/list")
	public ResponseEntity<Page<Product>> getProductList(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "6") int size){
//		page (預設值 "0")：代表要請求的頁碼，從 0 開始計算（即第一頁）。
//
//		size (預設值 "6")：代表每頁顯示的商品筆數。
		Page<Product> productPage = productService.getAllProducts(page, size);
		return ResponseEntity.ok(productPage);
	}
	// 取得單一商品 API (可用於商品詳情頁)
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable Integer id){
		return ResponseEntity.ok(productService.getProductById(id));
	}
}
