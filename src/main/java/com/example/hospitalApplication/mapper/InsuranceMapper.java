package com.example.hospitalApplication.mapper;

import com.example.hospitalApplication.dto.InsuranceDto;
import com.example.hospitalApplication.model.InsuranceEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class InsuranceMapper {
    public InsuranceDto toDto(InsuranceEntity entity) {
        InsuranceDto dto = new InsuranceDto();
        BeanUtils.copyProperties(entity, dto, "patient");
        dto.setPatient(entity.getPatient().getId());
        return dto;
    }

    public InsuranceEntity toEntity(InsuranceDto dto) {
        InsuranceEntity entity = new InsuranceEntity();
        BeanUtils.copyProperties(dto, entity, "patient");
        return entity;
    }
}
