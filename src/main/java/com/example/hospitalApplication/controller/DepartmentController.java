package com.example.hospitalApplication.controller;

import com.example.hospitalApplication.dto.DepartmentDto;

import static com.example.hospitalApplication.security.AuthorizationConst.*;

import com.example.hospitalApplication.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {
    public final DepartmentService service;

    @PreAuthorize(ADMIN)
    @PostMapping("/create")
    public ResponseEntity<DepartmentDto> create(@RequestBody DepartmentDto department) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(department));
    }

    @GetMapping("/read")
    public ResponseEntity<List<DepartmentDto>> read() {
        return ResponseEntity.status(HttpStatus.OK).body(service.read());
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<DepartmentDto> read(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.read(id));
    }

    @PreAuthorize(ADMIN)
    @PutMapping("/update")
    public ResponseEntity<DepartmentDto> update() {
        return null;
    }

    @PreAuthorize(ADMIN)
    @GetMapping("/updateHead/{departmentId}/{doctorId}")
    public ResponseEntity<DepartmentDto> updateHead(@PathVariable Integer departmentId, @PathVariable Integer doctorId) {
        return null;
    }

    @PreAuthorize(ADMIN + " OR (" + DOCTOR + " AND #doctorId.equals(authentication.principal.id))")
    @GetMapping("/addDoctor/{departmentId}/{doctorId}")
    public ResponseEntity<DepartmentDto> addDoctor(@PathVariable Integer departmentId, @PathVariable Integer doctorId) {
        return null;
    }

    @PreAuthorize(ADMIN + " OR (" + DOCTOR + " AND #doctorId==authentication.principal.id)")
    @GetMapping("/removeDoctor/{departmentId}/{doctorId}")
    public ResponseEntity<DepartmentDto> removeDoctor(@PathVariable Integer departmentId, @PathVariable Integer doctorId) {
        return null;
    }

    @PreAuthorize(ADMIN)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DepartmentDto> delete(@PathVariable Integer id) {
        return null;
    }
}
