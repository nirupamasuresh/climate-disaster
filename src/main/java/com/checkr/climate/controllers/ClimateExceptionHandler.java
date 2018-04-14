package com.checkr.climate.controllers;

import com.checkr.climate.entities.ErrorDetails;
import com.checkr.climate.exceptions.UnparseableDateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ClimateExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UnparseableDateException.class)
    public ResponseEntity<ErrorDetails> handleUserNotFoundException(UnparseableDateException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
