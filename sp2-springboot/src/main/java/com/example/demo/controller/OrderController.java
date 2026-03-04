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
		// 這裡現在會接收到單個 Orders 物件（ 執行結帳邏輯取得實體
		Orders order = orderService.createOrderBatch(memberId, carItems);
		// 將實體轉為 DTO (使用寫好的 Service 轉換邏輯)
		OrderDTO orderDto = orderService.convertToDTO(order);
		// 從 MessageService 撈取格式化範本，並填入訂單資訊
		// 並填入 DTO 的資訊
		String successMsg = String.format(messageService.getMessage("order-success-alert"), orderDto.getOrderNo(),
				orderDto.getTotalAmount());
		
		// 封裝 DTO 在 Map 中回傳，避免前端直接碰到 Entity
		return ResponseEntity.ok(Map.of("order", orderDto, "message", successMsg));
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
