package com.bms.BankManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bms.BankManagement.entity.AccountEntity;
import com.bms.BankManagement.entity.CustomerEntity;

@Repository
public interface AccountDao extends JpaRepository<AccountEntity,Long> {

	AccountEntity findByCustomerEntity(CustomerEntity customerEntity);
	AccountEntity findByAccountNumber(Long accountNumber);
	
}
