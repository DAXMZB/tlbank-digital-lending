package com.example.demo.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Member;
import com.example.demo.entity.Post;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.service.CommentService;

import jakarta.transaction.Transactional;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentRepository commentRepo;
	@Autowired
	private PostRepository postRepo;
	@Autowired
	private MemberRepository memberRepo;

	@Override
	@Transactional
	public void saveComment(Integer postId, Integer memberNo, String content) {
		
		// 驗證資料是否存在
		Post post = postRepo.findById(postId).orElseThrow(() -> new RuntimeException("貼文不存在"));
		Member member = memberRepo.findById(memberNo).orElseThrow(() -> new RuntimeException("請先登入"));
		
		// 建立留言物件，並設立關聯
		Comment comment = new Comment();
		comment.setContent(content);
		comment.setPost(post); // 設定留言數與哪則貼文
		comment.setMember(member); // 設定是誰的留言
		comment.setCommentTime(LocalDateTime.now());
		
		// 存入資料庫
		commentRepo.save(comment);

	}

}
