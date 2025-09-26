package com.sdlcpro.txdemo.core.dto;

import com.sdlcpro.txdemo.enums.AccountType;
import com.sdlcpro.txdemo.enums.Bank;

import java.util.UUID;

public record BankAccountDto(UUID id, String accountNumber, AccountType accountType, Bank bank, Double balance) {
}