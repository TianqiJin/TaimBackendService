package com.taim.taimbackendservice.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String msg) {
        super(msg);
    }

    public ResourceNotFoundException(String msg, Exception ex) {
        super(msg, ex);
    }
}
