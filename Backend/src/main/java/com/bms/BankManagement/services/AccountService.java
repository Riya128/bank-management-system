package com.bms.BankManagement.services;

import com.bms.BankManagement.entity.AccountEntity;
import com.bms.BankManagement.entity.CustomerEntity;
import com.bms.BankManagement.entity.TransactionEntity;

public interface AccountService {

	public AccountEntity createAccount(CustomerEntity customer);
	public AccountEntity findByCustomerEntity(CustomerEntity customerEntity);
	public AccountEntity findByAccountNumber(Long accountNumber);
	public void updateBalance(Double balanceInAccount, AccountEntity accountEntity);
	
}
