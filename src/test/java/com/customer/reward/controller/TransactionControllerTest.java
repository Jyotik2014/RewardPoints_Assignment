package com.customer.reward.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.customer.reward.entity.Customer;
import com.customer.reward.entity.Transaction;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"spring.sql.init.mode=never"})
/*
 * @ActiveProfiles("test")
 * 
 * @TestPropertySource(properties =
 * {"spring.config.location=classpath:application-test.properties"})
 */
public class TransactionControllerTest {
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Test
	public void testSaveTransaction() {
		Transaction transaction= new Transaction();
		transaction.setId(12L);
		Customer cust = new Customer();
		cust.setId(1L);
		cust.setName("Ram");
		cust.setEmail("Ram@abc.com");
		transaction.setCustomer(cust);
		transaction.setAmount((double) 100);
		transaction.setTransactionDate(new Date());
		
		ResponseEntity<Transaction> response= testRestTemplate.postForEntity("/addTransaction", transaction, Transaction.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(transaction.getAmount(), response.getBody().getAmount());
	}

	@Test
	public void testFetchAllTransactions() {
		ResponseEntity<List<Transaction>> response = testRestTemplate.exchange("/transactions", HttpMethod.GET, null, new ParameterizedTypeReference<List<Transaction>>() {});
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
	}

	/*
	 * @Test public void testUpdateTransaction() { Long tranId= 1L; Transaction
	 * newTran=new Transaction(); Customer cust = new Customer(); cust.setId(1L);
	 * cust.setName("Ram"); cust.setEmail("Ram@abc.com"); newTran.setCustomer(cust);
	 * newTran.setAmount((double) 100); newTran.setTransactionDate(new Date());
	 * ResponseEntity<Transaction> response=
	 * testRestTemplate.exchange("/updateTransaction/"+tranId+"/"+cust.getId(),
	 * HttpMethod.PUT,new HttpEntity<Transaction>(newTran),Transaction.class);
	 * assertEquals(HttpStatus.OK, response.getStatusCode());
	 * assertEquals(newTran.getAmount(), response.getBody().getAmount()); }
	 */

	@Test
	public void testDeleteTransactionById() {
		Long tranId= 13L;
		ResponseEntity<Void> response= testRestTemplate.exchange("/deleteTransaction/"+tranId, HttpMethod.DELETE,null,Void.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
	}
}
