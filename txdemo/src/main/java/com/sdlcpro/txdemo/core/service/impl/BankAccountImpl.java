package com.sdlcpro.txdemo.core.service.impl;

import com.sdlcpro.txdemo.common.PageableDto;
import com.sdlcpro.txdemo.core.dto.BankAccountDto;
import com.sdlcpro.txdemo.core.mapper.ResponseBankAccountMapper;
import com.sdlcpro.txdemo.core.param.BankAccountParam;
import com.sdlcpro.txdemo.core.param.FundTransferParam;
import com.sdlcpro.txdemo.core.param.TransactionParam;
import com.sdlcpro.txdemo.core.service.BankAccountService;
import com.sdlcpro.txdemo.core.service.client.FundServiceClient;
import com.sdlcpro.txdemo.core.service.client.UserServiceClient;
import com.sdlcpro.txdemo.data.entity.BankAccount;
import com.sdlcpro.txdemo.data.entity.UserInfo;
import com.sdlcpro.txdemo.data.repository.BankAccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class BankAccountImpl extends AbstractBaseService<BankAccount, UUID> implements BankAccountService, FundServiceClient {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final BankAccountRepository bankAccountRepository;

    private final ResponseBankAccountMapper responseBankAccountMapper;

    private final UserServiceClient userServiceClient;

    public BankAccountImpl(JpaRepository<BankAccount, UUID> repository, BankAccountRepository bankAccountRepository, ResponseBankAccountMapper responseBankAccountMapper, UserServiceClient userServiceClient) {
        super(repository);
        this.bankAccountRepository = bankAccountRepository;
        this.responseBankAccountMapper = responseBankAccountMapper;
        this.userServiceClient = userServiceClient;
    }

    @Transactional
    @Override
    public BankAccountDto saveBank(BankAccountParam param){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        UserInfo userInfo = userServiceClient.getUserInfoById(param.userInfoId());

        var bankAccountBuilder = BankAccount.builder()
                .accountType(param.accountType())
                .bank(param.bank())
                .userInfo(userInfo)
                .balance(param.balance()).build();

        return responseBankAccountMapper.apply(super.save(bankAccountBuilder));
    }

    @Override
    public List<BankAccountDto> getBankAccounts(){

        return super.findAll().stream().map(responseBankAccountMapper).toList();
    }

    @Override
    public Page<BankAccountDto> getBankAccounts(PageableDto pageableDto) {

        return super.findAll(pageableDto.getPageable()).map(responseBankAccountMapper);
    }

    @Override
    public BankAccountDto findBankAccountById(UUID id){

        return responseBankAccountMapper.apply(super.findById(id).orElseThrow(()-> new EntityNotFoundException(i18n("x0.not.found", "bank.account"))));
    }

    @Override
    public void deleteBankAccountById(UUID id){
        try {
            super.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void credit(TransactionParam param) {

        validateAccountNumber(param.accountNumber());

        validateAmount(param.amount(), "credit");

        var bankAccount = getBankAccountByAccountNumber(param.accountNumber());

        bankAccount.setBalance(bankAccount.getBalance() + param.amount());
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void debit(TransactionParam param) {

        validateAccountNumber(param.accountNumber());

        validateAmount(param.amount(), "debit");

        var bankAccount = getBankAccountByAccountNumber(param.accountNumber());

        if (bankAccount.getBalance() < param.amount()) {

            throw new IllegalArgumentException(i18n("insufficient.funds.in.account.x0", param.accountNumber()));
        }

        bankAccount.setBalance(bankAccount.getBalance() - param.amount());
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void transferFunds(FundTransferParam param) {

        validateAccountNumber(param.fromAccountNumber());

        validateAccountNumber(param.toAccountNumber());

        validateAmount(param.amount(), "fund");

        debit(new TransactionParam(param.fromAccountNumber(), param.amount()));

        credit(new TransactionParam(param.toAccountNumber(), param.amount()));
    }

    @Override
    public BankAccount getBankAccountByAccountNumber(String accountNumber) {

        validateAccountNumber(accountNumber);

        return bankAccountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new IllegalArgumentException(i18n("invalid.account.number.x0",accountNumber)));
    }

    private void validateAccountNumber(final String accountNumber) {

        if (accountNumber == null || accountNumber.isBlank()) {

            throw new IllegalArgumentException(i18n("invalid.account.number.x0", accountNumber));
        }
    }

    private void validateAmount(final Double amount, final String operation) {

        if (amount == null || amount <= 0) {

            throw new IllegalArgumentException(i18n("x0.amount.must.be.positive", operation));
        }
    }


}