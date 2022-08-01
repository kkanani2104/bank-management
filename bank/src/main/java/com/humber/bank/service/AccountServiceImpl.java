package com.humber.bank.service;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humber.bank.entity.Account;
import com.humber.bank.entity.AccountType;
import com.humber.bank.entity.CurrentAccount;
import com.humber.bank.exception.ResourceNotFoundException;
import com.humber.bank.repository.AccountDao;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	AccountDao accountDao;

	@Override
	@Transactional
	public Map<String, Account> addAccount(Account account) {
		Map<String, Account> map = new HashMap<String, Account>();
		account.setAccountType(AccountType.SAVING);
		accountDao.save(account);
		map.put("Account saved successfully", account);
		return map;
	}
	
	@Override
	@Transactional
	public Map<String, Account> saveAccount(CurrentAccount account) {
		Map<String, Account> map = new HashMap<String, Account>();
		account.setAccountType(AccountType.CURRENT);
		accountDao.save(account);
		map.put("Current account saved successfully", account);
		return map;
	}

	@Override
	@Transactional
	public Map<String, Account> updateAccount(Account account) {
		Map<String, Account> map = new HashMap<String, Account>();
		accountDao.findById(account.getAccNumber())
				.orElseThrow(() -> new ResourceNotFoundException("Account with account number "+ account.getAccNumber() + " does not exist"));
		accountDao.save(account);
		map.put("success", account);
		return map;
	}

	@Override
	@Transactional
	public void deleteAccount(long accountId) {
		accountDao.deleteById(accountId);
	}

	@Override
	public Account findAccountById(long accNo) {
		Account account = accountDao.findById(accNo).orElseThrow(()-> new ResourceNotFoundException("Account with account number "+ accNo + " does not exist"));
            return account;
	}

	
	@Override
	public double getBalance(long accNo) {
		Account account = accountDao.findById(accNo).orElseThrow(()-> new ResourceNotFoundException("Account with account number "+ accNo + "does not exist"));
		return account.getBalance();
	}
	
	@Override
	public double getBalance(long accNo,long managerId) {
		Account account = accountDao.findById(accNo).orElseThrow(()-> new ResourceNotFoundException("Account with account number "+ accNo + " does not exist"));
		long mId = account.getCustomer().getManager().getPId();
		if(account !=null && managerId == mId) {
			return account.getBalance();
		}
		return 0;
	}
}
