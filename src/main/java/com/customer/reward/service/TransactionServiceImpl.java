/*
 * Service implementation class for Transactions for CRUD operations 
 */
package com.customer.reward.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.customer.reward.entity.Transaction;
import com.customer.reward.repository.CustomerRepository;
import com.customer.reward.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService{
	@Autowired
	private TransactionRepository transactionRepository;

	private CustomerRepository customerRepository;
	
	//Saving Transaction
	@Override
	public Transaction saveTransaction(Transaction transaction) {
		transactionRepository.save(transaction);
		return transaction;
	}
	
	//Fetch Transaction by ID
	@Override
	public Transaction getTransactionById(Long transactionId) {
		Transaction t = transactionRepository.findById(transactionId).orElseThrow();
		return t;
	}

	//Fetch all Transactions
	@Override
	public List<Transaction> fetchTransactionList() {
		
		return (List<Transaction>) transactionRepository.findAll();
	}

	//Update transaction
	@Override
	public Transaction updateTransaction(Transaction transaction, Long transactionId) {
		Transaction transactionDb = getTransactionById(transactionId);
		    if(transaction.getAmount()!=null)
		    	transactionDb.setAmount(transaction.getAmount());
		    if(transaction.getTransactionDate()!=null)
		    	transactionDb.setTransactionDate(transaction.getTransactionDate());
			return transactionRepository.save(transactionDb);
		
	}

	//Delete Transaction
	@Override
	public void deleteTransactionById(Long transactionId) {
		transactionRepository.deleteById(transactionId);
	}

	

}
