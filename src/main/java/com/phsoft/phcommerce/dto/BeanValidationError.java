package com.phsoft.phcommerce.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class BeanValidationError extends CustomError {

    // Lista com todos os erros de validação de um dto
    private List<FieldError> errors = new ArrayList<>();

    public BeanValidationError(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public void addError(FieldError fe) {
        errors.add(fe);
    }

    public List<FieldError> getErrors() {
        return errors;
    }
}
