package com.sdlcpro.txdemo.core.mapper;

import com.sdlcpro.txdemo.core.dto.AccountTransactionDto;
import com.sdlcpro.txdemo.data.entity.AccountTransaction;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ResponseAccountTransactionMapper implements Function<AccountTransaction, AccountTransactionDto> {
    @Override
    public AccountTransactionDto apply(AccountTransaction entity) {
        return entity == null ? null : new AccountTransactionDto(entity.getId(), entity.getBankAccount() == null ? null : entity.getBankAccount().getAccountNumber(),
                entity.getAmount(), entity.getTransactionType(), entity.getDescription());
    }

}