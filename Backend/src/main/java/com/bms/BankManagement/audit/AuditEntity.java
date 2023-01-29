package com.bms.BankManagement.audit;

import java.net.InetAddress;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AuditEntity {

	@Id
	@GeneratedValue
	private int id;
	private String serviceName;
	private long reqTime;
	private long resTime;
	private long duration;
	private int responseCode;
	private String user;
	private String ip;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public long getReqTime() {
		return reqTime;
	}

	public void setReqTime(long reqTime) {
		this.reqTime = reqTime;
	}

	public long getResTime() {
		return resTime;
	}

	public void setResTime(long resTime) {
		this.resTime = resTime;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public AuditEntity(int id, String serviceName, long reqTime, long resTime, long duration, int responseCode,
			String user, String ip) {
		super();
		this.id = id;
		this.serviceName = serviceName;
		this.reqTime = reqTime;
		this.resTime = resTime;
		this.duration = duration;
		this.responseCode = responseCode;
		this.user = user;
		this.ip = ip;
	}

	public AuditEntity() {

	}

}
