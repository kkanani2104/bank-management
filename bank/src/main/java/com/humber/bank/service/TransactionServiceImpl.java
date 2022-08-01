package com.humber.bank.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humber.bank.entity.Account;
import com.humber.bank.entity.Transaction;
import com.humber.bank.entity.TransactionType;
import com.humber.bank.exception.ResourceNotFoundException;
import com.humber.bank.repository.AccountDao;
import com.humber.bank.repository.TransactionDao;

@Service
public class TransactionServiceImpl  implements TransactionService{


	@Autowired
	AccountDao accountDao;
	
	

	@Autowired
	TransactionDao transactionDao;

	@Override
	@Transactional
	public Map<String, String> fundTransfer(Transaction transaction) {
		Map<String, String> map = new HashMap<String, String>();
		long fromAccountNo = transaction.getFromAccount().getAccNumber();
		long toAccountNo = transaction.getToAccount().getAccNumber();
		Account fromAcc = accountDao.findById(fromAccountNo)
				.orElseThrow(() -> new ResourceNotFoundException("Account with account number "+ fromAccountNo + " does not exist"));
		Account toAcc = accountDao.findById(toAccountNo)
				.orElseThrow(() -> new ResourceNotFoundException("Account with account number "+ toAccountNo + " does not exist"));

		if (fromAcc != null && toAcc != null && fromAccountNo != toAccountNo) {
			if (fromAcc.getBalance() > transaction.getTransactionAmount()) {
				transaction.setTransactionType(TransactionType.TRANSFER);
				transactionDao.save(transaction);
				toAcc.setBalance(toAcc.getBalance() + transaction.getTransactionAmount());
				fromAcc.setBalance(fromAcc.getBalance() - transaction.getTransactionAmount());
				accountDao.save(fromAcc);
				accountDao.save(toAcc);
				map.put("success", "Fund Transfered Successfully");
				Map<String, Object> fundTransferInfo=new HashMap<String, Object>();
				fundTransferInfo.put("FROM_ACC", transaction.getFromAccount().getAccNumber());
				fundTransferInfo.put("TRANSFER_DATE",transaction.getTransactionDate());
				fundTransferInfo.put("TO_ACC", transaction.getToAccount().getAccNumber());
				fundTransferInfo.put("TRANSFER_AMOUNT", transaction.getTransactionAmount());
				fundTransferInfo.put("BALANCE", fromAcc.getBalance());
				
				//sender.sendFundTransferEmail(fundTransferInfo);
			}
		}
		return map;
	}

	@Override
	@Transactional
	public Map<String, String> deposit(Transaction transaction) {
		Map<String, String> map = new HashMap<String, String>();
		Account account = accountDao.findById(transaction.getToAccount().getAccNumber())
				.orElseThrow(() -> new ResourceNotFoundException("Account with account number "+ transaction.getToAccount().getAccNumber() + " does not exist"));
		Account toAccount = transaction.getToAccount();
		transaction.setTransactionType(TransactionType.DEPOSIT);
		transaction.setToAccount(toAccount);
		transactionDao.save(transaction);
		double transactionAmount = transaction.getTransactionAmount();
		double balance = account.getBalance();
		account.setBalance(balance + transactionAmount);
		accountDao.save(account);
		map.put("success", "Deposit sucessfully");
		Map<String, Object> depositInfo=new HashMap<String, Object>();
		depositInfo.put("TO_ACC", transaction.getToAccount().getAccNumber());
		depositInfo.put("TRANSFER_AMOUNT", transaction.getTransactionAmount());
		//sender.sendDepositEmail(depositInfo);
		return map;
	}

	@Override
	@Transactional
	public Map<String, String> withdraw(Transaction transaction) {
		Map<String, String> map = new HashMap<String, String>();
		double transactionAmount = transaction.getTransactionAmount();
		Account fromAccount = transaction.getFromAccount();
		Account account = accountDao.findById(fromAccount.getAccNumber())
				.orElseThrow(() -> new ResourceNotFoundException("Account with account number "+ fromAccount.getAccNumber() + " does not exist"));
		double balance = account.getBalance();
		if (balance > transactionAmount) {
			transaction.setTransactionType(TransactionType.SAVING);
			transaction.setToAccount(transaction.getFromAccount());
			transactionDao.save(transaction);
			account.setBalance(balance - transactionAmount);
			accountDao.save(account);
			double totalBalance = accountDao.findById(transaction.getFromAccount().getAccNumber()).get().getBalance();
			map.put("success", "Withdrawal sucessfully");
			Map<String, Object> withdrawInfo=new HashMap<String, Object>();
			withdrawInfo.put("BALANCE", totalBalance);
			//sender.sendWithdrawEmail(withdrawInfo);
		}else {
			throw new ResourceNotFoundException("Insufficient Balance");
		}
		return map;
	}

	@Override
	public List<Transaction> miniStatement(long accNo) {
		List<Transaction> transactions = new ArrayList<Transaction>();
		Map<String, List<Transaction>> map = new HashMap<String, List<Transaction>>();
		accountDao.findById(accNo).orElseThrow(() -> new ResourceNotFoundException("Account with account number "+ accNo + " does not exist"));
		transactions = transactionDao.getMiniStatement(accNo);
		map.put("success", transactions);
		return transactions;
	}

	@Override
	public List<Transaction> customizeStatement(Transaction transaction, long accNo) {
		List<Transaction> transactions = new ArrayList<Transaction>();
		Map<String, List<Transaction>> map = new HashMap<String, List<Transaction>>();
		accountDao.findById(accNo).orElseThrow(() -> new ResourceNotFoundException("Account with account number "+ accNo + " does not exist"));
		transactions = transactionDao.getCustomiseStatement(transaction.getTransactionDate(),
				transaction.getTransactionDate(),accNo,accNo);
		System.out.println(transaction.getTransactionAmount());
		map.put("success", transactions);
		return transactions;
	}

}


