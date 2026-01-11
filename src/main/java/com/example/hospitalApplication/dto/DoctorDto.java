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
public class DoctorDto {
    private UUID id;
    private String name;
    private String specialization;
    private UUID headed;
    private List<UUID> departments = new ArrayList<>();
    private List<UUID> appointments = new ArrayList<>();
}
