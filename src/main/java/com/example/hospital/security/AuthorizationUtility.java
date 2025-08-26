package com.example.hospital.security;

public interface AuthorizationUtility {
    String ADMIN = "hasRole('ADMIN')";
    String ADMIN_DOCTOR = "hasAnyRole('ADMIN,'DOCTOR')";
    String ADMIN_DOCTOR_PATIENT = "hasAnyRole('ADMIN,'DOCTOR','PATIENT')";
}
