package com.example.hospitalApplication.controller;

import com.example.hospitalApplication.dto.PatientDto;

import static com.example.hospitalApplication.security.AuthorizationConst.*;

import com.example.hospitalApplication.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService service;

    @PreAuthorize(ADMIN)
    @PostMapping("/create")
    public ResponseEntity<PatientDto> create(@RequestBody PatientDto patient) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(patient));
    }

    @PreAuthorize(ADMIN)
    @GetMapping("/read")
    public ResponseEntity<List<PatientDto>> read() {
        return ResponseEntity.status(HttpStatus.OK).body(service.read());
    }

    @PreAuthorize(ADMIN_DOCTOR + " OR (hasRole('PATIENT') AND #id==authentication.principal.id)")
    @GetMapping("/read/{id}")
    public ResponseEntity<PatientDto> read(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.read(id));
    }

    @PreAuthorize(ADMIN)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PatientDto> delete(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
    }
}
