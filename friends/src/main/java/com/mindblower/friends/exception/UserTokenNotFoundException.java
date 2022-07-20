package com.mindblower.friends.exception;

public class UserTokenNotFoundException extends RuntimeException {

	private String resourceName;
	private String fieldName;
	private String authString;
	
	
	public UserTokenNotFoundException(String resourceName, String fieldName, String authString) {
		super(String.format("%s not found with %s : %s", resourceName,fieldName,authString));
		this.resourceName = resourceName;
		this.authString = authString;
		this.fieldName = fieldName;
	}
	
	
	
}
