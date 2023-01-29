package com.bms.BankManagement.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bms.BankManagement.audit.AuditService;
import com.bms.BankManagement.dao.CustomerDao;
import com.bms.BankManagement.dao.RoleDao;
import com.bms.BankManagement.entity.CustomerEntity;
import com.bms.BankManagement.entity.Role;
import com.bms.BankManagement.model.ResponseModel;
import com.bms.BankManagement.shared.CustomerDto;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;
	@Autowired
	RoleDao roleDao;

	@Autowired
	private AuditService auditService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	// getting all the customers with account
	@Override
	public List<ResponseModel> getAllCustomers(String status, HttpServletRequest request) {

		List<ResponseModel> returnValue = new ArrayList<>();
		long startTime = System.currentTimeMillis();
		Iterable<CustomerEntity> customers = this.customerDao.findAllCustomers(status);
		long endTime = System.currentTimeMillis();
		long duration = endTime - startTime;
		for (CustomerEntity c : customers) {
			ResponseModel resModel = new ResponseModel();
			BeanUtils.copyProperties(c, resModel);
			returnValue.add(resModel);
		}
		int statusCode = 200;

		this.auditService.save("GET CUSTOMERS", startTime, endTime, duration, statusCode, "ADMIN",
				request.getRemoteAddr());
		return returnValue;

	}

	String line = "";

	// registering a customer
	@Override
	public CustomerDto createCustomer(CustomerDto customerDto, HttpServletRequest request) {

		customerDto.setEncryptedPassword(bCryptPasswordEncoder.encode(customerDto.getPassword()));
		 Role role = roleDao.findById("User").get();
	        Set<Role> userRoles = new HashSet<>();
	        userRoles.add(role);
	        customerDto.setRole(userRoles);
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		CustomerEntity customerEntity = modelMapper.map(customerDto, CustomerEntity.class);
		long startTime = System.currentTimeMillis();
		this.customerDao.save(customerEntity);
		long endTime = System.currentTimeMillis();
		long duration = endTime - startTime;

		CustomerDto cDto = modelMapper.map(customerEntity, CustomerDto.class);
		this.auditService.save("CREATE ACCOUNT", startTime, endTime, duration, 200, "ALL", request.getRemoteAddr());
		return cDto;

	}

	// update the status of a customer
	@Override
	public CustomerEntity getCustomerDetailsByCustomerId(Long id) {

		CustomerEntity customerEntity = this.customerDao.findByCustomerId(id);

		return customerEntity;

		// return new ModelMapper().map(customerEntity, CustomerDto.class);

	}

	@Override
	public CustomerDto updateUser(Long id, String status, HttpServletRequest request) {
		long startTime = System.currentTimeMillis();
		CustomerEntity customerEntity = this.customerDao.findByCustomerId(id);

		customerEntity.setStatus(status);
		CustomerEntity updatedUserDetails = customerDao.save(customerEntity);
		long endTime = System.currentTimeMillis();
		long duration = endTime - startTime;
		int statusCode = 0;
		if (updatedUserDetails == null)
			statusCode = 404;
		else
			statusCode = 200;
		this.auditService.save("UPDATE USER", startTime, endTime, duration, statusCode, Long.toString(id),
				request.getRemoteAddr());
		return new ModelMapper().map(updatedUserDetails, CustomerDto.class);

	}

	@Override
	public String findStatus(long id, HttpServletRequest request) {
		long startTime = System.currentTimeMillis();
		String status = this.customerDao.findStatusByCustomerId(id);
		long endTime = System.currentTimeMillis();
		long duration = endTime - startTime;
		this.auditService.save("VIEW ACCOUNT STATUS", startTime, endTime, duration, 200, Long.toString(id),
				request.getRemoteAddr());
		return status;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		

		CustomerEntity customerEntity = customerDao.findByCustomerId(Long.parseLong(username));
		if (customerEntity == null) {
			throw new UsernameNotFoundException("No user found");
		}
		String cId = Long.toString(customerEntity.getCustomerId());
		return new User(cId, customerEntity.getEncryptedPassword(), new ArrayList<>());
	}

//	@Override
//	public List<ResponseModel> getPendingRequest(String status) {
//		
//List<ResponseModel> returnValue = new ArrayList<>();
//		
//		Iterable<CustomerEntity> customers = this.customerDao.findPendingRequest(status);
//		for (CustomerEntity c : customers) {
//			ResponseModel resModel = new ResponseModel();
//			BeanUtils.copyProperties(c, resModel);
//			returnValue.add(resModel);
//		}
//		return returnValue;
//
//		
//	}

	
	 public void initRoleAndUser() {

	        Role adminRole = new Role();
	        adminRole.setRoleName("Admin");
	        adminRole.setRoleDescription("Admin role");
	        roleDao.save(adminRole);

	        Role userRole = new Role();
	        userRole.setRoleName("User");
	        userRole.setRoleDescription("Default role for newly created record");
	        roleDao.save(userRole);

	        CustomerEntity adminUser = new CustomerEntity();
	        adminUser.setCustomerName("Admin");
	        adminUser.setAadharNumber("878987657890"); 
	        adminUser.setStatus("");
	         
	        adminUser.setAge(22);
	        adminUser.setAddress("Sec 101 London"); 
	        adminUser.setContactNo("9908978900");
	        adminUser.setEmail("admin@gmail.com");
	        adminUser.setEncryptedPassword(bCryptPasswordEncoder.encode("password"));
	        adminUser.setGender("male");
	        

	        
//	        Set<Role> adminRoles = new HashSet<>();
//	        adminRoles.add(adminRole);
//	        adminUser.setRole(adminRoles);
	         
	       
	        

	        customerDao.save(adminUser);
	
	 }
}
