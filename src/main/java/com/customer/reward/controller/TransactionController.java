package com.customer.reward.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.customer.reward.entity.Transaction;

import com.customer.reward.service.TransactionService;

@RestController
public class TransactionController {
	@Autowired 
	private TransactionService transactionService;
	
	@PostMapping("/addTransaction")
	public ResponseEntity<Object> saveTransaction(@RequestBody Transaction transaction) {
		try {
			   return ResponseEntity.ok().body( transactionService.saveTransaction(transaction));
		    }
			catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Error occured while Adding Transactions");
			}
		
	}

		
	@GetMapping("/transactions")
	public ResponseEntity<Object> fetchAllTransactions()
	{
		try {
		   return ResponseEntity.ok().body(transactionService.fetchTransactionList());
	    }
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error occured while fetching Transactions");
		}
	}

	@PutMapping("/transactions/{transactionId}/{custId}")
	public ResponseEntity<Object> updateTransaction(@RequestBody Transaction transaction ,@PathVariable Long transactionId,@PathVariable Long custId) {
		try {
			if (custId <= 0 || transactionId <= 0 ) {
				throw new IllegalArgumentException("Customer ID or Transaction ID must be a positive number");
			}
			   return ResponseEntity.ok().body( transactionService.updateTransaction(transaction, transactionId, custId));
		    }
		    catch (NumberFormatException e) {
			  return ResponseEntity.badRequest().body("Invalid customer ID/Transaction ID format");
		    }
		    catch (IllegalArgumentException e) {
		    	return ResponseEntity.badRequest().body(e.getMessage());
			}
			catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Error occured while Updating Transactions");
			}
		
	}

	@DeleteMapping("/transactions/{transactionId}")
	public ResponseEntity<Object> deleteTransactionById(@PathVariable Long transactionId) {
		try {
			if (transactionId <= 0 ) {
				throw new IllegalArgumentException("Transaction ID must be a positive number");
			}
			transactionService.deleteTransactionById(transactionId);
			   return ResponseEntity.ok().body("Transaction Deleted scuccessfully");
		    }
		    catch (NumberFormatException e) {
			  return ResponseEntity.badRequest().body("Invalid customer ID format");
		    }
		    catch (IllegalArgumentException e) {
		    	return ResponseEntity.badRequest().body(e.getMessage());
			}
			catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Error occured while Deleting Transactions");
			}
		
	}
}
