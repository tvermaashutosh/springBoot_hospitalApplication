package com.example.hospital.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointment_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Integer id;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime creation;

    @NotNull(message = "Appointment time is required")
    private LocalDateTime time;

    @NotNull(message = "Appointment patient is required")
    @ManyToOne
    @ToString.Exclude
    private PatientEntity patient;

    @NotNull(message = "Appointment doctor is required")
    @ManyToOne
    @ToString.Exclude
    private DoctorEntity doctor;
}
