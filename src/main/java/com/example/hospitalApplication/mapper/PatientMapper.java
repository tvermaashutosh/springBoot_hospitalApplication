package com.example.hospitalApplication.mapper;

import com.example.hospitalApplication.dto.PatientDto;
import com.example.hospitalApplication.enumeration.Blood;
import com.example.hospitalApplication.enumeration.Sex;
import com.example.hospitalApplication.model.AppointmentEntity;
import com.example.hospitalApplication.model.PatientEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {
    public PatientDto toDto(PatientEntity entity) {
        PatientDto dto = new PatientDto();
        BeanUtils.copyProperties(entity, dto, "sex", "blood", "insurance", "appointments");
        dto.setSex(entity.getSex().name().charAt(0));
        dto.setBlood(entity.getBlood().name());
        dto.setInsurance(entity.getInsurance() != null ? entity.getInsurance().getId() : null);
        for (AppointmentEntity i : entity.getAppointments()) dto.getAppointments().add(i.getId());
        return dto;
    }

    public PatientEntity toEntity(PatientDto dto) {
        PatientEntity entity = new PatientEntity();
        BeanUtils.copyProperties(dto, entity, "sex", "blood", "insurance", "appointments");
        entity.setSex(Sex.valueOf(dto.getSex().toString()));
        entity.setBlood(Blood.valueOf(dto.getBlood()));
        return entity;
    }
}
