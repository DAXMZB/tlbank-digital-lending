package com.example.demo.service.impl;

import java.math.BigDecimal;
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
import org.springframework.orm.ObjectOptimisticLockingFailureException;

@Service
public class OrderSerivceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private MemberRepository memberRepo;

	@Autowired
	private ProductRepository productRepo;

	

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

	// 避免商品庫存不足拋出異常，前面的商品可能已經被扣了庫存並存檔，會導致資料庫狀態混亂。
	@Transactional
	@Override
	public Orders createOrderBatch(Integer memberNo, List<CartItemDTO> cartItems) {
		// TODO Auto-generated method stub
		// 初始化金額為零
		BigDecimal grandTotal = BigDecimal.ZERO;

		// 使用 StringBuilder 優化字串接收效能
		StringBuilder invoiceDetails = new StringBuilder("=== 訂單明細內容 ===\\n");

		try {
			// 獲取會員
			Member member = memberRepo.findById(memberNo)
					.orElseThrow(() -> new MemberException("找不到會員 ID:" + memberNo));

			// 1.建立一張「訂單主表」紀錄
			Orders order = new Orders();
			order.setOrderNo("TL" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
			order.setMember(member);
			order.setStatus("Unpaid");

			List<OrderItem> details = new ArrayList<>();
			// 2.處理每一項商品並轉為訂單明細
			for (CartItemDTO dto : cartItems) {
				Product product = productRepo.findById(dto.getProductId())
						.orElseThrow(() -> new MemberException("找不到該商品"));

				// 檢查庫存
				if (product.getStock() < dto.getQuantity()) {
					throw new MemberException(product.getProductName() + "庫存不足，剩餘：" + product.getStock());
				}

				// 【延伸】將 int 轉為 BigDecimal 並使用 multipy 進行運算
				BigDecimal itemPrice = product.getPrice();
				BigDecimal itemTotal = itemPrice.multiply(new BigDecimal(dto.getQuantity()));
				grandTotal = grandTotal.add(itemTotal);
				
				// 【延伸】使用 append 拼接資訊，將 String 不可變性（Immutability）優化
				invoiceDetails.append("- ").append(product.getProductName())
								.append(" ｜ 數量： ").append(dto.getQuantity())
								.append(" ｜ 小記： ").append(itemTotal).append("\n");
				

				// 建立 OrderItem
				OrderItem detail = new OrderItem();
				detail.setOrder(order); // 關聯回同一張訂單
				detail.setProduct(product);
				detail.setQuantity(dto.getQuantity());
				detail.setPrice(product.getPrice());

				details.add(detail);
				
				// 扣庫存並觸發樂觀校驗 (version
				product.setStock(product.getStock() - dto.getQuantity());
				productRepo.save(product);
			}

			order.setItems(details); // 將所有明細塞進訂單
			order.setTotalAmount(grandTotal); // 直接賦值 BigDecimal
			; // 設定整筆訂單的總金額
				// svae 的同時，Hibernate 會自動檢查 version
			return orderRepo.save(order); // 一次存檔，產生一個 ID 與一個訂單編號
		} catch (ObjectOptimisticLockingFailureException e) {
			// 新增樂觀鎖衝突 當很多人搶購同一商品時會觸發
			throw new MemberException("系統忙碌中 (庫存更新衝突），請稍後再試");
		}catch (Exception e) {
			throw new MemberException("訂單結算發生意外錯誤：" + e.getMessage());
		}finally {
			// 即使上方執行了 return 或 throw ，這裡依然會執行
			// 用於資源釋放或強製性的審核日誌記錄
			invoiceDetails.append("總計金額：").append(grandTotal).append("\n");
			invoiceDetails.append("=== 結算日誌結束 ===");
			System.out.println(invoiceDetails.toString());
		}
		// 金融系統需要記錄每一筆「嘗試交易」的軌跡。
		//即使因為樂觀鎖衝突而失敗（沒存入資料庫），
			//系統仍需在日誌（Log）中記錄「使用者 Eric 在 21:40 嘗試購買 3 筆商品，總額 1,500 元，結果：併發衝突失敗」。
		//這能幫助工程師在事後查帳時，確認到底是程式 Bug 還是單純的高併發衝突。
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
