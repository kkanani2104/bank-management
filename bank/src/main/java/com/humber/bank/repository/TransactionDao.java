package com.humber.bank.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.humber.bank.entity.Transaction;



@Repository
public interface TransactionDao extends JpaRepository<Transaction, Long>{

	@Query(nativeQuery = true, value  = "select * from Transaction where from_account =?1 or to_account =?1 order by transaction_date DESC ")
	List<Transaction> getMiniStatement(long accNo);

	@Query(nativeQuery = true, value  = "select * from Transaction where from_account =:fromAccNo OR to_account =:toAccNo  AND transaction_date between :fromDate and :toDate")
	List<Transaction> getCustomiseStatement(LocalDate fromDate,LocalDate toDate,long fromAccNo,long toAccNo);
	
}
