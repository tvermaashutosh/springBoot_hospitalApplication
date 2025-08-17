package com.example.hospital.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "insurance_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class InsuranceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Integer id;

    @NotBlank(message = "Insurance company is required")
    private String company;

    @NotNull(message = "Insurance validity is required")
    @Future(message = "Insurance validity must be in future")
    private LocalDate validity;

    @OneToOne(mappedBy = "insurance")
    @ToString.Exclude
    private PatientEntity patient;

    @CreationTimestamp
    @Column(name = "created", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
