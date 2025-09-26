package com.sdlcpro.txdemo.exception.validators;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RequestValidator<T> {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    public void validate(T objectValidate) {
        Set<ConstraintViolation<T>> violations = validator.validate(objectValidate);
        Map<String, String> errorData = violations.stream()
                .collect(Collectors.toMap(
                        violation -> violation.getPropertyPath().toString().trim(),
                        ConstraintViolation::getMessage,
                        (existingValue, newValue) -> existingValue + ", " + newValue // Handle key collisions
                ));
        if (!errorData.isEmpty()) {
            throw new RequestValidatorException(errorData);
        }
    }
}