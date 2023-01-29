package com.bms.BankManagement.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bms.BankManagement.entity.CustomerEntity;

public interface CustomerDao extends JpaRepository<CustomerEntity,Long> {

	CustomerEntity findByCustomerId(Long id);
	
	@Query(value="Select* from t_customer where status=?1",nativeQuery=true)
	Iterable<CustomerEntity> findAllCustomers(String status);

	@Query(value="Select status from t_customer where customer_id=?1",nativeQuery=true)
	String findStatusByCustomerId(Long id);
	
//	@Query(value="SELECT* from t_customer t where t.status=?1",nativeQuery=true)
//	Iterable<CustomerEntity> findPendingRequest(String status);

}
