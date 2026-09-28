package com.example.LoyaltyPoints.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.LoyaltyPoints.model.Tier;

public interface TierRepository extends JpaRepository<Tier, Long> {

    Optional<Tier> findByName(String name);
}