package com.phsoft.phcommerce.controller.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.phsoft.phcommerce.dto.CustomError;
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


}
