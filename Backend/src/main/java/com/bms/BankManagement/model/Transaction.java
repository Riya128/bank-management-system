package com.bms.BankManagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Transaction {

	// private String transactionType;
	private Double transactionAmount;

	private Long bAccountNumber;

//	public String getTransactionType() {
//		return transactionType;
//	}
//
//	public void setTransactionType(String transactionType) {
//		this.transactionType = transactionType;
//	}

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	@JsonProperty(value = "bAccountNumber")
	public Long getBAccountNumber() {
		return bAccountNumber;
	}

	@JsonProperty(value = "bAccountNumber")
	public void setBAccountNumber(Long bAccountNumber) {
		this.bAccountNumber = bAccountNumber;
	}

	public Transaction(Double transactionAmount, Long bAccountNumber) {
		super();
		// this.transactionType = transactionType;
		this.transactionAmount = transactionAmount;
		this.bAccountNumber = bAccountNumber;
	}

	public Transaction() {

	}

}
