package com.sdlcpro.txdemo.common;

import com.sdlcpro.txdemo.utils.common.LocalDateFormatPattern;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ErrorResponseDto<T>(
        int statusCode,
        String statusMessage,
        String message,
        T payload,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LocalDateFormatPattern.pattern)
        LocalDateTime timestamp
) {}