package com.finanace.tractor_finance_system_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finanace.tractor_finance_system_backend.model.Farmer;

public interface FarmerRepository extends JpaRepository<Farmer, Long> {
}
