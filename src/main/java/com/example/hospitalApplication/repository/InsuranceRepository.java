package com.example.hospitalApplication.repository;

import com.example.hospitalApplication.model.InsuranceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceRepository extends JpaRepository<InsuranceEntity, Integer> {
}
