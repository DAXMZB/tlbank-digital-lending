package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Post;
import com.example.demo.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	@Autowired
	private PostService postService;

	@PostMapping("/create")
	public ResponseEntity<String> createPost(@RequestParam Integer memberId, @RequestParam String content) {
		postService.savePost(memberId, content);
		return ResponseEntity.ok("發佈成功！");
	}

	@GetMapping("/all")
	public ResponseEntity<Page<Post>> getAllPosts(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		// 呼叫 Service 進行分頁查詢
		return ResponseEntity.ok(postService.findPostByPage(page, size));
	}

	@DeleteMapping("/{postId}")
	public ResponseEntity<String> deletePost(@PathVariable Integer postId, @RequestParam Integer memberNo) {
		try {
			postService.deletePost(postId, memberNo);
			return ResponseEntity.ok("貼文已刪除");
		}catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
		}
		
		
	}

}
