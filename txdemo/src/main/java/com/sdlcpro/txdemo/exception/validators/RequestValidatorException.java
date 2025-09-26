package com.sdlcpro.txdemo.exception.validators;

import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
public class RequestValidatorException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    private final Map<String, String> errors;

    public RequestValidatorException(Map<String, String> errors) {
        super(errors.toString());
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}