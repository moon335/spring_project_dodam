package com.dodam.hotel.handler.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ManagerCustomRestFullException extends RuntimeException{
	
private HttpStatus status;
	
	public ManagerCustomRestFullException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}
}
