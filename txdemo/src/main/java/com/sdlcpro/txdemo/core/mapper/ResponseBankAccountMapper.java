package com.sdlcpro.txdemo.core.mapper;

import com.sdlcpro.txdemo.core.dto.BankAccountDto;
import com.sdlcpro.txdemo.data.entity.BankAccount;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ResponseBankAccountMapper implements Function<BankAccount, BankAccountDto> {
    @Override
    public BankAccountDto apply(final BankAccount entity) {
        return entity == null ? null : new BankAccountDto(entity.getId(), entity.getAccountNumber(), entity.getAccountType(), entity.getBank(), entity.getBalance());
    }
}