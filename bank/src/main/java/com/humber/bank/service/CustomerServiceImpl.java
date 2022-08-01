package com.humber.bank.service;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humber.bank.entity.Customer;
import com.humber.bank.exception.ResourceNotFoundException;
import com.humber.bank.repository.CustomerDao;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerDao customerDao;

	@Override
	@Transactional
	public Map<String, Customer> addCustomer(Customer customer) {
		Map<String, Customer> map = new HashMap<String, Customer>();
		customerDao.save(customer);
		map.put("Customer saved successfully", customer);
		return map;
	}

	@Override
	@Transactional
	public Map<String, Customer> updateCustomer(Customer customer) {
		Map<String, Customer> map = new HashMap<String, Customer>();
		customerDao.findById(customer.getPId()).orElseThrow(
				() -> new ResourceNotFoundException("No customer record exists for customerId: " + customer.getPId()));
		customerDao.save(customer);
		map.put("success", customer);
		return map;
	}

	@Override
	@Transactional
	public void deleteCustomer(long customerId) {
		customerDao.findById(customerId).orElseThrow(
				() -> new ResourceNotFoundException("No customer record exists for customerId: " + customerId));
		customerDao.deleteById(customerId);
		
	}

	

	
}
