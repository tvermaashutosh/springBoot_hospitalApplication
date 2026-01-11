package com.example.hospitalApplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "insurance_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class InsuranceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false)
    private UUID id;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime creation;

    @NotBlank(message = "Insurance company is required")
    private String company;

    @NotNull(message = "Insurance validity is required")
    @Future(message = "Insurance validity must be in future")
    private LocalDate validity;

    @NotNull(message = "Insurance patient is required")
    @OneToOne(mappedBy = "insurance")
    @ToString.Exclude
    private PatientEntity patient;
}
