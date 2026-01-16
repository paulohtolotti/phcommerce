package com.phsoft.phcommerce.dto;

/*
*   Classe que representa um erro de validação de um DTO.
*   Possui um atributo que indica o campo em que o erro ocorreu e outro para a mensagem da validação.
 */
public class FieldError {
    private String fieldName;
    private String fieldMessage;

    public FieldError(String fieldName, String fieldMessage) {
        this.fieldName = fieldName;
        this.fieldMessage = fieldMessage;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldMessage() {
        return fieldMessage;
    }
}
