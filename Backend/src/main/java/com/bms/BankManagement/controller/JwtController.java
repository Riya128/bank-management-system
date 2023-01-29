package com.bms.BankManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bms.BankManagement.config.JwtResponse;
import com.bms.BankManagement.model.LoginModel;
import com.bms.BankManagement.services.JwtService;

@RestController
@CrossOrigin
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @PostMapping({"/login"})
    public JwtResponse createJwtToken(@RequestBody LoginModel jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }
}