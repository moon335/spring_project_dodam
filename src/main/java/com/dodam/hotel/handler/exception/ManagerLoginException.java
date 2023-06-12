package com.dodam.hotel.handler.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ManagerLoginException extends RuntimeException{
	
	private HttpStatus status;

	   public ManagerLoginException(String message, HttpStatus status) {
	      super(message);
	      this.status = status;
	   }
}
