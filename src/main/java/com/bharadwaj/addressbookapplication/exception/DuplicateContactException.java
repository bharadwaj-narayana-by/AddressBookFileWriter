package com.bharadwaj.addressbookapplication.exception;

public class DuplicateContactException extends RuntimeException{
    public DuplicateContactException(String message) {
        super(message);
    }
}
