package com.bms.BankManagement.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AccountResponseModel {
	
	private Long accountNumber;
	private Double balance;
	private String accountType;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
	private LocalDate accountOpenDate;

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public LocalDate getAccountOpenDate() {
		return accountOpenDate;
	}

	public void setAccountOpenDate(LocalDate accountOpenDate) {
		this.accountOpenDate = accountOpenDate;
	}

	public AccountResponseModel(Long accountNumber, Double balance, String accountType, LocalDate accountOpenDate) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.accountType = accountType;
		this.accountOpenDate = accountOpenDate;
	}

	public AccountResponseModel() {
		super();
		// TODO Auto-generated constructor stub
	} 
	
	
	

}
