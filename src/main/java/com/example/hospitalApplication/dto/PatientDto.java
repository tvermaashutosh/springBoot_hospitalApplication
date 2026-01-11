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
public class PatientDto {
    private UUID id;
    private String name;
    private Integer age;
    private Character sex;
    private String blood;
    private UUID insurance;
    private List<UUID> appointments = new ArrayList<>();
}
