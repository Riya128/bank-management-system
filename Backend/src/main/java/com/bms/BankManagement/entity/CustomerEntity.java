package com.bms.BankManagement.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "t_customer")
@SequenceGenerator(name = "id_seq", initialValue = 1789000, allocationSize = 20)
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
	private long customerId;
	@Column(nullable = false)
	private String customerName;
	@Column(nullable = false)
	private String email;
	private String encryptedPassword;
	@Column(nullable = false)
	private int age;
	@Column(nullable = false)
	private String gender;
	@Column(nullable = false)
	private String contactNo;
	@Column(nullable = false, unique = true)
	private String aadharNumber;
	private String status = "pending";

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "customer_id") }, inverseJoinColumns = {
			@JoinColumn(name = "roleName") })
	private Set<Role> role;

	@OneToOne(cascade = CascadeType.ALL, targetEntity = AccountEntity.class, mappedBy = "customerEntity")
	private AccountEntity account;

	public void setRole(Set<Role> role) {
		this.role = role;
	}

	public Set<Role> getRole() {
		return role;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public AccountEntity getAccount() {
		return account;
	}

	private String accountType;

	public void setAccount(AccountEntity account) {
		this.account = account;
	}

	public CustomerEntity(long customerId, String customerName, String email, String encryptedPassword, int age,
			String gender, String contactNo, String address, String aadharNumber) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.email = email;
		this.encryptedPassword = encryptedPassword;
		this.age = age;
		this.gender = gender;
		this.contactNo = contactNo;
		this.address = address;
		this.aadharNumber = aadharNumber;
	}

	public String getContactNo() {
		return contactNo;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public CustomerEntity() {

	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	private String address;

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
