//package com.example.hospital.controller;
//
//import com.example.hospital.dto.AppointmentDto;
//import com.example.hospital.entity.UserEntity;
//
//import static com.example.hospital.security.AuthorizationUtility.*;
//
//import com.example.hospital.service.AppointmentService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/appointment")
//@RequiredArgsConstructor
//public class AppointmentController {
//    private final AppointmentService service;
//
//    @PreAuthorize(ADMIN_DOCTOR)
//    @PostMapping("/create")
//    public ResponseEntity<AppointmentDto> create(@RequestBody AppointmentDto appointment) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(appointment));
//    }
//
//    @PreAuthorize(ADMIN_DOCTOR_PATIENT)
//    @GetMapping("/read")
//    public ResponseEntity<List<AppointmentDto>> read(@AuthenticationPrincipal UserEntity user) {
//        return ResponseEntity.status(HttpStatus.OK).body(service.read(user));
//    }
//
//    @PreAuthorize(ADMIN_DOCTOR_PATIENT)
//    @GetMapping("/read/{id}")
//    public ResponseEntity<AppointmentDto> read(@AuthenticationPrincipal UserEntity user, @PathVariable Integer id) {
//        return ResponseEntity.status(HttpStatus.OK).body(service.read(user, id));
//    }
//
//    @PreAuthorize(ADMIN_DOCTOR)
//    @PutMapping("/update")
//    public ResponseEntity<AppointmentDto> update(@RequestBody AppointmentDto appointment) {
//        return ResponseEntity.status(HttpStatus.OK).body(service.update(appointment));
//    }
//
//    @PreAuthorize(ADMIN_DOCTOR)
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<AppointmentDto> delete(@PathVariable Integer id) {
//        return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
//    }
//}
