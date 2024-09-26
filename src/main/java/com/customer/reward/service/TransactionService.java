package com.customer.reward.service;

import java.util.List;

import com.customer.reward.entity.Transaction;



public interface TransactionService {
	Transaction saveTransaction(Transaction transaction);
	List<Transaction> fetchTransactionList();
	Transaction updateTransaction(Transaction transaction,Long transactionId);
	void deleteTransactionById(Long transactionId);
	Transaction getTransactionById(Long transactionId);
}
