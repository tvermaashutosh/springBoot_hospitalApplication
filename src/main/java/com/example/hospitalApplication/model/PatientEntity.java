package com.example.hospitalApplication.model;

import com.example.hospitalApplication.enumeration.Blood;
import com.example.hospitalApplication.enumeration.BloodConverter;
import com.example.hospitalApplication.enumeration.Sex;
import com.example.hospitalApplication.enumeration.SexConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "patient_tbl",
        uniqueConstraints = {
                @UniqueConstraint(name = "patientUniqueConstraint", columnNames = {"name", "age", "sex", "blood"})
        },
        indexes = {
                @Index(name = "ageIndex", columnList = "age")
        }
)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false)
    private UUID id;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime creation;

    @NotNull(message = "User mapped by this patient is required")
    @OneToOne
    @JoinColumn(unique = true)
    @MapsId
    private UserEntity user;

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
    @Builder.Default
    @ToString.Exclude
    private List<AppointmentEntity> appointments = new ArrayList<>();
}
