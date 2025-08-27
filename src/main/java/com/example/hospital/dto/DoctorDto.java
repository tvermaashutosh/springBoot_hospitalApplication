package com.example.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DoctorDto {
    private Integer id;
    private String name;
    private String specialization;
    private Integer headed;
    private List<Integer> departments = new ArrayList<>();
    private List<Integer> appointments = new ArrayList<>();
}
