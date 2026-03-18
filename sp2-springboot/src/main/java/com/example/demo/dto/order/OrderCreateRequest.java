package com.example.demo.dto.order;

import java.util.List;

import com.example.demo.dto.CartItemDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class OrderCreateRequest {
	@NotNull(message = "會員編號不可為空")
    private Integer memberNo;

    @NotEmpty(message = "購物車內容不可為空")
    private List<CartItemDTO> cartItems;

	public Integer getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}

	public List<CartItemDTO> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItemDTO> cartItems) {
		this.cartItems = cartItems;
	}
}
