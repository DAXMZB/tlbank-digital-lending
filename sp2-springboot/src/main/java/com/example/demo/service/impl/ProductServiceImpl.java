package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.exception.MemberException;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.MessageService;
import com.example.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private MessageService messageService; // ✅ 注入訊息服務
	@Override
	public Page<Product> getAllProducts(int page, int size) {
		// 建立分頁請求物件
		Pageable pageable = PageRequest.of(page, size);
		return productRepo.findAll(pageable);
	}

	@Override
	public Product getProductById(Integer id) {
		// 找不到商品，拋出自定義異常，由全域處理器捕捉
		// ✅ 調整：從資料庫撈取帶有 %d 佔位符的訊息，並填入商品 ID
		return productRepo.findById(id)
						.orElseThrow(() -> new MemberException(
							String.format(messageService.getMessage("product-error-notfound-id"), id)
						));
				
	}

}
