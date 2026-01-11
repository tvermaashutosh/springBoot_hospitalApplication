package com.example.hospitalApplication.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorizationConst {
    public static final String ADMIN = "hasRole('ADMIN')";
    public static final String DOCTOR = "hasRole('DOCTOR')";
    public static final String PATIENT = "hasRole('PATIENT')";
    public static final String ADMIN_DOCTOR = "hasAnyRole('ADMIN,'DOCTOR')";
    public static final String ADMIN_PATIENT = "hasAnyRole('ADMIN','PATIENT')";
    public static final String ADMIN_DOCTOR_PATIENT = "hasAnyRole('ADMIN','DOCTOR','PATIENT')";
}
