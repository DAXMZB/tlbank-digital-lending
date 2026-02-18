package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{
	// 預設已包含 save, find, findAll CRUD 等功能

}
