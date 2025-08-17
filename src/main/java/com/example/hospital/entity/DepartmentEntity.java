package com.example.hospital.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
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

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime creation;

    @NotBlank(message = "Department name is required")
    @Column(unique = true)
    private String name;

    @OneToOne
    @JoinColumn(unique = true)
    @ToString.Exclude
    private DoctorEntity head;

    @ManyToMany
    @JoinTable(name = "department_doctor_tbl",
            joinColumns = @JoinColumn(name = "department_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_user_id")
    )
    @Builder.Default
    @ToString.Exclude
    private List<DoctorEntity> doctors = new ArrayList<>();
}
