package com.example.demo.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Member;
import com.example.demo.entity.Post;
import com.example.demo.exception.MemberException;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.service.CommentService;
import com.example.demo.service.MessageService;

import jakarta.transaction.Transactional;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentRepository commentRepo;
	@Autowired
	private PostRepository postRepo;
	@Autowired
	private MemberRepository memberRepo;
	@Autowired
	private MessageService messageService; // ✅ 注入

	@Override
	@Transactional
	public void saveComment(Integer postId, Integer memberNo, String content) {

		// 驗證資料是否存在
		// 1. ✅ 驗證資料是否存在：改由 MessageService 撈取訊息
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new MemberException(messageService.getMessage("comment-error-post-notfound")));
		Member member = memberRepo.findById(memberNo)
				.orElseThrow(() -> new MemberException(messageService.getMessage("common-msg-login-required")));

		// 建立留言物件，並設立關聯
		Comment comment = new Comment();
		comment.setContent(content);
		comment.setPost(post); // 設定留言數與哪則貼文
		comment.setMember(member); // 設定是誰的留言
		comment.setCommentTime(LocalDateTime.now());

		// 存入資料庫
		commentRepo.save(comment);

	}

	@Override
	@Transactional
	public void deleteComment(Integer commentId, Integer memberNo) {
		// 1. 找出該留言
		Comment comment = commentRepo.findById(commentId)
				.orElseThrow(() -> new MemberException(messageService.getMessage("comment-error-notfound")));

		// 2. 校驗權限：檢查留言擁有人是否為當前操作者
		// 透過 comment.getMember().getMemberNo() 取得留言者的 ID
		if (!comment.getMember().getMemberNo().equals(memberNo)) {
			throw new MemberException(messageService.getMessage("comment-error-delete-forbidden"));
		}
		
		// 3. 執行刪除
		commentRepo.delete(comment);		
	}

}
