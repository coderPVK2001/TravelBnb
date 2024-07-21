package com.Travelbnb.exception;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ErrorDetails {

    private String message;
    private LocalDate date;
    private String request;
}
