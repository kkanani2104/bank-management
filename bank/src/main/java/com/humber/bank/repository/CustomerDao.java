package com.humber.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.humber.bank.entity.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Long>{

}
