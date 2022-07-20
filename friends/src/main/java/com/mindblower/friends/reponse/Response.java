package com.mindblower.friends.reponse;

import java.util.List;

public class Response {

	private String message;
	private boolean success;
	private int count;
	private Object object;
	private String authorization;
	


	public Response() {
		super();
	}


	public Response(String message, boolean success, int count, Object object) {
		super();
		this.message = message;
		this.success = success;
		this.count = count;
		this.object = object;
	}


	public Response(String message, boolean success) {
		super();
		this.message = message;
		this.success = success;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}

	public Object getObject() {
		return object;
	}


	public void setObject(Object object) {
		this.object = object;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public boolean isSuccess() {
		return success;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}


	public String getAuthorization() {
		return authorization;
	}


	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}
	
	
	
	
}
