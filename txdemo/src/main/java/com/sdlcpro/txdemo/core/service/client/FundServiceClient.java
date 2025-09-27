package com.sdlcpro.txdemo.core.service.client;


import com.sdlcpro.txdemo.core.param.FundTransferParam;
import com.sdlcpro.txdemo.core.param.TransactionParam;
import com.sdlcpro.txdemo.data.entity.BankAccount;

public interface FundServiceClient {

    void credit(final TransactionParam param);

    void debit(final TransactionParam param);

    void transferFunds(final FundTransferParam param);

    BankAccount getBankAccountByAccountNumber(final String accountNumber);
}
