package com.example.hospitalApplication.mapper;

import com.example.hospitalApplication.dto.DepartmentDto;
import com.example.hospitalApplication.model.DepartmentEntity;
import com.example.hospitalApplication.model.DoctorEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {
    public DepartmentDto toDto(DepartmentEntity entity) {
        DepartmentDto dto = new DepartmentDto();
        BeanUtils.copyProperties(entity, dto, "head", "doctors");
        dto.setHead(entity.getHead() != null ? entity.getHead().getId() : null);
        for (DoctorEntity i : entity.getDoctors()) dto.getDoctors().add(i.getId());
        return dto;
    }

    public DepartmentEntity toEntity(DepartmentDto dto) {
        DepartmentEntity entity = new DepartmentEntity();
        BeanUtils.copyProperties(dto, entity, "id", "head", "doctors");
        return entity;
    }
}
