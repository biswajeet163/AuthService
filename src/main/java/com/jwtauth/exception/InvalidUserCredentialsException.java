package com.jwtauth.exception;

public class InvalidUserCredentialsException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1831771983458315291L;

	public InvalidUserCredentialsException(String msg) {
		super(msg);
	}

}
