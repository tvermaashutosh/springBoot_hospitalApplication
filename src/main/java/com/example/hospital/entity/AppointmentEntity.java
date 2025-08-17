package com.example.hospital.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @NotNull(message = "Appointment time is required")
    private LocalDateTime time;

    @NotNull(message = "Appointment patient is required")
    @ManyToOne
    private PatientEntity patient;

    @NotNull(message = "Appointment doctor is required")
    @ManyToOne
    private DoctorEntity doctor;

    @CreationTimestamp
    @Column(name = "created", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
