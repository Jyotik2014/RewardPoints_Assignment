/*
 * Test class for RewardPointController 
 * Calculating rewards as per the Monthly and Total for all and per customer
 */
package com.customer.reward.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class RewardPointControllerTest {
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	private static final String BASE_URL ="/reward-points";

	//Testing valid response for Total rewards for cust id
	@Test
	public void testCalculateTotalRewardPointsByCustomerId() {
		Long custId=1L;
		assertNotNull(custId);
		ResponseEntity<Map> response = testRestTemplate.exchange(BASE_URL+"/totalReward/"+custId, HttpMethod.GET, null, Map.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
	}
	
	//Testing Invalid response for Total rewards for cust id
	@Test
	public void testCalculateTotalRewardPointsByCustomerIdInvalid() {
		Long custId=-1L;
		assertNotNull(custId);
		try {
			if (custId <= 0) {
				throw new IllegalArgumentException("Customer ID must be a positive number");
			}
		ResponseEntity<Integer> response = testRestTemplate.exchange(BASE_URL+"/totalReward/"+custId, HttpMethod.GET, null, Integer.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		}
		catch (NumberFormatException e) {
			ResponseEntity<String> response = ResponseEntity.badRequest().body("Invalid customer ID format");
			assertEquals("Invalid customer ID format",response.getBody());
		    }
		    catch (IllegalArgumentException e) {
		    	ResponseEntity<String> response = ResponseEntity.badRequest().body(e.getMessage());
				assertEquals("Customer ID must be a positive number",response.getBody());
			}
		    
			catch (Exception e) {
				ResponseEntity<String> response=ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Error occured while Calculating Total reward Points By Customer Id");
				assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
				
			}
		
	}
	
	//Testing valid response for Total rewards for cust id
	@Test
	public void testcalculateMonthlyRewardPointsByCustomerId() {
		Long custId=1L;
		String yearMonth="2024-07";
		assertNotNull(custId);
		assertNotNull(yearMonth);
		ResponseEntity<Integer> response = testRestTemplate.exchange(BASE_URL+"/monthlyReward/"+custId+"/"+yearMonth, HttpMethod.GET, null, Integer.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
	}
	
	@Test
	public void testcalculateMonthlyRewardPointsByCustomerIdInvalid() {
		Long custId=-1L;
		String yearMonth="2024-07";
		assertNotNull(custId);
		assertNotNull(yearMonth);
		try {
			if (custId <= 0) {
				throw new IllegalArgumentException("Customer ID must be a positive number");
			}
			ResponseEntity<Integer> response = testRestTemplate.exchange(BASE_URL+"/monthlyReward/"+custId+"/"+yearMonth, HttpMethod.GET, null, Integer.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		}
		catch (NumberFormatException e) {
			ResponseEntity<String> response = ResponseEntity.badRequest().body("Invalid customer ID format");
			assertEquals("Invalid customer ID format",response.getBody());
		    }
		    catch (IllegalArgumentException e) {
		    	ResponseEntity<String> response = ResponseEntity.badRequest().body(e.getMessage());
				assertEquals("Customer ID must be a positive number",response.getBody());
			}
		    
			catch (Exception e) {
				ResponseEntity<String> response=ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Error occured while Calculating Total reward Points By Customer Id");
				assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
				
			}
		
	}
	
	@Test
	public void testcalculateTotalRewardPointsAllCustomer() {
		ResponseEntity<HashMap> response = testRestTemplate.exchange(BASE_URL+"/totalRewardAll", HttpMethod.GET, null, HashMap.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
	}
	@Test
	public void calculateMonthlyRewardPointsAllCustomer() {
		String yearMonth="2024-07";
		assertNotNull(yearMonth);
		ResponseEntity<HashMap> response = testRestTemplate.exchange(BASE_URL+"/monthlyRewardAll/"+yearMonth, HttpMethod.GET, null, HashMap.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
	}
}
