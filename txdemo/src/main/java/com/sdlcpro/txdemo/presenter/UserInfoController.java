package com.sdlcpro.txdemo.presenter;

import com.sdlcpro.txdemo.common.ApiResponseDto;
import com.sdlcpro.txdemo.common.PageableDto;
import com.sdlcpro.txdemo.common.PaginationResponse;
import com.sdlcpro.txdemo.core.dto.UserInfoDto;
import com.sdlcpro.txdemo.core.param.UserInfoParam;
import com.sdlcpro.txdemo.core.service.UserInfoService;
import com.sdlcpro.txdemo.presenter.api.UserInfoApi;
import com.sdlcpro.txdemo.presenter.common.AbstractController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class UserInfoController extends AbstractController implements UserInfoApi {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final UserInfoService userInfoService;

    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Override
    public ResponseEntity<ApiResponseDto<UserInfoDto>> save(UserInfoParam param) {
        return buildResponse("Bank account created successfully", userInfoService.saveUserInfo(param));
    }

    @Override
    public ResponseEntity<ApiResponseDto<List<UserInfoDto>>> findAll() {
        return buildResponse("Fetched all bank accounts", userInfoService.getUserInfos());
    }

    @Override
    public ResponseEntity<PaginationResponse<UserInfoDto>> findByPagination(PageableDto param) {
        return buildPaginatedResponse("Fetched all bank accounts", userInfoService.getUserInfos(param));
    }

    @Override
    public ResponseEntity<ApiResponseDto<UserInfoDto>> findById(UUID id) {
        return buildResponse("Fetched bank account by id", userInfoService.findUserInfoById(id));
    }

    @Override
    public ResponseEntity<ApiResponseDto<String>> deleteById(UUID id) {
        userInfoService.deleteUserInfoById(id);
        return buildResponse("Bank account deleted successfully", "Deleted ID: " + id);
    }

//    public ResponseEntity<ApiResponseDto<PaginationResponse<BankAccountDto>>> getPaginated(int page, int size, String sort) {
//        var pageData = bankAccountService.getPaginatedBankAccounts(page, size, sort);
//        return buildPaginatedResponse("Fetched paginated bank accounts", pageData);
//    }
}
