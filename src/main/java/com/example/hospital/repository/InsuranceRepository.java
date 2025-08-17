package com.example.hospital.repository;

import com.example.hospital.entity.InsuranceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceRepository extends JpaRepository<InsuranceEntity, Integer> {
}
