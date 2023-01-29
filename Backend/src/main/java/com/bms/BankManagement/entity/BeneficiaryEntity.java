package com.bms.BankManagement.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "t_beneficiary")
public class BeneficiaryEntity {

	@Id
	@GeneratedValue
	private long beneficiaryId;

	private String bName;

	private Long bAccountNumber;
	private String bAccountType;
	private String bEmail;

	@ManyToOne(targetEntity = AccountEntity.class)
	@JoinColumn(name = "accountNumber", referencedColumnName = "accountNumber")
	@JsonBackReference
	AccountEntity accountEntity;

	public String getbAccountType() {
		return bAccountType;
	}

	public void setbAccountType(String bAccountType) {
		this.bAccountType = bAccountType;
	}

	public AccountEntity getAccountEntity() {
		return accountEntity;
	}

	public Long getBeneficiaryId() {
		return beneficiaryId;
	}

	public void setBeneficiaryId(Long beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}

	public void setAccountEntity(AccountEntity accountEntity) {
		this.accountEntity = accountEntity;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public long getbAccountNumber() {
		return bAccountNumber;
	}

	public void setbAccountNumber(Long bAccountNumber) {
		this.bAccountNumber = bAccountNumber;
	}

	public String getAccountType() {
		return bAccountType;
	}

	public void setAccountType(String accountType) {
		this.bAccountType = accountType;
	}

	public String getbEmail() {
		return bEmail;
	}

	public void setbEmail(String bEmail) {
		this.bEmail = bEmail;
	}

	public BeneficiaryEntity(long beneficiaryId, String bName, Long bAccountNumber, String accountType, String bEmail) {
		super();
		this.beneficiaryId = beneficiaryId;
		this.bName = bName;
		this.bAccountNumber = bAccountNumber;
		this.bAccountType = accountType;
		this.bEmail = bEmail;
	}

	public BeneficiaryEntity() {

	}
}
