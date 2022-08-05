package com.humber.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.humber.bank.entity.CustomerUserDetails;
import com.humber.bank.entity.User;
import com.humber.bank.repository.UserDao;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDao repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUserName(username);
        return new CustomerUserDetails(user);
    }
}
