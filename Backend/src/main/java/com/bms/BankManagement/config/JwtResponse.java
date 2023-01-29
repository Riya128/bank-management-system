package com.bms.BankManagement.config;

import com.bms.BankManagement.entity.CustomerEntity;

public class JwtResponse {

	private String jwtToken;

	public JwtResponse(String jwtToken) {

		this.jwtToken = jwtToken;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
}
