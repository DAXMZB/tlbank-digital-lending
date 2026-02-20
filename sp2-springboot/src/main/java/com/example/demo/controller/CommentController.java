package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.CommentService;

@RestController
@RequestMapping("api/comments")
public class CommentController {
	@Autowired
	private CommentService commentService;

	@PostMapping("/create")
	public ResponseEntity<String> createComment(@RequestParam Integer postId, @RequestParam Integer memberNo,
			@RequestParam String content) {
		// 呼叫 Service 存入資料庫
		commentService.saveComment(postId, memberNo, content);

		return ResponseEntity.ok("回應成功！");
	}
}
