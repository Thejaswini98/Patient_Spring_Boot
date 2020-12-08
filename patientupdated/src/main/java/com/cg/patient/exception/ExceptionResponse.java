package com.cg.patient.exception;

public class ExceptionResponse {
	String responseMessage;

	public ExceptionResponse(String responseMessage) {
		super();
		this.responseMessage = responseMessage;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	
	

}
