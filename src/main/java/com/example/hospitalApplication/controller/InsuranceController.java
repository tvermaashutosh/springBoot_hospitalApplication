package com.example.hospitalApplication.controller;

import com.example.hospitalApplication.dto.InsuranceDto;

import static com.example.hospitalApplication.security.AuthorizationConst.*;

import com.example.hospitalApplication.model.UserEntity;
import com.example.hospitalApplication.service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/insurance")
@RequiredArgsConstructor
public class InsuranceController {
    private final InsuranceService service;

    @PostMapping("/create")
    public ResponseEntity<InsuranceDto> create(@RequestBody InsuranceDto insurance) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create());
    }

    @PreAuthorize(ADMIN_PATIENT)
    public ResponseEntity<List<InsuranceDto>> read(@AuthenticationPrincipal UserEntity user) {
        return null;//ResponseEntity.status(HttpStatus.OK).body(service.read(user));
    }

    public ResponseEntity<InsuranceDto> read(@PathVariable Integer id) {
        return null;
    }

    public InsuranceDto update(@RequestBody InsuranceDto insurance) {
        return null;
    }

    public InsuranceDto delete(@PathVariable Integer id) {
        return null;
    }
}
