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
public class SignupSigninResponseDto {
    private String username;
    private String token;
    private List<Role> roles = new ArrayList<>();
}
