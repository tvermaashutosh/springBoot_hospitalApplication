package com.example.hospitalApplication.model;

import com.example.hospitalApplication.enumeration.Role;
import com.example.hospitalApplication.enumeration.RoleConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "user_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false)
    private UUID id;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime creation;

    @NotBlank(message = "User username is required")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "User password is required")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Convert(converter = RoleConverter.class)
    @CollectionTable(name = "user_role_tbl",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "role", columnDefinition = "CHAR(1) CHECK (role IN ('A','D','P'))")
    @Builder.Default
    List<Role> roles = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = {CascadeType.REMOVE}, orphanRemoval = true)
    private PatientEntity patient;

    @OneToOne(mappedBy = "user", cascade = {CascadeType.REMOVE}, orphanRemoval = true)
    private DoctorEntity doctor;

    @Override
    public Collection<SimpleGrantedAuthority> getAuthorities() {
        return roles.stream().map(x -> new SimpleGrantedAuthority("ROLE_" + x.name()))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
