package com.bms.BankManagement.shared;

import java.util.Set;

import com.bms.BankManagement.entity.Role;

public class CustomerDto {
//shared between req and response

	private long customerId;
	private String customerName;
	private String email;
	private String password;
	private String encryptedPassword;
	private String contactNumber;
	private String gender;
	private String aadharNumber;
	private int age;
	private String contactNo;
	private String address;
	private String accountType;
	private Set<Role> role;
	
	

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String name) {
		this.customerName = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public CustomerDto(long customerId, String name, String email, String password, String encryptedPassword,
			String contactNumber, String gender, String aadharNumber, int age, String contactNo, String address,
			String accountType) {
		super();
		this.customerId = customerId;
		this.customerName = name;
		this.email = email;
		this.password = password;
		this.encryptedPassword = encryptedPassword;
		this.contactNumber = contactNumber;
		this.gender = gender;
		this.aadharNumber = aadharNumber;
		this.age = age;
		this.contactNo = contactNo;
		this.address = address;
		this.accountType = accountType;

	}

	public CustomerDto() {

	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountType() {
		return accountType;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}

}
