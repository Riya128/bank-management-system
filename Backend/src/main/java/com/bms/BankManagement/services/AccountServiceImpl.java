package com.bms.BankManagement.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bms.BankManagement.dao.AccountDao;
import com.bms.BankManagement.entity.AccountEntity;
import com.bms.BankManagement.entity.CustomerEntity;
import com.bms.BankManagement.entity.TransactionEntity;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;

	@Override
//	@Transactional
	public AccountEntity createAccount(CustomerEntity customer) {
		AccountEntity accEntity = new AccountEntity();
		accEntity.setAccountType(customer.getAccountType());
		accEntity.setCustomerEntity(customer);
		accEntity.setAccountOpenDate(LocalDate.now());
		accountDao.save(accEntity);
		return accEntity;
	}

	@Override
	public AccountEntity findByCustomerEntity(CustomerEntity customerEntity) {

		AccountEntity accountDetails = this.accountDao.findByCustomerEntity(customerEntity);
		return accountDetails;

	}

	@Override
//	@Transactional
	public AccountEntity findByAccountNumber(Long accountNumber) {
		AccountEntity ifAccountExistsEntity = this.accountDao.findByAccountNumber(accountNumber);
		return ifAccountExistsEntity;
	}

	@Override
//	@Transactional
	public void updateBalance(Double balanceInAccount, AccountEntity accountEntity) {
		Double initialBalance = accountEntity.getBalance();
		accountEntity.setBalance(balanceInAccount);
		this.accountDao.save(accountEntity);

	}

}
