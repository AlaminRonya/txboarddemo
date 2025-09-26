package com.sdlcpro.txdemo.presenter.common;

import com.sdlcpro.txdemo.common.ApiResponseDto;
import com.sdlcpro.txdemo.common.I18nService;
import com.sdlcpro.txdemo.common.PaginationResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public abstract class AbstractController implements I18nService {

    // ✅ Success response
    protected <T> ResponseEntity<ApiResponseDto<T>> buildResponse(final String message, final T data) {
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        HttpStatus.OK.value(),
                        HttpStatus.OK.name(),
                        message,
                        data,
                        LocalDateTime.now()
                )
        );
    }

    protected <T> ResponseEntity<PaginationResponse<T>> buildPaginatedResponse(final String message, final Page<T> pageData) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new PaginationResponse<>(
                        HttpStatus.OK.value(),
                        HttpStatus.OK.name(),
                        message,
                        pageData.getContent(),
                        pageData.getNumber(),
                        pageData.getSize(),
                        pageData.getTotalElements(),
                        pageData.getTotalPages(),
                        pageData.isLast(),
                        LocalDateTime.now()
                )
        );
    }

    // ✅ Error response
    protected ResponseEntity<ApiResponseDto<?>> buildErrorResponse(final HttpStatus status, final String message) {
        return ResponseEntity.status(status)
                .body(new ApiResponseDto<>(
                        status.value(),
                        status.name(),
                        message,
                        null,
                        LocalDateTime.now()
                ));
    }
}
