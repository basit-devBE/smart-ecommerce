package com.example.smart_ecommerce.global.exceptions;

public class EmailAlreadyExist extends RuntimeException {
    public EmailAlreadyExist(String message) {
        super(message);
    }
}
