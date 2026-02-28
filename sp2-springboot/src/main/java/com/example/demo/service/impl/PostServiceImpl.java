package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Member;
import com.example.demo.entity.Post;
import com.example.demo.exception.MemberException;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.service.MessageService;
import com.example.demo.service.PostService;

import jakarta.transaction.Transactional;

@Service // 新增：漏掉這個註解 Spring 會找不到這個 Bean
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepository postRepo;

	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private MessageService messageService; // ✅ 注入訊息服務

	@Override
	@Transactional // 確保資料庫交易完整性
	public void savePost(Integer memberId, String content) {
		// 1. 找出發文的會員
		Member member = memberRepo.findById(memberId)
				.orElseThrow(() -> new MemberException(messageService.getMessage("post-error-member-notfound")));

		// 2. 建立貼文物件並設定屬性
		Post post = new Post();
		post.setContent(content);
		post.setMember(member);
		post.setPostTime(LocalDateTime.now()); // 設定為當前系統時間

		// 3. 儲存至資料庫
		postRepo.save(post);

	}

	@Override
	public List<Post> findAllPosts() {
		// 使用 JpaRepository 內建的 findAll 並搭配排序
		
		return postRepo.findAll(Sort.by(Sort.Direction.DESC, "postTime"));
	}

	@Override
	@Transactional
	public void deletePost(Integer postId, Integer memberNo) {
		// 1.找出該貼文
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new MemberException(messageService.getMessage("post-error-notfound")));
		
		// 2.[關鍵] 校驗權限：檢查貼文擁有人是否為當前操作者
		// 比對的是 Member 實體中的 ID
		
		if(!post.getMember().getMemberNo().equals(memberNo)) {
			throw new MemberException(messageService.getMessage("post-error-delete-forbidden"));
		}
		postRepo.deleteById(postId);
		
	}

	@Override
	public Page<Post> findPostByPage(int page, int size) {
		// 建立分頁請求，按時間倒序排序
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "postTime"));
		
		return postRepo.findAll(pageable);
	}

}
