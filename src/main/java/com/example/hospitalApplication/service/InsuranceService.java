package com.example.hospitalApplication.service;

import com.example.hospitalApplication.dto.InsuranceDto;
import com.example.hospitalApplication.mapper.InsuranceMapper;
import com.example.hospitalApplication.mapper.PatientMapper;
import com.example.hospitalApplication.model.InsuranceEntity;
import com.example.hospitalApplication.model.PatientEntity;
import com.example.hospitalApplication.model.UserEntity;
import com.example.hospitalApplication.repository.InsuranceRepository;
import com.example.hospitalApplication.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {
    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;
    private final InsuranceMapper insuranceMapper;
    private final PatientMapper patientMapper;

    @Transactional
    public PatientEntity associateWithPatient(InsuranceDto insuranceDto, Integer patientId) {
        InsuranceEntity insurance = insuranceMapper.toEntity(insuranceDto);
        PatientEntity patient = patientRepository.findById(patientId).orElseThrow();

        patient.setInsurance(insurance);
        insurance.setPatient(patient);

        return patient;
    }

    @Transactional
    public PatientEntity disassociateFromPatient(Integer patiendId) {
        PatientEntity patient = patientRepository.findById(patiendId).orElseThrow();

        patient.setInsurance(null);

        return patient;
    }

    public InsuranceDto create() {
        return null;
    }

    public InsuranceDto read(UserEntity user) {
        return null;
    }
}
