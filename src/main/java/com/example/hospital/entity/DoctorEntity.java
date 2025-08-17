package com.example.hospital.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctor_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DoctorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Integer id;

    @NotBlank(message = "Doctor name is required")
    private String name;

    @NotBlank(message = "Doctor specialization is required")
    private String specialization;

    @ManyToMany(mappedBy = "doctors")
    @ToString.Exclude
    private List<DepartmentEntity> departments = new ArrayList<>();

    @OneToMany(mappedBy = "doctor")
    @ToString.Exclude
    private List<AppointmentEntity> appointments = new ArrayList<>();

    @OneToOne
    @MapsId
    private UserEntity user;

    @CreationTimestamp
    @Column(name = "created", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
