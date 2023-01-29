package com.bms.BankManagement.model;

public class ResponseModel {
	// all the fields that will go as response

	private long customerId;
	private String customerName;
	private String email;
	private int age;
	private String contactNo;
	private String address;
	private String gender;
	private String aadharNumber;
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	
	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public ResponseModel(long customerId, String customerName, String email, int age, String contactNo, String address,
			String gender,String aadharNumber) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.email = email;
		this.age = age;
		this.contactNo = contactNo;
		this.address = address;
		this.gender = gender;
		this.aadharNumber=aadharNumber;
	}

	public ResponseModel() {

	}

}
