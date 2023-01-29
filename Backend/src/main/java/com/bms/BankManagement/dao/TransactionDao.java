package com.bms.BankManagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; 

import com.bms.BankManagement.entity.AccountEntity;
import com.bms.BankManagement.entity.TransactionEntity;

public interface TransactionDao extends JpaRepository<TransactionEntity, Long> {

//@Query(name="select u.* from t_transaction u inner join u. ar where ar.account_number = :accountNumber", nativeQuery=true)
	@Query(value = "select t.* from t_transaction as t inner join t_account as a on t.account_number= a.account_number where t.account_number = ?1", nativeQuery = true)
	 List<Object[]> findAllByAccountNumber(Long accountNumber);

}
