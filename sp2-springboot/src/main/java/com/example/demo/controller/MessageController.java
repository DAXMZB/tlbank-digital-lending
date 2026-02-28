package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.MessageService;

@RestController
public class MessageController {
	@Autowired
	private MessageService messageService;

	/**
	 * 批次獲取系統訊息內容 用法:
	 * /api/messages/batch?keys=common-error-empty,order-success-delete
	 */

	@GetMapping("/api/messages/batch")
	public ResponseEntity<Map<String, String>> getMessages(@RequestParam String keys) {
		// 將前端傳來的逗號字串轉為 List
		List<String> keyList = Arrays.asList(keys.split(","));
		
		// 透過 messageService 撈取對應的內容並封裝成 Map 回傳
		Map<String, String> messages = keyList.stream()
				.collect(Collectors.toMap(
						key -> key,
						key -> messageService.getMessage(key.trim())
						));
		return ResponseEntity.ok(messages);
	}
}
