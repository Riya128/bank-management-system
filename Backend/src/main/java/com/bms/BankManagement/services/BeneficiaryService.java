package com.bms.BankManagement.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bms.BankManagement.entity.AccountEntity;
import com.bms.BankManagement.entity.BeneficiaryEntity;
import com.bms.BankManagement.model.Beneficiary;
import com.bms.BankManagement.model.BeneficiaryResponseModel;

public interface BeneficiaryService {

	BeneficiaryEntity createBeneficiary(AccountEntity accountEntity,Beneficiary beneficiary);
	BeneficiaryEntity getBeneficiaryByBeneficiaryId(Long bId);
	BeneficiaryEntity updateBeneficiary(Long bId, BeneficiaryEntity benEntity);
	ResponseEntity<HttpStatus> deleteBeneficiary(String beneficiaryId);
	List<Object[]> getAllBeneficiary(Long accountNumber);

}
