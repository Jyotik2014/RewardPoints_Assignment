/*
 * Service implementation class for Customer CRUD operations 
 */
package com.customer.reward.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.reward.entity.Customer;
import com.customer.reward.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	//Save Customer
	@Override
	public Customer saveCustomer(Customer cust) {
		customerRepository.save(cust);
		return cust;
	}

	//Fetching Customers List
	@Override
	public List<Customer> fetchCustomerList() {
		return (List<Customer>) customerRepository.findAll();
	}

	//Updating Customer for particular ID
	@Override
	public Customer updateCustomer(Customer cust, Long custId) {
		    Customer custDb = customerRepository.findById(custId).get();
		    if(cust.getName()!=null  && "".equalsIgnoreCase(cust.getName()))
		    	custDb.setName(cust.getName());
		    if(cust.getEmail()!=null  && "".equalsIgnoreCase(cust.getEmail()))
		    	custDb.setEmail(cust.getEmail());
			return customerRepository.save(custDb);
	}

	//Delete Customer ID
	@Override
	public void deleteCustomerById(Long custId) {
		customerRepository.deleteById(custId);
	}

}
