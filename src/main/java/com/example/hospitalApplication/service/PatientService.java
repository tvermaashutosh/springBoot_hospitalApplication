package com.example.hospitalApplication.service;

import com.example.hospitalApplication.dto.PatientDto;
import com.example.hospitalApplication.mapper.PatientMapper;
import com.example.hospitalApplication.model.PatientEntity;
import com.example.hospitalApplication.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository repository;
    private final PatientMapper mapper;

    public PatientDto create(PatientDto patient) {
        PatientEntity newPatient = repository.save(mapper.toEntity(patient));

        return mapper.toDto(newPatient);
    }

    public List<PatientDto> read() {
        return repository.findAll().stream().map(x -> mapper.toDto(x)).toList();
    }

    public PatientDto read(Integer id) {
        return mapper.toDto(repository.findById(id).orElseThrow());
    }

    public PatientDto delete(Integer id) {
        PatientEntity patient = repository.findById(id).orElseThrow();

        repository.deleteById(id);
        
        return mapper.toDto(patient);
    }
}
