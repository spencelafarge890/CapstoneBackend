package com.meritamerica.capstonebackend.models;

public class AuthenticationResponse {
	private final String username;
	private final String role;
	private final String jwt;
	
	public AuthenticationResponse(String username, String role, String jwt) {
		this.username = username;
		this.jwt = jwt;		
		this.role = role;
	}
	
	public String getUsername() {
		return this.username;
	}

	public String getRole() {
		return this.role;
	}

	public String getJwt() {
		return this.jwt;
	}	
}
