package com.example.demo.Model;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class UserExceptionCustom extends Throwable{
	
	private String message;
	
	private HttpStatus status;

	public UserExceptionCustom(String message, HttpStatus status) {
		super();
		this.message = message;
		this.status = status;
	}

	@Override
	public String toString() {
		return "UserException [message=" + message + ", status=" + status + "]";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	

}
