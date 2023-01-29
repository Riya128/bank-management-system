package com.bms.BankManagement.dao;

import org.springframework.data.repository.CrudRepository;

import com.bms.BankManagement.entity.Role;

public interface RoleDao extends CrudRepository<Role,String> {

}
