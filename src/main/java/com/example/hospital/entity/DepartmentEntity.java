package com.example.hospital.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "department_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Integer id;

    @NotBlank(message = "Department name is required")
    @Column(unique = true)
    private String name;

    @OneToOne
    private DoctorEntity head;

    @ManyToMany
    private List<DoctorEntity> doctors = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
