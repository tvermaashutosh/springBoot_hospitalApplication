package com.example.hospital.dto.mapper;

import com.example.hospital.dto.PatientDto;
import com.example.hospital.dto.type.Blood;
import com.example.hospital.dto.type.Sex;
import com.example.hospital.entity.AppointmentEntity;
import com.example.hospital.entity.PatientEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {
    public PatientDto toDto(PatientEntity entity) {
        PatientDto dto = new PatientDto();
        BeanUtils.copyProperties(entity, dto, "sex", "blood", "insurance", "appointments");
        dto.setSex(entity.getSex().name());
        dto.setBlood(entity.getBlood().name());
        dto.setInsurance(entity.getInsurance() != null ? entity.getInsurance().getId() : null);
        for (AppointmentEntity i : entity.getAppointments()) dto.getAppointments().add(i.getId());
        return dto;
    }

    public PatientEntity toEntity(PatientDto dto) {
        PatientEntity entity = new PatientEntity();
        BeanUtils.copyProperties(dto, entity, "sex", "blood", "insurance", "appointments");
        entity.setSex(Sex.valueOf(dto.getSex()));
        entity.setBlood(Blood.valueOf(dto.getBlood()));
        return entity;
    }
}
