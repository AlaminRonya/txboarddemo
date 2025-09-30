package com.sdlcpro.txdemo.exception;

import com.sdlcpro.txdemo.common.ErrorResponseDto;
import com.sdlcpro.txdemo.exception.handler.BusinessException;
import com.sdlcpro.txdemo.exception.handler.ErrorFieldValidationResponse;
import com.sdlcpro.txdemo.exception.handler.ServiceException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class ApplicationExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorResponseDto<Object>> handleServiceException(ServiceException ex) {

        log.error("ServiceException occurred: {}", ex.getMessage(), ex);
        ErrorResponseDto<Object> body = new ErrorResponseDto<>(
                INTERNAL_SERVER_ERROR.value(),
                INTERNAL_SERVER_ERROR.name(),
                ex.getMessage(),
                null,
                LocalDateTime.now()
        );
        return ResponseEntity.status(BAD_REQUEST).body(body);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto<Object>> handleIllegalArgument(IllegalArgumentException ex) {

        log.warn("IllegalArgumentException: {}", ex.getMessage(), ex);
        ErrorResponseDto<Object> body = new ErrorResponseDto<>(
                BAD_REQUEST.value(),
                BAD_REQUEST.name(),
                ex.getMessage(),
                null,
                LocalDateTime.now()
        );
        return ResponseEntity.status(BAD_REQUEST).body(body);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponseDto<ErrorFieldValidationResponse>> handleBusiness(BusinessException ex) {
        ErrorFieldValidationResponse payload = ErrorFieldValidationResponse.builder()
                .code(ex.getErrorCode().getCode())
                .message(ex.getMessage())
                .build();
        log.info("BusinessException: {}", payload);
        log.info("BusinessException: {}", payload);
        log.info("BusinessException: {}", payload);
        log.info("BusinessException: {}", payload);
        log.info("BusinessException: {}", payload);
        log.info("BusinessException: {}", payload);
        log.debug("Stack trace:", ex);

        ErrorResponseDto<ErrorFieldValidationResponse> body = new ErrorResponseDto<>(
                ex.getErrorCode().getStatus() != null ? ex.getErrorCode().getStatus().value() : BAD_REQUEST.value(),
                ex.getErrorCode().getStatus() != null ? ex.getErrorCode().getStatus().name() : BAD_REQUEST.name(),
                ex.getMessage(),
                payload,
                LocalDateTime.now()
        );
        return ResponseEntity.status(BAD_REQUEST).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto<ErrorFieldValidationResponse>> handleValidationException(MethodArgumentNotValidException ex) {
        List<ErrorFieldValidationResponse.ValidationError> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorCode = error.getDefaultMessage();
            errors.add(ErrorFieldValidationResponse.ValidationError.builder()
                    .field(fieldName)
                    .code(errorCode)
                    .message(errorCode)
                    .build());
        });
        ErrorFieldValidationResponse payload = ErrorFieldValidationResponse.builder()
                .validationErrors(errors)
                .build();
        log.warn("Validation failed: {}", errors);

        ErrorResponseDto<ErrorFieldValidationResponse> body = new ErrorResponseDto<>(
                BAD_REQUEST.value(),
                BAD_REQUEST.name(),
                "One or more fields are invalid",
                payload,
                LocalDateTime.now()
        );
        return ResponseEntity.status(BAD_REQUEST).body(body);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseDto<ErrorFieldValidationResponse>> handleEntityNotFound(EntityNotFoundException ex) {
        log.warn("EntityNotFoundException: {}", ex.getMessage(), ex);
        ErrorFieldValidationResponse payload = ErrorFieldValidationResponse.builder()
                .code("NOT_FOUND")
                .message(ex.getMessage())
                .build();
        ErrorResponseDto<ErrorFieldValidationResponse> body = new ErrorResponseDto<>(
                NOT_FOUND.value(),
                NOT_FOUND.name(),
                ex.getMessage(),
                payload,
                LocalDateTime.now()
        );
        return ResponseEntity.status(BAD_REQUEST).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto<Object>> handleGlobalException(Exception ex) {
        log.error("Unhandled exception caught: {}", ex.getMessage(), ex);
        ErrorResponseDto<Object> body = new ErrorResponseDto<>(
                INTERNAL_SERVER_ERROR.value(),
                INTERNAL_SERVER_ERROR.name(),
                ex.getMessage() != null ? ex.getMessage() : "Unexpected error occurred",
                null,
                LocalDateTime.now()
        );
        return ResponseEntity.status(BAD_REQUEST).body(body);
    }
}
