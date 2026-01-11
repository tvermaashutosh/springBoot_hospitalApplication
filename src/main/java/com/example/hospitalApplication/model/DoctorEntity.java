package com.example.hospitalApplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "doctor_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DoctorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false)
    private UUID id;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime creation;

    @NotNull(message = "User mapped by this doctor is required")
    @OneToOne
    @JoinColumn(unique = true)
    @MapsId
    @ToString.Exclude
    private UserEntity user;

    @NotBlank(message = "Doctor name is required")
    private String name;

    @NotBlank(message = "Doctor specialization is required")
    private String specialization;

    @OneToOne(mappedBy = "head")
    @ToString.Exclude
    private DepartmentEntity headed;

    @ManyToMany(mappedBy = "doctors")
    @Builder.Default
    @ToString.Exclude
    private List<DepartmentEntity> departments = new ArrayList<>();

    @OneToMany(mappedBy = "doctor")
    @Builder.Default
    @ToString.Exclude
    private List<AppointmentEntity> appointments = new ArrayList<>();
}
