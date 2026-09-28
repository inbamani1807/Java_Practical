package com.example.LoyaltyPoints.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.LoyaltyPoints.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}