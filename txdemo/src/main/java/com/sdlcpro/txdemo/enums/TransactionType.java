package com.sdlcpro.txdemo.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Objects;

@Getter
@NoArgsConstructor(force = true)
public enum TransactionType {
    CREDIT(1, "CREDIT"),
    DEBIT(2, "DEBIT"),
    TRANSFER(3, "TRANSFER"),
    UNKNOWN(0, "UNKNOWN");

    private final Integer id;
    private final String type;

    TransactionType(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    public static TransactionType getById(Integer id) {
        return Arrays.stream(TransactionType.values())
                .filter(type -> type.getId().equals(id))
                .findAny()
                .orElse(UNKNOWN);
    }

    public static TransactionType getByName(String name) {
        return Arrays.stream(TransactionType.values())
                .filter(type -> type.getType().equalsIgnoreCase(name))
                .findAny()
                .orElse(UNKNOWN);
    }

    @Converter
    public static class TransactionTypeConverter implements AttributeConverter<TransactionType, Integer> {
        @Override
        public Integer convertToDatabaseColumn(TransactionType transactionType) {
            return Objects.nonNull(transactionType) ? transactionType.getId() : UNKNOWN.id;
        }

        @Override
        public TransactionType convertToEntityAttribute(Integer id) {
            return Objects.nonNull(id) ? TransactionType.getById(id) : UNKNOWN;
        }
    }
}
