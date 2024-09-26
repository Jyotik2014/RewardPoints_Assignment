package com.customer.reward.service;


import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"spring.sql.init.mode=never"})
public class RewardPointsServiceTest {
@Autowired
private RewardPointsService rewardPointsService;

@Test
public void testCalculateRewardPoints() {
	Double transactionAmoun = 100.00;
	assertNotNull(transactionAmoun);
	int rewardPoints = 0;
	rewardPoints = rewardPointsService.calculateRewardPoints(transactionAmoun);
	assertEquals(50, rewardPoints);
}

@Test
public void testCalculateTotalRewardPointsByCustomerId() {
	Integer totalRewardPoints =0;
	Long custId =1L;
	assertNotNull(custId);
	totalRewardPoints= rewardPointsService.calculateTotalRewardPointsByCustomerId(custId);
	assertNotNull(totalRewardPoints);
	
}

@Test
public void testCalculateMonthlyRewardPointsByCustomerId() {
	Integer monthlyRewardPoints =0;
	Long custId =1L;
	String yearMonth="2024-07";
	assertNotNull(custId);
	assertNotNull(yearMonth);
	monthlyRewardPoints=rewardPointsService.calculateMonthlyRewardPointsByCustomerId(custId, yearMonth);
	assertNotNull(monthlyRewardPoints);
}

@Test
public void tesCalculateTotalRewardPointsAllCustomer() {
	Map<String, Integer> custmerRewardMonthly = rewardPointsService.calculateTotalRewardPointsAllCustomer();
	assertNotNull(custmerRewardMonthly);
}

@Test
public void testCalculateMonthlyRewardPointsAllCustomer() {
	String yearMonth="2024-07";
	assertNotNull(yearMonth);
	Map<String, Integer> custmerRewardTotal = rewardPointsService.calculateMonthlyRewardPointsAllCustomer(yearMonth);
	assertNotNull(custmerRewardTotal);
}
}
