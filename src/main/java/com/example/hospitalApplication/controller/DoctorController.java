package com.example.hospitalApplication.controller;

import com.example.hospitalApplication.dto.DoctorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class DoctorController {
    public ResponseEntity<DoctorDto> create(@RequestBody DoctorDto doctor) {
        return null;
    }
}
