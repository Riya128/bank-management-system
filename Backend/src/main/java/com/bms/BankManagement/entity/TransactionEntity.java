package com.bms.BankManagement.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "t_transaction")
@SequenceGenerator(name = "tr_seq", initialValue = 19561, allocationSize = 55)

public class TransactionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tr_seq")
	private long transactionId;
	private LocalDate transactionDate;
	private String transactionType;
	private Double transactionAmount;

	@ManyToOne(cascade = CascadeType.ALL, targetEntity = AccountEntity.class)
	@JoinColumn(name = "accountNumber", referencedColumnName = "accountNumber")
	AccountEntity accountEntity;

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate localDate) {
		this.transactionDate = localDate;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public TransactionEntity() {

	}

	public TransactionEntity(long transactionId, LocalDate transactionDate, String transactionType,
			Double transactionAmount) {
		super();
		this.transactionId = transactionId;
		this.transactionDate = transactionDate;
		this.transactionType = transactionType;
		this.transactionAmount = transactionAmount;
	}

	public AccountEntity getAccountEntity() {
		return accountEntity;
	}

	public void setAccountEntity(AccountEntity accountEntity) {
		this.accountEntity = accountEntity;
	}

}
