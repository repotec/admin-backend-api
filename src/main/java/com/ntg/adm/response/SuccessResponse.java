package com.ntg.adm.response;

public class SuccessResponse<D> {
	private String code;
	private String message;
	private D data;
	
	public SuccessResponse(D data) {
		this.code = "0000";
		this.message = "success";
		this.data = data;
	}
	
	public SuccessResponse(String code, String message, D data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public D getData() {
		return data;
	}
	public void setData(D data) {
		this.data = data;
	}
}
