package com.assignment.horsetracking.model;

public class ValidationException extends Exception{

	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String errorMsg;

	public ValidationException() {
		super();
	}
	
	public ValidationException(String errorCode, String errorMsg) {
		this();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
