package com.bms.BankManagement.audit;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditRepo extends JpaRepository<AuditEntity, Integer> {

}
