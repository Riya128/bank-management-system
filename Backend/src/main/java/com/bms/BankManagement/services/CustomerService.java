package com.bms.BankManagement.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import com.bms.BankManagement.entity.CustomerEntity;
import com.bms.BankManagement.model.ResponseModel;
import com.bms.BankManagement.shared.CustomerDto;

public interface CustomerService extends UserDetailsService{

	List<ResponseModel>getAllCustomers(String status,HttpServletRequest request);
	CustomerDto createCustomer(CustomerDto customer,HttpServletRequest request);

	CustomerEntity getCustomerDetailsByCustomerId(Long id);
	CustomerDto updateUser(Long id, String status,HttpServletRequest request);

	
	String findStatus(long id,HttpServletRequest request);
	void initRoleAndUser();

	//List<ResponseModel> getPendingRequest(String status);
}
