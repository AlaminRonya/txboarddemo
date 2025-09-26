package com.sdlcpro.txdemo.exception.handler;

import lombok.EqualsAndHashCode;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
public class ConflictHandlerException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String message;
    public ConflictHandlerException(String message) {
        super(message);
        this.message = message;
    }
}