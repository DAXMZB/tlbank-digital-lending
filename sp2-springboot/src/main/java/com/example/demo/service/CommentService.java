package com.example.demo.service;

public interface CommentService {
	void saveComment(Integer postId, Integer memberNo, String content);
	void deleteComment(Integer commentId, Integer memberNo);
}
