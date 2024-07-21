package com.Travelbnb.exception;

public class ReviewAlreadyGivenException extends RuntimeException{
    public ReviewAlreadyGivenException(String message) {
        super(message);
    }
}
