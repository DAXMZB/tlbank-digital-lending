package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.CommentService;
import com.example.demo.service.MessageService;

@RestController
@RequestMapping("api/comments")
public class CommentController {
	@Autowired
	private CommentService commentService;
	@Autowired
	private MessageService messageService; // ✅ 注入
	@PostMapping("/create")
	public ResponseEntity<String> createComment(@RequestParam Integer postId, @RequestParam Integer memberNo,
			@RequestParam String content) {
		// 呼叫 Service 存入資料庫
		commentService.saveComment(postId, memberNo, content);
		// ✅ 調整：從資料庫撈取成功訊息
		return ResponseEntity.ok(messageService.getMessage("comment-msg-create-success"));
	}
	@DeleteMapping("/{commentId}")
	public ResponseEntity<String> deleteComment(
			@PathVariable Integer commentId,
			@RequestParam Integer memberNo){
		commentService.deleteComment(commentId, memberNo);
		
		// 從資料庫撈取刪除成功訊息，例如： "留言已成功刪除"
		return ResponseEntity.ok(messageService.getMessage("comment-msg-delete-success"));
	}
}
