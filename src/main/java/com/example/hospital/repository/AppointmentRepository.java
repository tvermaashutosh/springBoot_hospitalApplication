package com.example.hospital.repository;

import com.example.hospital.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Integer> {
}
