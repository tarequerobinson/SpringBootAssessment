package com.cinema.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinema.config.AuthenticationRequest;
import com.cinema.config.AuthenticationResponse;
import com.cinema.config.AuthenticationService;
import com.cinema.config.RegisterRequest;

@RestController
@RequestMapping("/api/v1/auth")

public class AuthenticationController {
	
	private final AuthenticationService authenticationService ;
	
	AuthenticationController (AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {
		AuthenticationResponse response = authenticationService.register(registerRequest);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
		AuthenticationResponse response = authenticationService.authenticate(authenticationRequest);
		return ResponseEntity.ok(response);
	}
		
		
	
	
	
	
	

}
