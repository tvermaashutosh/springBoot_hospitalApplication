package com.example.hospitalApplication.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class SignupSigninResponseDto extends UserDto {
    private String token;

    public SignupSigninResponseDto(UUID id, String username, List<Character> roles, UUID patient, UUID doctor, String token) {
        super(id, username, roles, patient, doctor);
        this.token = token;
    }
}
