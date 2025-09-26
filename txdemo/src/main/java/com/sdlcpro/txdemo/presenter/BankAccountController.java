package com.sdlcpro.txdemo.presenter;

import com.sdlcpro.txdemo.common.ApiResponseDto;
import com.sdlcpro.txdemo.common.PageableDto;
import com.sdlcpro.txdemo.common.PaginationResponse;
import com.sdlcpro.txdemo.core.dto.BankAccountDto;
import com.sdlcpro.txdemo.core.param.BankAccountParam;
import com.sdlcpro.txdemo.core.service.BankAccountService;
import com.sdlcpro.txdemo.presenter.api.BankAccountApi;
import com.sdlcpro.txdemo.presenter.common.AbstractController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class BankAccountController extends AbstractController implements BankAccountApi {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @Override
    public ResponseEntity<ApiResponseDto<BankAccountDto>> save(BankAccountParam param) {
        return buildResponse("Bank account created successfully", bankAccountService.saveBank(param));
    }

    @Override
    public ResponseEntity<ApiResponseDto<List<BankAccountDto>>> findAll() {
        return buildResponse("Fetched all bank accounts", bankAccountService.getBankAccounts());
    }

    @Override
    public ResponseEntity<PaginationResponse<BankAccountDto>> findByPagination(PageableDto param) {
        return buildPaginatedResponse("Fetched all bank accounts", bankAccountService.getBankAccounts(param));
    }

    @Override
    public ResponseEntity<ApiResponseDto<BankAccountDto>> findById(UUID id) {
        return buildResponse("Fetched bank account by id", bankAccountService.findBankAccountById(id));
    }

    @Override
    public ResponseEntity<ApiResponseDto<String>> deleteById(UUID id) {
        bankAccountService.deleteBankAccountById(id);
        return buildResponse("Bank account deleted successfully", "Deleted ID: " + id);
    }

//    public ResponseEntity<ApiResponseDto<PaginationResponse<BankAccountDto>>> getPaginated(int page, int size, String sort) {
//        var pageData = bankAccountService.getPaginatedBankAccounts(page, size, sort);
//        return buildPaginatedResponse("Fetched paginated bank accounts", pageData);
//    }
}
