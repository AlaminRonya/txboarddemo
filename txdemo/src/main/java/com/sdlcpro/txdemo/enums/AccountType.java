package com.sdlcpro.txdemo.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Objects;

@Getter
@NoArgsConstructor(force = true)
public enum AccountType {
    SAVINGS(1, "SAVINGS"),
    CURRENT(2, "CURRENT"),
    FIXED_DEPOSIT(3, "FIXED_DEPOSIT"),
    LOAN(4, "LOAN"),
    UNKNOWN(0, "UNKNOWN");

    private final Integer id;
    private final String type;

    AccountType(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    public static AccountType getById(Integer id) {
        return Arrays.stream(AccountType.values())
                .filter(type -> type.getId().equals(id))
                .findAny()
                .orElse(UNKNOWN);
    }

    public static AccountType getByName(String name) {
        return Arrays.stream(AccountType.values())
                .filter(type -> type.getType().equalsIgnoreCase(name))
                .findAny()
                .orElse(UNKNOWN);
    }

    @Converter
    public static class AccountTypeConverter implements AttributeConverter<AccountType, Integer> {
        @Override
        public Integer convertToDatabaseColumn(AccountType accountType) {
            return Objects.nonNull(accountType) ? accountType.getId() : UNKNOWN.id;
        }

        @Override
        public AccountType convertToEntityAttribute(Integer id) {
            return Objects.nonNull(id) ? AccountType.getById(id) : UNKNOWN;
        }
    }
}
