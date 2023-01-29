package com.bms.BankManagement.audit;

import java.net.InetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuditController {

	@Autowired
	AuditService auditService;
	
	@PostMapping("/audit")
	public void save(String serviceName,long reqTime,long resTime,long duration,int responseCode,String user,String ip) {
		this.auditService.save(serviceName,reqTime,resTime,duration,responseCode,user,ip);
	}
	
}
