/*
 * This is Restcontroller class for Customers CRUD operations
 * CustomerService is used to perform CRUD operations
 * */
package com.customer.reward.controller;



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

import com.customer.reward.entity.Customer;
import com.customer.reward.service.CustomerService;


@RestController
public class CustomerController {

	@Autowired 
	private CustomerService customerService;
	
	//Save customer
	@PostMapping("/addCustomer")
	public ResponseEntity<Object> saveCustomer(@RequestBody Customer cust) {
		try {
			   return ResponseEntity.ok().body(customerService.saveCustomer(cust));
		    }
			catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Error occured while Adding Customer");
			}
		
	}
	
	//Fetch List of all the customers
	@GetMapping("/customers")
	public ResponseEntity<Object> fetchAllCustomers()
	{
		try {
			   return ResponseEntity.ok().body(customerService.fetchCustomerList());
		    }
			catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Error occured while fetching customers");
			}
	}

	//Update Customer as per the customer id passed as path variable
	@PutMapping("/updateCustomer/{id}")
	public ResponseEntity<Object> updateCustomer(@RequestBody Customer cust ,@PathVariable Long id) {
		try {
			if (id <= 0) {
				throw new IllegalArgumentException("Customer ID must be a positive number");
			}
			   return ResponseEntity.ok().body(customerService.updateCustomer(cust, id));
		    }
		    catch (NumberFormatException e) {
			  return ResponseEntity.badRequest().body("Invalid customer ID format");
		    }
		    catch (IllegalArgumentException e) {
		    	return ResponseEntity.badRequest().body(e.getMessage());
			}
		    
			catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Error occured while Updating customers");
			}
	}

	//Delete Customer as per the customer id passed as path variable
	@DeleteMapping("/deleteCustomer/{id}")
	public ResponseEntity<Object> deleteCustomerById(@PathVariable Long id) {
		try {
			if (id <= 0) {
				throw new IllegalArgumentException("Customer ID must be a positive number");
			}
			customerService.deleteCustomerById(id);
			   return ResponseEntity.ok().body("Customer deleted successfully");
		    }
		catch (NumberFormatException e) {
			  return ResponseEntity.badRequest().body("Invalid customer ID format");
		    }
		    catch (IllegalArgumentException e) {
		    	return ResponseEntity.badRequest().body(e.getMessage());
			}
		    
			catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Error occured while Deleting customers");
			}
		
	}
}
