/*
 * Rest Controller for reward point calculation 
 * Calculated Mothly and total reward per customer and for all customers
 * 
 */
package com.customer.reward.controller;



import java.security.PublicKey;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.customer.reward.service.RewardPointsService;

@RestController
@RequestMapping("/reward-points")
public class RewardPointsController {
	
	@Autowired
	private RewardPointsService rewardPointsService;
	
	public static final Pattern YearMonth_pattern= Pattern.compile("^[0-9]{4}-[0-9]{2}$");
	
	// Total rewards for given customer id 
	@GetMapping("/totalReward/{custId}")
	public ResponseEntity<Object> calculateTotalRewardPointsByCustomerId(@PathVariable Long custId) {
		try {
			if (custId <= 0) {
				throw new IllegalArgumentException("Customer ID must be a positive number");
			}
			   Map<Long, Integer> result = new HashMap<Long, Integer>();
			   result.put(custId, rewardPointsService.calculateTotalRewardPointsByCustomerId(custId));
			   return ResponseEntity.ok().body(result);
			   
		    }
		    catch (NumberFormatException e) {
			  return ResponseEntity.badRequest().body("Invalid customer ID format");
		    }
		    catch (IllegalArgumentException e) {
		    	return ResponseEntity.badRequest().body(e.getMessage());
			}
		    
			catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Error occured while Calculating Total reward Points By Customer Id");
			}
	}
	
	//monthly rewards for given customer 
	@GetMapping("/monthlyReward/{custId}/{yearMonth}")
	public ResponseEntity<Object> calculateMonthlyRewardPointsByCustomerId(@PathVariable Long custId,@PathVariable String yearMonth) {
		try {
			if (custId <= 0) {
				throw new IllegalArgumentException("Customer ID must be a positive number");
			}
			if(!validateYearMonth(yearMonth)) {
				throw new IllegalArgumentException("Please provide Valid year Month YYYY-MM eg.2024-07");
			}
			   return ResponseEntity.ok().body(rewardPointsService.calculateMonthlyRewardPointsByCustomerId(custId,yearMonth));
		    }
		    catch (NumberFormatException e) {
			  return ResponseEntity.badRequest().body("Invalid customer ID or YearMonth format,for Customer ID use number and for year and Month use YYYY-MM format");
		    }
		    catch (IllegalArgumentException e) {
		    	return ResponseEntity.badRequest().body(e.getMessage());
			}
		    
			catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Error occured while Calculating Monthly reward Points By Customer Id");
			}

	}
	
	//Total rewards for all customers
	@GetMapping("/totalRewardAll")
	public ResponseEntity<Object> calculateTotalRewardPointsAllCustomer(){
		try {
			   return ResponseEntity.ok().body(rewardPointsService.calculateTotalRewardPointsAllCustomer());
		    }
			catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Error occured while Calculating Total rewards for all customer");
			}
	}
	
	//Monthly rewards for all customers
	@GetMapping("/monthlyRewardAll/{yearMonth}")
	public ResponseEntity<Object> calculateMonthlyRewardPointsAllCustomer(@PathVariable String yearMonth){
		try {
			if(!validateYearMonth(yearMonth)) {
				throw new IllegalArgumentException("Please provide Valid year Month YYYY-MM  eg.2024-07");
			}
			   return ResponseEntity.ok().body(rewardPointsService.calculateMonthlyRewardPointsAllCustomer(yearMonth));
		    }
		    catch (IllegalArgumentException e) {
		    	return ResponseEntity.badRequest().body(e.getMessage());
			}
			catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Error occured while Calculating Monthly rewards for all customer");
			}
	}
	//Method for year month validation
	public boolean validateYearMonth(String yearMonth) {
		boolean status=true;
		try {
		String[] yearMonthSplit = yearMonth.split("-");
		int year =Integer.parseInt(yearMonthSplit[0]);
		int month =Integer.parseInt(yearMonthSplit[1]);
		
		if(YearMonth_pattern.matcher(yearMonth).matches()) {
			if(year>LocalDate.now().getYear()) {
				status=false;
			}
			if(month<=0 || month>12)
			{
				status=false;
			}
		}
		else {
			status=false;
		}
		}
		catch (Exception e) {
			status=false;
		}
		return status;
	}

	
	  @ExceptionHandler(MethodArgumentTypeMismatchException.class) public
	  ResponseEntity<Object>
	  handleValidationExceptions(MethodArgumentTypeMismatchException ex){ return
	  ResponseEntity.badRequest().
	  body("Arugument passed is not Valid for Customer ID use number and for year and Month use YYYY-MM format"
	  ); }
	 
	
}
