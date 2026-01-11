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
public class DepartmentDto {
    private UUID id;
    private String name;
    private UUID head;
    private List<UUID> doctors = new ArrayList<>();
}
