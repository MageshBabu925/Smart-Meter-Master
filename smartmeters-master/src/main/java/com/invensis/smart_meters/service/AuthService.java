package com.invensis.smart_meters.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invensis.smart_meters.Request.AuthRequest;
import com.invensis.smart_meters.Response.AuthResponse;
import com.invensis.smart_meters.Security.JwtTokenService;
import com.invensis.smart_meters.Util.Constants;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthService {

	@Autowired
	private JwtTokenService jwtTokenService;

	public AuthResponse login(AuthRequest authRequest) {

		String username = authRequest.getUsername();
		String password = authRequest.getPassword();
		log.info("login Request - "+username+" - "+password );
		// if (authDao.authenticate(username, password)) {
		if (username != null && username.equals("spdcl") && password.equals("spdc@32!1!#")) {
			// Authentication succeeded - generate token
			final Map<String, Object> claims = new HashMap<>();
			claims.put(Constants.USERNAME, username);
			String token = jwtTokenService.generateToken(claims, username);
			AuthResponse authResponse = new AuthResponse();
			authResponse.setToken(token);
			return authResponse;
		} else {
			// Authentication failed
			return null;
		}
	}
}
