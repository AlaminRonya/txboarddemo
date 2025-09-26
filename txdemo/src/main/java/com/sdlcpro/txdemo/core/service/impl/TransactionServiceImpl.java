package com.sdlcpro.txdemo.core.service.impl;

import com.sdlcpro.txdemo.core.service.client.FundServiceClient;
import com.sdlcpro.txdemo.core.service.TransactionService;
import com.sdlcpro.txdemo.data.entity.AccountTransaction;
import com.sdlcpro.txdemo.data.repository.AccountTransactionRepository;
import com.sdlcpro.txdemo.enums.TransactionType;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionServiceImpl extends AbstractBaseService<AccountTransaction, UUID> implements TransactionService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final AccountTransactionRepository accountTransactionRepository;

    private final FundServiceClient fundServiceClient;

    public TransactionServiceImpl(JpaRepository<AccountTransaction, UUID> repository, AccountTransactionRepository accountTransactionRepository, FundServiceClient fundServiceClient) {
        super(repository);
        this.accountTransactionRepository = accountTransactionRepository;
        this.fundServiceClient = fundServiceClient;
    }

    @Override
    @Transactional
    public void credit(String accountNumber, Double amount) {

        saveAccountTransaction(accountNumber, amount, TransactionType.CREDIT,  "Credit to account " + accountNumber);

        log.info("Credited {} to account {}", amount, accountNumber);
    }

    @Override
    @Transactional
    public void debit(String accountNumber, Double amount) {

        saveAccountTransaction(accountNumber, amount, TransactionType.DEBIT,  "Debit from account " + accountNumber);

        log.info("Debited {} from account {}", amount, accountNumber);
    }

    @Override
    @Transactional
    public void transferFunds(String fromAccount, String toAccount, Double amount) {

        saveAccountTransaction(fromAccount, amount, TransactionType.DEBIT,  "Debit from account " + fromAccount);

        saveAccountTransaction(toAccount, amount, TransactionType.CREDIT,  "Credit from account " + toAccount);

        fundServiceClient.transferFunds(fromAccount, toAccount, amount);

        log.info("Transferred {} from account {} to account {}", amount, fromAccount, toAccount);
    }

    private void saveAccountTransaction(String accountNumber, Double amount, TransactionType transactionType, String description) {

        var bankAccount = fundServiceClient.getBankAccountByAccountNumber(accountNumber);

        AccountTransaction transaction = AccountTransaction.builder()
                .bankAccount(bankAccount)
                .amount(amount)
                .transactionType(transactionType)
                .description(description)
                .build();

        accountTransactionRepository.save(transaction);
    }
}
