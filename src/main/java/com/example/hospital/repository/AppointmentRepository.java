package com.example.hospital.repository;

import com.example.hospital.entity.AppointmentEntity;
import com.example.hospital.entity.DoctorEntity;
import com.example.hospital.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Integer> {
    List<AppointmentEntity> findByDoctor(DoctorEntity doctor);

    List<AppointmentEntity> findByPatient(PatientEntity patient);
}
