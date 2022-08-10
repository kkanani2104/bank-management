package com.humber.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.humber.bank.entity.Branch;

@Repository
public interface BranchDao extends JpaRepository<Branch, Integer>{

}
