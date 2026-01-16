package com.phsoft.phcommerce.controller.handlers;

import java.time.Instant;

import com.phsoft.phcommerce.dto.BeanValidationError;
import com.phsoft.phcommerce.dto.FieldError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.phsoft.phcommerce.dto.CustomError;
import com.phsoft.phcommerce.services.exception.DatabaseException;
import com.phsoft.phcommerce.services.exception.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * Método que lida com exceções do tipo recurso não encontrado.
     * Retorna uma ResponseEntity parametrizada com o Dto de error customizado.
     * @param e Exceção personalizada lançada pelo service
     * @param request objeto representando a requisição
     * @return
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    /**
     * Método que lida com exceções do tipo recurso não encontrado.
     * Retorna uma ResponseEntity parametrizada com o Dto de error customizado.
     * @param e Exceção personalizada lançada pelo service
     * @param request objeto representando a requisição
     * @return
     */
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<CustomError> databaseException(DatabaseException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    /**
     * Método que lida com exceções lançadas devido à falha de validação no ProductDto
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> beanValidationException(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        BeanValidationError err = new BeanValidationError(Instant.now(), status.value(), "Erro de validação de dados",
                request.getRequestURI());

        // Itera sobre a lista de erros e adiciona ao erro dto
        e.getBindingResult().getFieldErrors().forEach(x -> err.addError(new FieldError(x.getField(),
                x.getDefaultMessage())));

        return ResponseEntity.status(status).body(err);
    }


}
