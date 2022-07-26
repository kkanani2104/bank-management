package com.humber.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.humber.bank.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Long>{

}
