package com.example.demo.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
//「懶人包」註解，相當於同時啟動了以下幾個功能：
//Getter & Setter：為類別中所有的私有變數生成 getXXX() 和 setXXX() 方法。
//toString()：生成一個格式漂亮的字串，方便你在 Debug 時直接印出物件內容（例如：OrderDTO(id=1, orderNo=...)）。
//equals() & hashCode()：生成邏輯比較方法，確保內容相同的兩個物件在 Set 或 Map 中運作正常。
//RequiredArgsConstructor：為標記為 final 的變數生成建構子。
@Data
public class OrderDTO {
	private Integer id;
	private String orderNo;
	private BigDecimal totalAmount;
	private String status;
	private String paymentType;
	private String orderTime;
	private List<OrderItemDTO> items;
}
