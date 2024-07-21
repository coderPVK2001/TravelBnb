package com.Travelbnb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;

@ControllerAdvice
public class Exceptionshandling {

    @ExceptionHandler(PropertyIdNotFoundException.class)
    public ResponseEntity<?> propertyIdExceptionHandling(
            PropertyIdNotFoundException propertyIdNotFoundException,
            WebRequest webRequest
    ){
        ErrorDetails errorDetails=new ErrorDetails();
        errorDetails.setMessage(propertyIdNotFoundException.getMessage());
        errorDetails.setDate(LocalDate.now());
        errorDetails.setRequest(webRequest.getDescription(true));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReviewAlreadyGivenException.class)
    public ResponseEntity<?> reviewGiven(
            ReviewAlreadyGivenException reviewAlreadyGivenException,
            WebRequest webRequest
    ){
        ErrorDetails errorDetails=new ErrorDetails();
        errorDetails.setMessage(reviewAlreadyGivenException.getMessage());
        errorDetails.setDate(LocalDate.now());
        errorDetails.setRequest(webRequest.getDescription(true));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<?> reviewNotFound(
            ReviewNotFoundException reviewNotFoundException,
            WebRequest webRequest
    ){
        ErrorDetails errorDetails=new ErrorDetails();
        errorDetails.setMessage(reviewNotFoundException.getMessage());
        errorDetails.setDate(LocalDate.now());
        errorDetails.setRequest(webRequest.getDescription(true));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FavouriteException.class)
    public ResponseEntity<?> favouriteException(
            FavouriteException favouriteException,
            WebRequest webRequest
    ){
        ErrorDetails errorDetails=new ErrorDetails();
        errorDetails.setMessage(favouriteException.getMessage());
        errorDetails.setDate(LocalDate.now());
        errorDetails.setRequest(webRequest.getDescription(true));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
