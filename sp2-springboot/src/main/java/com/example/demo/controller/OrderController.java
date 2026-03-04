package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CartItemDTO;
import com.example.demo.dto.OrderDTO;
import com.example.demo.entity.Orders;
import com.example.demo.service.MessageService;
import com.example.demo.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	@Autowired
	private OrderService orderService;

	@Autowired
	private MessageService messageService;

	@PostMapping("/createBatch")
	public ResponseEntity<?> createOrderBatch(@RequestParam Integer memberId, @RequestBody List<CartItemDTO> carItems) {
		// 這裡現在會接收到單個 Orders 物件
		Orders order = orderService.createOrderBatch(memberId, carItems);
		// 從 MessageService 撈取格式化範本，並填入訂單資訊
		// 假設資料庫 Key 為 order-success-alert 内容為 "結算成功！\n訂單編號：%s\n總金額：NT$ %s"
		String successMsg = String.format(messageService.getMessage("order-success-alert"), order.getOrderNo(),
				order.getTotalAmount());
		
		// 封裝在 Map 中回傳
		// 讓前端一次拿到 order 物件（更新 UI）與 message 字串（彈窗提示）
		return ResponseEntity.ok(Map.of("order", order, "message", successMsg));
	}

	@GetMapping("/member/{memberId}")
	public ResponseEntity<List<OrderDTO>> getMemberOrder(@PathVariable Integer memberId) {
		// 呼叫 Service 取得該會員所有訂單
		// 回傳乾淨的 DTO 列表
		return ResponseEntity.ok(orderService.getOrdersMember(memberId));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable Integer id) {
		orderService.deleteOrder(id);
		String successMsg = messageService.getMessage("order-success-delete");
		return ResponseEntity.ok(successMsg);
	}
}
