package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
// 專門存放「訊息代碼」與「實際文字」的 Table
@Entity
@Table(name = "error_messages")
@Data
public class ErrorMessage {
	@Id
	@Column(name = "msg_key", length = 50)
	private String msgKey; // 例如: "order-error-batch", "member-error-notfound"

	@Column(name = "msg_content", nullable = false, length = 255)
	private String msgContent; // 例如: "訂單結算發生意外錯誤"

	public String getMsgKey() {
		return msgKey;
	}

	public void setMsgKey(String msgKey) {
		this.msgKey = msgKey;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	
}
