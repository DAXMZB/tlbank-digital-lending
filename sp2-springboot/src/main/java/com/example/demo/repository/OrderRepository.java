package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Member;
import com.example.demo.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
	// 如果需要查詢特定會員的訂單，可以定義此方法
	Page<Orders> findByMember(Member member, Pageable pageable);
	
	// 使用 EntityGraph 確保一條 SQL 抓取訂單及明細與產品細節
	@EntityGraph(attributePaths = {"items", "items.product"})
	List<Orders> findByMember_MemberNo(Integer memberNo);
	
	@EntityGraph(attributePaths = {"items", "items.product"})
	Orders findByOrderNo(String orderNo);

}
