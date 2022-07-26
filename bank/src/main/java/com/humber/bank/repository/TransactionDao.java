package com.humber.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.humber.bank.entity.Transaction;

@Repository
public interface TransactionDao extends JpaRepository<Transaction, Long>{

}
