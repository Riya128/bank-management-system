package com.bms.BankManagement.model;

//field that we get from user
public class Customer {

	private String customerName;
	private String password;
	private int age;
	private String aadharNumber;
	private String address;
	private String contactNo;
	private String email;
	private String gender;
	private String accountType;

	public Customer(String customerName, String password, int age, String aadharNumber, String address,
			String contactNo, String email, String gender, String accountType) {
		super();
		this.customerName = customerName;
		this.password = password;
		this.age = age;
		this.aadharNumber = aadharNumber;
		this.address = address;
		this.contactNo = contactNo;
		this.email = email;
		this.gender = gender;
		this.accountType = accountType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountType() {
		return accountType;
	}
}
