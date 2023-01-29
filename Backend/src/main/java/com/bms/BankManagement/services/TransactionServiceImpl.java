package com.bms.BankManagement.services;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bms.BankManagement.audit.AuditService;
import com.bms.BankManagement.dao.TransactionDao;
import com.bms.BankManagement.entity.AccountEntity;
import com.bms.BankManagement.entity.TransactionEntity;
import com.bms.BankManagement.model.Transaction;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionDao transactionDao;

	@Autowired
	AuditService auditService;

	@Override
	public TransactionEntity createTransaction(Transaction transactionResModel, AccountEntity accountEntity,
			String transactionType, HttpServletRequest request) {
		long startTime = System.currentTimeMillis();
		TransactionEntity transactionEntity = new TransactionEntity();
		transactionEntity.setAccountEntity(accountEntity);
		transactionEntity.setTransactionAmount(transactionResModel.getTransactionAmount());
		transactionEntity.setTransactionType(transactionType);
		transactionEntity.setTransactionDate(LocalDate.now());
		this.transactionDao.save(transactionEntity);
		long endTime = System.currentTimeMillis();
		long duration = endTime - startTime;
		this.auditService.save("CREATE TRANSACTION", startTime, endTime, duration, 200,
				Long.toString(accountEntity.getAccountNumber()), request.getRemoteAddr());
		return transactionEntity;

	}

	@Override
	public List<Object[]> getTransactions(Long accountNumber, HttpServletRequest request) {
		long startTime = System.currentTimeMillis();

		List<Object[]> transactions = this.transactionDao.findAllByAccountNumber(accountNumber);
		long endTime = System.currentTimeMillis();
		long duration = System.currentTimeMillis();

		this.auditService.save("VIEW TRANSACTION HISTORY", startTime, endTime, duration, 200,
				Long.toString(accountNumber), request.getRemoteAddr());
		return transactions;
	}

}
