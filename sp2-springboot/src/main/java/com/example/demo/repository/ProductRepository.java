package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	// 如果未來需要根據產品名稱搜尋，可以預留此方法
	Page<Product> findByProductNameContaining(String name, Pageable pageable);

}
