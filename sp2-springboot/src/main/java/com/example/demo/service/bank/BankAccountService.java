package com.example.demo.service.bank;

import java.util.List;

import com.example.demo.dto.bank.AccountResponse;
import com.example.demo.dto.bank.CreateAccountRequest;

public interface BankAccountService {
	// By com.example.demo.dto.bank
	AccountResponse createAccount(CreateAccountRequest request);
	
	AccountResponse getByAccountNo(String accountNo);
	
	List<AccountResponse> getAccountsByMemberNo(Integer memberNo);
}
