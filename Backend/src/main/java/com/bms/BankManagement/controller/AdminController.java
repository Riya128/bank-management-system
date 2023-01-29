package com.bms.BankManagement.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bms.BankManagement.entity.AccountEntity;
import com.bms.BankManagement.entity.CustomerEntity;
import com.bms.BankManagement.model.ResponseModel;
import com.bms.BankManagement.services.AccountService;
import com.bms.BankManagement.services.CustomerService;

@RestController
@RequestMapping("/bank/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

	@Autowired
	AccountService accountService;
	@Autowired
	private CustomerService customerService;

	// view all the customers on basis of status
	@GetMapping("/getcustomers")
    @PreAuthorize("hasRole('Admin')")

	public ResponseEntity<List<ResponseModel>> getCustomers(String status,HttpServletRequest request) {
		List<ResponseModel> returnValue = customerService.getAllCustomers(status,request);
		if(returnValue.isEmpty()) {
			return ResponseEntity.status(400).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(returnValue);
	}

	// approve/reject request
	@PutMapping("/request/{id}")
    @PreAuthorize("hasRole('Admin')")
	public ResponseEntity<?> processRequest(@PathVariable String id, String request,HttpServletRequest req) {
		CustomerEntity customerEntity = customerService.getCustomerDetailsByCustomerId(Long.parseLong(id));
		customerService.updateUser(Long.parseLong(id), request,req);

		if (request.equalsIgnoreCase("approved")) {
			// create the account
			AccountEntity accountEntity = accountService.createAccount(customerEntity);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	

}
