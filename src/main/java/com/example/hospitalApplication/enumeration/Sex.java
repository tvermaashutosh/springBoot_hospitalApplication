package com.example.hospitalApplication.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum Sex {
    MALE('M'),
    FEMALE('F'),
    OTHER('O');

    @Getter
    private final Character character;

    public static Sex fromCharacter(Character character) {
        return Arrays.stream(values()).filter(x -> x.getCharacter().equals(character)).findFirst().orElseThrow();
    }
}
