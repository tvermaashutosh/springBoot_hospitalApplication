package com.example.hospitalApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppointmentDto {
    private UUID id;
    private LocalDateTime time;
    private UUID patient;
    private UUID doctor;
}
