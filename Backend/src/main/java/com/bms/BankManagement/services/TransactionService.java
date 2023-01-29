package com.bms.BankManagement.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bms.BankManagement.entity.AccountEntity;
import com.bms.BankManagement.entity.TransactionEntity;
import com.bms.BankManagement.model.Transaction;

public interface TransactionService {

	TransactionEntity createTransaction(Transaction transactionResModel, AccountEntity accountEntity,
			String transactionType,HttpServletRequest request);

	 List<Object[]> getTransactions(Long accountNumber,HttpServletRequest request);

}
