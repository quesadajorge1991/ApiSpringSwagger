package com.example.ExampleCrudApiRest.security.payload.response;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private int id;
	private String username;
	private Collection<? extends GrantedAuthority> authorities;



	public JwtResponse(String token, String type, int id, String username,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.token = token;
		this.type = type;
		this.id = id;
		this.username = username;
		this.authorities = authorities;
	}
	
	

	public JwtResponse(String token, String username, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.token = token;
		this.username = username;
		this.authorities = authorities;
	}



	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
}
