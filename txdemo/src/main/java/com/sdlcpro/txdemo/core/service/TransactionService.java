package com.sdlcpro.txdemo.core.service;

import com.sdlcpro.txdemo.common.PageableDto;
import com.sdlcpro.txdemo.core.dto.AccountTransactionDto;
import com.sdlcpro.txdemo.core.dto.UserInfoDto;
import com.sdlcpro.txdemo.core.param.FundTransferParam;
import com.sdlcpro.txdemo.core.param.TransactionParam;
import com.sdlcpro.txdemo.core.param.UserInfoParam;
import com.sdlcpro.txdemo.enums.TransactionType;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface TransactionService {
    AccountTransactionDto credit(final TransactionParam param);
    AccountTransactionDto debit(final TransactionParam param);
    List<AccountTransactionDto> transferFunds(final FundTransferParam param);

    List<AccountTransactionDto> getAccountTransactions();

    Page<AccountTransactionDto> getAccountTransactions(final PageableDto pageableDto);
    Page<AccountTransactionDto> getAccountTransactionsBySearching(final PageableDto pageableDto,  final Set<TransactionType> transactionTypes,
                                                                   final Set<String> accountNumbers,
                                                                  final Double minAmount,
                                                                  final Double maxAmount);

    AccountTransactionDto findAccountTransactionById(final UUID id);

    void deleteUserInfoById(final UUID id);
}

