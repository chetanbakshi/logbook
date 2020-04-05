package com.log.book.ui.model.response;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class SuccessResponseModel {
	private Date timestamp = new Date();
	private int status;
	private HttpStatus httpStatus;
	private String message;	
	public SuccessResponseModel(String message, int status, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.status = status;
		this.httpStatus = httpStatus;		
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public int getStatus() {
		return status;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public String getMessage() {
		return message;
	}
	
	

}
