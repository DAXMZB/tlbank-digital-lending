package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.ApiResponse;
import com.example.demo.dto.CartItemDTO;
import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.order.OrderCreateRequest;
import com.example.demo.dto.order.OrderStatusUpdateRequest;
import com.example.demo.entity.Orders;
import com.example.demo.service.MessageService;
import com.example.demo.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	@Autowired
    private OrderService orderService;
	
	// 🚀 【新增】對接前端的批量結帳路徑
    @PostMapping("/createBatch")
    public ApiResponse<OrderDTO> createOrderBatch(
            @RequestBody List<CartItemDTO> cartItems, 
            @RequestParam Integer memberId) {
        
        // 呼叫 Service 執行扣庫存、存訂單邏輯
        Orders order = orderService.createOrderBatch(memberId, cartItems);
        
        // 轉為 DTO 並回傳
        return ApiResponse.success(orderService.convertToDTO(order), "訂單建立成功！");
    }

    @PostMapping
    public ResponseEntity<ApiResponse<OrderDTO>> createOrder(@Valid @RequestBody OrderCreateRequest request) {
        Orders order = orderService.createOrderBatch(request.getMemberNo(), request.getCartItems());
        return ResponseEntity.ok(ApiResponse.success(orderService.convertToDTO(order), "訂單建立成功"));
    }

    @GetMapping("/member/{memberNo}")
    public ResponseEntity<ApiResponse<List<OrderDTO>>> getOrdersByMember(@PathVariable Integer memberNo) {
        return ResponseEntity.ok(ApiResponse.success(orderService.getOrdersMember(memberNo)));
    }

    @GetMapping("/{orderNo}")
    public ResponseEntity<ApiResponse<OrderDTO>> getByOrderNo(@PathVariable String orderNo) {
        return ResponseEntity.ok(ApiResponse.success(orderService.convertToDTO(orderService.getByOrderNo(orderNo))));
    }

    @PatchMapping("/{orderNo}/status")
    public ResponseEntity<ApiResponse<OrderDTO>> updateStatus(
            @PathVariable String orderNo,
            @Valid @RequestBody OrderStatusUpdateRequest request) {
        Orders order = orderService.updateOrderStatus(orderNo, request.getStatus());
        return ResponseEntity.ok(ApiResponse.success(orderService.convertToDTO(order), "訂單狀態更新成功"));
    }

    @PostMapping("/{orderNo}/cancel")
    public ResponseEntity<ApiResponse<String>> cancelOrder(@PathVariable String orderNo) {
        orderService.cancelOrder(orderNo);
        return ResponseEntity.ok(ApiResponse.success("OK", "訂單取消成功並已回補庫存"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok(ApiResponse.success("OK", "訂單刪除成功"));
    }
}
