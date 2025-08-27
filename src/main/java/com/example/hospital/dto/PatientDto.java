package com.example.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PatientDto {
    private Integer id;
    private String name;
    private Integer age;
    private String sex;
    private String blood;
    private Integer insurance;
    private List<Integer> appointments = new ArrayList<>();
}
