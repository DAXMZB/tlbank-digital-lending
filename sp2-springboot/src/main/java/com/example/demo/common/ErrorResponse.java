package com.example.demo.common;

import java.time.LocalDateTime;

public class ErrorResponse {
	private boolean success;
	private String code;
	private String message;
	private LocalDateTime timestamp;

	public ErrorResponse() {

	}

	public ErrorResponse(boolean success, String code, String message, LocalDateTime timestamp) {
		this.success = success;
		this.code = code;
		this.message = message;
		this.timestamp = timestamp;
	}

	public static ErrorResponse of(String code, String message) {
		return new ErrorResponse(false, code, message, LocalDateTime.now());
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
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

}
