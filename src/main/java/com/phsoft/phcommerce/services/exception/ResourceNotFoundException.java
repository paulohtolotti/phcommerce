package com.phsoft.phcommerce.services.exception;

public class ResourceNotFoundException extends RuntimeException{
    
    public ResourceNotFoundException(String err) {
        super(err);
    }
}
