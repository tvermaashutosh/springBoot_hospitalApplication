package com.example.hospital.dto;

import com.example.hospital.dto.type.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    private Integer id;
    private String username;
    private List<Role> roles = new ArrayList<>();
    private Integer patient;
    private Integer doctor;
}
