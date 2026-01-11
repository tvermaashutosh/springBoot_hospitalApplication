package com.example.hospitalApplication.service;

import com.example.hospitalApplication.dto.DepartmentDto;
import com.example.hospitalApplication.mapper.DepartmentMapper;
import com.example.hospitalApplication.model.DepartmentEntity;
import com.example.hospitalApplication.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository repository;
    private final DepartmentMapper mapper;

    public DepartmentDto create(DepartmentDto dto) {
        DepartmentEntity entity = mapper.toEntity(dto);
        repository.save(entity);

        return mapper.toDto(entity);
    }

    public List<DepartmentDto> read() {
        return repository.findAll().stream().map(x -> mapper.toDto(x)).toList();
    }

    public DepartmentDto read(Integer id) {
        return mapper.toDto(repository.findById(id).orElseThrow());
    }


}
