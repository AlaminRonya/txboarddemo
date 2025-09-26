package com.sdlcpro.txdemo.core.param;

import com.sdlcpro.txdemo.enums.AccountType;
import com.sdlcpro.txdemo.enums.Bank;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record BankAccountParam (

        @Schema(description = "Settlement of Bank of the Bank-name", allowableValues = {"SONALI", "AGRANI", "RUPALI", "JANATA", "ISLAMI"})
        Bank bank,

        @Schema(description = "Settlement of Account of the Account-type", allowableValues = {"SAVINGS", "CURRENT", "FIXED_DEPOSIT", "LOAN"})
        AccountType accountType,

        Double balance,

        UUID userInfoId
){
}