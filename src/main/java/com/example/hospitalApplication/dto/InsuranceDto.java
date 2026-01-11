package com.example.hospitalApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InsuranceDto {
    private UUID id;
    private String company;
    private LocalDate validity;
    private UUID patient;
}
