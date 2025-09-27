package com.sdlcpro.txdemo.presenter.api;

import com.sdlcpro.txdemo.common.ApiResponseDto;
import com.sdlcpro.txdemo.common.ErrorResponseDto;
import com.sdlcpro.txdemo.common.PageableDto;
import com.sdlcpro.txdemo.common.PaginationResponse;
import com.sdlcpro.txdemo.core.dto.AccountTransactionDto;
import com.sdlcpro.txdemo.core.param.FundTransferParam;
import com.sdlcpro.txdemo.core.param.TransactionParam;
import com.sdlcpro.txdemo.enums.TransactionType;
import com.sdlcpro.txdemo.presenter.common.*;
import com.sdlcpro.txdemo.utils.url.UrlPath;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RequestMapping( value = UrlPath.Transaction.ROOT_PATH)
public interface TransactionApi extends GetApi<UUID, AccountTransactionDto>, CreateApi<FundTransferParam, List<AccountTransactionDto>>, GetAllApi<AccountTransactionDto>, GetPaginationApi<AccountTransactionDto>, DeleteApi<UUID, String> {
    @Override
    @Operation(summary = "find data by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Data not found",  content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @GetMapping(value = UrlPath.Transaction.TRANSACTION_IDENTIFIER)
    ResponseEntity<ApiResponseDto<AccountTransactionDto>> findById(@PathVariable("id") UUID id);

    @Override
    @Operation(summary = "find data by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Data not found",  content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping
    ResponseEntity<ApiResponseDto<List<AccountTransactionDto>>> save(@Valid @RequestBody FundTransferParam param);

    @Operation(summary = "Get All")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Data not found",  content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @GetMapping()
    @Override
    ResponseEntity<ApiResponseDto<List<AccountTransactionDto>>> findAll();

    @Operation(summary = "Get paginated list of bank accounts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PaginationResponse.class))}),
            @ApiResponse(responseCode = "400", description = "No bank accounts found", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @Parameters({
            @Parameter(name = "pageNo", in = ParameterIn.QUERY, description = "Page number, starting from 1", schema = @Schema(type = "integer", defaultValue = "1")),
            @Parameter(name = "pageSize", in = ParameterIn.QUERY, description = "Number of items per page", schema = @Schema(type = "integer", defaultValue = "20")),
            @Parameter(name = "fieldBy", in = ParameterIn.QUERY, description = "Field to sort by", schema = @Schema(type = "string", defaultValue = "createdAt")),
            @Parameter(name = "direction", in = ParameterIn.QUERY, description = "Sort direction (asc or desc)", schema = @Schema(type = "string", defaultValue = "desc"))
    })
    @GetMapping(value = UrlPath.Transaction.TRANSACTION_PAGINATION)
    ResponseEntity<PaginationResponse<AccountTransactionDto>> findByPagination(@ModelAttribute PageableDto param);

    @Operation(summary = "Get paginated list of bank accounts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PaginationResponse.class))}),
            @ApiResponse(responseCode = "400", description = "No bank accounts found", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @Parameters({
            @Parameter(name = "pageNo", in = ParameterIn.QUERY, description = "Page number, starting from 1", schema = @Schema(type = "integer", defaultValue = "1")),
            @Parameter(name = "pageSize", in = ParameterIn.QUERY, description = "Number of items per page", schema = @Schema(type = "integer", defaultValue = "20")),
            @Parameter(name = "fieldBy", in = ParameterIn.QUERY, description = "Field to sort by", schema = @Schema(type = "string", defaultValue = "createdAt")),
            @Parameter(name = "direction", in = ParameterIn.QUERY, description = "Sort direction (asc or desc)", schema = @Schema(type = "string", defaultValue = "desc"))
    })
    @GetMapping(value = UrlPath.Transaction.TRANSACTION_SEARCHING_PAGINATION)
    ResponseEntity<PaginationResponse<AccountTransactionDto>> filteringByPagination(@ModelAttribute PageableDto param, @RequestParam(required = false) Set<TransactionType> transactionTypes,
                                                                                    @RequestParam(required = false) Set<String> accountNumbers,
                                                                                    @RequestParam(required = false) Double minAmount,
                                                                                    @RequestParam(required = false) Double maxAmount);


    @Operation(summary = "delete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Data not found",  content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @DeleteMapping(value = UrlPath.Transaction.TRANSACTION_IDENTIFIER)
    @Override
   ResponseEntity<ApiResponseDto<String>> deleteById(@PathVariable("id") UUID id);

    @Operation(summary = "find data by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Data not found",  content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping(value = UrlPath.Transaction.TRANSACTION_DEBIT)
    ResponseEntity<ApiResponseDto<AccountTransactionDto>> debit(@Valid @RequestBody TransactionParam param);

    @Operation(summary = "find data by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Data not found",  content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping(value = UrlPath.Transaction.TRANSACTION_CREDIT)
    ResponseEntity<ApiResponseDto<AccountTransactionDto>> credit(@Valid @RequestBody TransactionParam param);
}
