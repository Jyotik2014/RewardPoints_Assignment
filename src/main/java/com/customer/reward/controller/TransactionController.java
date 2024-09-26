/*
 * This is Restcontroller class for Transactions CRUD operations
 * TransactionService is used to perform CRUD operations
 * */
package com.customer.reward.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.customer.reward.entity.Transaction;

import com.customer.reward.service.TransactionService;

@RestController
public class TransactionController {
	@Autowired 
	private TransactionService transactionService;
	
	//Save Transaction
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

	//Fetch List of all transactions	
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

	//Update transaction , transactionId passed as pathVariable
	@PutMapping("/updateTransaction/{transactionId}/{custId}")
	public ResponseEntity<Object> updateTransaction(@RequestBody Transaction transaction ,@PathVariable Long transactionId) {
		try {
			if (transactionId <= 0 ) {
				throw new IllegalArgumentException("Transaction ID must be a positive number");
			}
			   return ResponseEntity.ok().body( transactionService.updateTransaction(transaction, transactionId));
		    }
		    catch (NumberFormatException e) {
			  return ResponseEntity.badRequest().body("Invalid Transaction ID format");
		    }
		    catch (IllegalArgumentException e) {
		    	return ResponseEntity.badRequest().body(e.getMessage());
			}
			catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Error occured while Updating Transactions");
			}
		
	}

	//Delete Transaction , transactionId passed as pathVariable
	@DeleteMapping("/deleteTransaction/{transactionId}")
	public ResponseEntity<Object> deleteTransactionById(@PathVariable Long transactionId) {
		try {
			if (transactionId <= 0 ) {
				throw new IllegalArgumentException("Transaction ID must be a positive number");
			}
			transactionService.deleteTransactionById(transactionId);
			   return ResponseEntity.ok().body("Transaction Deleted scuccessfully");
		    }
		   catch (MethodArgumentTypeMismatchException e) {
	    	return ResponseEntity.badRequest().body("Invalid customer ID format");
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
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Object> handleValidationExceptions(MethodArgumentTypeMismatchException ex){
		return ResponseEntity.badRequest().body("Arugument passed is not Valid for transactionId ");
	}
}
