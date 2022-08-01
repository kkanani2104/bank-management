package com.humber.bank.service;

import java.util.Map;

import com.humber.bank.entity.Account;
import com.humber.bank.entity.CurrentAccount;

public interface AccountService {

	Map<String, Account> addAccount(Account account);
	Map<String, Account> saveAccount(CurrentAccount account);
	Map<String, Account> updateAccount(Account account);
	void deleteAccount(long accountId);
	Account findAccountById(long accNo);
	double getBalance(long accNo);
	public double getBalance(long accNo,long managerId);
	
	
}
