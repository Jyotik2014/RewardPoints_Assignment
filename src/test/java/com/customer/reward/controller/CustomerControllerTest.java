package com.customer.reward.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.customer.reward.entity.Customer;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"spring.sql.init.mode=never"})
/*
 * @ActiveProfiles("test")
 * 
 * @TestPropertySource(properties =
 * {"spring.config.location=classpath:application-test.properties"})
 */
public class CustomerControllerTest {
@Autowired
private TestRestTemplate testRestTemplate;


@Test
public void testSaveCustomer() {
	Customer cust= new Customer();
	cust.setId((long) 1);
	cust.setName("Raj");
	cust.setEmail("Raj@abc.com");
	assertNotNull(cust);
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
	assertNotNull(custId);
	Customer newCust=new Customer();
	newCust.setName("Ram");
	newCust.setEmail("ram@abc.com");
	ResponseEntity<Customer> response= testRestTemplate.exchange("/updateCustomer/"+custId, HttpMethod.PUT,new HttpEntity<Customer>(newCust),Customer.class);
	assertEquals(HttpStatus.OK, response.getStatusCode());
	assertEquals(newCust.getName(), response.getBody().getName());
}

@Test
public void testDeleteCustomer() {
	Long custId= 4L;
	assertNotNull(custId);
	ResponseEntity<Void> response= testRestTemplate.exchange("/deleteCustomer/"+custId, HttpMethod.DELETE,null,Void.class);
	assertEquals(HttpStatus.OK, response.getStatusCode());
	
}
}
