package com.appdirect.jbilling.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErrorMessage {
	
	private String message;
	
	private HttpStatus status;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime time;

	public LocalDateTime getTime() {
		return time;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorMessage(HttpStatus badRequest,Exception ex) {
		this.status=badRequest;
		this.message = ex.getMessage();
		this.time=LocalDateTime.now();
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

}
