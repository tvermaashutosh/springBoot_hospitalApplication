package com.example.hospitalApplication.repository;

import com.example.hospitalApplication.model.AppointmentEntity;
import com.example.hospitalApplication.model.DoctorEntity;
import com.example.hospitalApplication.model.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Integer> {
    List<AppointmentEntity> findByDoctor(DoctorEntity doctor);

    List<AppointmentEntity> findByPatient(PatientEntity patient);
}
