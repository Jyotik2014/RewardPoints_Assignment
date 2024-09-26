package com.customer.reward.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.customer.reward.entity.Transaction;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"spring.sql.init.mode=never"})
public class TransactionServiceTest {

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private CustomerService customerService;
	
	@Test
	public void testSaveTransaction() {
		Transaction t = new Transaction();
		t.setId(13L);
		t.setAmount(200.00);
		t.setCustomer(customerService.getCustById(1L));
		t.setTransactionDate(new Date());
		Transaction newTransaction=transactionService.saveTransaction(t);
		assertNotNull(newTransaction.getId());
		assertEquals(t.getAmount(), newTransaction.getAmount());
	}
	
	@Test 
	public void testGetTransactionById() {
		Long transactionId =13L;
		assertNotNull(transactionService.getTransactionById(transactionId));
	}
	
	@Test
	public void testUpdateTransaction() {
		Transaction t = new Transaction();
		t.setId(14L);
		t.setAmount(300.00);
		t.setCustomer(customerService.getCustById(2L));
		t.setTransactionDate(new Date());
		Transaction createdTransaction=transactionService.saveTransaction(t);
		Transaction updatedTransaction = new Transaction();
		updatedTransaction.setId(createdTransaction.getId());
		updatedTransaction.setAmount(500.00);
		updatedTransaction.setCustomer(customerService.getCustById(2L));
		updatedTransaction.setTransactionDate(new Date());
		
		Transaction retriveTransaction = transactionService.updateTransaction(updatedTransaction,createdTransaction.getId());
		
		assertEquals(retriveTransaction.getAmount(), updatedTransaction.getAmount());
	}
	
	
	@Test
	public void testDeleteTransactionById() {
		Long transactionId =13L;
		transactionService.deleteTransactionById(transactionId);
	}
	
	@Test
	public void testFetchTransactionList() {
		Transaction t1 = new Transaction();
		t1.setId(15L);
		t1.setAmount(300.00);
		t1.setCustomer(customerService.getCustById(2L));
		t1.setTransactionDate(new Date());
		Transaction t2 = new Transaction();
		t2.setId(16L);
		t2.setAmount(300.00);
		t2.setCustomer(customerService.getCustById(2L));
		t2.setTransactionDate(new Date());
		transactionService.saveTransaction(t1);
		transactionService.saveTransaction(t2);
		List<Transaction> tList = transactionService.fetchTransactionList();
		assertEquals(16, tList.size());
	}
	
	
}
