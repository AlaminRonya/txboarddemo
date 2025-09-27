package com.sdlcpro.txdemo.core.service.impl;

import com.sdlcpro.txdemo.common.PageableDto;
import com.sdlcpro.txdemo.core.dto.AccountTransactionDto;
import com.sdlcpro.txdemo.core.mapper.ResponseAccountTransactionMapper;
import com.sdlcpro.txdemo.core.param.FundTransferParam;
import com.sdlcpro.txdemo.core.param.TransactionParam;
import com.sdlcpro.txdemo.core.service.client.FundServiceClient;
import com.sdlcpro.txdemo.core.service.TransactionService;
import com.sdlcpro.txdemo.data.entity.AccountTransaction;
import com.sdlcpro.txdemo.data.repository.AccountTransactionRepository;
import com.sdlcpro.txdemo.data.specification.AccountTransactionSpecification;
import com.sdlcpro.txdemo.enums.TransactionType;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TransactionServiceImpl extends AbstractBaseService<AccountTransaction, UUID> implements TransactionService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final AccountTransactionRepository accountTransactionRepository;

    private final FundServiceClient fundServiceClient;

    private final ResponseAccountTransactionMapper responseAccountTransactionMapper;

    public TransactionServiceImpl(JpaRepository<AccountTransaction, UUID> repository, AccountTransactionRepository accountTransactionRepository, FundServiceClient fundServiceClient, ResponseAccountTransactionMapper responseAccountTransactionMapper) {
        super(repository);
        this.accountTransactionRepository = accountTransactionRepository;
        this.fundServiceClient = fundServiceClient;
        this.responseAccountTransactionMapper = responseAccountTransactionMapper;
    }

    @Override
    @Transactional
    public AccountTransactionDto credit(TransactionParam param) {

        AccountTransaction accountTransaction = saveAccountTransaction(param.accountNumber(), param.amount(), TransactionType.CREDIT, "Credit to account " + param.accountNumber());

        log.info("Credited {} to account {}", param.amount(), param.accountNumber());
        return responseAccountTransactionMapper.apply(accountTransaction);
    }

    @Override
    @Transactional
    public AccountTransactionDto debit(TransactionParam param) {

        AccountTransaction accountTransaction = saveAccountTransaction(param.accountNumber(), param.amount(), TransactionType.DEBIT,  "Debit from account " + param.accountNumber());

        log.info("Debited {} from account {}", param.amount(), param.accountNumber());
        return responseAccountTransactionMapper.apply(accountTransaction);
    }

    @Override
    @Transactional
    public List<AccountTransactionDto> transferFunds(FundTransferParam param) {
        List<AccountTransactionDto> accountTransactionDtoList = new ArrayList<>();

        AccountTransaction fromAccountTransaction = saveAccountTransaction(param.fromAccountNumber(), param.amount(), TransactionType.TRANSFER, "Fund Transfer to account: " + param.toAccountNumber());
        accountTransactionDtoList.add(responseAccountTransactionMapper.apply(fromAccountTransaction));

        AccountTransaction toAccountTransaction = saveAccountTransaction(param.toAccountNumber(), param.amount(), TransactionType.TRANSFER, "Fund Transfer from account:" + param.fromAccountNumber());
        accountTransactionDtoList.add(responseAccountTransactionMapper.apply(toAccountTransaction));

        fundServiceClient.transferFunds(param);


        log.info("Transferred {} from account {} to account {}", param.amount(), param.fromAccountNumber(), param.toAccountNumber());
        return accountTransactionDtoList;
    }

    @Override
    public List<AccountTransactionDto> getAccountTransactions() {
        return super.findAll().stream().map(responseAccountTransactionMapper).toList();
    }

    @Override
    public Page<AccountTransactionDto> getAccountTransactions(PageableDto pageableDto) {
        return  super.findAll(pageableDto.getPageable()).map(responseAccountTransactionMapper);
    }

    @Override
    public Page<AccountTransactionDto> getAccountTransactionsBySearching(PageableDto pageableDto, Set<TransactionType> transactionTypes, Set<String> accountNumbers, Double minAmount, Double maxAmount) {
        Specification<AccountTransaction> spec = Specification.allOf(
                AccountTransactionSpecification.byListOfTransactionTypes(transactionTypes))
                .and(AccountTransactionSpecification.byListOfAccountNumbers(accountNumbers))
                .and(AccountTransactionSpecification.byTransactionRance(minAmount, maxAmount));

        return accountTransactionRepository.findAll(spec, pageableDto.getPageable()).map(responseAccountTransactionMapper);
    }



    @Override
    public AccountTransactionDto findAccountTransactionById(UUID id) {
        return responseAccountTransactionMapper.apply(super.findById(id).orElseThrow(()-> new EntityNotFoundException(i18n("x0.not.found", "bank.account"))));
    }

    @Override
    public void deleteUserInfoById(UUID id) {
        try {
            super.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private AccountTransaction saveAccountTransaction(String accountNumber, Double amount, TransactionType transactionType, String description) {

        var bankAccount = fundServiceClient.getBankAccountByAccountNumber(accountNumber);

        AccountTransaction transaction = AccountTransaction.builder()
                .bankAccount(bankAccount)
                .amount(amount)
                .transactionType(transactionType)
                .description(description)
                .build();

        return super.save(transaction);
    }
}
