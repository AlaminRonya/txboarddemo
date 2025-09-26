package com.sdlcpro.txdemo.core.service;

public interface TransactionService {
    void credit(final String accountNumber, final Double amount);
    void debit(final String accountNumber, final Double amount);
    void transferFunds(final String fromAccount, final String toAccount, final Double amount);
}

