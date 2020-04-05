package com.log.book.shared.message;

public enum SuccessMessage {
	CREATE("Record has been created successfully");
	private String message;
	SuccessMessage(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	
}
