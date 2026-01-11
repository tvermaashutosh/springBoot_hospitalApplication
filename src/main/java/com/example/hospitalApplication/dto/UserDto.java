package com.example.hospitalApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    private UUID id;
    private String username;
    private List<Character> roles = new ArrayList<>();
    private UUID patient;
    private UUID doctor;
}
