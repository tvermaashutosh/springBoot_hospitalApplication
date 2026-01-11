package com.example.hospitalApplication.mapper;

import com.example.hospitalApplication.dto.AppointmentDto;
import com.example.hospitalApplication.model.AppointmentEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {
    public AppointmentDto toDto(AppointmentEntity entity) {
        AppointmentDto dto = new AppointmentDto();
        BeanUtils.copyProperties(entity, dto, "patient", "doctor");
        dto.setPatient(entity.getPatient() != null ? entity.getPatient().getId() : null);
        dto.setDoctor(entity.getDoctor() != null ? entity.getDoctor().getId() : null);
        return dto;
    }

    public AppointmentEntity toEntity(AppointmentDto dto) {
        AppointmentEntity entity = new AppointmentEntity();
        BeanUtils.copyProperties(dto, entity, "patient", "doctor");
        return null;
    }
}
