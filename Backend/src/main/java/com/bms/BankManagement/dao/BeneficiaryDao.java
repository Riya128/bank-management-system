package com.bms.BankManagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bms.BankManagement.entity.BeneficiaryEntity;

@Repository
public interface BeneficiaryDao extends JpaRepository<BeneficiaryEntity, Long> {
	@Query(value = "Select*from t_beneficiary where beneficiary_id=?1", nativeQuery = true)
	public BeneficiaryEntity findByBenificiaryId(Long beneficiaryId);

	@Query(value = "select b.* from t_beneficiary as b inner join t_account as a on b.account_number= a.account_number where b.account_number = ?1", nativeQuery = true)
	 List<Object[]> findAllBeneficiaryByAccountNumber(Long accountNumber);
}
