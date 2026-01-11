package com.example.hospitalApplication.mapper;

import com.example.hospitalApplication.dto.SignupSigninResponseDto;
import com.example.hospitalApplication.dto.UserDto;
import com.example.hospitalApplication.enumeration.Role;
import com.example.hospitalApplication.model.UserEntity;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toDto(UserEntity entity) {
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(entity, dto, "roles", "patient", "doctor");
        for (Role i : entity.getRoles()) dto.getRoles().add(i.name().charAt(0));
        dto.setPatient(entity.getPatient() != null ? entity.getPatient().getId() : null);
        dto.setDoctor(entity.getDoctor() != null ? entity.getDoctor().getId() : null);
        return dto;
    }

    public SignupSigninResponseDto toDto(UserEntity entity, String token) {
        SignupSigninResponseDto dto = new SignupSigninResponseDto();
        BeanUtils.copyProperties(entity, dto, "roles", "patient", "doctor");
        for (Role i : entity.getRoles()) dto.getRoles().add(i.name().charAt(0));
        dto.setPatient(entity.getPatient() != null ? entity.getPatient().getId() : null);
        dto.setDoctor(entity.getDoctor() != null ? entity.getDoctor().getId() : null);
        dto.setToken(token);
        return dto;
    }
}
