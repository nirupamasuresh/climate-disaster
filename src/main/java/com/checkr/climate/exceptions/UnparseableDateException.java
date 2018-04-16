package com.checkr.climate.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Unparseable date exception for incorrect dates
 */
@ResponseStatus(BAD_REQUEST)
public class UnparseableDateException extends RuntimeException {

    /**
     * Exception handles incorrect date format
     *
     * @param exception exception message
     */
    public UnparseableDateException(String exception) {
        super(exception);
    }
}
