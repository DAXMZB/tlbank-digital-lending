package com.example.demo.repository;

import com.example.demo.entity.Member; //import Entity

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository; //import JPA Repository
import org.springframework.stereotype.Repository;

//不需要寫任何 SQL，因為 JpaRepository 已經內建了對 Pageable 的支援。
@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
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
	Optional<Member> findByUsernameAndPassword(String username, String password);

	Optional<Member> findByUsername(String username);

	boolean existsByUsername(String username);
	// 不需要額外寫 findAll(Pageable)，JpaRepository 已經內建了
}
