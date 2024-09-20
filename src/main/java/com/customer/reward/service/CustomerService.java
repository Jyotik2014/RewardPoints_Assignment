package com.customer.reward.service;

import java.util.List;

import com.customer.reward.entity.Customer;



public interface CustomerService {
	Customer saveCustomer(Customer cust);
	List<Customer> fetchCustomerList();
	Customer updateCustomer(Customer cust,Long custId);
	void deleteCustomerById(Long custId);
}
