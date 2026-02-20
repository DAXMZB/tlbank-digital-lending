package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Comment;

//不需要寫任何 SQL，因為 JpaRepository 已經內建了對 Pageable 的支援。
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
	/*
	 * Generic Types Explained 1.Member：This is the Entity class the repository will
	 * manage. 2.Integer：This is the data type of the Primary Key(@Id) in the Member
	 * class
	 */
//	自動生成 CRUD：Spring Data JPA 會在程式啟動時，自動幫你實作所有的基本功能，你不需要寫任何 SQL 指令。
//
//	內建的方法包括：
//
//	C (Create)：repo.save(m)
//
//	R (Read)：repo.findById(id) 或 repo.findAll()
//
//	U (Update)：同樣是 repo.save(m) (當 ID 已存在時會自動變成更新)
//
//	D (Delete)：repo.deleteById(id)
}
