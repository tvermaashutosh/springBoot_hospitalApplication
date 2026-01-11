package com.example.hospitalApplication.enumeration;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class SexConverter implements AttributeConverter<Sex, Character> {
    @Override
    public Character convertToDatabaseColumn(Sex sex) {
        return sex.getCharacter();
    }

    @Override
    public Sex convertToEntityAttribute(Character character) {
        return Sex.fromCharacter(character);
    }
}
