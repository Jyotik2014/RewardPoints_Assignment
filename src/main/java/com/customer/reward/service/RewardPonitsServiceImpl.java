/*
 * Service implementation class for Reward point Calculation 
 */
package com.customer.reward.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.reward.entity.Customer;
import com.customer.reward.entity.Transaction;
import com.customer.reward.repository.CustomerRepository;
import com.customer.reward.repository.TransactionRepository;

@Service
public class RewardPonitsServiceImpl implements RewardPointsService{

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	
	
	@Override
	public Integer calculateRewardPoints(Double transactionAmount) {
		// TODO Auto-generated method stub
		int rewardPoints = 0;
		if(transactionAmount!=null) {
			if (transactionAmount >100)
			{
				Double remAmount=transactionAmount-100;
				rewardPoints += remAmount*2;
				rewardPoints+= 50 ;
			}
			else if(transactionAmount>=50 && transactionAmount<=100) {
				rewardPoints+= (transactionAmount-50);
			}
		}
		return rewardPoints;
	}

	//Calculating total reward points by Customer id
	@Override
	public Integer calculateTotalRewardPointsByCustomerId(Long customerId) {
		Integer totalRewardPoints =0;
	 	List<Transaction> allTransactions = transactionRepository.findAll();
	 	
	 	List<Transaction> transactionListByCustomerId= allTransactions.stream()
	 			.filter(transaction -> transaction.getCustomer().getId()==customerId)
	 			.toList();
	 	//transactionListByCustomerId.forEach(transaction -> {totalRewardPoints += calculateRewardPoints(transaction.getAmount());});
	 	for(Transaction transaction :transactionListByCustomerId) {
	 		totalRewardPoints += calculateRewardPoints(transaction.getAmount());
	 	}
		return totalRewardPoints;
	}

	//Calculating monthly reward points by Customer id
	@Override
	public Integer calculateMonthlyRewardPointsByCustomerId(Long customerId,String yearMonth) {
		Integer monthlyRewardPoints =0;
		List<Transaction> allTransactions = transactionRepository.findAll();
	 	List<Transaction> transactionListByCustomerId= allTransactions.stream()
	 			.filter(transaction -> transaction.getCustomer().getId()==customerId)
	 			.toList();
	 	
	 	YearMonth parsedYearMonth = YearMonth.parse(yearMonth);
	 	DateTimeFormatter newPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	 	for(Transaction transaction :transactionListByCustomerId) {
	 		String[] newFormatedDate= transaction.getTransactionDate().toString().split(" ");
	 		
	 		if(LocalDate.parse(newFormatedDate[0],newPattern).getMonthValue() ==parsedYearMonth.getMonthValue() && LocalDate.parse(newFormatedDate[0],newPattern).getYear()==parsedYearMonth.getYear())
	 		{
	 			monthlyRewardPoints +=calculateRewardPoints(transaction.getAmount());
	 		}
	 	}
		return monthlyRewardPoints;
	}

	
	//Calculating Total reward points for all Customers
	@Override
	public Map<String, Integer> calculateTotalRewardPointsAllCustomer() {
		Map<String, Integer> custmerRewardTotal = new HashMap<String, Integer>();
		List<Transaction> allTransactions = transactionRepository.findAll();
		for(Transaction transaction :allTransactions) {
		Customer customer=customerRepository.findById(transaction.getCustomer().getId()).get();	
		custmerRewardTotal.put(customer.getName(), calculateTotalRewardPointsByCustomerId(transaction.getCustomer().getId()));
		}
		return custmerRewardTotal;
	}

	//Calculating Monthly reward points for all Customers
	@Override
	public Map<String, Integer> calculateMonthlyRewardPointsAllCustomer(String yearMonth) {
		List<Transaction> allTransactions = transactionRepository.findAll();
		Map<String, Integer> custmerRewardMonthly = new HashMap<String, Integer>();
		for(Transaction transaction :allTransactions) {
			Customer customer=customerRepository.findById(transaction.getCustomer().getId()).get();	
			custmerRewardMonthly.put(customer.getName()+":"+yearMonth, calculateMonthlyRewardPointsByCustomerId(transaction.getCustomer().getId(),yearMonth));
			}
		return custmerRewardMonthly;
	}

}
