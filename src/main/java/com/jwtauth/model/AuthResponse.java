package com.jwtauth.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

	
	private String username;
	private Date dateofExpiry;
	private boolean valid;
	
	public AuthResponse(boolean valid) {
		super();
		this.valid = valid;
	}

}
