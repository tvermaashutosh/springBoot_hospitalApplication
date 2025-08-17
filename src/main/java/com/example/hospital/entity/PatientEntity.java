package com.example.hospital.entity;

import com.example.hospital.dto.type.Blood;
import com.example.hospital.dto.type.BloodConverter;
import com.example.hospital.dto.type.Sex;
import com.example.hospital.dto.type.SexConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "patient_tbl",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name", "age", "sex"})
        },
        indexes = {
                @Index(columnList = "age")
        }
)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Integer id;

    @NotBlank(message = "Patient name is required")
    private String name;

    @NotNull(message = "Patient age is required")
    @Min(value = 0, message = "Patient age must be non-negative")
    @Max(value = 150, message = "Patient age is very high")
    private Integer age;

    @NotNull(message = "Patient sex is required")
    @Convert(converter = SexConverter.class)
    @Column(columnDefinition = "CHAR(1) CHECK (sex IN ('M','F','O'))")
    private Sex sex;

    @NotNull(message = "Patient blood group is required")
    @Convert(converter = BloodConverter.class)
    @Column(columnDefinition = "VARCHAR(3) CHECK (blood IN ('A+','A-','B+','B-','AB+','AB-','O+','O-'))")
    private Blood blood;

    @OneToOne(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @ToString.Exclude
    private InsuranceEntity insurance;

    @OneToMany(mappedBy = "patient", cascade = {CascadeType.REMOVE}, orphanRemoval = true)
    @ToString.Exclude
    private List<AppointmentEntity> appointments = new ArrayList<>();

    @NotNull(message = "User mapped to this patient is required")
    @OneToOne
    @MapsId
    private UserEntity user;

    @CreationTimestamp
    @Column(name = "created", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
