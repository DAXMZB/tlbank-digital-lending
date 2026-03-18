package com.example.demo.dto.order;

import com.example.demo.dto.enums.OrderStatus;

import jakarta.validation.constraints.NotNull;

public class OrderStatusUpdateRequest {
	@NotNull(message = "訂單狀態不可為空")
    private OrderStatus status;

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}
}
