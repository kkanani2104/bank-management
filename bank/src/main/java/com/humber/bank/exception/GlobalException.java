package com.humber.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.humber.bank.entity.ErrorMessage;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler
	public ResponseEntity<ErrorMessage> handleResourceNotFoundException(ResourceNotFoundException ex,
			WebRequest request) {
		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND.value(), System.currentTimeMillis(),
				ex.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorMessage> handleNoDataFoundException(NoDataFoundException ex, WebRequest request) {
		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NO_CONTENT.value(), System.currentTimeMillis(),
				ex.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NO_CONTENT);
	}
}
