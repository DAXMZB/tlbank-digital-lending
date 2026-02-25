package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.dto.CartItemDTO;
import com.example.demo.entity.Orders;

public interface OrderService {
	// 建立訂單
	// 將參數增加 Integer quantity
	

	// 查詢特定會員的所有訂單
	List<Orders> getOrdersMember(Integer memberNo);

	// 透過訂單編號查詢 (未來串接金流回傳時會用到(
	Orders getByOrderNo(String orderNo);

	// 批次訂單方法
	Orders createOrderBatch(Integer memberNo, List<CartItemDTO> items);

	// 刪除訂單
	void deleteOrder(Integer id);

	
}
