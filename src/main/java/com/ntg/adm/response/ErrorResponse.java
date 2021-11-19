package com.ntg.adm.response;

import java.time.LocalDateTime;
import java.util.List;


public class ErrorResponse {
	private int status;
	private String code;
	private String message;
	private LocalDateTime timestamp;
	private String url;
	private List<String> errors;
	
	public ErrorResponse() {
		timestamp = LocalDateTime.now();
	}
	
	public ErrorResponse(int status, String message, String url, List<String> errors) {
		this();
		this.status = status;
		this.message = message;
		this.url = url;
		this.errors = errors;
	}
	
	public ErrorResponse(int status, String code, String message, String url, List<String> errors) {
		this();
		this.status = status;
		this.code = code;
		this.message = message;
		this.url = url;
		this.errors = errors;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
}
