package com.example.hospitalApplication.controller;

import static com.example.hospitalApplication.security.AuthorizationConst.*;

import com.example.hospitalApplication.dto.*;
import com.example.hospitalApplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping("/signup")
    public ResponseEntity<SignupSigninResponseDto> signup(@RequestBody SignupSigninRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<SignupSigninResponseDto> signin(@RequestBody SignupSigninRequestDto request) {
        return ResponseEntity.status(HttpStatus.OK).body(service.signin(request));
    }

    @PreAuthorize(ADMIN)
    @GetMapping("/read")
    public ResponseEntity<List<UserDto>> read() {
        return ResponseEntity.status(HttpStatus.OK).body(service.read());
    }

    @PreAuthorize(ADMIN)
    @GetMapping("/read/{id}")
    public ResponseEntity<UserDto> read(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.read(id));
    }

    @RestController
    @RequestMapping("/user/addRole")
    @RequiredArgsConstructor
    public static class UserAddRoleController {
        private final UserService service;

        @PreAuthorize(ADMIN)
        @PostMapping("/doctor/{id}")
        public ResponseEntity<DoctorDto> addRoleDoctor(@PathVariable Integer id, @RequestBody DoctorDto doctor) {
            return ResponseEntity.status(HttpStatus.OK).body(service.addRoleDoctor(id, doctor));
        }

        @PreAuthorize(ADMIN)
        @PostMapping("/patient/{id}")
        public ResponseEntity<PatientDto> addRolePatient(@PathVariable Integer id, @RequestBody PatientDto patient) {
            return ResponseEntity.status(HttpStatus.OK).body(service.addRolePatient(id, patient));
        }
    }

    @PreAuthorize(ADMIN)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserDto> delete(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
    }
}
