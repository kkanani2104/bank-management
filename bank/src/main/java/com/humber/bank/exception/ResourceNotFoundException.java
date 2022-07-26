package com.humber.bank.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

public class ResourceNotFoundException extends RuntimeException{
	
	public ResourceNotFoundException(String message) {
		super(message);
	}
}
