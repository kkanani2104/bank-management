package com.humber.bank.service;

import java.util.List;
import java.util.Map;

import com.humber.bank.entity.Transaction;

public interface TransactionService {

    Map<String, String> fundTransfer(Transaction transaction);
	
    Map<String, String> deposit(Transaction transaction);

    Map<String, String> withdraw(Transaction transaction);
    
    List<Transaction> miniStatement(long accNo);
    
    List<Transaction> customizeStatement(Transaction transaction,long accNo);

}
