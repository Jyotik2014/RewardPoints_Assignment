package com.customer.reward.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customer.reward.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
