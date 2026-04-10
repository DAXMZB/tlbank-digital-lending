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

import com.example.demo.common.ApiResponse;
import com.example.demo.dto.product.ProductPageResponse;
import com.example.demo.dto.product.ProductResponse;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	@Autowired
	private ProductService productService;

//	@GetMapping
//	public ApiResponse<List<ProductResponse>> getAllProducts() { // 【修改】不再呼叫不存在的分頁方法
//        
//		
//		return ApiResponse.success(productService.getAllProducts()); // 【修改】統一回傳格式
//
//	}

	// 取得商品清單 API
	@GetMapping("/list")
	public ApiResponse<ProductPageResponse> getProductList(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "6") int size) {
		
		Page<ProductResponse> products = productService.getAllProducts(page, size);
		
		ProductPageResponse dto = new ProductPageResponse();
	    dto.setContent(products.getContent());
	    dto.setNumber(products.getNumber());
	    dto.setTotalPages(products.getTotalPages());
	    dto.setFirst(products.isFirst());
	    dto.setLast(products.isLast());	
	    
        return ApiResponse.success(dto); // 【修改】統一回傳格式

	}

	// 取得單一商品 API (可用於商品詳情頁)
	@GetMapping("/{id}")
	public ApiResponse<ProductResponse> getProduct(@PathVariable Integer id) {
        return ApiResponse.success(productService.getProductById(id)); // 【修改】統一回傳格式

	}
}
