package com.customer.reward.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customer.reward.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
