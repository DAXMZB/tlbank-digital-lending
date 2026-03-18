package com.example.demo.util;

import com.example.demo.dto.enums.OrderStatus;
import com.example.demo.exception.BusinessException;
import com.example.demo.exception.ErrorCode;

public class OrderStatusValidator {
	private OrderStatusValidator() {
    }

    public static void validateTransition(OrderStatus currentStatus, OrderStatus targetStatus) {
        if (currentStatus == targetStatus) {
            throw new BusinessException(ErrorCode.ORDER_STATUS_INVALID, "訂單狀態未改變，無需更新");
        }

        if (currentStatus == OrderStatus.CANCELLED || currentStatus == OrderStatus.REFUNDED) {
            throw new BusinessException(ErrorCode.ORDER_STATUS_INVALID, "已取消或已退款訂單不可再次變更狀態");
        }

        if (currentStatus == OrderStatus.UNPAID && targetStatus == OrderStatus.REFUNDED) {
            throw new BusinessException(ErrorCode.ORDER_STATUS_INVALID, "未付款訂單不可直接退款");
        }
    }
}
