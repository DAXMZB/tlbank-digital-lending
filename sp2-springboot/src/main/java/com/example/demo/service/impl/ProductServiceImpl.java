package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.product.ProductResponse;
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
	public Page<ProductResponse> getAllProducts(int page, int size) {
		// 建立分頁請求物件
		// 1. 建立分頁請求物件 (page 是從 0 開始的索引)
		Pageable pageable = PageRequest.of(page, size);
		
		// 2. 直接呼叫 Repository 的分頁查詢功能
		Page<Product> productPage = productRepo.findAll(pageable);
		
		// 3. 利用 Page 的 map 方法，將每一筆 Entity 轉為 DTO
	    // 這會維持分頁的所有元數據 (metadata)，如總頁數、是否為第一頁等
		return productPage.map(this::convertToDTO);
	}

	@Override
	public ProductResponse getProductById(Integer id) {
		// 找不到商品，拋出自定義異常，由全域處理器捕捉
		// ✅ 調整：從資料庫撈取帶有 %d 佔位符的訊息，並填入商品 ID
		Product product = productRepo.findById(id)
                .orElseThrow(() -> new MemberException(
                		String.format(messageService.getMessage("product-error-notfound"), id)
                		));

        return convertToDTO(product);
				
	}
	private ProductResponse convertToDTO(Product product) {
        ProductResponse dto = new ProductResponse();
        dto.setId(product.getId());
        dto.setProductName(product.getProductName());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        dto.setDescription(product.getDescription());
        return dto;
    }
}
