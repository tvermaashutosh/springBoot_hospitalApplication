package com.example.hospital.entity;

import com.example.hospital.dto.type.Role;
import com.example.hospital.dto.type.RoleConverter;
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
import java.util.stream.Collectors;

@Entity
@Table(name = "user_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Integer id;

    @NotBlank(message = "User username is required")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "User password is required")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Convert(converter = RoleConverter.class)
    @Column(columnDefinition = "CHAR(1) CHECK (roles IN ('A','D','P'))")
    List<Role> roles = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Override
    public Collection<SimpleGrantedAuthority> getAuthorities() {
        return roles.stream().map(x -> new SimpleGrantedAuthority("ROLE_" + x.name())).collect(Collectors.toCollection(ArrayList::new));
    }
}
