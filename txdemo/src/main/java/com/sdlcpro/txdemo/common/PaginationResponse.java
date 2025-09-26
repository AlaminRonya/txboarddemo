package com.sdlcpro.txdemo.common;

import com.sdlcpro.txdemo.utils.common.LocalDateFormatPattern;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public record PaginationResponse<T>(
        int statusCode,
        String statusMessage,
        String message,
        List<T> payload,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean last,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LocalDateFormatPattern.pattern)
        LocalDateTime timestamp
) {}
