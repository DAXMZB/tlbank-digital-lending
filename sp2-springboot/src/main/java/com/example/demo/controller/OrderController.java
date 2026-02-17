package com.example.demo.controller;

import java.util.List;

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
import com.example.demo.entity.Orders;
import com.example.demo.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	@Autowired
	private OrderService orderService;

	@PostMapping("/create")
	// 呼叫 Service 層 建立訂單，錯誤由 GlobalExceptionHandler 攔截
	public ResponseEntity<Orders> createOrder(@RequestParam Integer memberId, @RequestParam Integer productId,
			@RequestParam(defaultValue = "1") Integer quantity) {

		Orders newOrder = orderService.createOrder(memberId, productId, quantity);
		return ResponseEntity.ok(newOrder);
	}

	@PostMapping("/createBatch")
	public ResponseEntity<?> createOrderBatch(@RequestParam Integer memberId, @RequestBody List<CartItemDTO> carItems) {
		// 這裡現在會接收到單個 Orders 物件
		Orders order = orderService.createOrderBatch(memberId, carItems);
		return ResponseEntity.ok(order);
	}

	@GetMapping("/member/{memberId}")
	public ResponseEntity<List<Orders>> getMemberOrder(@PathVariable Integer memberId) {
		// 呼叫 Service 取得該會員所有訂單
		List<Orders> orders = orderService.getOrdersMember(memberId);
		return ResponseEntity.ok(orders);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable Integer id) {
		orderService.deleteOrder(id);
		return ResponseEntity.ok("訂單已成功刪除");
	}
}
