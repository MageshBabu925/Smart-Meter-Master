package com.invensis.smart_meters.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.invensis.smart_meters.Request.AuthRequest;
import com.invensis.smart_meters.Response.AuthResponse;
import com.invensis.smart_meters.Response.ResponseHandler;
import com.invensis.smart_meters.Response.StandardResponse;
import com.invensis.smart_meters.service.AuthService;

@RestController
public class AuthController {
	@Autowired
	private AuthService authService;

	@PostMapping("/authenticate")
	public StandardResponse login(@RequestBody AuthRequest authRequest) {

		StandardResponse standardResponse = null;
		AuthResponse authResponse = authService.login(authRequest);
		try {
			if (authResponse != null) {
				standardResponse = ResponseHandler.validResponse(authResponse);
			} else {
				standardResponse = ResponseHandler.failedResponse("No data found");
			}
		} catch (Exception e) {

			standardResponse = ResponseHandler.failedResponse("UserName/Password is invalid");
		}
		return standardResponse;

	}


}