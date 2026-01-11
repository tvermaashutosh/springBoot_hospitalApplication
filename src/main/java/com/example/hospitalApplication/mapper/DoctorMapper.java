package com.example.hospitalApplication.mapper;

import com.example.hospitalApplication.dto.DoctorDto;
import com.example.hospitalApplication.model.AppointmentEntity;
import com.example.hospitalApplication.model.DepartmentEntity;
import com.example.hospitalApplication.model.DoctorEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {
    public DoctorDto toDto(DoctorEntity entity) {
        DoctorDto dto = new DoctorDto();
        BeanUtils.copyProperties(entity, dto, "headed", "departments", "appointments");
        dto.setHeaded(entity.getHeaded() != null ? entity.getHeaded().getId() : null);
        for (DepartmentEntity i : entity.getDepartments()) dto.getDepartments().add(i.getId());
        for (AppointmentEntity i : entity.getAppointments()) dto.getAppointments().add(i.getId());
        return dto;
    }

    public DoctorEntity toEntity(DoctorDto dto) {
        DoctorEntity entity = new DoctorEntity();
        BeanUtils.copyProperties(dto, entity, "headed", "departments", "appointments");
        return entity;
    }
}
