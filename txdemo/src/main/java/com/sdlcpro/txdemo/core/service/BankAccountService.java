package com.sdlcpro.txdemo.core.service;

import com.sdlcpro.txdemo.common.PageableDto;
import com.sdlcpro.txdemo.core.dto.BankAccountDto;
import com.sdlcpro.txdemo.core.param.BankAccountParam;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;


public interface BankAccountService  {

    BankAccountDto saveBank(final BankAccountParam param);

    List<BankAccountDto> getBankAccounts();

    Page<BankAccountDto> getBankAccounts(final PageableDto pageableDto);

    BankAccountDto findBankAccountById(final UUID id);

    void deleteBankAccountById(final UUID id);
}
