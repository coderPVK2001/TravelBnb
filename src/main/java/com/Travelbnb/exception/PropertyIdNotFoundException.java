package com.Travelbnb.exception;

public class PropertyIdNotFoundException extends RuntimeException{
    public PropertyIdNotFoundException(String message) {
        super(message);
    }
}
