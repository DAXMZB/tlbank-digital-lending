package com.example.demo.repository.bank;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.bank.BankAccount;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer>{
	
	// Optional -> 強制檢查物件是否為 null
	Optional<BankAccount> findByAccountNo(String AccountNo);
	
	List<BankAccount> findByMember_MemberNo(Integer memberNo);
	
	boolean existsByAccountNo(String accountNo);
}
