/*
 * Test class for RewardPointController 
 * Calculating rewards as per the Monthly and Total for all and per customer
 */
package com.customer.reward.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;


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

	@Test
	public void testCalculateTotalRewardPointsByCustomerId() {
		Long custId=1L;
		ResponseEntity<Integer> response = testRestTemplate.exchange(BASE_URL+"/totalReward/"+custId, HttpMethod.GET, null, Integer.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
	}
	
	@Test
	public void testcalculateMonthlyRewardPointsByCustomerId() {
		Long custId=1L;
		String yearMonth="2024-07";
		ResponseEntity<Integer> response = testRestTemplate.exchange(BASE_URL+"/monthlyReward/"+custId+"/"+yearMonth, HttpMethod.GET, null, Integer.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
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
		ResponseEntity<HashMap> response = testRestTemplate.exchange(BASE_URL+"/monthlyRewardAll/"+yearMonth, HttpMethod.GET, null, HashMap.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
	}
}
