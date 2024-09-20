package com.customer.reward.service;

import java.util.Map;

public interface RewardPointsService {
public Integer calculateRewardPoints(Double transactionAmount);
public Integer calculateTotalRewardPointsByCustomerId(Long customerId);
public Integer calculateMonthlyRewardPointsByCustomerId(Long customerId,String yearMonth);
public Map<String,Integer> calculateTotalRewardPointsAllCustomer();
public Map<String,Integer> calculateMonthlyRewardPointsAllCustomer(String yearMonth);
}
