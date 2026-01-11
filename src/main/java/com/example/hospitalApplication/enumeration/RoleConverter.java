package com.example.hospitalApplication.enumeration;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class RoleConverter implements AttributeConverter<Role, Character> {
    @Override
    public Character convertToDatabaseColumn(Role role) {
        return role.getCharacter();
    }

    @Override
    public Role convertToEntityAttribute(Character character) {
        return Role.fromCharacter(character);
    }
}
