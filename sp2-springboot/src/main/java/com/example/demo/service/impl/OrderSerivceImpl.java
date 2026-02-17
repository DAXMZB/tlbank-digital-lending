package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.CartItemDTO;
import com.example.demo.entity.Member;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Orders;
import com.example.demo.entity.Product;
import com.example.demo.exception.MemberException;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.OrderService;

@Service
public class OrderSerivceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private MemberRepository memberRepo;

	@Autowired
	private ProductRepository productRepo;

	@Override
	@Transactional
	public Orders createOrder(Integer memberNo, Integer productId, Integer quantity) {
		// 1.驗證會員與商品是否存在 (利用現有的 Exception 機制)
		Member member = memberRepo.findById(memberNo).orElseThrow(() -> new MemberException("找不到該會員"));

		Product product = productRepo.findById(productId).orElseThrow(() -> new MemberException("找不到該商品"));

		// 2.檢查庫存
		if (product.getStock() < quantity) {
			throw new MemberException("商品庫存不足，剩餘：" + product.getStock());
		}
		// 3.建立訂單物件
		Orders order = new Orders();
		// 產生唯一訂單號 (金流規格通常建議英數混合，這裡用 UUID 前 8 碼 搭配時間)
		String orderNo = "TL" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

		order.setOrderNo(orderNo);
		order.setMember(member);
		// 計算總金額 (單價 * 數量)
		int total = product.getPrice() * quantity;
		order.setTotalAmount(total);

		order.setStatus("Unpaid"); // 預設狀態

		// 4. (選配) 扣減庫存
		product.setStock(product.getStock() - 1);
		productRepo.save(product);
		return orderRepo.save(order);
	}

	@Override
	public List<Orders> getOrdersMember(Integer memberNo) {
		// TODO Auto-generated method stub
		return orderRepo.findByMember_MemberNo(memberNo);
	}

	@Override
	public Orders getByOrderNo(String orderNo) {
		// TODO Auto-generated method stub
		return orderRepo.findByOrderNo(orderNo);
	}

	@Override
	public Orders createOrderBatch(Integer memberNo, List<CartItemDTO> cartItems) {
		// TODO Auto-generated method stub
		// 獲取會員
		Member member = memberRepo.findById(memberNo).orElseThrow(() -> new MemberException("找不到會員 ID:" + memberNo));

		// 1.建立一張「訂單主表」紀錄
		Orders order = new Orders();
		order.setOrderNo("TL" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
		order.setMember(member);
		order.setStatus("Unpaid");

		int grandTotal = 0;
		List<OrderItem> details = new ArrayList<>();
		// 2.處理每一項商品並轉為訂單明細
		for (CartItemDTO dto : cartItems) {
			Product product = productRepo.findById(dto.getProductId()).orElseThrow(() -> new MemberException("找不到該商品"));

			if (product.getStock() < dto.getQuantity()) {
				throw new MemberException(product.getProductName() + "庫存不足");
			}

			OrderItem detail = new OrderItem();
			detail.setOrder(order); // 關聯回同一張訂單
			detail.setProduct(product);
			detail.setQuantity(dto.getQuantity());
			detail.setPrice(product.getPrice());

			details.add(detail);
			grandTotal += (product.getPrice() * dto.getQuantity());

			// 扣庫存
			product.setStock(product.getStock() - dto.getQuantity());
			productRepo.save(product);
		}

		order.setItems(details); // 將所有明細塞進訂單
		order.setTotalAmount(grandTotal);
		; // 設定整筆訂單的總金額

		return orderRepo.save(order); // 一次存檔，產生一個 ID 與一個訂單編號
	}

	@Override
	public void deleteOrder(Integer id) {
		// 檢查訂單是否存在
		if (!orderRepo.existsById(id)) {
			throw new MemberException("訂單不存在");
		}
		orderRepo.deleteById(id);

	}

	

}
