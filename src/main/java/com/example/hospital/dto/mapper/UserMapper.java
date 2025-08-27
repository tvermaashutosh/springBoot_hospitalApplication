package com.example.hospital.dto.mapper;

import com.example.hospital.dto.UserDto;
import com.example.hospital.entity.UserEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toDto(UserEntity entity) {
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(entity, dto, "patient", "doctor");
        dto.setPatient(entity.getPatient() != null ? entity.getPatient().getId() : null);
        dto.setDoctor(entity.getDoctor() != null ? entity.getDoctor().getId() : null);
        return dto;
    }
}
