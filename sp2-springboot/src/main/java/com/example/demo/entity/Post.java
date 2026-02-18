package com.example.demo.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Post {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "TEXT")
	private String content; //留言內容
	
	private LocalDateTime postTime;
	
	@ManyToOne
	@JoinColumn(name = "member_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // 防止延遲加載產生的 JSON 錯誤
	private Member member; //關聯發文會員

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getPostTime() {
		return postTime;
	}

	public void setPostTime(LocalDateTime postTime) {
		this.postTime = postTime;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	
	
}
