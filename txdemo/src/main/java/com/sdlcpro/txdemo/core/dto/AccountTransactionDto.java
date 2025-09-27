package com.sdlcpro.txdemo.core.dto;

import com.sdlcpro.txdemo.enums.TransactionType;

import java.util.UUID;

public record AccountTransactionDto(UUID id, String accountNumber, Double amount, TransactionType transactionType, String description) {
}
