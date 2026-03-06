package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Comment {
 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Integer id;
 
 @Column(columnDefinition = "TEXT")
 private String content;
 
 private LocalDateTime commentTime;
 
 @ManyToOne
 @JoinColumn(name = "post_id")
 @JsonIgnore //避免循環引用
 private Post post;
 
 @ManyToOne
 @JoinColumn(name = "member_no")
 private Member member;
 
 @ManyToOne
 @JoinColumn(name = "parent_id")
 @JsonIgnore
 private Comment parentComment; // 父留言

 @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL)
 private List<Comment> replies = new ArrayList<>(); // 子回應

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

public LocalDateTime getCommentTime() {
	return commentTime;
}

public void setCommentTime(LocalDateTime commentTime) {
	this.commentTime = commentTime;
}

public Post getPost() {
	return post;
}

public void setPost(Post post) {
	this.post = post;
}

public Member getMember() {
	return member;
}

public void setMember(Member member) {
	this.member = member;
}
}
