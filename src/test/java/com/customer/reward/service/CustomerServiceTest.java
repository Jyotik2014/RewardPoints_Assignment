package com.customer.reward.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.customer.reward.entity.Customer;
import com.customer.reward.repository.CustomerRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"spring.sql.init.mode=never"})
public class CustomerServiceTest {
	@Autowired 
	private CustomerService customerService;

	
	@Test
	public void testSaveCustomer() {
		Customer cust=new Customer();
		cust.setId(5L);
		cust.setName("Sandhya");
		cust.setEmail("sandhya@abc.com");
		Customer newCust=customerService.saveCustomer(cust);
		assertNotNull(newCust.getId());
		assertEquals(cust.getName(), newCust.getName());
		
	}
	
	@Test
	public void testUpdateCustomer() {
		Customer cust=new Customer();
		cust.setId(6L);
		cust.setName("Raj");
		cust.setEmail("raj@abc.com");
		Customer createdCustomer=customerService.saveCustomer(cust);
		Customer updateCustomer = new Customer();
		updateCustomer.setEmail("ravi@abc.com");
		updateCustomer.setName("Ravi");
		updateCustomer.setId(cust.getId());
		
		Customer retrievedCust =customerService.updateCustomer(updateCustomer,createdCustomer.getId());
		assertEquals("Ravi", retrievedCust.getName());
	    
	}
	
	@Test
	public void testDeleteCustomer() {
		Long custId=6L;
		customerService.deleteCustomerById(custId);
	}
	
	@Test
	public void testFetchAllList() {
		Customer cust1=new Customer();
		cust1.setId(7L);
		cust1.setName("Raju");
		cust1.setEmail("raju@abc.com");
		Customer cust2=new Customer();
		cust2.setId(8L);
		cust2.setName("Sai");
		cust2.setEmail("sai@abc.com");
		customerService.saveCustomer(cust1);
		customerService.saveCustomer(cust2);
		List<Customer> custList= customerService.fetchCustomerList();
		assertEquals(6, custList.size());	
	}
	
	}


