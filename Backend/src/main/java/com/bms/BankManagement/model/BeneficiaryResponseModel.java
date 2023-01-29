package com.bms.BankManagement.model;

public class BeneficiaryResponseModel {

	private Long bAccountNumber;
	private String bAccountType;
	private String bName;
	private String bEmail;

	
	
	public Long getbAccountNumber() {
		return bAccountNumber;
	}

	public void setbAccountNumber(Long bAccountNumber) {
		this.bAccountNumber = bAccountNumber;
	}

	public String getbAccountType() {
		return bAccountType;
	}

	public void setbAccountType(String bAccountType) {
		this.bAccountType = bAccountType;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public String getbEmail() {
		return bEmail;
	}

	public void setbEmail(String bEmail) {
		this.bEmail = bEmail;
	}

	
	public BeneficiaryResponseModel(Long bAccountNumber, String bAccountType, String bName, String bEmail) {
		super();
		this.bAccountNumber = bAccountNumber;
		this.bAccountType = bAccountType;
		this.bName = bName;
		this.bEmail = bEmail;
	}

	public BeneficiaryResponseModel() {

	}

}
