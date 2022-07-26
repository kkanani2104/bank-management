package com.humber.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.humber.bank.entity.Account;

@Repository
public interface AccountDao extends JpaRepository<Account, Long>{

}
