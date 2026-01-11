package com.example.hospitalApplication.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum Role {
    ADMIN('A'),
    DOCTOR('D'),
    PATIENT('P');

    @Getter
    private final Character character;

    public static Role fromCharacter(Character character) {
        return Arrays.stream(values()).filter(x -> x.getCharacter().equals(character)).findFirst().orElseThrow();
    }
}
