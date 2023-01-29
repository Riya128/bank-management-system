package com.bms.BankManagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Beneficiary {

	private String bName;
	
	private Long bAccountNumber;
	
	private String bAccountType;
	
	private String bEmail;
	
	@JsonProperty(value="bName")
	public String getbName() {
		return bName;
	}
	@JsonProperty(value="bName")
	public void setbName(String bName) {
		this.bName = bName;
	}
	@JsonProperty(value="bAccountNumber")
	public Long getbAccountNumber() {
		return bAccountNumber;
	}
	@JsonProperty(value="bAccountNumber")
	public void setbAccountNumber(Long bAccountNumber) {
		this.bAccountNumber = bAccountNumber;
	}
	@JsonProperty(value="bAccountType")
	public String getbAccountType() {
		return bAccountType;
	}
	@JsonProperty(value="bAccountType")
	public void setbAccountType(String bAccountType) {
		this.bAccountType = bAccountType;
	}
	@JsonProperty(value="bEmail")
	public String getbEmail() {
		return bEmail;
	}
	@JsonProperty(value="bEmail")
	public void setbEmail(String bEmail) {
		this.bEmail = bEmail;
	}
	public Beneficiary(String bName, Long bAccountNumber, String bAccountType, String bEmail) {
		super();
		this.bName = bName;
		this.bAccountNumber = bAccountNumber;
		this.bAccountType = bAccountType;
		this.bEmail = bEmail;
	}
	public Beneficiary() {
		
	}
	
}
