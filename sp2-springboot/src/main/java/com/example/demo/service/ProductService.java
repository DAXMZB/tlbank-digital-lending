package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.dto.product.ProductResponse;
import com.example.demo.entity.Product;

public interface ProductService {
	//取得所有商品 (支援分頁)
	Page<ProductResponse> getAllProducts(int page, int size);
	
	//根據 ID 取得商品詳細資訊
	ProductResponse getProductById(Integer id);

}
