package com.example.hospitalApplication.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum Blood {
    A_POSITIVE("A+"),
    A_NEGATIVE("A-"),
    B_POSITIVE("B+"),
    B_NEGATIVE("B-"),
    AB_POSITIVE("AB+"),
    AB_NEGATIVE("AB-"),
    O_POSITIVE("O+"),
    O_NEGATIVE("O-");

    @Getter
    private final String string;

    public static Blood fromString(String string) {
        return Arrays.stream(values()).filter(x -> x.getString().equals(string)).findFirst().orElseThrow();
    }
}
