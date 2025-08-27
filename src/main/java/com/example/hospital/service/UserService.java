package com.example.hospital.service;

import com.example.hospital.dto.*;
import com.example.hospital.dto.mapper.DoctorMapper;
import com.example.hospital.dto.mapper.PatientMapper;
import com.example.hospital.dto.mapper.UserMapper;
import com.example.hospital.entity.DoctorEntity;
import com.example.hospital.entity.PatientEntity;
import com.example.hospital.entity.UserEntity;
import com.example.hospital.dto.type.Role;
import com.example.hospital.repository.DoctorRepository;
import com.example.hospital.repository.PatientRepository;
import com.example.hospital.repository.UserRepository;
import com.example.hospital.security.AuthenticationUtility;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final AuthenticationManager manager;
    private final AuthenticationUtility utility;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;
    private final DoctorMapper doctorMapper;
    private final PatientMapper patientMapper;

    private UserEntity getUserEntity(String username, Role role) {
        UserEntity user = userRepository.findByUsername(username).orElseThrow();
        if (user.getRoles().contains(role))
            throw new RuntimeException("User is already a " + role.name().toLowerCase());
        return user;
    }

    public SignupSigninResponseDto signup(SignupSigninRequestDto request) {
        UserEntity user = userRepository.findByUsername(request.getUsername()).orElse(null);

        if (user != null) throw new RuntimeException("User already exists");

        UserEntity newUser = UserEntity.builder().username(request.getUsername()).build();
        newUser.setPassword(encoder.encode(request.getPassword()));
        userRepository.save(newUser);

        return signin(request);
    }

    public SignupSigninResponseDto signin(SignupSigninRequestDto request) {
        try {
            Authentication authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUsername(), request.getPassword()));

            UserEntity user = (UserEntity) authentication.getPrincipal();

            String token = utility.generateAccessToken(user);

            return new SignupSigninResponseDto(request.getUsername(), token, user.getRoles());
        } catch (Exception e) {
            throw new BadCredentialsException(e.getMessage());
        }
    }

    public List<UserDto> read() {
        return userRepository.findAll().stream().map(x -> userMapper.toDto(x)).toList();
    }

    public UserDto read(Integer id) {
        UserEntity user = userRepository.findById(id).orElseThrow();

        return userMapper.toDto(user);
    }

    @Transactional
    public DoctorDto addRoleDoctor(String username, DoctorDto doctor) {
        UserEntity user = getUserEntity(username, Role.DOCTOR);
        user.getRoles().add(Role.DOCTOR);

        DoctorEntity newDoctor = doctorMapper.toEntity(doctor);
        newDoctor.setUser(user);

        return doctorMapper.toDto(doctorRepository.save(newDoctor));
    }

    @Transactional
    public PatientDto addRolePatient(String username, PatientDto patient) {
        UserEntity user = getUserEntity(username, Role.PATIENT);
        user.getRoles().add(Role.PATIENT);

        PatientEntity newPatient = patientMapper.toEntity(patient);
        newPatient.setUser(user);

        return patientMapper.toDto(patientRepository.save(newPatient));
    }

    public UserDto delete(Integer id) {
        UserEntity user = userRepository.findById(id).orElseThrow();

        userRepository.deleteById(id);

        return userMapper.toDto(user);
    }
}
