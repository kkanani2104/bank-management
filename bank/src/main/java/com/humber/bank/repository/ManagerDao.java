package com.humber.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.humber.bank.entity.Manager;

@Repository
public interface ManagerDao extends JpaRepository<Manager, Long>{

}
