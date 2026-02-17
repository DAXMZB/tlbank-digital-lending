package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.OrderService;

import jakarta.persistence.criteria.Order;

@RestController
@RequestMapping("/api/ecpay")
public class EcpayController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/checkout")
	public String checkout(@RequestBody List<Integer> orderIds) {
		// 1. 根據 ID 列表從資料庫加總實際金額
		// 假設加總後金額為 totalAmount

		// 2. 產生綠界要求的參數 (這裡建議使用綠界官方 Java SDK 會更簡單)
		// 必須包含 MerchantTradeNo (不能重複), MerchantTradeDate, TotalAmount, ItemName,
		// ReturnURL 等

		// 3. 計算簽章 (CheckMacValue)
		// 這是最難的部分，建議直接引入 ECPay SDK 來處理簽章邏輯

		String form = "<html><form id='ecpay-form' action='https://payment-stage.ecpay.com.tw/Cashier/PaymentV3' method='POST'>"
				+ "<input type='hidden' name='MerchantID' value='2000132'>" // 測試環境特店編號
				+ "<input type='hidden' name='MerchantTradeNo' value='DX" + System.currentTimeMillis() + "'>" // 動態產生成交編號
				+ "<input type='hidden' name='MerchantTradeDate' value='2026/02/16 22:40:00'>" // 需為當前時間
				+ "<input type='hidden' name='TotalAmount' value='420'>" // 改為變數 totalAmount
				+ "<input type='hidden' name='TradeDesc' value='Eric Coffee Checkout'>"
				+ "<input type='hidden' name='ItemName' value='精選咖啡等多筆商品'>"
				+ "<input type='hidden' name='ReturnURL' value='https://yourdomain.com/api/ecpay/callback'>" // 支付成功後的回傳路徑
				+ "<input type='hidden' name='ChoosePayment' value='ALL'>" // 提供所有付款方式
				+ "<input type='hidden' name='CheckMacValue' value='具備真實加密後的簽章'>" // 關鍵！
				+ "</form><script>document.getElementById('ecpay-form').submit();</script></html>";
		return form;
	}

}
