package com.dodam.hotel.handler.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class CustomRestFullException extends RuntimeException{
	
	private HttpStatus status;
	
	public CustomRestFullException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}
}
