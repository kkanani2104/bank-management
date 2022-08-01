package com.humber.bank.service;

import java.util.Map;

import com.humber.bank.entity.Customer;

public interface CustomerService {

	Map<String, Customer> addCustomer(Customer customer);
	Map<String, Customer> updateCustomer(Customer customer);
	void deleteCustomer(long customerId);
}
