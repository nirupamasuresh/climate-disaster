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

/**
 * Exception handling for unparseable date
 */
@ControllerAdvice
public class DisasterExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Exception to handle date parsing
     *
     * @param ex      exception
     * @param request request
     * @return Response Entity with error details and status code 400
     */
    @ExceptionHandler(UnparseableDateException.class)
    public ResponseEntity<ErrorDetails> dateParsingException(UnparseableDateException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
