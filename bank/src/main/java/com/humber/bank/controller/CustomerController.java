package com.humber.bank.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.humber.bank.entity.Transaction;
import com.humber.bank.exception.ResourceNotFoundException;
import com.humber.bank.service.AccountService;
import com.humber.bank.service.CustomerService;
import com.humber.bank.service.TransactionService;

@CrossOrigin
@RestController
@RequestMapping("/api/customer")
@PreAuthorize("hasAuthority('CUSTOMER')")
public class CustomerController {

	@Autowired
	TransactionService transactionService;

	@Autowired
	AccountService accountService;
	
	@Autowired
	CustomerService customerService;

	@GetMapping("/check1")
	public String hello() {
		return "hello customer";
		
	}
	
	
	
	@GetMapping("/userDashboard")
	public void method(HttpServletResponse httpServletResponse) {
		httpServletResponse.setHeader("Location", "http://localhost:3001/MainPage");
		httpServletResponse.setStatus(302);
	}
	
	@PostMapping("/withdraw")
	public ResponseEntity<?> withdraw(@RequestBody Transaction transaction) {
		try {
			Map<String, String> withdrawMap = transactionService.withdraw(transaction);
			return !withdrawMap.isEmpty() ? new ResponseEntity<>(withdrawMap, HttpStatus.OK)
					: new ResponseEntity<String>("Insufficient Balance", HttpStatus.BAD_REQUEST);
		} catch (Exception exception) {
			throw new ResourceNotFoundException(exception.getMessage());
		}
	}
	
	@PostMapping("/fundTransfer")
	public ResponseEntity<?> fundTransfer(@RequestBody Transaction transaction) {
		try {
			Map<String, String> fundTransferMap = transactionService.fundTransfer(transaction);
			return !fundTransferMap.isEmpty() ? new ResponseEntity<>(fundTransferMap, HttpStatus.OK)
					: new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception exception) {
			throw new ResourceNotFoundException("Fund transfer failed");
		}
	}
	
	
	@PostMapping("/deposit")
	public ResponseEntity<?> deposit(@RequestBody Transaction transaction) {
		try {
			Map<String, String> depositMap = transactionService.deposit(transaction);
			return !depositMap.isEmpty() ? new ResponseEntity<>(depositMap, HttpStatus.OK)
					: new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception exception) {
			throw new ResourceNotFoundException(exception.getMessage());
		}
	}

	
	
}

