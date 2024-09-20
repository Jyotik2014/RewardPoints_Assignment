package com.customer.reward.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.customer.reward.entity.Customer;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {
@Autowired
private TestRestTemplate testRestTemplate;

@Test
public void testSaveCustomer() {
	Customer cust= new Customer();
	cust.setId((long) 1);
	cust.setName("Raj");
	cust.setEmail("Raj@abc.com");
	ResponseEntity<Customer> response= testRestTemplate.postForEntity("/addCustomer", cust, Customer.class);
	assertEquals(HttpStatus.OK, response.getStatusCode());
	assertEquals(cust.getName(), response.getBody().getName());
}

@Test
public void testFetchAllCustomers() {
	ResponseEntity<List<Customer>> response = testRestTemplate.exchange("/customers", HttpMethod.GET, null, new ParameterizedTypeReference<List<Customer>>() {});
	assertEquals(HttpStatus.OK, response.getStatusCode());
	assertNotNull(response.getBody());
}

@Test
public void testUpdateCustomer() {
	Long custId= 1L;
	Customer newCust=new Customer();
	newCust.setName("Ram");
	newCust.setEmail("ram@abc.com");
	ResponseEntity<Customer> response= testRestTemplate.exchange("/customers/"+custId, HttpMethod.PUT,new HttpEntity<Customer>(newCust),Customer.class);
	assertEquals(HttpStatus.OK, response.getStatusCode());
	assertEquals(newCust.getName(), response.getBody().getName());
}

@Test
public void testDeleteCustomer() {
	Long custId= 4L;
	ResponseEntity<Void> response= testRestTemplate.exchange("/customers/"+custId, HttpMethod.DELETE,null,Void.class);
	assertEquals(HttpStatus.OK, response.getStatusCode());
	
}
}
