package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.product.ProductResponse;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	@Autowired
	private ProductService productService;

	@GetMapping
	public ResponseEntity<List<ProductResponse>> getAllProducts() { // 【修改】不再呼叫不存在的分頁方法
		return ResponseEntity.ok(productService.getAllProducts());
	}

	// 取得商品清單 API
	@GetMapping("/list")
	public ResponseEntity<List<ProductResponse>> getProductList() {

		return ResponseEntity.ok(productService.getAllProducts());
	}

	// 取得單一商品 API (可用於商品詳情頁)
	@GetMapping("/{id}")
	public ResponseEntity<ProductResponse> getProduct(@PathVariable Integer id) {
		return ResponseEntity.ok(productService.getProductById(id));
	}
}
