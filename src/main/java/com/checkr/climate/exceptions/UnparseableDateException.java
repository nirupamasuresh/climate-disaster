package com.checkr.climate.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnparseableDateException extends RuntimeException{
    public UnparseableDateException(String exception) {
        super(exception);
    }
}
