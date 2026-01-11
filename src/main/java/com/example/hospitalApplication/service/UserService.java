package com.example.hospitalApplication.service;

import com.example.hospitalApplication.dto.*;
import com.example.hospitalApplication.mapper.DoctorMapper;
import com.example.hospitalApplication.mapper.PatientMapper;
import com.example.hospitalApplication.mapper.UserMapper;
import com.example.hospitalApplication.model.DoctorEntity;
import com.example.hospitalApplication.model.PatientEntity;
import com.example.hospitalApplication.model.UserEntity;
import com.example.hospitalApplication.enumeration.Role;
import com.example.hospitalApplication.repository.DoctorRepository;
import com.example.hospitalApplication.repository.PatientRepository;
import com.example.hospitalApplication.repository.UserRepository;
import com.example.hospitalApplication.security.JwtUtil;
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
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final DoctorMapper doctorMapper;
    private final PatientMapper patientMapper;

    public SignupSigninResponseDto signup(SignupSigninRequestDto dto) {
        UserEntity entity = UserEntity.builder().username(dto.getUsername()).build();
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));

        userRepository.save(entity);

        return signin(dto);
    }

    public SignupSigninResponseDto signin(SignupSigninRequestDto request) {
        try {
            UsernamePasswordAuthenticationToken upaToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());

            Authentication authentication = authenticationManager.authenticate(upaToken);

            UserEntity entity = (UserEntity) authentication.getPrincipal();

            String token = jwtUtil.generateAccessToken(entity);

            return userMapper.toDto(entity, token);
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
    public DoctorDto addRoleDoctor(Integer id, DoctorDto doctor) {
        UserEntity user = getValidUserEntity(id, Role.DOCTOR);
        user.getRoles().add(Role.DOCTOR);

        DoctorEntity newDoctor = doctorMapper.toEntity(doctor);
        newDoctor.setUser(user);

        return doctorMapper.toDto(doctorRepository.save(newDoctor));
    }

    @Transactional
    public PatientDto addRolePatient(Integer id, PatientDto patient) {
        UserEntity user = getValidUserEntity(id, Role.PATIENT);
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

    private UserEntity getValidUserEntity(Integer id, Role role) {
        UserEntity user = userRepository.findById(id).orElseThrow();

        if (user.getRoles().contains(role))
            throw new RuntimeException("User is already a " + role.name().toLowerCase());

        return user;
    }
}
