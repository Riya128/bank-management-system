package com.bms.BankManagement.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "t_account")
@SequenceGenerator(name = "acc_seq", initialValue = 9223372, allocationSize = 20)
public class AccountEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acc_seq")

	private Long accountNumber;
	private Double balance = 0.0;
	private String accountType;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
	private LocalDate accountOpenDate;

	@OneToMany
	// ( mappedBy = "t_account",cascade = { CascadeType.PERSIST, CascadeType.MERGE,
	// CascadeType.DETACH,
	// CascadeType.REFRESH })
	@JsonBackReference
	List<TransactionEntity> transactions;

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "t_account")
//    private List<BeneficiaryEntity>beneficiary;

	@OneToOne(targetEntity = CustomerEntity.class)
	@JoinColumn(name = "customerId", referencedColumnName = "customerId")
	@JsonBackReference
	CustomerEntity customerEntity;

	public Long getAccountNumber() {
		return accountNumber;
	}

	public List<TransactionEntity> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TransactionEntity> transactions) {
		this.transactions = transactions;
	}

	public CustomerEntity getCustomerEntity() {
		return customerEntity;
	}

	public void setCustomerEntity(CustomerEntity customerEntity) {
		this.customerEntity = customerEntity;
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

	public AccountEntity(Long accountNumber, Double balance, String accountType, LocalDate accountOpenDate,
			List<TransactionEntity> transactions) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.accountType = accountType;
		this.accountOpenDate = accountOpenDate;
		this.transactions = transactions;
	}

	public AccountEntity() {

	}
}
