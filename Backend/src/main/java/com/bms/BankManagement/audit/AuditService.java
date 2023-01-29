package com.bms.BankManagement.audit;

import java.net.InetAddress;

public interface AuditService {

	

	void save(String serviceName, long reqTime, long resTime, long duration, int responseCode, String user, String ip);

}
