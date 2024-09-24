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

	//Fetch all Transactions
	@Override
	public List<Transaction> fetchTransactionList() {
		
		return (List<Transaction>) transactionRepository.findAll();
	}

	//Update transaction
	@Override
	public Transaction updateTransaction(Transaction transaction, Long transactionId,Long custId) {
		Transaction transactionDb = transactionRepository.findById(transactionId).get();
		    if(transaction.getAmount()!=null)
		    	transactionDb.setAmount(transaction.getAmount());
		    if(transaction.getTransactionDate()!=null)
		    	transactionDb.setTransactionDate(transaction.getTransactionDate());
		    if(custId!=null) {
		      transactionDb.setCustomer(customerRepository.findById(custId).get());
		    }
			return transactionRepository.save(transactionDb);
		
	}

	//Delete Transaction
	@Override
	public void deleteTransactionById(Long transactionId) {
		transactionRepository.deleteById(transactionId);
	}

}
