package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ErrorMessage;

// 效能考量，不能每次報錯都去查資料庫，因此配合一個簡單的 Map 做快取。
public interface ErrorMessageRepository extends JpaRepository<ErrorMessage, String> {

}
