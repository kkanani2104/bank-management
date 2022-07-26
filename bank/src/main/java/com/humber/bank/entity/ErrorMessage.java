package com.humber.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

	 private int statusCode;
	 private long timestamp;
	 private String message;
	 
	  
}
