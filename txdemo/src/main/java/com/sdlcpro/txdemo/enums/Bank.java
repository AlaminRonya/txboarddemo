package com.sdlcpro.txdemo.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Objects;

@Getter
@NoArgsConstructor(force = true)
public enum Bank {
    SONALI(1, "SONALI"),
    AGRANI(2, "AGRANI"),
    RUPALI(3, "RUPALI"),
    JANATA(4, "JANATA"),
    ISLAMI(5, "ISLAMI"),
    UNKNOWN(0, "UNKNOWN");

    private final Integer id;
    private final String type;

    Bank(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    public static Bank getById(Integer id) {
        return Arrays.stream(Bank.values())
                .filter(type -> type.getId().equals(id))
                .findAny()
                .orElse(UNKNOWN);
    }

    public static Bank getByName(String name) {
        return Arrays.stream(Bank.values())
                .filter(type -> type.getType().equalsIgnoreCase(name))
                .findAny()
                .orElse(UNKNOWN);
    }

    @Converter
    public static class BankConverter implements AttributeConverter<Bank, Integer> {
        @Override
        public Integer convertToDatabaseColumn(Bank bank) {
            return Objects.nonNull(bank) ? bank.getId() : UNKNOWN.id;
        }

        @Override
        public Bank convertToEntityAttribute(Integer id) {
            return Objects.nonNull(id) ? Bank.getById(id) : UNKNOWN;
        }
    }
}
