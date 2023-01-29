package com.bms.BankManagement.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bms.BankManagement.dao.BeneficiaryDao;
import com.bms.BankManagement.entity.AccountEntity;
import com.bms.BankManagement.entity.BeneficiaryEntity;
import com.bms.BankManagement.model.Beneficiary;
import com.bms.BankManagement.model.BeneficiaryResponseModel;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {

	@Autowired
	BeneficiaryDao beneficiaryDao;

	@Override
	public BeneficiaryEntity createBeneficiary(AccountEntity accountEntity, Beneficiary beneficiary) {
//		BeneficiaryEntity beneficiaryEntity = new BeneficiaryEntity();
//		
//		beneficiaryEntity.setAccountEntity(accountEntity);
//		
//		beneficiaryDao.save(beneficiaryEntity);
//		return beneficiaryEntity;

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		BeneficiaryEntity beneficiaryEntity = modelMapper.map(beneficiary, BeneficiaryEntity.class);
		beneficiaryEntity.setAccountEntity(accountEntity);
		this.beneficiaryDao.save(beneficiaryEntity);

		return beneficiaryEntity;

	}

	@Override
	public BeneficiaryEntity getBeneficiaryByBeneficiaryId(Long bId) {
		BeneficiaryEntity beneficiaryEntity = beneficiaryDao.findByBenificiaryId(bId);
		if (beneficiaryEntity == null) {
			throw new UsernameNotFoundException("Beneficiary with ID: " + bId + " not found");
		}

		return beneficiaryEntity;

	}

	@Override
	public BeneficiaryEntity updateBeneficiary(Long bId, BeneficiaryEntity benEntity) {
		BeneficiaryEntity beneficiaryEntity = this.beneficiaryDao.findByBenificiaryId(bId);

		if (beneficiaryEntity == null) {

			throw new UsernameNotFoundException("Beneficiary with ID: " + bId + " not found");
		}

		beneficiaryEntity.setbEmail(benEntity.getbEmail());
		beneficiaryEntity.setbName(benEntity.getbName());

		BeneficiaryEntity updatedDetails = this.beneficiaryDao.save(beneficiaryEntity);
		return updatedDetails;
	}

	@Override
	public ResponseEntity<HttpStatus> deleteBeneficiary(String beneficiaryId) {
		BeneficiaryEntity beneficiaryEntity = beneficiaryDao.findByBenificiaryId(Long.parseLong(beneficiaryId));
		if (beneficiaryEntity == null) {
			// throw some exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		beneficiaryDao.delete(beneficiaryEntity);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public List<Object[]> getAllBeneficiary(Long accountNumber) {
		
		 List<Object[]> returnValue=this.beneficiaryDao.findAllBeneficiaryByAccountNumber(accountNumber);
		return returnValue;
	}

}
