package com.example.demo.repository.bank;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.bank.BankTransaction;

public interface BankTransactionRepository extends JpaRepository<BankTransaction, Integer>{
	
	List<BankTransaction> findByAccount_AccountNoOrderByCreatedAtDesc(String accountNo);
}
