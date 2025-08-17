package com.example.hospital.dto.type;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class BloodConverter implements AttributeConverter<Blood, String> {
    @Override
    public String convertToDatabaseColumn(Blood blood) {
        return blood.getString();
    }

    @Override
    public Blood convertToEntityAttribute(String string) {
        return Blood.fromString(string);
    }
}
