package com.bms.BankManagement.audit;

import java.net.InetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditServiceImpl implements AuditService {

	@Autowired
	AuditRepo auditRepo;

	@Override
	public void save(String serviceName, long reqTime, long resTime, long duration, int responseCode, String user,
			String ip) {
		AuditEntity auditEntity = new AuditEntity();
		auditEntity.setServiceName(serviceName);
		auditEntity.setReqTime(reqTime);
		auditEntity.setResTime(resTime);
		auditEntity.setDuration(duration);
		auditEntity.setResponseCode(responseCode);
		auditEntity.setUser(user);
		auditEntity.setIp(ip);
		this.auditRepo.save(auditEntity);

	}
}
