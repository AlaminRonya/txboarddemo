package com.sdlcpro.txdemo.presenter;

import com.sdlcpro.txdemo.common.ApiResponseDto;
import com.sdlcpro.txdemo.common.PageableDto;
import com.sdlcpro.txdemo.common.PaginationResponse;
import com.sdlcpro.txdemo.core.dto.AccountTransactionDto;
import com.sdlcpro.txdemo.core.param.FundTransferParam;
import com.sdlcpro.txdemo.core.param.TransactionParam;
import com.sdlcpro.txdemo.core.service.TransactionService;
import com.sdlcpro.txdemo.enums.TransactionType;
import com.sdlcpro.txdemo.presenter.api.TransactionApi;
import com.sdlcpro.txdemo.presenter.common.AbstractController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
public class TransactionController extends AbstractController implements TransactionApi {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @Override
    public ResponseEntity<ApiResponseDto<List<AccountTransactionDto>>> save(FundTransferParam param) {
        return buildResponse("Bank account created successfully", transactionService.transferFunds(param));
    }

    @Override
    public ResponseEntity<ApiResponseDto<List<AccountTransactionDto>>> findAll() {
        return buildResponse("Fetched all bank accounts", transactionService.getAccountTransactions());
    }

    @Override
    public ResponseEntity<PaginationResponse<AccountTransactionDto>> findByPagination(PageableDto param) {
        return buildPaginatedResponse("Fetched all bank accounts", transactionService.getAccountTransactions(param));
    }

    @Override
    public ResponseEntity<PaginationResponse<AccountTransactionDto>> filteringByPagination(PageableDto param, Set<TransactionType> transactionTypes, Set<String> accountNumbers, Double minAmount, Double maxAmount) {
        return buildPaginatedResponse("Fetched all bank accounts", transactionService.getAccountTransactionsBySearching(param, transactionTypes, accountNumbers, minAmount, maxAmount));
    }

    @Override
    public ResponseEntity<ApiResponseDto<AccountTransactionDto>> findById(UUID id) {
        return buildResponse("Fetched bank account by id", transactionService.findAccountTransactionById(id));
    }

    @Override
    public ResponseEntity<ApiResponseDto<String>> deleteById(UUID id) {
        transactionService.deleteUserInfoById(id);
        return buildResponse("Bank account deleted successfully", "Deleted ID: " + id);
    }

    @Override
    public ResponseEntity<ApiResponseDto<AccountTransactionDto>> debit(TransactionParam param) {
        return buildResponse("Bank account created successfully", transactionService.debit(param));
    }

    @Override
    public ResponseEntity<ApiResponseDto<AccountTransactionDto>> credit(TransactionParam param) {
        return buildResponse("Bank account created successfully", transactionService.credit(param));
    }

//    public ResponseEntity<ApiResponseDto<PaginationResponse<BankAccountDto>>> getPaginated(int page, int size, String sort) {
//        var pageData = bankAccountService.getPaginatedBankAccounts(page, size, sort);
//        return buildPaginatedResponse("Fetched paginated bank accounts", pageData);
//    }
}
