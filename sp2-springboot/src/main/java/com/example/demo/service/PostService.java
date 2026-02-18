package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.entity.Post;

public interface PostService {
	// 介面新增
	List<Post> findAllPosts();

	// 處理分頁與排序
	Page<Post> findPostByPage(int page, int size);

	// 定義儲存貼文方碼
	void savePost(Integer memberId, String content);

	void deletePost(Integer id);

}
