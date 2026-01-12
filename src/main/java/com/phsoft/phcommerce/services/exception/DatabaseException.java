package com.phsoft.phcommerce.services.exception;

public class DatabaseException extends RuntimeException {
    
    public DatabaseException(String err) {
        super(err);
    }
}
