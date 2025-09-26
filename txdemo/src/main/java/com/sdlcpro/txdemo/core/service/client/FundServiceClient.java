package com.sdlcpro.txdemo.core.service.client;


import com.sdlcpro.txdemo.data.entity.BankAccount;

public interface FundServiceClient {

    void credit(final String accountNumber, final Double amount);

    void debit(final String accountNumber, final Double amount);

    void transferFunds(final String fromAccount, final String toAccount, final Double amount);

    BankAccount getBankAccountByAccountNumber(final String accountNumber);
}
